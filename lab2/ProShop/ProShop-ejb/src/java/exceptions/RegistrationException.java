/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

/**
 *
 * @author Admin
 */
public class RegistrationException extends Exception {
    private String str;
    public RegistrationException(){
        super("Ошибка регистрации");
    }
    public RegistrationException(String msg)
    {
        super(msg);
    }
}
