package com.honeymoon.web.controller;

import static com.honeymoon.web.controller.fixture.WebDataFixture.albumDetailsEvent;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.honeymoon.core.services.AlbumService;
import com.honeymoon.events.albums.RequestAlbumDetailsEvent;
import com.honeymoon.web.controller.fixture.WebDataFixture;

public class AlbumIntegrationTest {

	private static final String ALBUM_VIEW = "/WEB-INF/views/album.html";
	
	private static UUID uuid;
	
	MockMvc mockMvc;
	
	@InjectMocks
	AlbumController controller;
	
	@Mock
	AlbumService albumService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		mockMvc = standaloneSetup(controller).setViewResolvers(viewResolver())
				.build();
		uuid = UUID.randomUUID();
	}
	
	private InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views");
		viewResolver.setSuffix(".html");
		return viewResolver;
	}
	
	@Test
	public void thatAlbumViewIsForwardedTo() throws Exception {
		
		when(albumService.requestAlbumDetails(any(RequestAlbumDetailsEvent.class))).thenReturn(albumDetailsEvent(uuid));
		//when(orderService.requestOrderStatus(any(RequestOrderStatusEvent.class))).thenReturn(orderStatusEvent(uuid));
		
		mockMvc.perform(get("/album/" + uuid))
		.andExpect(status().isOk())
		.andExpect(forwardedUrl(ALBUM_VIEW));
	}
	
	@Test
	public void thatAlbumIsPutInModel() throws Exception {
		
		when(albumService.requestAlbumDetails(any(RequestAlbumDetailsEvent.class))).thenReturn(albumDetailsEvent(uuid));
		//when(orderService.requestOrderStatus(any(RequestOrderStatusEvent.class))).thenReturn(orderStatusEvent(uuid));
		
		mockMvc.perform(get("/album/" + uuid))
			.andExpect(model().attributeExists("album"))
			.andExpect(model().attribute("album", hasProperty("title", equalTo(WebDataFixture.ALBUM_TITLE))))
			.andExpect(model().attribute("album", hasProperty("description", equalTo(WebDataFixture.ALBUM_DESC))))
			.andExpect(model().attribute("album", hasProperty("photos", equalTo(WebDataFixture.PHOTOS))));
		
		verify(albumService).requestAlbumDetails(Matchers.<RequestAlbumDetailsEvent>argThat(
				org.hamcrest.Matchers.<RequestAlbumDetailsEvent>hasProperty("id", equalTo(uuid.toString()))));
		//verify(albumService).requestOrderStatus(any(RequestOrderStatusEvent.class));
	}
}
