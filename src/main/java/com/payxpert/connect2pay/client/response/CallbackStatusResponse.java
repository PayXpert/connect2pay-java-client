package com.payxpert.connect2pay.client.response;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payxpert.connect2pay.utils.json.Connect2payClientJacksonModule;

/**
 * A structure that can be used to easily respond to the payment page callback.
 * 
 * @author jsh
 * 
 */
public class CallbackStatusResponse {
  protected static final Logger logger = LoggerFactory.getLogger(CallbackStatusResponse.class);

  private String status;
  private String message;

  /**
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * @param status
   *          the status to set
   * @return the current status for method chaining
   */
  public CallbackStatusResponse setStatus(String status) {
    this.status = status;
    return this;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param message
   *          the message to set
   * @return the current status for method chaining
   */
  public CallbackStatusResponse setMessage(String message) {
    this.message = message;
    return this;
  }

  /**
   * Export the current callback response as a JSON string.
   * 
   * @return The JSON string representation of the request.
   * @throws JsonGenerationException
   * @throws JsonMappingException
   * @throws IOException
   */
  public String toJson() throws JsonGenerationException, JsonMappingException, IOException {
    String json = null;

    ObjectMapper mapper = new ObjectMapper();
    // This will load Mixins
    mapper.registerModule(new Connect2payClientJacksonModule());

    try {
      json = mapper.writeValueAsString(this);
    } catch (JsonGenerationException e) {
      logger.error("Error generating JSON response : " + e.getMessage());
      e.printStackTrace();
      throw e;
    } catch (JsonMappingException e) {
      logger.error("Error mapping JSON response: " + e.getMessage());
      e.printStackTrace();
      throw e;
    } catch (IOException e) {
      logger.error("IO Error generating JSON response: " + e.getMessage());
      e.printStackTrace();
      throw e;
    }

    return json;
  }

  /**
   * Get a default success callback execution response. Can be exported as JSON
   * using the toJson() method and written to the response output.
   * 
   * @return A CallbackStatusResponse that will be interpreted as a success by
   *         the payment page application
   */
  public static CallbackStatusResponse getDefaultSuccessResponse() {
    CallbackStatusResponse response = new CallbackStatusResponse();
    response.setStatus("OK").setMessage("Status recorded.");
    return response;
  }

  /**
   * Get a default failure callback execution response. Can be exported as JSON
   * using the toJson() method and written to the response output.
   * 
   * @return A CallbackStatusResponse that will be interpreted as a failure by
   *         the payment page application
   */
  public static CallbackStatusResponse getDefaultFailureResponse() {
    CallbackStatusResponse response = new CallbackStatusResponse();
    response.setStatus("KO").setMessage("Error when recording status.");
    return response;
  }
}