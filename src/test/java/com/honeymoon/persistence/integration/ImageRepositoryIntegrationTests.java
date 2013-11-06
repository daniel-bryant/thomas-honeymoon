package com.honeymoon.persistence.integration;

import static com.honeymoon.persistence.domain.fixture.PersistenceFixture.simpleMetaData;
import static junit.framework.TestCase.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.honeymoon.config.MongoConfiguration;
import com.mongodb.gridfs.GridFSFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfiguration.class})
public class ImageRepositoryIntegrationTests {
	/* 
	 * These tests should all pass initially. Given that we have no ImageRepository,
	 * they simply ensure that the GridFS operations work the way we want.
	 */

	@Autowired
	GridFsOperations grid;
	
	@Before
	public void setup() throws Exception {
		grid.delete(null);
	}
	
	@After
	public void teardown() {
		grid.delete(null);
	}
	
	@Test
	public void thatImageIsInsertedIntoGridWorks() throws Exception {
		assertEquals(0, grid.find(null).size());
		
		Resource res = new ClassPathResource("frog.jpg");
		
		grid.store(res.getInputStream(), "frog.jpg", simpleMetaData());
		
		assertEquals(1, grid.find(null).size());
	}
	
	@Test
	public void thatFindImageByIdWorks() throws Exception {
		Resource res = new ClassPathResource("frog.jpg");
		
		GridFSFile fileIn = grid.store(res.getInputStream(), "frog.jpg", simpleMetaData());
		
		GridFSFile fileOut = grid.findOne(new Query().addCriteria(Criteria.where("_id").is(fileIn.getId())));
		
		assertEquals(fileIn.getFilename(), fileOut.getFilename());
	}
	
	@Test
	public void thatDeleteImageByIdWorks() throws Exception {
		Resource res = new ClassPathResource("frog.jpg");
		
		GridFSFile fileIn = grid.store(res.getInputStream(), "frog.jpg", simpleMetaData());
		
		assertEquals(1, grid.find(null).size());
		
		grid.delete(new Query(Criteria.where("_id").is(fileIn.getId())));
		
		assertEquals(0, grid.find(null).size());
	}
}
