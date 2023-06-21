package com.solvd.university.parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLParser {
    public static void main(String[] args) {
        try {
            // Create a DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Create a DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file
            Document document = builder.parse(new File("example.xml"));

            // Get the root element
            Element rootElement = document.getDocumentElement();

            // Traverse the XML tree
            traverseXML(rootElement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void traverseXML(Node node) {
        // Check if the current node is an element
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            // Print the node name
            System.out.println("Node: " + node.getNodeName());

            // Get the child nodes
            NodeList childNodes = node.getChildNodes();

            // Iterate over the child nodes
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node childNode = childNodes.item(i);

                // Recursive call to traverse the child nodes
                traverseXML(childNode);
            }
        }
    }
}

