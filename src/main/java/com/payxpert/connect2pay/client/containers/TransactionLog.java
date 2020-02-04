package com.payxpert.connect2pay.client.containers;

import java.util.Date;

/**
 * This represents a transaction log entry. Usage of transaction logs requires special permissions.
 */
public class TransactionLog {

  private String ipAddress;
  private Date date;
  private String type;
  private String path;
  private String details;

  /**
   * @return the ipAddress
   */
  public String getIpAddress() {
    return ipAddress;
  }

  /**
   * @param ipAddress
   *          the ipAddress to set
   */
  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  /**
   * @return the date
   */
  public Date getDate() {
    return date;
  }

  /**
   * @param date
   *          the date to set
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type
   *          the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the path
   */
  public String getPath() {
    return path;
  }

  /**
   * @param path
   *          the path to set
   */
  public void setPath(String path) {
    this.path = path;
  }

  /**
   * @return the details
   */
  public String getDetails() {
    return details;
  }

  /**
   * @param details
   *          the details to set
   */
  public void setDetails(String details) {
    this.details = details;
  }

}
