package com.honeymoon.events.albums;

import com.honeymoon.events.UpdatedEvent;

public class AlbumUpdatedEvent extends UpdatedEvent {

	private String id;
	private AlbumDetails details;
	
	public AlbumUpdatedEvent(String id, AlbumDetails details) {
	    this.id = id;
	    this.details = details;
	  }

	  public AlbumUpdatedEvent(String id) {
	    this.id = id;
	  }

	  public String getId() {
	    return id;
	  }

	  public AlbumDetails getDetails() {
	    return details;
	  }

	  public static AlbumUpdatedEvent notFound(String id) {
		AlbumUpdatedEvent ev = new AlbumUpdatedEvent(id);
	    ev.entityFound=false;
	    return ev;
	  }
}
