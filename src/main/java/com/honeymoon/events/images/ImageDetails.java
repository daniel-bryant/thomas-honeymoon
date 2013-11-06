package com.honeymoon.events.images;

public class ImageDetails {

	private String id;
	private byte[] bytes;
	
	public ImageDetails() {
		id = null;
	}
	
	public ImageDetails(String id) {
		this.id = id;
	}
	
	public ImageDetails(byte[] bytes) {
		this.bytes = bytes;
	}
	
	public ImageDetails(String id, byte[] bytes) {
		this.id = id;
		this.bytes = bytes;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public byte[] getBytes() {
		return bytes;
	}
	
	public void setImage(byte[] bytes) {
		this.bytes = bytes;
	}
}
