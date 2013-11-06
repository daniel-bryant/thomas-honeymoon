package com.honeymoon.core.services;

import com.honeymoon.events.images.*;

public interface ImageService {

	public ImageDetailsEvent requestImageDetails(RequestImageDetailsEvent requestImageDetailsEvent);
	
	public ImageCreatedEvent createImage(CreateImageEvent event);
	
	public ImageDeletedEvent deleteImage(DeleteImageEvent deleteImageEvent);
}
