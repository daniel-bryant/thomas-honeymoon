package com.honeymoon.persistence.integration;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.honeymoon.config.JPAConfiguration;
import com.honeymoon.persistence.domain.MenuItem;
import com.honeymoon.persistence.repository.MenuItemRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class MenuItemRepositoryIntegrationTests {

  private static String NAME = "Beach Trip";
  private static String DESCRIPTION = "Description here.";
  private static int COST = 15;
	
  @Autowired
  MenuItemRepository menuItemRepository;

  @Test
  public void thatItemIsInsertedIntoRepoWorks() throws Exception {

    MenuItem menuItem = new MenuItem();
    menuItem.setName(NAME);
    menuItem.setDescription(DESCRIPTION);
    menuItem.setCost(COST);

    MenuItem savedItem = menuItemRepository.save(menuItem);

    MenuItem retrievedItem = menuItemRepository.findById(savedItem.getId());

    assertNotNull(retrievedItem);
    assertEquals(savedItem.getName(), retrievedItem.getName());
    //assertEquals(items.get("yummy1"), retrievedOrder.getOrderItems().get("yummy1"));
  }

}
