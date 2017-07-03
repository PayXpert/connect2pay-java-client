package com.payxpert.connect2pay.constants;

/**
 * Available Shipping Types to be used to build payments.
 * 
 * @author jsh
 * 
 */
public enum ShippingType {
  /**
   * Classic physical shipping
   */
  PHYSICAL("Physical"),

  /**
   * The order gives an access, no physical delivery
   */
  ACCESS("Access"),

  /**
   * The order concerns a virtual product
   */
  VIRTUAL("Virtual");

  private String value;

  ShippingType(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public String valueToString() {
    return this.value.toLowerCase();
  }

  public ShippingType fromString(String type) {
    return valueOfFromString(type);
  }

  public static ShippingType valueOfFromString(String type) {
    if (type != null) {
      for (ShippingType subType : ShippingType.values()) {
        if (type.equalsIgnoreCase(subType.value)) {
          return subType;
        }
      }
    }
    return null;
  }

  public static ShippingType valueOfFromName(String name) {
    if (name != null) {
      for (ShippingType subType : ShippingType.values()) {
        if (name.equalsIgnoreCase(subType.name())) {
          return subType;
        }
      }
    }
    return null;
  }
}
