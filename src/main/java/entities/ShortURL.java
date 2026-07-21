package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long userId;


}
