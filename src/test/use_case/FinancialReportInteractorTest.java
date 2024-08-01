package use_case;

import entity.account.UserAccount;
import entity.transaction.Transaction;
import entity.transaction.one_time.OneTimeTransaction;
import entity.transaction.periodic.PeriodicTransaction;
import interface_adaptors.FinancialReport.FinancialReportPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.FinancialReport.FinancialReportInputData;
import use_case.FinancialReport.FinancialReportInteractor;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the FinancialReportInteractor class.
 *
 * Author: Chi Fong Huang
 */
public class FinancialReportInteractorTest {

    private UserAccount account;
    private FinancialReportPresenter presenter;
    private FinancialReportInteractor interactor;

    @BeforeEach
    public void setUp() {
        account = new UserAccount("testUser", "password", "id123");
        presenter = new FinancialReportPresenter();
        interactor = new FinancialReportInteractor(account, presenter);

        // test for one time
        Transaction t1 = new OneTimeTransaction("t1", 100.0f, LocalDate.of(2024, 7, 1), "Salary", "Income") {};
        Transaction t2 = new OneTimeTransaction("t2", -50.0f, LocalDate.of(2024, 7, 2), "Groceries", "Expense") {};
        account.addTransaction(t1);
        account.addTransaction(t2);

        // test Periodic transactions
        Transaction p1 = new PeriodicTransaction("p1", 500.0f, LocalDate.of(2024, 7, 1), "Rent", LocalDate.of(2025, 7, 1), 30) {};
        account.addTransaction(p1);
    }

    @Test
    public void testGenerateReport() {
        FinancialReportInputData inputData = new FinancialReportInputData("id123", new Date(), new Date());
        interactor.generateReport(inputData);

        String reportContent = presenter.getReportContent();

        // Assert that the report contains the data for One-Time transactions
        assertTrue(reportContent.contains("testUser"));
        assertTrue(reportContent.contains("Total Income: 600.0"));
        assertTrue(reportContent.contains("Total Outflow: -50.0"));
        assertTrue(reportContent.contains("Total Balance: 550.0"));
        assertTrue(reportContent.contains("Salary"));
        assertTrue(reportContent.contains("Groceries"));

        // Assert that the report contains the data for Periodic transactions
        assertTrue(reportContent.contains("Rent"));
        assertTrue(reportContent.contains("500.0"));
    }
}
