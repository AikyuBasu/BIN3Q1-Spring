package be.vinci.wishlist;

import be.vinci.wishlist.models.Product;
import be.vinci.wishlist.models.User;
import be.vinci.wishlist.models.Wishlist;
import org.springframework.stereotype.Service;
import be.vinci.wishlist.repositories.ProductsProxy;
import be.vinci.wishlist.repositories.UsersProxy;
import be.vinci.wishlist.repositories.WishlistRepository;

@Service
public class WishlistService {
  private final ProductsProxy productsProxy;
  private final UsersProxy usersProxy;
  private final WishlistRepository wishlistRepository;

  public WishlistService(ProductsProxy productsProxy, UsersProxy usersProxy, WishlistRepository WishlistRepository) {
    this.productsProxy = productsProxy;
    this.usersProxy = usersProxy;
    this.wishlistRepository = WishlistRepository;
  }

  public boolean addProductToWishlist(String pseudo, int productId) {
    User user = usersProxy.readOne(pseudo);
    Product product = productsProxy.readOne(productId);
    if (user == null || product == null) {
      return false;
    }
    // if a wishlist already exists with the same pseudo+productId, return false
    if (wishlistRepository.existsWishlistByPseudoAndProductId(pseudo,productId)) {
      return false;
    }
    Wishlist w = new Wishlist();
    w.setPseudo(pseudo);
    w.setProductId(productId);
    wishlistRepository.save(w);
    return true;
  }


  public boolean deleteProductFromWishlist(String pseudo, int productId) {
    User user = usersProxy.readOne(pseudo);
    Product product = productsProxy.readOne(productId);
    if (user == null || product == null) {
      return false;
    }
    // if no wishlist exists with the given pseudo and productId, return false
    if (!wishlistRepository.existsWishlistByPseudoAndProductId(pseudo,productId)) {
      return false;
    }
    wishlistRepository.deleteWishlistByPseudoAndProductId(pseudo,productId);
    return true;
  }

  public Iterable<Wishlist> getWishlistsByUserPseudo(String pseudo) {
    User user = usersProxy.readOne(pseudo);
    if (user == null) {
      return null;
    }
    return wishlistRepository.findWishlistsByPseudo(pseudo);
  }

  public boolean deleteWishlistByUserPseudo(String pseudo) {
    User user = usersProxy.readOne(pseudo);
    if (user == null) {
      return false;
    }
    wishlistRepository.deleteWishlistsByPseudo(pseudo);
    return true;
  }

  public boolean deleteProductFromAllWishlists(int productId) {
    Product product = productsProxy.readOne(productId);
    if (product == null) {
      return false;
    }
    wishlistRepository.deleteWishlistByProductId(productId);
    return true;
  }
}
