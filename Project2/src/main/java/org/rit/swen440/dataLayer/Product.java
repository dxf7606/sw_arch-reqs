package org.rit.swen440.dataLayer;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.nio.file.Path;

/**
 * A record of each product type
 */
@Data
@Entity
@Table(name="products")
public class Product {
  @Setter(AccessLevel.PRIVATE)
  private boolean updated = false;
  @Transient
  private Path path;

  @Id
  @Column(name="sku")
  private int skuCode;
  @Column(name="item_count")
  private int itemCount;
  @Column
  private int threshold;
  @Column(name="reorder_amount")
  private int reorderAmount;
  @Column
  private String title;
  @Column
  private String description;
  @Column
  private BigDecimal cost;
  @Column
  private String category;

  /**
   * Check to see if we have enough of this item for an order
   *
   * @param amount Number of items being ordered
   * @return true if enough stock
   */
  public boolean canOrder(int amount) {
    return (itemCount - amount >= 0);
  }

  /**
   * Place an order, decrement the available itemCount
   *
   * @param amount being ordered
   * @return if order was successfully processed
   */
  public boolean order(int amount) {
    if (canOrder(amount)) {
      itemCount = itemCount - amount;
      setUpdated(true);  // Need to store the updated product information

      // TODO:  add stock management functionality
      return true;
    }

    return false;
  }
}
