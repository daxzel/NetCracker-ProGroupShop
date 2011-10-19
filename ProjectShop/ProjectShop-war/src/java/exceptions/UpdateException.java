/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

/**
 *
 * @author Yra
 */
public class UpdateException extends Exception {
    private String str;
    public UpdateException(){
        super("Ошибка при редактировании профиля");
    }
    public UpdateException(String msg)
    {
        super(msg);
    }
}
