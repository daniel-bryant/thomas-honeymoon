package com.honeymoon.persistence.services;

import com.honeymoon.events.images.*;

public interface ImagePersistenceService {

	ImageDetailsEvent requestImageDetails(RequestImageDetailsEvent requestImageDetailsEvent);
	ImageCreatedEvent createImage(CreateImageEvent createImageEvent);
	ImageDeletedEvent deleteImage(DeleteImageEvent deleteImageEvent);
}
