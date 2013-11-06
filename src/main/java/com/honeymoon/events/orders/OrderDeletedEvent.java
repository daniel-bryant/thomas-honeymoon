package com.honeymoon.events.orders;

import com.honeymoon.events.DeletedEvent;

public class OrderDeletedEvent extends DeletedEvent {

  private String key;
  private OrderDetails details;
  private boolean deletionCompleted;

  private OrderDeletedEvent(String key) {
    this.key = key;
  }

  public OrderDeletedEvent(String key, OrderDetails details) {
    this.key = key;
    this.details = details;
    this.deletionCompleted = true;
  }

  public String getKey() {
    return key;
  }

  public OrderDetails getDetails() {
    return details;
  }

  public boolean isDeletionCompleted() {
    return deletionCompleted;
  }

  public static OrderDeletedEvent deletionForbidden(String key, OrderDetails details) {
    OrderDeletedEvent ev = new OrderDeletedEvent(key, details);
    ev.entityFound=true;
    ev.deletionCompleted=false;
    return ev;
  }

  public static OrderDeletedEvent notFound(String key) {
    OrderDeletedEvent ev = new OrderDeletedEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
