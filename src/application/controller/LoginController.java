package application.controller;

import java.awt.Label;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.stream.FileImageInputStream;

import javafx.scene.Node;
import application.model.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*		Login Class
 * 
 * 		Purpose:
 * 
 * 		Notes:
 * 
 * 
 */
public class LoginController {

	//PIV
	private ArrayList<Account> alLogs = new ArrayList<Account>();	//list of all the accounts in file

	//buttons
	@FXML javafx.scene.control.Button buttonCreateNewAccountPrompt;
	@FXML javafx.scene.control.Button buttonCreateNewAccount;
	@FXML javafx.scene.control.Button buttonLogin;
	@FXML javafx.scene.control.Button buttonBack;

	//textFields
	@FXML javafx.scene.control.TextField textFieldUsername;
	@FXML javafx.scene.control.PasswordField passFieldPassword;

	//textArea
	@FXML javafx.scene.control.Label labelLoginError;


	//Constructors
	/*
	 * Purpose:
	 * 	Runs on open and loads Account logs
	 * Notes:
	 * 	loads all Accounts from file
	 */
	public LoginController() {
		//read from Accounts.txt file and load all Accounts
		try {
			Scanner accountsBuffer = new Scanner(new File("Accounts.txt"));
			while(accountsBuffer.hasNext()){
				String[] tempAccount = accountsBuffer.nextLine().split("[,]");
				//tempAccount[0] = id
				//tempAccount[1] = type
				//tempAccount[2] = username
				//tempAccount[3] = password
				Account accountLoad = new Account(Integer.parseInt(tempAccount[0]),Integer.parseInt(tempAccount[1]),tempAccount[2],tempAccount[3]);
				alLogs.add(accountLoad);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
	public void loginClicked(Event event){
		if(textFieldUsername.getText().toString().equals(null) || passFieldPassword.getText().toString().equals(null)){
			labelLoginError.setText("Error: Invalid Username and Password");
		}
		else{
			String username = textFieldUsername.getText().toString().toLowerCase();
			String password = passFieldPassword.getText().toString().toLowerCase();
			Account loger = accountExists(username);
			//make sure user Account exists
			if(loger != null){
				//login
				//check if password matches for Account
				if(loger.authenticatePassword(password)){

					Parent kiosk = null;
					try {
						kiosk = FXMLLoader.load(getClass().getResource("Kiosk.fxml"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					Scene sScene = new Scene(kiosk);
					Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

					window.setScene(sScene);
					window.show();
				}
				else{
					labelLoginError.setText("Error: Invalid Username and Password");
				}
			}
			else{ //account doesn't exist
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
	public void createNewAccountClicked(Event event){

		String username = textFieldUsername.getText().toString().toLowerCase();
		String password = passFieldPassword.getText().toString().toLowerCase();
		Account loger = accountExists(username);
		//make sure Account doesn't already exist
		if(loger == null){
			//create new account and login

		}
		else{ //account does exist
			//show user error
			labelLoginError.setText("Error: Account Already Exists");
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
	public void createNewAccountPromptClicked(Event event){
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
	public void buttonBackClicked(Event event){
		//make login prompt buttons visible
		buttonLogin.visibleProperty().setValue(true);
		buttonCreateNewAccountPrompt.visibleProperty().setValue(true);
		//make creatAccount button and back button invisible 
		buttonCreateNewAccount.visibleProperty().setValue(false);
		buttonBack.visibleProperty().setValue(false);
	}

	/*
	 * Purpose:
	 * 	Checks to see is Account exists
	 * Parameters:
	 * 	I - String username						username of Account
	 *  I - String password						passwrod of Account
	 * Returns:
	 * 	Account is exists
	 * 	null is Account doesn't exist
	 * Notes:
	 * 	Used by login and creatNewAccoutn
	 */
	public Account accountExists(String username){
		for(int i = 0; i < alLogs.size();i++){
			//check for matching username and password
			Account aTemp = alLogs.get(i);
			if(aTemp.getAccountUsername().equals(username))
				return aTemp;
		}
		return null;
	}
}
