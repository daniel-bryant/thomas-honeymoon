package com.honeymoon.persistence.repository;

import com.honeymoon.persistence.domain.OrderStatus;

import java.util.*;

public class OrderStatusMemoryRepository implements OrderStatusRepository {

  private Map<String, OrderStatus> orderStatuses = new HashMap<String, OrderStatus>();

  @Override
  public OrderStatus save(OrderStatus order) {
    orderStatuses.put(order.getId(), order);
    return order;
  }

  @Override
  public void delete(String key) {
    orderStatuses.remove(key);
  }

  @Override
  public OrderStatus findLatestById(String key) {
    for(OrderStatus item: orderStatuses.values()) {
      if (item.getOrderId().equals(key)) {
        return item;
      }
    }
    return null;
  }

  @Override
  public List<OrderStatus> findAll() {
    return new ArrayList<OrderStatus>(orderStatuses.values());
  }
}
