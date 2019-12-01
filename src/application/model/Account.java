package application.model;

/*		Account Class
 *
 * 		Purpose:
 *
 * 		Notes:
 *
 *
 */
public class Account {

    //PIV
    private final int iID;
    private final int iType;

    private final String pszUsername;
    private final String pszPassword;

    //Constructors
    public Account(int id, int type, String username, String password) {
        iID = id;
        iType = type;
        pszUsername = username;
        pszPassword = password;
    }

    //Methods
    /*
     * Purpose:
     * 	used to get the name of the Account
     * Parameters:
     * 	None
     * Returns:
     * 	String username of the Account
     * Notes:
     *
     */
    public String getAccountUsername() {
        return pszUsername;
    }

    /*
     * Purpose:
     * 	used to get the name of the Account
     * Parameters:
     * 	None
     * Returns:
     * 	String username of the Account
     * Notes:
     *
     */
    public int getAccountType() {
        return iType;
    }

    /*
     * Purpose:
     * 	used to get the name of the Account
     * Parameters:
     * 	None
     * Returns:
     * 	String username of the Account
     * Notes:
     *
     */
    public int getAccountId() {
        return iID;
    }

    /*
     * Purpose:
     * 	Used to check if password is correct during login
     * Parameters:
     * 	I - String password							password entered by the user (not accounts password if wrong)
     * Returns:
     * 	true if password is correct
     *  false if password is incorrect
     * Notes:
     *
     */
    public boolean authenticatePassword(String password) {
        return this.pszPassword.equals(password);
    }

    //toString
    public String toString() {
        return "ID - " + iID + "\nType - " + iType + "\nUsername - " + pszUsername + "\nPassword - " + pszPassword + "\n\n";
    }
}
