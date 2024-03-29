package Repository_Pattern.Services;

import Repository_Pattern.Models.Student;
import Repository_Pattern.Repositorys.CourseRepository;
import Repository_Pattern.Repositorys.StudentRepository;
import Repository_Pattern.Repositorys.TeacherRepository;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(StudentRepository studentRepository,
                              CourseRepository courseRepository,
                              TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Student> getStudentsForCourse(String courseId) {
        return courseRepository.getStudentsForCourse(courseId);
    }

    @Override
    public void addStudentToCourse(int studentId, String courseId) {
        // Check if the student exists
        Student student = studentRepository.getStudentById(studentId);
        if (student == null) {
            System.out.println("Student with ID " + studentId + " does not exist.");
            return;
        }
        // Add the student to the course
        courseRepository.enrollStudentInCourse(studentId, courseId);
    }

    @Override
    public void setMarkForStudentInCourse(int studentId, String courseId, int mark) {
        // Set the mark for the student in the course
        courseRepository.setMarkForStudentInCourse(studentId, courseId, mark);
    }
}
