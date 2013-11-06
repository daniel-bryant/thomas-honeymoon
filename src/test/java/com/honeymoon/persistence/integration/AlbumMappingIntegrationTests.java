package com.honeymoon.persistence.integration;

import com.mongodb.Mongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import static com.honeymoon.persistence.domain.fixture.MongoAssertions.usingMongo;
import static com.honeymoon.persistence.domain.fixture.PersistenceFixture.weddingAlbum;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class AlbumMappingIntegrationTests {
	
	MongoOperations mongo;

	@Before
	public void setup() throws Exception {
		mongo = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), "honeymoon"));
		
		mongo.dropCollection("albums");
	}
	
	@After
	public void teardown() {
		mongo.dropCollection("albums");
	}
	
	@Test
	public void thatAlbumIsInsertedIntoCollectionHasCorrectIndexes() throws Exception {
		mongo.insert(weddingAlbum());
		
		assertEquals(1, mongo.getCollection("albums").count());
		
		assertTrue(usingMongo(mongo).collection("albums").hasIndexOn("_id"));
	    assertTrue(usingMongo(mongo).collection("albums").hasIndexOn("albumTitle"));
	}
	
	@Test
	public void thatAlbumCustomMappingWorks() throws Exception {
		mongo.insert(weddingAlbum());

	    assertTrue(usingMongo(mongo).collection("albums").first().hasField("albumTitle"));
	}
}
