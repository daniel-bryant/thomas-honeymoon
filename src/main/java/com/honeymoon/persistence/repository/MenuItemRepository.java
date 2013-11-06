package com.honeymoon.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.honeymoon.persistence.domain.MenuItem;

public interface MenuItemRepository extends CrudRepository<MenuItem, String> {

  MenuItem findById(String key);

}
