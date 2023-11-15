package be.vinci.authentication;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
  private final AuthenticationService service;

  public AuthenticationController(AuthenticationService service) {
    this.service = service;
  }

  // generate JavaDoc

  /**
   * Connect a user with his credentials
   * @param credentials
   * @return a token if the credentials are valid, null otherwise
   */
  @PostMapping("/authenticate/connect")
  public ResponseEntity<String> connect(@RequestBody UnsafeCredentials credentials){
    if (credentials.invalid()) return ResponseEntity.badRequest().build();
    String res = service.connect(credentials);
    return res == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(res);
  }

  /**
   * Verify a token
   * @param token
   * @return 200 if the token is valid, 404 otherwise
   */
  @PostMapping("/authentication/verify")
  public ResponseEntity<String> verify(@RequestBody String token){
    String res = service.verify(token);
    return res == null ? ResponseEntity.notFound().build() : ResponseEntity.ok().build();
  }

  /**
   * Create a new user
   * @param pseudo
   * @param password
   * @return 201 if the user is created, 409 if the user already exists
   */
  @PostMapping("/authentication/{pseudo}")
  public ResponseEntity<Void> createOne(@PathVariable String pseudo, @RequestBody String password){
    if (pseudo == null || password == null) return ResponseEntity.badRequest().build();
    UnsafeCredentials credentials = new UnsafeCredentials();
    credentials.setPseudo(pseudo);
    credentials.setPassword(password);
    if (service.createOne(credentials)) return ResponseEntity.status(201).build();
    return ResponseEntity.status(409).build();
  }

  /**
   * Update a user
   * @param pseudo
   * @param password
   * @return
   */
  @PutMapping("/authentication/{pseudo}")
  public ResponseEntity<Void> updateOne(@PathVariable String pseudo, @RequestBody String password){
    if (pseudo == null || password == null) return ResponseEntity.badRequest().build();
    UnsafeCredentials credentials = new UnsafeCredentials();
    credentials.setPseudo(pseudo);
    credentials.setPassword(password);
    if (service.updateOne(credentials)) return ResponseEntity.ok().build();
    return ResponseEntity.notFound().build();
  }

  /**
   * Delete a user
   * @param pseudo
   * @return
   */
  @DeleteMapping
  public ResponseEntity<Void> deleteOne(@PathVariable String pseudo){
    if (pseudo == null) return ResponseEntity.badRequest().build();
    if (service.deleteOne(pseudo)) return ResponseEntity.ok().build();
    return ResponseEntity.notFound().build();
  }


}
