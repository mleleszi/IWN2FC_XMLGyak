package hu.domparse.iwn2fc;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

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

            // change the address of a passanger
            nodes = doc.getElementsByTagName("utas");
            for	(int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    if (node.getAttributes().getNamedItem("utasId").getTextContent().equals("u2")) {
                        NodeList childNodes = node.getChildNodes();
                        for (int j = 0; j < childNodes.getLength(); j++) {
                            Node childNode = childNodes.item(j);
                            if (childNode.getNodeName().equals("cim")) {
                                childNodes = childNode.getChildNodes();
                                for(int k = 0;k < childNodes.getLength();k++) {
                                    childNode = childNodes.item(k);
                                    if(childNode.getNodeName().equals("orszag")) {
                                        childNode.setTextContent("Ausztria");
                                    }
                                    if(childNode.getNodeName().equals("helyiseg")) {
                                        childNode.setTextContent("Becs");
                                    }
                                    if(childNode.getNodeName().equals("irsz")) {
                                        childNode.setTextContent("9999");
                                    }
                                    if(childNode.getNodeName().equals("utca")) {
                                        childNode.setTextContent("Utcanev");
                                    }
                                    if(childNode.getNodeName().equals("hazszam")) {
                                        childNode.setTextContent("100");
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // change the flight id of a booking
            nodes = doc.getElementsByTagName("foglalas");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    if (node.getAttributes().getNamedItem("utasId").getTextContent().equals("u2") &&
                            node.getAttributes().getNamedItem("jaratId").getTextContent().equals("j2")) {
                        node.getAttributes().getNamedItem("jaratId").setTextContent("j3");
                    }
                }
            }

            // change service type of a flight
            nodes = doc.getElementsByTagName("jarat");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    if (node.getAttributes().getNamedItem("jaratId").getTextContent().equals("j4")) {
                        NodeList childNodes = node.getChildNodes();
                        for (int j = 0; j < childNodes.getLength(); j++) {
                            Node childNode = childNodes.item(j);
                            if (childNode.getNodeName().equals("vanKiszolgalas")) {
                                if (childNode.getTextContent().equals("igen")) {
                                    childNode.setTextContent("nem");
                                } else {
                                    childNode.setTextContent("igen");
                                }
                            }
                        }
                    }
                }
            }

            // write the modified document to a new file and to the console
            writeXml(doc, new File("XMLiwn2fc_modified.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // writes document to xml file and console
    private static void writeXml(Document doc, File output) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(doc);

        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(output);

        transformer.transform(source, console);
        transformer.transform(source, file);
    }

}
