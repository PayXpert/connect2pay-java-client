package com.payxpert.connect2pay.constants;

/**
 * Available Shipping Types to be used to build transactions.
 * 
 * @author jsh
 * 
 */
public enum ShippingType {
  PHYSICAL("Physical"), ACCESS("Access"), VIRTUAL("Virtual");

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
        if (type.toLowerCase().equals(subType.value.toLowerCase())) {
          return subType;
        }
      }
    }
    return null;
  }

  public static ShippingType valueOfFromName(String name) {
    if (name != null) {
      for (ShippingType subType : ShippingType.values()) {
        if (name.toLowerCase().equals(subType.name().toLowerCase())) {
          return subType;
        }
      }
    }
    return null;
  }
}
