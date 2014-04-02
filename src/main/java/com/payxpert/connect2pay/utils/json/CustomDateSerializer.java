package com.payxpert.connect2pay.utils.json;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomDateSerializer extends StdSerializer<Date> {
  protected CustomDateSerializer() {
    super(Date.class);
  }

  @Override
  public void serialize(Date value, JsonGenerator generator, SerializerProvider provider) throws IOException,
      JsonProcessingException {
    generator.writeNumber(value.getTime() / 1000);
  }
}
