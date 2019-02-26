package com.payxpert.connect2pay.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.payxpert.connect2pay.constants.SubscriptionType;
import com.payxpert.connect2pay.utils.PartPayment;

public class PartPaymentTest {

  @Test
  public void testValidPartPayment() {
    PartPayment payment = new PartPayment(5000, 3);
    assertFalse(payment.isResultValid());
    assertTrue(payment.compute());
    assertTrue(payment.isResultValid());
    assertEquals(SubscriptionType.NORMAL, payment.getSubscriptionType());
    assertEquals(2, payment.getMaxIterations());
    assertEquals(1668, payment.getInitialAmount());
    assertEquals(1666, payment.getRebillAmount());

    payment.setAmount(3533);
    assertFalse(payment.isResultValid());
    assertTrue(payment.compute());
    assertTrue(payment.isResultValid());
    assertEquals(SubscriptionType.NORMAL, payment.getSubscriptionType());
    assertEquals(2, payment.getMaxIterations());
    assertEquals(1179, payment.getInitialAmount());
    assertEquals(1177, payment.getRebillAmount());

    payment.setPartNumber(7);
    assertFalse(payment.isResultValid());
    assertTrue(payment.compute());
    assertTrue(payment.isResultValid());
    assertEquals(SubscriptionType.NORMAL, payment.getSubscriptionType());
    assertEquals(6, payment.getMaxIterations());
    assertEquals(509, payment.getInitialAmount());
    assertEquals(504, payment.getRebillAmount());

    payment.setAmount(3533);
    payment.setPartNumber(3533);
    assertFalse(payment.isResultValid());
    assertTrue(payment.compute());
    assertTrue(payment.isResultValid());
    assertEquals(SubscriptionType.NORMAL, payment.getSubscriptionType());
    assertEquals(3532, payment.getMaxIterations());
    assertEquals(1, payment.getInitialAmount());
    assertEquals(1, payment.getRebillAmount());
  }

  @Test
  public void testInvalidPartPayment() {
    PartPayment payment = new PartPayment(-1000, 3);
    assertFalse(payment.compute());
    assertFalse(payment.isResultValid());

    payment.setAmount(2);
    assertFalse(payment.compute());
    assertFalse(payment.isResultValid());

    payment.setAmount(-1000);
    payment.setPartNumber(-1);
    assertFalse(payment.compute());
    assertFalse(payment.isResultValid());

    payment.setAmount(1000);
    payment.setPartNumber(-1);
    assertFalse(payment.compute());
    assertFalse(payment.isResultValid());

    payment.setAmount(1000);
    payment.setPartNumber(-13);
    assertFalse(payment.compute());
    assertFalse(payment.isResultValid());

    payment.setAmount(0);
    payment.setPartNumber(-13);
    assertFalse(payment.compute());
    assertFalse(payment.isResultValid());

    payment.setAmount(0);
    payment.setPartNumber(0);
    assertFalse(payment.compute());
    assertFalse(payment.isResultValid());

    payment.setAmount(3000);
    payment.setPartNumber(0);
    assertFalse(payment.compute());
    assertFalse(payment.isResultValid());

  }
}
