package controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ShortURLService;

@RestController
@RequestMapping("shorter")
@RequiredArgsConstructor
public class RedirectController {

    private final ShortURLService shortURLService;

    @GetMapping("/{hash}")
    public ResponseEntity<Void> redirectToOriginalString(@PathVariable String hash){
        String originalUrl = shortURLService.getOriginalURL(hash);

        if(!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")){
                originalUrl = "https://" + originalUrl;
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, originalUrl)
                .build();
    }
}
