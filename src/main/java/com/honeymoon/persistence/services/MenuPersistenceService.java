package com.honeymoon.persistence.services;

import com.honeymoon.events.menu.*;

public interface MenuPersistenceService {

  AllMenuItemsEvent requestAllMenuItems(RequestAllMenuItemsEvent requestAllMenuItemsEvent);
  MenuItemDetailsEvent requestMenuItemDetails(RequestMenuItemDetailsEvent requestMenuItemDetailsEvent);
  MenuItemCreatedEvent createMenuItem(CreateMenuItemEvent createMenuItemEvent);
  MenuItemDeletedEvent deleteMenuItem(DeleteMenuItemEvent deleteMenuItemEvent);

}
