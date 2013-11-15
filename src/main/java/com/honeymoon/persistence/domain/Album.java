package com.honeymoon.persistence.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.honeymoon.events.albums.AlbumDetails;
import com.honeymoon.events.albums.Photo;

@Document(collection = "albums")
public class Album {
	
	@Id
	private String id;
	
	@Field("albumTitle")
	//@Indexed
	private String title;
	
	private String description;
	
	private List<Photo> photos = new ArrayList<Photo>();
	
	public Album() {}
	
	public Album(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Photo> getPhotos() {
		return photos;
	}
	
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	public AlbumDetails toAlbumDetails() {
		return new AlbumDetails(id, title, description, photos);
	}

	public static Album fromAlbumDetails(AlbumDetails albumDetails) {
		Album album = new Album();
		
		album.id = albumDetails.getId();
		album.title = albumDetails.getTitle();
		album.description = albumDetails.getDescription();
		album.photos = albumDetails.getPhotos();
		
		return album;
	}
	
}
