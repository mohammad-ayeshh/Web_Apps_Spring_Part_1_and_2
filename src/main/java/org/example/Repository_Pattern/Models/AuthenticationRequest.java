package Repository_Pattern.Models;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userType;
    private String email;
    private String password;

    public AuthenticationRequest(String userType, String username, String password) {
        this.userType = userType;
        this.email = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "userType='" + userType + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
