/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import OtherBean.Helper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.FinderException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javassist.tools.rmi.ObjectNotFoundException;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.sql.DataSource;

/**
 *
 * @author Yra
 */
public class OpinionBean implements EntityBean {

    private EntityContext context;
    private DataSource dataSource;
    private long id_op;
    private long id_pr;
    private long id_usr;
    private String text;

    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">
    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise beans, Web services)
    // TODO Add business methods
    // TODO Add create methods
    /**
     * @see javax.ejb.EntityBean#setEntityContext(javax.ejb.EntityContext)
     */
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }

    /**
     * @see javax.ejb.EntityBean#ejbActivate()
     */
    public void ejbActivate() {
    }

    /**
     * @see javax.ejb.EntityBean#ejbPassivate()
     */
    public void ejbPassivate() {
    }

    /**
     * @see javax.ejb.EntityBean#ejbRemove()
     */
    public void ejbRemove() {
    }

    /**
     * @see javax.ejb.EntityBean#unsetEntityContext()
     */
    public void unsetEntityContext() {
        context = null;
    }

    /**
     * @see javax.ejb.EntityBean#ejbLoad()
     */
    public void ejbLoad() {
        // TODO add code to retrieve data
    }

    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
        // TODO add code to persist data
    }

    // </editor-fold>
    /**
     * See EJB 2.0 and EJB 2.1 section 12.2.5
     */
    public java.lang.Long ejbFindByPrimaryKey(java.lang.Long id_op) throws FinderException, ObjectNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = dataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM \"OPINION\" WHERE ID_OPINION = ?");
            //id_op.longValue();
            pst.setLong(1, id_op.longValue());
            ResultSet resultSet = pst.executeQuery();
            if (!resultSet.next()) {
                throw new ObjectNotFoundException("Запись не найдена");
            }
            return id_op;
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                Helper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }

    }

    public java.lang.Long ejbFindOpinionByProduct(int id_pr) throws ObjectNotFoundException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            pst = conn.prepareStatement("SELECT ID_USER FROM \"OPINION\" WHERE ID_PRODUCT = ?");
            pst.setInt(1, id_pr);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new ObjectNotFoundException("Запись не найдена");
            }
            return new Long(rs.getLong(1));
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                Helper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    public java.lang.Long ejbFindOpinionByProductName(String name_pr) throws ObjectNotFoundException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM PRODUCT WHERE NAME=?");
            pst.setString(1, name_pr);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new ObjectNotFoundException("Запись не найдена");
            }
            return new Long(rs.getLong(1));
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                Helper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    public java.lang.Long ejbCreate(java.lang.Long id_prod, java.lang.Long id_user, String txt) throws CreateException {
        this.id_pr = id_prod.longValue();
        this.id_usr = id_user.longValue();
        this.text = txt;
        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            pst = conn.prepareCall("BEGIN INSERT INTO \"OPINION\" " + "(ID_PRODUCT, ID_USER, TEXT)" + "VALUES(?, ?, ?) RETURNING ID_OPINION INTO ?;END;");
            pst.setLong(1, id_pr);
            pst.setLong(2, id_usr);
            pst.setString(3, txt);
            pst.registerOutParameter(4, Types.INTEGER);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }
            id_op = pst.getLong(10);
            return new Long(id_op);
        } catch (SQLException ex) {
            throw new EJBException("Произошла ошибка добавления");
        } finally {
            try {
                Helper.closeConnection(conn, pst, rs);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }

        }
    }

    public long getIdOpinion() {
        return id_op;
    }

    public void setIdOpinion(java.lang.Long id_opn) {
        id_op = id_opn.longValue();
    }

    public long getIdProduct() {
        return id_pr;
    }

    public void setIdProduct(java.lang.Long id_prod) {
        id_pr = id_prod.longValue();
    }

    public long getIdUser() {
        return id_usr;
    }

    public void setIdUser(java.lang.Long id_user) {
        id_usr = id_user.longValue();
    }

    public String getText() {
        return text;
    }

    public void setText(String txt) {
        text = txt;
    }
}
