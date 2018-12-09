package org.rit.swen440.control;

import org.rit.swen440.dataLayer.Category;
import org.rit.swen440.dataLayer.Product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Controls access to data, on start-up scans directories and builds internal
 * representation of categories and items within each category.  Isolates the
 * categories and products from information on the underlying file system.
 */
public class Controller {
  private Path dirPath;
  private Set<Category> categories = new HashSet<>();

  public  enum PRODUCT_FIELD {
    NAME,
    DESCRIPTION,
    COST,
    INVENTORY
  };

  public Controller(String directory) {
    loadCategories(directory);
  }

  /**
   * Load the Category information
   *
   * @param directory root directory
   */
  private void loadCategories(String directory) {
    categories = DataManager.getCategories();
  }

  /**
   * Get a list of all category names
   *
   * @return list of categories
   */
  public List<String> getCategories() {

    return categories.stream()
        .map(Category::getName)
        .collect(Collectors.toList());
  }

  /**
   * Return a list of Products based on the provided category.
   *
   * @param categoryName Name of Category to use
   * @return List of Products in the category
   */
  public List<String> getProducts(String categoryName) {
    return DataManager.getProductNamesForCategory(categoryName);
  }


  public String getProductInformation(String category, String product, PRODUCT_FIELD field) {
   Optional<Product> selectedProduct = getProduct(category, product);
   switch (field) {
     case NAME:
       return selectedProduct.map(Product::getTitle).orElse(null);

     case DESCRIPTION:
       return selectedProduct.map(Product::getDescription).orElse(null);

     case COST:
       return selectedProduct.map(p -> String.format("%.2f", p.getCost())).orElse(null);

     case INVENTORY:
       return selectedProduct.map(p -> String.valueOf(p.getItemCount())).orElse(null);
   }

   return null;
  }

  /**
   * Get the category that matches the provided category name
   *
   * @param name
   * @return Category, if present
   */
  public Optional<Category> findCategory(String name) {
    return categories.stream()
        .filter(c -> c.getName().equalsIgnoreCase(name))
        .findFirst();
  }

  /* -----------------------------------
   *
   * Private Methods
   */

  private Optional<Product> getProduct(String category, String product) {
    return findCategory(category).map(c -> c.findProduct(product)).orElse(null);
  }
}
