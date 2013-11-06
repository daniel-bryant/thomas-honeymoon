package com.honeymoon.persistence.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.honeymoon.config.MongoConfiguration;
import com.honeymoon.persistence.domain.Album;
import com.honeymoon.persistence.repository.AlbumRepository;
import static com.honeymoon.persistence.domain.fixture.PersistenceFixture.weddingAlbum;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfiguration.class})
public class AlbumRepositoryIntegrationTests {

	@Autowired
	AlbumRepository albumRepository;
	
	@Autowired
	MongoOperations mongo;
	
	@Before
	public void setup() throws Exception {
		mongo.dropCollection("albums");
	}
	
	@After
	public void teardown() {
		mongo.dropCollection("albums");
	}
	
	@Test
	public void thatAlbumIsInsertedIntoRepoWorks() throws Exception {
		assertEquals(0, mongo.getCollection("albums").count());
		
		albumRepository.save(weddingAlbum());
		
		assertEquals(1, mongo.getCollection("albums").count());
	}
	
	@Test
	public void thatAlbumFindByTitleWorks() throws Exception {
		String TITLE = "test_title";
		String DESC = "test_desc";
		
		assertEquals(0, mongo.getCollection("albums").count());
		
		albumRepository.save(new Album(TITLE, DESC));

		assertEquals(albumRepository.findByTitle(TITLE).getDescription(), DESC);
	}
	
	@Test
	public void thatAlbumFindByIdWorks() throws Exception {
		String TITLE = "test_title";
		String DESC = "test_desc";
		
		assertEquals(0, mongo.getCollection("albums").count());
		
		Album retAlbum = albumRepository.save(new Album(TITLE, DESC));

		assertEquals(albumRepository.findById(retAlbum.getId()).getDescription(), DESC);	
	}
}
