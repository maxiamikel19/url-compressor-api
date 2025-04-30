package com.maxiamikel.url_compressor_spring_api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.maxiamikel.url_compressor_spring_api.entities.UrlEntity;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {

}
