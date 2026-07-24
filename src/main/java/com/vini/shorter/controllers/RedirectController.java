package com.vini.shorter.controllers;

import com.vini.shorter.dtos.OriginalUrlDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vini.shorter.service.ShortURLService;

import java.net.URI;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/shorter")
@RequiredArgsConstructor
public class RedirectController {

    private final ShortURLService shortURLService;

    @PostMapping("/create-short-url")
    public String createShortUrl(@RequestBody OriginalUrlDTO originalUrlDTO) throws NoSuchAlgorithmException {
        return shortURLService.createShortUrl(originalUrlDTO);
    }

    @GetMapping("/{hash}")
    public ResponseEntity<Void> redirectToOriginalString(@PathVariable String hash){
        String originalUrl = shortURLService.getOriginalURL(hash);

        if (!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")) {
            originalUrl = "https://" + originalUrl;
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}
