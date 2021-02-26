package com.payxpert.connect2pay.constants.sca;

import java.util.Arrays;
import java.util.Optional;

public enum ShippingType {
    /**
     * 01 -> Ship to cardholder's billing address
     */
    TO_CARDHOLDER("01"),
    /**
     * 02 -> Ship to another verified address on file with merchant
     */
    TO_VERIFIED_ADDRESS("02"),
    /**
     * 03 -> Ship to address that is different than the cardholder's billing address
     */
    TO_NON_BILLING_ADDRESS("03"),
    /**
     * 04 -> "Ship to Store" / Pick-up at local store (Store address shall be populated in shipping address fields)
     */
    SHIP_TO_STORE("04"),
    /**
     * 05 -> Digital goods (includes online services, electronic gift cards and redemption codes)
     */
    DIGITAL_GOODS("05"),
    /**
     * 06 -> Travel and Event tickets, not shipped
     */
    TRAVEL_EVENT_TICKET("06"),
    /**
     * 07 -> Other (for example, Gaming, digital services not shipped, e-media subscriptions, etc.)
     */
    OTHER("07");

    private String value;

    ShippingType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Optional<ShippingType> fromValue(String value) {
        return Arrays.stream(ShippingType.values()).filter(shippingType -> {
            return shippingType.getValue().equalsIgnoreCase(value);
        }).findFirst().map(Optional::of).orElse(Optional.empty());
    }
}
