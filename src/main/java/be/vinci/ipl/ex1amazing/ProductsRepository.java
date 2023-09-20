package be.vinci.ipl.ex1amazing;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<Product, Integer> {
    // rien n'est défini. Les findById, findAll etc, sont normalement générées automatiquement
    // SAUF SI j'ai des requêtes très spécifiques
}
