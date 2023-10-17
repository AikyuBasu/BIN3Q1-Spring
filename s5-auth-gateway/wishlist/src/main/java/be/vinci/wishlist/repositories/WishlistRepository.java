package be.vinci.wishlist.repositories;

import be.vinci.wishlist.models.Wishlist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends CrudRepository<Wishlist, String> {
  // add method that finds a wishlist by user pseudo and product id
  boolean existsWishlistByPseudoAndProductId(String pseudo, int productId);

  void deleteWishlistByPseudoAndProductId(String pseudo, int productId);

  Iterable<Wishlist> findWishlistsByPseudo(String pseudo);

  void deleteWishlistsByPseudo(String pseudo);

  void deleteWishlistByProductId(int productId);
}
