package vinci.stock.price.models;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Order {
  private String ticker;
  private Timestamp timestamp;
  private int quantity;
  private double price;
  private String side;
  private String type;
  private double limit;
  private int filled;
  /**
   *
   owner:picsou
   timestamp:457696995
   ticker:LNRD
   quantity:10
   side:BUY
   type:LIMIT
   limit:100
   filled:5
   */
}
