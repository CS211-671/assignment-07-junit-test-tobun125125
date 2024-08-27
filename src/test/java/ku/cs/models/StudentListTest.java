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


        assertEquals("6xxxxxxxx", student.getId());
        assertEquals("Student1Test", student.getName());
        assertEquals(0, student.getScore());
    }

    @Test
    @DisplayName("Test adding a new student with initial score")
    void testAddNewStudentWithScore() {
        studentList.addNewStudent("6xxxxxxxx", "Student2Test", 85.5);
        Student student = studentList.findStudentById("6xxxxxxxx");

        assertEquals("6xxxxxxxx", student.getId());
        assertEquals("Student2Test", student.getName());
        assertEquals(85.5, student.getScore());
    }

    @Test
    @DisplayName("Test adding a duplicate student")
    void testAddDuplicateStudent() {
        studentList.addNewStudent("6xxxxxxxx", "Student3Test");
        studentList.addNewStudent("6xxxxxxxx", "Student3Test", 90.0);

        ArrayList<Student> students = studentList.getStudents();
        assertEquals(1, students.size());
        assertEquals("Student3Test", students.get(0).getName());
        assertEquals(0, students.get(0).getScore());
    }

    @Test
    @DisplayName("Test giving score to an existing student")
    void testGiveScoreToId() {
        studentList.addNewStudent("6xxxxxxxx", "Student4Test", 50.0);
        studentList.giveScoreToId("6xxxxxxxx", 25.0);

        Student student = studentList.findStudentById("6xxxxxxxx");

        assertEquals(75.0, student.getScore());
    }

    @Test
    @DisplayName("Test viewing grade of an existing student")
    void testViewGradeOfId() {
        studentList.addNewStudent("6xxxxxxxx", "Student5Test", 72.0);

        String grade = studentList.viewGradeOfId("6xxxxxxxx");
        assertEquals("B", grade);
    }

    @Test
    @DisplayName("Test viewing grade of a non-existent student")
    void testViewGradeOfNonExistentId() {
        String grade = studentList.viewGradeOfId("6xxxxxxxx");
        assertNull(grade);
    }

    @Test
    @DisplayName("Test that students list is initially empty")
    void testEmptyStudentList() {
        ArrayList<Student> students = studentList.getStudents();
        assertTrue(students.isEmpty());
    }
}
