package service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.ShortURLRepository;

@Service
@RequiredArgsConstructor
public class ShortURLService {

    private final ShortURLRepository shortURLRepository;

    @Transactional
    public String createShortUrl(String longUrl, Long userId){
        return "";
    }

    @Transactional
    public void deleteShortUrl(String hashId, Long userId){}



}
