package Repository_Pattern.implementations;

import Repository_Pattern.Repositorys.UserRepository;

import java.sql.*;

public class JDBCUserRepository implements UserRepository {

    private static final String DB_URL = "jdbc:mysql://localhost:3307/school_management";
    private static final String USER = "mohammad";
    private static final String PASSWORD = "mohammad";

    @Override
    public boolean authenticateStudent(String email, String password) {
        String sql = "SELECT * FROM Students WHERE email = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if student with provided credentials exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean authenticateTeacher(String email, String password) {
        String sql = "SELECT * FROM Teachers WHERE email = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if teacher with provided credentials exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean authenticateAdmin(String email, String password) {
        String sql = "SELECT * FROM Admins WHERE email = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if admin with provided credentials exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}