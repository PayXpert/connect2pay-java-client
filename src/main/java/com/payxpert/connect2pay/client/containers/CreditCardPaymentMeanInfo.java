package com.payxpert.connect2pay.client.containers;

public class CreditCardPaymentMeanInfo extends PaymentMeanInfo {
  private String cardNumber;
  private String cardExpireYear;
  private String cardExpireMonth;
  private String cardHolderName;
  private String cardBrand;
  private String cardLevel;
  private String cardSubType;
  private String iinCountry;
  private String iinBankName;
  private Boolean is3DSecure;
  private String statementDescriptor;

  /**
   * @return the cardNumber
   */
  public String getCardNumber() {
    return this.cardNumber;
  }

  /**
   * @param cardNumber
   *          the cardNumber to set
   */
  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  /**
   * @return the cardExpireYear
   */
  public String getCardExpireYear() {
    return this.cardExpireYear;
  }

  /**
   * @param cardExpireYear
   *          the cardExpireYear to set
   */
  public void setCardExpireYear(String cardExpireYear) {
    this.cardExpireYear = cardExpireYear;
  }

  /**
   * @return the cardExpireMonth
   */
  public String getCardExpireMonth() {
    return this.cardExpireMonth;
  }

  /**
   * @param cardExpireMonth
   *          the cardExpireMonth to set
   */
  public void setCardExpireMonth(String cardExpireMonth) {
    this.cardExpireMonth = cardExpireMonth;
  }

  /**
   * @return the cardHolderName
   */
  public String getCardHolderName() {
    return this.cardHolderName;
  }

  /**
   * @param cardHolderName
   *          the cardHolderName to set
   */
  public void setCardHolderName(String cardHolderName) {
    this.cardHolderName = cardHolderName;
  }

  /**
   * @return the cardBrand
   */
  public String getCardBrand() {
    return this.cardBrand;
  }

  /**
   * @param cardBrand
   *          the cardBrand to set
   */
  public void setCardBrand(String cardBrand) {
    this.cardBrand = cardBrand;
  }

  /**
   * @return the cardLevel
   */
  public String getCardLevel() {
    return this.cardLevel;
  }

  /**
   * @param cardLevel
   *          the cardLevel to set
   */
  public void setCardLevel(String cardLevel) {
    this.cardLevel = cardLevel;
  }

  /**
   * @return the cardSubType
   */
  public String getCardSubType() {
    return this.cardSubType;
  }

  /**
   * @param cardSubType
   *          the cardSubType to set
   */
  public void setCardSubType(String cardSubType) {
    this.cardSubType = cardSubType;
  }

  /**
   * @return the iinCountry
   */
  public String getIinCountry() {
    return this.iinCountry;
  }

  /**
   * @param iinCountry
   *          the iinCountry to set
   */
  public void setIinCountry(String iinCountry) {
    this.iinCountry = iinCountry;
  }

  /**
   * @return the iinBankName
   */
  public String getIinBankName() {
    return this.iinBankName;
  }

  /**
   * @param iinBankName
   *          the iinBankName to set
   */
  public void setIinBankName(String iinBankName) {
    this.iinBankName = iinBankName;
  }

  /**
   * @return the is3DSecure
   */
  public Boolean getIs3DSecure() {
    return this.is3DSecure;
  }

  /**
   * @param is3dSecure
   *          the is3DSecure to set
   */
  public void setIs3DSecure(Boolean is3dSecure) {
    this.is3DSecure = is3dSecure;
  }

  public String getStatementDescriptor() {
    return statementDescriptor;
  }

  public void setStatementDescriptor(String statementDescriptor) {
    this.statementDescriptor = statementDescriptor;
  }
}
