package entity;

public abstract class OneTimeTransaction implements Transaction {
    protected float transactionAmount;
    private transactionDate;
    private String transactionDescription;
    private String transactionCategory;
    private String TransactionNotes;

    public OneTimeTransaction(float transactionAmount, String transactionDate,  String transactionDescription,
                              String transactionCategory, String TransactionNotes) {
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionDescription = transactionDescription;
        this.transactionCategory = transactionCategory;
        this.TransactionNotes = TransactionNotes;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public String getTransactionDate() {
        return TransactionDate
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public String getTransactionNotes() {
        return TransactionNotes;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setTransactionDate(String transactionDate) {
        this.TransactionDate = transactionDate
    }
    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }
    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }
    public void setTransactionNotes(String transactionNotes) {
        this.TransactionNotes = transactionNotes;
    }

    // Abstract method to record the transaction
    public abstract void recordTransaction(float amount);

    public void displayTransactionDetails() {
        System.out.println("amount: " + transactionAmount);
        System.out.println("Date: " + transactionDate);
        System.out.println("Descriptioon: " + transactionDescription);
        System.out.println("Category: " + transactionCategory);
        System.out.println("Additional Notes: " + TransactionNotes);
    }
}
