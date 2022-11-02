package domiwn2fc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomWriteIWN2FC {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        write();
    }

    public static void write() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElementNS("DOMIwn2fc", "users");
        doc.appendChild(root);

        root.appendChild(createUser(doc, "1", "Marcell", "Leleszi", "Developer"));
        root.appendChild(createUser(doc, "2", "Adam", "Toth", "Project Manager"));
        root.appendChild(createUser(doc, "3", "Sandor", "Veres", "Analyst"));

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);

        File myFile = new File("user1IWN2FC.xml");

        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(myFile);

        transf.transform(source, console);
        transf.transform(source, file);


    }

    public static Node createUser(Document doc, String id, String firstName, String lastName, String profession) {
        Element user = doc.createElement("user");

        user.setAttribute("id", id);
        user.appendChild(createUserElement(doc, "firstName", firstName));
        user.appendChild(createUserElement(doc, "lastName", lastName));
        user.appendChild(createUserElement(doc, "profession", profession));

        return user;
    }

    private static Node createUserElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

}
