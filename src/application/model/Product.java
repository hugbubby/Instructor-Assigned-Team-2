package application.model;

import javafx.scene.image.Image;

/*		Product Class
 * 
 * 		Purpose:
 * 
 * 		Notes:
 * 
 * 
 */
public class Product {

	//PIV
	private String pszName;
	private String pszDescription;

	private Integer iID;

	private double fPrice;

	//Constructors
	//Methods
	/*
	 * Purpose:
	 * 	Adds a new items to the inventory and stock of the Kiosk
	 * Parameters:
	 * 	I - String name							name of Product
	 *  I - String description					description of Product
	 *  I - Integer id							id number for Product
	 *  I - int stockCount						number being added to Kiosk
	 *  I - double price						cost of Product
	 * Returns:
	 * 	Integer object of the id for the Product
	 * Notes:
	 * 	Should only be usable by the Admin
	 * 	changes files
	 */
	public Integer addInventory(String name, String description, Integer id,
			int stockCount, double price)
	{
		return null;
	}
	//toString
}
