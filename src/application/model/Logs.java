package application.model;

import application.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Logs stores a list of all of the Kiosk's accounts.
 */
public class Logs {

    private final ArrayList<Account> alLogs = new ArrayList<>();    //list of all the accounts in file

    //Constructors

    /**
     * Loads the account data from the application's files.
     */
    public Logs() {
        //read from Accounts.txt file and load all Accounts
        Scanner accountsBuffer;
        try {
            accountsBuffer = new Scanner(new File("Accounts.txt"));
        } catch (FileNotFoundException e) {
            try {
                new FileOutputStream("Accounts.txt", false).close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("WARNING: NO ACCOUNTS AVAILABLE. You will not be able to log in.");
            return;
        }
        while (accountsBuffer.hasNext()) {
            String[] tempAccount = accountsBuffer.nextLine().split("[,]");
            //tempAccount[0] = id
            //tempAccount[1] = type
            //tempAccount[2] = username
            //tempAccount[3] = password
            Account accountLoad = new Account(Integer.parseInt(tempAccount[0]), Integer.parseInt(tempAccount[1]), tempAccount[2], tempAccount[3]);
            alLogs.add(accountLoad);
        }
    }

    //Methods

    /**
     * Retrieves the list of all of the Kiosk's accounts.
     *
     * @return alLogs An ArrayList containing all of the Kiosk's Accounts.
     */
    public ArrayList<Account> getAccounts() {
        return alLogs;
    }

    /**
     * Creates a new account based on user input. Updates the data in the application's files.
     *
     * @param id       The new Account's given ID.
     * @param type     The new Account's given type.
     * @param username The new Account's given username.
     * @param password The new Account's given password.
     * @return - Returns an Account object or null based on whether the Account was successfully created.
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

    /**
     * Verifies that a specified Account exists.
     *
     * @param username The specified Account's username.
     * @return - An Account object or null based on whether the Account was found.
     */
    public Account accountExists(String username) {
        for (Account aTemp : alLogs) {
            //check for matching username and password
            if (aTemp.getAccountUsername().equals(username))
                return aTemp;
        }
        return null;
    }

    /**
     * Provides a textual representation of the logs.
     */
    public String toString() {
        String setText = "";
        for (Account alLog : alLogs) {
            setText += alLog.toString();
        }
        return setText;
    }
}
