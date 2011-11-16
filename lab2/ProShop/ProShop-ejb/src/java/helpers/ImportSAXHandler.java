/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package helpers;
import org.xml.sax.helpers.DefaultHandler;
/**
 *
 * @author Admin
 */
//public class ImportSAXHandler  extends DefaultHandler
//{
//    boolean error=false;
//
//    Long id=null;
//
//    boolean image = false;
//    boolean imageIdProductBool = false;
//    boolean imageNameBool = false;
//    boolean imageImageBool = false;
//    boolean imageWidthBool = false;
//    boolean imageHeigthBool = false;
//    Long imageIdProduct=null;
//    String imageName=null;
//    String imageImage=null;
//    int imageWidth=0;
//    int imageHeight=0;
//
//
//    boolean productBool = false;
//    boolean productDescriptionBool = false;
//    boolean productIdCatalogBool = false;
//    boolean productIdNameBool = false;
//    boolean productPriceBool = false;
//    String productDescription=null;
//    Long productIdCatalog=null;
//    String productIdName=null;
//    double productPrice=0;
//
//
//    boolean userBool = false;
//    boolean userNameBool = false;
//    boolean userSurnameBool = false;
//    boolean userOtchestvoBool = false;
//    boolean userNikBool = false;
//    boolean userPasswordBool = false;
//    boolean userBornBool = false;
//    boolean userPhoneBool = false;
//    boolean userEmailBool = false;
//    boolean userIdRoleBool = false;
//    String userName = null;
//    String userSurname = null;
//    String userOtchestvo = null;
//    String userNik = null;
//    String userPassword = null;
//    java.util.Date userBorn = null;
//    String userPhone = null;
//    String userEmail = null;
//    Long userIdRole = null;
//
//    boolean roleBool = false;
//    boolean roleNameBool = false;
//    String roleName = null;
//
//    boolean catalogBool = false;
//    boolean catalogIdParentBool = false;
//    boolean catalogNameBool = false;
//    Long catalogIdParent = null;
//    String catalogName = null;
//
//    boolean orderBool = false;
//    boolean orderIdUserBool = false;
//    boolean orderIdProductBool = false;
//    boolean orderStatusBool = false;
//    boolean orderCountBool = false;
//    Long orderIdUser = null;
//    Long orderIdProduct = null;
//    Boolean orderStatus = null;
//    Integer orderCount = null;
//
//    boolean opinionBool = false;
//    boolean opinionIdUserBool = false;
//    boolean opinionIdProductBool = false;
//    boolean opinionTextBool = false;
//    Long opinionIdUser = null;
//    Long opinionIdProduct = null;
//    String opinionText = null;
//
//
//    public void startElement(String uri, String localName,String qName,
//            Attributes attributes) throws SAXException {
//
//        if (image)
//            {
//                if (qName.equalsIgnoreCase("ID_PRODUCT"))
//                {
//                    imageIdProductBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("NAME"))
//                {
//                    imageNameBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("IMAGE"))
//                {
//                    imageImageBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("WIDTH"))
//                {
//                    imageWidthBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("HEIGHT"))
//                {
//                    imageHeigthBool=true;
//                    return;
//                }
//            }
//
//            if (productBool)
//            {
//                if (qName.equalsIgnoreCase("DESCRIPTION"))
//                {
//                    productDescriptionBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("ID_CATALOG"))
//                {
//                    productIdCatalogBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("NAME"))
//                {
//                    productIdNameBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("PRICE"))
//                {
//                    productPriceBool=true;
//                    return;
//                }
//            }
//
//            if (userBool)
//            {
//                if (qName.equalsIgnoreCase("NAME"))
//                {
//                    userNameBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("SURNAME"))
//                {
//                    userSurnameBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("OTCHESTVO"))
//                {
//                    userOtchestvoBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("NIK"))
//                {
//                    userNikBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("PASSWORD"))
//                {
//                    userPasswordBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("BORN"))
//                {
//                    userBornBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("PHONE"))
//                {
//                    userPhoneBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("EMAIL"))
//                {
//                    userEmailBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("ID_ROLE"))
//                {
//                    userIdRoleBool=true;
//                    return;
//                }
//            }
//
//            if (roleBool)
//            {
//                if (qName.equalsIgnoreCase("NAME"))
//                {
//                    roleNameBool=true;
//                    return;
//                }
//            }
//
//            if (catalogBool)
//            {
//                if (qName.equalsIgnoreCase("ID_PARENT"))
//                {
//                    catalogIdParentBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("Name"))
//                {
//                    catalogNameBool=true;
//                    return;
//                }
//            }
//
//            if (orderBool)
//            {
//                if (qName.equalsIgnoreCase("ID_USER"))
//                {
//                    orderIdUserBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("ID_PRODUCT"))
//                {
//                    orderIdProductBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("STATUS"))
//                {
//                    orderStatusBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("KOL_VO"))
//                {
//                    orderCountBool=true;
//                    return;
//                }
//            }
//
//            if (opinionBool)
//            {
//                if (qName.equalsIgnoreCase("ID_USER"))
//                {
//                    opinionIdUserBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("ID_PRODUCT"))
//                {
//                    opinionIdProductBool=true;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("TEXT"))
//                {
//                    opinionTextBool=true;
//                    return;
//                }
//            }
//
//            if (qName.equalsIgnoreCase("IMAGE"))
//            {
//                    image = true;
//                    return;
//            }
//
//            if (qName.equalsIgnoreCase("PRODUCT"))
//            {
//                    productBool = true;
//            }
//
//            if (qName.equalsIgnoreCase("USER"))
//            {
//                    userBool = true;
//                    return;
//            }
//
//            if (qName.equalsIgnoreCase("ROLE"))
//            {
//                    roleBool = true;
//                    return;
//            }
//
//            if (qName.equalsIgnoreCase("ORDER"))
//            {
//                    orderBool = true;
//                    return;
//            }
//
//            if (qName.equalsIgnoreCase("CATALOG"))
//            {
//                    catalogBool = true;
//                    return;
//            }
//
//            if (qName.equalsIgnoreCase("OPINION"))
//            {
//                    opinionBool = true;
//                    return;
//            }
//    }
//
//    public void endElement(String uri, String localName,
//            String qName) throws SAXException {
//
//            if (image)
//            {
//                if (qName.equalsIgnoreCase("ID_PRODUCT"))
//                {
//                    imageIdProductBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("NAME"))
//                {
//                    imageNameBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("IMAGE"))
//                {
//                    imageImageBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("WIDTH"))
//                {
//                    imageWidthBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("HEIGHT"))
//                {
//                    imageHeigthBool=false;
//                    return;
//                }
//            }
//
//            if (productBool)
//            {
//                if (qName.equalsIgnoreCase("DESCRIPTION"))
//                {
//                    productDescriptionBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("ID_CATALOG"))
//                {
//                    productIdCatalogBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("NAME"))
//                {
//                    productIdNameBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("PRICE"))
//                {
//                    productPriceBool=false;
//                    return;
//                }
//            }
//
//            if (userBool)
//            {
//                if (qName.equalsIgnoreCase("NAME"))
//                {
//                    userNameBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("SURNAME"))
//                {
//                    userSurnameBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("OTCHESTVO"))
//                {
//                    userOtchestvoBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("NIK"))
//                {
//                    userNikBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("PASSWORD"))
//                {
//                    userPasswordBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("BORN"))
//                {
//                    userBornBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("PHONE"))
//                {
//                    userPhoneBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("EMAIL"))
//                {
//                    userEmailBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("ID_ROLE"))
//                {
//                    userIdRoleBool=false;
//                    return;
//                }
//            }
//
//            if (roleBool)
//            {
//                if (qName.equalsIgnoreCase("NAME"))
//                {
//                    roleNameBool=false;
//                    return;
//                }
//            }
//
//            if (catalogBool)
//            {
//                if (qName.equalsIgnoreCase("ID_PARENT"))
//                {
//                    catalogIdParentBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("Name"))
//                {
//                    catalogNameBool=false;
//                    return;
//                }
//            }
//
//            if (orderBool)
//            {
//                if (qName.equalsIgnoreCase("ID_USER"))
//                {
//                    orderIdUserBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("ID_PRODUCT"))
//                {
//                    orderIdProductBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("STATUS"))
//                {
//                    orderStatusBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("KOL_VO"))
//                {
//                    orderCountBool=false;
//                    return;
//                }
//            }
//
//            if (opinionBool)
//            {
//                if (qName.equalsIgnoreCase("ID_USER"))
//                {
//                    opinionIdUserBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("ID_PRODUCT"))
//                {
//                    opinionIdProductBool=false;
//                    return;
//                }
//                if (qName.equalsIgnoreCase("TEXT"))
//                {
//                    opinionTextBool=false;
//                    return;
//                }
//            }
//
//            if (qName.equalsIgnoreCase("IMAGE"))
//            {
//                    try
//                    {
//                        EJBHelper.Add.Image(imageIdProduct, imageName, null, imageWidth, imageHeight);
//                    }
//                    catch(Exception ex)
//                    {
//                        error=true;
//                    }
//                    image = false;
//                    imageIdProduct=null;
//                    imageName=null;
//                    imageImage=null;
//                    imageWidth=0;
//                    imageHeight=0;
//                    return;
//            }
//
//            if (qName.equalsIgnoreCase("PRODUCT"))
//            {
//                    try
//                    {
//                        EJBHelper.Add.Product(productDescription, qName, qName, productPrice)
//                    }
//                    catch(Exception ex)
//                    {
//                        error=true;
//                    }
//                    productBool = false;
//                    productDescription=null;
//                    productIdCatalog=null;
//                    productIdName=null;
//                    double productPrice=0;
//            }
//
//            if (qName.equalsIgnoreCase("USER"))
//            {
//                    try
//                    {
//                        EJBHelper.Add.User(userName, userSurname, userOtchestvo, userNik, userPassword,
//                                userBorn, userPhone, userEmail, userIdRole);
//                    }
//                    catch(Exception ex)
//                    {
//                        error=true;
//                    }
//                    userBool = false;
//                    userName = null;
//                    userSurname = null;
//                    userOtchestvo = null;
//                    userNik = null;
//                    userPassword = null;
//                    userBorn = null;
//                    userPhone = null;
//                    userEmail = null;
//                    userIdRole = null;
//                    return;
//            }
//
//            if (qName.equalsIgnoreCase("ROLE"))
//            {
//                    try
//                    {
//                        EJBHelper.Add.
//                    }
//                    catch(Exception ex)
//                    {
//                        error=true;
//                    }
//                    roleBool = false;
//                    roleName = null;
//                    return;
//            }
//
//            if (qName.equalsIgnoreCase("ORDER"))
//            {
//                    try
//                    {
//                        EJBHelper.Add.Order(orderIdUser, orderIdProduct, orderStatus, orderCount);
//                    }
//                    catch(Exception ex)
//                    {
//                        error=true;
//                    }
//                    orderIdUser = null;
//                    orderIdProduct = null;
//                    orderStatus = null;
//                    orderCount = null;
//                    orderBool = false;
//
//                    return;
//            }
//
//            if (qName.equalsIgnoreCase("CATALOG"))
//            {
//                    try
//                    {
//                        EJBHelper.Add.Catalog(catalogIdParent,catalogName);
//                    }
//                    catch(Exception ex)
//                    {
//                        error=true;
//                    }
//                    catalogBool = false;
//                    catalogIdParent = null;
//                    catalogName = null;
//                    return;
//            }
//
//            if (qName.equalsIgnoreCase("OPINION"))
//            {
//                    try
//                    {
//                        EJBHelper.Add.Opinion(opinionIdProduct, opinionIdUser, opinionText);
//                    }
//                    catch(Exception ex)
//                    {
//                        error=true;
//                    }
//                    opinionBool = false;
//                    opinionIdUser = null;
//                    opinionIdProduct = null;
//                    opinionText = null;
//                    return;
//            }
//    }
//
//    public void characters(char ch[], int start, int length) throws SAXException {
//
//        String str = new String(ch, start, length);
//
//        if (image)
//        {
//            if (imageIdProductBool)
//            {
//                imageIdProduct=Long.getLong(str);
//                return;
//            }
//            if (imageNameBool)
//            {
//                imageName=str;
//                return;
//            }
//            if (imageImageBool)
//            {
//                imageImage=str;
//                return;
//            }
//            if (imageIdProductBool)
//            {
//                imageIdProduct=Long.getLong(str);
//                return;
//            }
//            if (imageWidthBool)
//            {
//                imageWidth=Integer.parseInt(str);
//                return;
//            }
//            if (imageHeigthBool)
//            {
//                imageHeight=Integer.parseInt(str);
//                return;
//            }
//        }
//
//        if (productBool)
//        {
//            if (productDescriptionBool)
//            {
//                productDescription=str;
//                return;
//            }
//            if (productIdCatalogBool)
//            {
//                productIdCatalog=Long.getLong(str);
//                return;
//            }
//            if (productIdNameBool)
//            {
//                productIdName=str;
//                return;
//            }
//            if (productPriceBool)
//            {
//                productPrice=Double.parseDouble(str);
//                return;
//            }
//        }
//
//        if (userBool)
//        {
//            if (userNameBool)
//            {
//                userName=str;
//                return;
//            }
//            if (userSurnameBool)
//            {
//                userSurname=str;
//                return;
//            }
//            if (userOtchestvoBool)
//            {
//                userOtchestvo=str;
//                return;
//            }
//            if (userNikBool)
//            {
//                userNik=str;
//                return;
//            }
//            if (userPasswordBool)
//            {
//                userPassword=str;
//                return;
//            }
//            if (userBornBool)
//            {
//                try
//                {
//                    java.text.SimpleDateFormat formt = new java.text.SimpleDateFormat("yyyy-MM-dd");
//                    userBorn = formt.parse(str);
//                }
//                catch(Exception ex)
//                {
//                }
//                return;
//            }
//            if (userPhoneBool)
//            {
//                userPhone=str;
//                return;
//            }
//            if (userEmailBool)
//            {
//                userEmail=str;
//                return;
//            }
//            if (userIdRoleBool)
//            {
//                userIdRole=Long.getLong(str);
//                return;
//            }
//        }
//
//        if (roleBool)
//        {
//            if (roleNameBool)
//            {
//                roleName=str;
//                return;
//            }
//        }
//
//        if (catalogBool)
//        {
//            if (catalogIdParentBool)
//            {
//                catalogIdParent=Long.getLong(str);
//                return;
//            }
//            if (catalogNameBool)
//            {
//                catalogName=str;
//                return;
//            }
//        }
//
//        if (orderBool)
//        {
//            if (orderIdUserBool)
//            {
//                orderIdUser=Long.getLong(str);
//                return;
//            }
//            if (orderIdProductBool)
//            {
//                orderIdProduct=Long.getLong(str);
//                return;
//            }
//            if (orderStatusBool)
//            {
//                orderStatus=Boolean.valueOf(str);
//                return;
//            }
//            if (orderCountBool)
//            {
//                orderCount=Integer.valueOf(str);
//                return;
//            }
//        }
//
//        if (opinionBool)
//        {
//            if (opinionIdUserBool)
//            {
//                opinionIdUser=Long.getLong(str);
//                return;
//            }
//            if (opinionIdProductBool)
//            {
//                opinionIdProduct=Long.getLong(str);
//                return;
//            }
//            if (opinionTextBool)
//            {
//                opinionText=str;
//                return;
//            }
//        }
//    //                            System.out.println("First Name : " + new String(ch, start, length));
//    //                            bfname = false;
//    //                    }
//    //
//    //                    if (blname) {
//    //                            System.out.println("Last Name : " + new String(ch, start, length));
//    //                            blname = false;
//    //                    }
//    //
//    //                    if (bnname) {
//    //                            System.out.println("Nick Name : " + new String(ch, start, length));
//    //                            bnname = false;
//    //                    }
//    //
//    //                    if (bsalary) {
//    //                            System.out.println("Salary : " + new String(ch, start, length));
//    //                            bsalary = false;
//    //                    }
//
//    }
//}

