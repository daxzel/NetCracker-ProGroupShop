/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityBeans;

import helpers.EJBHelper;
import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.naming.NamingException;
import javax.ejb.*;
import java.sql.*;
import java.util.*;
import java.awt.*;
import java.io.*;
import javax.jms.JMSException;
import moreTools.HistoryMessage;
/**
 *
 * @author Admin
 */
public class ImageBean implements EntityBean {

    private EntityContext entityContext;
  
    private long id_img;
    private long id_product;
    private String name;
    private int width;
    private int height;
        private long userId;
     private long objId;
    
    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click on the + sign on the left to edit the code.">

    // TODO Consider creating Transfer Object to encapsulate data
    // TODO Review finder methods

    /**
     * @see javax.ejb.EntityBean#setEntityContext(javax.ejb.EntityContext)
     */
    public void setEntityContext(EntityContext ctx) {
          this.entityContext = ctx;
        try {
            // javax.naming.Context context = new javax.naming.InitialContext();
            try {
            } catch (Exception e) {
                throw new EJBException("Проблема с подключением к базе");
            }
        } catch (Exception e) {
            throw new EJBException("Проблема с подключением к базе");
        }
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

    public void unsetEntityContext() {
        entityContext = null;
    }
    /**
     * @see javax.ejb.EntityBean#ejbRemove()
     */

    public java.lang.Long ejbFindByPrimaryKey(java.lang.Long i_id_image) throws ObjectNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT ID_IMG FROM IMAGE WHERE ID_IMG = ?");
            //id_catalog.longValue();
            pst.setLong(1, i_id_image.longValue());
            ResultSet resultSet = pst.executeQuery();
            if (!resultSet.next()) {
                throw new ObjectNotFoundException("Запись не найдена");
            }
            return i_id_image;
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }
    }

    public void ejbRemove() throws RemoveException {
         Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("DELETE FROM IMAGE WHERE ID_IMG = ?");
            pst.setLong(1, id_img);
            
          //  EJBHelper.sendMessage(new HistoryMessage(userId,"IMAGE","Удалено изображение",objId));
            
            if (pst.executeUpdate() < 1) {
                throw new RemoveException("Ошибка удаления");
            }
            // conn.commit();
               
       // } catch (JMSException ex){
       //     throw new EJBException("Ошибка jms");
        }  catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
