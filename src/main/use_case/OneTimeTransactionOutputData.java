package use_case;

import entity.OneTimeOutflow;
import entity.OneTimeInflow;

import java.time.LocalDate;

public class OneTimeTransactionOutputData {

    private float newBalance;
    private LocalDate transactionDate;
    private String transactionDescription;
    private String transactionCategory;

    private boolean useCaseFailed;

    // Constructor for outflow
    public OneTimeTransactionOutputData(OneTimeOutflow oneTimeOutflow, float newBalance) {
        this.newBalance = newBalance;
        this.transactionDate = oneTimeOutflow.getDate();
        this.transactionDescription = oneTimeOutflow.getDescription();
        this.transactionCategory = oneTimeOutflow.getCategory();
        this.useCaseFailed = false;
    }
    // Constructor for inflow
    public OneTimeTransactionOutputData(OneTimeInflow oneTimeInflow, float newBalance) {
        this.newBalance = newBalance;
        this.transactionDate = oneTimeInflow.getDate();
        this.transactionDescription = oneTimeInflow.getDescription();
        this.transactionCategory = oneTimeInflow.getCategory();
        this.useCaseFailed = false;
    }

    public float getNewBalance() {
            return newBalance;
        }

    public LocalDate getTransactionDate() {
        return transactionDate;
        }

    public String getTransactionDescription() {
            return transactionDescription;
        }

    public String getTransactionCategory() {
            return transactionCategory;
        }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public void setUseCaseFailed(boolean useCaseFailed) {}

    public void setTransactionDate(LocalDate transactionDate) {
            this.transactionDate = transactionDate;
        }
}