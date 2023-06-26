package com.solvd.university;

import com.solvd.university.models.Department;
import com.solvd.university.models.Professor;
import com.solvd.university.models.Student;
import com.solvd.university.models.Subject;
import com.solvd.university.service.GroupService;
import com.solvd.university.service.impl.JsonService;
import com.solvd.university.service.impl.ProfessorService;
import com.solvd.university.service.impl.StudentService;
import com.solvd.university.service.impl.SubjectService;
import com.solvd.university.util.ConnectionPool;
import com.solvd.university.util.ParseAndValidationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

public class Main {
    private static final String STUDENT_FILE_PATH = "xml/student.xml";
    private static final String STUDENT_XSD_PATH = "xml/student.xsd";
    private static final String INPUT_JAXB = "src/main/resources/xml/input_jaxb.xml";
    private final static Logger LOGGER = LogManager.getLogger(Main.class);
    private static final ParseAndValidationService parseAndValidationService = new ParseAndValidationService();
    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static void main(String[] args) {
        //Select a student
        StudentService studentService = new StudentService();
        Student studentOne = studentService.getStudentById(1);
        LOGGER.info(studentOne);

        //Select all students
        List<Student> studentList = studentService.getAllStudents();
        LOGGER.info(studentList);

        Student studentThree = studentService.getFullStudentInfoById(1);
        LOGGER.info(studentThree);

        //Select a professor
        ProfessorService professorService = new ProfessorService();
        Professor professorOne = professorService.getProfessorById(1);
        LOGGER.info(professorOne);

        //Select all professors
        List<Professor> professorsList = professorService.getAllProfessorsAlphabetically();
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
        subjectService.registerSubject(subjectTwo);
        LOGGER.info(subjectService.getAllSubjects());

        //Parse to object - DOM
        Student student = parseAndValidationService.parseStudent(STUDENT_FILE_PATH);

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

        //Serialize JSON
        GroupService groupService = new GroupService();
        jsonService.serializeObjectToJson(groupService, jsonFile);

        //de-serializing a JSON into an object
        GroupService groupServiceObj = jsonService.deserializeJsonToObject(GroupService.class, jsonFile);
        LOGGER.info(groupServiceObj);
        }
    }


