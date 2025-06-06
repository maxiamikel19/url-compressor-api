package com.maxiamikel.url_compressor_spring_api.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maxiamikel.url_compressor_spring_api.dtos.UrlCreateDto;
import com.maxiamikel.url_compressor_spring_api.entities.UrlEntity;
import com.maxiamikel.url_compressor_spring_api.exception.DataIntegrityException;
import com.maxiamikel.url_compressor_spring_api.exception.ResourceNotFoundException;
import com.maxiamikel.url_compressor_spring_api.repositories.UrlRepository;
import com.maxiamikel.url_compressor_spring_api.services.UrlService;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private RandomStringReneratorServiceImpl randomStringReneratorService;

    @Override
    @Transactional
    public UrlEntity compresseAndSaveUrl(UrlCreateDto url) {
        if (url.getOriginalUrl().trim().length() == 0) {
            throw new DataIntegrityException("Url is required");
        }

        if (url.getDuration() == 0) {
            url.setDuration(1L);
        }

        String randomUrlId = randomStringReneratorService.generateRandomString();

        UrlEntity obj = new UrlEntity();
        obj.setId(randomUrlId);
        obj.setOriginalUrl(url.getOriginalUrl());
        obj.setExpiredAt(LocalDateTime.now().plusMinutes(url.getDuration()));

        return urlRepository.save(obj);

    }

    @Override
    public UrlEntity redirectToOriginalUrl(String id) {

        Optional<UrlEntity> opUrl = urlRepository.findById(id);
        if (!opUrl.isPresent()) {
            throw new ResourceNotFoundException("Url not exists or edpired");
        }

        return opUrl.get();
    }

}
