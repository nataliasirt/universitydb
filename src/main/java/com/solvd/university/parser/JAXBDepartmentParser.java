package com.solvd.university.parser;

import com.solvd.university.models.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBDepartmentParser {
    private static final Logger LOGGER = LogManager.getLogger(JAXBDepartmentParser.class);

    private static final String OUTPUT_JAXB = "jaxb/jaxboutput.xml";


    public void marshall(Department department) {
        try {
            JAXBContext context = JAXBContext.newInstance(Department.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(department, new File(OUTPUT_JAXB));
        } catch (JAXBException e) {
            LOGGER.info("JAXB Exception occurred during marshall" + e.getMessage());
        }
        LOGGER.info("Marshalling Successful");
    }


    public Department unmarshall(String filePath) {
        Unmarshaller unmarshaller = null;
        Department department = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Department.class);
            unmarshaller = context.createUnmarshaller();
            department = (Department) unmarshaller.unmarshal(new File(filePath));
            LOGGER.info("UnMarshalling Successful");
        } catch (JAXBException e) {
            LOGGER.info("JAXB Exception occurred during unmarshall" + e.getMessage());
        }
        return department;
    }
}


