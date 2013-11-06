package com.honeymoon.persistence.repository;

import com.honeymoon.persistence.domain.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Order, String> {

  Order findById(String id);
  
}
