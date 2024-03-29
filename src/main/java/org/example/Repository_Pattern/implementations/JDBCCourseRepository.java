package Repository_Pattern.implementations;

import Repository_Pattern.Models.Course;
import Repository_Pattern.Models.CourseImpl;
import Repository_Pattern.Models.Student;
import Repository_Pattern.Models.StudentImpl;
import Repository_Pattern.Repositorys.CourseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JDBCCourseRepository implements CourseRepository {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/school_management";
    private static final String USER = "mohammad";
    private static final String PASSWORD = "mohammad";

    @Override
    public void addCourse(Course course) {
        String sql = "INSERT INTO Courses (course_id, course_name) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, course.getId());
            pstmt.setString(2, course.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Course getCourseById(String courseId) {
        String sql = "SELECT * FROM Courses WHERE course_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractCourseFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Course> getCoursesByIds(List<String> courseIds) {
        List<Course> courses = new ArrayList<>();
        // Create a parameter placeholder string for the IN clause with the appropriate number of placeholders
        String placeholders = String.join(",", Collections.nCopies(courseIds.size(), "?"));
        String sql = "SELECT * FROM Courses WHERE course_id IN (" + placeholders + ")";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the parameters using the course IDs
            for (int i = 0; i < courseIds.size(); i++) {
                pstmt.setString(i + 1, courseIds.get(i));
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Course course = extractCourseFromResultSet(rs);
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }


    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Courses";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                courses.add(extractCourseFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }


    @Override
    public void updateCourse(Course course) {
        String sql = "UPDATE Courses SET course_name = ? WHERE course_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, course.getName());
            pstmt.setString(2, course.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCourse(String courseId) {
        String sql = "DELETE FROM Courses WHERE course_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, courseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getStudentsForCourse(String courseId) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT s.* FROM Students s INNER JOIN Student_Courses sc ON s.student_id = sc.student_id WHERE sc.course_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Student student = extractStudentFromResultSet(rs);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    @Override
    public void enrollStudentInCourse(int studentId, String courseId) {
        String sql = "INSERT INTO Student_Courses (student_id, course_id) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setString(2, courseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setMarkForStudentInCourse(int studentId, String courseId, int mark) {
        String sql = "UPDATE Student_Courses SET mark = ? WHERE student_id = ? AND course_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, mark);
            pstmt.setInt(2, studentId);
            pstmt.setString(3, courseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to extract student information from the result set
    private Student extractStudentFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("student_id");
        String name = rs.getString("student_name");
        String email = rs.getString("email"); // Assuming the column name in the database is "email"
        String password = rs.getString("password"); // Assuming the column name in the database is "password"
        return new StudentImpl(id, name, email, password);
    }

    private Course extractCourseFromResultSet(ResultSet rs) throws SQLException {
        String id = rs.getString("course_id");
        String name = rs.getString("course_name");
        String teacher = rs.getString("teacher_id");
        return new CourseImpl(id, name, teacher);
    }
}