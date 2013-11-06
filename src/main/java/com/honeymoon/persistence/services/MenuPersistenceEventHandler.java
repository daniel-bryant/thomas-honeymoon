package com.honeymoon.persistence.services;

import com.honeymoon.events.menu.*;
import com.honeymoon.persistence.domain.MenuItem;
import com.honeymoon.persistence.repository.MenuItemRepository;

import java.util.ArrayList;
import java.util.List;

public class MenuPersistenceEventHandler implements MenuPersistenceService {

  private MenuItemRepository menuItemRepository;

  public MenuPersistenceEventHandler(MenuItemRepository menuItemRepository) {
    this.menuItemRepository = menuItemRepository;
  }

  @Override
  public AllMenuItemsEvent requestAllMenuItems(RequestAllMenuItemsEvent requestAllMenuItemsEvent) {
    Iterable<MenuItem> menuItems = menuItemRepository.findAll();

    List<MenuItemDetails> details = new ArrayList<MenuItemDetails>();

    for(MenuItem item: menuItems) {
      details.add(item.toStatusDetails());
    }

    return new AllMenuItemsEvent(details);
  }

  @Override
  public MenuItemDetailsEvent requestMenuItemDetails(RequestMenuItemDetailsEvent requestMenuItemDetailsEvent) {
    MenuItem item = menuItemRepository.findById(requestMenuItemDetailsEvent.getId());

    if (item == null) {
      return MenuItemDetailsEvent.notFound(requestMenuItemDetailsEvent.getId());
    }

    return new MenuItemDetailsEvent(
        requestMenuItemDetailsEvent.getId(),
        item.toStatusDetails());
  }

  @Override
  public MenuItemCreatedEvent createMenuItem(CreateMenuItemEvent createMenuItemEvent) {
    MenuItem item = menuItemRepository.save(
        MenuItem.fromStatusDetails(createMenuItemEvent.getDetails()));

    return new MenuItemCreatedEvent(item.getId(), item.toStatusDetails());
  }
  
  @Override
  public MenuItemDeletedEvent deleteMenuItem(DeleteMenuItemEvent deleteMenuItemEvent) {
	  MenuItem menuItem = menuItemRepository.findById(deleteMenuItemEvent.getKey());
	  
	  if (menuItem == null) {
	      return MenuItemDeletedEvent.notFound(deleteMenuItemEvent.getKey());
	    }

	  menuItemRepository.delete(deleteMenuItemEvent.getKey());

	  return new MenuItemDeletedEvent(deleteMenuItemEvent.getKey(), menuItem.toStatusDetails());
  }
}
