package xpathiwn2fc;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public class xPathIWN2FC {
    public static void main(String[] args){
        try {
            File file = new File("studentIWN2FC.xml");

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression1 = "class/student";
            String expression2 = "class/student[@id='01']";
            String expression3 = "*/student";
            String expression4 = "class/student[2]";
            String expression5 = "class/student[last()]";
            String expression6 = "class/student[last() - 1]";
            String expression7 = "class/student[position() > last() - 2]";
            String expression8 = "class/*";
            String expression9 = "class/student[@*]";
            String expression10 = "*";
            String expression11 = "class/student[age > 20]";
            String expression12 = "(class/student/firstname | class/student/lastname)";
            String expression13 = "class/student[1]/firstname";
            String expression14 = "class/student[starts-with(firstname, 'M')]/firstname";
            String expression15 = "class/student[string-length(nickname) = 4]/nickname";



            NodeList nodeList = (NodeList) xPath.compile(expression15).evaluate(document, XPathConstants.NODESET);

            /*
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                System.out.println("\nAktuális elem: " + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
                    Element element = (Element) node;

                    System.out.println("Hallgato ID: " + element.getAttribute("id"));
                    System.out.println("Keresztnév: " + element.getElementsByTagName("firstname").item(0).getTextContent());
                    System.out.println("Vezetéknév: " + element.getElementsByTagName("lastname").item(0).getTextContent());
                    System.out.println("Becenév: " + element.getElementsByTagName("nickname").item(0).getTextContent());
                }
            }

             */

            printElements(nodeList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // prints a list of nodes
    public static void printElements(NodeList nodeList) {
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
