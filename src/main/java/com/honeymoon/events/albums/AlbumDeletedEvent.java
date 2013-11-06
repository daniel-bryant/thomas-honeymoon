package com.honeymoon.events.albums;

import com.honeymoon.events.DeletedEvent;

public class AlbumDeletedEvent extends DeletedEvent {
	
	private String key;
	private AlbumDetails details;
	private boolean deletionCompleted;
	
	private AlbumDeletedEvent(String key) {
		this.key = key;
	}
	
	public AlbumDeletedEvent(String key, AlbumDetails details) {
		this.key = key;
		this.details = details;
		this.deletionCompleted = true;
	}
	
	public String getKey() {
		return key;
	}
	
	public AlbumDetails getDetails() {
		return details;
	}
	
	public boolean isDeletionCompleted() {
		return deletionCompleted;
	}
	
	public static AlbumDeletedEvent deletionForbidden(String key, AlbumDetails details) {
		AlbumDeletedEvent ev = new AlbumDeletedEvent(key, details);
		ev.entityFound = true;
		ev.deletionCompleted = false;
		return ev;
	}
	
	public static AlbumDeletedEvent notFound(String key) {
		AlbumDeletedEvent ev = new AlbumDeletedEvent(key);
		ev.entityFound = false;
		return ev;
	}
}
