package application.model;

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
    private final String pszName;
    private final String pszDescription;
    private final Integer iID;
    private final double fPrice;

    //Constructors

    /**
     * @param name        The new Product's given name.
     * @param description The new Product's given description.
     * @param id          The new Product's given ID.
     * @param price       The new Product's given price.
     */
    public Product(String name, String description, int id, double price) {
        pszName = name;
        pszDescription = description;
        iID = id;
        fPrice = price;
    }

    //Methods

    /**
     * Retrieves the Product's name.
     *
     * @return pszName A String containing the Product's name.
     */
    public String getName() {
        return pszName;
    }

    /**
     * Retrieves the Product's description.
     *
     * @return pszDescription A String containing the Product's description.
     */
    public String getDescription() {
        return pszDescription;
    }

    /**
     * Retrieves the Product's price.
     *
     * @return fPrice A double containing the Product's price.
     */
    public double getPrice() {
        return fPrice;
    }

    /**
     * Retrieves the Product's ID.
     *
     * @return iID An integer containing the Product's ID.
     */
    public int getId() {
        return iID;
    }

    /**
     * Provides a textual representation of the Product's attributes.
     */
    public String toString() {
        String tempProductString = "";
        tempProductString += pszName;
        tempProductString += "\nDescription:  ";
        String[] tempDescription = pszDescription.split("\\s+");
        for (String s : tempDescription) {
            tempProductString += " -" + s;
        }
        tempProductString += "\nPrice: $" + fPrice;
        return tempProductString;
    }
}
