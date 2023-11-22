package vinci.stock.price;

import java.util.Optional;
import org.springframework.stereotype.Service;
import vinci.stock.price.entities.Price;
import vinci.stock.price.repositories.PriceRepository;

@Service
public class PriceService {
  private final PriceRepository repository;

  public PriceService(PriceRepository repository) {
    this.repository = repository;
  }

  public int getOne(String ticker) {
    Optional<Price> p = repository.findById(ticker);
    return p.map(Price::getPrice).orElse(1);
  }

  // update existing price or create one
  public void updateOne(Price p) {
    Optional<Price> pr = repository.findById(p.getTicker());
    if (pr.isEmpty()){
      repository.save(p);
    } else {
      Price price = pr.get();
      price.setPrice(p.getPrice());
      repository.save(price);
    }
  }
}
