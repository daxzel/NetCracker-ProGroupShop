/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package helpers;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;

/**
 *
 * @author Admin
 */
public class ImportSAXHandler extends DefaultHandler
{
    Long id=null;

    boolean image = false;
    boolean imageIdProductBool = false;
    boolean imageNameBool = false;
    boolean imageImageBool = false;
    boolean imageWidthBool = false;
    boolean imageHeigthBool = false;
    Long imageIdProduct=null;
    String imageName=null;
    moreTools.ReaderInputStream imageImage=null;
    int imageWidth=0;
    int imageHeight=0;


    boolean productBool = false;
    boolean productDescriptionBool = false;
    boolean productIdCatalogBool = false;
    boolean productIdNameBool = false;
    boolean productPriceBool = false;
    String productDescription=null;
    Long productIdCatalog=null;
    String productName=null;
    double productPrice=0;


    boolean userBool = false;
    boolean userNameBool = false;
    boolean userSurnameBool = false;
    boolean userOtchestvoBool = false;
    boolean userNikBool = false;
    boolean userPasswordBool = false;
    boolean userBornBool = false;
    boolean userPhoneBool = false;
    boolean userEmailBool = false;
    boolean userIdRoleBool = false;
    String userName = null;
    String userSurname = null;
    String userOtchestvo = null;
    String userNik = null;
    String userPassword = null;
    java.sql.Date userBorn = null;
    String userPhone = null;
    String userEmail = null;
    Long userIdRole = null;

    boolean roleBool = false;
    boolean roleNameBool = false;
    String roleName = null;

    boolean catalogBool = false;
    boolean catalogIdParentBool = false;
    boolean catalogNameBool = false;
    Long catalogIdParent = null;
    String catalogName = null;

    boolean orderBool = false;
    boolean orderIdUserBool = false;
    boolean orderIdProductBool = false;
    boolean orderStatusBool = false;
    boolean orderCountBool = false;
    Long orderIdUser = null;
    Long orderIdProduct = null;
    Boolean orderStatus = null;
    Integer orderCount = null;

    boolean opinionBool = false;
    boolean opinionIdUserBool = false;
    boolean opinionIdProductBool = false;
    boolean opinionTextBool = false;
    Long opinionIdUser = null;
    Long opinionIdProduct = null;
    String opinionText = null;

    String[] idUsers;
    String[] idProducts;
    String[] idOrders;
    String[] idOpinions;
    String[] idImages;
    String[] idRoles;
    String[] idCatalogs;

    public ImportSAXHandler(String[] idProducts,
            String[] idUsers, String[] idOrders, String[] idOpinions, String[] idImages, String[] idRoles, String[] idCatalogs)
    {
        this.idUsers=idUsers;
        this.idProducts=idProducts;
        this.idOrders=idOrders;
        this.idOpinions=idOpinions;
        this.idImages=idImages;
        this.idRoles=idRoles;
        this.idCatalogs = idCatalogs;
    }

    private boolean AddUser(String chekedId)
    {
        if (idUsers==null)
        {
            return false;
        }
        for(int i=0; i<idUsers.length; i++)
        {
            if (idUsers[i].equals(chekedId))
            {
                return true;
            }
        }
        return false;
    }

     private boolean AddProduct(String chekedId)
    {
        if (idProducts==null)
        {
            return false;
        }
        for(int i=0; i<idProducts.length; i++)
        {
            if (idProducts[i].equals(chekedId))
            {
                return true;
            }
        }
        return false;
    }

    private boolean AddOrder(String chekedId)
    {
        if (idOrders==null)
        {
            return false;
        }
        for(int i=0; i<idOrders.length; i++)
        {
            if (idOrders[i].equals(chekedId))
            {
                return true;
            }
        }
        return false;
    }

    private boolean AddOpinion(String chekedId)
    {
        if (idOpinions==null)
        {
            return false;
        }
        for(int i=0; i<idOpinions.length; i++)
        {
            if (idOpinions[i].equals(chekedId))
            {
                return true;
            }
        }
        return false;
    }

    private boolean AddImage(String chekedId)
    {
        if (idImages==null)
        {
            return false;
        }
        for(int i=0; i<idImages.length; i++)
        {
            if (idImages[i].equals(chekedId))
            {
                return true;
            }
        }
        return false;
    }

    private boolean AddRole(String chekedId)
    {
        if (idRoles==null)
        {
            return false;
        }
        for(int i=0; i<idRoles.length; i++)
        {
            if (idRoles[i].equals(chekedId))
            {
                return true;
            }
        }
        return false;
    }

