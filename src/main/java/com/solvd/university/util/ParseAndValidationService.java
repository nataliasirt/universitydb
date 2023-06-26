package com.solvd.university.util;

import com.solvd.university.Main;
import com.solvd.university.models.Department;
import com.solvd.university.models.Student;
import com.solvd.university.parser.DOMStudentParser;
import com.solvd.university.parser.JAXBDepartmentParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParseAndValidationService {
    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final DOMStudentParser studentParser = new DOMStudentParser();
    private static final JAXBDepartmentParser jaxbParser = new JAXBDepartmentParser();

    public ParseAndValidationService(){
    };
    public Department unmarshallDepartment(String departmentfilePath){return jaxbParser.unmarshall(departmentfilePath);
    }
    public Department marshallDepartment(Department department){
        jaxbParser.marshall(department);
        return department;
    }




    public Student parseStudent(String studentFilePath) {
        return studentParser.parse(studentFilePath);
    }
    public boolean validateXML(String XMLFilePath, String XSDFilePath) {
        DOMValidator validator = new DOMValidator();
        boolean isValid = validator.validateXML(XMLFilePath, XSDFilePath);
        if (isValid) {
            logger.info("XML is valid.");
            return isValid;
        } else {
            logger.info("XML is invalid.");
            return false;
        }
    }
}

