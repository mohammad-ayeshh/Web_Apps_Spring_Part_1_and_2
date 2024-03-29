package Repository_Pattern.Models;

// Concrete implementation of the StudentCourse interface
public class StudentCourseImpl implements StudentCourse {
    private int studentId;
    private String courseId;
    private int mark;
    // Other properties relevant to a student-course relationship

    public StudentCourseImpl(int studentId, String courseId, int mark) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.mark = mark;
        // Initialize other properties if needed
    }

    @Override
    public int getStudentId() {
        return studentId;
    }

    @Override
    public String getCourseId() {
        return courseId;
    }

    @Override
    public int getMark() {
        return mark;
    }

    // Implement other methods if needed
}