package com.payxpert.connect2pay.client.requests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.payxpert.connect2pay.constants.C2PLang;
import com.payxpert.connect2pay.constants.PaymentMode;
import com.payxpert.connect2pay.constants.PaymentType;
import com.payxpert.connect2pay.constants.ShippingType;
import com.payxpert.connect2pay.constants.SubscriptionType;
import com.payxpert.connect2pay.exception.BadRequestException;

public class TransactionRequestTest {

  private final static List<FieldState> requestFields = new ArrayList<FieldState>();

  public TransactionRequestTest() {
    requestFields.add(new FieldState("shopperFields", "shopperEmail", false));
    requestFields.add(new FieldState("ecommerceFields", "shipToFirstName", false));
    requestFields.add(new FieldState("ecommerceFields", "shipToLastName", false));
    requestFields.add(new FieldState("ecommerceFields", "shipToCompany", false));
    requestFields.add(new FieldState("ecommerceFields", "shipToPhone", false));
    requestFields.add(new FieldState("ecommerceFields", "shipToAddress", false));
    requestFields.add(new FieldState("ecommerceFields", "shipToState", false));
    requestFields.add(new FieldState("ecommerceFields", "shipToZipcode", false));
    requestFields.add(new FieldState("ecommerceFields", "shipToCity", false));
    requestFields.add(new FieldState("ecommerceFields", "shipToCountryCode", false));

    requestFields.add(new FieldState("shopperFields", "shopperFirstName", false));
    requestFields.add(new FieldState("shopperFields", "shopperLastName", false));
    requestFields.add(new FieldState("shopperFields", "shopperPhone", false));
    requestFields.add(new FieldState("shopperFields", "shopperAddress", false));
    requestFields.add(new FieldState("shopperFields", "shopperState", false));
    requestFields.add(new FieldState("shopperFields", "shopperZipcode", false));
    requestFields.add(new FieldState("shopperFields", "shopperCity", false));
    requestFields.add(new FieldState("shopperFields", "shopperCountryCode", false));
    requestFields.add(new FieldState("shopperFields", "shopperCompany", false));
    requestFields.add(new FieldState("shopperFields", "shopperLoyaltyProgram", false));

    requestFields.add(new FieldState("commonFields", "orderID", true));
    requestFields.add(new FieldState("commonFields", "currency", true));
    requestFields.add(new FieldState("commonFields", "amount", true));

    requestFields.add(new FieldState("ecommerceFields", "orderTotalWithoutShipping", false));
    requestFields.add(new FieldState("ecommerceFields", "orderShippingPrice", false));
    requestFields.add(new FieldState("ecommerceFields", "orderDiscount", false));

    requestFields.add(new FieldState("ecommerceFields", "orderFOLanguage", false));
    requestFields.add(new FieldState("ecommerceFields", "orderDescription", false));
    requestFields.add(new FieldState("ecommerceFields", "orderCartContent", false));
    requestFields.add(new FieldState("ecommerceFields", "shippingType", true));
    requestFields.add(new FieldState("ecommerceFields", "shippingName", false));

    requestFields.add(new FieldState("ecommerceFields", "paymentMode", true));
    requestFields.add(new FieldState("ecommerceFields", "paymentType", true));
    requestFields.add(new FieldState("ecommerceFields", "operation", false));
    requestFields.add(new FieldState("ecommerceFields", "secure3d", false));

    requestFields.add(new FieldState("subscriptionFields", "offerID", false));
    requestFields.add(new FieldState("subscriptionFields", "subscriptionType", false));
    requestFields.add(new FieldState("subscriptionFields", "trialPeriod", false));
    requestFields.add(new FieldState("subscriptionFields", "rebillAmount", false));
    requestFields.add(new FieldState("subscriptionFields", "rebillPeriod", false));
    requestFields.add(new FieldState("subscriptionFields", "rebillMaxIteration", false));

    requestFields.add(new FieldState("controlFields", "ctrlRedirectURL", false));
    requestFields.add(new FieldState("controlFields", "ctrlCallbackURL", false));
    requestFields.add(new FieldState("controlFields", "ctrlCustomData", false));
    requestFields.add(new FieldState("controlFields", "timeOut", false));
    requestFields.add(new FieldState("controlFields", "merchantNotification", false));
    requestFields.add(new FieldState("controlFields", "merchantNotificationTo", false));
    requestFields.add(new FieldState("controlFields", "merchantNotificationLang", false));
    requestFields.add(new FieldState("controlFields", "themeID", false));
  }

  @Test
  public void testSaleRequestWellformed() throws Exception {
    TransactionRequest request = TransactionRequestTest.getDefaultRequest();

    // No exception must be thrown
    request.validate();
  }

  @Test
  public void testRequestLongField() throws Exception {
    TransactionRequest request = TransactionRequestTest.getDefaultRequest();
    request.setShopperFirstName(
        "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

    // No exception must be thrown
    request.validate();
    Assert.assertTrue(request.getShopperFirstName().length() <= 35);
  }

  @Test(expected = BadRequestException.class)
  public void testSaleRequestMissingAmount() throws Exception {
    TransactionRequest request = TransactionRequestTest.getDefaultRequest();
    request.setAmount(null);
    request.validate();
  }

  @Test(expected = BadRequestException.class)
  public void testSaleRequestMissingCurrency() throws Exception {
    TransactionRequest request = TransactionRequestTest.getDefaultRequest();
    request.setCurrency("");
    request.validate();
  }

  @Test
  public void testJsonFormating() {
    TransactionRequest request = getDefaultFullRequest();

    String json = "";
    try {
      json = request.toJson();
    } catch (JsonGenerationException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (FieldState field : requestFields) {
      if (field.isMandatory) {
        assertTrue("Sale Request : field=" + field.group + "[" + field.name + "] must be present in " + json,
            json.contains(field.name));
      }
    }
  }

  public static TransactionRequest getDefaultRequest() {
    TransactionRequest request = new TransactionRequest();

    request.setPaymentType(PaymentType.CREDIT_CARD).setPaymentMode(PaymentMode.SINGLE)
        .setOrderId(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
    request.setAmount(100).setCurrency("EUR").setShopperFirstName("Bernard").setShopperLastName("Ménez")
        .setShopperAddress("Passeig de Gracia, 55").setShopperZipcode("08008").setShopperCity("Barcelona")
        .setShopperState("Barcelona").setShopperCountryCode("ES").setShopperPhone("+34666666666")
        .setShopperEmail("bernard.menez@gmail.com").setShippingType(ShippingType.VIRTUAL)
        .setCtrlRedirectURL("https://redirect.here/");

    return request;
  }

  public static TransactionRequest getDefaultFullRequest() {
    TransactionRequest request = getDefaultRequest();

    request.setShipToFirstName("Bernard").setShipToLastName("Ménez").setShipToAddress("Passeig de Gracia, 55")
        .setShipToZipcode("08008").setShipToCity("Barcelona").setShipToState("Barcelona").setShipToCountryCode("ES")
        .setShipToPhone("+34666666666").setMerchantNotification(true).setMerchantNotificationTo("dev@payxpert.com")
        .setMerchantNotificationLang(C2PLang.EN);

    request.setOrderDescription("orderDescription");
    request.setOfferId(1L);
    request.setSubscriptionType(SubscriptionType.NORMAL).setRebillAmount(100).setRebillPeriod("P1M")
        .setRebillMaxIteration(10).setTrialPeriod("P1M");

    return request;
  }
}
