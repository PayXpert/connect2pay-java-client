package com.payxpert.connect2pay.client.containers;

import com.payxpert.connect2pay.constants.sca.*;
import com.payxpert.connect2pay.utils.Utils;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.MaxLength;

public class Account {
    private ShopperAccountAge age;

    @MaxLength(8)
    @MatchPattern(pattern = "^[0-9]{8}$")
    private String date;

    private ShopperAccountLastChange lastChange;

    @MaxLength(8)
    @MatchPattern(pattern = "^[0-9]{8}$")
    private String lastChangeDate;

    private ShopperAccountPwChange pwChange;

    @MaxLength(8)
    @MatchPattern(pattern = "^[0-9]{8}$")
    private String pwChangeDate;

    private ShippingInfoAge shipInfoAge;

    @MaxLength(8)
    @MatchPattern(pattern = "^[0-9]{8}$")
    private String shipInfoDate;

    private Integer transLastDay;

    private Integer transLastYear;

    private Integer cardsAddLastDay;

    private Integer orderSixMonths;

    private Boolean suspicious;

    private Boolean namesMatching;

    private PaymentMeanAge paymentMeanAge;

    @MaxLength(8)
    @MatchPattern(pattern = "^[0-9]{8}$")
    private String paymentMeanDate;

    public ShopperAccountAge getAge() {
        return age;
    }

    public Account setAge(ShopperAccountAge age) {
        this.age = age;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Account setDate(String date) {
        this.date = Utils.limitLength(date, 8);
        return this;
    }

    public ShopperAccountLastChange getLastChange() {
        return lastChange;
    }

    public Account setLastChange(ShopperAccountLastChange lastChange) {
        this.lastChange = lastChange;
        return this;
    }

    public String getLastChangeDate() {
        return lastChangeDate;
    }

    public Account setLastChangeDate(String lastChangeDate) {
        this.lastChangeDate = Utils.limitLength(lastChangeDate, 8);
        return this;
    }

    public ShopperAccountPwChange getPwChange() {
        return pwChange;
    }

    public Account setPwChange(ShopperAccountPwChange pwChange) {
        this.pwChange = pwChange;
        return this;
    }

    public String getPwChangeDate() {
        return pwChangeDate;
    }

    public Account setPwChangeDate(String pwChangeDate) {
        this.pwChangeDate = Utils.limitLength(pwChangeDate, 8);
        return this;
    }

    public ShippingInfoAge getShipInfoAge() {
        return shipInfoAge;
    }

    public Account setShipInfoAge(ShippingInfoAge shipInfoAge) {
        this.shipInfoAge = shipInfoAge;
        return this;
    }

    public String getShipInfoDate() {
        return shipInfoDate;
    }

    public Account setShipInfoDate(String shipInfoDate) {
        this.shipInfoDate = Utils.limitLength(shipInfoDate, 8);
        return this;
    }

    public Integer getTransLastDay() {
        return transLastDay;
    }

    public Account setTransLastDay(Integer transLastDay) {
        this.transLastDay = transLastDay;
        return this;
    }

    public Integer getTransLastYear() {
        return transLastYear;
    }

    public Account setTransLastYear(Integer transLastYear) {
        this.transLastYear = transLastYear;
        return this;
    }

    public Integer getCardsAddLastDay() {
        return cardsAddLastDay;
    }

    public Account setCardsAddLastDay(Integer cardsAddLastDay) {
        this.cardsAddLastDay = cardsAddLastDay;
        return this;
    }

    public Integer getOrderSixMonths() {
        return orderSixMonths;
    }

    public Account setOrderSixMonths(Integer orderSixMonths) {
        this.orderSixMonths = orderSixMonths;
        return this;
    }

    public Boolean getSuspicious() {
        return suspicious;
    }

    public Account setSuspicious(Boolean suspicious) {
        this.suspicious = suspicious;
        return this;
    }

    public Boolean getNamesMatching() {
        return namesMatching;
    }

    public Account setNamesMatching(Boolean namesMatching) {
        this.namesMatching = namesMatching;
        return this;
    }

    public PaymentMeanAge getPaymentMeanAge() {
        return paymentMeanAge;
    }

    public Account setPaymentMeanAge(PaymentMeanAge paymentMeanAge) {
        this.paymentMeanAge = paymentMeanAge;
        return this;
    }

    public String getPaymentMeanDate() {
        return paymentMeanDate;
    }

    public Account setPaymentMeanDate(String paymentMeanDate) {
        this.paymentMeanDate = Utils.limitLength(paymentMeanDate, 8);
        return this;
    }
}
