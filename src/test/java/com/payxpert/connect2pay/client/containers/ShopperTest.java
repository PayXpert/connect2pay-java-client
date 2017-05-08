package com.payxpert.connect2pay.client.containers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ShopperTest {

  /**
   * Test Transaction Attempt creation
   */
  @Test
  public void shopperCreationTest() {
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

    assertEquals("35 Milton Avenue", shopper.getAddress());
    assertEquals("20000101", shopper.getBirthDate());
    assertEquals("New York", shopper.getCity());
    assertEquals("US", shopper.getCountryCode());
    assertEquals("james.bond@bss.uk", shopper.getEmail());
    assertEquals("ADV123456", shopper.getIdNumber());
    assertEquals("123.456.789.123", shopper.getIpAddress());
    assertEquals("James Bond", shopper.getName());
    assertEquals("+22 456 789 456", shopper.getPhone());
    assertEquals("New York state", shopper.getState());
  }
}
