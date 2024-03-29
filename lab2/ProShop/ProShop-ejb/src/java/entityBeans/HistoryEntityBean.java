/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import helpers.EJBHelper;
import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.FinderException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Vector;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.NoSuchEntityException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;

/**
 *
 * @author Yra
 */
public class HistoryEntityBean implements EntityBean {

    private EntityContext context;
    private long id_his;
    private long id_user;
    private String name_table;
    private String status;
    private Timestamp date_update;
    private long id_obj;

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

    public void unsetEntityContext() {
        this.context = null;
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
    public void ejbRemove() throws RemoveException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("DELETE FROM \"HISTORY\" WHERE ID_HIS = ?");
            pst.setLong(1, id_his);
            if (pst.executeUpdate() < 1) {
                throw new RemoveException("Ошибка удаления");
            }
        } catch (NamingException ex) {
            throw new EJBException("Ошибка DELETE");
        } catch (SQLException e) {
            throw new EJBException("Ошибка DELETE");
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    /**
     * @see javax.ejb.EntityBean#unsetEntityContext()
     */
    /**
     * @see javax.ejb.EntityBean#ejbLoad()
     */
    public void ejbLoad() {
        id_his = Long.parseLong(context.getPrimaryKey().toString());
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT * FROM \"HISTORY\" WHERE ID_HIS = ?");
            pst.setLong(1, id_his);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new NoSuchEntityException("Не найдена указанная запись");
            }
            id_his = rs.getLong(1);
            id_user = rs.getLong(2);
            name_table = rs.getString(3);
            status = rs.getString(4);
            date_update = rs.getTimestamp(5);
            id_obj = rs.getLong(6);


        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();

            pst = conn.prepareStatement("UPDATE \"HISTORY\"" + "SET ID_USER =?, NAME_TABLE=?,STATUS=?,DATE_UPDATE=?,ID_OBJ=? WHERE ID_HIS=?");
            pst.setLong(1, id_user);
            pst.setString(2, name_table);
            pst.setString(3, status);
            pst.setTimestamp(4, date_update);
            pst.setLong(5, id_obj);
            pst.setLong(6, id_his);

