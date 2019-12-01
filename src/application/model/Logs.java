package application.model;

import application.Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*		Logs Class
 *
 * 		Purpose:
 *
 * 		Notes:
 *
 *
 */
public class Logs {

    //PIV
    private final ArrayList<Account> alLogs = new ArrayList<>();    //list of all the accounts in file

    //Constructors
    public Logs() {
        //read from Accounts.txt file and load all Accounts
        try {
            Scanner accountsBuffer = new Scanner(Main.class.getResourceAsStream("resources/Accounts.txt"));
            while (accountsBuffer.hasNext()) {
                String[] tempAccount = accountsBuffer.nextLine().split("[,]");
                //tempAccount[0] = id
                //tempAccount[1] = type
                //tempAccount[2] = username
                //tempAccount[3] = password
                Account accountLoad = new Account(Integer.parseInt(tempAccount[0]), Integer.parseInt(tempAccount[1]), tempAccount[2], tempAccount[3]);
                alLogs.add(accountLoad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Methods
    /*
     * Purpose:
     * 	Used by Admins to create new employees
     * 	Used by LoginController for new customers
     * Parameters:
     *
     * Returns:
     * 	Account being added to system
     * Notes:
     * 	changes files
     */
    public ArrayList<Account> getAccounts() {
        return alLogs;
    }

    /*
     * Purpose:
     * 	Used by Admins to create new employees
     * 	Used by LoginController for new customers
     * Parameters:
     *
     * Returns:
     * 	Account being added to system
     * Notes:
     * 	changes files
     */
    public Account addNewAccount(int id, int type, String username, String password) {
        //make sure account doesn't already exist
        if (accountExists(username) != null)
            return null;
        //int id, int type, String username, String password
        //create new Account Object
        Account accountTemp = new Account(id, type, username, password);

        //add it to the alLogs
        alLogs.add(accountTemp);
        //write new account into the files
        //setup string for account file
        String str = id + "," + type + "," + username + "," + password;
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(Main.class.getResource("resources/Accounts.txt").getFile()));
            writer.append("\n");
            writer.append(str);
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return accountTemp;
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
    public Account accountExists(String username) {
        for (Account aTemp : alLogs) {
            //check for matching username and password
            if (aTemp.getAccountUsername().equals(username))
                return aTemp;
        }
        return null;
    }

    //toString
    public String toString() {
        String setText = "";
        for (Account alLog : alLogs) {
            setText += alLog.toString();
        }
        return setText;
    }
}
