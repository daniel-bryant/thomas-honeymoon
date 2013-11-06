package com.honeymoon.events.albums;

import com.honeymoon.events.CreateEvent;

public class CreateAlbumEvent extends CreateEvent {
	
	private AlbumDetails details;

	public CreateAlbumEvent(AlbumDetails details) {
		this.details = details;
	}
	
	public AlbumDetails getDetails() {
	    return details;
	  }
}
