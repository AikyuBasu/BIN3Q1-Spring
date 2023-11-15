package vinci.stock.price.repositories;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import vinci.stock.price.models.Order;

@Repository
@FeignClient(name = "order")
public class OrderProxy {
  // se rappeler le dernier prix d'échange (= prix market) pour
  //    tous les titres en circulation sur la plateforme VSX.
// /order/open/by-ticker/{ticker}/{side}:
  @GetMapping("/order/open/by-ticker/{ticker}/{side}")
  public List<Order> getOpenOrdersByTickerAndSide(String ticker, String side) {
    return null;
    // only get the ones with type = MARKET
  }
}
