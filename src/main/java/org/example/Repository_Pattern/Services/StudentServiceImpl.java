package Repository_Pattern.Services;

import Repository_Pattern.Models.Course;
import Repository_Pattern.Repositorys.CourseRepository;
import Repository_Pattern.Repositorys.StudentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getCoursesForStudent(int studentId) {
        // Retrieve the list of course IDs enrolled by the student from the repository
        List<String> courseIds = studentRepository.getEnrolledCourses(studentId);

        // Retrieve the course objects for the given course IDs
        List<Course> courses = courseRepository.getCoursesByIds(courseIds);

        return courses;
    }


    @Override
    public Map<Course, Integer> getMarksForStudent(int studentId) {
        // Retrieve the map of course IDs and marks for the student from the repository
        Map<String, Integer> marks = studentRepository.getMarksForStudent(studentId);

        // Retrieve the course objects for the given course IDs
        List<Course> courses = courseRepository.getCoursesByIds(new ArrayList<>(marks.keySet()));

        // Create a map of courses and marks
        Map<Course, Integer> courseMarks = new HashMap<>();
        for (Course course : courses) {
            Integer mark = marks.get(course.getId());
            courseMarks.put(course, mark);
        }

        return courseMarks;
    }

    @Override
    public int getMaxInCourse(String Course_id) {

        // Retrieve the map of course IDs and marks for the student from the repository
        Map<String, Integer> course_mark = studentRepository.getAllStudentsMarks();
        int max = 0;
        for (Map.Entry<String, Integer> entry : course_mark.entrySet()) {
            String course = entry.getKey();
            int mark = entry.getValue();
            if (course.equals(Course_id)) {
                max = Math.max(max, mark);
            }
        }
        return max;
    }

    @Override
    public int getStudentIdByEmailAndPassword(String email, String password) {

        return studentRepository.getStudentIdByEmailAndPassword(email, password);

    }
}
