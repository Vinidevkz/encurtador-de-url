package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long userId;


}
