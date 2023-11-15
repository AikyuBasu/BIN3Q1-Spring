package vinci.stock.price;

import org.springframework.stereotype.Service;
import vinci.stock.price.models.Price;
import vinci.stock.price.repositories.OrderProxy;
import vinci.stock.price.repositories.PriceRepository;

@Service
public class PriceService {
  private final PriceRepository priceRepository;
  private final OrderProxy orderProxy;

  public PriceService(PriceRepository priceRepository, OrderProxy orderProxy) {
    this.priceRepository = priceRepository;
    this.orderProxy = orderProxy;
  }

  public Price getPriceFromStock(String ticker) {
    return null;
  }

  public void updatePrice(String ticker) {
    return;
  }
}
