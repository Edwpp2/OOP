package Bank;

import java.util.UUID;

public class User {
    private String name;
    private Account account;
    private String password;
    private UUID id;

    {
        this.id = UUID.randomUUID();
        this.account = new Account();
    }

    public User(){}

    public User(String name,String password)
    {
        this.name = name;
        this.password = password;
    }
    public void setAccont(Account account)
    {
        this.account = account;
    }
    public Account getAccount()
    {
        return account;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String toString()
    {
        return "Name: " + name + ", " + "id: " + id + ", " + "Balance: " + this.account.getBalance() + "\n";
    }
    
    public int hashCode()
    {
        int hash = 0;
        for(int i = 0; i < name.length();i++)
        {
            hash += name.charAt(i);
        }
        return hash;
    }
    
    public boolean equals(Object obj)
    {
        User user = (User)obj;
        return this.name.equals(user.name) || this == user;
    }
    

}
