package use_case;

import data_access.authentication.user_account.UserAccountLoginDataAccessInterface;
import entity.account.user_account.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoginInteractorTest {

    private UserAccountLoginDataAccessInterface userDataAccessObject;
    private SimplePresenter presenter;
    private LoginInteractor loginInteractor;
    private class ConcreteloginInteractor extends LoginInteractor{

        public ConcreteloginInteractor(Object userDataAccessObject, LoginOutputBoundary presenter) {
            super(userDataAccessObject, presenter);
        }

        @Override
        public void execute(LoginInputData inputData) {

        }
    }
    private class ConcreteloginLoginInputData extends LoginInputData {
        public ConcreteloginLoginInputData(String identification, String password) {
            super(identification, password);
        }
    }

    @BeforeEach
    public void setUp() {
        userDataAccessObject = new InMemoryLoginDataAccess();
        presenter = new SimplePresenter();
        loginInteractor = new ConcreteloginInteractor(userDataAccessObject, presenter) {
            @Override
            public void execute(LoginInputData inputData) {

            }
        };
    }

    @Test
    public void testUserNotFound() {
        String password = "password123";
        String userId = "nonexistentUser";
        LoginInputData inputData = new ConcreteloginLoginInputData(password, userId);

        loginInteractor.execute(inputData);

        assertEquals("User not found", presenter.getMessage());
        assertFalse(presenter.isSuccess());
    }


    @Test
    public void testEmptyPassword() {
        String password = "";
        String userId = "existentUser";
        LoginInputData inputData = new ConcreteloginLoginInputData(password, userId);

        loginInteractor.execute(inputData);

        assertEquals("Password can not be empty!", presenter.getMessage());
        assertFalse(presenter.isSuccess());
    }

    @Test
    public void testEmptyIdentification() {
        String password = "password123";
        String userId = "";
        LoginInputData inputData = new ConcreteloginLoginInputData(password, userId);

        loginInteractor.execute(inputData);

        assertEquals("Identification can not be empty!", presenter.getMessage());
        assertFalse(presenter.isSuccess());
    }

    @Test
    public void testEmptyPasswordAndIdentification() {
        String password = "";
        String userId = "";
        LoginInputData inputData = new ConcreteloginLoginInputData(password, userId);

        loginInteractor.execute(inputData);

        assertEquals("Identification AND Password can not be empty!", presenter.getMessage());
        assertFalse(presenter.isSuccess());
    }

    @Test
    public void testIncorrectPassword() {
        String username = "testUser";
        String password = "password123";
        String userId = "existentUser";
        UserAccount user = new UserAccount(username, password, userId);
        ((InMemoryLoginDataAccess) userDataAccessObject).addUser(user);
        LoginInputData inputData = new ConcreteloginLoginInputData("wrongPassword", userId);

        loginInteractor.execute(inputData);

        assertEquals("Incorrect Password!", presenter.getMessage());
        assertFalse(presenter.isSuccess());
    }

    @Test
    public void testLoginFailure() {
        String username = "testUser";
        String password = "password123";
        String userId = "existentUser";
        UserAccount user = new UserAccount(username, password, userId);
        ((InMemoryLoginDataAccess) userDataAccessObject).addUser(user);
        LoginInputData inputData = new ConcreteloginLoginInputData(password, userId);

        ((InMemoryLoginDataAccess) userDataAccessObject).setLoginSuccess(false);

        loginInteractor.execute(inputData);

        assertEquals("Incorrect Password!", presenter.getMessage());
        assertFalse(presenter.isSuccess());
    }

    private static class InMemoryLoginDataAccess implements UserAccountLoginDataAccessInterface {
        private final Map<String, UserAccount> userDatabase = new HashMap<>();
        private boolean loginSuccess = true;

        @Override
        public boolean existById(String identification) {
            return userDatabase.containsKey(identification);
        }

        @Override
        public UserAccount getById(String identification) {
            return userDatabase.get(identification);
        }

        @Override
        public boolean login(UserAccount userAccount) {
            return loginSuccess;
        }

        public void addUser(UserAccount userAccount) {
            userDatabase.put(userAccount.getIdentification(), userAccount);
        }

        public void setLoginSuccess(boolean success) {
            this.loginSuccess = success;
        }
    }

    static class SimplePresenter implements LoginOutputBoundary {
        private String message;
        private LoginOutputData data;
        private boolean success;

        @Override
        public void prepareSuccessView(Object user) {

        }

        @Override
        public void prepareFailView(String message) {
            this.message = message;
            this.success = false;
        }


        public String getMessage() {
            return message;
        }

        public boolean isSuccess() {
            return success;
        }

        public LoginOutputData getData() {
            return data;
        }
    }
}