    private boolean AddCatalog(String chekedId)
    {
        if (idCatalogs==null)
        {
            return false;
        }
        for(int i=0; i<idCatalogs.length; i++)
        {
            if (idCatalogs[i].equals(chekedId))
            {
                return true;
            }
        }
        return false;
    }

    public void startElement(String uri, String localName,String qName,
            Attributes attributes) throws SAXException
    {

        if (image)
            {
                if (qName.equalsIgnoreCase("ID_PRODUCT"))
                {
                    imageIdProductBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("NAME"))
                {
                    imageNameBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("IMAGE"))
                {
                    imageImageBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("WIDTH"))
                {
                    imageWidthBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("HEIGHT"))
                {
                    imageHeigthBool=true;
                    return;
                }
            }

            if (productBool)
            {
                if (qName.equalsIgnoreCase("DESCRIPTION"))
                {
                    productDescriptionBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("ID_CATALOG"))
                {
                    productIdCatalogBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("NAME"))
                {
                    productIdNameBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("PRICE"))
                {
                    productPriceBool=true;
                    return;
                }
            }

            if (userBool)
            {
                if (qName.equalsIgnoreCase("NAME"))
                {
                    userNameBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("SURNAME"))
                {
                    userSurnameBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("OTCHESTVO"))
                {
                    userOtchestvoBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("NIK"))
                {
                    userNikBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("PASSWORD"))
                {
                    userPasswordBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("BORN"))
                {
                    userBornBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("PHONE"))
                {
                    userPhoneBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("EMAIL"))
                {
                    userEmailBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("ID_ROLE"))
                {
                    userIdRoleBool=true;
                    return;
                }
            }

            if (roleBool)
            {
                if (qName.equalsIgnoreCase("NAME"))
                {
                    roleNameBool=true;
                    return;
                }
            }

            if (catalogBool)
            {
                if (qName.equalsIgnoreCase("ID_PARENT"))
                {
                    catalogIdParentBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("Name"))
                {
                    catalogNameBool=true;
                    return;
                }
            }

            if (orderBool)
            {
                if (qName.equalsIgnoreCase("ID_USER"))
                {
                    orderIdUserBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("ID_PRODUCT"))
                {
                    orderIdProductBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("STATUS"))
                {
                    orderStatusBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("KOL_VO"))
                {
                    orderCountBool=true;
                    return;
                }
            }

            if (opinionBool)
            {
                if (qName.equalsIgnoreCase("ID_USER"))
                {
                    opinionIdUserBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("ID_PRODUCT"))
                {
                    opinionIdProductBool=true;
                    return;
                }
                if (qName.equalsIgnoreCase("TEXT"))
                {
                    opinionTextBool=true;
                    return;
                }
            }

            if (qName.equalsIgnoreCase("IMAGE"))
            {
                    image = true;
                    id = Long.valueOf(attributes.getValue("ID_IMAGE"));
                    return;
            }

            if (qName.equalsIgnoreCase("PRODUCT"))
            {
                    productBool = true;
                    id = Long.valueOf(attributes.getValue("ID_PRODUCT"));
                    return;
            }

            if (qName.equalsIgnoreCase("USER"))
            {
                    userBool = true;
                    id = Long.valueOf(attributes.getValue("ID_USER"));
                    return;
            }

            if (qName.equalsIgnoreCase("ROLE"))
            {
                    roleBool = true;
                    id = Long.valueOf(attributes.getValue("ID_ROLE"));
                    return;
            }

            if (qName.equalsIgnoreCase("ORDER"))
            {
                    orderBool = true;
                    id = Long.valueOf(attributes.getValue("ID_ORDER"));
                    return;
            }

            if (qName.equalsIgnoreCase("CATALOG"))
            {
                    catalogBool = true;
                    id = Long.valueOf(attributes.getValue("ID_CATALOG"));
                    return;
            }

            if (qName.equalsIgnoreCase("OPINION"))
            {
                    opinionBool = true;
                    id = Long.valueOf(attributes.getValue("ID_OPINION"));
                    return;
            }
    }

