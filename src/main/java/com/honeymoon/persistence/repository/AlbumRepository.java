package com.honeymoon.persistence.repository;

import com.honeymoon.persistence.domain.Album;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {

	public Album findByTitle(String title);
	public Album findById(String id);
}
