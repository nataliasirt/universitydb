package com.solvd.university.service.impl;

import com.solvd.university.Main;
import com.solvd.university.models.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMStudentParser implements IDOMParser<Student> {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private Document document;

    public DOMStudentParser(){
    }
    public Student parse (String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(filePath));
        } catch (IOException | SAXException | ParserConfigurationException e) {
            LOGGER.info("Exception " + e.getClass().getSimpleName() + " was thrown, Details: " + e);
        }
        Student student = new Student();
        Element studentElement = document.getDocumentElement();
        student.setStudentId(Integer.parseInt(getElementValue(studentElement, "studentId")));
        student.setEnrollment(Integer.parseInt(getElementValue(studentElement, "enrollment")));
        return student;}

    public String getElementValue(Element parentElement, String elementName){
    NodeList nodeList = parentElement.getElementsByTagName(elementName);
    Element element = (Element) nodeList.item(0);
    return element.getTextContent();
    }
}

















