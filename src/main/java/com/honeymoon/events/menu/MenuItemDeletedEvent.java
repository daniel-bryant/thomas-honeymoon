package com.honeymoon.events.menu;

import com.honeymoon.events.DeletedEvent;

public class MenuItemDeletedEvent extends DeletedEvent {
	
	private String key;
	private MenuItemDetails details;
	private boolean deletionCompleted;
	
	private MenuItemDeletedEvent(String key) {
		this.key = key;
	}
	
	public MenuItemDeletedEvent(String key, MenuItemDetails details) {
		this.key = key;
		this.details = details;
		this.deletionCompleted = true;
	}
	
	public String getKey() {
		return key;
	}
	
	public MenuItemDetails getDetails() {
		return details;
	}
	
	public boolean isDeletionCompleted() {
		return deletionCompleted;
	}
	
	public static MenuItemDeletedEvent deletionForbidden(String key, MenuItemDetails details) {
		MenuItemDeletedEvent ev = new MenuItemDeletedEvent(key, details);
		ev.entityFound = true;
		ev.deletionCompleted = false;
		return ev;
	}
	
	public static MenuItemDeletedEvent notFound(String key) {
		MenuItemDeletedEvent ev = new MenuItemDeletedEvent(key);
		ev.entityFound = false;
		return ev;
	}
}
