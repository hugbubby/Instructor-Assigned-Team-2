package application.model;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

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
	/*
	 * Purpose:
	 * 	Loads Product
	 * Parameters:
	 * 	I - String name							name of Product
	 *  I - String description					description of Product
	 *  I - int id							id number for Product
	 *  I - int stockCount						number being added to Kiosk
	 *  I - double price						cost of Product
	 * Notes:
	 * 	
	 */
	public Product(String name, String description, int id, double price){
		pszName = name;
		pszDescription = description;
		iID = id;
		fPrice = price;
	}
	
	//Methods
	/*
	 * Purpose:
	 * 	Get the name of a Product
	 * Parameters:
	 * 	None
	 * Returns:
	 * 	String name of the Product
	 * Notes:
	 * 	
	 */
	public String getName(){
		return pszName;
	}
	
	/*
	 * Purpose:
	 * 	Get the name of a Product
	 * Parameters:
	 * 	None
	 * Returns:
	 * 	String name of the Product
	 * Notes:
	 * 	
	 */
	public String getDescription(){
		return pszDescription;
	}
	
	/*
	 * Purpose:
	 * 	Get the price of a Product
	 * Parameters:
	 * 	None
	 * Returns:
	 * 	double price of the Product
	 * Notes:
	 * 	
	 */
	public double getPrice(){
		return fPrice;
	}
	
	/*
	 * Purpose:
	 * 	Get the id of a Product
	 * Parameters:
	 * 	None
	 * Returns:
	 * 	int ID of the Product
	 * Notes:
	 * 	
	 */
	public int getId(){
		return iID;
	}
	
	//toString
	public String toString(){
		String tempProductString = "";
		tempProductString += pszName;
		tempProductString += "\nDescription:  ";
		String[] tempDescription = pszDescription.split("\\s+");
		for(int i = 0; i < tempDescription.length;i++){
			tempProductString += " -"+tempDescription[i];
		}
		tempProductString += "\nPrice: $"+fPrice;
		return tempProductString;
	}
}
