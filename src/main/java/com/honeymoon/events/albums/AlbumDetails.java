package com.honeymoon.events.albums;

import java.util.ArrayList;
import java.util.List;

public class AlbumDetails {

	private String id;
	private String title;
	
	private String description;
	
	private List<Photo> photos = new ArrayList<Photo>();
	
	public AlbumDetails() {
	    id = null;
	}
	
	public AlbumDetails(String id, String title, String description, List<Photo> photos) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.photos = photos;
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
	
	/* Tries to insert a photo with ID 'id' into the linked list */
	public void addPhoto(String photoId, String thumbId) {
		photos.add(new Photo(photoId, thumbId));
	}
	
	/* Removes the photo if its id is contained in the list */
	public void removePhoto(String photoId) {
		for (Photo pho : photos) {
			if (pho.getPhotoId() == photoId) {
				photos.remove(pho);
				break;
			}
		}
	}
}
