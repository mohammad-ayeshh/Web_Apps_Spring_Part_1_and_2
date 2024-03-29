package Repository_Pattern.Services;

import Repository_Pattern.Models.Course;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<Course> getCoursesForStudent(int studentId);
    Map<Course, Integer> getMarksForStudent(int studentId);
    int getMaxInCourse(String Course_id);
    int getStudentIdByEmailAndPassword(String email, String password);
}