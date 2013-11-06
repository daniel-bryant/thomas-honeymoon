package com.honeymoon.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.honeymoon.core.services.ImageService;
import com.honeymoon.events.images.ImageDetailsEvent;
import com.honeymoon.events.images.RequestImageDetailsEvent;

@Controller
public class ImageController {

	private static final Logger LOG = LoggerFactory.getLogger(ImageController.class);
	
	@Autowired
	private ImageService imageService;

	@RequestMapping(value="/image/{imageId}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] returnImageBytes(@PathVariable String imageId) {
		ImageDetailsEvent imageDetailsEvent = imageService.requestImageDetails(new RequestImageDetailsEvent(imageId));
		
		if (imageDetailsEvent.isEntityFound() == false) {
			LOG.debug("Image {} not found", imageDetailsEvent.getId());
			//TODO: replace with a 'not found' image
			
			return new byte[0];
			
		} else {
			LOG.debug("Image {} found", imageDetailsEvent.getId());
			
			return imageDetailsEvent.getDetails().getBytes();
		}
	}
}
