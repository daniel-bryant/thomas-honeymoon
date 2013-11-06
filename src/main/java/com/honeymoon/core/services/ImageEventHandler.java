package com.honeymoon.core.services;

import com.honeymoon.events.images.*;
import com.honeymoon.persistence.services.ImagePersistenceService;

public class ImageEventHandler implements ImageService {

	private final ImagePersistenceService imagePersistenceService;

	public ImageEventHandler(ImagePersistenceService imagePersistenceService) {
		this.imagePersistenceService = imagePersistenceService;
	}
	
	@Override
	public ImageDetailsEvent requestImageDetails(RequestImageDetailsEvent requestImageDetailsEvent) {
		return imagePersistenceService.requestImageDetails(requestImageDetailsEvent);
	}

	@Override
	public ImageCreatedEvent createImage(CreateImageEvent createImageevent) {
		return imagePersistenceService.createImage(createImageevent);
	}

	@Override
	public ImageDeletedEvent deleteImage(DeleteImageEvent deleteImageEvent) {
		return imagePersistenceService.deleteImage(deleteImageEvent);
	}
}
