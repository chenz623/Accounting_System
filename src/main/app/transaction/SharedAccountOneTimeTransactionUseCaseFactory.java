package app.transaction;

import data_access.DAOFactory;
import data_access.account.shared_account.SharedAccountDataAccessInterface;
import entity.account.shared_account.SharedAccount;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.one_time.shared_account.SharedOneTimeTransactionController;
import interface_adaptors.transaction.one_time.shared_account.SharedOneTimeTransactionPresenter;
import interface_adaptors.transaction.one_time.shared_account.SharedOneTimeTransactionViewModel;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionInteractor;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionOutputBoundary;
import view.transaction.one_time.shared_account.SharedAccountOneTimeTransactionView;

import javax.swing.*;
import java.io.IOException;

public class SharedAccountOneTimeTransactionUseCaseFactory {
    private SharedAccountOneTimeTransactionUseCaseFactory() {}

    public static SharedAccountOneTimeTransactionView create(ViewManagerModel viewManagerModel,
                                                SharedOneTimeTransactionViewModel oneTimeTransactionViewModel) {
        try {
            SharedOneTimeTransactionController oneTimeTransactionController = createSharedAccountOneTimeUseCase(viewManagerModel,
                    oneTimeTransactionViewModel);
            return new SharedAccountOneTimeTransactionView(oneTimeTransactionViewModel, oneTimeTransactionController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static SharedOneTimeTransactionController createSharedAccountOneTimeUseCase(ViewManagerModel viewManagerModel,
                                                                                        SharedOneTimeTransactionViewModel oneTimeTransactionViewModel) throws IOException {
        SharedAccountDataAccessInterface dataAccessObject = DAOFactory.getShareAccountDataAccessObject();
        SharedAccountOneTimeTransactionOutputBoundary presenter = new SharedOneTimeTransactionPresenter(oneTimeTransactionViewModel, viewManagerModel);
        SharedAccount sharedAccount = dataAccessObject.getById(viewManagerModel.getUserId());
        SharedAccountOneTimeTransactionInteractor interactor = new SharedAccountOneTimeTransactionInteractor(dataAccessObject, presenter, sharedAccount);
        return new SharedOneTimeTransactionController(interactor, oneTimeTransactionViewModel);
    }

}



