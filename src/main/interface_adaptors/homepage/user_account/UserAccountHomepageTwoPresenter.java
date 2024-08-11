package interface_adaptors.homepage.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.HomepageTwoPresenter;
import use_case.homepage.user_account.UserAccountHomepageTwoOutputBoundary;
import use_case.homepage.user_account.UserAccountHomepageTwoOutputData;

/**
 * The presenter for the second homepage view. This class is responsible for handling
 * the output data and updating the view model and view manager with the basic user information.
 *
 * @author Eric Chen
 */
public class UserAccountHomepageTwoPresenter extends HomepageTwoPresenter<
        UserAccountHomepageTwoOutputData,
        UserAccountHomepageTwoViewModel,
        UserAccountHomepageTwoState> implements UserAccountHomepageTwoOutputBoundary {

    /**
     * Constructs a new UserAccountHomepageTwoPresenter with the specified view model and view manager.
     *
     * @param viewModel the view model to be updated
     * @param viewManager the view manager to be updated
     */
    public UserAccountHomepageTwoPresenter(UserAccountHomepageTwoViewModel viewModel, ViewManagerModel viewManager) {
        super(viewModel, viewManager);
    }
}