package com.honeymoon.events.images;

import com.honeymoon.events.DeletedEvent;

public class ImageDeletedEvent extends DeletedEvent {

	private String id;
	private ImageDetails details;
	private boolean deletionCompleted;
	
	private ImageDeletedEvent(String id) {
		this.id = id;
	}
	
	public ImageDeletedEvent(String id, ImageDetails details) {
		this.id = id;
		this.details = details;
		this.deletionCompleted = true;
	}
	
	public String getId() {
		return id;
	}
	
	public ImageDetails getDetails() {
		return details;
	}
	
	public boolean isDeletionCompleted() {
		return deletionCompleted;
	}
	
	public static ImageDeletedEvent deletionForbidden(String id, ImageDetails details) {
		ImageDeletedEvent ev = new ImageDeletedEvent(id, details);
		ev.entityFound = true;
		ev.deletionCompleted = false;
		return ev;
	}
	
	public static ImageDeletedEvent notFound(String id) {
		ImageDeletedEvent ev = new ImageDeletedEvent(id);
		ev.entityFound = false;
		return ev;
	}
}
