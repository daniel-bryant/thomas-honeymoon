package com.honeymoon.events.albums;

public class Photo {

	private String photoId;
	private String thumbId;
	
	public Photo(String photoId, String thumbId) {
		this.photoId = photoId;
		this.thumbId = thumbId;
	}
	
	public String getPhotoId() {
		return photoId;
	}
	
	public String getThumbId() {
		return thumbId;
	}
}
