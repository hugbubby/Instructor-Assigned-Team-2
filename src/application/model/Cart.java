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
    private final Map<Product, Integer> mpCart = new HashMap<>();

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
    public void addToCart(Product product) {
        //add product to cart and increment count in cart
        if (mpCart.containsKey(product)) {
            Integer count = mpCart.get(product);
            mpCart.put(product, count + 1);
        } else {
            mpCart.putIfAbsent(product, 1);
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
    public Map<Product, Integer> getCart() {
        return mpCart;
    }

    //toString
    public String toString() {
        StringBuilder strCart = new StringBuilder();
        Set<Product> keys = mpCart.keySet();
        Iterator<Product> iterator = keys.iterator();
        int subCost = 0;
        while (iterator.hasNext()) {
            Product currentProduct = iterator.next();
            int count = mpCart.get(currentProduct);
            strCart.append(count).append(" : ").append(currentProduct.getName()).append("\n").append("Cost: ").append(currentProduct.getPrice() * count).append("\n");
            subCost += currentProduct.getPrice() * count;
        }
        double tax = Math.round(subCost * .0625 * 100);
        tax /= 100.0;
        double totalCost = Math.round((subCost * .0625) + subCost * 100);
        totalCost /= 100.0;

        strCart.append("\n\n\n      Sub Cost: ").append(subCost);
        strCart.append("\n+ %6.25 Tax: ").append(tax);
        strCart.append("\n+  Total Cost: ").append(totalCost);
        return strCart.toString();
    }
}
