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

    private Long userId;
    private Long objId;
    private String nameTable;
    private String message;



    public String getMessage() {
        return message;
    }
     public String getNameTable() {
        return nameTable;
    }

    public Long getUserId() {
        return userId;
    }
        public Long getObjId() {
         return objId;
    }
    public HistoryMessage(Long userId, String nameTable, String message, Long objId ) {
        this.userId = userId;
        this.objId = objId;
        this.nameTable = nameTable;
        this.message = message;
    }

        public HistoryMessage(String nameTable, String message, Long objId ) {
        this.objId = objId;
        this.nameTable = nameTable;
        this.message = message;
    }
}
