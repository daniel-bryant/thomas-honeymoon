package com.honeymoon.events.images;

import com.honeymoon.events.CreatedEvent;

public class ImageCreatedEvent extends CreatedEvent {
	private final String newImageId;
	private final ImageDetails details;

	public ImageCreatedEvent(final String newImageId, final ImageDetails details) {
		this.newImageId = newImageId;
		this.details = details;
	}
	
	public ImageDetails getDetails() {
		return details;
	}
	
	public String getNewImageId() {
		return newImageId;
	}
}
