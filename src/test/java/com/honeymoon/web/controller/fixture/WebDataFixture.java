package com.honeymoon.web.controller.fixture;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.honeymoon.events.albums.AlbumCreatedEvent;
import com.honeymoon.events.albums.AlbumDetails;
import com.honeymoon.events.albums.AlbumDetailsEvent;
import com.honeymoon.events.albums.AllAlbumsEvent;
import com.honeymoon.events.albums.Photo;
import com.honeymoon.events.images.ImageCreatedEvent;
import com.honeymoon.events.images.ImageDetails;
import com.honeymoon.events.images.ImageDetailsEvent;
import com.honeymoon.events.menu.AllMenuItemsEvent;
import com.honeymoon.events.menu.MenuItemCreatedEvent;
import com.honeymoon.events.menu.MenuItemDetails;
import com.honeymoon.events.orders.OrderCreatedEvent;
import com.honeymoon.events.orders.OrderDetails;
import com.honeymoon.events.orders.OrderDetailsEvent;
import com.honeymoon.events.orders.OrderStatusDetails;
import com.honeymoon.events.orders.OrderStatusEvent;
import com.honeymoon.events.orders.RequestOrderDetailsEvent;
import com.honeymoon.web.domain.MenuItem;

public class WebDataFixture {
	
	public static final String CUSTOMER_NAME = "Best Customer";
	public static final String NAME = "Yummy Noodles";
	public static final String CHEF_SPECIAL = "Special ";
	public static final String LOW_CAL = "Low cal ";
	public static final BigDecimal COST = new BigDecimal("10.99");
	public static final String DESC = "Trip to the beach.";
	public static final int MINUTES_TO_PREPARE = 5;
	public static final String STATUS_RECEIVED = "RECEIVED";
	public static final String ALBUM_1 = "Wedding";
	public static final String ALBUM_2 = "Reception";
	public static final String ALBUM_TITLE = "Wedding";
	public static final String ALBUM_DESC = "Wedding Photos";
	public static final List<Photo> PHOTOS = Arrays.asList(new Photo("1", "2"), new Photo("3", "4"), new Photo("5", "6"));

  public static OrderCreatedEvent newOrder(UUID id) {
    return new OrderCreatedEvent(id.toString(), new OrderDetails());
  }
  
  public static RequestOrderDetailsEvent orderDetailsRequest(UUID id) {
	return new RequestOrderDetailsEvent(id.toString());
	
}
  
  public static OrderDetailsEvent orderDetailsEvent(UUID id) {
	return new OrderDetailsEvent(id.toString(), standardOrderDetails(id));
	
}
  
  public static OrderDetails standardOrderDetails () {
	  return standardOrderDetails(UUID.randomUUID());
}
  
  public static OrderDetails standardOrderDetails (UUID id) {
	  OrderDetails orderDetails = new OrderDetails(id);
	  orderDetails.setName(CUSTOMER_NAME);
	  return orderDetails;
}
  
  public static OrderStatusEvent orderStatusEvent(UUID id) {
	  return new OrderStatusEvent(id.toString(), standardOrderStatusDetails(id));
  }
  
  public static OrderStatusDetails standardOrderStatusDetails(UUID orderId) {
	  return new OrderStatusDetails(orderId.toString(), UUID.randomUUID().toString(), new Date(), STATUS_RECEIVED);
  }

	public static AllMenuItemsEvent allMenuItems() {
		List<MenuItemDetails> menuItemDetails = new ArrayList<MenuItemDetails>();
		menuItemDetails.add(standardMenuItemDetails());
		menuItemDetails.add(standardMenuItemDetails(CHEF_SPECIAL + NAME));
		menuItemDetails.add(standardMenuItemDetails(LOW_CAL + NAME));
		return new AllMenuItemsEvent(menuItemDetails);
	}

	public static MenuItemCreatedEvent newMenuItem(String id) {
	  return new MenuItemCreatedEvent(id, new MenuItemDetails());
	}
	
	public static MenuItemDetails standardMenuItemDetails(String name) {
		return new MenuItemDetails(UUID.randomUUID().toString(), name, DESC, MINUTES_TO_PREPARE);
	}
	
	public static MenuItemDetails standardMenuItemDetails() {
		return standardMenuItemDetails(NAME);
	}
	
	public static MenuItem standardWebMenuItem () {
		return MenuItem.fromMenuDetails(standardMenuItemDetails());
	}
	
	public static AllAlbumsEvent allAlbums() {
		List<AlbumDetails> albumDetails = new LinkedList<AlbumDetails>();
		albumDetails.add(standardAlbumDetails(ALBUM_1));
		albumDetails.add(standardAlbumDetails(ALBUM_2));
		return new AllAlbumsEvent(albumDetails);
	}
	
	public static AlbumDetailsEvent albumDetailsEvent(UUID id) {
		return new AlbumDetailsEvent(id.toString(), standardAlbumDetails(id));
	}
	
	public static AlbumCreatedEvent newAlbum(String id) {
		return new AlbumCreatedEvent(id, new AlbumDetails());
	}
	
	public static AlbumDetails standardAlbumDetails(String title) {
		return new AlbumDetails(UUID.randomUUID().toString(), title, ALBUM_DESC, PHOTOS);
	}
	
	public static AlbumDetails standardAlbumDetails(UUID id) {
		return new AlbumDetails(id.toString(), ALBUM_TITLE, ALBUM_DESC, PHOTOS);
	}
	
	public static ImageCreatedEvent newImage(String id) {
		return new ImageCreatedEvent(id, new ImageDetails());
	}
	
	public static ImageDetailsEvent newImageDetails(String id, byte[] bytes) {
		return new ImageDetailsEvent(id, imageDetails(id, bytes));
	}
	
	public static ImageDetails imageDetails(String id, byte[] bytes) {
		return new ImageDetails(id, bytes);
	}

}