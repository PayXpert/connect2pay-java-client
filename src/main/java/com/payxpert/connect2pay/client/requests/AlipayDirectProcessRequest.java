package com.payxpert.connect2pay.client.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payxpert.connect2pay.constants.AlipayIdentityCodeType;
import com.payxpert.connect2pay.constants.AlipayPaymentMode;

import com.payxpert.connect2pay.utils.Utils;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.NotNull;

/**
 * Request for the Alipay Direct Process API call.
 * 
 */
public class AlipayDirectProcessRequest extends GenericRequest<AlipayDirectProcessRequest> {
  @NotNull
  @JsonIgnore
  private String customerToken;

  @NotNull
  private AlipayPaymentMode mode;

  @CheckWith(ignoreIfNull = false, value = IdentityCodeValidator.class)
  private AlipayIdentityCodeType identityCodeType;

  @MaxLength(32)
  @CheckWith(ignoreIfNull = false, value = IdentityCodeValidator.class)
  private String buyerIdentityCode;

  @MaxLength(10)
  private String notificationLang;

  @MaxLength(64)
  private String notificationTimeZone;

  public String getCustomerToken() {
    return customerToken;
  }

  public AlipayDirectProcessRequest setCustomerToken(String customerToken) {
    this.customerToken = customerToken;
    return getThis();
  }

  public AlipayPaymentMode getMode() {
    return mode;
  }

  public AlipayDirectProcessRequest setMode(AlipayPaymentMode mode) {
    this.mode = mode;
    return getThis();
  }

  public String getBuyerIdentityCode() {
    return buyerIdentityCode;
  }

  public AlipayDirectProcessRequest setBuyerIdentityCode(String buyerIdentityCode) {
    this.buyerIdentityCode = Utils.limitLength(buyerIdentityCode, 32);
    return getThis();
  }

  public AlipayIdentityCodeType getIdentityCodeType() {
    return identityCodeType;
  }

  public AlipayDirectProcessRequest setIdentityCodeType(AlipayIdentityCodeType identityCodeType) {
    this.identityCodeType = identityCodeType;
    return getThis();
  }

  public String getNotificationLang() {
    return notificationLang;
  }

  public AlipayDirectProcessRequest setNotificationLang(String notificationLang) {
    this.notificationLang = Utils.limitLength(notificationLang, 10);
    return getThis();
  }

  public String getNotificationTimeZone() {
    return notificationTimeZone;
  }

  public AlipayDirectProcessRequest setNotificationTimeZone(String notificationTimeZone) {
    this.notificationTimeZone = Utils.limitLength(notificationTimeZone, 64);
    return getThis();
  }

  @Override
  protected AlipayDirectProcessRequest getThis() {
    return this;
  }

  private static class IdentityCodeValidator implements CheckWithCheck.SimpleCheck {
    private static final long serialVersionUID = 1L;

    @Override
    public boolean isSatisfied(Object obj, Object value) {
      if (obj != null && AlipayDirectProcessRequest.class.isAssignableFrom(obj.getClass())) {
        AlipayDirectProcessRequest request = AlipayDirectProcessRequest.class.cast(obj);

        if (AlipayPaymentMode.APP.equals(request.mode) && value == null) {
          return false;
        }
      }

      return true;
    }
  }
}
