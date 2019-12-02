package application.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Inventory stores the data for all of the Kiosk's products.
 */
public class Inventory {

    //PIV
    /*
     * Map<Product iID, Product stock count>
     */
    private final Map<Integer, Integer> mpStock = new HashMap<>();

    private final ArrayList<Product> alInventory = new ArrayList<>();

    //Constructors

    /**
     * Loads the inventory's data from the application's files.
     */
    public Inventory() {
        //read from Products file
        try {
            Scanner productsBuffer = new Scanner(new File("Products.txt"));
            while (productsBuffer.hasNext()) {
                String[] tempProduct = productsBuffer.nextLine().split("[,]");
                //tempProduct[0] = Name
                //tempProduct[1] = description
                //tempProduct[2] = id
                //tempProduct[3] = stockCount
                //tempProduct[4] = price

                //add inventory
                Product pTemp = new Product(tempProduct[0], tempProduct[1], Integer.parseInt(tempProduct[2]), Double.parseDouble(tempProduct[4]));
                alInventory.add(pTemp);
                //add stock
                mpStock.putIfAbsent(Integer.valueOf(tempProduct[2]), Integer.valueOf(tempProduct[3]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Methods

    /**
     * Retrieves a list of all of the Kiosk's products.
     *
     * @return alInventory An ArrayList containing all of the Kiosk's products.
     */
    public ArrayList<Product> getInventory() {
        return alInventory;
    }

    /*
     * Purpose:
     * 	Used to find a product stockCount
     * Parameters:
     * 	I - Integer id							id number for the product being searched
     * Returns:
     * 	int number of that product in stock
     * Notes:
     * 	Used for stock Map
     */
    public Integer getStockCount(Integer id) {
        return mpStock.get(id);
    }

    /**
     * Searches the inventory based on the provided product ID.
     *
     * @param id The given product ID.
     * @return - A Product object or null based on whether the specified product was found.
     */
    private Product searchByID(Integer id) {
        for (Product product : alInventory) {
            if (product.getId() == id)
                return product;
        }
        return null;
    }

    /**
     * Searches the inventory based on the provided product name.
     *
     * @param name The given product name.
     * @return - A Product object or null based on whether the specified product was found.
     */
    private Product searchByName(String name) {
        for (Product product : alInventory) {
            if (product.getName().equals(name))
                return product;
        }
        return null;
    }

    /**
     * Searches the inventory for products based on user search input.
     *
     * @param search A String containing user search input.
     * @return searchPop An ArrayList containing all the products that matched the user's search input.
     */
    public ArrayList<Product> searchByHash(String search) {
        ArrayList<Product> searchPop = new ArrayList<>();
        //search by every Product description (description will always contain name of Product written by the Admin)
        for (Product product : alInventory) {
            String[] tempDescription = product.getDescription().split("\\s+");
            for (String s : tempDescription) {
                if (s.equals(search)) {
                    searchPop.add(product);
                    break;
                }
            }
        }
        return searchPop;
    }

    /**
     * Used to retrieve a product based on a given product ID.
     *
     * @param id The given product ID.
     * @return - A Product object or null based on whether the specified product was successfully retrieved.
     */
    public Product decrement(Integer id) {
        int dec = mpStock.get(id) - 1;
        mpStock.put(id, dec);
        return searchByID(id);
    }

    /**
     * Adds new items to the Kiosk's inventory based on the input provided by an Admin user.
     *
     * @param name        The new Product's given name.
     * @param description The new Product's given description.
     * @param id          The new Product's given ID.
     * @param stockCount  The new Product's given stock amount.
     * @param price       The new Product's given price.
     * @return - An Integer object or null if the new product was successfully added to the inventory.
     */
    public Integer addNewProduct(String name, String description, Integer id, int stockCount, double price) {
        //make sure product doesn't already exist
        if (searchByName(name) != null)
            return null;
        //String name, String description, int id, double price
        //setup new product
        Product tempAdd = new Product(name, description, id, price);
        //add it to both the inventory and the stock
        alInventory.add(tempAdd);
        mpStock.put(id, stockCount);

        //write new product into the files
        //setup string for product file
        String str = name + "," + description + "," + id.toString() + "," + stockCount + "," + price + "0";
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(new File("Products.txt")));
            writer.append("\n");
            writer.append(str);
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return id;
    }

    /**
     * Increases the stock amount of a Product specified by an Admin or Employee user.
     *
     * @param id       The ID of the specified Product.
     * @param stockAdd The number of stock to add for the Product.
     */
    public void restock(Integer id, Integer stockAdd) {
        //add stock add amount to current stock count num
        Integer stockNew = mpStock.get(id) + stockAdd;
        //set new map value for stock
        mpStock.put(id, stockNew);

        //write to files new stock value
        //first locate which line Product is on by using its id
        //read from Products file
        int line = 1;
        try {
            Scanner productsBuffer = new Scanner(new File("Products.txt"));
            while (line < id) {
                productsBuffer.nextLine();
                line++;
            }
            //prductsBuffer is now on the line where product is
            String[] tempProduct = productsBuffer.nextLine().split("[,]");
            //tempProduct[0] = Name
            //tempProduct[1] = description
            //tempProduct[2] = id
            //tempProduct[3] = stockCount
            //tempProduct[4] = price

            //change Products stockCount num to new number
            tempProduct[3] = stockNew.toString();
            //set up new string for Product at line id
            StringBuilder strLine = new StringBuilder();
            for (int i = 0; i < tempProduct.length - 1; i++) {
                strLine.append(tempProduct[i]).append(",");
            }
            strLine.append(tempProduct[4]).append("\n");

            //setup new string for entire file with changed line
            StringBuilder strFile = new StringBuilder();
            line = 1;
            Scanner fileBuffer = new Scanner(new File("Products.txt"));
            while (fileBuffer.hasNext()) {
                //change the line with Product to new strLine
                if (line == id) {
                    strFile.append(strLine);
                    fileBuffer.nextLine();
                } else {
                    strFile.append(fileBuffer.nextLine());
                    if (!(fileBuffer.hasNext()))
                        break;
                    else
                        strFile.append("\n");
                }
                line++;
            }
            //write new string file into Products file
            BufferedWriter writer;
            try {
                writer = new BufferedWriter(new FileWriter(new File("Products.txt")));
                writer.write(strFile.toString());
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Provides a textual representation of the entire Inventory.
     */
    public String toString() {
        String printInventory = "";
        for (Product product : alInventory) {
            printInventory += "Product # " + product.getId() + "\t" + product.toString() + "\tIn Stock: " + getStockCount(product.getId()) + "\n\n";
        }
        return printInventory;
    }
}
