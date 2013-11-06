package com.honeymoon.events.orders;

import java.util.Date;

public class OrderStatusDetails {

  private String orderId;
  private String id;
  private Date statusDate;
  private String status;

  public OrderStatusDetails(String orderId,
      String id, Date statusDate, String status) {
    this.id = id;
    this.orderId = orderId;
    this.status = status;
    this.statusDate = statusDate;
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
}
