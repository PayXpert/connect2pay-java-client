package com.payxpert.connect2pay.client.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.payxpert.connect2pay.constants.ResultCode;

public class WeChatDirectProcessResponseTest {
  @Test
  public void testWeChatDirectProcessResponse() throws Exception {
    String json = "{\"apiVersion\":\"002.60\",\"code\":\"200\",\"message\":\"Request processed successfully\","
        + "\"webSocketUrl\":\"ws://connect2pay.dev.payxpert.com:9001/wsock/payment/gfrbFvsuAdDvPXYVyOFrhw/wait/restricted/x8la6mw44e-fwg9\","
        + "\"transactionID\":\"x8la6mw44e-1ouu\"}";

    WeChatDirectProcessResponse response = new WeChatDirectProcessResponse().fromJson(json);
    assertNotNull(response);
    assertEquals("002.60", response.getApiVersion());
    assertEquals(ResultCode.SUCCESS, response.getCode());
    assertEquals("Request processed successfully", response.getMessage());
    assertEquals(
        "ws://connect2pay.dev.payxpert.com:9001/wsock/payment/gfrbFvsuAdDvPXYVyOFrhw/wait/restricted/x8la6mw44e-fwg9",
        response.getWebSocketUrl());
    assertEquals("x8la6mw44e-1ouu", response.getTransactionId());
  }
}
