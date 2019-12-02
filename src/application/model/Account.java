package application.model;

/**
 * Each account has an ID, type, username, and password.
 *
 * @author
 */
public class Account {

    //PIV
    private final int iID;
    private final int iType;

    private final String pszUsername;
    private final String pszPassword;

    //Constructors

    /**
     * @param id       An integer containing the new account's given ID.
     * @param type     An integer containing the new account's given type.
     * @param username A String containing the new account's given username.
     * @param password A String containing the new account's given password.
     */
    public Account(int id, int type, String username, String password) {
        iID = id;
        iType = type;
        pszUsername = username;
        pszPassword = password;
    }

    //Methods

    /**
     * Retrieves an account's username.
     *
     * @return pszUsername A String containing the account's username.
     */
    public String getAccountUsername() {
        return pszUsername;
    }

    /**
     * Retrieves the account's type.
     *
     * @return iType An integer containing the account's type.
     */
    public int getAccountType() {
        return iType;
    }

    /**
     * Retrieves the account's ID.
     *
     * @return iID An integer containing the account's ID.
     */
    public int getAccountId() {
        return iID;
    }

    /**
     * Verifies that the given password matches that of the account.
     *
     * @param password The password provided by user input.
     * @return - A boolean value indicating whether the given password was correct.
     */
    public boolean authenticatePassword(String password) {
        return this.pszPassword.equals(password);
    }

    /**
     * Provides a textual representation of an account's data.
     */
    public String toString() {
        return "ID - " + iID + "\nType - " + iType + "\nUsername - " + pszUsername + "\nPassword - " + pszPassword + "\n\n";
    }
}
