package be.vinci.wishlist;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;

@Repository
@FeignClient
public interface UsersProxy {
}
