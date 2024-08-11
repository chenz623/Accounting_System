package interface_adaptors.login.shared_account;

import interface_adaptors.login.LoginViewModel;

/**
 * The SharedAccountLoginViewModel class extends LoginViewModel to manage the state and labels
 * for the shared account login view. It adds support for shared account-specific information.
 */
public class SharedAccountLoginViewModel extends LoginViewModel<SharedAccountLoginState> {

    private final String titleLabel = "SHARED ACCOUNT LOGIN";
    private final String identificationLabel = "Enter shared account ID";
    private final String passwordLabel = "Enter shared account password";

    /**
     * Constructs a SharedAccountLoginViewModel object with the view name set to "shared account login".
     * Initiates state.
     */
    public SharedAccountLoginViewModel() {
        super("shared account log in");
        state = new SharedAccountLoginState();
    }

    @Override
    public String getTitleLabel() {
        return this.titleLabel;
    }

    @Override
    public String getIdentificationLabel() {
        return this.identificationLabel;
    }

    @Override
    public String getPasswordLabel() {
        return this.passwordLabel;
    }



}