/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.validation.*;
import javax.xml.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Admin
 */
public class XMLHelper
{
    public static void ProductToXml(java.util.Collection products, PrintWriter result) throws Exception
    {
        try
        {
           // result.write("<?xml-stylesheet  type=\"text/xsl\" href=\"/ProShop-war/static/Products.xslt\"?>");

            DocumentBuilderFactory factory
              = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = null;

            builder = factory.newDocumentBuilder();

            org.w3c.dom.DOMImplementation impl = builder.getDOMImplementation();

            Document doc = impl.createDocument(null,null,null);
            Element productsE = doc.createElement("PRODUCTS");
            doc.appendChild(productsE);

            java.util.Iterator it = products.iterator();
            while(it.hasNext())
            {
                entityBeans.ProductBeanRemote product = (entityBeans.ProductBeanRemote)it.next();

                Element productE = doc.createElement("PRODUCT");
                productsE.appendChild(productE);
                productE.setAttribute("ID", java.lang.Long.toString(product.getId()));

                Element drscriptionE = doc.createElement("DESCRIPTION");
                drscriptionE.appendChild(doc.createTextNode(product.getDescription()));
                productE.appendChild(drscriptionE);

                Element idCatalogE = doc.createElement("ID_CATALOG");
                idCatalogE.appendChild(doc.createTextNode(java.lang.Long.toString(product.getIdCatalog())));
                productE.appendChild(idCatalogE);

                Element nameE = doc.createElement("NAME");
                nameE.appendChild(doc.createTextNode(product.getName()));
                productE.appendChild(nameE);

                Element priceE = doc.createElement("PRICE");
                priceE.appendChild(doc.createTextNode(java.lang.Double.toString(product.getPrice())));
                productE.appendChild(priceE);

            }

            Node pi = doc.createProcessingInstruction("xml-stylesheet",
                "type=\"text/xsl\" href=\"/ProShop-war/static/Products.xslt\"");

            doc.insertBefore(pi, productsE);

            DOMSource domSource = new DOMSource(doc);
            TransformerFactory tf = TransformerFactory.newInstance();

            Transformer transformer = tf.newTransformer();
       //     transformer.setOutputProperty(OutputKeys, "yes");



            StreamResult sr = new StreamResult(result);
            transformer.transform(domSource, sr);
            result.flush();
        }
        catch(javax.xml.transform.TransformerConfigurationException ex)
        {
            throw new Exception("Ощибка в реализации XMLHelper");
        }
        catch(java.rmi.RemoteException ex)
        {
            throw new Exception("Ощибка подключения к EJB");
        }
        catch(ParserConfigurationException ex)
        {
            throw new Exception("Ощибка в реализации XMLHelper");
        }

    }
    public static void OpinionToXml(java.util.Collection opinions, PrintWriter result) throws Exception
    {
        try
        {


            DocumentBuilderFactory factory
              = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = null;

            builder = factory.newDocumentBuilder();

            org.w3c.dom.DOMImplementation impl = builder.getDOMImplementation();

            Document doc = impl.createDocument(null,null,null);
            Element opinionsE = doc.createElement("OPINIONS");
            doc.appendChild(opinionsE);

            java.util.Iterator it = opinions.iterator();
            while(it.hasNext())
            {
                entityBeans.OpinionBeanRemote opinion = (entityBeans.OpinionBeanRemote)it.next();

                Element opinionE = doc.createElement("OPINION");
                opinionsE.appendChild(opinionE);
                opinionE.setAttribute("ID", java.lang.Long.toString(opinion.getIdOpinion()));

                Element idProductE = doc.createElement("ID_PRODUCT");
                idProductE.appendChild(doc.createTextNode (java.lang.Long.toString(opinion.getIdProduct())));
                opinionE.appendChild(idProductE);

                Element idUserE = doc.createElement("ID_USER");
                idUserE.appendChild(doc.createTextNode(java.lang.Long.toString(opinion.getIdUser())));
                opinionE.appendChild(idUserE);

                Element textE = doc.createElement("TEXT");
                textE.appendChild(doc.createTextNode(opinion.getText()));
                opinionE.appendChild(textE);



            }

            Node pi = doc.createProcessingInstruction("xml-stylesheet",
                "type=\"text/xsl\" href=\"/ProShop-war/static/Opinions.xslt\"");

            doc.insertBefore(pi, opinionsE);

            DOMSource domSource = new DOMSource(doc);
            TransformerFactory tf = TransformerFactory.newInstance();

            Transformer transformer = tf.newTransformer();




            StreamResult sr = new StreamResult(result);
            transformer.transform(domSource, sr);
            result.flush();
        }
        catch(javax.xml.transform.TransformerConfigurationException ex)
        {
            throw new Exception("Ощибка в реализации XMLHelper");
        }
        catch(java.rmi.RemoteException ex)
        {
            throw new Exception("Ощибка подключения к EJB");
        }
        catch(ParserConfigurationException ex)
        {
            throw new Exception("Ощибка в реализации XMLHelper");
        }

    }
        public static void RoleToXml(java.util.Collection roles, PrintWriter result) throws Exception
    {
        try
        {

            DocumentBuilderFactory factory
              = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = null;

            builder = factory.newDocumentBuilder();

            org.w3c.dom.DOMImplementation impl = builder.getDOMImplementation();

            Document doc = impl.createDocument(null,null,null);
            Element rolesE = doc.createElement("ROLES");
            doc.appendChild(rolesE);

            java.util.Iterator it = roles.iterator();
            while(it.hasNext())
            {

                entityBeans.RoleBeanRemote role = (entityBeans.RoleBeanRemote)it.next();

                Element roleE = doc.createElement("ROLE");
                roleE.setAttribute("ID", Long.toString(role.getId().longValue()));
                rolesE.appendChild(roleE);

                Element nameE = doc.createElement("NAME");
                nameE.appendChild(doc.createTextNode(role.getName()));
                roleE.appendChild(nameE);


            }

            Node pi = doc.createProcessingInstruction("xml-stylesheet",
                "type=\"text/xsl\" href=\"/ProShop-war/static/Roles.xslt\"");

            doc.insertBefore(pi, rolesE);

            DOMSource domSource = new DOMSource(doc);
            TransformerFactory tf = TransformerFactory.newInstance();

            Transformer transformer = tf.newTransformer();



            StreamResult sr = new StreamResult(result);
            transformer.transform(domSource, sr);
            result.flush();
        }
        catch(javax.xml.transform.TransformerConfigurationException ex)
        {
            throw new Exception("Ощибка в реализации XMLHelper");
        }
        catch(java.rmi.RemoteException ex)
        {
            throw new Exception("Ощибка подключения к EJB");
        }
        catch(ParserConfigurationException ex)
        {
            throw new Exception("Ощибка в реализации XMLHelper");
        }

    }
         public static void OrderToXml(java.util.Collection orders, PrintWriter result) throws Exception
    {
        try
        {


            DocumentBuilderFactory factory
              = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = null;

            builder = factory.newDocumentBuilder();

            org.w3c.dom.DOMImplementation impl = builder.getDOMImplementation();

            Document doc = impl.createDocument(null,null,null);
            Element ordersE = doc.createElement("ORDERS");
            doc.appendChild(ordersE);

            java.util.Iterator it = orders.iterator();
            while(it.hasNext())
            {
                entityBeans.OrderBeanRemote order = (entityBeans.OrderBeanRemote)it.next();

                Element orderE = doc.createElement("ORDER");
                ordersE.appendChild(orderE);
                orderE.setAttribute("ID", java.lang.Long.toString(order.getId()));

                Element idUserE = doc.createElement("ID_USER");
                idUserE.appendChild(doc.createTextNode(Long.toString(order.getIdUser())));
                orderE.appendChild(idUserE);

                Element idProductE = doc.createElement("ID_PRODUCT");
                idProductE.appendChild(doc.createTextNode(java.lang.Long.toString(order.getIdProduct().longValue())));
                orderE.appendChild(idProductE);

                Element statusE = doc.createElement("STATUS");
                statusE.appendChild(doc.createTextNode(Boolean.toString(order.getStatus())));
                orderE.appendChild(statusE);

                Element amountE = doc.createElement("KOL_VO");
                amountE.appendChild(doc.createTextNode(java.lang.Double.toString(order.getAmount())));
                orderE.appendChild(amountE);

            }

            Node pi = doc.createProcessingInstruction("xml-stylesheet",
                "type=\"text/xsl\" href=\"/ProShop-war/static/Orders.xslt\"");

            doc.insertBefore(pi, ordersE);

            DOMSource domSource = new DOMSource(doc);
            TransformerFactory tf = TransformerFactory.newInstance();

            Transformer transformer = tf.newTransformer();



            StreamResult sr = new StreamResult(result);
            transformer.transform(domSource, sr);
            result.flush();
        }
        catch(javax.xml.transform.TransformerConfigurationException ex)
        {
            throw new Exception("Ощибка в реализации XMLHelper");
        }
        catch(java.rmi.RemoteException ex)
        {
            throw new Exception("Ощибка подключения к EJB");
        }
        catch(ParserConfigurationException ex)
        {
            throw new Exception("Ощибка в реализации XMLHelper");
        }

    }
         public static void CatalogToXml(java.util.Collection catalogs, PrintWriter result) throws Exception
    {
        try
        {


            DocumentBuilderFactory factory
              = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = null;

            builder = factory.newDocumentBuilder();

            org.w3c.dom.DOMImplementation impl = builder.getDOMImplementation();

            Document doc = impl.createDocument(null,null,null);
            Element catalogsE = doc.createElement("CATALOGS");
            doc.appendChild(catalogsE);

            java.util.Iterator it = catalogs.iterator();
            while(it.hasNext())
            {
                entityBeans.CatalogBeanRemote catalog = (entityBeans.CatalogBeanRemote)it.next();

                Element catalogE = doc.createElement("CATALOG");
                catalogsE.appendChild(catalogE);
                catalogE.setAttribute("ID", java.lang.Long.toString(catalog.getId()));

                Element idParentE = doc.createElement("ID_PARENT");
                idParentE.appendChild(doc.createTextNode(Long.toString(catalog.getParentId())));
                catalogE.appendChild(idParentE);

                Element nameE = doc.createElement("NAME");
                nameE.appendChild(doc.createTextNode(catalog.getName()));
                catalogE.appendChild(nameE);



            }

            Node pi = doc.createProcessingInstruction("xml-stylesheet",
                "type=\"text/xsl\" href=\"/ProShop-war/static/Catalogs.xslt\"");

            doc.insertBefore(pi, catalogsE);

            DOMSource domSource = new DOMSource(doc);
            TransformerFactory tf = TransformerFactory.newInstance();

            Transformer transformer = tf.newTransformer();




            StreamResult sr = new StreamResult(result);
            transformer.transform(domSource, sr);
            result.flush();
        }
        catch(javax.xml.transform.TransformerConfigurationException ex)
        {
            throw new Exception("Ощибка в реализации XMLHelper");
        }
        catch(java.rmi.RemoteException ex)
        {
            throw new Exception("Ощибка подключения к EJB");
        }
        catch(ParserConfigurationException ex)
        {
            throw new Exception("Ощибка в реализации XMLHelper");
        }

    }
         public static void UserToXml(java.util.Collection users, PrintWriter result) throws Exception
    {
        try
        {

            DocumentBuilderFactory factory
              = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = null;

            builder = factory.newDocumentBuilder();

            org.w3c.dom.DOMImplementation impl = builder.getDOMImplementation();

            Document doc = impl.createDocument(null,null,null);
            Element usersE = doc.createElement("USERS");
            doc.appendChild(usersE);

            java.util.Iterator it = users.iterator();
            while(it.hasNext())
            {
                entityBeans.UserBeanRemote user = (entityBeans.UserBeanRemote)it.next();

                Element userE = doc.createElement("USER");
                usersE.appendChild(userE);
                userE.setAttribute("ID", java.lang.Long.toString(user.getId()));

                Element nameE = doc.createElement("NAME");
                nameE.appendChild(doc.createTextNode(user.getName()));
                userE.appendChild(nameE);

                Element surenameE = doc.createElement("SURNAME");
                surenameE.appendChild(doc.createTextNode(user.getSurname()));
                userE.appendChild(surenameE);

                Element otchestvoE = doc.createElement("OTCHESTVO");
                otchestvoE.appendChild(doc.createTextNode(user.getOtchestvo()));
                userE.appendChild(otchestvoE);

                Element nikE = doc.createElement("NIK");
                nikE.appendChild(doc.createTextNode(user.getNik()));
                userE.appendChild(nikE);

                Element passwordE = doc.createElement("PASSWORD");
                passwordE.appendChild(doc.createTextNode(user.getPassword()));
                userE.appendChild(passwordE);

                Element bornE = doc.createElement("BORN");
                bornE.appendChild(doc.createTextNode(user.getBorn().toString()));
                userE.appendChild(bornE);

                Element phoneE = doc.createElement("PHONE");
                phoneE.appendChild(doc.createTextNode(user.getPhone()));
                userE.appendChild(phoneE);

                Element emailE = doc.createElement("EMAIL");
                emailE.appendChild(doc.createTextNode(user.getEmail()));
                userE.appendChild(emailE);

                Element idRoleE = doc.createElement("ID_ROLE");
                idRoleE.appendChild(doc.createTextNode(java.lang.Long.toString(user.getRoleId())));
                userE.appendChild(idRoleE);

            }

            Node pi = doc.createProcessingInstruction("xml-stylesheet",
                "type=\"text/xsl\" href=\"/ProShop-war/static/Users.xslt\"");

            doc.insertBefore(pi, usersE);

            DOMSource domSource = new DOMSource(doc);
            TransformerFactory tf = TransformerFactory.newInstance();

            Transformer transformer = tf.newTransformer();
 



            StreamResult sr = new StreamResult(result);
            transformer.transform(domSource, sr);
            result.flush();
        }
        catch(javax.xml.transform.TransformerConfigurationException ex)
        {
            throw new Exception("Ощибка в реализации XMLHelper");
        }
        catch(java.rmi.RemoteException ex)
        {
            throw new Exception("Ощибка подключения к EJB");
        }
        catch(ParserConfigurationException ex)
        {
            throw new Exception("Ощибка в реализации XMLHelper");
        }

    }
public static void ImageToXml(java.util.Collection images, PrintWriter result) throws Exception
    {
        try
        {

            DocumentBuilderFactory factory
              = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = null;

            builder = factory.newDocumentBuilder();

            org.w3c.dom.DOMImplementation impl = builder.getDOMImplementation();

            Document doc = impl.createDocument(null,null,null);
            Element imagesE = doc.createElement("IMAGES");
            doc.appendChild(imagesE);

            java.util.Iterator it = images.iterator();
            while(it.hasNext())
            {
                entityBeans.ImageBeanRemote image = (entityBeans.ImageBeanRemote)it.next();

                Element imageE = doc.createElement("ID_IMAGE");
                imagesE.appendChild(imageE);
                imageE.setAttribute("ID", java.lang.Long.toString(image.getId_img()));

                Element idProductE = doc.createElement("ID_PRODUCT");
                idProductE.appendChild(doc.createTextNode(Long.toString(image.getId_product())));
                imageE.appendChild(idProductE);

                Element imageByteE = doc.createElement("IMAGE");
                imageByteE.appendChild(doc.createTextNode(image.getImage().toString()));
                imageE.appendChild(imageByteE);

                Element widthE = doc.createElement("WIDTH");
                widthE.appendChild(doc.createTextNode(Long.toString(image.getWidth())));
                imageE.appendChild(widthE);

                Element heightE = doc.createElement("HEIGHT");
                heightE.appendChild(doc.createTextNode(java.lang.Long.toString(image.getHeaight())));
                imageE.appendChild(heightE);

            }

            Node pi = doc.createProcessingInstruction("xml-stylesheet",
                "type=\"text/xsl\" href=\"/ProShop-war/static/Images.xslt\"");

            doc.insertBefore(pi, imagesE);

            DOMSource domSource = new DOMSource(doc);
            TransformerFactory tf = TransformerFactory.newInstance();

            Transformer transformer = tf.newTransformer();
       



            StreamResult sr = new StreamResult(result);
            transformer.transform(domSource, sr);
            result.flush();
        }
        catch(javax.xml.transform.TransformerConfigurationException ex)
        {
            throw new Exception("Ощибка в реализации XMLHelper");
        }
        catch(java.rmi.RemoteException ex)
        {
            throw new Exception("Ощибка подключения к EJB");
        }
        catch(ParserConfigurationException ex)
        {
            throw new Exception("Ощибка в реализации XMLHelper");
        }

    }
    public static boolean CheckSchema(InputStream xml, File schemeFiles) throws Exception
    {
        try
        {
            DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = parser.parse(xml);

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Source schemaFile = new StreamSource(schemeFiles);
            Schema schema = factory.newSchema(schemaFile);

            Validator validator = schema.newValidator();

            try {
                validator.validate(new DOMSource(document));
                return true;
            } catch (SAXException e) {
                return false;
            }
        }
        catch(Exception ex)
        {
            throw new Exception("Ошибка в методе CheckSheme");
        }
    }

