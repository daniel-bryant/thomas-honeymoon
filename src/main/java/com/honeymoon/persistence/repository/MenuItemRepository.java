package com.honeymoon.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.honeymoon.persistence.domain.MenuItem;

public interface MenuItemRepository extends MongoRepository<MenuItem, String> {

  public MenuItem findById(String id);

}
