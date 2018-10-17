package com.payxpert.connect2pay.constants;

/**
 * Available languages in the payment page.
 * 
 * @author jsh
 * 
 */
public enum C2PLang {
  EN, FR, ES, IT, DE, ZH_HANT;

  public String getValue() {
    return this.name().toLowerCase();
  }

  public C2PLang fromString(String type) {
    return valueOfFromString(type);
  }

  public static C2PLang valueOfFromString(String type) {
    if (type != null) {
      for (C2PLang subType : C2PLang.values()) {
        if (type.equalsIgnoreCase(subType.name())) {
          return subType;
        }
      }
    }
    return null;
  }
}
