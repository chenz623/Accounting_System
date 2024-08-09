package interface_adaptors.transaction.one_time;

import interface_adaptors.ViewManagerModel;
import use_case.transaction.one_time.OneTimeTransactionOutputBoundary;
import use_case.transaction.one_time.OneTimeTransactionOutputData;
import use_case.transaction.one_time.UserAccountOneTimeTransactionOutputData;

public abstract class AccountOneTimeTransactionPresenter<
        V extends AccountOneTimeTransactionViewModel<S>,
        S extends AccountOneTimeTransactionState>{

    protected final V viewModel;
    protected final ViewManagerModel viewManager;

    /**
     * Constructs a UserAccountOneTimeTransactionPresenter object with the specified view model and view manager model.
     *
     * @param viewModel   the view model to update the one-time transaction state
     * @param viewManager the view manager model to manage view transitions
     */
    public AccountOneTimeTransactionPresenter(V viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }

    /**
     * Prepares the fail view with the given error message.
     * Updates the transaction state with the error message and clears the success message.
     *
     * @param error the error message to be presented in case of a failed one-time transaction attempt
     */
    public void prepareFailView (String error){
        S oneTimeState = (S) viewModel.getState();
        oneTimeState.setErrorMessage(error);
        oneTimeState.setSuccessMessage(null); // Clear success message on failure
        viewModel.firePropertyChanged();
    }
}











