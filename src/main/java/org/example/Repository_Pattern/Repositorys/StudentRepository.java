package Repository_Pattern.Repositorys;

import Repository_Pattern.Models.Student;

import java.util.List;
import java.util.Map;

public interface StudentRepository {
    // CRUD operations
    void addStudent(Student student);
    Student getStudentById(int studentId);
    List<Student> getAllStudents();
    void updateStudent(Student student);
    void deleteStudent(int studentId);
    List<String> getEnrolledCourses(int studentId);
    Map<String, Integer> getMarksForStudent(int studentId);
    Map<String, Integer> getAllStudentsMarks();
    int getStudentIdByEmailAndPassword(String email, String password);
}