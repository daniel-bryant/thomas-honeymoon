package com.honeymoon.events.menu;

import com.honeymoon.events.CreatedEvent;

public class MenuItemCreatedEvent extends CreatedEvent {
	
	private final String newMenuItemId;
	private final MenuItemDetails details;
	
	public MenuItemCreatedEvent(final String newMenuItemId, final MenuItemDetails details) {
		this.newMenuItemId = newMenuItemId;
		this.details = details;
	}

	public MenuItemDetails getDetails() {
		return details;
	}
	
	public String getNewMenuItemId() {
		return newMenuItemId;
	}
}
