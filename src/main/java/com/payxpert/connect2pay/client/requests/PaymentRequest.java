package com.payxpert.connect2pay.client.requests;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payxpert.connect2pay.client.Connect2payClient;
import com.payxpert.connect2pay.constants.C2PLang;
import com.payxpert.connect2pay.constants.PaymentMode;
import com.payxpert.connect2pay.constants.PaymentType;
import com.payxpert.connect2pay.constants.ShippingType;
import com.payxpert.connect2pay.constants.SubscriptionType;
import com.payxpert.connect2pay.constants.TransactionOperation;

import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.MinLength;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Range;

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

  // Customer fields
  // Shipping Information
  @JsonProperty("shopperID")
  @MaxLength(32)
  private String shopperId;

  @MatchPattern(pattern = {
      "NA|[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[a-zA-Z0-9](?:[\\w-]*[\\w])?" })
  @MaxLength(100)
  private String shopperEmail;
  @MaxLength(35)
  private String shipToFirstName;
  @MaxLength(35)
  private String shipToLastName;
  @MaxLength(128)
  private String shipToCompany;
  @MaxLength(20)
  private String shipToPhone;
  @MaxLength(255)
  private String shipToAddress;
  @MaxLength(30)
  private String shipToState;
  @MaxLength(10)
  private String shipToZipcode;
  @MaxLength(50)
  private String shipToCity;
  @MaxLength(2)
  private String shipToCountryCode;

  // Invoicing Information
  @MaxLength(35)
  private String shopperFirstName;
  @MaxLength(35)
  private String shopperLastName;
  @MaxLength(20)
  private String shopperPhone;
  @MaxLength(255)
  private String shopperAddress;
  @MaxLength(30)
  private String shopperState;
  @MaxLength(10)
  private String shopperZipcode;
  @MaxLength(50)
  private String shopperCity;
  @MaxLength(2)
  private String shopperCountryCode;
  @MaxLength(128)
  private String shopperCompany;
  @MaxLength(50)
  private String shopperLoyaltyProgram;
  @MinLength(8)
  @MaxLength(8)
  private String shopperBirthDate;
  @MaxLength(32)
  private String shopperIDNumber;

  // Order fields
  @NotNull
  @NotEmpty
  @MaxLength(100)
  @JsonProperty("orderID")
  private String orderId;

  @NotNull
  @NotEmpty
  @MaxLength(3)
  private String currency;
  @NotNull
  @Range(min = 1, max = 999999999)
  private Integer amount;
  private Integer orderTotalWithoutShipping;
  private Integer orderShippingPrice;
  private Integer orderDiscount;

  @MaxLength(50)
  private String orderFOLanguage;

  @MaxLength(500)
  private String orderDescription;
  private List<Product> orderCartContent;

  // Shipping fields
  @NotNull
  private ShippingType shippingType;
  @MaxLength(50)
  private String shippingName;

  // Payment fields
  @NotNull
  private PaymentMode paymentMode;

  @NotNull
  private PaymentType paymentType;

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

  @Override
  protected PaymentRequest getThis() {
    return this;
  }

  /**
   * @return the afClientId
   * @deprecated Removed since API 002.02
   */
  @Deprecated
  public Integer getAfClientId() {
    return null;
  }

  /**
   * @param afClientId
   *          the afClientId to set
   * 
   * @return The current request for method chaining
   * @deprecated Removed since API 002.02
   */
  @Deprecated
  public PaymentRequest setAfClientId(Integer afClientId) {
    return getThis();
  }

  /**
   * @return the afPassword
   * @deprecated Removed since API 002.02
   */
  @Deprecated
  public String getAfPassword() {
    return null;
  }

  /**
   * @param afPassword
   *          the afPassword to set
   * 
   * @return The current request for method chaining
   * @deprecated Removed since API 002.02
   */
  @Deprecated
  public PaymentRequest setAfPassword(String afPassword) {
    return getThis();
  }

  /**
   * @return the shopperId
   */
  public String getShopperId() {
    return this.shopperId;
  }

  /**
   * @param shopperId
   *          the shopperId to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShopperId(String shopperId) {
    this.shopperId = shopperId;
    return getThis();
  }

  /**
   * @param shopperId
   *          the shopperId to set
   * 
   * @return The current request for method chaining
   * @deprecated since 002.50
   */
  @Deprecated
  public PaymentRequest setShopperId(Integer shopperId) {
    if (shopperId == null) {
      this.shopperId = null;
    } else {
      this.shopperId = shopperId.toString();
    }
    return getThis();
  }

  /**
   * @return the shopperEmail
   */
  public String getShopperEmail() {
    return shopperEmail;
  }

  /**
   * @param shopperEmail
   *          the shopperEmail to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShopperEmail(String shopperEmail) {
    this.shopperEmail = this.limitLength(shopperEmail, 100);
    return getThis();
  }

  /**
   * @return the shipToFirstName
   */
  public String getShipToFirstName() {
    return shipToFirstName;
  }

  /**
   * @param shipToFirstName
   *          the shipToFirstName to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShipToFirstName(String shipToFirstName) {
    this.shipToFirstName = this.limitLength(shipToFirstName, 35);
    return getThis();
  }

  /**
   * @return the shipToLastName
   */
  public String getShipToLastName() {
    return shipToLastName;
  }

  /**
   * @param shipToLastName
   *          the shipToLastName to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShipToLastName(String shipToLastName) {
    this.shipToLastName = this.limitLength(shipToLastName, 35);
    return getThis();
  }

  /**
   * @return the shipToCompany
   */
  public String getShipToCompany() {
    return shipToCompany;
  }

  /**
   * @param shipToCompany
   *          the shipToCompany to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShipToCompany(String shipToCompany) {
    this.shipToCompany = this.limitLength(shipToCompany, 128);
    return getThis();
  }

  /**
   * @return the shipToPhone
   */
  public String getShipToPhone() {
    return shipToPhone;
  }

  /**
   * @param shipToPhone
   *          the shipToPhone to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShipToPhone(String shipToPhone) {
    this.shipToPhone = this.limitLength(shipToPhone, 20);
    return getThis();
  }

  /**
   * @return the shipToAddress
   */
  public String getShipToAddress() {
    return shipToAddress;
  }

  /**
   * @param shipToAddress
   *          the shipToAddress to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShipToAddress(String shipToAddress) {
    this.shipToAddress = this.limitLength(shipToAddress, 255);
    return getThis();
  }

  /**
   * @return the shipToState
   */
  public String getShipToState() {
    return shipToState;
  }

  /**
   * @param shipToState
   *          the shipToState to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShipToState(String shipToState) {
    this.shipToState = this.limitLength(shipToState, 30);
    return getThis();
  }

  /**
   * @return the shipToZipcode
   */
  public String getShipToZipcode() {
    return shipToZipcode;
  }

  /**
   * @param shipToZipcode
   *          the shipToZipcode to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShipToZipcode(String shipToZipcode) {
    this.shipToZipcode = this.limitLength(shipToZipcode, 10);
    return getThis();
  }

  /**
   * @return the shipToCity
   */
  public String getShipToCity() {
    return shipToCity;
  }

  /**
   * @param shipToCity
   *          the shipToCity to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShipToCity(String shipToCity) {
    this.shipToCity = this.limitLength(shipToCity, 50);
    return getThis();
  }

  /**
   * @return the shipToCountryCode
   */
  public String getShipToCountryCode() {
    return shipToCountryCode;
  }

  /**
   * @param shipToCountryCode
   *          the shipToCountryCode to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShipToCountryCode(String shipToCountryCode) {
    this.shipToCountryCode = this.limitLength(shipToCountryCode, 2);
    return getThis();
  }

  /**
   * @return the shopperFirstName
   */
  public String getShopperFirstName() {
    return shopperFirstName;
  }

  /**
   * @param shopperFirstName
   *          the shopperFirstName to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShopperFirstName(String shopperFirstName) {
    this.shopperFirstName = this.limitLength(shopperFirstName, 35);
    return getThis();
  }

  /**
   * @return the shopperLastName
   */
  public String getShopperLastName() {
    return shopperLastName;
  }

  /**
   * @param shopperLastName
   *          the shopperLastName to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShopperLastName(String shopperLastName) {
    this.shopperLastName = this.limitLength(shopperLastName, 35);
    return getThis();
  }

  /**
   * @return the shopperPhone
   */
  public String getShopperPhone() {
    return shopperPhone;
  }

  /**
   * @param shopperPhone
   *          the shopperPhone to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShopperPhone(String shopperPhone) {
    this.shopperPhone = this.limitLength(shopperPhone, 20);
    return getThis();
  }

  /**
   * @return the shopperAddress
   */
  public String getShopperAddress() {
    return shopperAddress;
  }

  /**
   * @param shopperAddress
   *          the shopperAddress to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShopperAddress(String shopperAddress) {
    this.shopperAddress = this.limitLength(shopperAddress, 255);
    return getThis();
  }

  /**
   * @return the shopperState
   */
  public String getShopperState() {
    return shopperState;
  }

  /**
   * @param shopperState
   *          the shopperState to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShopperState(String shopperState) {
    this.shopperState = this.limitLength(shopperState, 30);
    return getThis();
  }

  /**
   * @return the shopperZipcode
   */
  public String getShopperZipcode() {
    return shopperZipcode;
  }

  /**
   * @param shopperZipcode
   *          the shopperZipcode to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShopperZipcode(String shopperZipcode) {
    this.shopperZipcode = this.limitLength(shopperZipcode, 10);
    return getThis();
  }

  /**
   * @return the shopperCity
   */
  public String getShopperCity() {
    return shopperCity;
  }

  /**
   * @param shopperCity
   *          the shopperCity to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShopperCity(String shopperCity) {
    this.shopperCity = this.limitLength(shopperCity, 50);
    return getThis();
  }

  /**
   * @return the shopperCountryCode
   */
  public String getShopperCountryCode() {
    return shopperCountryCode;
  }

  /**
   * @param shopperCountryCode
   *          the shopperCountryCode to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShopperCountryCode(String shopperCountryCode) {
    this.shopperCountryCode = this.limitLength(shopperCountryCode, 2);
    return getThis();
  }

  /**
   * @return the shopperCompany
   */
  public String getShopperCompany() {
    return shopperCompany;
  }

  /**
   * @param shopperCompany
   *          the shopperCompany to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShopperCompany(String shopperCompany) {
    this.shopperCompany = this.limitLength(shopperCompany, 128);
    return getThis();
  }

  /**
   * @return the shopperLoyaltyProgram
   */
  public String getShopperLoyaltyProgram() {
    return shopperLoyaltyProgram;
  }

  /**
   * @param shopperLoyaltyProgram
   *          the shopperLoyaltyProgram to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShopperLoyaltyProgram(String shopperLoyaltyProgram) {
    this.shopperLoyaltyProgram = this.limitLength(shopperLoyaltyProgram, 50);
    return getThis();
  }

  /**
   * @return the shopperBirthDate
   */
  public String getShopperBirthDate() {
    return this.shopperBirthDate;
  }

  /**
   * @param shopperBirthDate
   *          the date of shopper birth date, format YYYYMMDD
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShopperBirthDate(String shopperBirthDate) {
    this.shopperBirthDate = this.limitLength(shopperBirthDate, 8);
    return getThis();
  }

  /**
   * @param shopperBirthDate
   *          the date of shopper birth date
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShopperBirthDate(Date shopperBirthDate) {
    if (shopperBirthDate == null) {
      this.shopperBirthDate = null;
    } else {
      Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/UTC"));
      calendar.setTime(shopperBirthDate);
      // Format as YYYYMMDD
      this.shopperBirthDate = String.format("%04d%02d%02d", calendar.get(Calendar.YEAR),
          calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
    }

    return getThis();
  }

  /**
   * @return the shopperIDNumber
   */
  public String getShopperIDNumber() {
    return this.shopperIDNumber;
  }

  /**
   * @param shopperIDNumber
   *          Customers document (passport number, ID number, taxpayer ID...)
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShopperIDNumber(String shopperIDNumber) {
    this.shopperIDNumber = this.limitLength(shopperIDNumber, 32);
    return getThis();
  }

  /**
   * @return the orderID
   */
  public String getOrderId() {
    return orderId;
  }

  /**
   * @param orderId
   *          the orderId to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setOrderId(String orderId) {
    this.orderId = this.limitLength(orderId, 100);
    return getThis();
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
    this.currency = this.limitLength(currency, 3);
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
   * @return the orderTotalWithoutShipping
   */
  public Integer getOrderTotalWithoutShipping() {
    return orderTotalWithoutShipping;
  }

  /**
   * @param orderTotalWithoutShipping
   *          the orderTotalWithoutShipping to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setOrderTotalWithoutShipping(Integer orderTotalWithoutShipping) {
    this.orderTotalWithoutShipping = orderTotalWithoutShipping;
    return getThis();
  }

  /**
   * @return the orderShippingPrice
   */
  public Integer getOrderShippingPrice() {
    return orderShippingPrice;
  }

  /**
   * @param orderShippingPrice
   *          the orderShippingPrice to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setOrderShippingPrice(Integer orderShippingPrice) {
    this.orderShippingPrice = orderShippingPrice;
    return getThis();
  }

  /**
   * @return the orderDiscount
   */
  public Integer getOrderDiscount() {
    return orderDiscount;
  }

  /**
   * @param orderDiscount
   *          the orderDiscount to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setOrderDiscount(Integer orderDiscount) {
    this.orderDiscount = orderDiscount;
    return getThis();
  }

  /**
   * @return the customerIP
   * @deprecated This field is not present anymore in the API, the value is obtained from the connected user
   */
  @Deprecated
  public String getCustomerIP() {
    return null;
  }

  /**
   * @param customerIP
   *          the customerIP to set
   * 
   * @return The current request for method chaining
   * @deprecated This field is not present anymore in the API, the value is obtained from the connected user
   */
  @Deprecated
  public PaymentRequest setCustomerIP(String customerIP) {
    return getThis();
  }

  /**
   * @return the orderFOLanguage
   */
  public String getOrderFOLanguage() {
    return orderFOLanguage;
  }

  /**
   * @param orderFOLanguage
   *          the orderFOLanguage to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setOrderFOLanguage(String orderFOLanguage) {
    this.orderFOLanguage = this.limitLength(orderFOLanguage, 50);
    return getThis();
  }

  /**
   * @return the orderDescription
   */
  public String getOrderDescription() {
    return orderDescription;
  }

  /**
   * @param orderDescription
   *          the orderDescription to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setOrderDescription(String orderDescription) {
    this.orderDescription = this.limitLength(orderDescription, 500);
    return getThis();
  }

  /**
   * @return the orderCartContent
   */
  public List<Product> getOrderCartContent() {
    return orderCartContent;
  }

  /**
   * @param orderCartContent
   *          the orderCartContent to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setOrderCartContent(List<Product> orderCartContent) {
    this.orderCartContent = orderCartContent;
    return getThis();
  }

  /**
   * @return the shippingType
   */
  public ShippingType getShippingType() {
    return shippingType;
  }

  /**
   * @param shippingType
   *          the shippingType to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShippingType(ShippingType shippingType) {
    this.shippingType = shippingType;
    return getThis();
  }

  /**
   * @return the shippingName
   */
  public String getShippingName() {
    return shippingName;
  }

  /**
   * @param shippingName
   *          the shippingName to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setShippingName(String shippingName) {
    this.shippingName = this.limitLength(shippingName, 50);
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
   * @return the paymentType
   */
  public PaymentType getPaymentType() {
    return paymentType;
  }

  /**
   * @param paymentType
   *          the paymentType to set
   * 
   * @return The current request for method chaining
   */
  public PaymentRequest setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
    return getThis();
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
    this.trialPeriod = this.limitLength(trialPeriod, 10);
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
    this.rebillPeriod = this.limitLength(rebillPeriod, 10);
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
    this.ctrlRedirectURL = this.limitLength(ctrlRedirectURL, 2048);
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
    this.ctrlCallbackURL = this.limitLength(ctrlCallbackURL, 2048);
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
    this.ctrlCustomData = this.limitLength(ctrlCustomData, 2048);
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
    this.timeOut = timeOut;
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
    this.merchantNotificationTo = merchantNotificationTo;
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
            default:
              return false;
          }
        }
      }
      return true;
    }
  }

  /**
   * Class to deal with ordered products.
   * 
   * @author jsh
   * 
   */
  public static class Product {
    private int cartProductId;
    private String cartProductName;
    private Float cartProductUnitPrice;
    private int cartProductQuantity;
    private String cartProductBrand;
    private String cartProductMPN;
    private String cartProductCategoryName;
    private int cartProductCategoryID;

    public int getCartProductId() {
      return cartProductId;
    }

    public Product setCartProductId(int cartProductId) {
      this.cartProductId = cartProductId;
      return this;
    }

    public String getCartProductName() {
      return cartProductName;
    }

    public Product setCartProductName(String cartProductName) {
      this.cartProductName = cartProductName;
      return this;
    }

    public Float getCartProductUnitPrice() {
      return cartProductUnitPrice;
    }

    public Product setCartProductUnitPrice(Float cartProductUnitPrice) {
      this.cartProductUnitPrice = cartProductUnitPrice;
      return this;
    }

    public int getCartProductQuantity() {
      return cartProductQuantity;
    }

    public Product setCartProductQuantity(int cartProductQuantity) {
      this.cartProductQuantity = cartProductQuantity;
      return this;
    }

    public String getCartProductBrand() {
      return cartProductBrand;
    }

    public Product setCartProductBrand(String cartProductBrand) {
      this.cartProductBrand = cartProductBrand;
      return this;
    }

    public String getCartProductMPN() {
      return cartProductMPN;
    }

    public Product setCartProductMPN(String cartProductMPN) {
      this.cartProductMPN = cartProductMPN;
      return this;
    }

    public String getCartProductCategoryName() {
      return cartProductCategoryName;
    }

    public Product setCartProductCategoryName(String cartProductCategoryName) {
      this.cartProductCategoryName = cartProductCategoryName;
      return this;
    }

    public int getCartProductCategoryID() {
      return cartProductCategoryID;
    }

    public Product setCartProductCategoryID(int cartProductCategoryID) {
      this.cartProductCategoryID = cartProductCategoryID;
      return this;
    }
  }
}
