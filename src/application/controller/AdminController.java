package application.controller;

import application.model.Account;
import application.model.Inventory;
import application.model.Logs;
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

/*		Admin Class
 *
 * 		Purpose:
 *
 * 		Notes:
 *
 *
 */
public class AdminController {

    //PIV
    private final Logs Logs = new Logs();
    private final Inventory IKiosk = new Inventory();
    //textArea
    @FXML javafx.scene.control.TextArea textAreaAdmin;
    //testFields
    @FXML javafx.scene.control.TextField textFieldAdmin0;
    @FXML javafx.scene.control.TextField textFieldAdmin1;
    @FXML javafx.scene.control.TextField textFieldAdmin2;
    @FXML javafx.scene.control.TextField textFieldAdmin3;
    //buttons
    @FXML javafx.scene.control.Button buttonAdminAddProduct;
    @FXML javafx.scene.control.Button buttonAdminAddAccount;
    //radio buttons
    @FXML javafx.scene.control.RadioButton rbProducts;
    @FXML javafx.scene.control.RadioButton rbAccounts;
    //labels
    @FXML javafx.scene.control.Label labelCustomer;
    @FXML javafx.scene.control.Label labelEmployee;
    @FXML javafx.scene.control.Label labelAdmin;
    @FXML javafx.scene.control.Label labelAdmin0;
    @FXML javafx.scene.control.Label labelAdmin1;
    @FXML javafx.scene.control.Label labelAdmin2;
    @FXML javafx.scene.control.Label labelAdmin3;

