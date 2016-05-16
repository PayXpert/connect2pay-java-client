package com.payxpert.connect2pay.utils.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class PaymentMeanInfoDeserializer extends StdDeserializer<String> {
  private static final long serialVersionUID = 4325313438957336046L;

  public PaymentMeanInfoDeserializer() {
    super(String.class);
  }

  @Override
  public String deserialize(JsonParser parser, DeserializationContext context)
      throws IOException, JsonProcessingException {
    JsonToken curr = parser.getCurrentToken();
    if (curr != null && curr.equals(JsonToken.START_OBJECT)) {
      ObjectNode node = parser.readValueAsTree();
      if (node != null) {
        return node.toString();
      }
    }
    throw context.mappingException(String.class);
  }
}
