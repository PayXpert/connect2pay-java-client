package com.payxpert.connect2pay.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payxpert.connect2pay.utils.json.Connect2payClientJacksonModule;

public class Utils {

  protected static final Logger logger = LoggerFactory.getLogger(Utils.class);

  public static ObjectMapper getJSONObjectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    // This will load Mixins
    mapper.registerModule(new Connect2payClientJacksonModule());
    // Ignore unknown fields, this can ease API changes
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    return mapper;
  }

  public static <T> T readJson(String json, Class<T> type) throws Exception {
    if (json == null) {
      return null;
    }
    try {
      return getJSONObjectMapper().readValue(json, type);
    } catch (Exception e) {
      logger.error("Error parsing JSON response : " + e.getMessage());
      logger.debug("JSON is: " + json);
      throw e;
    }
  }
}
