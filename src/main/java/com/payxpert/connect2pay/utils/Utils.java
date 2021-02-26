package com.payxpert.connect2pay.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    // Don't serialize absent fields, don't use NON_NULL otherwise empty optional gets included
    mapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);

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

  public static String limitLength(String value, int length) {
    if (value != null && value.length() > length) {
      return value.substring(0, length);
    }

    return value;
  }
}
