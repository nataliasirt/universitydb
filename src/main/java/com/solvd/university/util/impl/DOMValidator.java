package com.solvd.university.util.impl;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class DOMValidator {
    public boolean validateXML(String xmlFilePath, String schemaFilePath) {
        try {
            //Creates a DOM Object used to parse XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFilePath));

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(schemaFilePath));

            Validator validator = schema.newValidator();

            validator.validate(new DOMSource(document));

            return true;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            //Not Valid
            return false;
        }
    }
}
