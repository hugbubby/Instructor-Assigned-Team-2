package application.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
	public Product addToCart(Product product){
		//add product to cart and increment count in cart
		if(mpCart.containsKey(product)){
			Integer count = mpCart.get(product);
			mpCart.put(product, count+1);
			return product;
		}
		else{
			mpCart.putIfAbsent(product, 1);
			return product;
		}
	}
	
	/*
	 * Purpose:
	 * 	Used by the buy function to handle all items in cart
	 * Parameters:
	 * 	none
	 * Returns:
	 * 	Map<Product, Integer>					all items in cart
	 * Notes:
	 * 	used by buy only
	 */
	public Map<Product, Integer> getCart(){
		return mpCart;
	}
	//toString
	public String toString(){
		String strCart = "";
		Set<Product> keys = mpCart.keySet();
		Iterator<Product> iterator = keys.iterator();
		int subCost = 0;
		while(iterator.hasNext()){
			Product currentProduct = iterator.next();
			int count = mpCart.get(currentProduct);
			strCart += count +" : "+ currentProduct.getName() +"\n"+"Cost: "+ currentProduct.getPrice()*count + "\n";
			subCost += currentProduct.getPrice()*count;
		}
		double tax = Math.round(subCost*.0625 * 100);
		tax /= 100.0;
		double totalCost = Math.round((subCost*.0625) + subCost * 100);
		totalCost /= 100.0;
		
		strCart += "\n\n\n      Sub Cost: " + subCost;
		strCart += "\n+ %6.25 Tax: " + tax;
		strCart += "\n+  Total Cost: " + totalCost;
		return strCart;
	}
}
