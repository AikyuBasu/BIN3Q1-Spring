package be.vinci.wishlist;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Wishlist {
    @Id
    int id;
    String pseudo;
    int productId;
}
