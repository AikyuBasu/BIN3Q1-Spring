package vinci.stock.price.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="price")
public class Price {
  @Id
  @Column(nullable=false, length=4)
  private String ticker;
  @Column(nullable=false)
  private double price;
}
