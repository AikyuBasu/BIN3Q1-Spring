package be.vinci.authentication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends CrudRepository<SafeCredentials, String> {

  @Override
  boolean existsById(String s);
}
