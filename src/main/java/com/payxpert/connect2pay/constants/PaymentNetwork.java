package com.payxpert.connect2pay.constants;

/**
 * Available payment networks to filter transactions for export.
 */
public enum PaymentNetwork {

  SOFORT("Sofort", PaymentMethod.BANK_TRANSFER), //

  PRZELEWY24("Przelewy24", PaymentMethod.BANK_TRANSFER), //

  IDEAL("iDeal", PaymentMethod.BANK_TRANSFER), //

  GIROPAY("Giropay", PaymentMethod.BANK_TRANSFER), //

  EPS("EPS", PaymentMethod.BANK_TRANSFER), //

  DRAGONPAY("DragonPay", PaymentMethod.BANK_TRANSFER), //

  POLI("POLi", PaymentMethod.BANK_TRANSFER), //

  TRUSTLY("Trustly", PaymentMethod.BANK_TRANSFER);

  private final String value;
  private final PaymentMethod supportedPaymentMethod;

  PaymentNetwork(String value, PaymentMethod belongsToMethod) {
    this.value = value;
    this.supportedPaymentMethod = belongsToMethod;
  }

  public String valueToString() {
    return this.value;
  }

  public boolean supports(PaymentMethod paymentMethod) {
    return paymentMethod == this.supportedPaymentMethod;
  }

  public static PaymentNetwork fromValue(String value) {
    for (PaymentNetwork network : PaymentNetwork.values()) {
      if (network.value.equalsIgnoreCase(value)) {
        return network;
      }
    }
    return null;
  }

}
