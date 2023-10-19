package be.vinci.authentication;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
  private final AuthenticationService service;

  public AuthenticationController(AuthenticationService service) {
    this.service = service;
  }

  @PostMapping("/authenticate/connect")
  public ResponseEntity<String> connect(@RequestBody UnsafeCredentials credentials){
    if (credentials.invalid()) return ResponseEntity.badRequest().build();
    String res = service.connect(credentials);
    return res == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(res);
  }

  @PostMapping("/authentication/verify")
  public ResponseEntity<String> verify(@RequestBody String token){
    String res = service.verify(token);
    return res == null ? ResponseEntity.notFound().build() : ResponseEntity.ok().build();
  }

  @PostMapping("/authentication/{pseudo}")
  public ResponseEntity<Void> createOne(@PathVariable String pseudo, @RequestBody String password){
    return null;
  }


}
