package application.controller;

import application.model.Inventory;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class that handles all events associated with the Employee page.
 */
public class EmployeeController {

    //PIV
    private final Inventory IKiosk = new Inventory();
    //buttons
    @FXML javafx.scene.control.Button buttonEmployeeAdminPage;
    //textAreas
    @FXML javafx.scene.control.TextArea textAreaEmployee;
    //textFields
    @FXML javafx.scene.control.TextField textFieldEmployeeProductId;
    @FXML javafx.scene.control.TextField textFieldEmployeeStockAmount;

    //Methods

    /**
     * Loads the content of the Employee page.
     */
    public void initialize() {
        //populate text area with Products
        textAreaEmployee.setText(IKiosk.toString());

        //if Admin load Admin Page button
        if (LoginController.loger.getAccountType() >= 2)
            buttonEmployeeAdminPage.visibleProperty().set(true);
    }

    /**
     * Switches the current view from the Employee to the Admin page.
     *
     * @param event the Admin button is clicked.
     */
    public void buttonEmployeeAdminPageClicked(Event event) {
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
     * Switched the current view from the Employee page to the Kiosk page.
     *
     * @param event The Kiosk button is clicked.
     */
    public void buttonEmployeeKioskPageClicked(Event event) {
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
     * Logs out the employee; switches the view to the Login page.
     *
     * @param event The logout button is clicked.
     */
    public void buttonEmployeeLogoutClicked(Event event) {
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
     * Adds to the current stock of a specific product based on user input.
     *
     * @param event The Restock button is clicked.
     */
    public void buttonRestockClicked(Event event) {
        Integer amount = null;
        Integer id = null;
        //chech if product id is null
        if (textFieldEmployeeProductId.getText().equals("")) {
            textAreaEmployee.setText("Error: Missing Product Id\n\n Unable to Add Stock\n");
            return;
        }
        //chech if stock add amount is null
        if (textFieldEmployeeStockAmount.getText().equals("")) {
            textAreaEmployee.setText("Error: Stock Add Amount\n\n Unable to Add Stock\n");
            return;
        }
        //both fields pass with entered items
        //chech if product id is a number
        try {
            id = Integer.parseInt(textFieldEmployeeProductId.getText());
        } catch (NumberFormatException e) {
            textAreaEmployee.setText("Error: Product Number Must Be a Number\n\n Unable to Add Stock\n");
            return;
        }
        //chech if stock add amount is number
        try {
            amount = Integer.parseInt(textFieldEmployeeStockAmount.getText());
        } catch (NumberFormatException e) {
            textAreaEmployee.setText("Error: Stock Add Amount Must Be a Number\n\n Unable to Add Stock\n");
            return;
        }
        //check if amount is negative or zero
        if (amount < 1) {
            textAreaEmployee.setText("Error: Stock Add Amount Must Be Greater Than Zero\n\n Unable to Add Stock\n");
            return;
        }
        //check if Product exists
        Integer count = IKiosk.getStockCount(id);
        if (count == null) {
            textAreaEmployee.setText("Error: Product # " + id + " Doesn't Exist\n\n Unable to Add Stock\n");
            return;
        }
        //everything passed
        //Add stock amount to stock count for Product with id
        IKiosk.restock(id, amount);
        //clear textFields
        textFieldEmployeeStockAmount.setText("");
        textFieldEmployeeProductId.setText("");
        //reload products
        textAreaEmployee.setText(IKiosk.toString());
    }
}