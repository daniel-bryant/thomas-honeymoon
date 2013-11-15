package com.honeymoon.config;

import com.mongodb.Mongo;
import com.mongodb.MongoURI;
import com.honeymoon.persistence.repository.AlbumRepository;
import com.honeymoon.persistence.repository.MenuItemRepository;
import com.honeymoon.persistence.repository.OrdersRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import java.net.UnknownHostException;

@Configuration
@EnableMongoRepositories(basePackages = "com.honeymoon.persistence.repository",
      includeFilters = @ComponentScan.Filter(value = {AlbumRepository.class, MenuItemRepository.class, OrdersRepository.class},
      type = FilterType.ASSIGNABLE_TYPE))
public class MongoConfiguration {

  public @Bean
  MongoTemplate mongoTemplate(Mongo mongo) throws UnknownHostException {
	//MongoTemplate mongoTemplate = new MongoTemplate(mongo, "honeymoon");
	MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
	
	MongoTemplate mongoTemplate = new MongoTemplate(mongo, mongoURI.getDatabase());
	mongoTemplate.getDb().authenticate(mongoURI.getUsername(), mongoURI.getPassword());
	
    return mongoTemplate; 
  }

  public @Bean Mongo mongo() throws UnknownHostException {
    //return new Mongo("localhost");
	return new Mongo(new MongoURI(System.getenv("MONGOHQ_URL")));
  }
}
