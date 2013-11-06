package com.honeymoon.config;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.honeymoon.config.CoreConfig;
import com.honeymoon.config.PersistenceConfig;
import com.honeymoon.core.services.MenuService;
import com.honeymoon.events.menu.AllMenuItemsEvent;
import com.honeymoon.events.menu.RequestAllMenuItemsEvent;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, CoreConfig.class})
public class CoreDomainIntegrationTest {
	
	@Autowired
	MenuService menuService;
			
	@Test
	public void thatAllMenuItemsReturned() {
		
	AllMenuItemsEvent allMenuItems = menuService.requestAllMenuItems(new RequestAllMenuItemsEvent());
	
	assertEquals(3, allMenuItems.getMenuItemDetails().size());
			
	}	

}
