package Bank;

import java.util.HashSet;

public class Bank
{
    HashSet<User> users;
    User user;

    public Bank()
    {
        users = new HashSet<User>();
    }

    public void registerUser(User user)
    {
        users.add(user);
    }

    public void deleteAccount(User user)
    {
        user.setAccont(null);
        users.remove(user);
    }

    public Account getAccount(User user)
    {
        return user.getAccount();
    }

    public String toString()
    {
        String result = "";

        for (User user : users)
        {
            result += user.toString() + "\n";
        }
        return result;
    }

}
