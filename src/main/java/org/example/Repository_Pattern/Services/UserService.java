package Repository_Pattern.Services;

import Repository_Pattern.Models.AuthenticationRequest;

public interface UserService {

    boolean authenticate(AuthenticationRequest requestData);
}
