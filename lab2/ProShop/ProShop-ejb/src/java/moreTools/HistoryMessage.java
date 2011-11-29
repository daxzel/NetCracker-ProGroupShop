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
    private long objId;
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
        public long getObjId() {
         return objId;
    }
    public HistoryMessage(long userId, String nameTable, String message, long objId ) {
        this.userId = userId;
        this.objId = objId;
        this.nameTable = nameTable;
        this.message = message;
    }
}
