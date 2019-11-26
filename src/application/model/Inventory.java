package application.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*		Inventory Class
 * 
 * 		Purpose:
 * 
 * 		Notes:
 * 
 * 
 */
public class Inventory {

	//PIV
	/*
	 * Map<Product iID, Product stock count>
	 */
	private Map<Integer, Integer> mpStock = new HashMap<Integer, Integer>();
	
	private ArrayList<String> alInventory = new ArrayList<String>();
	//Constructors
	//Methods
	/*
	 * Purpose:
	 * 	Will be called on by the KioskController to load inventory
	 * Parameters:
	 * 	
	 * Returns:
	 * 	
	 * Notes:
	 * 	
	 */
	public void fillInventory(){
		
	}
	
	/*
	 * Purpose:
	 * 	Used to find a product in stock by id
	 * Parameters:
	 * 	I - int id							id number for the product being searched
	 * Returns:
	 * 	Product - 							if product with name is found
	 * 	null - 								if product not found
	 * Notes:
	 * 	Used for stock Map
	 */
	public Product searchByID(Integer id){
		return null;
	}
	
	/*
	 * Purpose:
	 * 	Used to find product in inventory by name
	 * Parameters:
	 * 	I - String name						name of the product being searched
	 * Returns:
	 * 	Product - 							if product with name is found
	 * 	null - 								if product not found
	 * Notes:
	 * 	Used for inventory List
	 */
	public Product searchByName(String name){
		return null;
	}
	
	/*
	 * Purpose:
	 * 	USed to find products by hash-tag relevance search
	 * Parameters:
	 * 	I - String search					user entered search
	 * Returns:
	 * 	ArrayList of products with relevance to the search
	 * 	null - 								if no products found
	 * Notes:
	 * 	Uses Product description to find relevance to search
	 */
	public ArrayList<Product> searchByHash(String search){
		return null;
	}
	//toString
}
