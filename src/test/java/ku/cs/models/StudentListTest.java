package ku.cs.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentListTest {

    private StudentList studentList;

    @BeforeEach
    void setUp() {
        studentList = new StudentList();
    }

    @Test
    @DisplayName("Test adding a new student without initial score")
    void testAddNewStudentWithoutScore() {
        studentList.addNewStudent("6xxxxxxxx", "Student1Test");
        Student student = studentList.findStudentById("6xxxxxxxx");

        assertNotNull(student, "Student should not be null");
        assertEquals("6xxxxxxxx", student.getId(), "ID should match");
        assertEquals("Student1Test", student.getName(), "Name should match");
        assertEquals(0, student.getScore(), "Score should be initialized to 0");
    }

    @Test
    @DisplayName("Test adding a new student with initial score")
    void testAddNewStudentWithScore() {
        studentList.addNewStudent("6xxxxxxxx", "Student2Test", 85.5);
        Student student = studentList.findStudentById("6xxxxxxxx");

        assertNotNull(student, "Student should not be null");
        assertEquals("6xxxxxxxx", student.getId(), "ID should match");
        assertEquals("Student2Test", student.getName(), "Name should match");
        assertEquals(85.5, student.getScore(), "Score should match");
    }

    @Test
    @DisplayName("Test adding a duplicate student")
    void testAddDuplicateStudent() {
        studentList.addNewStudent("6xxxxxxxx", "Student3Test");
        studentList.addNewStudent("6xxxxxxxx", "Student3Test", 90.0);

        ArrayList<Student> students = studentList.getStudents();
        assertEquals(1, students.size(), "There should be only one student with this ID");
        assertEquals("Student3Test", students.get(0).getName(), "Name should match");
        assertEquals(0, students.get(0).getScore(), "Score should remain 0 for duplicate IDs");
    }

    @Test
    @DisplayName("Test giving score to an existing student")
    void testGiveScoreToId() {
        studentList.addNewStudent("6xxxxxxxx", "Student4Test", 50.0);
        studentList.giveScoreToId("6xxxxxxxx", 25.0);

        Student student = studentList.findStudentById("6xxxxxxxx");
        assertNotNull(student, "Student should not be null");
        assertEquals(75.0, student.getScore(), "Score should be updated");
    }

    @Test
    @DisplayName("Test viewing grade of an existing student")
    void testViewGradeOfId() {
        studentList.addNewStudent("6xxxxxxxx", "Student5Test", 72.0);

        String grade = studentList.viewGradeOfId("6xxxxxxxx");
        assertEquals("B", grade, "Grade should be B for score 72");
    }

    @Test
    @DisplayName("Test viewing grade of a non-existent student")
    void testViewGradeOfNonExistentId() {
        String grade = studentList.viewGradeOfId("6xxxxxxxx");
        assertNull(grade, "Grade should be null for non-existent students");
    }

    @Test
    @DisplayName("Test that students list is initially empty")
    void testEmptyStudentList() {
        ArrayList<Student> students = studentList.getStudents();
        assertTrue(students.isEmpty(), "Student list should be empty initially");
    }
}
