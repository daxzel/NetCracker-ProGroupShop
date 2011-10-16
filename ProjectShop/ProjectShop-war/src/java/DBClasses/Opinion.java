/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBClasses;

import DBManager.DBManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author ололо
 */
public class Opinion implements OpinionInterface {

    private int idOpinion;
    private String text;
    private Product product;
    private User user;

    public Opinion (int idOp, int idUsr, int idPr, String txt ) throws SQLException, NamingException{
        setId(idOp);
        setProduct(idPr);
        setUser(idUsr);
        setText(txt);

    }

    public Opinion(ResultSet rs) throws SQLException, NamingException {
        setId(rs.getInt(1));
        setProduct(rs.getInt(2));
        setUser(rs.getInt(3));
        setText(rs.getString(4));
    }


    @Override
    public int getId() {
        return idOpinion;
    }

    public void setId(int idOp) {
        idOpinion = idOp;
    }

    @Override
    public int getIdProduct() {
        return product.getId();
    }

    public void setProduct(int idPr) throws SQLException, NamingException {
          product = (Product) DBManager.getById("PRODUCT", idPr);
    }

    @Override
    public int getIdUser() {
        return user.getId();
    }

    public void setUser(int idUsr) throws SQLException, NamingException {
         user = (User) DBManager.getById("USER", idUsr);
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String txt) {
        text = txt;
    }

}
