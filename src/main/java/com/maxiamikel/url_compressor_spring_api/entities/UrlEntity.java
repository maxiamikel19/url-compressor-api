package com.maxiamikel.url_compressor_spring_api.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "urls_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlEntity {

    @Id
    private String id;

    private String originalUrl;

    private String compressedUrl;

    @Indexed(expireAfterSeconds = 0)
    private LocalDateTime expiredAt;

}
