/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBClasses;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import DBManager.DBManager;
import javax.naming.NamingException;

/**
 *
 * @author Yra
 */
public class User implements UserInterface{
private int id_user;
private String name;
private String surname;
private String otchestvo;
private String nik;
private String password;
private Date born;
private String phone;
private String email;

private Role role;

public User(ResultSet rs) throws SQLException, NamingException{
    //SimpleDateFormat formt = new SimpleDateFormat("dd MM yyyy");
      // Date born = formt.parse(brn);
     SimpleDateFormat formt = new SimpleDateFormat("dd MM yyyy");
     setId(rs.getInt(1));
     setName(rs.getString(2));
     setSurname(rs.getString(3));
     setOtchestvo(rs.getString(4));
     setNik(rs.getString(5));
     setPassword(rs.getString(6));
     setBorn(rs.getDate(7));
     setPhone(rs.getString(8));
     setEmail(rs.getString(9));
     setRole(rs.getInt(10));
}
public User(String name, String surname, String otchestvo,String nik,String password,String brn, String phone, String email, String role) throws SQLException, NamingException{
      //SimpleDateFormat formt = new SimpleDateFormat("dd MM yyyy");
      // Date born = formt.parse(brn);
        SimpleDateFormat formt = new SimpleDateFormat("dd MM yyyy");
        Date born=null;
        try {

            born = formt.parse(brn);
        } catch (ParseException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        setName(name);
        setSurname(surname);
        setOtchestvo(otchestvo);
        setNik(nik);
        setPassword(password);
        setBorn(born);
        setPhone(phone);
        setEmail(email);
        setRole(role);
}
public int getId(){
   return id_user;
}
public void setId(int nid_user){
    id_user=nid_user;
}
public String getName(){
    return name;
    }
public void setName(String nname){
    name=nname;
}
public String getSurname(){
    return surname;
    }
public void setSurname(String nsurname){
    surname=nsurname;
}
public String getOtchestvo(){
    return surname;
    }
public void setOtchestvo(String notchestvo){
    otchestvo=notchestvo;
}
public String getNik(){
    return nik;
    }
public void setNik(String nnik){
    nik=nnik;
}
public String getPassword(){
    return password;
    }
public void setPassword(String npassword){
    password=npassword;
}
public Date getBorn(){
    return born;
    }
public void setBorn(Date nborn){
    born=nborn;
}
public String getPhone(){
    return phone;
    }
public void setPhone(String nphone){
    phone=nphone;
}
public String getEmail(){
    return email;
    }
public void setEmail(String nemail){
    email=nemail;
}
public String getRoleName(){
    return role.getName();
    }
public int getRoleId(){
    return role.getId();
    }
public void setRole(int nid_role) throws SQLException, NamingException{
    role = (Role) DBManager.getById("ROLE", nid_role);
}
public void setRole(String roleName) throws SQLException, NamingException{
    role = DBManager.findRoleByName(roleName);
}
public String toString(){
    return surname+" "+name+" "+otchestvo;
}
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package DBClasses;
//import java.util.Date;
//import java.text.SimpleDateFormat;
//import java.sql.ResultSet;
//import java.sql.SQLException;
///**
// *
// * @author Yra
// */
//public class User {
//private int id_user;
//private String name;
//private String surname;
//private String otchestvo;
//private String nik;
//private String password;
//private Date born;
//private String phone;
//private String email;
//private int id_role;
//public User(ResultSet rs) throws SQLException{
//    //SimpleDateFormat formt = new SimpleDateFormat("dd MM yyyy");
//      // Date born = formt.parse(brn);
//    setId_user(rs.getInt(1));
//    setName(rs.getString(2));
//    setSurname(rs.getString(3));
//    setOtchestvo(rs.getString(4));
//    setNik(rs.getString(5));
//    setPassword(rs.getString(6));
//    setBorn(rs.getDate(7));
//    setPhone(rs.getString(8));
//    setEmail(rs.getString(9));
//    setId_role(rs.getInt(10));
//}
//public User(String name, String surname, String otchestvo,String nik,String password, String phone, String email, int id_role) throws SQLException{
//    //SimpleDateFormat formt = new SimpleDateFormat("dd MM yyyy");
//      // Date born = formt.parse(brn);
//  /*  setName(name);
//    setSurname(surname);
//    setOtchestvo(otchestvo);
//    setNik(nik);
//    setPassword();
//    setBorn(rs.getDate(7));
//    setPhone(rs.getString(8));
//    setEmail(rs.getString(9));
//    setId_role(rs.getInt(10));*/
//}
//public int getId_user(){
//   return id_user;
//}
//public void setId_user(int nid_user){
//    id_user=nid_user;
//}
//public String getName(){
//    return name;
//    }
//public void setName(String nname){
//    name=nname;
//}
//public String getSurname(){
//    return surname;
//    }
//public void setSurname(String nsurname){
//    surname=nsurname;
//}
//public String getOtchestvo(){
//    return surname;
//    }
//public void setOtchestvo(String notchestvo){
//    otchestvo=notchestvo;
//}
//public String getNik(){
//    return nik;
//    }
//public void setNik(String nnik){
//    nik=nnik;
//}
//public String getPassword(){
//    return password;
//    }
//public void setPassword(String npassword){
//    password=npassword;
//}
//public Date getBorn(){
//    return born;
//    }
//public void setBorn(Date nborn){
//    born=nborn;
//}
//public String getPhone(){
//    return phone;
//    }
//public void setPhone(String nphone){
//    phone=nphone;
//}
//public String getEmail(){
//    return email;
//    }
//public void setEmail(String nemail){
//    email=nemail;
//}
//public int getId_role(){
//    return id_role;
//    }
//public void setId_role(int nid_role){
//    id_role=nid_role;
//}
//public String toString(){
//    return surname+" "+name+" "+otchestvo;
//}
//}
