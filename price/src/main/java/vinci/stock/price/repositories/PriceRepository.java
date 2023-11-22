package vinci.stock.price.repositories;

import java.util.Optional;
import org.jvnet.hk2.annotations.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vinci.stock.price.entities.Price;

@Repository
public interface PriceRepository extends CrudRepository<Price, String> {

}
