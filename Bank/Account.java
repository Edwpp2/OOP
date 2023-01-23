package Bank;

public class Account {

    private int balance;

    public Account(){}

    public void deposit(int money)
    {
        if (money >= 0)
        {
            this.balance += money;
        }
    }

    public void withdraw(int money)
    {
        if (money >= 0 && this.balance - money >= 0)
        {
            this.balance -= money;
        }
    }

    public void transfer(int money, Account account)
    {
        if (money >= 0 && this.balance - money >= 0)
        {
            this.balance -= money;
            account.balance += money;
        }
    }

    public int getBalance()
    {
        return this.balance;
    }

    public String toString()
    {
        return "Balance:" + this.balance;
    }
}
