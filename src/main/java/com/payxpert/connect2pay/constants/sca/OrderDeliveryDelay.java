package com.payxpert.connect2pay.constants.sca;

import java.util.Arrays;
import java.util.Optional;

public enum OrderDeliveryDelay {
    /**
     * 01 -> Electronic Delivery
     */
    ELECTRONIC("01"),
    /**
     * 02 -> Same day shipping
     */
    SAME_DAY("02"),
    /**
     * 03 -> Overnight shipping
     */
    OVERNIGHT("03"),
    /**
     * 04 -> Two-day or more shipping
     */
    TWO_OR_MORE_DAY("04");

    private String value;

    OrderDeliveryDelay(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Optional<OrderDeliveryDelay> fromValue(String value) {
        return Arrays.stream(OrderDeliveryDelay.values()).filter(orderDeliveryDelay -> {
            return orderDeliveryDelay.getValue().equalsIgnoreCase(value);
        }).findFirst().map(Optional::of).orElse(Optional.empty());
    }
}