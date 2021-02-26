package com.payxpert.connect2pay.client.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.payxpert.connect2pay.constants.WeChatPaymentMode;

import com.payxpert.connect2pay.utils.Utils;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.NotNull;

/**
 * Request for the WeChat Direct Process API call.
 * 
 */
public class WeChatDirectProcessRequest extends GenericRequest<WeChatDirectProcessRequest> {
  @NotNull
  @JsonIgnore
  private String customerToken;

  @NotNull
  private WeChatPaymentMode mode;

  @MaxLength(18)
  @CheckWith(ignoreIfNull = false, value = QuickPayCodeValidator.class)
  private String quickPayCode;

  @MaxLength(10)
  private String notificationLang;

  @MaxLength(64)
  private String notificationTimeZone;

  @JsonProperty("openID")
  @MaxLength(64)
  private String openId;

  public String getCustomerToken() {
    return customerToken;
  }

  public WeChatDirectProcessRequest setCustomerToken(String customerToken) {
    this.customerToken = customerToken;
    return getThis();
  }

  public WeChatPaymentMode getMode() {
    return mode;
  }

  public WeChatDirectProcessRequest setMode(WeChatPaymentMode mode) {
    this.mode = mode;
    return getThis();
  }

  public String getQuickPayCode() {
    return quickPayCode;
  }

  public WeChatDirectProcessRequest setQuickPayCode(String quickPayCode) {
    this.quickPayCode = Utils.limitLength(quickPayCode, 18);
    return getThis();
  }

  public String getNotificationLang() {
    return notificationLang;
  }

  public WeChatDirectProcessRequest setNotificationLang(String notificationLang) {
    this.notificationLang = Utils.limitLength(notificationLang, 10);
    return getThis();
  }

  public String getNotificationTimeZone() {
    return notificationTimeZone;
  }

  public WeChatDirectProcessRequest setNotificationTimeZone(String notificationTimeZone) {
    this.notificationTimeZone = Utils.limitLength(notificationTimeZone, 64);
    return getThis();
  }

  public String getOpenId() {
    return openId;
  }

  public WeChatDirectProcessRequest setOpenId(String openId) {
    this.openId = openId;
    return getThis();
  }

  @Override
  protected WeChatDirectProcessRequest getThis() {
    return this;
  }

  private static class QuickPayCodeValidator implements CheckWithCheck.SimpleCheck {
    private static final long serialVersionUID = 1L;

    @Override
    public boolean isSatisfied(Object obj, Object value) {
      if (obj != null && WeChatDirectProcessRequest.class.isAssignableFrom(obj.getClass())) {
        WeChatDirectProcessRequest request = WeChatDirectProcessRequest.class.cast(obj);

        if (WeChatPaymentMode.QUICKPAY.equals(request.mode) && value == null) {
          return false;
        }
      }

      return true;
    }
  }
}
