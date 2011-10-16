/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBClasses;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ололо
 */
public class Product implements ProductInterface {
private int idProduct;
private String description;
private int idCatalog;
private String name;
private double price;

private Catalog catalog;

    public Product(ResultSet rs) throws SQLException {
        setDescription(rs.getString(1));
        setId(rs.getInt(2));
        setIdCatalog(rs.getInt(3));
        setName(rs.getString(4));
        setPrice(rs.getDouble(5));
}

    public int getIdCatalog() {
       return idCatalog;
    }
    public void setIdCatalog(int nid){
       idCatalog  = nid;
    }
    @Override
    public int getId() {
       return idProduct;
    }
    public void setId(int nid){
       idProduct  = nid;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String nDescription){
        description = nDescription;
    }
    public String getName(){
        return name;
    }
    public void setName(String nname){
        name=nname;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double nprice){
        price= nprice;
    }
}
