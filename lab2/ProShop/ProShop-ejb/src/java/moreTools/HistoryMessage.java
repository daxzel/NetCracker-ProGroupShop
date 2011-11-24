/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package moreTools;

/**
 *
 * @author Yra
 */
public class HistoryMessage implements java.io.Serializable{

    private long userId;
    private String nameTable;
    private String message;

    public String getMessage() {
        return message;
    }
     public String getNameTable() {
        return nameTable;
    }

    public long getUserId() {
        return userId;
    }

    public HistoryMessage(long userId,String nameTable, String message) {
        this.userId = userId;
        this.nameTable = nameTable;
        this.message = message;
    }
}
