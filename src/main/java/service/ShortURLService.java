package service;

import entities.ShortURL;
import entities.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.ShortURLRepository;
import tools.Base62Encoder;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShortURLService {

    private final Base62Encoder base62Encoder;
    private final ShortURLRepository shortURLRepository;
    private final UserService userService;

    @Transactional
    public String createShortUrl(String longUrl, Long userId) throws NoSuchAlgorithmException{
        String hashCode = base62Encoder.encode(longUrl);
        Optional<User> opUser = userService.findById(userId);

        if(opUser.isEmpty()){
            throw new RuntimeException("Usuário não encontrado.");
        }
        User user = opUser.get();

        ShortURL shortURL = new ShortURL(hashCode, longUrl, user);

        return shortURL.getHashId();
    }

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
