package com.honeymoon.events.albums;

import com.honeymoon.events.ReadEvent;

public class AlbumDetailsEvent extends ReadEvent {
	private String key;
	private AlbumDetails albumDetails;
	
	private AlbumDetailsEvent() {}
	
	public AlbumDetailsEvent(String key, AlbumDetails albumDetails) {
		this.key = key;
		this.albumDetails = albumDetails;
	}

	public AlbumDetails getAlbumDetails() {
		return albumDetails;
	}
	
	public String getKey() {
		return key;
	}
	
	public static AlbumDetailsEvent notFound(String key) {
		AlbumDetailsEvent ev = new AlbumDetailsEvent();
		ev.key = key;
	    ev.entityFound=false;
	    return ev;
	}
}