//                 conn.commit();
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }
    
    /**
     * @see javax.ejb.EntityBean#unsetEntityContext()
     */


    public Collection ejbFindAll() {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
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
                EJBHelper.closeConnection(conn, pst);
            } catch (SQLException ex1) {
                throw new EJBException("Ошибка закрытии соединия с базой");
            }
        }
    }

    /**
     * @see javax.ejb.EntityBean#ejbLoad()
     */
    public void ejbLoad() {
        //java.lang.Long lg = (java.lang.Long) entityContext.getPrimaryKey();
        id_img  = Long.parseLong(entityContext.getPrimaryKey().toString());
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT * FROM IMAGE WHERE ID_IMG = ?");
            pst.setLong(1, id_img);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new NoSuchEntityException("Не найдена указанная запись");
            }
            //id_img = rs.getLong(1);
            id_product = rs.getLong(2);
            name = rs.getString(3);
            width=rs.getInt(4);
            height=rs.getInt(5);
        } catch (NamingException ex) {
            throw new EJBException("Ошибка SELECT");
        } catch (SQLException e) {
            throw new EJBException("Ошибка SELECT");
        } finally {
            try {
                EJBHelper.closeConnection(conn, pst,rs);
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
            pst = conn.prepareStatement("UPDATE IMAGE SET ID_PRODUCT =?, NAME =?, WIDTH=?, HEIGHT =? WHERE ID_IMG=?");
            //pst = conn.prepareStatement("UPDATE IMAGE SET ID_PRODUCT =?, NAME =?, WIDTH=?, HEIGHT =? WHERE ID_IMG=?");
//            pst = conn.prepareStatement("UPDATE IMAGE" + "SET NAME =? WHERE ID_IMG=?");
            pst.setLong(1, id_product);
            pst.setString(2, name);
            pst.setInt(3, width);
            pst.setInt(4, height);
            pst.setLong(5, id_img);
            
           // EJBHelper.sendMessage(new HistoryMessage(userId,"IMAGE","Изменено изображение",objId));
            
            if (pst.executeUpdate() < 1) {
                throw new NoSuchEntityException("Не найдена запись");
            }
          //      } catch (JMSException ex){
          //  throw new EJBException("Ошибка jms");
        } catch (NamingException ex) {
            throw new EJBException("Ошибка UPDATE");
        } catch (SQLException e) {
            e.printStackTrace();

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
    
    public java.lang.Long getPk()
    {
        return new Long(getId_img());
    }

    public void setPk(java.lang.Long key)
    {
        setId_img(key.longValue());
    }
    /*
    private InputStream imageToStream(moreTools.SerializbleImage image) throws IOException, SQLException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        ObjectOutputStream oos = new ObjectOutputStream(out);
        
        oos.writeObject(image);
        
        oos.flush();

        byte[] outBytes = out.toByteArray();

        ByteArrayInputStream in = new ByteArrayInputStream(outBytes);

        return in;

    }

    private byte[] imageToBytes(moreTools.SerializbleImage image) throws IOException, SQLException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        ObjectOutputStream oos = new ObjectOutputStream(out);

        oos.writeObject(image);

        oos.flush();

        return out.toByteArray();

    }

    private void imageToBlob2(moreTools.SerializbleImage image, oracle.sql.BLOB blob) throws IOException, SQLException
    {
        ObjectOutputStream oos = new ObjectOutputStream(blob.getBinaryOutputStream(1L));

        oos.writeObject(image);

        oos.flush();

    }

    private moreTools.SerializbleImage blobToImage( oracle.sql.BLOB blob) throws IOException, SQLException, java.lang.ClassNotFoundException
    {
        ObjectInputStream ois = new ObjectInputStream(blob.getBinaryStream(1L));

        return (moreTools.SerializbleImage)ois.readObject();
    }
*/
    /*
    public java.lang.Long ejbCreate(long i_id_product,String i_name, moreTools.SerializbleImage image,  int i_width, int i_heaight)  throws CreateException {
        this.id_product=i_id_product;
        this.name = i_name;
        this.width = i_width;
        this.height= i_heaight;
        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareCall("BEGIN INSERT INTO IMAGE (ID_PRODUCT, NAME, IMAGE, WIDTH, HEIGHT)" + "VALUES(?,?,empty_blob(),?,?) RETURNING ID_IMG INTO ?;END;");
            pst.setLong(1, id_product);
            pst.setString(2, name);
            pst.setInt(3, width);
            pst.setInt(4, height);


            pst.registerOutParameter(5, Types.INTEGER);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }
            id_img = pst.getLong(5);

            pst=conn.prepareCall("select IMAGE from IMAGE where ID_IMG="+id_img);
            rs = pst.executeQuery();
            rs.next();
            Blob blob = rs.getBlob(1);
            imageToBlob2(image, (oracle.sql.BLOB)blob);

            pst = conn.prepareCall("UPDATE IMAGE SET IMAGE =? WHERE ID_IMG=?");
            pst.setBlob(1, blob);
            pst.setLong(2, id_img);
            pst.executeQuery();

           // EJBHelper.sendMessage(new HistoryMessage(userId,"IMAGE","Добавлено изображение",pst.getLong(5)));
                   

            return new Long(id_img);

       // } catch (JMSException ex){
        //    throw new EJBException("Ошибка jms");
        } catch (NamingException ex) {
            throw new EJBException("Произошла ошибка добавления");
        } catch (Exception ex) {
            throw new EJBException("Произошла ошибка добавления");
        } finally {

            try {
                EJBHelper.closeConnection(conn, pst, rs);
            } catch (SQLException ex1) {
              //  throw new EJBException("Ошибка закрытии соединия с базой");
            }

        }

    }

    public java.lang.Long ejbCreate(long id,long i_id_product,String i_name, moreTools.SerializbleImage image,  int i_width, int i_heaight)  throws CreateException {
        this.id_img = id;
        this.id_product=i_id_product;
        this.name = i_name;
        this.width = i_width;
        this.heaight= i_heaight;
        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareCall("INSERT INTO IMAGE (ID_IMG ,ID_PRODUCT, NAME, IMAGE, WIDTH, HEIGHT)" + "VALUES(?,?,?,empty_blob(),?,?)");
            pst.setLong(1, id);
            pst.setLong(2, id_product);
            pst.setString(3, name);
            pst.setInt(4, width);
            pst.setInt(5, heaight);

            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }

            pst=conn.prepareCall("select IMAGE from IMAGE where ID_IMG="+id_img);
            rs = pst.executeQuery();
            rs.next();
            Blob blob = rs.getBlob(1);
            imageToBlob2(image, (oracle.sql.BLOB)blob);

            pst = conn.prepareCall("UPDATE IMAGE SET IMAGE =? WHERE ID_IMG=?");
            pst.setBlob(1, blob);
            pst.setLong(2, id_img);
            pst.executeQuery();

            return new Long(id_img);
        } catch (NamingException ex) {
            throw new EJBException("Произошла ошибка добавления");
        } catch (Exception ex) {
            throw new EJBException("Произошла ошибка добавления");
        } finally {

            try {
                EJBHelper.closeConnection(conn, pst, rs);
            } catch (SQLException ex1) {
              //  throw new EJBException("Ошибка закрытии соединия с базой");
            }

        }

    }


    public void ejbPostCreate(long i_id_product,String i_name,  moreTools.SerializbleImage image, int i_width, int i_heaight)  throws CreateException {
    }

    public void ejbPostCreate(long id, long i_id_product,String i_name,  moreTools.SerializbleImage image, int i_width, int i_heaight)  throws CreateException {
    }
*/
     public java.lang.Long ejbCreate(long i_id_product,String i_name,  int i_width, int i_height)  throws CreateException {


         this.id_product=i_id_product;
        this.name = i_name;
        this.width = i_width;
        this.height= i_height;
        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareCall("BEGIN INSERT INTO IMAGE (ID_PRODUCT, NAME, WIDTH, HEIGHT)" + "VALUES(?,?,?,?) RETURNING ID_IMG INTO ?;END;");
            pst.setLong(1, id_product);
            pst.setString(2, name);
            pst.setInt(3, width);
            pst.setInt(4, height);


            pst.registerOutParameter(5, Types.INTEGER);
            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }
            id_img = pst.getLong(5);

            return new Long(id_img);

       // } catch (JMSException ex){
        //    throw new EJBException("Ошибка jms");
        } catch (NamingException ex) {
            throw new EJBException("Произошла ошибка добавления");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new EJBException("Произошла ошибка добавления");
        } finally {

            try {
                EJBHelper.closeConnection(conn, pst, rs);
            } catch (SQLException ex1) {
              //  throw new EJBException("Ошибка закрытии соединия с базой");
            }

        }

    }

    public java.lang.Long ejbCreate(long id,long i_id_product,String i_name, int i_width, int i_height)  throws CreateException {
        this.id_img = id;
        this.id_product=i_id_product;
        this.name = i_name;
        this.width = i_width;
        this.height= i_height;
        Connection conn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareCall("INSERT INTO IMAGE (ID_IMG ,ID_PRODUCT, NAME,  WIDTH, HEIGHT)" + "VALUES(?,?,?,?,?)");
            pst.setLong(1, id);
            pst.setLong(2, id_product);
            pst.setString(3, name);
            pst.setInt(4, width);
            pst.setInt(5, height);

            rs = pst.executeQuery();
            if (!rs.next()) {
                throw new CreateException("Ошибка вставки");
            }


            return new Long(id_img);
        } catch (NamingException ex) {
            throw new EJBException("Произошла ошибка добавления");
        } catch (Exception ex) {
            throw new EJBException("Произошла ошибка добавления");
        } finally {

            try {
                EJBHelper.closeConnection(conn, pst, rs);
            } catch (SQLException ex1) {
              //  throw new EJBException("Ошибка закрытии соединия с базой");
            }

        }

    }


    public void ejbPostCreate(long i_id_product,String i_name,   int i_width, int i_height)  throws CreateException {
    }

    public void ejbPostCreate(long id, long i_id_product,String i_name,  int i_width, int i_height)  throws CreateException {
    }

     public java.lang.Long ejbFindByName(java.lang.String name) throws ObjectNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT * FROM IMAGE WHERE NAME = ?");
            pst.setString(1, name);
            ResultSet resultSet = pst.executeQuery();
            if (!resultSet.next()) {
                throw new ObjectNotFoundException("Запись не найдена");
            }
            Long id = new Long(resultSet.getLong(2));
            return id;
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
    public long getId_product()
    {
        return id_product;
    }

    public void setId_product(java.lang.Long id)
    {
        id_product=id.longValue();
    }
