package com.vini.shorter.service;

import com.vini.shorter.dtos.OriginalUrlDTO;
import com.vini.shorter.entities.ShortURL;
import com.vini.shorter.entities.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.vini.shorter.repositories.ShortURLRepository;
import com.vini.shorter.tools.Base62Encoder;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShortURLService {

    private final Base62Encoder base62Encoder;
    private final ShortURLRepository shortURLRepository;
    private final UserService userService;

    @Transactional
    public String createShortUrl(OriginalUrlDTO originalUrlDTO) throws NoSuchAlgorithmException{

        String originalUrl = originalUrlDTO.originalUrl();
        Long userId = originalUrlDTO.userId();

        String hashCode = base62Encoder.encode(originalUrl);
        Optional<User> opUser = userService.findById(userId);

        if(opUser.isEmpty()){
            throw new RuntimeException("Usuário não encontrado.");
        }
        User user = opUser.get();

        ShortURL shortURL = new ShortURL(hashCode, originalUrl, user);

        shortURLRepository.save(shortURL);

        return shortURL.getHashId();
    }

    @Transactional
    public String getOriginalURL(String hashId){
        Optional<ShortURL> opShortURL = shortURLRepository.findById(hashId);

        if(opShortURL.isEmpty()){
            throw new RuntimeException("URl não encontrada.");
        }

        ShortURL shortURL = opShortURL.get();

        String originalURL = shortURL.getOriginalUrl();

        return originalURL;
    }



    @Transactional
    public void deleteShortUrl(String hashId, Long userId){}



}
