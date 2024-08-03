package view.login;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.LoginController;
import interface_adaptors.login.SharedAccountLoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The SharedAccountLoginPanel class extends LoginPanel to handle shared account login.
 * It adds a field for entering the shared account ID and handles additional logic for shared account scenarios.
 */
public class SharedAccountLoginPanel extends LoginPanel {
    private final SharedAccountLoginViewModel viewModel;
    private final JTextField sharedAccountIdField;

    /**
     * Constructs a SharedAccountLoginPanel object with the specified view model, login controller, and view manager.
     *
     * @param viewModel        the view model for the shared account login panel
     * @param loginController  the controller handling login actions
     * @param viewManager      the view manager for managing view transitions
     */
    public SharedAccountLoginPanel(SharedAccountLoginViewModel viewModel, LoginController loginController, ViewManagerModel viewManager) {
        super(viewModel, loginController, viewManager);
        this.viewModel = viewModel;
        this.sharedAccountIdField = new JTextField(20); // Field for shared account ID
    }

    /**
     * Initializes additional components specific to the shared account login panel.
     */
    @Override
    protected void initializeComponents() {
        super.initializeComponents();
        sharedAccountIdField.setToolTipText("Enter Shared Account ID");
    }

    /**
     * Sets up the user interface for the shared account login panel, arranging components using a GridBagLayout.
     * Adds components such as labels, text fields, and buttons to the panel.
     */
    @Override
    protected void setupUI() {
        super.setupUI();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);  // Padding

        // Add shared account ID field
        constraints.gridx = 0;
        constraints.gridy = 3; // Place below password field
        constraints.anchor = GridBagConstraints.WEST;
        add(new JLabel(viewModel.getSharedAccountIdLabel()), constraints); // Label for shared account ID
        constraints.gridx = 1;
        add(this.sharedAccountIdField, constraints); // Text field for shared account ID
    }

    /**
     * Sets up listeners for user interactions, including button clicks and key presses.
     * Updates the view model based on user input and handles actions for login and cancel buttons.
     */
    @Override
    protected void setupListeners() {
        super.setupListeners();

        // Override login button action listener to handle shared account login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(loginButton)) {
                    loginController.execute(
                            String.valueOf(passwordField.getPassword()),
                            identificationTextField.getText(),
                            sharedAccountIdField.getText() // Include shared account ID
                    );
                }
            }
        });

        // Listen for shared account ID field changes
        this.sharedAccountIdField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                viewModel.getState().setSharedAccountId(sharedAccountIdField.getText() + evt.getKeyChar());
                viewModel.firePropertyChanged();
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    /**
     * Clears the text fields for identification, password, and shared account ID.
     */
    @Override
    public void clearFields() {
        super.clearFields();
        sharedAccountIdField.setText(""); // Clear shared account ID field
    }
}


