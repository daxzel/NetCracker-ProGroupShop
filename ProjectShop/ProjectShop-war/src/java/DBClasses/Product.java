/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBClasses;

import java.sql.ResultSet;

/**
 *
 * @author ололо
 */
public class Product implements ProductInterface {
private int idProduct;
private String Description;
private String name;
private double price;

private Catalog catalog;

    public Product(ResultSet rs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int getId() {
       return idProduct;
    }


}
