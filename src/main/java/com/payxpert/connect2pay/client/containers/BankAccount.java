package com.payxpert.connect2pay.client.containers;

import java.util.Optional;

/**
 * Information about a bank account used in a bank transfer or direct debit transaction.
 * 
 * @author jsh
 */
public class BankAccount {

  private String holderName;
  private String bankName;
  private String iban;
  private String bic;

  private String countryCode;

  private SepaMandate sepaMandate;

  /**
   * The account holder name
   * 
   * @return the holderName
   */
  public String getHolderName() {
    return holderName;
  }

  /**
   * @param holderName
   *          the account holder name to set
   */
  public void setHolderName(String holderName) {
    this.holderName = holderName;
  }

  /**
   * Name of the bank of the account
   * 
   * @return the bankName
   */
  public String getBankName() {
    return bankName;
  }

  /**
   * Name of the bank of the account
   * 
   * @param bankName
   *          the name of the bank to set
   */
  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  /**
   * IBAN number of the account (truncated)
   * 
   * @return the IBAN
   */
  public String getIban() {
    return iban;
  }

  /**
   * IBAN number of the account (truncated)
   * 
   * @param iban
   *          the IBAN to set
   */
  public void setIban(String iban) {
    this.iban = iban;
  }

  /**
   * @return the bic
   */
  public String getBic() {
    return bic;
  }

  /**
   * @param bic
   *          The bic to set
   */
  public void setBic(String bic) {
    this.bic = bic;
  }

  /**
   * Country code (ISO2) of the account
   * 
   * @return the country code (ISO2)
   */
  public String getCountryCode() {
    return countryCode;
  }

  /**
   * Country code (ISO2) of the account
   * 
   * @param countryCode
   *          the country code (ISO2) to set
   */
  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  /**
   * The optional SEPA mandate associated with that bank account
   * 
   * @return An Optional with the SEPA mandate or an empty Optional if none exists
   */
  public Optional<SepaMandate> getSepaMandate() {
    return Optional.ofNullable(this.sepaMandate);
  }

  /**
   * Set the SEPA mandate associated with that bank account
   * 
   * @param sepaMandate
   *          The SEPA mandate to set
   */
  public void setSepaMandate(SepaMandate sepaMandate) {
    this.sepaMandate = sepaMandate;
  }
}
