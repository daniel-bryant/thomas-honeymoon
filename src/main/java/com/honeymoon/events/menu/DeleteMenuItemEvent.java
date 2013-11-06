package com.honeymoon.events.menu;

import com.honeymoon.events.DeleteEvent;

public class DeleteMenuItemEvent extends DeleteEvent {

	private String key;
	
	public DeleteMenuItemEvent(final String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
}
