package com.payxpert.connect2pay.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.payxpert.connect2pay.client.requests.SubscriptionCancelRequest;
import com.payxpert.connect2pay.client.requests.TransactionRequest;
import com.payxpert.connect2pay.client.requests.TransactionStatusRequest;
import com.payxpert.connect2pay.client.response.SubscriptionCancelResponse;
import com.payxpert.connect2pay.client.response.TransactionResponse;
import com.payxpert.connect2pay.client.response.TransactionStatusResponse;
import com.payxpert.connect2pay.constants.APIRoute;
import com.payxpert.connect2pay.constants.ResultCode;
import com.payxpert.connect2pay.exception.HttpForbiddenException;
import com.payxpert.connect2pay.exception.HttpNotFoundException;
import com.payxpert.connect2pay.utils.Connect2payRESTClient;
import com.payxpert.connect2pay.utils.CryptoHelper;

/**
 * Client class for the payment page application.<br>
 * The normal transaction creation workflow is as follows:
 * <ul>
 * <li>Instantiate the {@link TransactionRequest} class</li>
 * <li>Set all the required parameters of the transaction</li>
 * <li>Validate the request object by calling {@link TransactionRequest#validate()}</li>
 * <li>Call {@link Connect2payClient#prepareTransaction(TransactionRequest)} to create the transaction</li>
 * <li>Call {@link TransactionResponse#getCustomerRedirectURL()} and redirect the customer to this URL</li>
 * <li>If receiving result via callback (recommended), use {@link Connect2payClient#handleCallbackStatus(String)} to
 * initialize the status from the POST request</li>
 * <li>When receiving the result via customer redirection, use
 * {@link Connect2payClient#handleRedirectStatus(String, String)} to initialize the status from the POST data</li>
 * </ul>
 * 
 * This class does not do any sanitization on received data. This must be done externally.<br>
 * Every text must be encoded as UTF-8 when passed to this class.<br>
 * 
 * @version 1.0.3 (20160512)
 * @author JsH <jsh@payxpert.com><br>
 *         Copyright 2011-2016 Payxpert
 * 
 */
public class Connect2payClient {
  private static final String AUTH_FAILED_JSON = "{\"code\":\"403\",\"message\":\"Authentication failed\"}";
  private static final String NOT_FOUND_JSON = "{\"code\":\"404\",\"message\":\"Page not found\"}";
  private Connect2payRESTClient httpClient;
  private String serviceUrl;

  private static final Logger logger = LoggerFactory.getLogger(Connect2payClient.class);

  /**
   * Instantiate a new Connect2pay client instance
   * 
   * @param serviceURL
   *          URL of the payment page application
   * @param originator
   *          Originator to use for authentication
   * @param apiKey
   *          API key of the originator
   */
  public Connect2payClient(String serviceURL, String originator, String apiKey) {
    if (serviceURL != null) {
      this.serviceUrl = serviceURL.replaceAll("/+$", "");
    }
    this.httpClient = new Connect2payRESTClient().addBasicAuthentication(originator, apiKey);
  }

  /**
   * This is used in blocking scenario. Client will time out after waiting this amount of time in milliseconds.
   * 
   * @param timeOutInMilliSeconds
   *          : time in MilliSeconds
   * @since Version 1.0
   */
  public void setTimeOutInMilliSeconds(int timeOutInMilliSeconds) {
    this.httpClient.setTimeout(timeOutInMilliSeconds);
  }

  /**
   * Allows to set outgoing proxy information.
   * 
   * @param host
   *          Proxy hostname
   * @param port
   *          Proxy TCP port
   */
  public void setProxy(String host, int port) {
    this.httpClient.setProxyServer(host, port);
  }

  /**
   * Allows to set outgoing authentified proxy information.
   * 
   * @param host
   *          Proxy hostname
   * @param port
   *          Proxy TCP port
   * @param user
   *          Proxy username
   * @param password
   *          Proxy password
   */
  public void setProxy(String host, int port, String user, String password) {
    this.httpClient.setProxyServer(host, port, user, password);
  }

  /**
   * Add a host to bypass proxy for. Can be called several subsequent times.
   * 
   * @param host
   *          The host to bypass proxy for
   */
  public void skipProxyFor(String host) {
    this.httpClient.skipProxyFor(host);
  }

  /**
   * Prepare a new transaction on the payment page application. This method will call the payment page application to
   * create a new transaction.
   * 
   * @return The TransactionResponse object or null on error
   */
  public TransactionResponse prepareTransaction(TransactionRequest request) throws Exception {
    if (request == null) {
      throw new NullPointerException();
    }

    this.httpClient.setUrl(this.serviceUrl + APIRoute.TRANS_PREPARE.getRoute()).setBody(request.toJson());
    if (logger.isDebugEnabled()) {
      logger.debug("Doing prepare transaction with body: " + request.toJson());
    }
    TransactionResponse response = null;
    try {
      String json = this.httpClient.post();
      response = new TransactionResponse().fromJson(json);
      if (response != null) {
        response.setServiceURL(this.serviceUrl);
      }
    } catch (HttpForbiddenException e) {
      response = new TransactionResponse().fromJson(AUTH_FAILED_JSON);
    } catch (Exception e) {
      logger.error("Prepare call failed: " + e.getClass().getSimpleName() + " - " + e.getMessage());
      throw e;
    }

    return response;
  }

