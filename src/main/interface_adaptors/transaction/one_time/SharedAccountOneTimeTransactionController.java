package interface_adaptors.transaction.one_time;

import use_case.transaction.one_time.SharedAccountOneTimeTransactionInputBoundary;
import use_case.transaction.one_time.SharedAccountOneTimeTransactionInputData;
import use_case.transaction.one_time.SharedAccountOneTimeTransactionOutputBoundary;
import use_case.transaction.one_time.SharedAccountOneTimeTransactionOutputData;

/**
 * The SharedAccountOneTimeTransactionController class is responsible for handling user interactions
 * related to one-time transactions for shared accounts. It communicates with the use case interactor
 * to execute the shared account one-time transaction process.
 *
 * This controller extends functionality to support the selection of responsible users for the transaction.
 */
public class SharedAccountOneTimeTransactionController extends AccountOneTimeTransactionController<
        SharedAccountOneTimeTransactionInputBoundary,
        SharedAccountOneTimeTransactionViewModel> {

    /**
     * Constructs a SharedAccountOneTimeTransactionController object with the specified use case interactor
     * and view model.
     *
     * @param sharedAccountTransactionInteractor the use case interactor for shared account one-time transactions
     * @param viewModel                          the view model to update the transaction state
     */
    public SharedAccountOneTimeTransactionController(
            SharedAccountOneTimeTransactionInputBoundary sharedAccountTransactionInteractor,
            SharedAccountOneTimeTransactionViewModel viewModel) {
        super(sharedAccountTransactionInteractor, viewModel);
    }

    /**
     * Executes the shared account one-time transaction process with the given transaction details.
     *
     * @param amount               the amount of the transaction
     * @param transactionDate      the date of the transaction
     * @param transactionDescription the description of the transaction
     * @param transactionCategory  the category of the transaction
     * @param sharedAccountId      the identifier of the shared account
     * @param userId   the set of user IDs responsible for the transaction
     */
    public void execute(
            String amount,
            String transactionDate,
            String transactionDescription,
            String transactionCategory,
            String sharedAccountId, // Make sure sharedAccountId is passed here
            String userId) {

        // Create input data object for the transaction
        SharedAccountOneTimeTransactionInputData transactionInputData = new SharedAccountOneTimeTransactionInputData(
                amount, transactionDate, transactionDescription, transactionCategory, sharedAccountId, userId);

        // Execute the transaction process using the interactor
        oneTimeTransactionInteractor.execute(transactionInputData);

        // Reset the view model state after processing the transaction
        this.viewModel.resetState();
    }
}


