package be.vinci.ipl.ex1amazing;

import static java.nio.file.Files.exists;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {
  private static final List<Product> products = new ArrayList<>();

  // ajout de données bidon
  static {
    products.add(new Product(1, "Little spoon","Kitchenware", 6.99));
    products.add(new Product(2, "Big bone", "Dog food woof", 59.99));
    products.add(new Product(3, "Natural and totally legal steroids", "Bodybuilding Cheatcodes", 789.99));
  }

  @GetMapping("/products")
  public Iterable<Product> readAll(){
    return products;
  }

  @PostMapping("/products")
  public ResponseEntity<Product> createOne(@RequestBody Product product) {
    if (product == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    for (Product p : products) {
      if (product.getId() == p.getId()) return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    products.add(product);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }

  @DeleteMapping("/products/{id}")
  public ResponseEntity<Product> deleteOne(@PathVariable int id){
    if (id < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    Product pFound = null;
    for (Product p : products) {
      if (p.getId() == id){
        pFound = p;
        break;
      }
    }
    if (pFound == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    products.remove(pFound);
    return new ResponseEntity<>(pFound, HttpStatus.OK);
  }

  @DeleteMapping("/products")
  public ResponseEntity<Product> deleteAll(){
    while (!products.isEmpty()) products.remove(0);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
