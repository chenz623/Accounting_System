package interface_adaptors.homepage;

import interface_adaptors.ViewManagerModel;
import use_case.homepage.HomepageTwoOutputBoundary;
import use_case.homepage.HomepageTwoOutputData;

/**
 * The presenter for the second homepage view. This class is responsible for handling
 * the output data and updating the view model and view manager with the basic user information.
 *
 * @author Eric Chen
 */
public class HomepageTwoPresenter implements HomepageTwoOutputBoundary {
    private String[] basicUserInfo;
    private final HomepageTwoViewModel viewModel;
    private final ViewManagerModel viewManager;

    /**
     * Constructs a new HomepageTwoPresenter with the specified view model and view manager.
     *
     * @param viewModel the view model to be updated
     * @param viewManager the view manager to be updated
     */
    public HomepageTwoPresenter(HomepageTwoViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }

    /**
     * Presents the financial report by storing and updating the view with its content.
     * Resets any existing error message in the state.
     *
     * @param outputData the output data containing the basic user information
     */
    @Override
    public void prepareSuccessView(HomepageTwoOutputData outputData) {
        HomepageTwoState state = this.viewModel.getState();
        this.basicUserInfo = outputData.getBasicUserInfo();

        state.setBasicUserInfo(this.basicUserInfo);
        state.setErr(null);  // reset the no transaction error
        this.viewModel.setState(state);
        this.viewModel.setBasicUserInfo(state.getBasicUserInfo());
        this.viewManager.setBasicUserInfo(this.basicUserInfo);
        this.viewModel.firePropertyChanged();
    }

    /**
     * Prepares the view to display a failure message by updating the state with the error.
     *
     * @param err the error message to be displayed
     */
    @Override
    public void prepareFailView(String err) {
        HomepageTwoState state = this.viewModel.getState();
        this.viewModel.setState(state);
        this.viewModel.firePropertyChanged();
    }

    /**
     * Returns the report content.
     *
     * @return the report content
     */
    public String[] getBasicUserInfo() {
        return this.basicUserInfo;
    }
}