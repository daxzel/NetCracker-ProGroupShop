/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBClasses;

/**
 *
 * @author ололо
 */
public class Catalog implements CatalogInterface{
private int idCatalog;
private int idParent;
private String Name;


    @Override
    public int getId() {
        return idCatalog;
    }

}
