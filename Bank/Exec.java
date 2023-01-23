package Bank;
public class Exec {
    public static void main(String[] args) 
    {
        Bank B = new Bank();
        ATM atm = new ATM();
        atm.authorization(B);
    }
}
