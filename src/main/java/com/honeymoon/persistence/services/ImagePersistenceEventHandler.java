package com.honeymoon.persistence.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

import com.honeymoon.events.images.*;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

public class ImagePersistenceEventHandler implements ImagePersistenceService {

	@Autowired
	GridFsOperations grid;
	
	public ImagePersistenceEventHandler() {	}
	
	@Override
	public ImageDetailsEvent requestImageDetails(RequestImageDetailsEvent requestImageDetailsEvent) {
		GridFSDBFile dbFile = grid.findOne(new Query().addCriteria(Criteria.where("_id").is(requestImageDetailsEvent.getId())));
		
		if (dbFile == null) {
			return ImageDetailsEvent.notFound(requestImageDetailsEvent.getId());
		}
		
		return new ImageDetailsEvent(
				requestImageDetailsEvent.getId(),
				gridFSDBFiletoImageDetails(requestImageDetailsEvent.getId(), dbFile));
	}

	@Override
	public ImageCreatedEvent createImage(CreateImageEvent createImageEvent) {
		
		InputStream is = new ByteArrayInputStream(createImageEvent.getDetails().getBytes());
		
		GridFSFile gridFSFile = grid.store(is, "");
		
		return new ImageCreatedEvent(
				gridFSFile.getId().toString(),
				createImageEvent.getDetails());
	}

	@Override
	public ImageDeletedEvent deleteImage(DeleteImageEvent deleteImageEvent) {
		GridFSDBFile dbFile = grid.findOne(new Query().addCriteria(Criteria.where("_id").is(deleteImageEvent.getId())));
		
		if (dbFile == null) {
			return ImageDeletedEvent.notFound(deleteImageEvent.getId());
		}
		
		grid.delete(new Query(Criteria.where("_id").is(deleteImageEvent.getId())));
		
		dbFile = grid.findOne(new Query().addCriteria(Criteria.where("_id").is(deleteImageEvent.getId())));

		if (dbFile != null) {
			return ImageDeletedEvent.deletionForbidden(
					deleteImageEvent.getId(),
					gridFSDBFiletoImageDetails(deleteImageEvent.getId(), dbFile));
		}
		
		return new ImageDeletedEvent(
				deleteImageEvent.getId(),
				gridFSDBFiletoImageDetails(deleteImageEvent.getId(), dbFile));
	}
	
	private ImageDetails gridFSDBFiletoImageDetails(String id, GridFSDBFile gridFSDBFile) {
		byte[] bytes;
		
		try {
			bytes = IOUtils.toByteArray(gridFSDBFile.getInputStream());
			return new ImageDetails(id, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return new ImageDetails(id, new byte[0]);
		}
	}

}
