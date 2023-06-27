package com.solvd.university;

import com.solvd.university.models.Department;
import com.solvd.university.models.Professor;
import com.solvd.university.models.Student;
import com.solvd.university.models.Subject;
import com.solvd.university.service.GroupService;
import com.solvd.university.service.impl.*;
import com.solvd.university.service.impl.ProfessorService;
import com.solvd.university.service.impl.StudentService;
import com.solvd.university.service.impl.SubjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String STUDENT_FILE_PATH = "src/main/resources/xml/student.xml";
    private static final String STUDENT_XSD_PATH = "src/main/resources/xml/student.xsd";
    private static final String INPUT_JAXB = "src/main/resources/xml/input_jaxb.xml";
    private static final ParseAndValidationService parseAndValidationService = new ParseAndValidationService();

    public static void main(String[] args) {
        //Select student by ID
        StudentService studentService = new StudentService();
        Student student = studentService.getStudentById(1);
        LOGGER.info(student);

        //Select all students
        List<Student> studentList = studentService.getAllStudents();
        LOGGER.info(studentList);

        //Select a professor
        ProfessorService professorService = new ProfessorService();
        Professor professorOne = professorService.getProfessorById(1);
        LOGGER.info(professorOne);

        //Select all professors
        List<Professor> professorsList = professorService.getAllProfessors();
        LOGGER.info(professorsList);

        //Select a subject
        SubjectService subjectService = new SubjectService();
        Subject subjectOne = subjectService.getSubjectById(1);
        LOGGER.info(subjectOne);

        //Select all subjects
        List<Subject> subjects = subjectService.getAllSubjects();
        LOGGER.info(subjects);

        //Add a new subject
        Subject subjectTwo = new Subject(11, "Chemistry");
        subjectService.registerSubject(subjectTwo, 11);
        LOGGER.info(subjectService.getAllSubjects());

        //Parse to object - DOM
        Student student2 = parseAndValidationService.parseStudent(STUDENT_FILE_PATH);

        //Validate XML
        if(parseAndValidationService.validateXML(STUDENT_FILE_PATH,STUDENT_XSD_PATH)) {
            LOGGER.info("Invoice File Validation was Successful");
        }
        //Marhsall
        Department departmentJaxb = parseAndValidationService.unmarshallDepartment(INPUT_JAXB);

        //UnMarshall
        Department unMarshalled = parseAndValidationService.marshallDepartment(departmentJaxb);

        //JSON
        JsonService jsonService = new JsonService();
        File jsonFile = new File("src/main/resources/jackson/groupdata.json");

        //Serialize with JSON
        GroupService groupService = new GroupService();
        jsonService.serializeObjectToJson(groupService, jsonFile);

        //de-serializing with JSON into an object
        GroupService groupServiceObj = jsonService.deserializeJsonToObject(GroupService.class, jsonFile);
        LOGGER.info(groupServiceObj);
        }
    }


