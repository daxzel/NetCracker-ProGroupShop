/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBClasses;
import DBManager.*;
import java.sql.SQLException;
import javax.naming.NamingException;
/**
 *
 * @author Yra
 */
public class Order implements OrderInterface {
private int id_order;
private User user;
private Product product;
private boolean status;
private int kol_vo;
public Order(int id_ord, int id_user, int id_product, boolean stat, int kol_vo) throws SQLException, NamingException{
    this.id_order = id_ord;
    this.setUser(id_user);
    this.setProduct(id_product);
    this.status = stat;
    this.kol_vo = kol_vo;
}
   // @Override
    public int getId() {
       return id_order;
    }

   // @Override
    public void setId(int id) {
        this.id_order = id;
    }


   // @Override
    public int getIdProduct() {
        return product.getId();
    }
   // @Override
    public String getNameProduct() {
        return product.getName();
    }

    public void setProduct(int idPr) throws SQLException, NamingException {
          product = (Product) DBManager.getByIdProduct(idPr);
    }


   // @Override
    public int getIdUser() {
        return user.getId();
    }

    public void setUser(int idUsr) throws SQLException, NamingException {
         user = (User) DBManager.getById("USER", idUsr);
    }
    public User getUser() {
        return user;
    }

   // @Override
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean st) {
        this.status = st;
    }


   // @Override
    public String getUserNik() {
       return this.user.getNik();
    }

   // @Override
    public double getPriceProduct() {
        return product.getPrice();
    }

   // @Override
    public double getPrice() {
      return product.getPrice()*kol_vo;
    }

   // @Override
    public int getKol() {
        return kol_vo;
    }

}
