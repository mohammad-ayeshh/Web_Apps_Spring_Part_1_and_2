package Repository_Pattern.Services;

import Repository_Pattern.Models.Student;

import java.util.List;

public interface TeacherService {
    List<Student> getStudentsForCourse(String courseId);
    void addStudentToCourse(int studentId, String courseId);
    void setMarkForStudentInCourse(int studentId, String courseId, int mark);
}