package com.honeymoon.persistence.integration;

import static com.honeymoon.persistence.domain.fixture.PersistenceFixture.simpleMetaData;
import static junit.framework.TestCase.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.Mongo;

public class ImageMappingIntegrationTests {

	GridFsOperations grid;
	
	@Before
	public void setup() throws Exception {
		SimpleMongoDbFactory dbFactory = new SimpleMongoDbFactory(new Mongo(), "honeymoon");
		MongoConverter converter = new MappingMongoConverter(dbFactory, new MongoMappingContext());
		String bucket = "test";
		
		grid = new GridFsTemplate(dbFactory, converter, bucket);
		
		grid.delete(null);
	}
	
	@After
	public void teardown() throws Exception {
		grid.delete(null);
	}
	
	@Test
	public void thatGridFsSetupWorks() throws Exception {
		Resource res = new ClassPathResource("frog.jpg");
		
		grid.store(res.getInputStream(), "frog.jpg", simpleMetaData());
		
		assertEquals(1, grid.find(null).size());
	}
}
