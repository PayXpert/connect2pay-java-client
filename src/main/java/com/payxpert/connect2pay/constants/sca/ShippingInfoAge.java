package com.payxpert.connect2pay.constants.sca;

import java.util.Arrays;
import java.util.Optional;

public enum ShippingInfoAge {
    /**
     * 01 -> Changed during this transaction
     */
    DURING_TRANSACTION("01"),
    /**
     * 02 -> Less than 30 days
     */
    LESS_30_DAYS("02"),
    /**
     * 03 -> Between 30 and 60 days
     */
    BETWEEN_30_60_DAYS("03"),
    /**
     * 04 -> More than 60 days
     */
    MORE_60_DAYS("04");

    private String value;

    ShippingInfoAge(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Optional<ShippingInfoAge> fromValue(String value) {
        return Arrays.stream(ShippingInfoAge.values()).filter(shippingInfoAge -> {
            return shippingInfoAge.getValue().equalsIgnoreCase(value);
        }).findFirst().map(Optional::of).orElse(Optional.empty());
    }
}