/*
    public byte[] getImage()
    {
        try
        {
            Connection conn = null;
            PreparedStatement pst = null;
            conn = EJBHelper.getConnection();

            pst=conn.prepareCall("select IMAGE from IMAGE where ID_IMG="+id_img);
            ResultSet rs = pst.executeQuery();
            rs.next();
            oracle.sql.BLOB tempImage = (oracle.sql.BLOB)rs.getBlob(1);
            java.io.InputStream byte_stream = tempImage.getBinaryStream(1L);
            byte [] byte_array=new byte[3000000];
            int bytes_read = byte_stream.read(byte_array);
            return byte_array;
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    public void setImage(byte[] image)
    {
        try
        {
            Connection conn = null;
            PreparedStatement pst = null;
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("UPDATE IMAGE SET IMAGE =? WHERE ID_IMG=?");
            pst.setBytes(1, image);
            pst.setLong(2, id_img);
            pst.executeQuery();         
        }
        catch(Exception ex)
        {
            int i = 0;
        }
    }
    
    public moreTools.SerializbleImage getImageI() throws SQLException,IOException, NamingException, ClassNotFoundException
    {
        Connection conn = null;
        PreparedStatement pst = null;
        conn = EJBHelper.getConnection();

        pst=conn.prepareCall("select IMAGE from IMAGE where ID_IMG="+id_img);
        ResultSet rs = pst.executeQuery();
        rs.next();
        return this.blobToImage((oracle.sql.BLOB)rs.getBlob(1));
    }
    public void setImageI(moreTools.SerializbleImage image)
    {
        try
        {
            Connection conn = null;
            PreparedStatement pst = null;
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("UPDATE IMAGE SET IMAGE =? WHERE ID_IMG=?");
            pst.setBinaryStream(1, imageToStream(image));
            pst.setLong(2, id_img);
            pst.executeQuery();         
        }
        catch(Exception ex)
        {
        }
    }
   */
  public String getName()
    {
        return name;
    }

    public void setName(String i_name)
    {
        name=i_name;
    }
    public long getWidth()
    {
        return id_img;
    }

    public void setWidth(int i_width)
    {
        width=i_width;
    }

    public long getHeight()
    {
        return height;
    }

    public void setHeight(int i_height)
    {
        height=i_height;
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

    public void setAll(long i_id_product,String i_name, moreTools.SerializbleImage image,  int i_width, int i_height)
    {
        this.id_product=i_id_product;
        this.name = i_name;
        this.width = i_width;
        this.height = i_height;
    }

     public void setParamMessage(long userId, long objId ){
      this.userId = userId;
      this.objId = objId;
    }

        public void setParamMessage(long userId ){
      this.userId = userId;
    }

 public void sendMessage(Long id_user,  String nameTables, String message, Long id_obj, int prior) {
        try {
            EJBHelper.sendMessage(new HistoryMessage(id_user, nameTables, message, id_obj), prior);
        } catch (EJBException ex) {
            ex.printStackTrace();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

     public Collection ejbFindImageByProduct(java.lang.Long id_pr) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = EJBHelper.getConnection();
            pst = conn.prepareStatement("SELECT ID_IMG FROM IMAGE WHERE ID_PRODUCT = ?");
            pst.setLong(1, id_pr.longValue());
            rs = pst.executeQuery();
            Vector keys = new Vector();
            while (rs.next()) {
                long id_img = rs.getLong(1);
                keys.addElement(new Long(id_img));
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
}
