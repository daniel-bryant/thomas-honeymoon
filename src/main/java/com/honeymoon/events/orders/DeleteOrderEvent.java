package com.honeymoon.events.orders;

import com.honeymoon.events.DeleteEvent;

public class DeleteOrderEvent extends DeleteEvent {

  private final String key;

  public DeleteOrderEvent(final String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
