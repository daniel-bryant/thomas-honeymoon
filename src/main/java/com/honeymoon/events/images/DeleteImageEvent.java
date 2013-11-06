package com.honeymoon.events.images;

import com.honeymoon.events.DeleteEvent;

public class DeleteImageEvent extends DeleteEvent {

	private final String id;
	
	public DeleteImageEvent(final String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
