/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBClasses;
import java.util.*;

/**
 *
 * @author ололо
 */
public interface ProductInterface extends DBClassInterface{
    public int getIdCatalog() ;

    @Override
    public int getId() ;

    public String getDescription();

    public String getName();

    public double getPrice();
   public List getOpinionList();

}
