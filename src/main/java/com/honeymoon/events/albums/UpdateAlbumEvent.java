package com.honeymoon.events.albums;

import com.honeymoon.events.UpdateEvent;

public class UpdateAlbumEvent extends UpdateEvent {

	private String id;
	private AlbumDetails details;
	
	public UpdateAlbumEvent(String id, AlbumDetails details) {
	    this.id = id;
	    this.details = details;
	  }

	  public String getId() {
	    return id;
	  }

	  public AlbumDetails getDetails() {
	    return details;
	  }
}
