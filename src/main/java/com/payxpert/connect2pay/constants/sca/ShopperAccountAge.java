package com.payxpert.connect2pay.constants.sca;

import java.util.Arrays;
import java.util.Optional;

public enum ShopperAccountAge {
    /**
     * 01 -> No account
     */
    NO_ACCOUNT("01"),
    /**
     * 02 -> Created during this transaction
     */
    DURING_TRANSACTION("02"),
    /**
     * 03 -> Less than 30 days
     */
    LESS_30_DAYS("03"),
    /**
     * 04 -> Between 30 and 60 days
     */
    BETWEEN_30_60_DAYS("04"),
    /**
     * 05 -> More than 60 days
     */
    MORE_60_DAYS("05");

    private String value;

    ShopperAccountAge(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Optional<ShopperAccountAge> fromValue(String value) {
        return Arrays.stream(ShopperAccountAge.values()).filter(shopperAccountAge -> {
            return shopperAccountAge.getValue().equalsIgnoreCase(value);
        }).findFirst().map(Optional::of).orElse(Optional.empty());
    }
}
