package be.vinci.wishlist;

import be.vinci.wishlist.models.Wishlist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WishlistController {
  private WishlistService wishlistService;

  public WishlistController(WishlistService wishlistService) {
    this.wishlistService = wishlistService;

  }
  @PutMapping("/wishlist/{pseudo}/{productId}")
  public ResponseEntity<Wishlist> addProductToWishlist(@PathVariable String pseudo, @PathVariable int productId) {
    boolean added = wishlistService.addProductToWishlist(pseudo, productId);
    if (added) return new ResponseEntity<>(HttpStatus.OK);
    else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  // DELETE /wishlists/{pseudo}/{productId}
  @DeleteMapping("/wishlist/{pseudo}/{productId}")
  public ResponseEntity<Wishlist> deleteProductFromWishlist(@PathVariable String pseudo, @PathVariable int productId) {
    boolean deleted = wishlistService.deleteProductFromWishlist(pseudo, productId);
    if (deleted) return new ResponseEntity<>(HttpStatus.OK);
    else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  // GET /wishlists/user/{pseudo}
  @GetMapping("/wishlists/user/{pseudo}")
  public ResponseEntity<Iterable<Wishlist>> getWishlistByUserPseudo(@PathVariable String pseudo) {
    Iterable<Wishlist> wishlist = wishlistService.getWishlistsByUserPseudo(pseudo);
    if (wishlist == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    else return new ResponseEntity<>(wishlist, HttpStatus.OK);
  }

  // DELETE /wishlists/user/{pseudo}
  @DeleteMapping("/wishlists/user/{pseudo}")
  public ResponseEntity<Wishlist> deleteWishlistByUserPseudo(@PathVariable String pseudo) {
    boolean deleted = wishlistService.deleteWishlistByUserPseudo(pseudo);
    if (deleted) return new ResponseEntity<>(HttpStatus.OK);
    else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  // DELETE /wishlists/product/{productId} Supprime le produit de toutes les wishlists
  @DeleteMapping("/wishlists/product/{productId}")
  public ResponseEntity<Wishlist> deleteProductFromAllWishlists(@PathVariable int productId) {
    boolean deleted = wishlistService.deleteProductFromAllWishlists(productId);
    if (deleted) return new ResponseEntity<>(HttpStatus.OK);
    else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}

