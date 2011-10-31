/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBClasses;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Yra
 */
public class Role implements RoleInterface {
private int id_role;
private String name;
public int getId(){
    return id_role;
}
public void setId_role(int nId_role){
    id_role=nId_role;
}
public String getName(){
    return name;
}
public void setName(String nname){
    name=nname;
}
public Role(ResultSet  rs) throws SQLException{
    setId_role(rs.getInt(1));
    setName(rs.getString(2));
}
public Role(int id_role, String name) throws SQLException{
    setId_role(id_role);
    setName(name);
}

}
