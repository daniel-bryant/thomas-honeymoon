package com.honeymoon.events.albums;

import com.honeymoon.events.DeleteEvent;

public class DeleteAlbumEvent extends DeleteEvent {

	private String key;
	
	public DeleteAlbumEvent(final String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
}
