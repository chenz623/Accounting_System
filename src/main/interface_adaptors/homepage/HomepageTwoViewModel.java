package interface_adaptors.homepage;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class HomepageTwoViewModel<S extends HomepageTwoState> extends ViewModel {
    // labels
    protected final String USERNAME_LABEL = "User:";
    protected final String BALANCE_LABEL = "Total Balance:";
    protected final String INCOME_LABEL = "Total Income:";
    protected final String OUTFLOW_LABEL = "Total Outflow:";

    protected final String ONE_TIME_BUTTON_LABEL = "One Time Transaction";
    protected final String PERIODIC_BUTTON_LABEL = "Periodic Transaction";
    protected final String CANCEL_BUTTON_LABEL = "Log out";
    protected final String FINANCIAL_REPORT_BUTTON_LABEL = "Transaction Report";

    protected String[] basicUserInfo;
    /**
     * Notifies listeners that the signup state has changed.
     */
    protected final PropertyChangeSupport support = new PropertyChangeSupport(this);
    protected S state;

    public HomepageTwoViewModel(String viewName) {
        super(viewName);
    }

    /**
     * Gets the label for the username.
     *
     * @return the username label
     */
    public String getUSERNAME_LABEL() {
        return this.USERNAME_LABEL;
    }

    /**
     * Gets the balance label.
     *
     * @return the balance label
     */
    public String getBALANCE_LABEL() {
        return this.BALANCE_LABEL;
    }

    /**
     * Gets the income label.
     *
     * @return the income label
     */
    public String getINCOME_LABEL() {
        return this.INCOME_LABEL;
    }

    /**
     * Gets the outflow label.
     *
     * @return the outflow label
     */
    public String getOUTFLOW_LABEL() {
        return this.OUTFLOW_LABEL;
    }

    /**
     * Gets the label for the one-time transaction button.
     *
     * @return the label for the one-time transaction button
     */
    public String getONE_TIME_BUTTON_LABEL() {
        return this.ONE_TIME_BUTTON_LABEL;
    }

    /**
     * Gets the label for the periodic transaction button.
     *
     * @return the label for the periodic transaction button
     */
    public String getPERIODIC_BUTTON_LABEL() {
        return this.PERIODIC_BUTTON_LABEL;
    }

    /**
     * Gets the label for the cancel button.
     *
     * @return the label for the cancel button
     */
    public String getCANCEL_BUTTON_LABEL() {
        return this.CANCEL_BUTTON_LABEL;
    }

    /**
     * Gets the label for the history button.
     *
     * @return the label for the history button
     */
    public String getFINANCIAL_REPORT_BUTTON_LABEL() {return this.FINANCIAL_REPORT_BUTTON_LABEL;}

    /**
     * Gets the basic user information.
     *
     * @return an array containing the basic user information
     */
    public String[] getBasicUserInfo() {
        return this.basicUserInfo;
    }

    public abstract S getState();
    public abstract void setState(S state);
    protected abstract void resetState();

    /**
     * Sets the basic user information.
     *
     * @param basicUserInfo the basic user information to set
     */
    public void setBasicUserInfo(String[] basicUserInfo) {
        this.basicUserInfo = basicUserInfo;
    }

    /**
     * Notifies listeners that the signup state has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the listener list.
     *
     * @param listener the PropertyChangeListener to be added
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