    //Methods
    /*
     * Purpose:
     * 	Handles event for when admin kiosk button is clicked (loads Products or Accounts onto screen)
     * Parameters:
     * 	I - Event event							button clicked
     * Returns:
     * 	void
     * Notes:
     *  Will load Kiosk
     */
    public void buttonAdminKioskClicked(Event event) {
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

    /*
     * Purpose:
     * 	Handles event for when admin employee button is clicked (loads Products or Accounts onto screen)
     * Parameters:
     * 	I - Event event							button clicked
     * Returns:
     * 	void
     * Notes:
     *  Will load Employee
     */
    public void buttonAdminEmployeeClicked(Event event) {
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

    /*
     * Purpose:
     * 	Handles event for when admin load button is clicked (loads Products or Accounts onto screen)
     * Parameters:
     * 	I - Event event							button clicked
     * Returns:
     * 	void
     * Notes:
     *  Will load textArea based on radio buttons
     */
    public void buttonAdminLoadClicked(Event event) {
        //check what admin wants displayed
        //Products
        if (rbProducts.selectedProperty().getValue()) {
            //hide un-needed account information
            labelCustomer.visibleProperty().set(false);
            labelEmployee.visibleProperty().set(false);
            labelAdmin.visibleProperty().set(false);
            //populate text area with Products
            textAreaAdmin.setText(IKiosk.toString());
        }
        //Accounts
        else if (rbAccounts.selectedProperty().getValue()) {
            //display account information
            labelCustomer.visibleProperty().set(true);
            labelEmployee.visibleProperty().set(true);
            labelAdmin.visibleProperty().set(true);

            String setText = Logs.toString();
            textAreaAdmin.setText(setText);
        } else {
            textAreaAdmin.setText("Please Select a Display Option");
        }
    }

    /*
     * Purpose:
     * 	Handles event for when admin add product menu button is clicked (loads Products or Accounts onto screen)
     * Parameters:
     * 	I - Event event							button clicked
     * Returns:
     * 	void
     * Notes:
     *
     */
    public void menuButtonAddProductClicked(Event event) {
        //set add button to visible and opposing to invisible
        buttonAdminAddProduct.visibleProperty().set(true);
        buttonAdminAddAccount.visibleProperty().set(false);
        //set text fields for adding product to visible
        textFieldAdmin0.visibleProperty().set(true);
        textFieldAdmin1.visibleProperty().set(true);
        textFieldAdmin2.visibleProperty().set(true);
        textFieldAdmin3.visibleProperty().set(true);
        //set labels for adding product to visible
        labelAdmin0.visibleProperty().set(true);
        labelAdmin1.visibleProperty().set(true);
        labelAdmin2.visibleProperty().set(true);
        labelAdmin3.visibleProperty().set(true);
        //account helper labels to false
        labelCustomer.visibleProperty().set(false);
        labelEmployee.visibleProperty().set(false);
        labelAdmin.visibleProperty().set(false);
        //set labels text
        labelAdmin0.setText("Product Name:");
        labelAdmin1.setText("Product Description:");
        labelAdmin2.setText("Product Price:");
        labelAdmin3.setText("Number in Stock:");
        //clear textFeilds
        textFieldAdmin0.setText("");
        textFieldAdmin1.setText("");
        textFieldAdmin2.setText("");
        textFieldAdmin3.setText("");
    }

    /*
     * Purpose:
     * 	Handles event for when admin add account menu button is clicked (loads Products or Accounts onto screen)
     * Parameters:
     * 	I - Event event							button clicked
     * Returns:
     * 	void
     * Notes:
     *
     */
    public void menuButtonAddAccountClicked(Event event) {
        //set add button to visible and opposing to invisible
        buttonAdminAddAccount.visibleProperty().set(true);
        buttonAdminAddProduct.visibleProperty().set(false);
        //set text fields for adding product to visible
        textFieldAdmin0.visibleProperty().set(true);
        textFieldAdmin1.visibleProperty().set(true);
        textFieldAdmin2.visibleProperty().set(true);
        //only need three textFields
        textFieldAdmin3.visibleProperty().set(false);
        //set labels for adding product to visible
        labelAdmin0.visibleProperty().set(true);
        labelAdmin1.visibleProperty().set(true);
        labelAdmin2.visibleProperty().set(true);
        //only need three labels
        labelAdmin3.visibleProperty().set(false);
        //account helper labels to true
        labelCustomer.visibleProperty().set(true);
        labelEmployee.visibleProperty().set(true);
        labelAdmin.visibleProperty().set(true);
        //set labels text
        labelAdmin0.setText("Account Type:");
        labelAdmin1.setText("Account Username:");
        labelAdmin2.setText("Account Password:");
        //clear textFeilds
        textFieldAdmin0.setText("");
        textFieldAdmin1.setText("");
        textFieldAdmin2.setText("");
        textFieldAdmin3.setText("");
    }

    /*
     * Purpose:
     * 	Handles event for when admin add product button is clicked (loads Products or Accounts onto screen)
     * Parameters:
     * 	I - Event event							button clicked
     * Returns:
     * 	void
     * Notes:
     *
     */
    public void buttonAdminAddProductClicked(Event event) {
        String setText = "";
        Double price = null;
        Integer count = null;
        //clear area on each new add run
        textAreaAdmin.setText(setText);
        //Make sure none of the product fields are empty when adding new product
        if (!(textFieldAdmin0.getText().equals("") || textFieldAdmin1.getText().equals("") || textFieldAdmin2.getText().equals("") || textFieldAdmin3.getText().equals(""))) {
            //check to make sure that Price is a double
            try {
                price = Double.parseDouble(textFieldAdmin2.getText());
            } catch (NumberFormatException e) {
                textAreaAdmin.setText("Error: Price Must Be a Number\n\n Unable to create Product\n");
                return;
            }
            //check to make sure that count is an int
            try {
                count = Integer.parseInt(textFieldAdmin3.getText());
            } catch (NumberFormatException e) {
                textAreaAdmin.setText("Error: Number in Stock Must Be a Number\n\n Unable to create Product\n");
                return;
            }
            //all fields are clear then create new product
            //get id (id = Inventory.size() + 1)
            ArrayList<Product> temp = IKiosk.getInventory();
            Integer iNum = IKiosk.addNewProduct(textFieldAdmin0.getText(), textFieldAdmin1.getText(), temp.size() + 1, count, price);
            if (iNum == null) {
                textAreaAdmin.setText("Failed to Add : " + textFieldAdmin0.getText() + "\n\nProduct Already Exists");
                return;
            }
            textAreaAdmin.setText("Succesfully Added Product # " + iNum);
            textFieldAdmin0.setText("");
            textFieldAdmin1.setText("");
            textFieldAdmin2.setText("");
            textFieldAdmin3.setText("");
        } else { //if one is empty dont try to add new product and display errors
            if (textFieldAdmin0.getText().equals(""))
                setText += "Error : Product Name Empty\n";
            if (textFieldAdmin1.getText().equals(""))
                setText += "Error : Product Description Empty\n";
            if (textFieldAdmin2.getText().equals(""))
                setText += "Error : Product Price Empty\n";
            if (textFieldAdmin3.getText().equals(""))
                setText += "Error : Number in Stock Empty\n";
            setText += "\n Unable to create Product\n";
            textAreaAdmin.setText(setText);
        }
    }

    /*
     * Purpose:
     * 	Handles event for when admin add account button is clicked (loads Products or Accounts onto screen)
     * Parameters:
     * 	I - Event event							button clicked
     * Returns:
     * 	void
     * Notes:
     *
     */
    public void buttonAdminAddAccountClicked(Event event) {
        String setText = "";
        Integer type = null;
        //clear area on each new add run
        textAreaAdmin.setText(setText);
        //Make sure none of the product fields are empty when adding new product
        if (!(textFieldAdmin0.getText().equals("") || textFieldAdmin1.getText().equals("") || textFieldAdmin2.getText().equals(""))) {
            //check to make sure that Type is an int
            try {
                type = Integer.parseInt(textFieldAdmin0.getText());
            } catch (NumberFormatException e) {
                textAreaAdmin.setText("Error: Type Must Be a Number\n\n Unable to create Product\n");
                return;
            }
            //all fields are clear then create new product
            //get id (id = Logs.size() + 1)
            Account iNum = Logs.addNewAccount(Logs.getAccounts().size() + 1, type, textFieldAdmin1.getText(), textFieldAdmin2.getText());
            if (iNum == null) {
                textAreaAdmin.setText("Failed to Add Account : " + textFieldAdmin1.getText() + "\n\n Already Exists");
                return;
            }
            textAreaAdmin.setText("Successfully Added Account # " + iNum.getAccountId());
            textFieldAdmin0.setText("");
            textFieldAdmin1.setText("");
            textFieldAdmin2.setText("");
            textFieldAdmin3.setText("");
        } else { //if one is empty dont try to add new product and display errors
            if (textFieldAdmin0.getText().equals(""))
                setText += "Error : Account Type Empty\n";
            if (textFieldAdmin1.getText().equals(""))
                setText += "Error : Account Username Empty\n";
            if (textFieldAdmin2.getText().equals(""))
                setText += "Error : Account Password Empty\n";
            setText += "\n Unable to create Account\n";
            textAreaAdmin.setText(setText);
        }
    }

    /*
     * Purpose:
     * 	Handles event for when admin logout button is clicked (loads Products or Accounts onto screen)
     * Parameters:
     * 	I - Event event							button clicked
     * Returns:
     * 	void
     * Notes:
     *  Returns user to login page
     */
    public void buttonAdminLogoutClicked(Event event) {
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
}







