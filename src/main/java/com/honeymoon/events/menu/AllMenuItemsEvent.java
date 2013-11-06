package com.honeymoon.events.menu;


import com.honeymoon.events.ReadEvent;

import java.util.List;

public class AllMenuItemsEvent extends ReadEvent {
  private List<MenuItemDetails> menuItemDetails;

  public AllMenuItemsEvent(List<MenuItemDetails> menuItemDetails) {
    this.menuItemDetails = menuItemDetails;
  }

  public List<MenuItemDetails> getMenuItemDetails() {
    return menuItemDetails;
  }
}
