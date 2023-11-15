package vinci.stock.price;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vinci.stock.price.models.Price;

@RestController
public class PriceController {
  private final PriceService priceService;

  public PriceController(PriceService priceService) {
    this.priceService = priceService;
  }

  @GetMapping("/price/{ticker}")
  public Price getPriceFromStock(@PathVariable String ticker) {
    return null;
  }

  @PatchMapping("/price/{ticker}")
  public void updatePrice(@PathVariable String ticker) {
    return;
  }
}
