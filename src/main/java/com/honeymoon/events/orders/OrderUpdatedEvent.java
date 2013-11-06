package com.honeymoon.events.orders;

import com.honeymoon.events.UpdatedEvent;

public class OrderUpdatedEvent extends UpdatedEvent {

  private String key;
  private OrderDetails orderDetails;

  public OrderUpdatedEvent(String key, OrderDetails orderDetails) {
    this.key = key;
    this.orderDetails = orderDetails;
  }

  public OrderUpdatedEvent(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  public OrderDetails getOrderDetails() {
    return orderDetails;
  }

  public static OrderUpdatedEvent notFound(String key) {
    OrderUpdatedEvent ev = new OrderUpdatedEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
