package be.vinci.ipl.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {
  private final ProductsService service;

  public ProductsController(ProductsService service){
    this.service = service;
  }

  /**
   * Create a product
   * @request POST /products
   * @response 201: returns the created product
   * @response 409: if the product already exists
   * @response 400: if the product is null
   */
  @PostMapping("/products")
  public ResponseEntity<Product> createOne(@RequestBody Product product) {
    if (product == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    if (service.createOne(product)) return new ResponseEntity<>(product, HttpStatus.CREATED);
    return new ResponseEntity<>(HttpStatus.CONFLICT);
  }

  /**
   * Read all products
   * @request GET /products
   * @response 200: returns all videos
   * @response 404: if there are no products
   */
  @GetMapping("/products")
    public ResponseEntity<List<Product>> readAll(){
        List<Product> p = service.readAll();
        if (p.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

  /**
   * Read one product
   * @request GET /products/{id}
   * @response 200: returns the product
   * @response 404: if the product does not exist
   * @response 400: if the id is invalid
   */
  @GetMapping("/products/{id}")
  public ResponseEntity<Product> readOne(@PathVariable int id){
    if (id < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    Product p = service.readOne(id);
    if (p == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(p, HttpStatus.OK);
  }


  /**
   * Update one product
   * @request PUT /products/{id}
   * @response 200: returns the updated product
   * @response 404: if the product does not exist
   * @response 400: if the id is invalid
   */
  @PutMapping("/products/{id}")
  public ResponseEntity<Product> updateOne(@PathVariable int id, @RequestBody Product product){
    if (id < 1 || product == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    boolean updated = service.updateOne(product);
    if (!updated) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

    /**
     * Delete all products
     * @request DELETE /products
     * @response 200: returns the deleted products
     */
  @DeleteMapping("/products")
  public ResponseEntity<Product> deleteAll(){
    service.deleteAll();
    return new ResponseEntity<>(HttpStatus.OK);
  }

    /**
     * Delete one product
     * @request DELETE /products/{id}
     * @response 200: returns the deleted product
     * @response 404: if the product does not exist
     * @response 400: if the id is invalid
     */
  @DeleteMapping("/products/{id}")
  public ResponseEntity<Product> deleteOne(@PathVariable int id){
    if (id < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    Product p = service.deleteOne(id);
    if (p == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(p, HttpStatus.OK);
  }


}
