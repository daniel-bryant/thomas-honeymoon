package com.honeymoon.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
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
import com.honeymoon.events.albums.CreateAlbumEvent;
import com.honeymoon.events.albums.RequestAllAlbumsEvent;

import static com.honeymoon.web.controller.fixture.WebDataFixture.ALBUM_1;
import static com.honeymoon.web.controller.fixture.WebDataFixture.ALBUM_2;
import static com.honeymoon.web.controller.fixture.WebDataFixture.allAlbums;
import static com.honeymoon.web.controller.fixture.WebDataFixture.newAlbum;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumsIntegrationTest {

	private static final String ALBUMS_VIEW = "/WEB-INF/views/albums.html";
	private static final String VIEW = "/albums";
	private static final String TITLE = "Wedding Pics";
	private static final String DESC = "The Reception";
	
    MockMvc mockMvc;

    @InjectMocks
    AlbumsController controller;
    
    @Mock
    AlbumService albumService;
    
    @Before
    public void setup() {


        MockitoAnnotations.initMocks(this);

        mockMvc = standaloneSetup(controller)
        		.setViewResolvers(viewResolver())
                .build();
        
        when(albumService.requestAllAlbums(any(RequestAllAlbumsEvent.class))).thenReturn(allAlbums());
    }

    private InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }
    
    @SuppressWarnings("unchecked")
	@Test
	public void albumUrlPopulatesViewModel() throws Exception {
		mockMvc.perform(get("/albums"))
		.andDo(print())
		.andExpect(model().size(2))
		.andExpect(model().attribute("albums", hasSize(2)))
		.andExpect(model().attribute("albums", hasItems(hasProperty("title", is(ALBUM_1)),
        hasProperty("title", is(ALBUM_2)))));
	}
    
    @Test
    public void thatAlbumsViewIsCorrect() throws Exception {
        mockMvc.perform(get("/albums"))
                .andExpect(forwardedUrl(ALBUMS_VIEW));
    }
    
    
    
    
    @Test
	public void thatRedirectsToAlbumsOnSuccess() throws Exception {
		String id = UUID.randomUUID().toString();

		when(albumService.createAlbum(any(CreateAlbumEvent.class))).thenReturn(newAlbum(id));

		mockMvc.perform(
				post("/albums").param("title", TITLE)
								 .param("description", DESC))
						         .andExpect(status().isMovedTemporarily())
						         .andExpect(redirectedUrl(VIEW));
	}

	@Test
	public void thatSendsCorrectAlbumsEventOnSuccess() throws Exception {
		String id = UUID.randomUUID().toString();

		when(albumService.createAlbum(any(CreateAlbumEvent.class))).thenReturn(newAlbum(id));
		
		mockMvc.perform(post("/albums")
				.param("title", TITLE)
				.param("description", DESC))
				.andDo(print());

		//@formatter:off
	    verify(albumService).createAlbum(Matchers.<CreateAlbumEvent>argThat(
	        allOf(
	            org.hamcrest.Matchers.<CreateAlbumEvent>hasProperty("details",
	            										   hasProperty("title", equalTo(TITLE))),

	            org.hamcrest.Matchers.<CreateAlbumEvent>hasProperty("details",
	            										   hasProperty("description", equalTo(DESC)))
	        )));
	//@formatter:on
	}

	@Test
	public void thatReturnToAlbumsIfValidationFail() throws Exception {
		String id = UUID.randomUUID().toString();
		
		when(albumService.createAlbum(any(CreateAlbumEvent.class)))
		.thenReturn(newAlbum(id));
		
		mockMvc.perform(post("/albums").param("title", TITLE))
		.andExpect(forwardedUrl(ALBUMS_VIEW))
		.andExpect(model().size(2));
	}
}
