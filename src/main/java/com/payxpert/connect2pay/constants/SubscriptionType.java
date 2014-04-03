package com.payxpert.connect2pay.constants;

/**
 * The different types of subscriptions to use in transaction creation.
 * 
 * @author jsh <jsh@payxpert.com>
 * 
 */
public enum SubscriptionType {
  /**
   * Subscription with a fixed number of iterations, can have a trial period.
   */
  NORMAL("normal"),
  /**
   * Subscription that has an initial payment and then is valid forever without
   * any other payment.
   */
  LIFETIME("lifetime"),
  /**
   * Subscription that has an initial payment and will expire after a certain
   * time but will not have other payment iteration.
   */
  ONETIME("onetime"),
  /**
   * Normal subscription but without a maximum number of iterations, can
   * potentially last forever.
   */
  INFINITE("infinite");

  private String value;

  SubscriptionType(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public String valueToString() {
    return this.value.toLowerCase();
  }

  public SubscriptionType fromString(String type) {
    return valueOfFromString(type);
  }

  public static SubscriptionType valueOfFromString(String type) {
    if (type != null) {
      for (SubscriptionType subType : SubscriptionType.values()) {
        if (type.toLowerCase().equals(subType.value.toLowerCase())) {
          return subType;
        }
      }
    }
    return null;
  }

  public static SubscriptionType valueOfFromName(String name) {
    if (name != null) {
      for (SubscriptionType subType : SubscriptionType.values()) {
        if (name.toLowerCase().equals(subType.name().toLowerCase())) {
          return subType;
        }
      }
    }
    return null;
  }
}
