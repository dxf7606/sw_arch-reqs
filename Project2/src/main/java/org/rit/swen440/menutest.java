package org.rit.swen440;

import org.rit.swen440.control.DataManager;
import org.rit.swen440.presentation.menumgr;

import java.io.FileInputStream;
import java.io.IOException;

public class menutest {
    public static void main(String[] args) {
        final String databaseUrl = "jdbc:sqlite:online_ordering_system.sqlite3";
        DataManager.initializeDatabase();

        try {
            System.getProperties().load(new FileInputStream("orderSys.properties"));
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