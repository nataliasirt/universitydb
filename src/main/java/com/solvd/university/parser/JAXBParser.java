package com.solvd.university.parser;

import com.solvd.university.models.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JAXBParser {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void marshallDepartment(Department department) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(Department.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(department, new File("C:\\Users\\nsirt\\IdeaProjects\\universitydb\\src\\main\\resources\\jaxb\\jaxboutput.xml"));
    }

    public static Department unmarshallDepartment(String path) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Department.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Department) unmarshaller.unmarshal(new FileReader(path));
    }
    public static final String PATH = "C:\\Users\\nsirt\\IdeaProjects\\universitydb\\src\\main\\resources\\jaxb\\departmentdata.xml";

    public static void main(String[] args) throws JAXBException, IOException {

        Department department = JAXBParser.unmarshallDepartment(PATH);

        System.out.println(department);

        JAXBParser.marshallDepartment(department);
    }
}


