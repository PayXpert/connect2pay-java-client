package com.payxpert.connect2pay.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.payxpert.connect2pay.client.requests.PaymentRequest;
import com.payxpert.connect2pay.client.requests.PaymentStatusRequest;
import com.payxpert.connect2pay.client.requests.SubscriptionCancelRequest;
import com.payxpert.connect2pay.client.requests.TransactionRefundRequest;
import com.payxpert.connect2pay.client.response.PaymentResponse;
import com.payxpert.connect2pay.client.response.PaymentStatusResponse;
import com.payxpert.connect2pay.client.response.SubscriptionCancelResponse;
import com.payxpert.connect2pay.client.response.TransactionRefundResponse;
import com.payxpert.connect2pay.constants.APIRoute;
import com.payxpert.connect2pay.constants.ResultCode;
import com.payxpert.connect2pay.exception.HttpForbiddenException;
import com.payxpert.connect2pay.exception.HttpNotFoundException;
import com.payxpert.connect2pay.utils.Connect2payRESTClient;
import com.payxpert.connect2pay.utils.CryptoHelper;

/**
 * Main client class for the payment page application.<br>
 * The normal payment creation workflow is as follows:
 * <ul>
 * <li>Instantiate the {@link PaymentRequest} class</li>
 * <li>Set all the required parameters of the payment</li>
 * <li>Validate the request object by calling {@link PaymentRequest#validate()}</li>
 * <li>Call {@link Connect2payClient#preparePayment(PaymentRequest)} to create the payment</li>
 * <li>Call {@link PaymentResponse#getCustomerRedirectURL()} and redirect the customer to this URL</li>
 * <li>If receiving result via callback (recommended), use {@link Connect2payClient#handleCallbackStatus(String)} to
 * initialize the status from the POST request</li>
 * <li>When receiving the result via customer redirection, use
 * {@link Connect2payClient#handleRedirectStatus(String, String)} to initialize the status from the POST data</li>
 * </ul>
 * 
 * This class does not do any sanitization on received data. This must be done externally.<br>
 * Every text must be encoded as UTF-8 when passed to this class.<br>
 * 
 * @version 1.0.7 (20170703)
 * @author JsH <jsh@payxpert.com><br>
 *         Copyright 2011-2017 PayXpert
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
   * Client will time out after waiting this amount of time in milliseconds.
   * 
   * @param timeOutInMilliSeconds
   *          : time in MilliSeconds
   * @since Version 1.0
   */
  public void setTimeOutInMilliSeconds(int timeOutInMilliSeconds) {
    this.httpClient.setTimeout(timeOutInMilliSeconds);
  }

  /**
   * Set the virtual host this request will use. May be required when using an outgoing proxy.
   * 
   * @since Version 1.0.6
   */
  public void setVirtualHost(String virtualHost) {
    this.httpClient.setVirtualHost(virtualHost);
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
   * Allows to set outgoing authenticated proxy information.
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
   * Use preparePayment instead
   */
  @Deprecated
  public PaymentResponse prepareTransaction(PaymentRequest request) throws Exception {
    return this.preparePayment(request);
  }

  /**
   * Prepare a new payment on the payment page application. This method will call the payment page application to create
   * a new payment.
   * 
   * @param request
   *          The PaymentRequest object
   * @return The PaymentResponse object or null on error
   */
  public PaymentResponse preparePayment(PaymentRequest request) throws Exception {
    if (request == null) {
      throw new NullPointerException();
    }

    this.httpClient.setUrl(this.serviceUrl + APIRoute.PAYMENT_PREPARE.getRoute()).setBody(request.toJson());
    if (logger.isDebugEnabled()) {
      logger.debug("Doing prepare payment with body: " + request.toJson());
    }

    PaymentResponse response = null;
    try {
      String json = this.httpClient.post();
      response = new PaymentResponse().fromJson(json);
      if (response != null) {
        response.setServiceURL(this.serviceUrl);
      }
    } catch (HttpForbiddenException e) {
      response = new PaymentResponse().fromJson(AUTH_FAILED_JSON);
    } catch (Exception e) {
      logger.error("Prepare payment call failed: " + e.getClass().getSimpleName() + " - " + e.getMessage());
      throw e;
    }

    return response;
  }

  /**
   * Use getPaymentStatus instead
   */
  @Deprecated
  public PaymentStatusResponse getTransactionStatus(PaymentStatusRequest request) throws Exception {
    return this.getPaymentStatus(request);
  }

  /**
   * Do a payment status request on the payment page application.
   * 
   * @param request
   *          The payment status request object to use
   * @return The PaymentStatusResponse object of the payment or null on error
   */
  public PaymentStatusResponse getPaymentStatus(PaymentStatusRequest request) throws Exception {
    if (request == null) {
      throw new NullPointerException();
    }

    String url = APIRoute.PAYMENT_STATUS.getRoute().replaceAll(":merchantToken", request.getMerchantToken());
    this.httpClient.setUrl(this.serviceUrl + url);
    this.httpClient.setParameter("apiVersion", request.getApiVersion());
    if (logger.isDebugEnabled()) {
      logger.debug("Doing Payment status request.");
    }

    PaymentStatusResponse response = null;
    try {
      String json = this.httpClient.get();
      response = new PaymentStatusResponse().fromJson(json);
      if (response != null) {
        response.setCode(ResultCode.SUCCESS);
      }
    } catch (HttpForbiddenException e) {
      response = new PaymentStatusResponse().fromJson(AUTH_FAILED_JSON);
    } catch (HttpNotFoundException e) {
      response = new PaymentStatusResponse().fromJson(NOT_FOUND_JSON);
    } catch (Exception e) {
      logger.error("Payment status call failed: " + e.getClass().getSimpleName() + " - " + e.getMessage());
      throw e;
    }

    return response;
  }

  /**
   * Do a transaction refund request on the payment page application.
   * 
   * @param request
   *          The transaction refund request object to use
   * @return The transaction refund response object or null on error
   */
  public TransactionRefundResponse refundTransaction(TransactionRefundRequest request) throws Exception {
    if (request == null) {
      throw new NullPointerException();
    }

    String url = APIRoute.TRANS_REFUND.getRoute().replaceAll(":transactionId", request.getTransactionId());
    this.httpClient.setUrl(this.serviceUrl + url).setBody(request.toJson());
    if (logger.isDebugEnabled()) {
      logger.debug("Doing Transaction Refund request.");
    }

    TransactionRefundResponse response = null;
    try {
      String json = this.httpClient.post();
      response = new TransactionRefundResponse().fromJson(json);
    } catch (HttpForbiddenException e) {
      response = new TransactionRefundResponse().fromJson(AUTH_FAILED_JSON);
    } catch (HttpNotFoundException e) {
      response = new TransactionRefundResponse().fromJson(NOT_FOUND_JSON);
    } catch (Exception e) {
      logger.error("Transaction refund call failed: " + e.getClass().getSimpleName() + " - " + e.getMessage());
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
  public PaymentStatusResponse handleRedirectStatus(String encryptedData, String merchantToken) throws Exception {
    if (encryptedData == null || merchantToken == null) {
      throw new NullPointerException();
    }
    PaymentStatusResponse response = null;

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
        response = new PaymentStatusResponse().fromJson(json);
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
  public PaymentStatusResponse handleCallbackStatus(InputStream requestStream) throws Exception {
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
  public PaymentStatusResponse handleCallbackStatus(String requestBody) throws Exception {
    if (requestBody == null) {
      throw new NullPointerException();
    }
    PaymentStatusResponse response = null;

    try {
      response = new PaymentStatusResponse().fromJson(requestBody);
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