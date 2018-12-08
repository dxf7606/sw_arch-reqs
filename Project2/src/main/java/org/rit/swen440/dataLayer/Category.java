package org.rit.swen440.dataLayer;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * A product category supported by the Order System
 */
@Data
@Entity
@Table(name="categories")
public class Category {
  @Id
  private String name;
  private String description;
  @Transient
  private Set<Product> products = new HashSet<>();

  public Optional<Product> findProduct(String name) {
    return products.stream()
        .filter(p -> p.getTitle().equalsIgnoreCase(name))
        .findFirst();
  }
}
