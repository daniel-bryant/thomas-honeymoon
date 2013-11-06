package com.honeymoon.core.services;

import com.honeymoon.events.menu.*;
import com.honeymoon.persistence.services.MenuPersistenceService;

public class MenuEventHandler implements MenuService {

  private MenuPersistenceService menuPersistenceService;

  public MenuEventHandler(MenuPersistenceService menuPersistenceService) {
    this.menuPersistenceService = menuPersistenceService;
  }

  @Override
  public AllMenuItemsEvent requestAllMenuItems(RequestAllMenuItemsEvent requestAllMenuItemsEvent) {
    return menuPersistenceService.requestAllMenuItems(requestAllMenuItemsEvent);
  }

  @Override
  public MenuItemDetailsEvent requestMenuItemDetails(RequestMenuItemDetailsEvent requestMenuItemDetailsEvent) {
    return menuPersistenceService.requestMenuItemDetails(requestMenuItemDetailsEvent);
  }

  @Override
  public MenuItemCreatedEvent createMenuItem(CreateMenuItemEvent createMenuItemEvent) {
    return menuPersistenceService.createMenuItem(createMenuItemEvent);
  }
  
  @Override
  public MenuItemDeletedEvent deleteMenuItem(DeleteMenuItemEvent deleteMenuItemEvent) {
	  return menuPersistenceService.deleteMenuItem(deleteMenuItemEvent);
  }
}
