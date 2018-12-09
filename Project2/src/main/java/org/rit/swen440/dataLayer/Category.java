package org.rit.swen440.dataLayer;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * A product category supported by the Order System
 */
@Data
@DatabaseTable(tableName = "categories")
public class Category {

  @DatabaseField
  private String name;
  @DatabaseField
  private String description;

  private Set<Product> products = new HashSet<>();

  public Optional<Product> findProduct(String name) {
    return products.stream()
        .filter(p -> p.getTitle().equalsIgnoreCase(name))
        .findFirst();
  }
}
