package org.rit.swen440.dataLayer;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.nio.file.Path;

/**
 * A record of each product type
 */
@Data
@DatabaseTable(tableName = "products")
public class Product {
  @Setter(AccessLevel.PRIVATE)
  private boolean updated = false;

  private Path path;

  @DatabaseField(columnName = "sku", id = true, canBeNull = false)
  private int skuCode;
  @DatabaseField(columnName = "item_count")
  private int itemCount;
  @DatabaseField
  private int threshold;
  @DatabaseField(columnName = "reorder_amount")
  private int reorderAmount;
  @DatabaseField
  private String title;
  @DatabaseField
  private String description;
  @DatabaseField(dataType = DataType.BIG_DECIMAL_NUMERIC)
  private BigDecimal cost;

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
