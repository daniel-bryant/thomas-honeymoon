package com.honeymoon.persistence.integration;

import com.honeymoon.config.JPAConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.honeymoon.persistence.domain.fixture.JPAAssertions.assertTableExists;
import static com.honeymoon.persistence.domain.fixture.JPAAssertions.assertTableHasColumn;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OrderMappingIntegrationTests {

  @Autowired
  EntityManager manager;

  @Test
  public void thatItemCustomMappingWorks() throws Exception {
    assertTableExists(manager, "GIFT_ORDERS");
    assertTableExists(manager, "GIFT_ORDER_ITEMS");

    assertTableHasColumn(manager, "GIFT_ORDERS", "ORDER_ID");
    assertTableHasColumn(manager, "GIFT_ORDERS", "SUBMISSION_DATETIME");
  }

}
