package use_case.transaction.periodic.user_account;

import data_access.account.user_account.UserAccountDataAccessInterface;

import entity.account.user_account.UserAccount;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.PeriodicTransactionInteractor;

/**
 * The UserAccountPeriodicTransactionInteractor class implements the UserAccountPeriodicTransactionInputBoundary interface.
 * It handles the process of creating a periodic transaction by validating the input data,
 * interacting with the data access layer, and using the presenter to prepare the output views.
 *
 * @author Eric
 * @author Dana
 */
public class UserAccountPeriodicTransactionInteractor extends PeriodicTransactionInteractor<
        UserAccountDataAccessInterface,
        UserAccount,
        UserAccountOneTimeTransactionOutputData,
        UserAccountPeriodicTransactionOutputData,
        UserAccountPeriodicTransactionInputData> implements UserAccountPeriodicTransactionInputBoundary {
//    private final UserAccountPeriodicTransactionOutputBoundary presenter;

    /**
     * Constructs a UserAccountPeriodicTransactionInteractor object with the specified data access interface,
     * output boundary, and user account.
     *
     * @param userAccountDataAccessInterface the data access interface for user data
     * @param userAccountPeriodicTransactionOutputBoundary the output boundary for presenting the periodic transaction results
     * @param userAccount the user account associated with the transaction
     */
    public UserAccountPeriodicTransactionInteractor(UserAccountDataAccessInterface userAccountDataAccessInterface,
                                                    UserAccountPeriodicTransactionOutputBoundary userAccountPeriodicTransactionOutputBoundary,
                                                    UserAccount userAccount) {
        super(userAccountDataAccessInterface, userAccountPeriodicTransactionOutputBoundary, userAccount);
//        this.presenter = userAccountPeriodicTransactionOutputBoundary;
    }

//    /**
//     * Executes the periodic transaction process with the given input data.
//     *
//     * @param userAccountPeriodicTransactionInputData the input data required for the periodic transaction process
//     */
//    @Override
//    public void execute(UserAccountPeriodicTransactionInputData userAccountPeriodicTransactionInputData) {
//        String identification = userAccountPeriodicTransactionInputData.getId();
//        String stringAmount = userAccountPeriodicTransactionInputData.getTransactionAmount();
//        String endDate = userAccountPeriodicTransactionInputData.getTransactionEndDate();
//        String description = userAccountPeriodicTransactionInputData.getTransactionDescription();
//        String startDate = userAccountPeriodicTransactionInputData.getTransactionStartDate();
//        String period = userAccountPeriodicTransactionInputData.getTransactionPeriod();
//        String category = userAccountPeriodicTransactionInputData.getTransactionCategory();
//
//        //Set currentDate to today
//        LocalDate currentDate = LocalDate.now();
//
//        // if user entered empty input in one or more of the input fields
//        if(!checkValid(stringAmount) || !checkValid(startDate) || !checkValid(endDate) ||
//                !checkValid(description) || !checkValid(period)) {
//            presenter.prepareFailView("Please do NOT have any empty inputs!");
//            return;
//        }
//
//        // Parse and validate the amount
//        float amount = parseAmount(stringAmount);
//        // we set float.MIN VAL to be the false output of the helper
//        if (amount == Float.MIN_VALUE) {
//            presenter.prepareFailView("Incorrect amount! please ONLY enter number");
//            return;
//        }
//
//        // Parse and validate the dates
//        LocalDate localStartDate = parseDate(startDate);
//        LocalDate localEndDate = parseDate(endDate);
//
//        // null is returned or period is longer than the days between start and end
//        if (localStartDate == null || localEndDate == null || localStartDate.isAfter(localEndDate)) {
//            presenter.prepareFailView("Invalid date format or start date after end date! Please enter again in dd-MM-yyyy!");
//            return;
//        }
//
//        // Validate and parse the period
//        int customPeriod = validateAndParsePeriod(period);
//        if (customPeriod == -1) {
//            presenter.prepareFailView("The custom period is not in correct format or is 0, please enter again in dd-MM-yyyy!");
//            return;
//        }
//
//        // Determine the ChronoUnit for the period
//        ChronoUnit unit = getChronoUnit(period);
//        if (!validatePeriod(unit, customPeriod, localStartDate, localEndDate)) {
//            presenter.prepareFailView("Period is longer than the time between start and end date!");
//            return;
//        }
//
//        boolean isInflow = amount >= 0.0;  // if amount < 0 then inflow = false
//        processTransactions(isInflow, identification, amount, localStartDate, localEndDate, description, period, customPeriod, unit, category, currentDate);
//    }
//
//    /**
//     * Validates and parses the transaction period.
//     * <p>
//     * This method checks if the period is one of the predefined period types (day, week, month, year).
//     * If not, it tries to parse the period as an integer representing a custom period in days.
//     * If parsing fails or the custom period is 0, it returns -1 as an indication of failure.
//     * </p>
//     *
//     * @param period the transaction period as a string
//     * @return the custom period as an integer, or 0 if it's a predefined period, or -1 if parsing fails
//     */
//    private int validateAndParsePeriod(String period) {
//        ArrayList<String> periodTypes = new ArrayList<>();
//        periodTypes.add("day");
//        periodTypes.add("week");
//        periodTypes.add("month");
//        periodTypes.add("year");
//
//        if (periodTypes.contains(period)) {
//            return 0;
//        }
//
//        try {
//            int customPeriod = Integer.parseInt(period);
//            if (customPeriod == 0) {
//                return -1;
//            }
//            return customPeriod;
//        } catch (NumberFormatException e) {
//            return -1;
//        }
//    }
//
//    /**
//     * Returns the ChronoUnit corresponding to the given period string.
//     * <p>
//     * If the period is not one of the predefined types, it defaults to ChronoUnit.DAYS.
//     * </p>
//     *
//     * @param period the transaction period as a string
//     * @return the corresponding ChronoUnit
//     */
//    private ChronoUnit getChronoUnit(String period) {
//        switch (period) {
//            case "day":
//                return ChronoUnit.DAYS;
//            case "week":
//                return ChronoUnit.WEEKS;
//            case "month":
//                return ChronoUnit.MONTHS;
//            case "year":
//                return ChronoUnit.YEARS;
//            default:
//                return ChronoUnit.DAYS;
//        }
//    }
//
//    /**
//     * Validates if the period is shorter than the duration between the start and end dates.
//     *
//     * @param unit the ChronoUnit of the period
//     * @param customPeriod the custom period in days
//     * @param startDate the start date
//     * @param endDate the end date
//     * @return true if the period is valid, false otherwise
//     */
//    private boolean validatePeriod(ChronoUnit unit, int customPeriod, LocalDate startDate, LocalDate endDate) {
//        long totalDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
//
//        // if the start day and end day is the same day
//        if (totalDaysBetween == 0) {
//            totalDaysBetween++;
//        }
//
//        if (unit != ChronoUnit.DAYS && totalDaysBetween < unit.getDuration().toDays()) {
//            return false;
//        } else if (totalDaysBetween < customPeriod) {
//            return false;
//        }
//
//        return unit == ChronoUnit.DAYS || totalDaysBetween >= customPeriod;
//    }
//
//    /**
//     * Processes the transactions based on whether they are inflow or outflow.
//     * <p>
//     * This method iterates over the period between the start and end dates, creating and saving
//     * inflow or outflow transactions at each step. It updates the user account's balance and
//     * interacts with the data access object to save the transactions.
//     * </p>
//     *
//     * @param isInflow boolean indicating if the transaction is an inflow
//     * @param identification the user's identification
//     * @param amount the transaction amount
//     * @param startDate the transaction start date
//     * @param endDate the transaction end date
//     * @param description the transaction description
//     * @param period the transaction period
//     * @param customPeriod the custom period in days
//     * @param unit the ChronoUnit of the period
//     * @param category the category of the transaction
//     */
//    private void processTransactions(boolean isInflow, String identification, float amount, LocalDate startDate,
//                                     LocalDate endDate, String description, String period, int customPeriod,
//                                     ChronoUnit unit, String category, LocalDate currentDate) {
//        LocalDate date = startDate;
//        UserAccountPeriodicTransactionOutputData finalOutputData = null;
//
//        // add transactions while from start to current date and not after end date
//        while (!date.isAfter(currentDate) && !date.isAfter(endDate)) {
//            if (isInflow) {
//                finalOutputData = processInflowTransaction(identification, amount, startDate, description, endDate,
//                        period, category, date);
//            } else {
//                finalOutputData = processOutflowTransaction(identification, amount, startDate, description, endDate,
//                        period, category, date);
//            }
//
//            // update date
//            if (unit != ChronoUnit.DAYS) {
//                date = date.plus(1, unit);
//            } else if (customPeriod == 0) {
//                date = date.plus(1, unit);
//            } else {
//                date = date.plusDays(customPeriod);
//            }
//        }
//
//        // update the success view only after all transactions are done
//        if (finalOutputData != null) {
//            presenter.prepareSuccessView(finalOutputData);
//        }
//    }
//
//    /**
//     * Processes an inflow transaction.
//     * <p>
//     * This method creates and saves a periodic inflow transaction, updates the user's total income
//     * and balance, and interacts with the data access object to save the transaction.
//     * </p>
//     *
//     * @param identification the user's identification
//     * @param amount the transaction amount
//     * @param startDate the current transaction date
//     * @param description the transaction description
//     * @param endDate the transaction end date
//     * @param period the transaction period
//     */
//    private UserAccountPeriodicTransactionOutputData processInflowTransaction(String identification, float amount, LocalDate startDate, String description,
//                                                                              LocalDate endDate, String period, String category, LocalDate transactionDate) {
//        // Create a new PeriodicInflow object
//        PeriodicInflow periodicInflow = new PeriodicInflow(identification, amount, startDate, description, endDate,
//                period, category);
//
//        // Update transaction date
//        periodicInflow.setDate(transactionDate);
//
//        // Create a new PeriodicInflow object
//        float totalIncome = account.getTotalIncome() + amount;
//        account.setTotalIncome(totalIncome);
//
//        // Update the user's total income and balance
//        float totalBalance = account.getTotalBalance() + amount;
//        account.setTotalBalance(totalBalance);
//
//        // Prepare the output data
//        UserAccountPeriodicTransactionOutputData outputData = new UserAccountPeriodicTransactionOutputData(periodicInflow);
//        userDataAccessObject.saveTransaction(null, outputData, true);
//        userDataAccessObject.update(account);
//
//        return outputData;
//    }
//
//    /**
//     * Processes an outflow transaction.
//     * <p>
//     * This method creates and saves a periodic outflow transaction, updates the user's total outflow
//     * and balance, and interacts with the data access object to save the transaction.
//     * </p>
//     *
//     * @param identification the user's identification
//     * @param amount the transaction amount
//     * @param currentDate the current transaction date
//     * @param description the transaction description
//     * @param endDate the transaction end date
//     * @param period the transaction period
//     */
//    private UserAccountPeriodicTransactionOutputData processOutflowTransaction(String identification, float amount, LocalDate currentDate, String description,
//                                                                               LocalDate endDate, String period, String category, LocalDate transactionDate) {
//        // Create a new PeriodicOutflow object
//        PeriodicOutflow periodicOutflow = new PeriodicOutflow(identification, amount, currentDate, description, endDate,
//                period, category);
//
//        // Update transaction date
//        periodicOutflow.setDate(transactionDate);
//
//        // Update the user's total outflow and balance
//        float totalOutflow = account.getTotalOutflow() + amount;
//        account.setTotalOutflow(totalOutflow);
//
//        float totalBalance = account.getTotalBalance() + amount;
//        account.setTotalBalance(totalBalance);
//
//        // Prepare the output data
//        UserAccountPeriodicTransactionOutputData outputData = new UserAccountPeriodicTransactionOutputData(periodicOutflow);
//        userDataAccessObject.saveTransaction(null, outputData, true);
//        userDataAccessObject.update(account);
//
//        return outputData;
//    }

    @Override
    protected UserAccountPeriodicTransactionOutputData createOutputData(PeriodicTransaction transaction) {
        return new UserAccountPeriodicTransactionOutputData(transaction);
    }

}