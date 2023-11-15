package be.vinci.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private final AuthenticationRepository repository;
  private final Algorithm jwtAlgorithm;
  private final JWTVerifier jwtVerifier;

  public AuthenticationService(AuthenticationRepository repository, AuthenticationProperties properties) {
    this.repository = repository;
    this.jwtAlgorithm = Algorithm.HMAC256(properties.getSecret());
    this.jwtVerifier = JWT.require(jwtAlgorithm).withIssuer("auth0").build();
  }

  public boolean createOne(UnsafeCredentials unsafeCredentials) {
    if (repository.existsById(unsafeCredentials.getPseudo())) {
      return false;
    }
    String hashedPassword = BCrypt.hashpw(unsafeCredentials.getPassword(), BCrypt.gensalt());
    repository.save(unsafeCredentials.makeSafe(hashedPassword));
    return true;
  }

  public boolean updateOne(UnsafeCredentials unsafeCredentials) {
    if (!repository.existsById(unsafeCredentials.getPseudo())) {
      return false;
    }
    String hashedPassword = BCrypt.hashpw(unsafeCredentials.getPassword(), BCrypt.gensalt());
    repository.save(unsafeCredentials.makeSafe(hashedPassword));
    return true;
  }

  public String connect(UnsafeCredentials unsafeCredentials) {
    SafeCredentials safeCredentials = repository.findById(unsafeCredentials.getPseudo()).orElse(null);
    if (safeCredentials == null) return null;
    if (!BCrypt.checkpw(unsafeCredentials.getPassword(), safeCredentials.getHashedPassword())) return null;
    return JWT.create().withIssuer("auth0").withClaim("pseudo", safeCredentials.getPseudo()).sign(jwtAlgorithm);
  }

  public String verify(String token) {
    try {
      String pseudo = jwtVerifier.verify(token).getClaim("pseudo").asString();
      if (!repository.existsById(pseudo)) return null;
      return pseudo;
    } catch (JWTVerificationException e) {
      return null;
    }
  }

  public boolean deleteOne(String pseudo) {
    if (!repository.existsById(pseudo)) {
      return false;
    }
    repository.deleteById(pseudo);
    return true;
  }


}
