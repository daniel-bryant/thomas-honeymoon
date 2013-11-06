package com.honeymoon.events.images;

import com.honeymoon.events.RequestReadEvent;

public class RequestImageDetailsEvent extends RequestReadEvent {
	private String id;

	public RequestImageDetailsEvent(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
