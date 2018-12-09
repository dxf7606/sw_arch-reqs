package org.rit.swen440;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.rit.swen440.dataLayer.Category;
import org.rit.swen440.dataLayer.Product;
import org.rit.swen440.presentation.menumgr;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class menutest {
    public static void main(String[] args) {
        final String databaseUrl = "jdbc:sqlite:online_ordering_system.sqlite3";
        try(ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {
            TableUtils.createTableIfNotExists(connectionSource, Category.class);
            TableUtils.createTableIfNotExists(connectionSource, Product.class);

            Dao<Product, String> productDao = DaoManager.createDao(connectionSource, Product.class);
            Product testProduct = new Product();
            testProduct.setSkuCode(420);
            productDao.create(testProduct);

            Connection connection = DriverManager.getConnection(databaseUrl);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from products");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("sku"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.getProperties().load(new FileInputStream("orderSys.properties"));
            System.out.println("Hello");
            menumgr mgr = new menumgr();
            int currentLevel = 0;
            boolean done = false;
            do {
                done = mgr.loadLevel(currentLevel);
            } while (!done);

            System.out.println("Thank you for shopping at Hippolyta.com!");

        } catch (IOException e) {
            System.err.println("orderSys.properties not found.");
        }

    }
}