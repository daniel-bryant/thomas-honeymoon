package com.honeymoon.events.orders;

import com.honeymoon.events.RequestReadEvent;

public class RequestOrderStatusEvent extends RequestReadEvent {
  private String key;

  public RequestOrderStatusEvent(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
