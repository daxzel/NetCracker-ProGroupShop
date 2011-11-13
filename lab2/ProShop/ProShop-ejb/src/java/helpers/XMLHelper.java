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
            boolean catalog = false;
            boolean opinion = false;

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
