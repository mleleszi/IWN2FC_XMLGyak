package hu.domparse.iwn2fc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

public class DomReadIWN2FC {
    public static final String XML_FILE_PATH = "XMLiwn2fc.xml";
    public static final String TXT_OUTPUT_PATH = "output.txt";

    public static void read() {
        try {
            // open file
            File file = new File(XML_FILE_PATH);

            // set output stream to write to both txt file and console
            FileOutputStream fout = new FileOutputStream("test.txt");
            TeePrintStream tee = new TeePrintStream(fout, System.out);
            System.setOut(tee);

            // create a DocumentBuilder
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            // create document from the opened file
            Document doc = documentBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("jarat");
            List<String> attributes = Arrays.asList("jaratId", "legitarsasagId", "repuloId", "indulasiRepter", "erkezesiRepter");
            List<String> tagNames = Arrays.asList("indulasiIdo", "erkezesiIdo", "vanKiszolgalas");
            readNodes(nodeList, attributes, tagNames);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readNodes(NodeList nodeList, List<String> attributes, List<String> tagNames) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println("----- " + node.getNodeName() + (i + 1) + " -----");
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                attributes.forEach(attribute -> {
                    System.out.println(attribute + ": " + element.getAttribute(attribute));
                });
                tagNames.forEach(tagName -> {
                    System.out.println(tagName + ": " + element.getElementsByTagName(tagName).item(0).getTextContent());
                });
            }
            System.out.println();
        }
    }
}
