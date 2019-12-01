package application.controller;

import application.model.Account;
import application.model.Logs;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*		Login Class
 *
 * 		Purpose:
 *
 * 		Notes:
 *
 *
 */
public class LoginController {

    public static Account loger;                //used to tell who is currently logged in
    //PIV
    private final Logs Logs = new Logs();
    //buttons
    @FXML javafx.scene.control.Button buttonCreateNewAccountPrompt;
    @FXML javafx.scene.control.Button buttonCreateNewAccount;
    @FXML javafx.scene.control.Button buttonLogin;
    @FXML javafx.scene.control.Button buttonBack;
    //textFields
    @FXML javafx.scene.control.TextField textFieldUsername;
    @FXML javafx.scene.control.PasswordField passFieldPassword;
    //labels
    @FXML javafx.scene.control.Label labelLoginError;


    //Constructors
    /*
     * Purpose:
     *
     * Notes:
     *
     */
    public LoginController() {
    }

    //Methods
    /*
     * Purpose:
     * 	Handles event for when login button is clicked (loads Accounts)
     * Parameters:
     * 	I - Event event							button clicked
     * Returns:
     * 	void
     * Notes:
     * 	Logs user in (will load Kiosk)
     */
    public void loginClicked(Event event) {
        if (textFieldUsername.getText().equals("") || passFieldPassword.getText().equals("")) {
            labelLoginError.setText("Error: Invalid Username and Password");
        } else {
            String username = textFieldUsername.getText().toLowerCase();
            String password = passFieldPassword.getText().toLowerCase();
            loger = Logs.accountExists(username);
            //make sure user Account exists
            if (loger != null) {
                //login kiosk
                //check if password matches for Account
                if (loger.authenticatePassword(password)) {

                    //Check if Customer Logged in
                    if (loger.getAccountType() == 0) {
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
                    //Check if Employee Logged in
                    else if (loger.getAccountType() == 1) {
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
                    //Check if Admin Logged in
                    else if (loger.getAccountType() == 2) {
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
                } else {
                    labelLoginError.setText("Error: Invalid Username and Password");
                }
            } else { //account doesn't exist
                //show user error
                labelLoginError.setText("Error: Invalid Username and Password");
            }
        }
    }

    /*
     * Purpose:
     * 	Creation of new Account for a customer (loads Accounts)
     * Parameters:
     * 	I - Event event							button clicked
     * Returns:
     * 	void
     * Notes:
     * 	Will automatically login after creation
     * changes files
     */
    public void createNewAccountClicked(Event event) {

        if (textFieldUsername.getText().equals("") || passFieldPassword.getText().equals("")) {
            labelLoginError.setText("Error: Invalid Username and Password");
        } else {
            String username = textFieldUsername.getText().toLowerCase();
            String password = passFieldPassword.getText().toLowerCase();
            loger = Logs.accountExists(username);
            //make sure Account doesn't already exist
            if (loger == null) {
                //create new customer(0) account (add to files)
                loger = Logs.addNewAccount(Logs.getAccounts().size() + 1, 0, username, password);

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
            } else { //account does exist
                //show user error
                labelLoginError.setText("Error: Account Already Exists");
            }
        }
    }

    /*
     * Purpose:
     * 	Prompt for the user if they are new
     * Parameters:
     * 	I - Event event							button clicked
     * Returns:
     * 	void
     * Notes:
     * 	Handles view to show account creation is user is new
     */
    public void createNewAccountPromptClicked(Event event) {
        //make login prompt buttons invisible
        buttonLogin.visibleProperty().setValue(false);
        buttonCreateNewAccountPrompt.visibleProperty().setValue(false);
        //make creatAccount button and back button visible
        buttonCreateNewAccount.visibleProperty().setValue(true);
        buttonBack.visibleProperty().setValue(true);
    }

    /*
     * Purpose:
     * 	back to login page
     * Parameters:
     * 	I - Event event							button clicked
     * Returns:
     * 	void
     * Notes:
     * 	Used in case user accidently presses Create New Account
     */
    public void buttonBackClicked(Event event) {
        //make login prompt buttons visible
        buttonLogin.visibleProperty().setValue(true);
        buttonCreateNewAccountPrompt.visibleProperty().setValue(true);
        //make creatAccount button and back button invisible
        buttonCreateNewAccount.visibleProperty().setValue(false);
        buttonBack.visibleProperty().setValue(false);
    }

    /*
     * Purpose:
     * 	Checks to see what current id number is at
     * Parameters:
     * 	None
     * Returns:
     * 	int current id number
     * Notes:
     * 	Used to create new Account with id + 1
     */
    public int getIDCurrentNumberOfAccounts() {
        int num = 0;
        try {
            Scanner accountsBuffer = new Scanner(new File("Accounts.txt"));
            while (accountsBuffer.hasNext()) {
                accountsBuffer.next();
                num++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return num;
    }
}
