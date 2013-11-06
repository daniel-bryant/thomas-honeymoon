package com.honeymoon.config;

import com.mongodb.Mongo;
import com.honeymoon.persistence.repository.AlbumRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

@Configuration
@EnableMongoRepositories(basePackages = "com.honeymoon.persistence.repository",
      includeFilters = @ComponentScan.Filter(value = {AlbumRepository.class}, type = FilterType.ASSIGNABLE_TYPE))
public class MongoConfiguration {

  public @Bean
  MongoTemplate mongoTemplate(Mongo mongo) throws UnknownHostException {
    return new MongoTemplate(mongo, "honeymoon");
  }

  public @Bean Mongo mongo() throws UnknownHostException {
    return new Mongo("localhost");
  }
  
  public @Bean
  GridFsTemplate gridFsTemplate(Mongo mongo) throws Exception {
	  SimpleMongoDbFactory dbFactory = new SimpleMongoDbFactory(mongo, "honeymoon");
	  MongoConverter converter = new MappingMongoConverter(dbFactory, new MongoMappingContext());
	  String bucket = "photos";
	  
	  return new GridFsTemplate(dbFactory, converter, bucket);
  }
}
