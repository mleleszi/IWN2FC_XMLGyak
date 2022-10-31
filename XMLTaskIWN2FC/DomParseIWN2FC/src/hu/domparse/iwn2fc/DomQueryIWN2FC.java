package hu.domparse.iwn2fc;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;

import static hu.domparse.iwn2fc.DomReadIWN2FC.printElements;

public class DomQueryIWN2FC {
    public static void query() {
        try {
            // open file
            File file = new File("XMLiwn2fc.xml");

            // create a DocumentBuilder
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            // create document from the opened file
            Document doc = documentBuilder.parse(file);

            // normalize document
            doc.getDocumentElement().normalize();

            // create XPath object
            XPath xPath = XPathFactory.newInstance().newXPath();

            // queries the plane with id r1
            String query1 = "repules/repulo[@repuloId='r1']";

            // queries the planes that have a capacity greater than 300
            String query2 = "repules/repulo[kapacitas>300]";

            // queries the last order
            String query3 = "repules/foglalas[last()]";

            // queries the airline with phone number +36207683331
            String query4 = "repules/legitarsasag[telefonszam='+36207683331']";

            // queries the offices that have a capacity between 20 and 200
            String query5 = "repules/iroda[ferohely>20 and ferohely<200]";


            // evaluate queries and print to console
            System.out.println("Repülő 1-es id-val:");
            NodeList nodeList = (NodeList) xPath.compile(query1).evaluate(doc, XPathConstants.NODESET);
            printElements(nodeList);

            System.out.println("Repülők, amiknek a kapacitása nagyobb, mint 300:");
            nodeList = (NodeList) xPath.compile(query2).evaluate(doc, XPathConstants.NODESET);
            printElements(nodeList);

            System.out.println("Utolsó foglalas:");
            nodeList = (NodeList) xPath.compile(query3).evaluate(doc, XPathConstants.NODESET);
            printElements(nodeList);

            System.out.println("Légitársaság, aminek a telefonszáma +36207683331:");
            nodeList = (NodeList) xPath.compile(query4).evaluate(doc, XPathConstants.NODESET);
            printElements(nodeList);

            System.out.println("Irodák, melyeknek férőhelye több mint 20, de kevesebb mint 200:");
            nodeList = (NodeList) xPath.compile(query5).evaluate(doc, XPathConstants.NODESET);
            printElements(nodeList);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
