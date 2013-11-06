package com.honeymoon.events.orders;

import com.honeymoon.events.ReadEvent;

public class OrderStatusEvent extends ReadEvent {
  private String key;
  private OrderStatusDetails orderStatus;

  private OrderStatusEvent(String key) {
    this.key = key;
  }

  public OrderStatusEvent(String key, OrderStatusDetails orderStatus) {
    this.key = key;
    this.orderStatus = orderStatus;
  }

  public String getKey() {
    return key;
  }

  public OrderStatusDetails getOrderStatus() {
    return orderStatus;
  }

  public static OrderStatusEvent notFound(String key) {
    OrderStatusEvent ev = new OrderStatusEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
