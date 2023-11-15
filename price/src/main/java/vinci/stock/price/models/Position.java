package vinci.stock.price.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Position {
  private String ticker;
  private int quantity;
  private double price;
}
