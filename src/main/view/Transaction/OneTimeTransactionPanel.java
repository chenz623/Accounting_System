package view.Transaction;

import interface_adaptors.*;
import interface_adaptors.transaction.one_time.OneTimeTransactionController;
import interface_adaptors.transaction.one_time.OneTimeTransactionState;
import interface_adaptors.transaction.one_time.OneTimeTransactionViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The OneTimeTransactionPanel class represents the user interface for a one-time transaction form.
 * It extends JPanel and handles user inputs and interactions related to one-time transactions.
 *
 * @author Jessica
 * @author Eric
 */
public class OneTimeTransactionPanel extends JPanel {
    private final OneTimeTransactionViewModel viewModel;
    private final OneTimeTransactionController oneTimeTransactionController;
    private final ViewManagerModel viewManager;

    private JLabel titleLabel;
//    private JTextField idField;
    private JTextField amountField;
    private JTextField dateField;
    private JTextField descriptionField;
    private JTextField categoryField;
    private JButton submitButton;
    private JButton cancelButton;

    /**
     * Constructs a OneTimeTransactionPanel object with the specified view model, controller, and view manager.
     *
     * @param viewModel                  the view model for the one-time transaction panel
     * @param oneTimeTransactionController the controller handling one-time transaction actions
     * @param viewManager                the view manager for handling view transitions
     */
    public OneTimeTransactionPanel(OneTimeTransactionViewModel viewModel,
                                   OneTimeTransactionController oneTimeTransactionController,
                                   ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.oneTimeTransactionController = oneTimeTransactionController;
        this.viewManager = viewManager;
        initializeComponents();
        setupUI();
        setupListeners();
    }

    /**
     * Initializes the components for the one-time transaction panel, including labels, text fields, and buttons.
     */
    private void initializeComponents() {
        // title layout
        this.titleLabel = new JLabel(viewModel.getTitleLabel());
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

//        this.idField = new JTextField(20);
        this.amountField = new JTextField(20);
        this.dateField = new JTextField(20);
        this.descriptionField = new JTextField(20);
        this.categoryField = new JTextField(20);

        // add buttons
        JPanel buttons = new JPanel();
        this.submitButton = new JButton(this.viewModel.getSubmitButton());
        buttons.add(this.submitButton);
        this.cancelButton = new JButton(this.viewModel.getCancelButton());
        buttons.add(this.cancelButton);

        // Style buttons
        this.submitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.cancelButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.submitButton.setBackground(new Color(100, 150, 200));
        this.cancelButton.setBackground(new Color(200, 100, 100));
        this.submitButton.setForeground(Color.WHITE);
        this.cancelButton.setForeground(Color.WHITE);
    }

    /**
     * Sets up the user interface layout for the one-time transaction panel.
     */
    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);  // pad

        // title label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(this.titleLabel, constraints);

        // reset gridwidth and anchor for other components
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth = 1;

        // Amount
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getAmount()), constraints);
        constraints.gridx = 1;
        add(this.amountField, constraints);

        // Date
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getDate()), constraints);
        constraints.gridx = 1;
        add(this.dateField, constraints);

        // Description
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getDescription()), constraints);
        constraints.gridx = 1;
        add(this.descriptionField, constraints);

        // Category
        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel(viewModel.getCategory()), constraints);
        constraints.gridx = 1;
        add(this.categoryField, constraints);

        // Submit button
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        add(submitButton, constraints);

        // Cancel button
        constraints.gridy++;
        add(cancelButton, constraints);
    }

    /**
     * Sets up listeners for user interactions, including submit and cancel actions.
     */
    private void setupListeners() {
        // submit button response action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(submitButton)) {
                    oneTimeTransactionController.execute(
                            amountField.getText(),
                            dateField.getText(),
                            descriptionField.getText(),
                            categoryField.getText()
                    );
                }
            }
        });

        // cancel button response action
        cancelButton.addActionListener(e -> {
            viewManager.setActiveViewName("Transaction");
        });

        // get typed amount
        this.amountField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent evt) {
                        OneTimeTransactionState currentState = viewModel.getState();
                        currentState.setTransactionAmount(amountField.getText() + evt.getKeyChar());
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        // get typed date
        this.dateField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent evt) {
                        OneTimeTransactionState currentState = viewModel.getState();
                        currentState.setTransactionDate(dateField.getText() + evt.getKeyChar());
                        viewModel.setState(currentState);
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        // get typed description
        this.descriptionField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent evt) {
                        OneTimeTransactionState currentState = viewModel.getState();
                        currentState.setTransactionDescription(descriptionField.getText() + evt.getKeyChar());
                        viewModel.setState(currentState);
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        // get typed category
        this.categoryField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent evt) {
                        OneTimeTransactionState currentState = viewModel.getState();
                        currentState.setTransactionCategory(categoryField.getText() + evt.getKeyChar());
                        viewModel.setState(currentState);
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );
    }

    /**
     * Clears all input fields in the one-time transaction panel.
     */
    public void clearFields() {
        amountField.setText("");
        dateField.setText("");
        descriptionField.setText("");
        categoryField.setText("");
    }
}
