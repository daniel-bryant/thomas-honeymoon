package com.honeymoon.web.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.honeymoon.core.services.AlbumService;
import com.honeymoon.core.services.MenuService;
import com.honeymoon.events.albums.AlbumDetails;
import com.honeymoon.events.albums.AllAlbumsEvent;
import com.honeymoon.events.albums.RequestAllAlbumsEvent;
import com.honeymoon.events.menu.AllMenuItemsEvent;
import com.honeymoon.events.menu.CreateMenuItemEvent;
import com.honeymoon.events.menu.DeleteMenuItemEvent;
import com.honeymoon.events.menu.MenuItemCreatedEvent;
import com.honeymoon.events.menu.MenuItemDeletedEvent;
import com.honeymoon.events.menu.MenuItemDetails;
import com.honeymoon.events.menu.RequestAllMenuItemsEvent;
import com.honeymoon.web.domain.Album;
import com.honeymoon.web.domain.MenuItem;

@Controller
public class EditMenuController {

	private static final Logger LOG = LoggerFactory.getLogger(EditMenuController.class);

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping(value="/editMenu" , method = RequestMethod.GET)
	public String getCurrentMenu(Model model) {
		LOG.debug("MenuItemDetails to editMenu view");
		model.addAttribute("menuItems",getMenuItems(menuService.requestAllMenuItems(new RequestAllMenuItemsEvent())));
		
		LOG.debug("Albums to admin view");
		model.addAttribute("albums", getAlbums(albumService.requestAllAlbums(new RequestAllAlbumsEvent())));
		
		return "/editMenu";
	}
	
	@RequestMapping(value="/editMenu" , method = RequestMethod.POST)
    public String createMenuItem(Model model, @Valid @ModelAttribute("menuItem") MenuItem menuItem, BindingResult result, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			// errors in the form
			// shows editMenu again
			LOG.debug("MenuItemDetails to editMenu view");
			model.addAttribute("menuItems",getMenuItems(menuService.requestAllMenuItems(new RequestAllMenuItemsEvent())));
			return "/editMenu";
		}
		
		LOG.debug("No errors, continue with processing new Menu Item");
		
		MenuItemDetails  menuItemDetails = MenuItem.toMenuDetails(menuItem);
		
		MenuItemCreatedEvent event = menuService.createMenuItem(new CreateMenuItemEvent(menuItemDetails));
		
		String id = event.getNewMenuItemId();
		
		redirectAttrs.addFlashAttribute("message", "New Item Added.");
		
		LOG.debug("MenuItem " + id + " has been added.");
		
		return "redirect:/editMenu";
	}
	
	@RequestMapping(value="/removeMenuItem" , method = RequestMethod.POST)
	public String deleteMenuItem(@RequestParam(value = "id", required = true) String id) {
		LOG.debug("Deleting MenuItem " + id + ".");
		
		MenuItemDeletedEvent event = menuService.deleteMenuItem(new DeleteMenuItemEvent(id));
		
		if (!event.isEntityFound()) {
			LOG.debug("MenuItem " + id + " was not found.");
		}
		
		if (!event.isDeletionCompleted()) {
			LOG.debug("MenuItem " + id + " deletion was not completed.");
		}
		
		return "redirect:/editMenu";
	}
			
	private List<MenuItem> getMenuItems(AllMenuItemsEvent requestAllMenuItems) {
		List<MenuItem> menuDetails = new ArrayList<MenuItem>();
		
		for (MenuItemDetails menuItemDetails : requestAllMenuItems.getMenuItemDetails()) {
			menuDetails.add(MenuItem.fromMenuDetails(menuItemDetails));
		}

		return menuDetails;
	}
	
	private List<Album> getAlbums(AllAlbumsEvent requestAllAlbums) {
    	List<Album> albums = new LinkedList<Album>();
    	
    	for (AlbumDetails albumDetails : requestAllAlbums.getAlbumDetails()) {
    		albums.add(Album.fromAlbumDetails(albumDetails));
		}

		return albums;
    }
	
	@ModelAttribute("menuItem")
	public MenuItem getMenuItem() {
		return new MenuItem();
	}
	
	@ModelAttribute
    public Album getNewAlbum() {
    	return new Album();
    }
}
