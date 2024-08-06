package use_case.update_periodic_at_login;

import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import entity.transaction.Transaction;
import entity.transaction.periodic.PeriodicInflow;
import entity.transaction.periodic.PeriodicOutflow;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;

/**
 * The UpdatePeriodicAtLoginInteractor class is responsible for updating periodic transactions
 * when a user logs in. It processes both inflow and outflow transactions based on their periodicity
 * and ensures that the user's account balances are updated accordingly.
 *
 * @author
 * Jessica Chen and Eric Chen
 */
public class UpdatePeriodicAtLoginInteractor implements UpdatePeriodicAtLoginInputBoundary {
    private final UserAccountDataAccessInterface userDataAccessObject;

    /**
     * Constructs an UpdatePeriodicAtLoginInteractor with the given UserAccountDataAccessInterface.
     *
     * @param userDataAccessObject the data access object for user accounts
     */
    public UpdatePeriodicAtLoginInteractor(UserAccountDataAccessInterface userDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
    }

    /**
     * Executes the update of periodic transactions for the given user based on the provided input data.
     *
     * @param updatePeriodicAtLoginInputData the input data containing user identification and current date
     */
    @Override
    public void execute(UpdatePeriodicAtLoginInputData updatePeriodicAtLoginInputData) {
        // Get the information from the input data
        String userId = updatePeriodicAtLoginInputData.getIdentification();
        LocalDate currentDate = updatePeriodicAtLoginInputData.getCurrentDate();

        // get the user account, transactions, and the last login date
        UserAccount userAccount = userDataAccessObject.getById(userId);
        List<Transaction> transactions = userDataAccessObject.readTransactions(userId);
        LocalDate lastLoginDate = userAccount.getLastLoginDate();


        for (Transaction transaction : transactions) {
            // If the transaction is the most recently updated periodic transaction
            if ((transaction instanceof PeriodicTransaction) && (transaction.getDate() == lastLoginDate)) {

                // Get transaction information
                PeriodicTransaction periodicTransaction = (PeriodicTransaction) transaction;
                LocalDate endDate = periodicTransaction.getEndDate();
                LocalDate lastRecordedDate = periodicTransaction.getDate();
                LocalDate date = lastRecordedDate.plusDays(1); // start from the day after the last recorded date
                String period = periodicTransaction.getPeriod();

                // get the correspond chronoUnit;
                ChronoUnit unit = getChronoUnit(period);
                int customPeriod = validateAndParsePeriod(period);

                // Ensure we do not go beyond currentDate or endDate
                while (!date.isAfter(currentDate) && !date.isAfter(endDate)) {
                    if (periodicTransaction.getAmount() >= 0) {
                        processInflow(userAccount, periodicTransaction, userDataAccessObject, date);
                    } else {
                        processOutflow(userAccount, periodicTransaction, userDataAccessObject, date);
                    }

                    // update date
                    if (unit != ChronoUnit.DAYS) {
                        date = date.plus(1, unit);
                    } else if (customPeriod == 0) {
                        date = date.plus(1, unit);
                    } else {
                        date = date.plusDays(customPeriod);
                    }

                }
            }
        }
        userAccount.setLastLoginDate(currentDate);
        userDataAccessObject.update(userAccount);

    }

    /**
     * Returns the ChronoUnit corresponding to the given period string.
     * <p>
     * If the period is not one of the predefined types, it defaults to ChronoUnit.DAYS.
     * </p>
     *
     * @param period the transaction period as a string
     * @return the corresponding ChronoUnit
     */
    private ChronoUnit getChronoUnit(String period) {
        switch (period) {
            case "day":
                return ChronoUnit.DAYS;
            case "week":
                return ChronoUnit.WEEKS;
            case "month":
                return ChronoUnit.MONTHS;
            case "year":
                return ChronoUnit.YEARS;
            default:
                return ChronoUnit.DAYS;
        }
    }

    /**
     * Validates and parses the period string. If the period is a predefined type, it returns 0.
     * Otherwise, it parses the custom period as an integer.
     *
     * @param period the transaction period as a string
     * @return the parsed custom period or 0 if the period is predefined
     */
    private int validateAndParsePeriod(String period) {
        ArrayList<String> periodTypes = new ArrayList<>();
        periodTypes.add("day");
        periodTypes.add("week");
        periodTypes.add("month");
        periodTypes.add("year");

        if (periodTypes.contains(period)) {
            return 0;
        }

        // the period is prechecked in interactor and stored, so no need to check again
        int customPeriod = Integer.parseInt(period);
        return customPeriod;
    }

    /**
     * Processes an inflow transaction by creating a new PeriodicInflow object, updating the user's total income
     * and balance, and saving the transaction through the data access object.
     *
     * @param userAccount           the user account
     * @param periodicTransaction   the periodic transaction
     * @param userDataAccessObject  the data access object for user accounts
     * @param date                  the date of the transaction
     */
    private void processInflow(UserAccount userAccount, PeriodicTransaction periodicTransaction, UserAccountDataAccessInterface userDataAccessObject, LocalDate date){

        // Create new periodic inflow
        PeriodicInflow periodicInflow = new PeriodicInflow(
                userAccount.getIdentification(),
                periodicTransaction.getAmount(),
                periodicTransaction.getStartDate(),
                periodicTransaction.getDescription(),
                periodicTransaction.getEndDate(),
                periodicTransaction.getPeriod(),
                periodicTransaction.getTransactionCategory());

        // update date
        periodicInflow.setDate(date);

        // Create a new PeriodicInflow object
        float totalIncome = userAccount.getTotalIncome() + periodicTransaction.getAmount();
        userAccount.setTotalIncome(totalIncome);

        // Update the user's total income and balance
        float totalBalance = userAccount.getTotalBalance() + periodicTransaction.getAmount();
        userAccount.setTotalBalance(totalBalance);

        // Update through the DAO
        PeriodicTransactionOutputData outputData = new PeriodicTransactionOutputData(periodicInflow);
        userDataAccessObject.saveTransaction(null, outputData, true);
        userDataAccessObject.update(userAccount);
    }

    /**
     * Processes an outflow transaction by creating a new PeriodicOutflow object, updating the user's total outflow
     * and balance, and saving the transaction through the data access object.
     *
     * @param userAccount           the user account
     * @param periodicTransaction   the periodic transaction
     * @param userDataAccessObject  the data access object for user accounts
     * @param date                  the date of the transaction
     */
    private void processOutflow(UserAccount userAccount, PeriodicTransaction periodicTransaction, UserAccountDataAccessInterface userDataAccessObject, LocalDate date){

        // Create new periodic outflow
        PeriodicOutflow periodicOutflow = new PeriodicOutflow(
                userAccount.getIdentification(),
                periodicTransaction.getAmount(),
                periodicTransaction.getStartDate(),
                periodicTransaction.getDescription(),
                periodicTransaction.getEndDate(),
                periodicTransaction.getPeriod(),
                periodicTransaction.getTransactionCategory());

        // update date
        periodicOutflow.setDate(date);

        // Create a new PeriodicInflow object
        float totalOutflow = userAccount.getTotalOutflow() + periodicTransaction.getAmount();
        userAccount.setTotalOutflow(totalOutflow);

        // Update the user's total income and balance
        float totalBalance = userAccount.getTotalBalance() + periodicTransaction.getAmount();
        userAccount.setTotalBalance(totalBalance);

        // Update through the DAO
        PeriodicTransactionOutputData outputData = new PeriodicTransactionOutputData(periodicOutflow);
        userDataAccessObject.saveTransaction(null, outputData, true);
        userDataAccessObject.update(userAccount);
    }

}