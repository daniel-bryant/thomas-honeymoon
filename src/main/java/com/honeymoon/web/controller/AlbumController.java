package com.honeymoon.web.controller;

import javax.servlet.MultipartConfigElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.honeymoon.core.services.AlbumService;
import com.honeymoon.events.albums.AlbumDetails;
import com.honeymoon.events.albums.AlbumDetailsEvent;
import com.honeymoon.events.albums.AlbumUpdatedEvent;
import com.honeymoon.events.albums.RequestAlbumDetailsEvent;
import com.honeymoon.events.albums.UpdateAlbumEvent;
import com.honeymoon.web.domain.Album;

@Controller
public class AlbumController {

	private static final Logger LOG = LoggerFactory.getLogger(AlbumController.class);
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private MultipartConfigElement multipartConfigElement;
	
	@RequestMapping(value="/album/{albumId}", method = RequestMethod.GET)
	public String album(@ModelAttribute("album") Album album) {
		LOG.debug("Get album number {}", album.getId());
		return "/album";
	}
	
	@RequestMapping(value = "/album/{albumId}/{photoId}/{thumbId}", method = RequestMethod.POST)
	@ResponseBody
    public String saveImages(@PathVariable("albumId") String albumId,
    		@PathVariable("photoId") String photoId,
    		@PathVariable("thumbId") String thumbId) {
		
		AlbumDetailsEvent albumDetailsEvent = albumService.requestAlbumDetails(new RequestAlbumDetailsEvent(albumId));
		if(!albumDetailsEvent.isEntityFound()) {
			return "fail";
		}
		
		AlbumDetails albumDetails = albumDetailsEvent.getAlbumDetails();
		
		albumDetails.addPhoto(photoId, thumbId);
		
		AlbumUpdatedEvent albumUpdatedEvent = albumService.updateAlbum(new UpdateAlbumEvent(albumId, albumDetails));
		if (!albumUpdatedEvent.isEntityFound()) {
			return "fail";
		}
		
        return "success";
    }
	
	@ModelAttribute("album")
	private Album getAlbum(@PathVariable("albumId") String albumId) {
		AlbumDetailsEvent albumDetailsEvent = albumService.requestAlbumDetails(new RequestAlbumDetailsEvent(albumId));
		Album album = new Album();
		album.setid(albumId);
		album.setTitle(albumDetailsEvent.getAlbumDetails().getTitle());
		album.setDescription(albumDetailsEvent.getAlbumDetails().getDescription());
		album.setPhotos(albumDetailsEvent.getAlbumDetails().getPhotos());
		return album;
	}
}
