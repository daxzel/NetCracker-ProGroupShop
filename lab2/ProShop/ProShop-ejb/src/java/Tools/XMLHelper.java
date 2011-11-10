/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Tools;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
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
            result.write("<?xml-stylesheet  type=\"text/xsl\" href=\"http://localhost:32722/ProShop-war/static/Products.xslt\"?>");

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
}
