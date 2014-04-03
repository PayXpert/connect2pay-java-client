package com.payxpert.connect2pay.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.ProxyServer;
import com.ning.http.client.Realm;
import com.ning.http.client.Realm.AuthScheme;
import com.ning.http.client.Response;
import com.payxpert.connect2pay.client.requests.GenericRequest;
import com.payxpert.connect2pay.exception.HttpForbiddenException;
import com.payxpert.connect2pay.exception.HttpNotFoundException;

/**
 * REST Client for the payment page application. Should not be used directly,
 * use a Connect2payClient instead.
 * 
 * @author jsh
 * 
 */
public class Connect2payRESTClient {
  private AsyncHttpClient asyncHttpClient;
  private ProxyServer proxy;
  private Realm realm;
  private String url;
  private Map<String, String> parameters;
  private String body;
  private int timeout;

  private static final Logger logger = LoggerFactory.getLogger(Connect2payRESTClient.class);
  private static final int DEFAULT_TIMEOUT = 20000;

  private enum HttpMethod {
    GET, POST;
  }

  public Connect2payRESTClient() {
    this.parameters = new HashMap<String, String>();
    this.timeout = DEFAULT_TIMEOUT;
  }

  public Connect2payRESTClient setUrl(String url) {
    this.url = url;

    return this;
  }

  public void setParameters(GenericRequest<?> request) {
    Map<String, String> params = request.getRequestParameters();

    if (params != null) {
      for (String name : params.keySet()) {
        this.setParameter(name, params.get(name));
      }
    }
  }

  public Connect2payRESTClient setParameter(String name, String value) {
    this.parameters.put(name, value);

    return this;
  }

  public Connect2payRESTClient setBody(String body) {
    this.body = body;

    return this;
  }

  /**
   * Set the HTTP request timeout in milliseconds
   * 
   * @param timeout
   *          in milliseconds
   * @return The current Connect2payRESTClient for method chaining
   */
  public Connect2payRESTClient setTimeout(int timeout) {
    this.timeout = timeout;

    return this;
  }

  public Connect2payRESTClient setProxyServer(String proxy, Integer port) {
    return this.setProxyServer(proxy, port, null, null);
  }

  public Connect2payRESTClient setProxyServer(String proxy, Integer port, String proxyUser, String proxyPassword) {
    if (proxyUser != null && proxyPassword != null) {
      this.proxy = new ProxyServer(proxy, port, proxyUser, proxyPassword);
    } else {
      this.proxy = new ProxyServer(proxy, port);
    }

    return this;
  }

  public void skipProxyFor(String host) {
    if (this.proxy != null && host != null && !host.isEmpty()) {
      this.proxy.addNonProxyHost(host);
    }
  }

  public Connect2payRESTClient addBasicAuthentication(String originator, String apiKey) {
    this.realm = new Realm.RealmBuilder().setPrincipal(originator).setPassword(apiKey).setScheme(AuthScheme.BASIC)
        .setUsePreemptiveAuth(true).build();

    return this;
  }

  public String get() throws Exception {
    return this.request(HttpMethod.GET);
  }

  public String post() throws Exception {
    return this.request(HttpMethod.POST);
  }

  private String request(HttpMethod method) throws Exception {
    this.prepareHttpClient();
    String response = null;
    BoundRequestBuilder rBuilder = null;

    // Prepare the request according to the type
    if (HttpMethod.GET.equals(method)) {
      rBuilder = this.asyncHttpClient.prepareGet(this.url);
    } else {
      rBuilder = this.asyncHttpClient.preparePost(this.url);
      if (this.body != null && this.body.length() >= 0) {
        rBuilder.setBody(this.body.getBytes("utf-8"));
        rBuilder.setHeader("Content-Type", "application/json; charset=utf-8");
      } else {
        rBuilder.setHeader("Content-Length", "0");
      }
    }
    if (this.body != null) {
      logger.debug("Request body is: " + this.body);
    }
    // Add the parameters
    for (String param : this.parameters.keySet()) {
      rBuilder.addQueryParameter(param, this.parameters.get(param));
    }
    // Add the Basic authentication if defined
    if (this.realm != null) {
      rBuilder.setRealm(this.realm);
    }

    Future<String> fResponse = null;

    try {
      fResponse = rBuilder.execute(new C2PCompletionHandler());
      response = fResponse.get();
    } catch (ExecutionException e) {
      if (HttpNotFoundException.class.isAssignableFrom(e.getCause().getClass())) {
        throw (HttpNotFoundException) e.getCause();
      } else if (HttpForbiddenException.class.isAssignableFrom(e.getCause().getClass())) {
        throw (HttpForbiddenException) e.getCause();
      } else {
        throw e;
      }
    } catch (Exception e) {
      logger.error("Error during " + method.toString() + " request towards " + this.url + ": "
          + e.getClass().getSimpleName() + " - " + e.getMessage());
      e.printStackTrace();
      throw e;
    } finally {
      this.reset();
    }

    return response;
  }

  private void prepareHttpClient() {
    AsyncHttpClientConfig.Builder cBuilder = new AsyncHttpClientConfig.Builder();
    cBuilder.setRequestTimeoutInMs(this.timeout);

    if (this.proxy != null) {
      cBuilder.setProxyServer(this.proxy);
    }

    this.asyncHttpClient = new AsyncHttpClient(cBuilder.build());
  }

  private void reset() {
    this.body = null;
    this.parameters = new HashMap<String, String>();
    if (this.asyncHttpClient != null && !this.asyncHttpClient.isClosed()) {
      this.asyncHttpClient.close();
    }
  }

  private class C2PCompletionHandler extends AsyncCompletionHandler<String> {
    @Override
    public String onCompleted(Response response) throws Exception {
      String body = null;

      if (response != null) {
        if (new Integer(200).equals(response.getStatusCode())) {
          // The Content-type must be application/json otherwise we warn
          String contentType = response.getContentType();
          if (contentType == null || !contentType.startsWith("application/json")) {
            logger.warn("Connect2pay response content-type '" + response.getContentType()
                + "' is not the expected value 'application/json'.");
          }
          try {
            body = response.getResponseBody();
            logger.debug("Received response: " + body);
          } catch (Exception e) {
            logger.error("Error extracting response body: " + e.getMessage());
            e.printStackTrace();
          }
        } else {
          if (response.getStatusCode() == 403) {
            throw new HttpForbiddenException();
          } else if (response.getStatusCode() == 404) {
            throw new HttpNotFoundException();
          }
        }
      }

      return body;
    }
  }
}
