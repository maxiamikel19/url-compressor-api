package com.maxiamikel.url_compressor_spring_api.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxiamikel.url_compressor_spring_api.dtos.UrlCreateDto;
import com.maxiamikel.url_compressor_spring_api.dtos.UrlResponseDto;
import com.maxiamikel.url_compressor_spring_api.services.impl.UrlServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UrlController {

    @Autowired
    private UrlServiceImpl urlServiceImpl;

    @PostMapping(value = "compress-urls")
    public ResponseEntity<UrlResponseDto> compresseAndSaveUrl(@RequestBody UrlCreateDto url,
            HttpServletRequest servletRequest) {

        var newUrl = urlServiceImpl.compresseAndSaveUrl(url);

        String compressedUrl = servletRequest.getRequestURL().toString().replace("compress-urls", newUrl.getId());

        int hour = newUrl.getExpiredAt().getHour();
        int min = newUrl.getExpiredAt().getMinute();
        int sec = newUrl.getExpiredAt().getMinute();
        String expireAt = hour + ":" + min + ":" + sec;

        var response = new UrlResponseDto(compressedUrl, expireAt);

        return new ResponseEntity<UrlResponseDto>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String id) {
        var url = urlServiceImpl.redirectToOriginalUrl(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getOriginalUrl()));
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

}
