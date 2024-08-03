package view.FinancialReport;

import interface_adaptors.financial_report.FinancialReportController;
import interface_adaptors.financial_report.FinancialReportState;
import interface_adaptors.financial_report.FinancialReportViewModel;
import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FinancialReportView extends JFrame implements PropertyChangeListener {
    private FinancialReportPanel financialReportPanel;
    private FinancialReportViewModel viewModel;

    public FinancialReportView (FinancialReportViewModel viewModel,
                                FinancialReportController financialReportController,
                                ViewManagerModel viewManager) {
        super("Financial Report");
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        financialReportPanel = new FinancialReportPanel(viewModel, financialReportController, viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    private void setupUI() {
        this.getContentPane().add(financialReportPanel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        FinancialReportState state = (FinancialReportState) evt.getNewValue();
    }

    /**
     * Makes the view visible and clears the fields in the periodic transaction panel when the view becomes visible.
     *
     * @param visible whether the view should be visible
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            financialReportPanel.clearFields(); // Clear the fields when the view becomes visible
        }
    }
}