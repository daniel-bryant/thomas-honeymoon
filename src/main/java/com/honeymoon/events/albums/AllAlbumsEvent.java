package com.honeymoon.events.albums;

import java.util.List;

import com.honeymoon.events.ReadEvent;

public class AllAlbumsEvent extends ReadEvent {

	private List<AlbumDetails> albumDetails;
	
	public AllAlbumsEvent(List<AlbumDetails> albumDetails) {
		this.albumDetails = albumDetails;
	}
	
	public List<AlbumDetails> getAlbumDetails() {
	    return albumDetails;
	  }
}
