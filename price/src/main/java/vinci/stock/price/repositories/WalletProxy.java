package vinci.stock.price.repositories;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import vinci.stock.price.models.Position;

@Repository
@FeignClient(name = "wallet")
public class WalletProxy {
  @GetMapping("/wallet/{username}")
  public List<Position> getWalletByUsername(String username) {
    return null;
  }
}
