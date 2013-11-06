package com.honeymoon.web.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;

import com.honeymoon.events.albums.AlbumDetails;
import com.honeymoon.events.albums.Photo;

public class Album {
	
	private String id;
	
	@NotNull
	@NotEmpty
	private String title;
	
	@NotNull
	@NotEmpty
	private String description;
	
	private List<Photo> photos = new ArrayList<Photo>();
	
	public String getId() {
		return id;
	}

	public void setid(String id) {
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
	
	public static AlbumDetails toAlbumDetails(Album album) {
		  AlbumDetails albumDetails = new AlbumDetails();
		  BeanUtils.copyProperties(album, albumDetails);
		  return albumDetails;
	  }
	
	public static Album fromAlbumDetails(AlbumDetails albumDetails) {
		Album album = new Album();
		BeanUtils.copyProperties(albumDetails, album);
		return album;
	  }
}
