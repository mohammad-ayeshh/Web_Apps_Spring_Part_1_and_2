package Repository_Pattern.Services;

import Repository_Pattern.Models.AuthenticationRequest;
import Repository_Pattern.Repositorys.UserRepository;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean authenticate(AuthenticationRequest requestData) {
        return switch (requestData.getUserType()) {
            case "Student" ->
                // Authenticate student
                    userRepository.authenticateStudent(requestData.getEmail(), requestData.getPassword());
            case "Teacher" ->
                // Authenticate teacher
                    userRepository.authenticateTeacher(requestData.getEmail(), requestData.getPassword());
            case "Admin" ->
                // Authenticate admin
                    userRepository.authenticateAdmin(requestData.getEmail(), requestData.getPassword());
            default -> false;
        };
    }
}