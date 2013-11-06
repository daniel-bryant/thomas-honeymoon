package com.honeymoon.config;

import com.honeymoon.core.services.AlbumEventHandler;
import com.honeymoon.core.services.AlbumService;
import com.honeymoon.core.services.ImageEventHandler;
import com.honeymoon.core.services.ImageService;
import com.honeymoon.core.services.MenuEventHandler;
import com.honeymoon.core.services.MenuService;
import com.honeymoon.core.services.OrderEventHandler;
import com.honeymoon.core.services.OrderService;
import com.honeymoon.persistence.services.AlbumPersistenceService;
import com.honeymoon.persistence.services.ImagePersistenceService;
import com.honeymoon.persistence.services.MenuPersistenceService;
import com.honeymoon.persistence.services.OrderPersistenceService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {
	@Bean
	public MenuService menuService(MenuPersistenceService menuPersistenceService) {
		return new MenuEventHandler(menuPersistenceService);
	}
  @Bean
  public OrderService orderService(OrderPersistenceService orderPersistenceService) {
    return new OrderEventHandler(orderPersistenceService);
  }
  
  @Bean
  public AlbumService albumService(AlbumPersistenceService albumPersistenceService) {
	  return new AlbumEventHandler(albumPersistenceService);
  }
  
  @Bean
  public ImageService imageService(ImagePersistenceService imagePersistenceService) {
	  return new ImageEventHandler(imagePersistenceService);
  }
}
