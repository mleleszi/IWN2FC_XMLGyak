package dommodifyiwn2fc;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomModifyIWN2FC {
    public static void main(String[] args) {
        try {
            File file = new File("carsIWN2FC.xml");

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();

            Node cars = document.getFirstChild();
            Node supercars = document.getElementsByTagName("supercars").item(0);

            NamedNodeMap attr = supercars.getAttributes();
            Node nodeAttr = attr.getNamedItem("company");
            nodeAttr.setTextContent("Lamborghini");

            NodeList list = supercars.getChildNodes();

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
                    Element element = (Element) node;

                    if ("carname".equals(element.getNodeName())) {
                        if ("Ferrari 01".equals(element.getTextContent())) {
                            element.setTextContent("Lamborghini 01");
                        }
                    }
                }
            }

            NodeList childNodes = cars.getChildNodes();

            for (int j = 0; j < childNodes.getLength(); j++) {
                Node node = childNodes.item(j);

                if ("luxurycars".equals(node.getNodeName())) {
                    cars.removeChild(node);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);

            System.out.println("---- Modositott fajl -----");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
