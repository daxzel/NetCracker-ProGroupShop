/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBClasses;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author ололо
 */
public class Catalog implements CatalogInterface {

    private int id;
    private int parentId;
    private String name;

    public Catalog(ResultSet rs) throws SQLException {
        setId(rs.getInt(1));
        setParentId(rs.getInt(2));
        setName(rs.getString(3));
    }

    public Catalog(int id, int pId, String name) throws SQLException, NamingException{
        setId(id);
        setParentId(pId);
        setName(name);
}

    public void setId(int tIp) {
        id = tIp;
    }

    public void setParentId(int tpId) {
        parentId = tpId;
    }

    public void setName(String tName) {
        name = tName;
    }

   // @Override
    public int getId() {
        return id;
    }

    //@Override
    public int getParentId() {
        return parentId;
    }

  //  @Override
    public String getName() {
        return name;
    }


}