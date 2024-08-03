package interface_adaptors.login;

import interface_adaptors.ViewManagerModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The LoginPresenter class implements the LoginOutputBoundary interface.
 * It handles the presentation logic for the standard login process.
 */
public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a LoginPresenter object with the specified view manager model and login view model.
     *
     * @param viewManagerModel the view manager model to manage view transitions
     * @param loginViewModel   the login view model to update the login state
     */
    public LoginPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    /**
     * Prepares the success view with the given login output data.
     * Updates the login state and changes the view to the transaction view.
     *
     * @param userInfo the login output data containing user information and success status
     */
    @Override
    public void prepareSuccessView(LoginOutputData userInfo) {
        // Handle standard login
        LoginState loginState = loginViewModel.getState();
        loginState.setIdentification(userInfo.getIdentification());
        loginState.setSuccessMsg("Successfully Logged In!!!");
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveViewName(loginViewModel.getViewName());

        // Change to the next view
        viewManagerModel.changeView("Transaction");
    }

    /**
     * Prepares the fail view with the given error message.
     * Updates the login state with the error message and clears the success message.
     *
     * @param err the error message to be presented in case of a failed login attempt
     */
    @Override
    public void prepareFailView(String err) {
        LoginState loginState = loginViewModel.getState();
        loginState.setStateError(err);
        loginState.setSuccessMsg(null); // Clear success message on failure
        loginViewModel.firePropertyChanged();
    }
}


