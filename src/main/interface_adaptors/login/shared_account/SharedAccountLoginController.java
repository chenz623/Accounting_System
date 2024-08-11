package interface_adaptors.login.shared_account;

import interface_adaptors.login.LoginController;
import use_case.login.shared_account.SharedAccountLoginInputData;
import use_case.login.shared_account.SharedAccountLoginMediator;

public class SharedAccountLoginController extends LoginController<SharedAccountLoginMediator> {
    /**
     * Constructs a LoginController object with the specified use case interactor(mediator).
     *
     * @param loginMediator the use case interactor(mediator) for user login
     */
    public SharedAccountLoginController(SharedAccountLoginMediator loginMediator) {
        super(loginMediator);
    }

    /**
     * Constructs a LoginInputData object with the specified password and identification.
     *
     * @param sharedIdentification   the identification for the login
     * @param sharedPassword         the password for the login
     */
    @Override
    public void execute(String sharedIdentification, String sharedPassword) {
        SharedAccountLoginInputData sharedAccountLoginInputData = new SharedAccountLoginInputData(sharedIdentification, sharedPassword);
        this.loginMediator.execute(sharedAccountLoginInputData);
    }
}