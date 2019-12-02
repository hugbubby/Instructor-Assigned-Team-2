package application.controller;

import application.model.Cart;
import application.model.Inventory;
import application.model.Product;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Controller class that handles all the events associated with the Kiosk page.
 *
 * @author
 */
public class KioskController {

    //PIV
    private final Inventory IKiosk = new Inventory();                        //Inventory Object to hold kiosk inventory
    private final ArrayList<Product> alInventory = IKiosk.getInventory(); //Arraylist of Products in Inventory
    //buttons
    @FXML javafx.scene.control.Button buttonNextPage;
    @FXML javafx.scene.control.Button buttonPreviousPage;
    @FXML javafx.scene.control.Button buttonKioskAdminPage;
    @FXML javafx.scene.control.Button buttonKioskEmployeePage;
    @FXML javafx.scene.control.Button buttonAddToCart0;
    @FXML javafx.scene.control.Button buttonAddToCart1;
    @FXML javafx.scene.control.Button buttonAddToCart2;
    @FXML javafx.scene.control.Button buttonAddToCart3;
    @FXML javafx.scene.control.Button buttonAddToCart4;
    @FXML javafx.scene.control.Button buttonAddToCart5;
    @FXML javafx.scene.control.Button buttonClearCart;
    @FXML javafx.scene.control.Button buttonBuy;
    //textFields
    @FXML javafx.scene.control.TextField textFieldKioskSearch;
    //textAreas
    @FXML javafx.scene.control.TextArea textAreaKiosk0;
    @FXML javafx.scene.control.TextArea textAreaKiosk1;
    @FXML javafx.scene.control.TextArea textAreaKiosk2;
    @FXML javafx.scene.control.TextArea textAreaKiosk3;
    @FXML javafx.scene.control.TextArea textAreaKiosk4;
    @FXML javafx.scene.control.TextArea textAreaKiosk5;
    @FXML javafx.scene.control.TextArea textAreaCart;
    private Cart cart = new Cart();                                    //Users current Cart
    private ArrayList<Product> alResults;                            //Arraylist of Products produce from search results
    private boolean bSearch;                                        //used to tell if search was used
    private int iPosition;                                            //used to tell the position of display of Products on screen (i.e. which page)

    //Constructors

    /**
     * Loads all product information for the Kiosk page.
     * Loads the first page of products.
     */
    public void initialize() {
        bSearch = false;
        iPosition = 0;
        //load first page of products
        loadProducts(alInventory);

        //if Employee or Admin load Employee Page button
        if (LoginController.loger.getAccountType() >= 1) {
            buttonKioskEmployeePage.visibleProperty().set(true);
        }
        //if Admin load Admin Page button
        if (LoginController.loger.getAccountType() >= 2)
            buttonKioskAdminPage.visibleProperty().set(true);
    }

    //Methods

