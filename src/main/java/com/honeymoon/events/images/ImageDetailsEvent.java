package com.honeymoon.events.images;

import com.honeymoon.events.ReadEvent;

public class ImageDetailsEvent extends ReadEvent {
	private String id;
	private ImageDetails details;

	private ImageDetailsEvent(String id) {
		this.id = id;
	}
	
	public ImageDetailsEvent(String id, ImageDetails details) {
		this.id = id;
		this.details = details;
	}
	
	public String getId() {
		return id;
	}
	
	public ImageDetails getDetails() {
		return details;
	}
	
	public static ImageDetailsEvent notFound (String id) {
		ImageDetailsEvent ev = new ImageDetailsEvent(id);
		ev.entityFound = false;
		return ev;
	}
}
