package com.payxpert.connect2pay.client.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payxpert.connect2pay.client.Connect2payClient;
import com.payxpert.connect2pay.client.containers.Order;
import com.payxpert.connect2pay.client.containers.Shipping;
import com.payxpert.connect2pay.client.containers.Shopper;
import com.payxpert.connect2pay.constants.*;

import com.payxpert.connect2pay.utils.Utils;
import net.sf.oval.constraint.*;

/**
 * This class represents the initial request made by the merchant to initialize a payment:
 * <ul>
 * <li>Instantiate</li>
 * <li>Set the value of the variables</li>
 * <li>Call {@link PaymentRequest#validate()} to check that it is ok</li>
 * <li>Use it in {@link Connect2payClient#preparePayment(PaymentRequest)}</li>
 * </ul>
 * 
 * String fields will be automatically truncated to the maximum length allowed by the API.
 * 
 * @author jsh
 * 
 */
public class PaymentRequest extends GenericRequest<PaymentRequest> {

  @NotNull
  @NotEmpty
  @MaxLength(3)
  private String currency;

  @NotNull
  @Range(min = 1, max = 999999999)
  private Integer amount;

  @NotNull
  private PaymentMode paymentMode;

  private PaymentMethod paymentMethod;

  private PaymentNetwork paymentNetwork;

  private TransactionOperation operation;

  private Boolean secure3d;

  // Predefined Subscription Fields
  @JsonProperty("offerID")
  private Long offerId;

  // On the fly Subscription Fields
  @CheckWith(SubscriptionValidator.class)
  private SubscriptionType subscriptionType;
  @MaxLength(10)
  private String trialPeriod;
  private Integer rebillAmount;
  @MaxLength(10)
  private String rebillPeriod;
  private Integer rebillMaxIteration;

  // Template and control fields
  @MatchPattern(pattern = {
      "^(http|https)\\://[a-zA-Z0-9\\-\\.]+(:[a-zA-Z0-9]*)?/?([a-zA-Z0-9\\-\\._\\?\\,\\'/\\\\\\+&amp;%\\$#\\=~\\!])*$" })
  @MaxLength(2048)
  private String ctrlRedirectURL;

  @MatchPattern(pattern = {
      "^(http|https)\\://[a-zA-Z0-9\\-\\.]+(:[a-zA-Z0-9]*)?/?([a-zA-Z0-9\\-\\._\\?\\,\\'/\\\\\\+&amp;%\\$#\\=~\\!])*$" })
  @MaxLength(2048)
  private String ctrlCallbackURL;

  @MaxLength(2048)
  private String ctrlCustomData;

  @MaxLength(10)
  private String timeOut;

  private Boolean merchantNotification;

  @Email
  @MaxLength(100)
  private String merchantNotificationTo;

  private C2PLang merchantNotificationLang;

  @JsonProperty("themeID")
  private Long themeId;

  @AssertValid
  private Shopper shopper;

  @AssertValid
  private Shipping shipping;

  @AssertValid
  private Order order;

  @Override
  protected PaymentRequest getThis() {
    return this;
  }


  /**
   * @return the currency
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * @param currency
   *          the currency to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setCurrency(String currency) {
    this.currency = Utils.limitLength(currency, 3);
    return getThis();
  }

  /**
   * @return the amount
   */
  public Integer getAmount() {
    return amount;
  }

  /**
   * @param amount
   *          the amount to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setAmount(Integer amount) {
    this.amount = amount;
    return getThis();
  }

  /**
   * @return the paymentMode
   */
  public PaymentMode getPaymentMode() {
    return paymentMode;
  }

  /**
   * @param paymentMode
   *          the paymentMode to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setPaymentMode(PaymentMode paymentMode) {
    this.paymentMode = paymentMode;
    return getThis();
  }

  /**
   * @return the paymentMethod
   */
  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  /**
   * @param paymentMethod
   *          the paymentMethod to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
    return getThis();
  }

  /**
   * @param paymentNetwork
   *          the Payment Network to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setPaymentNetwork(PaymentNetwork paymentNetwork) {
    this.paymentNetwork = paymentNetwork;
    return getThis();
  }

  /**
   * @return the Payment Network
   */
  public PaymentNetwork getPaymentNetwork() {
    return paymentNetwork;
  }

  /**
   * @return the operation
   */
  public TransactionOperation getOperation() {
    return this.operation;
  }

  /**
   * @param operation
   *          the operation to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setOperation(TransactionOperation operation) {
    this.operation = operation;
    return getThis();
  }

  /**
   * @return the secure3d
   */
  public Boolean getSecure3d() {
    return secure3d;
  }

