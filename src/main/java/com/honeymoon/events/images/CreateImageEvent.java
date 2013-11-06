package com.honeymoon.events.images;

import com.honeymoon.events.CreateEvent;

public class CreateImageEvent extends CreateEvent{
	  private ImageDetails details;

	  public CreateImageEvent(ImageDetails details) {
	    this.details = details;
	  }

	  public ImageDetails getDetails() {
	    return details;
	  }
}
