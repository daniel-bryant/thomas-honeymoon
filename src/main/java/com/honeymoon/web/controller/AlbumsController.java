package com.honeymoon.web.controller;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.honeymoon.core.services.AlbumService;
import com.honeymoon.events.albums.AlbumCreatedEvent;
import com.honeymoon.events.albums.AlbumDeletedEvent;
import com.honeymoon.events.albums.AlbumDetails;
import com.honeymoon.events.albums.AllAlbumsEvent;
import com.honeymoon.events.albums.CreateAlbumEvent;
import com.honeymoon.events.albums.DeleteAlbumEvent;
import com.honeymoon.events.albums.RequestAllAlbumsEvent;
import com.honeymoon.web.domain.Album;

@Controller
public class AlbumsController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AlbumsController.class);
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	GridFsOperations template;
	
    @RequestMapping(value = "/albums" , method = RequestMethod.GET)
    public String getCurrentAlbums(Model model) {
    	LOG.debug("List of Albums to album view.");
    	model.addAttribute("albums", getAlbums(albumService.requestAllAlbums(new RequestAllAlbumsEvent())));
        return "/albums";
    }
    
    @RequestMapping(value = "/albums" , method = RequestMethod.POST)
    public String createAlbum(Model model, @Valid @ModelAttribute("album") Album album, BindingResult result, RedirectAttributes redirectAttrs) {
    	if (result.hasErrors()) {
			// errors in the form
			// shows albums again
    		LOG.debug("List of Albums to album view.");
        	model.addAttribute("albums", getAlbums(albumService.requestAllAlbums(new RequestAllAlbumsEvent())));
			return "/albums";
		}
    	
    	LOG.debug("No errors, continue with processing new Album");
    	
    	AlbumDetails albumDetails = Album.toAlbumDetails(album);
    	
    	AlbumCreatedEvent event = albumService.createAlbum(new CreateAlbumEvent(albumDetails));
    	
    	String id = event.getNewAlbumId();
    	
    	redirectAttrs.addFlashAttribute("message", "New Album Created.");
    	
    	LOG.debug("Album " + id + " has been created.");
    	
    	return "redirect:/editMenu";
    }
    
    @RequestMapping(value="/removeAlbum" , method = RequestMethod.POST)
    public String deleteMenuItem(@RequestParam(value = "id", required = true) String id) {
		LOG.debug("Deleting Album " + id + ".");
		
		//TODO: first remove all images in the album!!
		
		AlbumDeletedEvent event = albumService.deleteAlbum(new DeleteAlbumEvent(id));
		
		if (!event.isEntityFound()) {
			LOG.debug("Album " + id + " was not found.");
		}
		
		if (!event.isDeletionCompleted()) {
			LOG.debug("Album " + id + " deletion was not completed.");
		}
		
		return "redirect:/albums";
	}
    
    private List<Album> getAlbums(AllAlbumsEvent requestAllAlbums) {
    	List<Album> albums = new LinkedList<Album>();
    	
    	for (AlbumDetails albumDetails : requestAllAlbums.getAlbumDetails()) {
    		albums.add(Album.fromAlbumDetails(albumDetails));
		}

		return albums;
    }
}