  /**
   * @param secure3d
   *          the secure3d to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setSecure3d(Boolean secure3d) {
    this.secure3d = secure3d;
    return getThis();
  }

  /**
   * @return the offerId
   */
  public Long getOfferId() {
    return offerId;
  }

  /**
   * @param offerId
   *          the offerId to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setOfferId(Long offerId) {
    this.offerId = offerId;
    return getThis();
  }

  /**
   * @return the subscriptionType
   */
  public SubscriptionType getSubscriptionType() {
    return subscriptionType;
  }

  /**
   * @param subscriptionType
   *          the subscriptionType to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setSubscriptionType(SubscriptionType subscriptionType) {
    this.subscriptionType = subscriptionType;
    return getThis();
  }

  /**
   * @return the trialPeriod
   */
  public String getTrialPeriod() {
    return trialPeriod;
  }

  /**
   * @param trialPeriod
   *          the trialPeriod to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setTrialPeriod(String trialPeriod) {
    this.trialPeriod = Utils.limitLength(trialPeriod, 10);
    return getThis();
  }

  /**
   * @return the rebillAmount
   */
  public Integer getRebillAmount() {
    return rebillAmount;
  }

  /**
   * @param rebillAmount
   *          the rebillAmount to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setRebillAmount(Integer rebillAmount) {
    this.rebillAmount = rebillAmount;
    return getThis();
  }

  /**
   * @return the rebillPeriod
   */
  public String getRebillPeriod() {
    return rebillPeriod;
  }

  /**
   * @param rebillPeriod
   *          the rebillPeriod to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setRebillPeriod(String rebillPeriod) {
    this.rebillPeriod = Utils.limitLength(rebillPeriod, 10);
    return getThis();
  }

  /**
   * @return the rebillMaxIteration
   */
  public Integer getRebillMaxIteration() {
    return rebillMaxIteration;
  }

  /**
   * @param rebillMaxIteration
   *          the rebillMaxIteration to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setRebillMaxIteration(Integer rebillMaxIteration) {
    this.rebillMaxIteration = rebillMaxIteration;
    return getThis();
  }

  /**
   * @return the ctrlRedirectURL
   */
  public String getCtrlRedirectURL() {
    return ctrlRedirectURL;
  }

  /**
   * @param ctrlRedirectURL
   *          the ctrlRedirectURL to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setCtrlRedirectURL(String ctrlRedirectURL) {
    this.ctrlRedirectURL = Utils.limitLength(ctrlRedirectURL, 2048);
    return getThis();
  }

  /**
   * @return the ctrlCallbackURL
   */
  public String getCtrlCallbackURL() {
    return ctrlCallbackURL;
  }

  /**
   * @param ctrlCallbackURL
   *          the ctrlCallbackURL to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setCtrlCallbackURL(String ctrlCallbackURL) {
    this.ctrlCallbackURL = Utils.limitLength(ctrlCallbackURL, 2048);
    return getThis();
  }

  /**
   * @return the ctrlCustomData
   */
  public String getCtrlCustomData() {
    return ctrlCustomData;
  }

  /**
   * @param ctrlCustomData
   *          the ctrlCustomData to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setCtrlCustomData(String ctrlCustomData) {
    this.ctrlCustomData = Utils.limitLength(ctrlCustomData, 2048);
    return getThis();
  }

  /**
   * @return the timeOut
   */
  public String getTimeOut() {
    return timeOut;
  }

  /**
   * @param timeOut
   *          the timeOut to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setTimeOut(String timeOut) {
    this.timeOut = Utils.limitLength(timeOut, 10);
    return getThis();
  }

  /**
   * @return the merchantNotification
   */
  public Boolean getMerchantNotification() {
    return merchantNotification;
  }

  /**
   * @param merchantNotification
   *          the merchantNotification to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setMerchantNotification(Boolean merchantNotification) {
    this.merchantNotification = merchantNotification;
    return getThis();
  }

  /**
   * @return the merchantNotificationTo
   */
  public String getMerchantNotificationTo() {
    return merchantNotificationTo;
  }

  /**
   * @param merchantNotificationTo
   *          the merchantNotificationTo to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setMerchantNotificationTo(String merchantNotificationTo) {
    this.merchantNotificationTo = Utils.limitLength(merchantNotificationTo, 100);
    return getThis();
  }

  /**
   * @return the merchantNotificationLang
   */
  public C2PLang getMerchantNotificationLang() {
    return merchantNotificationLang;
  }

