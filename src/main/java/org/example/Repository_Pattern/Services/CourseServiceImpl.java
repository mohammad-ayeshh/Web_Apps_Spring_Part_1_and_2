package Repository_Pattern.Services;

import Repository_Pattern.Repositorys.CourseRepository;
import Repository_Pattern.Repositorys.StudentRepository;

import java.util.Map;

public class CourseServiceImpl implements CourseService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public CourseServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
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
}
