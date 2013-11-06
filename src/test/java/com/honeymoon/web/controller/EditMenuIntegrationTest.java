package com.honeymoon.web.controller;

import static com.honeymoon.web.controller.fixture.WebDataFixture.allMenuItems;
import static com.honeymoon.web.controller.fixture.WebDataFixture.newMenuItem;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
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

import com.honeymoon.core.services.MenuService;
import com.honeymoon.events.menu.CreateMenuItemEvent;
import com.honeymoon.events.menu.DeleteMenuItemEvent;
import com.honeymoon.events.menu.MenuItemDeletedEvent;
import com.honeymoon.events.menu.RequestAllMenuItemsEvent;

public class EditMenuIntegrationTest {
	
	private static final String STANDARD = "Yummy Noodles";
	private static final String CHEF_SPECIAL = "Special Yummy Noodles";
	private static final String LOW_CAL = "Low cal Yummy Noodles";
	private static final String EDITMENU_VIEW = "/WEB-INF/views/editMenu.html";
	private static final String VIEW = "/editMenu";
	private static final String NAME = "Beach Trip";
	private static final String DESC = "A trip to the beach.";
	private static final String COST = "100";

	MockMvc mockMvc;
	
	@InjectMocks
	EditMenuController controller;
	
	@Mock
	MenuService menuService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
						
		mockMvc = standaloneSetup(controller)
				.setViewResolvers(viewResolver())
				.build();
		
		when(menuService.requestAllMenuItems(any(RequestAllMenuItemsEvent.class))).thenReturn(allMenuItems());

	}
	
	private InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views");
		viewResolver.setSuffix(".html");
		return viewResolver;
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void editMenuUrlPopulatesViewModel() throws Exception {
		mockMvc.perform(get("/editMenu"))
		.andDo(print())
		.andExpect(model().size(2))
		.andExpect(model().attribute("menuItems", hasSize(3)))
		.andExpect(model().attribute("menuItems", hasItems(hasProperty("name", is(STANDARD)),
        hasProperty("name", is(CHEF_SPECIAL)),
        hasProperty("name", is(LOW_CAL)))));
	}
	
	@Test
	public void editMenuUrlforwardsCorrectly() throws Exception {
		mockMvc.perform(get("/editMenu"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(view().name(VIEW))
		.andExpect(forwardedUrl(EDITMENU_VIEW));

	}
	
	@Test
	public void thatRedirectsToEditMenuOnSuccess() throws Exception {
		String id = UUID.randomUUID().toString();

		when(menuService.createMenuItem(any(CreateMenuItemEvent.class))).thenReturn(newMenuItem(id));

		mockMvc.perform(
				post("/editMenu").param("name", NAME)
								 .param("description", DESC)
								 .param("cost", COST))
						         .andExpect(status().isMovedTemporarily())
						         .andExpect(redirectedUrl(VIEW));
	}

	@Test
	public void thatSendsCorrectMenuItemEventOnSuccess() throws Exception {
		String id = UUID.randomUUID().toString();

		when(menuService.createMenuItem(any(CreateMenuItemEvent.class))).thenReturn(newMenuItem(id));
		
		mockMvc.perform(post("/editMenu")
				.param("name", NAME)
				.param("description", DESC)
				.param("cost", COST))
				.andDo(print());

		//@formatter:off
	    verify(menuService).createMenuItem(Matchers.<CreateMenuItemEvent>argThat(
	        allOf(
	            org.hamcrest.Matchers.<CreateMenuItemEvent>hasProperty("details",
	            										   hasProperty("name", equalTo(NAME))),

	            org.hamcrest.Matchers.<CreateMenuItemEvent>hasProperty("details",
	            										   hasProperty("description", equalTo(DESC))),
	            org.hamcrest.Matchers.<CreateMenuItemEvent>hasProperty("details",
	            										   hasProperty("cost", equalTo(Integer.parseInt(COST))))
	        )));
	//@formatter:on
	}

	@Test
	public void thatReturnToEditMenuIfValidationFail() throws Exception {
		String id = UUID.randomUUID().toString();
		
		when(menuService.createMenuItem(any(CreateMenuItemEvent.class)))
		.thenReturn(newMenuItem(id));
		
		mockMvc.perform(post("/editMenu").param("name", NAME))
		.andExpect(forwardedUrl(EDITMENU_VIEW))
		.andExpect(model().size(2));
	}
	
	/*@Test
	public void thatDeleteButtonWorks() throws Exception {
		when(menuService.deleteMenuItem(any(DeleteMenuItemEvent.class))).thenReturn(new MenuItemDeletedEvent(key, details)));

		
	}*/
}
