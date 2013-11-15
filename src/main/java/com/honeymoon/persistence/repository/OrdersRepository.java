package com.honeymoon.persistence.repository;

import com.honeymoon.persistence.domain.Order;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdersRepository extends MongoRepository<Order, String> {

  public Order findById(String id);
  
}
