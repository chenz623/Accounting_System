package use_case.login;

/**
 * The LoginInputData class represents the input data required for a login operation.
 *
 * @author Dana
 */
public class UserAccountLoginInputData extends LoginInputData {

    /**
     * Constructs a LoginInputData object with the specified password and identification.
     *
     * @param password       the identification for the login
     * @param identification the password for the login
     */
    public UserAccountLoginInputData(String identification, String password){
        super(identification,password);
    }

}