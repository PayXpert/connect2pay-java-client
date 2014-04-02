package com.payxpert.connect2pay.client.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payxpert.connect2pay.utils.json.Connect2payClientJacksonModule;

/**
 * Generic class for the responses received from the API
 * 
 * @author jsh
 * 
 * @param <T>
 *          Real type of the current class used to implement JSON import.
 */
abstract public class GenericResponse<T> {
  protected static final Logger logger = LoggerFactory.getLogger(GenericResponse.class);

  @SuppressWarnings("unchecked")
  public T fromJson(String json) throws Exception {
    T response = null;

    if (json != null) {
      ObjectMapper mapper = new ObjectMapper();
      // This will load Mixins
      mapper.registerModule(new Connect2payClientJacksonModule());

      try {
        response = (T) mapper.readValue(json, this.getClass());
      } catch (Exception e) {
        logger.error("Error parsing JSON response : " + e.getMessage());
        logger.debug("JSON is: " + json);
        e.printStackTrace();
        throw e;
      }
    }

    return response;
  }
}