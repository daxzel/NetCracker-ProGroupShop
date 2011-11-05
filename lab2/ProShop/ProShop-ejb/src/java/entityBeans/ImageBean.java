/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.naming.NamingException;
import javax.ejb.*;
import java.sql.*;
import java.util.*;
import OtherBean.*;
/**
 *
 * @author Admin
 */
public class ImageBean implements EntityBean {

    private EntityContext entityContext;
    private Connection conn;
    private long id_img;
    private long id_product;
    private String name;
    private oracle.sql.BLOB image;
    private int width;
    private int heaight;
    
    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click on the + sign on the left to edit the code.">

    // TODO Consider creating Transfer Object to encapsulate data
    // TODO Review finder methods

    /**
     * @see javax.ejb.EntityBean#setEntityContext(javax.ejb.EntityContext)
     */
    public void setEntityContext(EntityContext aContext) {
        entityContext = aContext;
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

    public java.lang.Long ejbFindByPrimaryKey(java.lang.Long id_catalog) throws ObjectNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = Helper.getConnection();
            pst = conn.prepareStatement("SELECT * FROM IMAGE WHERE ID_IMG = ?");
            //id_catalog.longValue();
            pst.setLong(1, id_catalog.longValue());
            ResultSet resultSet = pst.executeQuery();
            if (!resultSet.next()) {
                throw new ObjectNotFoundException("Запись не найдена");
            }
            return id_catalog;
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
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

    public void ejbRemove() throws RemoveException {
         Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = Helper.getConnection();
            pst = conn.prepareStatement("DELETE FROM IMAGE WHERE ID_IMG = ?");
            pst.setLong(1, id_img);
            if (pst.executeUpdate() < 1) {
                throw new RemoveException("Ошибка удаления");
            }
            // conn.commit();
        }  catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
//                 conn.commit();
                Helper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }
    
    /**
     * @see javax.ejb.EntityBean#unsetEntityContext()
     */
    public void unsetEntityContext() {
        entityContext = null;
    }

    public Collection ejbFindAll() {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = Helper.getConnection();
            pst = conn.prepareStatement("SELECT * FROM IMAGE");
            ResultSet resultSet = pst.executeQuery();
            Vector keys = new Vector();
            while (resultSet.next()) {
                long id_catalog = resultSet.getLong(1);
                keys.addElement(new Long(id_catalog));
            }
            return keys;
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
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

    /**
     * @see javax.ejb.EntityBean#ejbLoad()
     */
    public void ejbLoad() {
        java.lang.Long lg = (java.lang.Long) entityContext.getPrimaryKey();
        id_img = lg.longValue();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = Helper.getConnection();
            pst = conn.prepareStatement("SELECT * FROM IMAGE WHERE ID_IMG = ?");
            pst.setLong(1, id_img);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new NoSuchEntityException("Не найдена указанная запись");
            }
            id_img = rs.getLong(1);
            id_product = rs.getLong(2);
            name = rs.getString(3);
            image = (oracle.sql.BLOB)rs.getBlob(4);
            width=rs.getInt(5);
            heaight=rs.getInt(6);
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                Helper.closeConnection(conn, pst,rs);
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
            conn = Helper.getConnection();
            pst = conn.prepareStatement("UPDATE IMAGE SET ID_PRODUCT =?, NAME =?, IMAGE =?, WIDTH=?, HEIGHT =? WHERE ID_IMG=?");
            pst.setLong(1, id_product);
            pst.setString(2, name);
            pst.setBlob(3, image);
            pst.setInt(4, width);
            pst.setInt(5, heaight);
            pst.setLong(6, id_img);
            if (pst.executeUpdate() < 1) {
                throw new NoSuchEntityException("Не найдена запись");
            }
        } catch (NamingException ex) {
            throw new EJBException("Ошибка UPDATE");
        } catch (SQLException e) {
            throw new EJBException("Ошибка UPDATE");
        } finally {
            try {
                Helper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    // </editor-fold>
    
    public java.lang.Long getPk()
    {
        return new Long(getId_img());
    }

    public void setPk(java.lang.Long key)
    {
        setId_img(key.longValue());
    }
    
    public java.lang.Long ejbCreate(long i_id_product,String i_name,  int i_width, int i_heaight)  throws CreateException {
        this.id_product=i_id_product;
        this.name = i_name;
        this.width = i_width;
        this.heaight= i_heaight;
        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            conn = Helper.getConnection();
            pst = conn.prepareCall("BEGIN INSERT INTO IMAGE (ID_PRODUCT, NAME, IMAGE, WIDTH, HEIGHT)" + "VALUES(?,?,empty_blob(),?,?) RETURNING ID_IMG INTO ?;END;");
            pst.setLong(1, id_product);
            pst.setString(2, name);
            pst.setInt(3, width);
            pst.setInt(4, heaight);


            pst.registerOutParameter(5, Types.INTEGER);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }
            id_img = pst.getLong(5);

            pst=conn.prepareCall("select IMAGE from IMAGE where ID_IMG="+id_img+" FOR UPDATE NOWAIT");
            rs = pst.executeQuery();
            rs.next();
            this.image=(oracle.sql.BLOB)rs.getBlob(1);
            return new Long(id_img);
        } catch (NamingException ex) {
            throw new EJBException("Произошла ошибка добавления");
        } catch (SQLException ex) {
            throw new EJBException("Произошла ошибка добавления");
        } finally {

            try {
                Helper.closeConnection(conn, pst, rs);
            } catch (SQLException ex1) {
              //  throw new EJBException("Ошибка закрытии соединия с базой");
            }

        }
    }

    public void ejbPostCreate(long i_id_product,String i_name, int i_width, int i_heaight)  throws CreateException {
    }

    public long getId_product()
    {
        return id_product;
    }

    public void setId_product(java.lang.Long id)
    {
        id_product=id.longValue();
    }

    public Blob getImage()
    {
        try
        {

            return new javax.sql.rowset.serial.SerialBlob(image);
            //return image;
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    public java.awt.Image getImageI()
    {
        try
        {
            java.io.InputStream stream = image.getBinaryStream();
            java.awt.Image img = javax.imageio.ImageIO.read(stream);
            return img;
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    public void setImageV(Vector v)
    {
        try
        {
            Iterator it = v.iterator();

            java.io.OutputStream out =  image.getBinaryOutputStream();

            while(it.hasNext())
            {
                byte[] bytes = (byte[])it.next();
                out.write(bytes);
            }
            out.flush();
            out.close();
        }
        catch(Exception ex)
        {
        }
    }

    public Vector getImageV()
    {
        try
        {
            Vector v = new Vector();
            java.io.InputStream in = image.getBinaryStream();
            
            int count = 1024;
            int index = 0 ;

            while(in.available()>0)
            {
                byte[] buff= new byte[count];
                in.read(buff, index, count);
                index+=count;
                v.add(buff);
            }
            return v;
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    public void setImage(Blob i_image)
    {
        image=(oracle.sql.BLOB)i_image;
    }

    public long getWidth()
    {
        return id_img;
    }

    public void setWidth(int i_width)
    {
        width=i_width;
    }

    public long getHeaight()
    {
        return heaight;
    }

    public void setHeaight(int i_heaight)
    {
        heaight=i_heaight;
    }

    public void ejbHomeHomeMethod() {
    }

    public long getId_img()
    {
        return id_img;
    }

    public void setId_img(long id)
    {
        id_img=id;
    }

}