    /**
     * Switches the current view from the Kiosk page to the Admin page.
     *
     * @param event The "Admin" button is clicked.
     */
    public void buttonKioskAdminPageClicked(Event event) {
        //load Admin.fxml
        Parent kiosk = null;
        try {
            kiosk = FXMLLoader.load(getClass().getResource("../view/Admin.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene sScene = new Scene(kiosk);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(sScene);
        window.show();
    }

    /**
     * Switches the current view from the Kiosk page to the Employee page.
     *
     * @param event The Employee button is clicked.
     */
    public void buttonKioskEmployeePageClicked(Event event) {
        //load Employee.fxml
        Parent kiosk = null;
        try {
            kiosk = FXMLLoader.load(getClass().getResource("../view/Employee.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene sScene = new Scene(kiosk);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(sScene);
        window.show();
    }

    /**
     * Logs out the user; switches the current view from the Kiosk page to the Login page.
     *
     * @param event
     */
    public void buttonKioskLogoutClicked(Event event) {
        //load Login.fxml
        Parent kiosk = null;
        try {
            kiosk = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene sScene = new Scene(kiosk);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(sScene);
        window.show();
    }

    /**
     * Takes the user back to the first page of products.
     *
     * @param event The Home button is clicked.
     */
    public void buttonKioskHomeClicked(Event event) {
        bSearch = false;
        //search is null set to first page
        buttonPreviousPage.visibleProperty().setValue(false);
        buttonNextPage.visibleProperty().setValue(true);
        iPosition = 0;//page 0
        loadProducts(alInventory);
    }

    /**
     * Takes the user to the next page of products.
     *
     * @param event The Next button is clicked.
     */
    public void buttonNextPageClicked(Event event) {
        //make previous page button visible
        buttonPreviousPage.visibleProperty().setValue(true);
        loadProducts(alInventory);
    }

    /**
     * Directs the user to the previous page of products.
     */
    public void buttonPreviousPageClicked() {
        //make next page button visible
        buttonNextPage.visibleProperty().setValue(true);
        //set iPostion back 12 (previous 6 products)
        iPosition -= 12;
        if (iPosition <= 0) {//iPosition cant be negative
            iPosition = 0;
            //set previous button to invisible since we are on the first page
            buttonPreviousPage.visibleProperty().setValue(false);
        }
        loadProducts(alInventory);
    }

    /**
     * Performs a search for certain products in the inventory based on user input provided in the "search" text area.
     *
     * @param event The Search button is clicked.
     */
    public void buttonKioskSearchClicked(Event event) {
        if (textFieldKioskSearch.getText().equals("")) {
            bSearch = false;
            //search is null set to first page
            buttonPreviousPage.visibleProperty().setValue(false);
            buttonNextPage.visibleProperty().setValue(true);
            iPosition = 0;//page 0
            loadProducts(alInventory);
        } else {
            bSearch = true;
            iPosition = 0;//page 0
            alResults = IKiosk.searchByHash(textFieldKioskSearch.getText().toLowerCase());
            loadProducts(alResults);
        }
    }

    /**
     * Adds a specific item to the user's cart.
     * Decrements the stock for that specific product.
     *
     * @param event The "Add to Cart" button is clicked.
     */
    public void buttonAddToCartClicked(Event event) {
        //set clear and buy buttons to visible since items are in cart
        buttonBuy.visibleProperty().setValue(true);
        buttonClearCart.visibleProperty().setValue(true);
        //find out which button was pressed
        String button = (event.getSource().toString().substring(10, 26));
        int location = Integer.parseInt(button.charAt(button.length() - 1) + "");

        //add product located at that button to cart
        //find out which product is currently located at that button then load is into cart and decrement stock
        if (location == 0) {
            //display that the stock count is decreasing
            //add product to cart
            //remove product from stock for each click/addition to cart
            if (bSearch) {
                cart.addToCart(IKiosk.decrement(alResults.get(iPosition - 6).getId()));
                iPosition -= 6;//load same page
                loadProducts(alResults);
            } else {
                cart.addToCart(IKiosk.decrement(alInventory.get(iPosition - 6).getId()));
                iPosition -= 6;//load same page
                loadProducts(alInventory);
            }
        }
        if (location == 1) {
            //display that the stock count is decreasing
            //add product to cart
            //remove product from stock for each click/addition to cart
            if (bSearch) {
                cart.addToCart(IKiosk.decrement(alResults.get(iPosition - 5).getId()));
                iPosition -= 6;//load same page
                loadProducts(alResults);
            } else {
                cart.addToCart(IKiosk.decrement(alInventory.get(iPosition - 5).getId()));
                iPosition -= 6;//load same page
                loadProducts(alInventory);
            }
        }
        if (location == 2) {
            //display that the stock count is decreasing
            //add product to cart
            //remove product from stock for each click/addition to cart
            if (bSearch) {
                cart.addToCart(IKiosk.decrement(alResults.get(iPosition - 4).getId()));
                iPosition -= 6;//load same page
                loadProducts(alResults);
            } else {
                cart.addToCart(IKiosk.decrement(alInventory.get(iPosition - 4).getId()));
                iPosition -= 6;//load same page
                loadProducts(alInventory);
            }
        }
        if (location == 3) {
            //display that the stock count is decreasing
            //add product to cart
            //remove product from stock for each click/addition to cart
            if (bSearch) {
                cart.addToCart(IKiosk.decrement(alResults.get(iPosition - 3).getId()));
                iPosition -= 6;//load same page
                loadProducts(alResults);
            } else {
                cart.addToCart(IKiosk.decrement(alInventory.get(iPosition - 3).getId()));
                iPosition -= 6;//load same page
                loadProducts(alInventory);
            }
        }
        if (location == 4) {
            //display that the stock count is decreasing
            //add product to cart
            //remove product from stock for each click/addition to cart
            if (bSearch) {
                cart.addToCart(IKiosk.decrement(alResults.get(iPosition - 2).getId()));
                iPosition -= 6;//load same page
                loadProducts(alResults);
            } else {
                cart.addToCart(IKiosk.decrement(alInventory.get(iPosition - 2).getId()));
                iPosition -= 6;//load same page
                loadProducts(alInventory);
            }
        }
        if (location == 5) {
            //display that the stock count is decreasing
            //add product to cart
            //remove product from stock for each click/addition to cart
            if (bSearch) {
                cart.addToCart(IKiosk.decrement(alResults.get(iPosition - 1).getId()));
                iPosition -= 6;//load same page
                loadProducts(alResults);
            } else {
                cart.addToCart(IKiosk.decrement(alInventory.get(iPosition - 1).getId()));
                iPosition -= 6;//load same page
                loadProducts(alInventory);
            }
        }
        textAreaCart.setText(cart.toString());
    }

    /**
     * Clears the user's cart of all products had been added to it.
     *
     * @param event The "Clear Cart" button is clicked.
     */
    public void buttonClearCartClicked(Event event) {
        //simply reload Kiosk
        //load Kiosk.fxml
        Parent kiosk = null;
        try {
            kiosk = FXMLLoader.load(getClass().getResource("../view/Kiosk.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene sScene = new Scene(kiosk);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(sScene);
        window.show();
    }

    /**
     * Handles the event that user buys the products currently in their cart.
     * The user's cart is cleared and the Kiosk's inventory is updated accordingly.
     *
     * @param event The Buy button is clicked.
     */
    public void buttonBuyClicked(Event event) {
        //handle inventory
        //will only need to edit the stock counts in files
        //cheat buy restocking nothing to overwrite permentant file stock info with our temporary stock info handled by add to cart
        Map<Product, Integer> mpCart = cart.getCart();
        Set<Product> keys = mpCart.keySet();

        //do this for each product in cart
        for (Product tempProduct : keys) {
            IKiosk.restock(tempProduct.getId(), 0);
        }
        //take total price
        String[] totalCost = textAreaCart.getText().split("\\s+");

        //clear cart
        cart = new Cart();

        //thank customer for purchase
        textAreaCart.setText("Thank You For Your Purchase!\n Amount Paid: " + totalCost[totalCost.length - 1]);
    }

    /**
     * Loads a specific page of products.
     *
     * @param inventory An ArrayList containing all products.
     */
    private void loadProducts(ArrayList<Product> inventory) {
        boolean end = false;
        //neutral set all buttons and textAreas to visible
        buttonAddToCart0.visibleProperty().setValue(true);
        buttonAddToCart1.visibleProperty().setValue(true);
        buttonAddToCart2.visibleProperty().setValue(true);
        buttonAddToCart3.visibleProperty().setValue(true);
        buttonAddToCart4.visibleProperty().setValue(true);
        buttonAddToCart5.visibleProperty().setValue(true);

        textAreaKiosk0.visibleProperty().setValue(true);
        textAreaKiosk1.visibleProperty().setValue(true);
        textAreaKiosk2.visibleProperty().setValue(true);
        textAreaKiosk3.visibleProperty().setValue(true);
        textAreaKiosk4.visibleProperty().setValue(true);
        textAreaKiosk5.visibleProperty().setValue(true);
        //will set invisible later based on inventory

        //Product 0
        if (iPosition < inventory.size() && !end) {
            //check if out of stock
            if (IKiosk.getStockCount(inventory.get(iPosition).getId()) <= 0) {
                buttonAddToCart0.visibleProperty().setValue(false);//remove add to cart button if out of stock
            }
            textAreaKiosk0.setText(printProduct(inventory));
        }
        iPosition++;
        if (iPosition >= inventory.size() && !end) {
            //set buttons and textAreas to invisible
            buttonAddToCart1.visibleProperty().setValue(false);
            buttonAddToCart2.visibleProperty().setValue(false);
            buttonAddToCart3.visibleProperty().setValue(false);
            buttonAddToCart4.visibleProperty().setValue(false);
            buttonAddToCart5.visibleProperty().setValue(false);

            textAreaKiosk1.visibleProperty().setValue(false);
            textAreaKiosk2.visibleProperty().setValue(false);
            textAreaKiosk3.visibleProperty().setValue(false);
            textAreaKiosk4.visibleProperty().setValue(false);
            textAreaKiosk5.visibleProperty().setValue(false);
            end = true;
        }
        //Product 1
        if (iPosition < inventory.size()) {
            //check if out of stock
            if (IKiosk.getStockCount(inventory.get(iPosition).getId()) <= 0) {
                buttonAddToCart1.visibleProperty().setValue(false);
            }
            textAreaKiosk1.setText(printProduct(inventory));
        }
        iPosition++;
        if (iPosition >= inventory.size() && !end) {
            //set buttons and textAreas to invisible
            buttonAddToCart2.visibleProperty().setValue(false);
            buttonAddToCart3.visibleProperty().setValue(false);
            buttonAddToCart4.visibleProperty().setValue(false);
            buttonAddToCart5.visibleProperty().setValue(false);

            textAreaKiosk2.visibleProperty().setValue(false);
            textAreaKiosk3.visibleProperty().setValue(false);
            textAreaKiosk4.visibleProperty().setValue(false);
            textAreaKiosk5.visibleProperty().setValue(false);
            end = true;
        }
        //Product 2
        if (iPosition < inventory.size()) {
            if (IKiosk.getStockCount(inventory.get(iPosition).getId()) <= 0) {
                buttonAddToCart2.visibleProperty().setValue(false);
            }
            textAreaKiosk2.setText(printProduct(inventory));
        }
        iPosition++;
        if (iPosition >= inventory.size() && !end) {
            //set buttons and textAreas to invisible
            buttonAddToCart3.visibleProperty().setValue(false);
            buttonAddToCart4.visibleProperty().setValue(false);
            buttonAddToCart5.visibleProperty().setValue(false);

            textAreaKiosk3.visibleProperty().setValue(false);
            textAreaKiosk4.visibleProperty().setValue(false);
            textAreaKiosk5.visibleProperty().setValue(false);
            end = true;
        }
        //Product 3
        if (iPosition < inventory.size()) {
            if (IKiosk.getStockCount(inventory.get(iPosition).getId()) <= 0) {
                buttonAddToCart3.visibleProperty().setValue(false);
            }
            textAreaKiosk3.setText(printProduct(inventory));
        }
        iPosition++;
        if (iPosition >= inventory.size() && !end) {
            //set buttons and textAreas to invisible
            buttonAddToCart4.visibleProperty().setValue(false);
            buttonAddToCart5.visibleProperty().setValue(false);

            textAreaKiosk4.visibleProperty().setValue(false);
            textAreaKiosk5.visibleProperty().setValue(false);
            end = true;
        }
        //Product 4
        if (iPosition < inventory.size()) {
            if (IKiosk.getStockCount(inventory.get(iPosition).getId()) <= 0) {
                buttonAddToCart4.visibleProperty().setValue(false);
            }
            textAreaKiosk4.setText(printProduct(inventory));
        }
        iPosition++;
        if (iPosition >= inventory.size() && !end) {
            //set buttons and textAreas to invisible
            buttonAddToCart5.visibleProperty().setValue(false);

            textAreaKiosk5.visibleProperty().setValue(false);
            end = true;
        }
        //Product 5
        if (iPosition < inventory.size()) {
            if (IKiosk.getStockCount(inventory.get(iPosition).getId()) <= 0) {
                buttonAddToCart5.visibleProperty().setValue(false);
            }
            textAreaKiosk5.setText(printProduct(inventory));
        }
        iPosition++;
        //if the end of the products is reached then set next page but to invisible
        //i.e. last page is met
        if (end) {
            buttonNextPage.visibleProperty().setValue(false);
        }
    }

    /**
     * Provides textual representation of the inventory.
     *
     * @param inventory An ArrayList containing all products.
     * @return - A String representation of all products with their corresponding information.
     */
    private String printProduct(ArrayList<Product> inventory) {
        return inventory.get(iPosition).toString() + "\nIn Stock: " + IKiosk.getStockCount(inventory.get(iPosition).getId());
    }
}