  /**
   * Do a transaction status request on the payment page application.
   * 
   * @param request
   *          The transaction status request object to use
   * @return The TransactionStatus object of the transaction or null on error
   */
  public TransactionStatusResponse getTransactionStatus(TransactionStatusRequest request) throws Exception {
    if (request == null) {
      throw new NullPointerException();
    }

    String url = APIRoute.TRANS_STATUS.getRoute().replaceAll(":merchantToken", request.getMerchantToken());
    this.httpClient.setUrl(this.serviceUrl + url);
    if (logger.isDebugEnabled()) {
      logger.debug("Doing Transaction status request.");
    }
    TransactionStatusResponse response = null;
    try {
      String json = this.httpClient.get();
      response = new TransactionStatusResponse().fromJson(json);
      if (response != null) {
        response.setCode(ResultCode.SUCCESS);
      }
    } catch (HttpForbiddenException e) {
      response = new TransactionStatusResponse().fromJson(AUTH_FAILED_JSON);
    } catch (HttpNotFoundException e) {
      response = new TransactionStatusResponse().fromJson(NOT_FOUND_JSON);
    } catch (Exception e) {
      logger.error("Transaction status call failed: " + e.getClass().getSimpleName() + " - " + e.getMessage());
      throw e;
    }

    return response;
  }

  /**
   * Do a subscription cancel request on the payment page application.
   * 
   * @param request
   *          The subscription cancel request object to use
   * @return The subscription cancel response object or null on error
   */
  public SubscriptionCancelResponse cancelSubscription(SubscriptionCancelRequest request) throws Exception {
    if (request == null) {
      throw new NullPointerException();
    }

    String url = APIRoute.SUB_CANCEL.getRoute().replaceAll(":subscriptionID", request.getSubscriptionId().toString());
    this.httpClient.setUrl(this.serviceUrl + url).setBody(request.toJson());
    if (logger.isDebugEnabled()) {
      logger.debug("Doing Subscription cancel request.");
    }
    SubscriptionCancelResponse response = null;
    try {
      String json = this.httpClient.post();
      response = new SubscriptionCancelResponse().fromJson(json);
    } catch (HttpForbiddenException e) {
      response = new SubscriptionCancelResponse().fromJson(AUTH_FAILED_JSON);
    } catch (HttpNotFoundException e) {
      response = new SubscriptionCancelResponse().fromJson(NOT_FOUND_JSON);
    } catch (Exception e) {
      logger.error("Subscription cancel call failed: " + e.getClass().getSimpleName() + " - " + e.getMessage());
      throw e;
    }

    return response;
  }

  /**
   * Handle the data received by the POST done when the payment page redirects the customer to the merchant website.
   * 
   * @param encryptedData
   *          The content of the 'data' field posted
   * @param merchantToken
   *          The merchant token related to this transaction
   * @return The TransactionStatus object of the transaction or null on error
   * @throws Exception
   */
  public TransactionStatusResponse handleRedirectStatus(String encryptedData, String merchantToken) throws Exception {
    if (encryptedData == null || merchantToken == null) {
      throw new NullPointerException();
    }
    TransactionStatusResponse response = null;

    // Decrypt the JSON using the merchantToken as key
    String json = null;
    try {
      json = CryptoHelper.decryptAESURLBase64ToString(encryptedData, CryptoHelper.fromBase64(merchantToken));
    } catch (Exception e) {
      logger.error("Redirect status decrypting failed: " + e.getClass().getSimpleName() + " - " + e.getMessage());
      throw e;
    }

    if (json != null) {
      try {
        response = new TransactionStatusResponse().fromJson(json);
        if (response != null) {
          response.setCode(ResultCode.SUCCESS);
        }
      } catch (Exception e) {
        logger.error("Redirect status handling failed: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        throw e;
      }
    }

    return response;
  }

  /**
   * Handle the callback done by the payment page application after a transaction processing.
   * 
   * @param requestStream
   *          The received request InputStream
   * @return The TransactionStatus object of the transaction or null on error
   * @throws Exception
   */
  public TransactionStatusResponse handleCallbackStatus(InputStream requestStream) throws Exception {
    char[] buff = new char[1024];
    StringBuilder writer = new StringBuilder();

    Reader reader = null;
    try {
      reader = new InputStreamReader(requestStream, "UTF-8");
      int count = -1;
      do {
        count = reader.read(buff);
        if (count >= 0) {
          writer.append(buff, 0, count);
        }
      } while (count != -1);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return handleCallbackStatus(writer.toString());
  }

  /**
   * Handle the callback done by the payment page application after a transaction processing.
   * 
   * @param requestBody
   *          The body of the received request as String
   * @return The TransactionStatus object of the transaction or null on error
   * @throws Exception
   */
  public TransactionStatusResponse handleCallbackStatus(String requestBody) throws Exception {
    if (requestBody == null) {
      throw new NullPointerException();
    }
    TransactionStatusResponse response = null;

    try {
      response = new TransactionStatusResponse().fromJson(requestBody);
      if (response != null) {
        response.setCode(ResultCode.SUCCESS);
      }
    } catch (Exception e) {
      logger.error("Callback status handling failed: " + e.getClass().getSimpleName() + " - " + e.getMessage());
      throw e;
    }

    return response;
  }
}