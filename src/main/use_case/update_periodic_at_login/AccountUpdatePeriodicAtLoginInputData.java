package use_case.update_periodic_at_login;

import java.time.LocalDate;

public abstract class AccountUpdatePeriodicAtLoginInputData {
    protected String identification;
    protected LocalDate currentDate;

    /**
     * Constructs a AccountUpdatePeriodicAtLoginInputData object with the specified details.
     *
     * @param identification identification of the user
     * @param currentDate date of the login
     */
    public AccountUpdatePeriodicAtLoginInputData(String identification, LocalDate currentDate) {
        this.identification = identification;
        this.currentDate = currentDate;
    }

    /**
     * Gets the identification of the user.
     *
     * @return identification of the user
     */
    public String getIdentification() {
        return identification;
    }

    /**
     * Gets current date of the login.
     *
     * @return the current date of the login
     */
    public LocalDate getCurrentDate() {return currentDate;
    }
}