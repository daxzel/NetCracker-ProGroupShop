/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package moreTools;

import entityBeans.CatalogBeanRemote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.List;

/**
 *
 * @author Yra
 */
public class CatalogNode {
    public CatalogBeanRemote ctg;
    public List children = new ArrayList();
    public CatalogNode (CatalogBeanRemote ctg){
        this.ctg= ctg;
    }
    public void accept(Map ctx) throws RemoteException{
        CatalogNode parent = (CatalogNode) ctx.get(new Long(ctg.getParentId()));
        if(parent!=null){
            parent.addChild(this);
        }
        ctx.put(new Long(ctg.getId()), this);
    }

    public void addChild (CatalogNode ctg){
        getChildren().add(ctg);
    }
    public Collection getChildren(){
        return children;
    }
    public String  getHtml() throws RemoteException{
        String html = "";
        if(children.isEmpty()){
            html = "<li><a href=\""+ "/ProShop-war/getProductsByCatalog?ID="+ctg.getId() +"\">"+ctg.getName()+"</a></li>"+"\r\n";
        }else{
            html = "<li>"+ctg.getName()+"\r\n";
            html = html + "<ul>"+"\r\n";
            for(int i =0;i<children.size();i++){
                CatalogNode ctg = (CatalogNode) children.get(i);
                html=html+ctg.getHtml();
            }
             html = html + "</ul></li>"+"\r\n";
        }
        return html;
    }
}
