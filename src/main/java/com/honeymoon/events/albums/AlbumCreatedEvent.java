package com.honeymoon.events.albums;

import com.honeymoon.events.CreatedEvent;

public class AlbumCreatedEvent extends CreatedEvent {
	
	private final String newAlbumId;
	private final AlbumDetails details;
	
	public AlbumCreatedEvent(final String newAlbumId, final AlbumDetails details) {
		this.newAlbumId = newAlbumId;
		this.details = details;
	}
	
	public String getNewAlbumId() {
		return newAlbumId;
	}
	
	public AlbumDetails getDetails() {
		return details;
	}
}
