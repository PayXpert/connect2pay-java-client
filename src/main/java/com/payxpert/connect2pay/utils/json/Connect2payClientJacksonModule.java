package com.payxpert.connect2pay.utils.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.payxpert.connect2pay.constants.C2PLang;
import com.payxpert.connect2pay.constants.PaymentMode;
import com.payxpert.connect2pay.constants.PaymentType;
import com.payxpert.connect2pay.constants.ResultCode;
import com.payxpert.connect2pay.constants.ShippingType;
import com.payxpert.connect2pay.constants.SubscriptionCancelReason;
import com.payxpert.connect2pay.constants.SubscriptionType;
import com.payxpert.connect2pay.constants.TransactionOperation;
import com.payxpert.connect2pay.constants.TransactionStatusValue;

/**
 * Jackson JSON module for the application.
 * 
 * @author jsh
 * 
 */
public class Connect2payClientJacksonModule extends SimpleModule {
  private static final long serialVersionUID = -5487183222015832502L;

  @Override
  public String getModuleName() {
    return "Connect2payClient";
  }

  @Override
  public void setupModule(SetupContext context) {
    this.addSerializer(new CustomDateSerializer());
    context.setMixInAnnotations(PaymentMode.class, PaymentModeMixIn.class);
    context.setMixInAnnotations(PaymentType.class, PaymentTypeMixIn.class);
    context.setMixInAnnotations(ShippingType.class, ShippingTypeMixIn.class);
    context.setMixInAnnotations(SubscriptionCancelReason.class, SubscriptionCancelReasonMixIn.class);
    context.setMixInAnnotations(SubscriptionType.class, SubscriptionTypeMixIn.class);
    context.setMixInAnnotations(ResultCode.class, ResultCodeMixIn.class);
    context.setMixInAnnotations(TransactionOperation.class, TransactionOperationMixIn.class);
    context.setMixInAnnotations(TransactionStatusValue.class, TransactionStatusValueMixIn.class);
    context.setMixInAnnotations(C2PLang.class, C2PLangMixIn.class);
  }

  @Override
  public Version version() {
    return new Version(1, 0, 0, getModuleName(), "com.payxpert", "connect2pay-client");
  }
}