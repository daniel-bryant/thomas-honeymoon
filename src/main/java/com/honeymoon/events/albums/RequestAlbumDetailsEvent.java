package com.honeymoon.events.albums;

import com.honeymoon.events.RequestReadEvent;

public class RequestAlbumDetailsEvent extends RequestReadEvent {
	private String id;
	
	public RequestAlbumDetailsEvent(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