            if (pst.executeUpdate() < 1) {
                throw new NoSuchEntityException("Не найдена запись");
            }
        } catch (NamingException ex) {
            throw new EJBException("Ошибка UPDATE");
        } catch (SQLException e) {
            throw new EJBException("Ошибка UPDATE");
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    // </editor-fold>
    /**
     * See EJB 2.0 and EJB 2.1 section 12.2.5
     */
    public java.lang.Long ejbFindByPrimaryKey(java.lang.Long aKey) throws FinderException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT ID_HIS FROM \"HISTORY\" WHERE ID_HIS = ?");
            pst.setLong(1, id_his);
            ResultSet resultSet = pst.executeQuery();
            if (!resultSet.next()) {
                throw new ObjectNotFoundException("Запись не найдена");
            }
            return new Long(id_his);
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

        public Collection ejbFindAll() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT ID_HIS FROM HISTORY ORDER BY DATE_UPDATE DESC");
            //   pst.setLong(1, id_catalog.longValue());
            // rs = pst.executeQuery();
            rs = pst.executeQuery();
            Vector keys = new Vector();
            while (rs.next()) {
                long id_his = rs.getLong(1);
                keys.addElement(new Long(id_his));
            }
            return keys;
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EJBException("Ошибка SELECT");
            // e.printStackTrace();
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst, rs);
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                throw new EJBException("Ошибка закрытии соединия с базой");
               //  
            }
        }
    }
                public Collection ejbFindAllAWeek() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT ID_HIS FROM HISTORY WHERE  (DATE_UPDATE > CURRENT_DATE - INTERVAL '7' DAY) ORDER BY DATE_UPDATE DESC ");
            //   pst.setLong(1, id_catalog.longValue());
            // rs = pst.executeQuery();
            rs = pst.executeQuery();
            Vector keys = new Vector();
            while (rs.next()) {
                long id_his = rs.getLong(1);
                keys.addElement(new Long(id_his));
            }
            return keys;
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EJBException("Ошибка SELECT");
            // e.printStackTrace();
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst, rs);
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                throw new EJBException("Ошибка закрытии соединия с базой");
               //
            }
        }
    }
    
    public java.util.Collection ejbFindByIdObjAndNameTableP(java.lang.Long id, String nameTable) throws FinderException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT ID_HIS FROM \"HISTORY\" WHERE ID_OBJ = ? AND NAME_TABLE = ?");
            pst.setLong(1, id.longValue());
            pst.setString(2, nameTable);
            ResultSet resultSet = pst.executeQuery();
            Vector keys = new Vector();
            while (resultSet.next()) {
                long id_his = resultSet.getLong(1);
                keys.addElement(new Long(id_his));
            }
            return keys;
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }             
    }

    public java.lang.Long ejbFindByIdObjAndNameTable(java.lang.Long id, String nameTable) throws FinderException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT ID_HIS FROM \"HISTORY\" WHERE ID_OBJ = ? AND NAME_TABLLE = ?");
            pst.setLong(1, id.longValue());
            pst.setString(2, nameTable);
            ResultSet resultSet = pst.executeQuery();
            long id_his = resultSet.getLong(1);
            if (!resultSet.next()) {
                throw new ObjectNotFoundException("Запись не найдена");
            }
            return new Long(id_his);
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

        public Collection ejbFindByNameTable(String nameTable) throws FinderException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT  HS.ID_HIS FROM HISTORY HS WHERE HS.DATE_UPDATE = (SELECT MAX(H.DATE_UPDATE) FROM HISTORY H WHERE H.NAME_TABLE = '\"PRODUCT\"' AND H.ID_OBJ = HS.ID_OBJ ) AND (HS.DATE_UPDATE > CURRENT_DATE - INTERVAL '7' DAY) ORDER BY HS.DATE_UPDATE DESC");
           //pst.setString(1, nameTable);
            ResultSet resultSet = pst.executeQuery();
            Vector keys = new Vector();
            while (resultSet.next()) {
                long id_his = resultSet.getLong(1);
                keys.addElement(new Long(id_his));
            }
            return keys;

        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }
    public java.lang.Long ejbCreate(long id_user, String name_table, String status, long id_obj) throws CreateException {


        this.id_user = id_user;
        this.name_table = name_table;
        this.status = status;
        this.id_obj = id_obj;

        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareCall("BEGIN INSERT INTO \"HISTORY\" " + "(ID_HIS,ID_USER,NAME_TABLE,STATUS,DATE_UPDATE,ID_OBJ)" + "VALUES(MY_SEQ_H.NEXTVAL,?,?,?,?,?) RETURNING ID_HIS INTO ?;END;");
            pst.setLong(1, id_user);
            pst.setString(2, name_table);
            pst.setString(3, status);
            java.sql.Timestamp f = new java.sql.Timestamp((new java.util.Date()).getTime());
            this.date_update = f;
            pst.setTimestamp(4,f);
            pst.setLong(5, id_obj);
            pst.registerOutParameter(6, Types.INTEGER);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }

            id_his = pst.getLong(6);
            return new Long(id_his);
        } catch (NamingException ex) {
            throw new EJBException("Произошла ошибка добавления");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new EJBException("Произошла ошибка добавления");
        } finally {

            try {
                EJBHelper.closeConnection(conn, pst, rs);
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                throw new EJBException("Ошибка закрытии соединия с базой");
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new EJBException("Ошибка закрытии соединия с базой");
            }


        }
    }

    public void ejbPostCreate(long id_user, String name_table, String status, long id_obj) throws CreateException {
    }

    public java.lang.Long ejbCreate(String name_table, String status, long id_obj) throws CreateException {


        // this.id_user = id_user;
        this.name_table = name_table;
        this.status = status;
        this.id_obj = id_obj;

        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareCall("BEGIN INSERT INTO \"HISTORY\" " + "(ID_HIS,NAME_TABLE,STATUS,DATE_UPDATE,ID_OBJ)" + "VALUES(MY_SEQ_H.NEXTVAL,?,?,?,?) RETURNING ID_HIS INTO ?;END;");
            pst.setString(1, name_table);
            pst.setString(2, status);
            pst.setLong(3, id_obj);
            java.sql.Timestamp f = new java.sql.Timestamp((new java.util.Date()).getTime());

            this.date_update = f;
            pst.setTimestamp(4, f);
            pst.registerOutParameter(5, Types.INTEGER);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }

            id_his = pst.getLong(5);
            return new Long(id_his);
        } catch (NamingException ex) {
            throw new EJBException("Произошла ошибка добавления");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new EJBException("Произошла ошибка добавления");
        } finally {

            try {
                EJBHelper.closeConnection(conn, pst, rs);
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                throw new EJBException("Ошибка закрытии соединия с базой");
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new EJBException("Ошибка закрытии соединия с базой");
            }


        }
    }

    public void ejbPostCreate(String name_table, String status, long id_obj) throws CreateException {
    }

    public java.lang.Long ejbCreate(long id_user, String name_table, String status) throws CreateException {


        this.id_user = id_user;
        this.name_table = name_table;
        this.status = status;
        // this.id_obj = id_obj;

        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareCall("BEGIN INSERT INTO \"HISTORY\" " + "(ID_HIS,ID_USER,NAME_TABLE,STATUS,DATE_UPDATE)" + "VALUES(MY_SEQ_H.NEXTVAL,?,?,?,?) RETURNING ID_HIS INTO ?;END;");
            pst.setLong(1, id_user);
            pst.setString(2, name_table);
            pst.setString(3, status);

            //java.sql.Date f = new java.sql.Date((new java.util.Date()).getTime());
            java.sql.Timestamp f = new java.sql.Timestamp((new java.util.Date()).getTime());


            this.date_update = f;

            pst.setTimestamp(4,f);
            pst.registerOutParameter(5, Types.INTEGER);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }

            id_his = pst.getLong(5);
            return new Long(id_his);
        } catch (NamingException ex) {
            throw new EJBException("Произошла ошибка добавления");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new EJBException("Произошла ошибка добавления");
        } finally {

            try {
                EJBHelper.closeConnection(conn, pst, rs);
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                throw new EJBException("Ошибка закрытии соединия с базой");
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new EJBException("Ошибка закрытии соединия с базой");
            }


        }
    }

    public void ejbPostCreate(long id_user, String name_table, String status) throws CreateException {
    }

    public String getStatus() {
        return status;
    }

    public long getRecordId() {
        return id_his;
    }

    public long getObjId() {
        return id_obj;
    }

    public long getUserId() {
        return id_user;
    }
    
    public String getNameTable() {
        return name_table;
    }

    public String getUserName() {
        UserBeanRemote user = null;
        String name = "";
        try {
            UserBeanRemoteHome userHome = (UserBeanRemoteHome) helpers.EJBHelper.lookupHome("ejb/UserBean", UserBeanRemoteHome.class);
            user = userHome.findByPrimaryKey(new Long(this.id_user));
            name = user.getNik();
        } catch (FinderException ex) {
            //ex.printStackTrace();
        } catch (RemoteException ex) {
            // ex.printStackTrace();
        } catch (NamingException ex) {
            //ex.printStackTrace();
        }
        return name;
    }

    public String getTimestampSaved() {
        
        java.text.SimpleDateFormat formt = new java.text.SimpleDateFormat(DateFormat.getDateTimeInstance(
            DateFormat.SHORT, DateFormat.MEDIUM).format(date_update));
        String date = formt.format(date_update);
        return date;
    }
}
