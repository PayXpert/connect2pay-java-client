package com.payxpert.connect2pay.utils.json;

import java.io.IOException;
import java.util.function.Function;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.payxpert.connect2pay.constants.C2PLang;
import com.payxpert.connect2pay.constants.PaymentMode;
import com.payxpert.connect2pay.constants.PaymentMethod;
import com.payxpert.connect2pay.constants.ResultCode;
import com.payxpert.connect2pay.constants.ShippingType;
import com.payxpert.connect2pay.constants.SubscriptionCancelReason;
import com.payxpert.connect2pay.constants.SubscriptionType;
import com.payxpert.connect2pay.constants.TransactionOperation;
import com.payxpert.connect2pay.constants.PaymentStatusValue;

/**
 * Jackson JSON module for the application.
 * 
 * @author jsh
 * 
 */
public class Connect2payClientJacksonModule extends SimpleModule {
  private static final long serialVersionUID = -5487183222015832502L;

  public Connect2payClientJacksonModule() {
    this.addSerializer(new CustomDateSerializer());

    this.setupEnum(PaymentMode.class, PaymentMode::getValue, PaymentMode::valueOfFromString);
    this.setupEnum(PaymentMethod.class, PaymentMethod::getValue, PaymentMethod::valueOfFromString);
    this.setupEnum(ShippingType.class, ShippingType::getValue, ShippingType::valueOfFromString);
    this.setupIntEnum(SubscriptionCancelReason.class, //
        SubscriptionCancelReason::getCode, SubscriptionCancelReason::valueOf);
    this.setupEnum(SubscriptionType.class, SubscriptionType::getValue, SubscriptionType::valueOfFromString);
    this.setupIntEnum(ResultCode.class, ResultCode::getCode, ResultCode::valueOfFromCode);
    this.setupEnum(TransactionOperation.class, //
        TransactionOperation::valueToString, TransactionOperation::valueOfFromString);
    this.setupEnum(PaymentStatusValue.class, PaymentStatusValue::getLabel, PaymentStatusValue::valueOfFromLabel);
    this.setupEnum(C2PLang.class, C2PLang::getValue, C2PLang::valueOfFromString);
  }

  @Override
  public String getModuleName() {
    return "Connect2payClient";
  }

  @Override
  public Version version() {
    return new Version(1, 0, 0, this.getModuleName(), "com.payxpert", "connect2pay-client");
  }

  private <T extends Enum<T>> void setupEnum( //
      Class<T> enumType, //
      Function<T, String> serializer, //
      Function<String, T> deserializer) {
    this.addSerializer(enumType, new StdSerializer<T>(enumType) {
      @Override
      public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(serializer.apply(value));
      }
    });
    this.addDeserializer(enumType, new StdDeserializer<T>(enumType) {
      @Override
      public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (p.getCurrentToken() == JsonToken.VALUE_STRING) {
          T value = deserializer.apply(p.getText());
          if (value == null) {
            throw InvalidFormatException.from(p, "Can not deserialize value to enum. Invalid value.", p.getText(),
                    enumType);
          }
          return value;
        } else {
          throw InvalidFormatException.from(p, "Can not deserialize value to enum. Expected VALUE_STRING", p.getText(),
              enumType);
        }
      }
    });
  }

  private <T extends Enum<T>> void setupIntEnum( //
      Class<T> enumType, //
      Function<T, Integer> serializer, //
      Function<Integer, T> deserializer) {
    this.addSerializer(enumType, new StdSerializer<T>(enumType) {
      @Override
      public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeNumber(serializer.apply(value));
      }
    });
    this.addDeserializer(enumType, new StdDeserializer<T>(enumType) {
      @Override
      public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        if (p.getCurrentToken() == JsonToken.VALUE_NUMBER_INT) {

          return deserializer.apply(p.getIntValue());

        } else if (p.getCurrentToken() == JsonToken.VALUE_STRING) {

          try {

            return deserializer.apply(Integer.parseInt(p.getText()));
          } catch (NumberFormatException e) {
            // handled below
          }

        }

        throw InvalidFormatException.from(p,
            "Can not deserialize value to enum. Expected VALUE_NUMBER_INT or VALUE_STRING containing an integer",
            p.getText(), enumType);
      }
    });
  }
}