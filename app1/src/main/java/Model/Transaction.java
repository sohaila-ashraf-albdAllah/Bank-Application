package Model;

public class Transaction {
    private int id;
    private String toacc,fromacc;
    private double balance;
    private String date;

    public Transaction(int id, String toacc, String fromacc, double balance, String date) {
        this.id = id;
        this.toacc = toacc;
        this.fromacc = fromacc;
        this.balance = balance;
        this.date = date;
    }

    public Transaction(String toacc, String fromacc, double balance, String date) {
        this.toacc = toacc;
        this.fromacc = fromacc;
        this.balance = balance;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToacc() {
        return toacc;
    }

    public void setToacc(String toacc) {
        this.toacc = toacc;
    }

    public String getFromacc() {
        return fromacc;
    }

    public void setFromacc(String fromacc) {
        this.fromacc = fromacc;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
