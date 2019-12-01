package application.model;

import application.Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*		Inventory Class
 *
 * 		Purpose:
 *
 * 		Notes:
 *
 *
 */
public class Inventory {

    //PIV
    /*
     * Map<Product iID, Product stock count>
     */
    private final Map<Integer, Integer> mpStock = new HashMap<>();

    private final ArrayList<Product> alInventory = new ArrayList<>();

    //Constructors
    /*
     * Purpose:
     * 	Will be called on by the KioskController to load inventory
     * Parameters:
     * 	None
     * Returns:
     * 	void
     * Notes:
     * 	also loads stock
     */
    public Inventory() {
        //read from Products file
        try {
            Scanner productsBuffer = new Scanner(Main.class.getResourceAsStream("resources/Products.txt"));
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
    /*
     * Purpose:
     * 	Used to get products
     * Parameters:
     * 	None
     * Returns:
     * 	ArrayList of Products
     * Notes:
     *
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

    /*
     * Purpose:
     * 	Used to find a product in stock by id
     * Parameters:
     * 	I - Integer id							id number for the product being searched
     * Returns:
     * 	Product - 							if product with id is found
     * 	null - 								if product not found
     * Notes:
     * 	Used for stock Map
     */
    private Product searchByID(Integer id) {
        for (Product product : alInventory) {
            if (product.getId() == id)
                return product;
        }
        return null;
    }

    /*
     * Purpose:
     * 	Used to find a product in stock by name
     * Parameters:
     * 	I - Integer id							id number for the product being searched
     * Returns:
     * 	Product - 							if product with name is found
     * 	null - 								if product not found
     * Notes:
     * 	Used for stock Map
     */
    private Product searchByName(String name) {
        for (Product product : alInventory) {
            if (product.getName().equals(name))
                return product;
        }
        return null;
    }

    /*
     * Purpose:
     * 	Used to find products by hash-tag relevance search
     * Parameters:
     * 	I - String search					user entered search
     * Returns:
     * 	ArrayList of products with relevance to the search
     * 	null - 								if no products found
     * Notes:
     * 	Uses Product description to find relevance to search
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

    /*
     * Purpose:
     * 	Used to find product in inventory by name
     * Parameters:
     * 	I - String name						name of the product being searched
     * Returns:
     * 	Product - 							if product with name is found
     * 	null - 								if product not found
     * Notes:
     * 	Used for inventory List
     */
    public Product decrement(Integer id) {
        int dec = mpStock.get(id) - 1;
        mpStock.put(id, dec);
        return searchByID(id);
    }

    /*
     * Purpose:
     * 	Adds a new items to the inventory and stock of the Kiosk
     * Parameters:
     * 	I - String name							name of Product
     *  I - String description					description of Product
     *  I - Integer id							id number for Product
     *  I - int stockCount						number being added to Kiosk
     *  I - double price						cost of Product
     * Returns:
     * 	Integer object of the id for the Product
     * Notes:
     * 	Should only be usable by the Admin
     * 	changes files
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
            writer = new BufferedWriter(new FileWriter(Main.class.getResource("resources/Products.txt").getFile()));
            writer.append("\n");
            writer.append(str);
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return id;
    }

    /*
     * Purpose:
     * 	Used by Employees (or Admins) to add more stock to an exsiting item
     * Parameters:
     * 	Integer id						id number for Product
     * 	int stockAdd					number of stock to add for Product
     * Returns:
     * 	void
     * Notes:
     * 	doens't create a new product if trying to add stock to non existent id
     * changes files
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
            Scanner productsBuffer = new Scanner(Main.class.getResourceAsStream("resources/Products.txt"));
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
            Scanner fileBuffer = new Scanner(Main.class.getResourceAsStream("resources/Products.txt"));
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
                writer = new BufferedWriter(new FileWriter(Main.class.getResource("resources/Products.txt").getFile()));
                writer.write(strFile.toString());
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //toString
    public String toString() {
        String printInventory = "";
        for (Product product : alInventory) {
            printInventory += "Product # " + product.getId() + "\t" + product.toString() + "\tIn Stock: " + getStockCount(product.getId()) + "\n\n";
        }
        return printInventory;
    }
}
