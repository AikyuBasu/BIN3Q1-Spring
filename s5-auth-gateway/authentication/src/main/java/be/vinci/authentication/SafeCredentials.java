package be.vinci.authentication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "credentials")
public class SafeCredentials {
  @Id
  @Column(nullable = false)
  private String pseudo;
  @Column(name = "password", nullable = false)
  private String hashedPassword;

  public SafeCredentials(String pseudo, String hashedPassword) {
    this.pseudo = pseudo;
    this.hashedPassword = hashedPassword;
  }
}
