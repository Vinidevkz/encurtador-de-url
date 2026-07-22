package entities;

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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at;

    @PrePersist
    public void prePersist(){
        this.created_at = LocalDateTime.now();
    }

    public ShortURL(String hash, String originalUrl, User user){
        this.hashId = hash;
        this.originalUrl = originalUrl;
        this.user = user;
    }


}
