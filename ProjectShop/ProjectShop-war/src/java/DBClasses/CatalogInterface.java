/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBClasses;
public interface CatalogInterface extends DBClassInterface {

    @Override
    public int getId();

    public int getParentId();

    public String getName();

}