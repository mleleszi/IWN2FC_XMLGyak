package domiwn2fc;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DomReadIWN2FC {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        read2();
    }


    public static void read() {
        try {
            File file = new File("usersIWN2FC.xml");

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = documentBuilder.parse(file);
            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            System.out.println("");

            NodeList nodeList = doc.getElementsByTagName("user");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("Current element: " + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    System.out.println("User id: "+ element.getAttribute("id") );
                    System.out.println("Firstname: " + element.getElementsByTagName("firstName").item(0).getTextContent());
                    System.out.println("Lastname: " + element.getElementsByTagName("lastName").item(0).getTextContent());
                    System.out.println("Profession: " + element.getElementsByTagName("profession").item(0).getTextContent());
                }
                System.out.println();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void read2() throws ParserConfigurationException, IOException, SAXException {
        File file = new File("usersIWN2FC.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();

        System.out.println("Root element:" + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("user");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            System.out.println("\nCurrent element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) nNode;

                String uid = element.getAttribute("id");

                Node node1 = element.getElementsByTagName("firstName").item(0);
                String fname = node1.getTextContent();

                Node node2 = element.getElementsByTagName("lastName").item(0);
                String lname = node2.getTextContent();

                Node node3 = element.getElementsByTagName("profession").item(0);
                String pname = node3.getTextContent();

                System.out.println("User id: " + uid);
                System.out.println("Firstname: " + fname);
                System.out.println("Lastname: " + lname);
                System.out.println("Profession: " + pname);


            }

        }
    }

}
