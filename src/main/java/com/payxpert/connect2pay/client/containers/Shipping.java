package com.payxpert.connect2pay.client.containers;

import com.payxpert.connect2pay.utils.Utils;
import net.sf.oval.constraint.MaxLength;

public class Shipping {
    @MaxLength(80)
    private String name;

    @MaxLength(128)
    private String company;

    @MaxLength(50)
    private String address1;

    @MaxLength(50)
    private String address2;

    @MaxLength(50)
    private String address3;

    @MaxLength(16)
    private String zipcode;

    @MaxLength(50)
    private String city;

    @MaxLength(3)
    private String state;

    @MaxLength(2)
    private String countryCode;

    @MaxLength(20)
    private String phone;

    public String getName() {
        return name;
    }

    public Shipping setName(String name) {
        this.name = Utils.limitLength(name, 80);
        return this;
    }

    public String getCompany() {
        return company;
    }

    public Shipping setCompany(String company) {
        this.company = Utils.limitLength(company, 128);
        return this;
    }

    public String getAddress1() {
        return address1;
    }

    public Shipping setAddress1(String address1) {
        this.address1 = Utils.limitLength(address1, 50);
        return this;
    }

    public String getAddress2() {
        return address2;
    }

    public Shipping setAddress2(String address2) {
        this.address2 = Utils.limitLength(address2, 50);
        return this;
    }

    public String getAddress3() {
        return address3;
    }

    public Shipping setAddress3(String address3) {
        this.address3 = Utils.limitLength(address3, 50);
        return this;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Shipping setZipcode(String zipcode) {
        this.zipcode = Utils.limitLength(zipcode, 16);
        return this;
    }

    public String getCity() {
        return city;
    }

    public Shipping setCity(String city) {
        this.city = Utils.limitLength(city, 50);
        return this;
    }

    public String getState() {
        return state;
    }

    public Shipping setState(String state) {
        this.state = Utils.limitLength(state, 3);
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Shipping setCountryCode(String countryCode) {
        this.countryCode = Utils.limitLength(countryCode, 2);
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Shipping setPhone(String phone) {
        this.phone = Utils.limitLength(phone, 20);
        return this;
    }
}
