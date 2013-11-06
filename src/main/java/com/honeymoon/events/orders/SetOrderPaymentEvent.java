package com.honeymoon.events.orders;

import com.honeymoon.events.UpdateEvent;

public class SetOrderPaymentEvent extends UpdateEvent {

  private String key;
  private PaymentDetails paymentDetails;

  public SetOrderPaymentEvent(String key, PaymentDetails paymentDetails) {
    this.key = key;
    this.paymentDetails = paymentDetails;
  }

  public String getKey() {
    return key;
  }

  public PaymentDetails getPaymentDetails() {
    return paymentDetails;
  }
}
