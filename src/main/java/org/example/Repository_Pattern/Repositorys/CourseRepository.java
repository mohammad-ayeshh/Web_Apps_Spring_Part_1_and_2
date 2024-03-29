package Repository_Pattern.Repositorys;

import Repository_Pattern.Models.Course;
import Repository_Pattern.Models.Student;

import java.util.List;

public interface CourseRepository {
    // CRUD operations
    void addCourse(Course course);
    Course getCourseById(String courseId);
    List<Course> getCoursesByIds(List<String> courseIds);
    List<Course> getAllCourses();

    void updateCourse(Course course);
    void deleteCourse(String courseId);
    List<Student> getStudentsForCourse(String courseId);
    void enrollStudentInCourse(int studentId, String courseId);
    void setMarkForStudentInCourse(int studentId, String courseId, int mark);
}
