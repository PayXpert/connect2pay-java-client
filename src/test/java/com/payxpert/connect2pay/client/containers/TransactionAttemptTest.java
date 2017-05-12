package com.payxpert.connect2pay.client.containers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.Test;

import com.payxpert.connect2pay.constants.PaymentType;
import com.payxpert.connect2pay.constants.TransactionOperation;
import com.payxpert.connect2pay.constants.TransactionStatusValue;

public class TransactionAttemptTest {

  /**
   * Test Transaction Attempt creation
   */
  @Test
  public void TransactionAttemptCreationTest() {
    TransactionAttempt trans1 = getDefaultTransactionAttempt();

    // Test getter
    Calendar calendar = Calendar.getInstance();
    calendar.set(2017, 11, 10, 8, 12, 35);
    calendar.set(Calendar.MILLISECOND, 12);

    assertEquals(calendar.getTime(), trans1.getDate());
    assertEquals(Integer.valueOf(1234), trans1.getAmount());
    assertEquals(TransactionOperation.SALE, trans1.getOperation());
    assertEquals(PaymentType.CREDIT_CARD, trans1.getPaymentType());
    assertEquals("000", trans1.getResultCode());
    assertEquals("Success", trans1.getResultMessage());

    assertEquals(TransactionStatusValue.AUTHORIZED, trans1.getStatus());
    assertEquals(Long.valueOf(123456L), trans1.getSubscriptionId());
    assertEquals("QWERTY01234", trans1.getTransactionId());

    Shopper shopperObj = trans1.getShopper();
    assertNotNull(shopperObj);
    assertEquals("35 Milton Avenue", shopperObj.getAddress());
    assertEquals("20000101", shopperObj.getBirthDate());
    assertEquals("New York", shopperObj.getCity());
    assertEquals("US", shopperObj.getCountryCode());
    assertEquals("james.bond@bss.uk", shopperObj.getEmail());
    assertEquals("ADV123456", shopperObj.getIdNumber());
    assertEquals("123.456.789.123", shopperObj.getIpAddress());
    assertEquals("James Bond", shopperObj.getName());
    assertEquals("+22 456 789 456", shopperObj.getPhone());
    assertEquals("New York state", shopperObj.getState());

    CreditCardPaymentMeanInfo paymentMean = trans1.getCCPaymentMeanInfo();
    assertNotNull(paymentMean);

    assertEquals(calendar.getTime(), paymentMean.getDate());
    assertEquals("411111XXXXXX1111", paymentMean.getCardNumber());
    assertEquals("2024", paymentMean.getCardExpireYear());
    assertEquals("10", paymentMean.getCardExpireMonth());
    assertEquals("Charles Ingals", paymentMean.getCardHolderName());
    assertEquals("VISA", paymentMean.getCardBrand());
    assertEquals("other", paymentMean.getCardLevel());
    assertEquals("credit", paymentMean.getCardSubType());
    assertEquals("US", paymentMean.getIinCountry());
    assertEquals("bank of hawaii", paymentMean.getIinBankName());
    assertEquals(false, paymentMean.getIs3DSecure());
    assertEquals("My bank statement", paymentMean.getStatementDescriptor());
  }

  /**
   * Test the connection fails to invalid IP address
   */
  @Test
  public void TransactionAttemptComparatorTest() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(2017, 11, 10, 8, 12, 35);
    calendar.set(Calendar.MILLISECOND, 12);
    TransactionAttempt trans1 = getDefaultTransactionAttempt();
    trans1.setDate(calendar.getTime());

    TransactionAttempt trans2 = getDefaultTransactionAttempt();
    assertEquals(0, trans1.compareTo(trans2));

    calendar.set(2016, 11, 10, 8, 13, 35);
    trans2.setDate(calendar.getTime());
    assertEquals(1, trans1.compareTo(trans2));

    calendar.set(2017, 11, 10, 8, 13, 35);
    trans2.setDate(calendar.getTime());
    assertEquals(-1, trans1.compareTo(trans2));
  }

  public TransactionAttempt getDefaultTransactionAttempt() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(2017, 11, 10, 8, 12, 35);
    calendar.set(Calendar.MILLISECOND, 12);

    TransactionAttempt trans1 = new TransactionAttempt();
    trans1.setDate(calendar.getTime());
    trans1.setAmount(1234);
    trans1.setOperation(TransactionOperation.SALE);
    trans1.setPaymentType(PaymentType.CREDIT_CARD);
    trans1.setResultCode("000");
    trans1.setResultMessage("Success");
    Shopper shopper = new Shopper();
    shopper.setAddress("35 Milton Avenue");
    shopper.setBirthDate("20000101");
    shopper.setCity("New York");
    shopper.setCountryCode("US");
    shopper.setEmail("james.bond@bss.uk");
    shopper.setIdNumber("ADV123456");
    shopper.setIpAddress("123.456.789.123");
    shopper.setName("James Bond");
    shopper.setPhone("+22 456 789 456");
    shopper.setState("New York state");

    trans1.setShopper(shopper);
    trans1.setStatus(TransactionStatusValue.AUTHORIZED);
    trans1.setSubscriptionId(123456L);
    trans1.setTransactionId("QWERTY01234");

    StringBuilder strBuilder = new StringBuilder(50);
    strBuilder.append('{');

    strBuilder.append("\"date\":").append(calendar.getTime().getTime());
    strBuilder.append(',').append("\"cardNumber\":").append('"').append("411111XXXXXX1111").append('"');
    strBuilder.append(',').append("\"cardExpireYear\":").append('"').append("2024").append('"');
    strBuilder.append(',').append("\"cardExpireMonth\":").append('"').append("10").append('"');
    strBuilder.append(',').append("\"cardHolderName\":").append('"').append("Charles Ingals").append('"');
    strBuilder.append(',').append("\"cardBrand\":").append('"').append("VISA").append('"');
    strBuilder.append(',').append("\"cardLevel\":").append('"').append("other").append('"');
    strBuilder.append(',').append("\"cardSubType\":").append('"').append("credit").append('"');
    strBuilder.append(',').append("\"iinCountry\":").append('"').append("US").append('"');
    strBuilder.append(',').append("\"iinBankName\":").append('"').append("bank of hawaii").append('"');
    strBuilder.append(',').append("\"is3DSecure\":").append('"').append("false").append('"');
    strBuilder.append(',').append("\"statementDescriptor\":").append('"').append("My bank statement").append('"');
    strBuilder.append('}');

    trans1.setPaymentMeanInfo(strBuilder.toString());
    return trans1;
  }
}