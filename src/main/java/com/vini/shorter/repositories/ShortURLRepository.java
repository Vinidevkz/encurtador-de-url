package com.vini.shorter.repositories;

import com.vini.shorter.entities.ShortURL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortURLRepository extends JpaRepository<ShortURL, String> {
}
