/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

/**
 *
 * @author Yra
 */
public class PasswordException extends Exception {
    private String str;
    public PasswordException(){
        str="pass exception";
    }
}
