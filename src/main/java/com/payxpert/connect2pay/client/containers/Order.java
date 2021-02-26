package com.payxpert.connect2pay.client.containers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payxpert.connect2pay.constants.sca.OrderDeliveryDelay;
import com.payxpert.connect2pay.constants.sca.OrderType;
import com.payxpert.connect2pay.constants.sca.ShippingType;
import com.payxpert.connect2pay.utils.Utils;
import net.sf.oval.constraint.*;

import java.util.List;

public class Order {
    @NotNull
    @NotEmpty
    @MaxLength(100)
    private String id;

    private OrderType type;

    private ShippingType shippingType;

    private OrderDeliveryDelay deliveryDelay;

    @MaxLength(100)
    @MatchPattern(pattern = {
        "NA|[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[a-zA-Z0-9](?:[\\w-]*[\\w])?" })
    private String deliveryEmail;

    private Boolean reorder;

    private Boolean preOrder;

    @MaxLength(8)
    @MatchPattern(pattern = "^[0-9]{8}$")
    private String preOrderDate;

    private Integer prepaidAmount;

    @MaxLength(3)
    private String prepaidCurrency;

    private Integer prepaidCount;

    @MaxLength(50)
    private String shopperLoyaltyProgram;

    private Integer totalWithoutShipping;

    private Integer shippingPrice;

    private Integer discount;

    @MaxLength(500)
    private String description;

    private List<CartProduct> cartContent;

    @MaxLength(16)
    @MatchPattern(pattern = "^[0-9]+$")
    @JsonProperty("affiliateID")
    private String affiliateId;

    @MaxLength(128)
    private String campaignName;

    @AssertValid
    private Recurrence recurrence;

    public String getId() {
        return id;
    }

    public Order setId(String id) {
        this.id = Utils.limitLength(id, 100);
        return this;
    }

    public OrderType getType() {
        return type;
    }

    public Order setType(OrderType type) {
        this.type = type;
        return this;
    }

    public ShippingType getShippingType() {
        return shippingType;
    }

    public Order setShippingType(ShippingType shippingType) {
        this.shippingType = shippingType;
        return this;
    }

    public OrderDeliveryDelay getDeliveryDelay() {
        return deliveryDelay;
    }

    public Order setDeliveryDelay(OrderDeliveryDelay deliveryDelay) {
        this.deliveryDelay = deliveryDelay;
        return this;
    }

    public String getDeliveryEmail() {
        return deliveryEmail;
    }

    public Order setDeliveryEmail(String deliveryEmail) {
        this.deliveryEmail = Utils.limitLength(deliveryEmail, 100);
        return this;
    }

    public Boolean getReorder() {
        return reorder;
    }

    public Order setReorder(Boolean reorder) {
        this.reorder = reorder;
        return this;
    }

    public Boolean getPreOrder() {
        return preOrder;
    }

    public Order setPreOrder(Boolean preOrder) {
        this.preOrder = preOrder;
        return this;
    }

    public String getPreOrderDate() {
        return preOrderDate;
    }

    public Order setPreOrderDate(String preOrderDate) {
        this.preOrderDate = Utils.limitLength(preOrderDate, 8);
        return this;
    }

    public Integer getPrepaidAmount() {
        return prepaidAmount;
    }

    public Order setPrepaidAmount(Integer prepaidAmount) {
        this.prepaidAmount = prepaidAmount;
        return this;
    }

    public String getPrepaidCurrency() {
        return prepaidCurrency;
    }

    public Order setPrepaidCurrency(String prepaidCurrency) {
        this.prepaidCurrency = Utils.limitLength(prepaidCurrency, 3);
        return this;
    }

    public Integer getPrepaidCount() {
        return prepaidCount;
    }

    public Order setPrepaidCount(Integer prepaidCount) {
        this.prepaidCount = prepaidCount;
        return this;
    }

    public String getShopperLoyaltyProgram() {
        return shopperLoyaltyProgram;
    }

    public Order setShopperLoyaltyProgram(String shopperLoyaltyProgram) {
        this.shopperLoyaltyProgram = Utils.limitLength(shopperLoyaltyProgram, 50);
        return this;
    }

    public Integer getTotalWithoutShipping() {
        return totalWithoutShipping;
    }

    public Order setTotalWithoutShipping(Integer totalWithoutShipping) {
        this.totalWithoutShipping = totalWithoutShipping;
        return this;
    }

    public Integer getShippingPrice() {
        return shippingPrice;
    }

    public Order setShippingPrice(Integer shippingPrice) {
        this.shippingPrice = shippingPrice;
        return this;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Order setDiscount(Integer discount) {
        this.discount = discount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Order setDescription(String description) {
        this.description = Utils.limitLength(description, 500);
        return this;
    }

    public List<CartProduct> getCartContent() {
        return cartContent;
    }

    public Order setCartContent(List<CartProduct> cartContent) {
        this.cartContent = cartContent;
        return this;
    }

    public String getAffiliateId() {
        return affiliateId;
    }

    public Order setAffiliateId(String affiliateId) {
        this.affiliateId = Utils.limitLength(affiliateId, 16);
        return this;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public Order setCampaignName(String campaignName) {
        this.campaignName = Utils.limitLength(campaignName, 128);
        return this;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public Order setRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
        return this;
    }
}
