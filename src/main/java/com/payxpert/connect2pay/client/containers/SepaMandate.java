package com.payxpert.connect2pay.client.containers;

import java.util.Date;

/**
 * Information about a SEPA mandate.
 * 
 */
public class SepaMandate {

  private String description;
  private String status;
  private String type;
  private String scheme;
  private String signatureType;
  private String phoneNumber;
  private Date signedAt;
  private Date createdAt;
  private Date lastUsedAt;
  private String downloadUrl;

  /**
   * Description of the mandate
   * 
   * @return The description of the mandate
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Set the description of the mandate
   * 
   * @param description
   *          The description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Status of the mandate. PENDING_SIGNATURE, AUTOSIGNED, SIGNED, EXPIRED, REVOKED or USED
   * 
   * @return The current status of the mandate
   */
  public String getStatus() {
    return this.status;
  }

  /**
   * Set the current status of the mandate
   * 
   * @param status
   *          The status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * The type of mandate. RECURRENT or ONETIME
   * 
   * @return The type of mandate
   */
  public String getType() {
    return this.type;
  }

  /**
   * Set the current mandate type
   * 
   * @param type
   *          The type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * The scheme of the mandate. CORE, COR1 or B2B
   * 
   * @return The scheme of the mandate
   */
  public String getScheme() {
    return this.scheme;
  }

  /**
   * Set the current scheme of the mandate
   * 
   * @param scheme
   *          The scheme to set
   */
  public void setScheme(String scheme) {
    this.scheme = scheme;
  }

  /**
   * The type of signature used to sign the mandate. CHECKBOX, BIOMETRIC or SMS
   * 
   * @return The type of signature used.
   */
  public String getSignatureType() {
    return this.signatureType;
  }

  /**
   * Set the type of signature used to sign the mandate.
   * 
   * @param signatureType
   *          The type to set
   */
  public void setSignatureType(String signatureType) {
    this.signatureType = signatureType;
  }

  /**
   * The phone number used in case the mandate has been signed by SMS.
   * 
   * @return The phone number used for SMS signature
   */
  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  /**
   * Set the phone number
   * 
   * @param phoneNumber
   *          The phone number to set
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * The date of mandate signature.
   *
   * @return The date that mandate was signed at
   */
  public Date getSignedAt() {
    return this.signedAt;
  }

  /**
   * Set the mandate signing date.
   * 
   * @param signedAt
   *          The signing date to set
   */
  public void setSignedAt(Date signedAt) {
    this.signedAt = signedAt;
  }

  /**
   * The date of mandate creation.
   *
   * @return The date that mandate was created at
   */
  public Date getCreatedAt() {
    return this.createdAt;
  }

  /**
   * Set the mandate creation date.
   * 
   * @param createdAt
   *          The creation date to set
   */
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * The date of last mandate use.
   *
   * @return The date that mandate was last used at
   */
  public Date getLastUsedAt() {
    return this.lastUsedAt;
  }

  /**
   * Set the mandate last used date.
   * 
   * @param lastUsedAt
   *          The last used date to set
   */
  public void setLastUsedAt(Date lastUsedAt) {
    this.lastUsedAt = lastUsedAt;
  }

  /**
   * The URL at which the mandate can be downloaded
   * 
   * @return The download URL of the mandate
   */
  public String getDownloadUrl() {
    return this.downloadUrl;
  }

  /**
   * Set the mandate download URL
   * 
   * @param downloadUrl
   *          The URL to set
   */
  public void setDownloadUrl(String downloadUrl) {
    this.downloadUrl = downloadUrl;
  }
}
