package com.payxpert.connect2pay.client.requests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

public class TransactionRequestTest {

  private final static List<FieldState> REQUEST_FIELDS = new ArrayList<>();

  public TransactionRequestTest() {
    REQUEST_FIELDS.add(new FieldState("shopperFields", "shopperEmail", false));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "shipToFirstName", false));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "shipToLastName", false));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "shipToCompany", false));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "shipToPhone", false));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "shipToAddress", false));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "shipToState", false));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "shipToZipcode", false));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "shipToCity", false));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "shipToCountryCode", false));

    REQUEST_FIELDS.add(new FieldState("shopperFields", "shopperFirstName", false));
    REQUEST_FIELDS.add(new FieldState("shopperFields", "shopperLastName", false));
    REQUEST_FIELDS.add(new FieldState("shopperFields", "shopperPhone", false));
    REQUEST_FIELDS.add(new FieldState("shopperFields", "shopperAddress", false));
    REQUEST_FIELDS.add(new FieldState("shopperFields", "shopperState", false));
    REQUEST_FIELDS.add(new FieldState("shopperFields", "shopperZipcode", false));
    REQUEST_FIELDS.add(new FieldState("shopperFields", "shopperCity", false));
    REQUEST_FIELDS.add(new FieldState("shopperFields", "shopperCountryCode", false));
    REQUEST_FIELDS.add(new FieldState("shopperFields", "shopperCompany", false));
    REQUEST_FIELDS.add(new FieldState("shopperFields", "shopperLoyaltyProgram", false));
    REQUEST_FIELDS.add(new FieldState("shopperFields", "shopperBirthDate", false));
    REQUEST_FIELDS.add(new FieldState("shopperFields", "shopperIDNumber", false));

    REQUEST_FIELDS.add(new FieldState("commonFields", "orderID", true));
    REQUEST_FIELDS.add(new FieldState("commonFields", "currency", true));
    REQUEST_FIELDS.add(new FieldState("commonFields", "amount", true));

    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "orderTotalWithoutShipping", false));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "orderShippingPrice", false));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "orderDiscount", false));

    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "orderFOLanguage", false));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "orderDescription", false));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "orderCartContent", false));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "shippingType", true));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "shippingName", false));

    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "paymentMode", true));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "paymentType", true));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "operation", false));
    REQUEST_FIELDS.add(new FieldState("ecommerceFields", "secure3d", false));

    REQUEST_FIELDS.add(new FieldState("subscriptionFields", "offerID", false));
    REQUEST_FIELDS.add(new FieldState("subscriptionFields", "subscriptionType", false));
    REQUEST_FIELDS.add(new FieldState("subscriptionFields", "trialPeriod", false));
    REQUEST_FIELDS.add(new FieldState("subscriptionFields", "rebillAmount", false));
    REQUEST_FIELDS.add(new FieldState("subscriptionFields", "rebillPeriod", false));
    REQUEST_FIELDS.add(new FieldState("subscriptionFields", "rebillMaxIteration", false));

    REQUEST_FIELDS.add(new FieldState("controlFields", "ctrlRedirectURL", false));
    REQUEST_FIELDS.add(new FieldState("controlFields", "ctrlCallbackURL", false));
    REQUEST_FIELDS.add(new FieldState("controlFields", "ctrlCustomData", false));
    REQUEST_FIELDS.add(new FieldState("controlFields", "timeOut", false));
    REQUEST_FIELDS.add(new FieldState("controlFields", "merchantNotification", false));
    REQUEST_FIELDS.add(new FieldState("controlFields", "merchantNotificationTo", false));
    REQUEST_FIELDS.add(new FieldState("controlFields", "merchantNotificationLang", false));
    REQUEST_FIELDS.add(new FieldState("controlFields", "themeID", false));
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
  public void testSaleRequestValidShopperEmailValidation() {
    Validator validation = new Validator();
    TransactionRequest request = TransactionRequestTest.getDefaultRequest();

    String[] validEmails = new String[] { "NA", "test@test.com" };
    for (String email : validEmails) {
      request.setShopperEmail(email);
      List<ConstraintViolation> constraintViolations = validation.validate(request);
      assertEquals("Error found on email " + email, 0, constraintViolations.size());
    }
  }

  @Test
  public void testSaleRequestInvalidShopperEmailValidation() {
    Validator validation = new Validator();
    TransactionRequest request = TransactionRequestTest.getDefaultRequest();

    String[] invalidEmails = new String[] { "na", "on", "test@test", "test#test.com.test" };
    for (String email : invalidEmails) {
      request.setShopperEmail(email);
      List<ConstraintViolation> constraintViolations = validation.validate(request);
      assertEquals("No error found on email " + email, 1, constraintViolations.size());
    }
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

    for (FieldState field : REQUEST_FIELDS) {
      if (field.isMandatory) {
        assertTrue("Sale Request : field=" + field.group + "[" + field.name + "] must be present in " + json,
            json.contains(field.name));
      }
    }
  }

  @Test
  public void testSetBirthDate() {
    TransactionRequest request = getDefaultFullRequest();
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/UTC"));

    request.setShopperBirthDate("19700208");
    assertEquals("19700208", request.getShopperBirthDate());
    request.setShopperBirthDate("197002/08");
    assertEquals("197002/0", request.getShopperBirthDate());
    request.setShopperBirthDate("1970/02/08");
    assertEquals("1970/02/", request.getShopperBirthDate());

    calendar.set(1970, Calendar.FEBRUARY, 8);
    request.setShopperBirthDate(calendar.getTime());
    assertEquals("19700208", request.getShopperBirthDate());

    calendar.set(2000, Calendar.DECEMBER, 15);
    request.setShopperBirthDate(calendar.getTime());
    assertEquals("20001215", request.getShopperBirthDate());

    calendar.set(2016, Calendar.JANUARY, 01);
    request.setShopperBirthDate(calendar.getTime());
    assertEquals("20160101", request.getShopperBirthDate());

    calendar.set(2016, Calendar.AUGUST, 31);
    request.setShopperBirthDate(calendar.getTime());
    assertEquals("20160831", request.getShopperBirthDate());
  }

  public static TransactionRequest getDefaultRequest() {
    TransactionRequest request = new TransactionRequest();

    request.setPaymentType(PaymentType.CREDIT_CARD).setPaymentMode(PaymentMode.SINGLE)
        .setOrderId(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
    request.setAmount(100).setCurrency("EUR").setShopperFirstName("Bernard").setShopperLastName("Ménez")
        .setShopperAddress("Passeig de Gracia, 55").setShopperZipcode("08008").setShopperCity("Barcelona")
        .setShopperState("Barcelona").setShopperCountryCode("ES").setShopperPhone("+34666666666")
        .setShopperEmail("bernard.menez@gmail.com").setShopperBirthDate("19700101").setShopperIDNumber("ID12345")
        .setShippingType(ShippingType.VIRTUAL).setCtrlRedirectURL("https://redirect.here/");

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