    public void endElement(String uri, String localName,
            String qName) throws SAXException {

            if (image)
            {
                if (qName.equalsIgnoreCase("ID_PRODUCT"))
                {
                    imageIdProductBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("NAME"))
                {
                    imageNameBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("IMAGE"))
                {
                    imageImageBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("WIDTH"))
                {
                    imageWidthBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("HEIGHT"))
                {
                    imageHeigthBool=false;
                    return;
                }
            }

            if (productBool)
            {
                if (qName.equalsIgnoreCase("DESCRIPTION"))
                {
                    productDescriptionBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("ID_CATALOG"))
                {
                    productIdCatalogBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("NAME"))
                {
                    productIdNameBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("PRICE"))
                {
                    productPriceBool=false;
                    return;
                }
            }

            if (userBool)
            {
                if (qName.equalsIgnoreCase("NAME"))
                {
                    userNameBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("SURNAME"))
                {
                    userSurnameBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("OTCHESTVO"))
                {
                    userOtchestvoBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("NIK"))
                {
                    userNikBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("PASSWORD"))
                {
                    userPasswordBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("BORN"))
                {
                    userBornBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("PHONE"))
                {
                    userPhoneBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("EMAIL"))
                {
                    userEmailBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("ID_ROLE"))
                {
                    userIdRoleBool=false;
                    return;
                }
            }

            if (roleBool)
            {
                if (qName.equalsIgnoreCase("NAME"))
                {
                    roleNameBool=false;
                    return;
                }
            }

            if (catalogBool)
            {
                if (qName.equalsIgnoreCase("ID_PARENT"))
                {
                    catalogIdParentBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("Name"))
                {
                    catalogNameBool=false;
                    return;
                }
            }

            if (orderBool)
            {
                if (qName.equalsIgnoreCase("ID_USER"))
                {
                    orderIdUserBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("ID_PRODUCT"))
                {
                    orderIdProductBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("STATUS"))
                {
                    orderStatusBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("KOL_VO"))
                {
                    orderCountBool=false;
                    return;
                }
            }

            if (opinionBool)
            {
                if (qName.equalsIgnoreCase("ID_USER"))
                {
                    opinionIdUserBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("ID_PRODUCT"))
                {
                    opinionIdProductBool=false;
                    return;
                }
                if (qName.equalsIgnoreCase("TEXT"))
                {
                    opinionTextBool=false;
                    return;
                }
            }

            if (qName.equalsIgnoreCase("IMAGE"))
            {
                    try
                    {
                        if (this.AddImage(id.toString()))
                        {
                            EJBHelper.AddOrUpdate.Image(id.longValue(),imageIdProduct.longValue(), imageName, imageWidth, imageHeight);
                        }
                    }
                    catch(Exception ex)
                    {
                        throw new SAXException(ex.getMessage());
                    }
                    image = false;
                    imageIdProduct=null;
                    imageName=null;
                    imageImage=null;
                    imageWidth=0;
                    imageHeight=0;
                    return;
            }

            if (qName.equalsIgnoreCase("PRODUCT"))
            {
                    try
                    {
                        if (this.AddProduct(id.toString()))
                        {
                            EJBHelper.AddOrUpdate.Product(id.longValue(), productDescription,
                                    productIdCatalog.longValue(), productName, productPrice);
                        }
                    }
                    catch(Exception ex)
                    {
                        throw new SAXException(ex.getMessage());
                    }
                    productBool = false;
                    productDescription=null;
                    productIdCatalog=null;
                    productName=null;
                    double productPrice=0;
            }

            if (qName.equalsIgnoreCase("USER"))
            {
                    try
                    {
                        if (AddUser(id.toString()))
                        {
                             EJBHelper.AddOrUpdate.User(id.longValue(), userName, userSurname, userOtchestvo,
                                userNik, userPassword, userBorn, userPhone, userEmail, userIdRole);
                        }

                    }
                    catch(Exception ex)
                    {
                        throw new SAXException(ex.getMessage());
                    }
                    userBool = false;
                    userName = null;
                    userSurname = null;
                    userOtchestvo = null;
                    userNik = null;
                    userPassword = null;
                    userBorn = null;
                    userPhone = null;
                    userEmail = null;
                    userIdRole = null;
                    return;
            }

            if (qName.equalsIgnoreCase("ROLE"))
            {
                    try
                    {
                        if (this.AddRole(id.toString()))
                        {
                            EJBHelper.AddOrUpdate.Role(id.longValue(), roleName);
                        }
                    }
                    catch(Exception ex)
                    {
                        throw new SAXException(ex.getMessage());
                    }
                    roleBool = false;
                    roleName = null;
                    return;
            }

            if (qName.equalsIgnoreCase("ORDER"))
            {
                    try
                    {
                        if (this.AddOrder(id.toString()))
                        {
                            EJBHelper.AddOrUpdate.Order(id.longValue(),orderIdUser.longValue(),
                                    orderIdProduct.longValue(), orderStatus, orderCount.intValue());
                        }
                    }
                    catch(Exception ex)
                    {
                        throw new SAXException(ex.getMessage());
                    }
                    orderIdUser = null;
                    orderIdProduct = null;
                    orderStatus = null;
                    orderCount = null;
                    orderBool = false;

                    return;
            }

            if (qName.equalsIgnoreCase("CATALOG"))
            {
                    try
                    {
                        if (this.AddCatalog(id.toString()))
                        {
                            EJBHelper.AddOrUpdate.Catalog(id.longValue(),catalogIdParent.longValue(),catalogName);
                        }
                    }
                    catch(Exception ex)
                    {
                        throw new SAXException(ex.getMessage());
                    }
                    catalogBool = false;
                    catalogIdParent = null;
                    catalogName = null;
                    return;
            }

            if (qName.equalsIgnoreCase("OPINION"))
            {
                    try
                    {
                        if (this.AddOpinion(id.toString()))
                        {
                            EJBHelper.AddOrUpdate.Opinion(id.longValue(),opinionIdProduct.longValue(),
                                    opinionIdUser.longValue(), opinionText);
                        }
                    }
                    catch(Exception ex)
                    {
                        throw new SAXException(ex.getMessage());
                    }
                    opinionBool = false;
                    opinionIdUser = null;
                    opinionIdProduct = null;
                    opinionText = null;
                    return;
            }
    }

