package com.honeymoon.core.services;

import com.honeymoon.events.menu.*;

public interface MenuService {
  AllMenuItemsEvent requestAllMenuItems(RequestAllMenuItemsEvent requestAllMenuItemsEvent);
  MenuItemDetailsEvent requestMenuItemDetails(RequestMenuItemDetailsEvent requestMenuItemDetailsEvent);
  MenuItemCreatedEvent createMenuItem(CreateMenuItemEvent createMenuItemEvent);
  MenuItemDeletedEvent deleteMenuItem(DeleteMenuItemEvent deleteMenuItemEvent);
}
