package Repository_Pattern.Services;

import Repository_Pattern.Models.Course;
import Repository_Pattern.Models.Student;
import Repository_Pattern.Models.Teacher;
import Repository_Pattern.Repositorys.CourseRepository;
import Repository_Pattern.Repositorys.StudentRepository;
import Repository_Pattern.Repositorys.TeacherRepository;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public AdminServiceImpl(StudentRepository studentRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.addStudent(student);
    }

    @Override
    public Student getStudentById(int studentId) {
        return studentRepository.getStudentById(studentId);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.updateStudent(student);
    }

    @Override
    public void deleteStudent(int studentId) {
        studentRepository.deleteStudent(studentId);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        teacherRepository.addTeacher(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherRepository.updateTeacher(teacher);
    }

    @Override
    public void deleteTeacher(int teacherId) {
        teacherRepository.deleteTeacher(teacherId);
    }

    @Override
    public void addCourse(Course course) {
        courseRepository.addCourse(course);
    }

    @Override
    public void updateCourse(Course course) {
        courseRepository.updateCourse(course);
    }

    @Override
    public void deleteCourse(String courseId) {
        courseRepository.deleteCourse(courseId);
    }

}