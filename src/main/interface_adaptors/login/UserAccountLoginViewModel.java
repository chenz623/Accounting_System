package interface_adaptors.login;

import entity.account.UserAccount;
import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * The LoginViewModel class extends the ViewModel class and manages the state and labels for the login view.
 * It provides getters for various labels used in the login view and supports property change notifications.
 *
 * @author Rita
 * @author Eric
 * @author Dana
 */
public class UserAccountLoginViewModel extends AccountLoginViewModel<UserAccountLoginState> {

    private final String titleLabel = "USER ACCOUNT LOGIN";
    private final String identificationLabel = "Enter user account ID";
    private final String passwordLabel = "Enter user account password";

    /**
     * Constructs a LoginViewModel object with the view name set to "login".
     * Initiates state.
     */
    public UserAccountLoginViewModel() {
        super("log in");
        state = new UserAccountLoginState();
    }

}