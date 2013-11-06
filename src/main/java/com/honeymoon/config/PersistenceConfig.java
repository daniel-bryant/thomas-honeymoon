package com.honeymoon.config;

import com.honeymoon.persistence.repository.*;
import com.honeymoon.persistence.services.AlbumPersistenceEventHandler;
import com.honeymoon.persistence.services.AlbumPersistenceService;
import com.honeymoon.persistence.services.ImagePersistenceEventHandler;
import com.honeymoon.persistence.services.ImagePersistenceService;
import com.honeymoon.persistence.services.MenuPersistenceEventHandler;
import com.honeymoon.persistence.services.MenuPersistenceService;
import com.honeymoon.persistence.services.OrderPersistenceEventHandler;
import com.honeymoon.persistence.services.OrderPersistenceService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {

  /*@Bean
  public OrdersRepository orderRepository() {
    return new OrdersMemoryRepository(new HashMap<UUID, Order>());
  }
  @Bean
  public OrderStatusRepository orderStatusRepository() {
    return new OrderStatusMemoryRepository();
  }
  @Bean
  public OrderPersistenceService ordersPersistenceService() {
    return new OrderPersistenceEventHandler(orderRepository(), orderStatusRepository());
  }

	@Bean
	public MenuItemRepository menuItemRepository() {
		return new MenuItemMemoryRepository(defaultMenu());
	}*/
	
	@Bean
	public OrderPersistenceService orderPersistenceService(OrdersRepository ordersRepository) {
		return new OrderPersistenceEventHandler(ordersRepository);
	}

	@Bean
	public MenuPersistenceService menuPersistenceService(MenuItemRepository menuItemRepository) {
		return new MenuPersistenceEventHandler(menuItemRepository);
	}
	
	@Bean
	public AlbumPersistenceService albumPersistenceService(AlbumRepository albumRepository) {
		return new AlbumPersistenceEventHandler(albumRepository);
	}
	
	@Bean
	public ImagePersistenceService imagePersistenceService() {
		return new ImagePersistenceEventHandler();
	}
	
	/*private Map<String, MenuItem> defaultMenu() {
		Map<String, MenuItem> items = new HashMap<String, MenuItem>();
		items.put("YM1", menuItem("YM1", new BigDecimal("1.99"), "Noodles on the beach.", "Yummy Noodles"));
		items.put("YM2", menuItem("YM2", new BigDecimal("2.99"), "Noodles on the grass.", "Special Yummy Noodles"));
		items.put("YM3", menuItem("YM3", new BigDecimal("3.99"), "Fun thing is fun.", "Low cal Yummy Noodles"));
		return items;
	}

	private MenuItem menuItem(String id, BigDecimal cost, String description, String name) {
		MenuItem item = new MenuItem();
		item.setId(id);
		item.setCost(cost.intValue());
		item.setDescription(description);
		item.setName(name);
		return item;
	}*/

}
