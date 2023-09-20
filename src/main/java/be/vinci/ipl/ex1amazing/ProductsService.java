package be.vinci.ipl.ex1amazing;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private final ProductsRepository repository;

    public ProductsService(ProductsRepository repository){
        this.repository = repository;
    }

    public Product readOne(int id){
        return repository.findById(id).orElse(null);
    }

    public List<Product> readAll(){
        return (List<Product>) repository.findAll();
    }

    public boolean createOne(Product product){
        if (repository.existsById(product.getId())) return false;
        repository.save(product);
        return true;
    }

    public boolean updateOne(Product newProduct){
        Product p = readOne(newProduct.getId());
        if (p == null) return false;
        repository.delete(p); // chépa si c necessaire
        repository.save(newProduct);
        return true;
    }

    public Product deleteOne(int id){
        Product p = readOne(id);
        if (p == null) return null;
        repository.delete(p);
        return p;
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}
