package com.payxpert.connect2pay.client.containers;

import java.util.Date;

/**
 * This represents a Notification. Usage of notifications requires special permissions.
 */
public class Notification {

  private String operation;
  private String category;
  private String status;
  private String mean;
  private String recipient;
  private String result;
  private String resultMessage;
  private Date date;

  /**
   * @return the operation
   */
  public String getOperation() {
    return operation;
  }

  /**
   * @param operation
   *          the operation to set
   */
  public void setOperation(String operation) {
    this.operation = operation;
  }

  /**
   * @return the category
   */
  public String getCategory() {
    return category;
  }

  /**
   * @param category
   *          the category to set
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * @param status
   *          the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * @return the mean
   */
  public String getMean() {
    return mean;
  }

  /**
   * @param mean
   *          the mean to set
   */
  public void setMean(String mean) {
    this.mean = mean;
  }

  /**
   * @return the recipient
   */
  public String getRecipient() {
    return recipient;
  }

  /**
   * @param recipient
   *          the recipient to set
   */
  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  /**
   * @return the result
   */
  public String getResult() {
    return result;
  }

  /**
   * @param result
   *          the result to set
   */
  public void setResult(String result) {
    this.result = result;
  }

  /**
   * @return the resultMessage
   */
  public String getResultMessage() {
    return resultMessage;
  }

  /**
   * @param resultMessage
   *          the resultMessage to set
   */
  public void setResultMessage(String resultMessage) {
    this.resultMessage = resultMessage;
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

}
