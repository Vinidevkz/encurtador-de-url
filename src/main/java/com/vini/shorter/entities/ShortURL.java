package com.vini.shorter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_shorturls")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShortURL {

    @Id
    private String hashId;
    private String originalUrl;


    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at;

    @PrePersist
    public void prePersist(){
        this.created_at = LocalDateTime.now();
    }

    public ShortURL(String hash, String originalUrl){
        this.hashId = hash;
        this.originalUrl = originalUrl;
    }


}
