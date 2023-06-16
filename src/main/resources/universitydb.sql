-- Establish one to one relationship between student table and user_account table
ALTER TABLE user_account
ADD COLUMN student_id INT UNIQUE,
ADD FOREIGN KEY (student_id) REFERENCES student(student_id);

-- 10 Insertion statements
INSERT INTO Student (student_id, first_name, last_name, date_of_birth, email, address, phone_number)
VALUES (1, 'John', 'Doe', '1995-05-15', 'johndoe@example.com', '123 Main St', '1234567890');

INSERT INTO Program (program_id, program_name, department_id)
VALUES (1, 'Computer Science', 1);

INSERT INTO Department (department_id, department_name)
VALUES (1, 'Engineering');

INSERT INTO Exam (exam_id, exam_name, date, program_id)
VALUES (1, 'Introduction to Programming', '2023-06-15', 1);

INSERT INTO Result (result_id, exam_id, student_id, marks_obtained)
VALUES (1, 1, 1, 85);

INSERT INTO Course (course_id, course_name, program_id)
VALUES (1, 'Data Structures', 1);

INSERT INTO Faculty (faculty_id, faculty_name)
VALUES (1, 'Engineering');

INSERT INTO Professor (professor_id, first_name, last_name, department_id)
VALUES (1, 'Jane', 'Smith', 1);

INSERT INTO Enrollment (enrollment_id, student_id, program_id, date_enrolled)
VALUES (1, 1, 1, '2023-05-01');

INSERT INTO Fee (fee_id, program_id, amount)
VALUES (1, 1, 1000);

-- 10 Update statements
UPDATE Student
SET email = 'updatedemail@example.com'
WHERE student_id = 1;

UPDATE Program
SET program_name = 'Information Technology'
WHERE program_id = 1;

UPDATE Exam
SET exam_name = 'Advanced Programming'
WHERE exam_id = 2;

UPDATE course
SET course_name = 'Database Management'
WHERE course_id = 1;

UPDATE faculty
SET faculty_name = 'Computer Science and Engineering'
WHERE faculty_id = 1;

UPDATE department
SET department_name = 'Software Engineering'
WHERE department_id = 1;

UPDATE professor
SET first_name = 'John'
WHERE professor_id = 1;

UPDATE enrollment
SET date_enrolled = '2023-06-01'
WHERE enrollment_id = 1;

UPDATE result
SET marks_obtained = 90
WHERE result_id = 1;

UPDATE fee
SET amount = 1200
WHERE fee_id = 1;

-- 10 Delete statements
DELETE FROM enrollment WHERE student_id = 1;

DELETE FROM student WHERE student_id = 1;

DELETE FROM student
WHERE student_id = 1;

DELETE FROM program
WHERE program_id = 1;

DELETE FROM exam
WHERE exam_id = 1;

DELETE FROM course
WHERE course_id = 1;

DELETE FROM faculty
WHERE faculty_id = 1;

DELETE FROM department
WHERE department_id = 1;

DELETE FROM professor
WHERE professor_id = 1;

-- 5 alter tables
ALTER TABLE Student
ADD COLUMN gender VARCHAR(10);

ALTER TABLE Exam
ADD COLUMN duration INT;

ALTER TABLE Result
DROP COLUMN marks_obtained;

ALTER TABLE UserAccount
ALTER COLUMN password SET DEFAULT 'password';

ALTER TABLE Department
CHANGE COLUMN department_name department_name VARCHAR(50);

-- 1 big statement to join all tables in the database
SELECT *
FROM Student
JOIN User_Account ON Student.student_id = User_Account.student_id
JOIN Result ON Student.student_id = Result.student_id
JOIN Exam ON Result.exam_id = Exam.exam_id
JOIN Program ON Exam.program_id = Program.program_id
JOIN Course ON Program.program_id = Course.program_id
JOIN Enrollment ON Student.student_id = Enrollment.student_id
JOIN Fee ON Program.program_id = Fee.program_id
JOIN Payment ON Student.student_id = Payment.student_id
JOIN Professor ON Course.program_id = Professor.department_id
JOIN Department ON Program.program_id = Department.department_id
JOIN Faculty ON Department.department_id = Faculty.faculty_id;

-- 5 statements with left, right, inner, outer joins:
-- Left Join
SELECT *
FROM Student
LEFT JOIN Enrollment ON Student.student_id = Enrollment.student_id;

-- Right Join
SELECT *
FROM Student
RIGHT JOIN Enrollment ON Student.student_id = Enrollment.student_id;

-- Inner Join
SELECT *
FROM Student
INNER JOIN Enrollment ON Student.student_id = Enrollment.student_id;

-- Outer Join (Full Outer Join)
SELECT *
FROM Student
LEFT JOIN Enrollment ON Student.student_id = Enrollment.student_id
UNION
SELECT *
FROM Student
RIGHT JOIN Enrollment ON Student.student_id = Enrollment.student_id;

-- Cross Join
SELECT *
FROM Student
CROSS JOIN Enrollment;

-- 7 statements with aggregate functions, GROUP BY, and without HAVING:
SELECT COUNT(*) AS student_count
FROM Student;

SELECT AVG(marks_obtained) AS avg_marks
FROM Result;

SELECT program_id, COUNT(*) AS student_count
FROM Enrollment
GROUP BY program_id;

SELECT department_id, MAX(marks_obtained) AS max_marks
FROM Result
GROUP BY department_id;

SELECT program_id, MIN(amount) AS min_fee
FROM Fee
GROUP BY program_id;

SELECT department_id, SUM(amount) AS total_fee
FROM Fee
GROUP BY department_id;

SELECT program_id, program_name, AVG(marks_obtained) AS avg_marks
FROM Result
JOIN Exam ON Result.exam_id = Exam.exam_id
JOIN Program ON Exam.program_id = Program.program_id
GROUP BY program_id;

-- 7 statements with aggregate functions, GROUP BY, and HAVING:
SELECT department_id, COUNT(*) AS student_count
FROM Student
GROUP BY department_id
HAVING COUNT(*) > 10;

SELECT program_id, AVG(marks_obtained) AS avg_marks
FROM Result
GROUP BY program_id
HAVING AVG(marks_obtained) > 80;

SELECT program_id, COUNT(*) AS student_count
FROM Enrollment
GROUP BY program_id
HAVING COUNT(*) > 5;

SELECT department_id, MAX(marks_obtained) AS max_marks
FROM Result
GROUP BY department_id
HAVING MAX(marks_obtained) > 90;

SELECT program_id, MIN(amount) AS min_fee
FROM Fee
GROUP BY program_id
HAVING MIN(amount) < 1000;

SELECT department_id, SUM(amount) AS total_fee
FROM Fee
GROUP BY department_id
HAVING SUM(amount) > 5000;

SELECT program_id, program_name, AVG(marks_obtained) AS avg_marks
FROM Result
JOIN Exam ON Result.exam_id = Exam.exam_id
JOIN Program ON Exam.program_id = Program.program_id
GROUP BY program_id
HAVING AVG(marks_obtained) > 75;




