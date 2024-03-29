package Repository_Pattern.Services;

import Repository_Pattern.Models.Course;
import Repository_Pattern.Models.Student;
import Repository_Pattern.Models.Teacher;

import java.util.List;

public interface AdminService {

    void addStudent(Student student);
    Student getStudentById(int studentId);
    List<Student> getAllStudents();
    void updateStudent(Student student);
    void deleteStudent(int studentId);

    void addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacher(int teacherId);

    void addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(String courseId);
}