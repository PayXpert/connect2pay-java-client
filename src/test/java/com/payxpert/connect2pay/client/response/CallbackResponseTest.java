package com.payxpert.connect2pay.client.response;

import org.junit.Assert;
import org.junit.Test;

public class CallbackResponseTest {

  @Test
  public void testCallbackResponseJSONExport() throws Exception {
    CallbackStatusResponse response = new CallbackStatusResponse();
    response.setStatus("Oh yeah").setMessage("That's it");

    Assert.assertEquals("{\"status\":\"Oh yeah\",\"message\":\"That's it\"}", response.toJson());
  }

  @Test
  public void testCallbackDefaultResponseJSONExport() throws Exception {
    CallbackStatusResponse response = CallbackStatusResponse.getDefaultSuccessResponse();

    Assert.assertEquals("{\"status\":\"OK\",\"message\":\"Status recorded.\"}", response.toJson());

    response = CallbackStatusResponse.getDefaultFailureResponse();

    Assert.assertEquals("{\"status\":\"KO\",\"message\":\"Error when recording status.\"}", response.toJson());
  }

}
