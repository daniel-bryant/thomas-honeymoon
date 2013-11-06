package com.honeymoon.persistence.integration;

import static com.honeymoon.persistence.domain.fixture.JPAAssertions.assertTableExists;
import static com.honeymoon.persistence.domain.fixture.JPAAssertions.assertTableHasColumn;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.honeymoon.config.JPAConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class MenuItemMappingIntegrationTests {

  @Autowired
  EntityManager manager;

  @Test
  public void thatItemCustomMappingWorks() throws Exception {
    assertTableExists(manager, "MENU_ITEMS");

    assertTableHasColumn(manager, "MENU_ITEMS", "ITEM_ID");
    assertTableHasColumn(manager, "MENU_ITEMS", "NAME");
    assertTableHasColumn(manager, "MENU_ITEMS", "DESCRIPTION");
    assertTableHasColumn(manager, "MENU_ITEMS", "COST");
  }

}
