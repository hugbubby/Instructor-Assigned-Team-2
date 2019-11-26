package application.model;

import java.util.HashMap;
import java.util.Map;

/*		Cart Class
 * 
 * 		Purpose:
 * 
 * 		Notes:
 * 
 * 
 */
public class Cart {

	//PIV
	/*
	 * 	<Product, number of Product being added to cart>
	 */
	private Map<Product, Integer> mpCart = new HashMap<Product, Integer>();
	
	//Constructors
	//Methods
	/*
	 * Purpose:
	 * 	User adds a Product to their cart for purcahse later
	 * Parameters:
	 * 	I - Integer id							id number for the Product
	 * Returns:
	 * 	Product being added to cart
	 * Notes:
	 * 	
	 */
	public Product addToCart(Integer id){
		return null;
	}
	
	/*
	 * Purpose:
	 * 	User removes a Product from their cart
	 * Parameters:
	 * 	I - Integer id							id number for the Product
	 * Returns:
	 * 	Product being removed from cart
	 * Notes:
	 * 	
	 */
	public Product removeFromCart(Integer id){
		return null;
	}
	//toString
}
