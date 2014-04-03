package com.payxpert.connect2pay.constants;

/**
 * Enumeration to use in case a required field don't have a value:
 * 
 * <pre>
 * ...
 * request.setShopperEmail(UnavailableFieldContent.UNAVAILABLE.getValue());
 * request.setShopperCountryCode(UnavailableFieldContent.UNAVAILABLE_COUNTRY.getValue());
 * ...
 * </pre>
 * 
 * @author jsh
 * 
 */
public enum UnavailableFieldContent {
  /**
   * The field content is not available
   */
  UNAVAILABLE("NA"),
  /**
   * The country ISO2 code is unavailable
   */
  UNAVAILABLE_COUNTRY("ZZ");

  private String value;

  UnavailableFieldContent(String value) {
    this.value = value;
  }

  /**
   * Get the value to use in API calls
   * 
   * @return The value of the current enum member
   */
  public String getValue() {
    return this.value;
  }

  public String valueToString() {
    return this.value.toLowerCase();
  }

  public UnavailableFieldContent fromString(String type) {
    return valueOfFromString(type);
  }

  public static UnavailableFieldContent valueOfFromString(String mode) {
    if (mode != null) {
      for (UnavailableFieldContent subType : UnavailableFieldContent.values()) {
        if (mode.equalsIgnoreCase(subType.value)) {
          return subType;
        }
      }
    }
    return null;
  }

  public static UnavailableFieldContent valueOfFromName(String name) {
    if (name != null) {
      for (UnavailableFieldContent subType : UnavailableFieldContent.values()) {
        if (name.equalsIgnoreCase(subType.name())) {
          return subType;
        }
      }
    }
    return null;
  }
}
