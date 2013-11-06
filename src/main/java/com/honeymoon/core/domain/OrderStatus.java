package com.honeymoon.core.domain;

import com.honeymoon.events.orders.OrderStatusDetails;

import java.util.Date;

public class OrderStatus {

  private String orderId;
  private String id;
  private Date statusDate;
  private String status;

  public OrderStatus(String orderId, String id, final Date date, final String status) {
    this.orderId = orderId;
    this.id = id;
    this.status = status;
    this.statusDate = date;
  }

  public Date getStatusDate() {
    return statusDate;
  }

  public String getStatus() {
    return status;
  }

  public String getOrderId() {
    return orderId;
  }

  public String getId() {
    return id;
  }

  public OrderStatusDetails toStatusDetails() {
    return new OrderStatusDetails(orderId, id, statusDate, status);
  }

  public static OrderStatus fromStatusDetails(OrderStatusDetails orderStatusDetails) {
    return new OrderStatus(
        orderStatusDetails.getOrderId(), orderStatusDetails.getId(),
        orderStatusDetails.getStatusDate(), orderStatusDetails.getStatus());
  }
}
