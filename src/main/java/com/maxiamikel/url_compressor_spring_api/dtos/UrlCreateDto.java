package com.maxiamikel.url_compressor_spring_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlCreateDto {

    private String originalUrl;
    private Long duration;
}
