package com.maxiamikel.url_compressor_spring_api.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.maxiamikel.url_compressor_spring_api.dtos.UrlCreateDto;
import com.maxiamikel.url_compressor_spring_api.entities.UrlEntity;
import com.maxiamikel.url_compressor_spring_api.exception.DataIntegrityException;
import com.maxiamikel.url_compressor_spring_api.exception.ResourceNotFoundException;
import com.maxiamikel.url_compressor_spring_api.repositories.UrlRepository;

public class UrlServiceImplTest {

    @InjectMocks
    private UrlServiceImpl urlService;

    @Mock
    private UrlRepository urlRepository;

    @Mock
    private RandomStringReneratorServiceImpl randomStringReneratorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveCompressedUrlSuccessfully() {
        // Arrange
        UrlCreateDto dto = new UrlCreateDto();
        dto.setOriginalUrl("https://exemplo.com");
        dto.setDuration(5L);

        String generatedId = "abc123";

        when(randomStringReneratorService.generateRandomString()).thenReturn(generatedId);
        when(urlRepository.save(any(UrlEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        UrlEntity result = urlService.compresseAndSaveUrl(dto);

        // Assert
        assertNotNull(result);
        assertEquals(generatedId, result.getId());
        assertEquals(dto.getOriginalUrl(), result.getOriginalUrl());
        assertTrue(result.getExpiredAt().isAfter(LocalDateTime.now()));
    }

    @Test
    void shouldThrowExceptionIfOriginalUrlIsEmpty() {
        UrlCreateDto dto = new UrlCreateDto();
        dto.setOriginalUrl("  "); // apenas espaços

        var ex = assertThrows(DataIntegrityException.class, () -> {
            urlService.compresseAndSaveUrl(dto);
        });

        assertEquals("Url is required", ex.getMessage());
    }

    @Test
    void shouldSetDefaultDurationIfNullOrZero() {
        UrlCreateDto dto = new UrlCreateDto();
        dto.setOriginalUrl("https://teste.com");
        dto.setDuration(1L); // ou 1L

        when(randomStringReneratorService.generateRandomString()).thenReturn("xyz456");
        when(urlRepository.save(any(UrlEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UrlEntity result = urlService.compresseAndSaveUrl(dto);

        assertNotNull(result.getExpiredAt());
    }

    @Test
    void shouldRedirectToOriginalUrlSuccessfully() {
        String id = "xyz456";
        UrlEntity entity = new UrlEntity();
        entity.setId(id);
        entity.setOriginalUrl("https://teste.com");

        when(urlRepository.findById(id)).thenReturn(Optional.of(entity));

        UrlEntity result = urlService.redirectToOriginalUrl(id);

        assertNotNull(result);
        assertEquals("https://teste.com", result.getOriginalUrl());
    }

    @Test
    void shouldThrowExceptionIfUrlNotFound() {
        String id = "nao-existe";

        when(urlRepository.findById(id)).thenReturn(Optional.empty());

        var ex = assertThrows(ResourceNotFoundException.class, () -> {
            urlService.redirectToOriginalUrl(id);
        });

        assertEquals("Url not exists or edpired", ex.getMessage());
    }
}
