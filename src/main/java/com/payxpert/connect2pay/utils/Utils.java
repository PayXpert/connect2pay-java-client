package com.payxpert.connect2pay.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payxpert.connect2pay.utils.json.Connect2payClientJacksonModule;

public class Utils {
  public static ObjectMapper getJSONObjectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    // This will load Mixins
    mapper.registerModule(new Connect2payClientJacksonModule());
    // Ignore unknown fields, this can ease API changes
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    return mapper;
  }
}
