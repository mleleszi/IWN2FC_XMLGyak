package hu.domparse.iwn2fc;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import static hu.domparse.iwn2fc.DomReadIWN2FC.printElements;

public class DomModifyIWN2FC {
    public static void modify() {
        try {
            // open file
            File file = new File("XMLiwn2fc.xml");

            // create a DocumentBuilder
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            // create document from the opened file
            Document doc = documentBuilder.parse(file);

            // normalize document
            doc.getDocumentElement().normalize();

            // change the employee count of airline with id l1
            NodeList nodes = doc.getElementsByTagName("legitarsasag");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    if (node.getAttributes().getNamedItem("legitarsasagId").getTextContent().equals("l1")) {
                        NodeList childNodes = node.getChildNodes();
                        for (int j = 0; j < childNodes.getLength(); j++) {
                            Node childNode = childNodes.item(j);
                            if (childNode.getNodeName().equals("dolgozokSzama")) {
                                childNode.setTextContent("60000");
                            }
                        }
                    }
                }
            }

            // change the price of a booking
            nodes = doc.getElementsByTagName("foglalas");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    if (node.getAttributes().getNamedItem("utasId").getTextContent().equals("u1") &&
                            node.getAttributes().getNamedItem("jaratId").getTextContent().equals("j1")) {
                        NodeList childNodes = node.getChildNodes();
                        for (int j = 0; j < childNodes.getLength(); j++) {
                            Node childNode = childNodes.item(j);
                            if (childNode.getNodeName().equals("ar")) {
                                childNode.setTextContent("50000");
                            }
                        }
                    }
                }
            }

            printElements(nodes);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
