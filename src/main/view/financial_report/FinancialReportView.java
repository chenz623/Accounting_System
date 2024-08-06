package view.financial_report;

import interface_adaptors.financial_report.FinancialReportController;
import interface_adaptors.financial_report.FinancialReportState;
import interface_adaptors.financial_report.FinancialReportViewModel;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.PeriodicTransactionController;
import interface_adaptors.transaction.periodic.PeriodicTransactionState;
import interface_adaptors.transaction.periodic.PeriodicTransactionViewModel;
import use_case.financial_report.FinancialReportInputData;
import view.transaction.periodic.PeriodicTransactionPanel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FinancialReportView extends JFrame implements PropertyChangeListener {
    private FinancialReportPanel financialReportPanel;
    private FinancialReportViewModel viewModel;
    private FinancialReportController financialReportController;
    private ViewManagerModel viewManager;

    public FinancialReportView (FinancialReportViewModel viewModel,
                                FinancialReportController financialReportController,
                                ViewManagerModel viewManager) {
        super("Financial Report");
        this.viewModel = viewModel;
        this.viewManager = viewManager;
        this.viewModel.addPropertyChangeListener(this);
        this.financialReportController = financialReportController;

//        this.viewModel.addPropertyChangeListener(evt -> {
//            if ("state".equals(evt.getPropertyName())){
//
//            }
//        });



        this.financialReportPanel = new FinancialReportPanel(this.viewModel, this.financialReportController,
                this.viewManager);

        setupUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setVisible(true);
    }

    private void setupUI() {
        this.getContentPane().add(this.financialReportPanel, BorderLayout.CENTER);
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
            String id = this.viewManager.getUserId();
            this.financialReportController.execute(id);
        }
    }
}