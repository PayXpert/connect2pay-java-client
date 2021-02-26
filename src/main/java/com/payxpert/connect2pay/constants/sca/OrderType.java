package com.payxpert.connect2pay.constants.sca;

import java.util.Arrays;
import java.util.Optional;

public enum OrderType {
    /**
     * 01 -> Goods / Service purchase
     */
    GOODS_SERVICE("01"),
    /**
     * 03 -> Check Acceptance
     */
    CHECK_ACCEPTANCE("03"),
    /**
     * 10 -> Account Funding
     */
    ACCOUNT_FUNDING("10"),
    /**
     * 11 -> Quasi-Cash Transaction
     */
    QUASI_CASH("11"),
    /**
     * 28 -> Prepaid activation and Loan
     */
    PREPAID_LOAN("28");

    private String value;

    OrderType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Optional<OrderType> fromValue(String value) {
        return Arrays.stream(OrderType.values()).filter(orderType -> {
            return orderType.getValue().equalsIgnoreCase(value);
        }).findFirst().map(Optional::of).orElse(Optional.empty());
    }
}
