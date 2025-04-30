package com.maxiamikel.url_compressor_spring_api.services;

import com.maxiamikel.url_compressor_spring_api.dtos.UrlCreateDto;
import com.maxiamikel.url_compressor_spring_api.entities.UrlEntity;

public interface UrlService {

    UrlEntity compresseAndSaveUrl(UrlCreateDto url);

    UrlEntity redirectToOriginalUrl(String id);

}
