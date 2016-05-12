package com.payxpert.connect2pay.client.containers;

public class CreditCardPaymentMeanInfo implements PaymentMeanInfo {
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

  /**
   * @return the cardNumber
   */
  public String getCardNumber() {
    return cardNumber;
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
    return cardExpireYear;
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
    return cardExpireMonth;
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
    return cardHolderName;
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
    return cardBrand;
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
    return cardLevel;
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
    return cardSubType;
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
    return iinCountry;
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
    return iinBankName;
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
    return is3DSecure;
  }

  /**
   * @param is3dSecure
   *          the is3DSecure to set
   */
  public void setIs3DSecure(Boolean is3dSecure) {
    is3DSecure = is3dSecure;
  }
}
