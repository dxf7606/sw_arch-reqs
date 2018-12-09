package org.rit.swen440.control;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.rit.swen440.dataLayer.Category;
import org.rit.swen440.dataLayer.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataManager {
    private final static String DATABASE_URL = "jdbc:sqlite:online_ordering_system.sqlite3";
    protected static Dao<Category, String> categoryDao;
    protected static Dao<Product, String> productDao;

    public static void initializeDatabaseConnection() {
        try(ConnectionSource connectionSource = new JdbcConnectionSource(DATABASE_URL)) {
            TableUtils.createTableIfNotExists(connectionSource, Category.class);
            TableUtils.createTableIfNotExists(connectionSource, Product.class);

            categoryDao = DaoManager.createDao(connectionSource, Category.class);
            productDao = DaoManager.createDao(connectionSource, Product.class);
//            Product testProduct = new Product();
//            testProduct.setSkuCode(420);
//            productDao.create(testProduct);
//
//            Connection connection = DriverManager.getConnection(DATABASE_URL);
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from products");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("sku"));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
