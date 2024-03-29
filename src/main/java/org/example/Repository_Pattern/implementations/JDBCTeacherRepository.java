package Repository_Pattern.implementations;

import Repository_Pattern.Models.Teacher;
import Repository_Pattern.Models.TeacherImpl;
import Repository_Pattern.Repositorys.TeacherRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTeacherRepository implements TeacherRepository {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/school_management";
    private static final String USER = "mohammad";
    private static final String PASSWORD = "mohammad";

    @Override
    public void addTeacher(Teacher teacher) {
        String sql = "INSERT INTO Teachers (teacher_id, teacher_name) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, teacher.getId());
            pstmt.setString(2, teacher.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Teacher getTeacherById(int teacherId) {
        String sql = "SELECT * FROM Teachers WHERE teacher_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, teacherId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("teacher_id");
                String name = rs.getString("teacher_name");
                return new TeacherImpl(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM Teachers";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("teacher_id");
                String name = rs.getString("teacher_name");
                Teacher teacher = new TeacherImpl(id, name); // Instantiate TeacherImpl
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        String sql = "UPDATE Teachers SET teacher_name = ? WHERE teacher_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teacher.getName());
            pstmt.setInt(2, teacher.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTeacher(int teacherId) {
        String sql = "DELETE FROM Teachers WHERE teacher_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, teacherId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
