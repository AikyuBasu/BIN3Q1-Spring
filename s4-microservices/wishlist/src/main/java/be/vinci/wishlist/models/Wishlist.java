package be.vinci.wishlist.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "wishlists")
public class Wishlist {
    @Id
    int id;
    String pseudo;
    @Column(name="product_id")
    int productId;
}
