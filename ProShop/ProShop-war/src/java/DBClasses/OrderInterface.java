/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBClasses;

/**
 *
 * @author Yra
 */
public interface OrderInterface extends DBClassInterface{
    public void setId(int id) ;
    public int getIdProduct() ;
    public String getNameProduct();
    public int getIdUser() ;
    public boolean getStatus() ;
    public String getUserNik();
    public double getPriceProduct();
    public double getPrice();
    public int getKol();

}
