package com.honeymoon.web.controller;

import static com.honeymoon.web.controller.fixture.WebDataFixture.newImageDetails;
import static com.honeymoon.events.images.ImageDetailsEvent.notFound;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MockMvc;

import com.honeymoon.core.services.ImageService;
import com.honeymoon.events.images.RequestImageDetailsEvent;

public class ImageIntegrationTest {
	
	private static final String FILENAME = "frog.jpg";

	MockMvc mockMvc;
	
	@InjectMocks
	ImageController controller;
	
	@Mock
	ImageService imageService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
						
		mockMvc = standaloneSetup(controller)
				.build();
	}
	
	@Test
	public void thatSendsCorrectByteArrayOnSuccess() throws Exception {
		String id = UUID.randomUUID().toString();
		
		Resource res = new ClassPathResource(FILENAME);
		byte[] bytes = IOUtils.toByteArray(res.getInputStream());

		when(imageService.requestImageDetails(any(RequestImageDetailsEvent.class))).thenReturn(newImageDetails(id, bytes));
		
		mockMvc.perform(get("/image/" + id))
				.andDo(print())
				.andExpect(content().bytes(bytes));
	}

	@Test
	public void thatSendsNullOnFail() throws Exception {
		String id = UUID.randomUUID().toString();
		
		when(imageService.requestImageDetails(any(RequestImageDetailsEvent.class))).thenReturn(notFound(id));
		
		mockMvc.perform(get("/image/" + id))
				.andDo(print())
				.andExpect(content().bytes(new byte[0]));
	}
}
