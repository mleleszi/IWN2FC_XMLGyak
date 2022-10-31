package hu.domparse.iwn2fc;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;


public class DomReadIWN2FC {
    public static void read() {
        try {
            // open file
            File file = new File("XMLiwn2fc.xml");

            // set output stream to write to both txt file and console
            FileOutputStream fout = new FileOutputStream("output.txt");
            TeePrintStream tee = new TeePrintStream(fout, System.out);
            System.setOut(tee);

            // create a DocumentBuilder
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            // create document from the opened file
            Document doc = documentBuilder.parse(file);

            // normalize document
            doc.getDocumentElement().normalize();

            // print elements
            List<String> tagNames = Arrays.asList("jarat", "repulo", "repter", "iroda", "legitarsasag", "utas", "foglalas");
            tagNames.forEach(tagName -> printElements(doc.getElementsByTagName(tagName)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // prints a list of nodes
    private static void printElements(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println("----- " + node.getNodeName() + (i + 1) + " -----");
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