  /**
   * @param merchantNotificationLang
   *          the merchantNotificationLang to set
   */
  public PaymentRequest setMerchantNotificationLang(C2PLang merchantNotificationLang) {
    this.merchantNotificationLang = merchantNotificationLang;
    return getThis();
  }

  /**
   * @return the themeId
   */
  public Long getThemeId() {
    return themeId;
  }

  /**
   * @param themeId
   *          the themeId to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setThemeId(Long themeId) {
    this.themeId = themeId;
    return getThis();
  }

  public Shopper getShopper() {
    return shopper;
  }

  public PaymentRequest setShopper(Shopper shopper) {
    this.shopper = shopper;
    return this;
  }

  public Shipping getShipping() {
    return shipping;
  }

  public PaymentRequest setShipping(Shipping shipping) {
    this.shipping = shipping;
    return this;
  }

  public Order getOrder() {
    return order;
  }

  public PaymentRequest setOrder(Order order) {
    this.order = order;
    return this;
  }

  /**
   * Automatically compute orderAmount according to orderTotalWithoutShipping, orderShippingPrice and orderDiscount
   * values.
   * 
   * @param orderTotalWithoutShipping
   *          Amount of the order without shipping
   * @param orderShippingPrice
   *          Shipping amount of the order
   * @param orderDiscount
   *          Discount amount of the order
   * @return The computed orderAmount value or null if orderTotalWithoutShipping is null
   */
  public static Integer computeOrderAmount(Integer orderTotalWithoutShipping, Integer orderShippingPrice,
      Integer orderDiscount) {
    Integer orderAmount = null;
    // We check that the computed orderAmount is valid
    if (orderTotalWithoutShipping != null) {
      orderAmount = orderTotalWithoutShipping;
      if (orderShippingPrice != null) {
        orderAmount += orderShippingPrice;
      }
      if (orderDiscount != null) {
        orderAmount -= orderDiscount;
      }
    }

    return orderAmount;
  }

  private static class SubscriptionValidator implements CheckWithCheck.SimpleCheck {
    private static final long serialVersionUID = -1808037567283704534L;

    @Override
    public boolean isSatisfied(Object validatedObject, Object value) {
      if (validatedObject != null && value != null) {
        SubscriptionType type = null;
        PaymentRequest request = null;
        if (PaymentRequest.class.isAssignableFrom(validatedObject.getClass())) {
          request = PaymentRequest.class.cast(validatedObject);
        }
        if (SubscriptionType.class.isAssignableFrom(value.getClass())) {
          type = SubscriptionType.class.cast(value);
        }

        if (request != null && type != null) {
          switch (type) {
            case NORMAL:
              if (request.rebillAmount == null || request.rebillPeriod == null || request.rebillMaxIteration == null) {
                logger.error(
                    "rebillAmount, rebillPeriod and rebillMaxIteration must be set for a subscription of type NORMAL.");
                return false;
              }
              break;
            case INFINITE:
              if (request.rebillAmount == null || request.rebillPeriod == null) {
                logger.error("rebillAmount and rebillPeriod must be set for a subscription of type INFINTE.");
                return false;
              }
              if (request.rebillMaxIteration != null) {
                logger.error("RebillMaxIteration must not be set for subscription type INFINITE.");
                return false;
              }
              break;
            case ONETIME:
              if (request.rebillPeriod == null || request.rebillAmount == null) {
                logger.error("rebillAMount and rebillPeriod must be set for a subscription of type ONETIME.");
                return false;
              }
              if (request.trialPeriod != null || request.rebillMaxIteration != null) {
                logger.error("trialPeriod and rebillMaxIteration must not be set for a subscription of type ONETIME.");
                return false;
              }
              break;
            case LIFETIME:
              if (request.rebillAmount == null) {
                logger.error("rebillAMount must be set for a subscription of type LIFETIME.");
                return false;
              }
              if (request.trialPeriod != null || request.rebillPeriod != null || request.rebillMaxIteration != null) {
                logger.error(
                    "trialPeriod, rebillPeriod and rebillMaxIteration must not be set for a subscription of type LIFETIME.");
                return false;
              }
              break;
            case PARTPAYMENT:
              if (request.rebillAmount == null || request.rebillPeriod == null || request.rebillMaxIteration == null) {
                logger.error("rebillAMount, rebillPeriod and rebillMaxIteration must be set for a subscription of type PARTPAYMENT.");
                return false;
              }
              break;
            default:
              return false;
          }
        }
      }
      return true;
    }
  }
}
