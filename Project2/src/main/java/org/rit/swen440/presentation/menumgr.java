package org.rit.swen440.presentation;

import org.rit.swen440.control.Controller;
import org.rit.swen440.dataLayer.Category;

import java.util.*;


public class menumgr {
    int currentLevel = 0;
    String currentCategoryName;
    String currentItemName;
    category currentCategory;
    item currentItem;
    private Controller controller;
    private HashMap<String, Integer> sessionPurchases = new HashMap<String, Integer>();

    public menumgr() {
        controller = new Controller(System.getProperty("fileSystemRoot"));
    }

    public boolean loadLevel(int level) {
//        System.out.println("Loading level:" + currentLevel);
        switch (currentLevel) {
            case -1:
                return true;
            case 0:
                Level0();
                break;
            case 1:
                Level1();
                break;
            case 2:
                Level2();
                break;
            default:
                System.out.println("Returning to main org.rit.swen440.presentation.menu");
                currentLevel = 0;
                Level0();
                break;
        }

        return false;
    }

    public void Level0() {
        menu m = new menu();
        List<String> categories = controller.getCategories();
        m.loadMenu(categories);
        m.addMenuItem("'q' to Quit");
        m.addMenuItem("'p' to view Session Purchases");
        System.out.println("The following org.rit.swen440.presentation.categories are available");
        m.printMenu();
        String result = "0";
        try {
            result = m.getSelection();
        } catch (Exception e) {
            result = "q";
        }
        if (Objects.equals(result, "q")) {
            currentLevel--;
        } else if (Objects.equals(result, "p")) {
            System.out.println("You have purchased:");
            for (String product : sessionPurchases.keySet()) {
                System.out.println(" - " + sessionPurchases.get(product) + " " + product + "(s)");
            }
            if (sessionPurchases.keySet().size() == 0) {
                System.out.println(" - NOTHING. BUY STUFF");
            }
            System.out.println("");
        } else {
            currentLevel++;
            int iSel = Integer.parseInt(result);

            currentCategoryName = categories.get(iSel);
            System.out.println("\nYour Selection was:" + currentCategoryName);
        }
    }

    public void Level1() {
        menu m = new menu();

        //items it = new items("orderSys/" + currentCategory.getName());

        // List<item> itemList = controller.getProducts(currentCategoryName);
        List<String> itemList = controller.getProducts(currentCategoryName);
        List<String> l = new ArrayList<>();
        System.out.println("");
        for (String itm : itemList)
            l.add(controller.getProductInformation(currentCategoryName, itm, Controller.PRODUCT_FIELD.NAME)
                    + "($" + controller.getProductInformation(currentCategoryName, itm, Controller.PRODUCT_FIELD.COST) + ")");

        m.loadMenu(l);
        m.addMenuItem("'q' to quit");
        System.out.println("The following items are available");
        m.printMenu();
        String result = m.getSelection();
        try {
            int iSel = Integer.parseInt(result);//Item  selected
            currentItemName = itemList.get(iSel);
            //currentItem = itemList.get(iSel);
            //Now read the file and print the org.rit.swen440.presentation.items in the catalog
            System.out.println("You want item from the catalog: " + currentItemName);
        } catch (Exception e) {
            result = "q";
        }
        if (Objects.equals(result, "q"))
            currentLevel--;
        else if (Objects.equals(result, "p")) {
            System.out.println("You have purchased:");
            for (String product : sessionPurchases.keySet()) {
                System.out.println(" - " + sessionPurchases.get(product) + " " + product + "(s)");
            }
            if (sessionPurchases.keySet().size() == 0) {
                System.out.println(" - NOTHING. BUY STUFF");
            }
            System.out.println("");
        } else {
            //currentLevel++;//Or keep at same level?
            OrderQty(currentCategoryName, currentItemName);
        }
    }


    public void Level2() {

    }

    public void OrderQty(String category, String item) {
        System.out.println("Please select a quantity");
        System.out.println(controller.getProductInformation(category, item, Controller.PRODUCT_FIELD.NAME) +
                " availability:" + controller.getProductInformation(category, item, Controller.PRODUCT_FIELD.INVENTORY));
        System.out.print(":");
        menu m = new menu();
        String result = m.getSelection();
        System.out.println("You ordered:" + result);
        if (sessionPurchases.containsKey(item)) {
            sessionPurchases.put(item, Integer.parseInt(result));
        } else {
            sessionPurchases.put(item, (sessionPurchases.get(item) == null ? 0 : sessionPurchases.get(item))
                    + Integer.parseInt(result));
        }
    }
}