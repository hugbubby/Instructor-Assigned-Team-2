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
	private int iID;
	private int iType;
	
	private String pszUsername;
	private String pszPassword;
	
	//Constructors
	public Account() {}
	public Account(int id, int type, String username, String password) {
		iID = id;
		iType = type;
		pszUsername = username;
		pszPassword = password;
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
	public Account addNewAccount(){
		return null;
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
	public String getAccountUsername(){
		return pszUsername;
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
		if(this.pszPassword.equals(password))
			return true;
		return false;
	}
	//toString
	public String toString(){
		return "ID - " + iID + "\nType - " + iType + "\nUsername - " + pszUsername + "\nPassword - " + pszPassword;
	}
}
