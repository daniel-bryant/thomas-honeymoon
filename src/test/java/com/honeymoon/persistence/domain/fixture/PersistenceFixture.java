package com.honeymoon.persistence.domain.fixture;

import com.honeymoon.persistence.domain.Album;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class PersistenceFixture {
	public static final String TITLE = "Wedding Pics";
	public static final String DESC = "test album";
	public static final String FILE_NAME = "wedding01";
	public static final String CONTENT_TYPE = "JPG";
	
	public static Album weddingAlbum() {
		Album album = new Album();
		album.setTitle(TITLE);
		album.setDescription(DESC);
		return album;
	}
	
	public static DBObject simpleMetaData() {
		DBObject metaData = new BasicDBObject();
		metaData.put("filename", FILE_NAME);
		metaData.put("content-type", CONTENT_TYPE);
		return metaData;
	}
}
