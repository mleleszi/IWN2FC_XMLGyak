package hu.domparse.iwn2fc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
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
            FileOutputStream fout = new FileOutputStream(TXT_OUTPUT_PATH);
            TeePrintStream tee = new TeePrintStream(fout, System.out);
            System.setOut(tee);

            // create a DocumentBuilder
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            // create document from the opened file
            Document doc = documentBuilder.parse(file);

            doc.getDocumentElement().normalize();

            // read and print jarat element
            NodeList nodeList = doc.getElementsByTagName("jarat");
            List<String> attributes = Arrays.asList("jaratId", "legitarsasagId", "repuloId", "indulasiRepter", "erkezesiRepter");
            List<String> tagNames = Arrays.asList("indulasiIdo", "erkezesiIdo", "vanKiszolgalas");
            readNodes(nodeList, attributes, tagNames);

            // read and print repulo element
            nodeList = doc.getElementsByTagName("repulo");
            attributes = Arrays.asList("repuloId");
            tagNames = Arrays.asList("gyarto", "modell", "gyartasiEv", "kapacitas");
            readNodes(nodeList, attributes, tagNames);

            // read and print repter element
            nodeList = doc.getElementsByTagName("repter");
            attributes = Arrays.asList("repterId");
            tagNames = Arrays.asList("IATA", "ICAO", "nev", "orszag", "varos");
            readNodes(nodeList, attributes, tagNames);

            // read and print iroda element
            nodeList = doc.getElementsByTagName("iroda");
            attributes = Arrays.asList("repterId", "legitarsasagId");
            tagNames = Arrays.asList("ferohely");
            readNodes(nodeList, attributes, tagNames);

            // read and print legitarsasag element
            nodeList = doc.getElementsByTagName("legitarsasag");
            attributes = Arrays.asList("legitarsasagId");
            tagNames = Arrays.asList("nev", "dolgozokSzama", "orszag", "telefonszam");
            readNodes(nodeList, attributes, tagNames);

            // read and print utas element
            nodeList = doc.getElementsByTagName("utas");
            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                System.out.println("----- " + node.getNodeName() + (itr + 1) + " -----");
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    Element cim = (Element) element.getElementsByTagName("cim").item(0);

                    System.out.println("utasId: " + element.getAttribute("utasId"));
                    System.out.println("orszag: "+ cim.getElementsByTagName("orszag").item(0).getTextContent());
                    System.out.println("helyiseg: "+ cim.getElementsByTagName("helyiseg").item(0).getTextContent());
                    System.out.println("irsz: "+ cim.getElementsByTagName("irsz").item(0).getTextContent());
                    System.out.println("utca: "+ cim.getElementsByTagName("utca").item(0).getTextContent());
                    System.out.println("hazszam: "+ cim.getElementsByTagName("hazszam").item(0).getTextContent());
                    System.out.println("email: "+ element.getElementsByTagName("email").item(0).getTextContent());
                    System.out.println("szuletesiIdo: "+ element.getElementsByTagName("szuletesiIdo").item(0).getTextContent());
                }
                System.out.println("");
            }

            // read and print foglalas element, it's attributes and sub-elements
            nodeList = doc.getElementsByTagName("foglalas");
            attributes = Arrays.asList("utasId", "jaratId");
            tagNames = Arrays.asList("ar", "datum");
            readNodes(nodeList, attributes, tagNames);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // given a list of nodes, it writes their attributes and sub-elements to the console and to a file
    private static void readNodes(NodeList nodeList, List<String> attributes, List<String> tagNames) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println("----- " + node.getNodeName() + (i + 1) + " -----");
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                attributes.forEach(attribute -> {
                    System.out.println(attribute + ": " + element.getAttribute(attribute));
                });
                tagNames.forEach(tagName -> {
                    NodeList nodeList1 = element.getElementsByTagName(tagName);
                    for (int j = 0; j < nodeList1.getLength(); j++) {
                        System.out.println(tagName + ": " + nodeList1.item(0).getTextContent());
                    }
                });
            }
            System.out.println();
        }
    }
}
