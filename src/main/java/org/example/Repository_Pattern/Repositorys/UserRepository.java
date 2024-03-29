package Repository_Pattern.Repositorys;


public interface UserRepository {
    boolean authenticateStudent(String username, String password);
    boolean authenticateTeacher(String username, String password);
    boolean authenticateAdmin(String username, String password);
}