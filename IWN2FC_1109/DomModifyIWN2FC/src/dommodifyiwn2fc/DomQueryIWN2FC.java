package dommodifyiwn2fc;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public class DomQueryIWN2FC {
    public static void main(String[] args) {
        try {
            File file = new File("carsIWN2FC.xml");

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = documentBuilder.parse(file);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            String query1 = "cars/supercars";

            NodeList nodeList = (NodeList) xPath.compile(query1).evaluate(doc, XPathConstants.NODESET);
            printElements(nodeList);
            
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    // prints a list of nodes
    public static void printElements(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println(node.getNodeName());
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                // print the attributes
                NamedNodeMap attributes = element.getAttributes();
                for(int k = 0; k < attributes.getLength(); k++) {
                    System.out.println(attributes.item(k).getNodeName() + ": " + attributes.item(k).getTextContent());
                }

                // print child nodes
                printChildNodes(element);
            }
            System.out.println();
        }
    }

    // prints child nodes of an element
    public static void printChildNodes(Element element) {
        NodeList childNodes = element.getChildNodes();
        for (int j = 0; j < childNodes.getLength(); j++) {
            // if a child node has child nodes, print them recursively
            if (childNodes.item(j).getChildNodes().getLength() > 1) {
                printChildNodes((Element) childNodes.item(j));
            } else if (childNodes.item(j).getTextContent().trim() != "") {
                System.out.println(childNodes.item(j).getNodeName() + ": " + childNodes.item(j).getTextContent().trim());
            }
        }
    }
}
