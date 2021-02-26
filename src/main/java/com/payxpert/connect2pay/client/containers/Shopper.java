package com.payxpert.connect2pay.client.containers;

import com.payxpert.connect2pay.utils.Utils;
import net.sf.oval.constraint.AssertValid;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.MaxLength;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Shopper {
  @MaxLength(100)
  private String id;

  @MaxLength(80)
  private String name;

  @MaxLength(35)
  public String firstName;

  @MaxLength(35)
  private String lastName;

  @MaxLength(50)
  private String address1;

  @MaxLength(50)
  private String address2;

  @MaxLength(50)
  private String address3;

  @MaxLength(16)
  private String zipcode;

  @MaxLength(50)
  private String city;

  @MaxLength(3)
  private String state;

  @MaxLength(2)
  private String countryCode;

  @MaxLength(4)
  private String homePhonePrefix;

  @MaxLength(20)
  private String homePhone;

  @MaxLength(4)
  private String mobilePhonePrefix;

  @MaxLength(20)
  public String mobilePhone;

  @MaxLength(4)
  public String workPhonePrefix;

  @MaxLength(20)
  public String workPhone;

  @MaxLength(100)
  @MatchPattern(pattern = {
      "NA|[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[a-zA-Z0-9](?:[\\w-]*[\\w])?" })
  private String email;

  @MaxLength(128)
  public String company;

  private String ipAddress;

  /**
   * Date of shopper birth date, format YYYYMMDD
   */
  @MaxLength(8)
  private String birthDate;
  /**
   * Customers document (passport number, ID number, taxpayer ID...)
   */
  @MaxLength(32)
  private String idNumber;

  @AssertValid
  private Account account;

  public String getId() {
    return id;
  }

  public Shopper setId(String id) {
    this.id = Utils.limitLength(id, 100);
    return this;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  public String getFirstName() {
    return firstName;
  }

  public Shopper setFirstName(String firstName) {
    this.firstName = Utils.limitLength(firstName, 35);
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public Shopper setLastName(String lastName) {
    this.lastName = Utils.limitLength(lastName, 35);
    return this;
  }

  /**
   * @return the address
   */
  public String getAddress1() {
    return address1;
  }

  /**
   * @param address1 the address to set
   */
  public Shopper setAddress1(String address1) {
    this.address1 = Utils.limitLength(address1, 50);
    return this;
  }

  public String getAddress2() {
    return address2;
  }

  public Shopper setAddress2(String address2) {
    this.address2 = Utils.limitLength(address2, 50);
    return this;
  }

  public String getAddress3() {
    return address3;
  }

  public Shopper setAddress3(String address3) {
    this.address3 = Utils.limitLength(address3, 50);
    return this;
  }

  /**
   * @return the zipcode
   */
  public String getZipcode() {
    return zipcode;
  }

  /**
   * @param zipcode the zipcode to set
   */
  public Shopper setZipcode(String zipcode) {
    this.zipcode = Utils.limitLength(zipcode, 16);
    return this;
  }

  /**
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * @param city the city to set
   */
  public Shopper setCity(String city) {
    this.city = Utils.limitLength(city, 50);
    return this;
  }

  /**
   * @return the state
   */
  public String getState() {
    return state;
  }

  /**
   * @param state the state to set
   */
  public Shopper setState(String state) {
    this.state = Utils.limitLength(state, 3);
    return this;
  }

  /**
   * @return the countryCode
   */
  public String getCountryCode() {
    return countryCode;
  }

  /**
   * @param countryCode the countryCode to set
   */
  public Shopper setCountryCode(String countryCode) {
    this.countryCode = Utils.limitLength(countryCode, 2);
    return this;
  }

  public String getHomePhonePrefix() {
    return homePhonePrefix;
  }

  public Shopper setHomePhonePrefix(String homePhonePrefix) {
    this.homePhonePrefix = Utils.limitLength(homePhonePrefix, 4);
    return this;
  }

  /**
   * @return the phone
   */
  public String getHomePhone() {
    return homePhone;
  }

  /**
   * @param homePhone the phone to set
   */
  public Shopper setHomePhone(String homePhone) {
    this.homePhone = Utils.limitLength(homePhone, 20);
    return this;
  }

  public String getMobilePhonePrefix() {
    return mobilePhonePrefix;
  }

  public Shopper setMobilePhonePrefix(String mobilePhonePrefix) {
    this.mobilePhonePrefix = Utils.limitLength(mobilePhonePrefix, 4);
    return this;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public Shopper setMobilePhone(String mobilePhone) {
    this.mobilePhone = Utils.limitLength(mobilePhone, 20);
    return this;
  }

  public String getWorkPhonePrefix() {
    return workPhonePrefix;
  }

  public Shopper setWorkPhonePrefix(String workPhonePrefix) {
    this.workPhonePrefix = Utils.limitLength(workPhonePrefix, 4);
    return this;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public Shopper setWorkPhone(String workPhone) {
    this.workPhone = Utils.limitLength(workPhone, 20);
    return this;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public Shopper setEmail(String email) {
    this.email = Utils.limitLength(email, 100);
    return this;
  }

  public String getCompany() {
    return company;
  }

  public Shopper setCompany(String company) {
    this.company = Utils.limitLength(company, 128);
    return this;
  }

  /**
   * @return the ipAddress
   */
  public String getIpAddress() {
    return ipAddress;
  }

  /**
   * @return the birthDate
   */
  public String getBirthDate() {
    return this.birthDate;
  }

  /**
   * @param birthDate the birthDate to set
   */
  public Shopper setBirthDate(String birthDate) {
    this.birthDate = Utils.limitLength(birthDate, 8);
    return this;
  }

  public Shopper setBirthDate(Date birthDate) {
    if (birthDate != null) {
      Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/UTC"));
      calendar.setTime(birthDate);

      // Format as YYYYMMDD
      this.birthDate = String.format("%04d%02d%02d", calendar.get(Calendar.YEAR),
          calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
    }

    return this;
  }

  /**
   * @return the idNumber
   */
  public String getIdNumber() {
    return this.idNumber;
  }

  /**
   * @param idNumber the idNumber to set
   */
  public Shopper setIdNumber(String idNumber) {
    this.idNumber = Utils.limitLength(idNumber, 32);
    return this;
  }

  public Account getAccount() {
    return account;
  }

  public Shopper setAccount(Account account) {
    this.account = account;
    return this;
  }
}
