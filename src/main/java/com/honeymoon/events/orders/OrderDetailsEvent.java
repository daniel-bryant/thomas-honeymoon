package com.honeymoon.events.orders;

import com.honeymoon.events.ReadEvent;

public class OrderDetailsEvent extends ReadEvent {
  private String key;
  private OrderDetails orderDetails;

  private OrderDetailsEvent(String key) {
    this.key = key;
  }

  public OrderDetailsEvent(String key, OrderDetails orderDetails) {
    this.key = key;
    this.orderDetails = orderDetails;
  }

  public String getKey() {
    return key;
  }

  public OrderDetails getOrderDetails() {
    return orderDetails;
  }

  public static OrderDetailsEvent notFound(String key) {
    OrderDetailsEvent ev = new OrderDetailsEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
