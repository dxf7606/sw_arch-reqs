package org.rit.swen440.control;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.rit.swen440.dataLayer.Category;
import org.rit.swen440.dataLayer.Product;

import java.sql.*;
import java.util.*;

public class DataManager {
    private final static String DATABASE_URL = "jdbc:sqlite:online_ordering_system.sqlite3";
    protected static Dao<Category, String> categoryDao;
    protected static Dao<Product, String> productDao;

    public static void initializeDatabase() {
        try (ConnectionSource connectionSource = new JdbcConnectionSource(DATABASE_URL)) {
            TableUtils.createTableIfNotExists(connectionSource, Category.class);
            TableUtils.createTableIfNotExists(connectionSource, Product.class);

            categoryDao = DaoManager.createDao(connectionSource, Category.class);
            productDao = DaoManager.createDao(connectionSource, Product.class);
//            Product testProduct = new Product();
//            testProduct.setSkuCode(420);
//            productDao.create(testProduct);
//
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from products");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("sku"));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Set<Category> getCategories() {
        Set<Category> results = new HashSet<>();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categories");
            while (resultSet.next()) {
                Category category = new Category();
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                results.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static List<String> getProductNamesForCategory(String categoryName) {
        List<String> results = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE category LIKE ?");
            statement.setString(1, categoryName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(resultSet.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static Set<Product> getProductsForCategory(String categoryName) {
        Set<Product> results = new HashSet<>();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE category LIKE ?");
            statement.setString(1, categoryName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setSkuCode(resultSet.getInt("sku"));
                product.setItemCount(resultSet.getInt("item_count"));
                product.setThreshold(resultSet.getInt("threshold"));
                product.setReorderAmount(resultSet.getInt("reorder_amount"));
                product.setTitle(resultSet.getString("title"));
                product.setDescription(resultSet.getString("description"));
                product.setCost(resultSet.getBigDecimal("cost"));
                product.setCategory(resultSet.getString("category"));
                results.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
