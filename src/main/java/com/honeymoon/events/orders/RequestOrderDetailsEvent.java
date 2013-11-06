package com.honeymoon.events.orders;

import com.honeymoon.events.RequestReadEvent;

public class RequestOrderDetailsEvent extends RequestReadEvent {
  private String key;

  public RequestOrderDetailsEvent(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