    public void characters(char ch[], int start, int length) throws SAXException {

        String str = new String(ch, start, length);

        if (image)
        {
            if (imageIdProductBool)
            {
                imageIdProduct=Long.valueOf(str);
                return;
            }
            if (imageNameBool)
            {
                imageName=str;
                return;
            }
            if (imageImageBool)
            {
                try
                {
                    imageImage=new moreTools.ReaderInputStream(new java.io.CharArrayReader(ch,start,length));
                }
                catch(Exception ex)
                {
                    throw new SAXException(ex.getMessage());
                }
                return;
            }
            if (imageIdProductBool)
            {
                imageIdProduct=Long.valueOf(str);
                return;
            }
            if (imageWidthBool)
            {
                imageWidth=Integer.parseInt(str);
                return;
            }
            if (imageHeigthBool)
            {
                imageHeight=Integer.parseInt(str);
                return;
            }
        }

        if (productBool)
        {
            if (productDescriptionBool)
            {
                productDescription=str;
                return;
            }
            if (productIdCatalogBool)
            {
                productIdCatalog=Long.valueOf(str);
                return;
            }
            if (productIdNameBool)
            {
                productName=str;
                return;
            }
            if (productPriceBool)
            {
                productPrice=Double.parseDouble(str);
                return;
            }
        }

        if (userBool)
        {
            if (userNameBool)
            {
                userName=str;
                return;
            }
            if (userSurnameBool)
            {
                userSurname=str;
                return;
            }
            if (userOtchestvoBool)
            {
                userOtchestvo=str;
                return;
            }
            if (userNikBool)
            {
                userNik=str;
                return;
            }
            if (userPasswordBool)
            {
                userPassword=str;
                return;
            }
            if (userBornBool)
            {
                try
                {
                    java.text.SimpleDateFormat formt = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    userBorn = new java.sql.Date(formt.parse(str).getTime());
                }
                catch(Exception ex)
                {
                    throw new SAXException(ex.getMessage());
                }
                return;
            }
            if (userPhoneBool)
            {
                userPhone=str;
                return;
            }
            if (userEmailBool)
            {
                userEmail=str;
                return;
            }
            if (userIdRoleBool)
            {
                userIdRole=Long.valueOf(str);
                return;
            }
        }

        if (roleBool)
        {
            if (roleNameBool)
            {
                roleName=str;
                return;
            }
        }

        if (catalogBool)
        {
            if (catalogIdParentBool)
            {
                catalogIdParent=Long.valueOf(str);
                return;
            }
            if (catalogNameBool)
            {
                catalogName=str;
                return;
            }
        }

        if (orderBool)
        {
            if (orderIdUserBool)
            {
                orderIdUser=Long.valueOf(str);
                return;
            }
            if (orderIdProductBool)
            {
                orderIdProduct=Long.valueOf(str);
                return;
            }
            if (orderStatusBool)
            {
                orderStatus=Boolean.valueOf(str);
                return;
            }
            if (orderCountBool)
            {
                orderCount=Integer.valueOf(str);
                return;
            }
        }

        if (opinionBool)
        {
            if (opinionIdUserBool)
            {
                opinionIdUser=Long.valueOf(str);
                return;
            }
            if (opinionIdProductBool)
            {
                opinionIdProduct=Long.valueOf(str);
                return;
            }
            if (opinionTextBool)
            {
                opinionText=str;
                return;
            }
        }
    }
}

