package application.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Cart uses a HashMap to store a user's chosen products and their corresponding IDs.
 */
public class Cart {

    //PIV
    /*
     * 	<Product, number of Product being added to cart>
     */
    private final Map<Product, Integer> mpCart = new HashMap<>();

    //Constructors
    //Methods

    /**
     * Adds the specified product to the cart.
     *
     * @param product A Product object containing the added product's attributes.
     * @return product The Product object to confirm its addition to the cart.
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

    /**
     * Retrieves the entire cart.
     *
     * @return mpCart A HashMap containing all the products with their corresponding IDs.
     */
    public Map<Product, Integer> getCart() {
        return mpCart;
    }

    /**
     * Provides a textual representation of all of the cart's contents.
     */
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