    public void importOfXML(InputStream xml) throws Exception
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();

	DefaultHandler handler = new DefaultHandler() {

            boolean image = false;
            boolean imageIdProduct = false;
            boolean imageName = false;
            boolean imageImage = false;
            boolean imageWidth = false;
            boolean imageHeigth = false;

            boolean product = false;
            boolean productDescription = false;
            boolean productIdCatalog = false;
            boolean productIdName = false;
            boolean productPrice = false;

            boolean user = false;
            boolean userName = false;
            boolean userSurname = false;
            boolean userOtchestvo = false;
            boolean userNik = false;
            boolean userPassword = false;
            boolean userBorn = false;
            boolean userPhone = false;
            boolean userEmail = false;
            boolean userIdRole = false;

            boolean role = false;
            boolean roleName = false;



            boolean order = false;
            boolean orderIdUser = false;
            boolean orderIdProduct = false;
            boolean orderStatus = false;
            boolean orderAmount = false;


            boolean catalog = false;
            boolean catalogIdParent = false;
            boolean catalogName = false;

            boolean opinion = false;
            boolean opinionIdProduct = false;
            boolean opinionIdUser = false;
            boolean opinionText = false;

            public void startElement(String uri, String localName,String qName,
                    Attributes attributes) throws SAXException {

                    if (image)
                    {
                        if (qName.equalsIgnoreCase("ID_PRODUCT"))
                        {
                            imageIdProduct=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("NAME"))
                        {
                            imageName=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("IMAGE"))
                        {
                            imageImage=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("WIDTH"))
                        {
                            imageWidth=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("HEIGHT"))
                        {
                            imageHeigth=true;
                            return;
                        }
                    }

                    if (product)
                    {
                        if (qName.equalsIgnoreCase("DESCRIPTION"))
                        {
                            productDescription=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("ID_CATALOG"))
                        {
                            productIdCatalog=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("NAME"))
                        {
                            productIdName=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("PRICE"))
                        {
                            productPrice=true;
                            return;
                        }
                    }

                    if (user)
                    {
                        if (qName.equalsIgnoreCase("NAME"))
                        {
                            userName=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("SURNAME"))
                        {
                            userSurname=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("OTCHESTVO"))
                        {
                            userOtchestvo=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("NIK"))
                        {
                            userNik=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("PASSWORD"))
                        {
                            userPassword=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("BORN"))
                        {
                            userBorn=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("PHONE"))
                        {
                            userPhone=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("EMAIL"))
                        {
                            userEmail=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("ID_ROLE"))
                        {
                            userIdRole=true;
                            return;
                        }
                    }

                    if (order)
                    {

                        if (qName.equalsIgnoreCase("ID_USER"))
                        {
                            orderIdUser=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("ID_PRODUCT"))
                        {
                            orderIdProduct=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("STATUS"))
                        {
                            orderStatus=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("KOL_VO"))
                        {
                            orderAmount=true;
                            return;
                        }
                    }
                     if (catalog)
                    {

                        if (qName.equalsIgnoreCase("ID_PARENT"))
                        {
                            catalogIdParent=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("NAME"))
                        {
                            catalogName=true;
                            return;
                        }

                    }

                     if (opinion)
                    {

                        if (qName.equalsIgnoreCase("ID_PRODUCT"))
                        {
                            opinionIdProduct=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("ID_USER"))
                        {
                            opinionIdUser=true;
                            return;
                        }
                        if (qName.equalsIgnoreCase("TEXT"))
                        {
                            opinionText=true;
                            return;
                        }

                    }
                     if (order)
                    {

                        if (qName.equalsIgnoreCase("NAME"))
                        {
                            roleName=true;
                            return;
                        }

                    }



                    if (qName.equalsIgnoreCase("IMAGE"))
                    {
                            image = true;
                            return;
                    }

                    if (qName.equalsIgnoreCase("PRODUCT"))
                    {
                            product = true;
                    }

                    if (qName.equalsIgnoreCase("USER"))
                    {
                            user = true;
                            return;
                    }

                    if (qName.equalsIgnoreCase("ROLE"))
                    {
                            role = true;
                            return;
                    }

                    if (qName.equalsIgnoreCase("ORDER"))
                    {
                            order = true;
                            return;
                    }

                    if (qName.equalsIgnoreCase("CATALOG"))
                    {
                            catalog = true;
                            return;
                    }

                    if (qName.equalsIgnoreCase("OPINION"))
                    {
                            opinion = true;
                            return;
                    }

            }

            public void endElement(String uri, String localName,
                    String qName) throws SAXException {
            }

            public void characters(char ch[], int start, int length) throws SAXException {

//                    if (bfname) {
//                            System.out.println("First Name : " + new String(ch, start, length));
//                            bfname = false;
//                    }
//
//                    if (blname) {
//                            System.out.println("Last Name : " + new String(ch, start, length));
//                            blname = false;
//                    }
//
//                    if (bnname) {
//                            System.out.println("Nick Name : " + new String(ch, start, length));
//                            bnname = false;
//                    }
//
//                    if (bsalary) {
//                            System.out.println("Salary : " + new String(ch, start, length));
//                            bsalary = false;
//                    }

            }

         };

       saxParser.parse("c:\\file.xml", handler);

//     } catch (Exception e) {
//       e.printStackTrace();
//     }

    }
}
