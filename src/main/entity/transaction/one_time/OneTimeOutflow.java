package entity.transaction.one_time;

import java.time.LocalDate;

/**
 * The OneTimeOutflow class represents a single, non-recurring outflow transaction.
 * It extends the OneTimeTransaction class.
 *
 * @author Dana
 * @author Jessica
 * @author Eric
 */
public class OneTimeOutflow extends OneTimeTransaction {

    /**
     * Constructs a OneTimeOutflow object with the specified details.
     *
     * @param identification the identification of the transaction
     * @param amount         the amount of the transaction
     * @param date           the date of the transaction
     * @param description    the description of the transaction
     * @param category       the category of the transaction
     */
    public OneTimeOutflow(String identification, float amount, LocalDate date, String description, String category) {
        super(identification, amount, date, description, category);
    }
}