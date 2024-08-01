package use_case.transaction.one_time;

import entity.transaction.one_time.OneTimeOutflow;
import entity.transaction.one_time.OneTimeInflow;
import entity.transaction.one_time.OneTimeTransaction;
import use_case.transaction.TransactionOutputData;

import java.time.LocalDate;

/**
 * The OneTimeTransactionOutputData class represents the output data of a one-time transaction operation.
 * It includes details such as the new balance, transaction amount, date, description, category, and status of the use case.
 *
 * @author Dana
 * @author Eric
 * @author Jessica
 */
public class OneTimeTransactionOutputData extends TransactionOutputData<OneTimeTransaction> {

    // private float newBalance;
    // private boolean useCaseFailed;

    /**
     * Constructs a OneTimeTransactionOutputData object for an outflow transaction with the specified details.
     *
     * @param oneTimeTransaction the outflow transaction entity
     */
    public OneTimeTransactionOutputData(OneTimeTransaction oneTimeTransaction) {
        super(oneTimeTransaction);
        // this.useCaseFailed = false;
    }

//    /**
//     * Checks if the use case has failed.
//     *
//     * @return true if the use case has failed, false otherwise
//     */
//    public boolean isUseCaseFailed() {
//        return this.useCaseFailed;
//    }
//
//    /**
//     * Sets the status of the use case failure.
//     *
//     * @param useCaseFailed the new status of the use case failure
//     */
//    public void setUseCaseFailed(boolean useCaseFailed) { this.useCaseFailed = useCaseFailed; }
}