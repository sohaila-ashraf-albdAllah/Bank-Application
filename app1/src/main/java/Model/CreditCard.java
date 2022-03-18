package Model;

public class CreditCard {
    private String cardnumber,accnumber;
    private double balance;



    public CreditCard(String cardnumber, double balance, String accnumber ) {
        this.cardnumber = cardnumber;
        this.accnumber = accnumber;
        this.balance = balance;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getAccnumber() {
        return accnumber;
    }

    public void setAccnumber(String accnumber) {
        this.accnumber = accnumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
