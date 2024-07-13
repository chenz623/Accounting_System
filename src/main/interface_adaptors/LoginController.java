package interface-adapters;

public class LoginController {
    private String username;
    private String usernameError;
    private String password;
    private String passwordError;

    public LoginController() {
        this.username = "";
        this.usernameError = null;
        this.password = "";
        this.passwordError = null;
    }

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setUsername(String username) {
        this.username = username;
        validate();
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
        validate();
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    private void validate() {
        if (username == null || username.isEmpty()) {
            usernameError = "Username cannot be empty";
        } else {
            usernameError = null;
        }

        if (password == null || password.isEmpty()) {
            passwordError = "Password cannot be empty";
        } else {
            passwordError = null;
        }
    }
}
