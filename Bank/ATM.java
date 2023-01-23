package Bank;

import java.util.Scanner;

public class ATM {
    Bank bank;
    User user;
    User authorizedUser;
    Scanner input = new Scanner(System.in);

    public ATM(){}

    public void printOperationMessage(int balance, int money, OperationType operationType)
    {
        if (balance - money < 0 && money >= 0 && operationType == OperationType.Decrease)
        {
            System.out.println("Ошибка: на счету недостаточно средств\n");
        } 
        else if (money < 0)
        {
            System.out.println("Ошибка: введена отрицательная сумма\n");
        } else 
        {
            System.out.println("Операция успешно проведена\n");
        }
    }

    public void authorization(Bank bank) 
    {
        while (true) {
            System.out.println("Добро пожаловать в банокам GachyGangBang*c");
            while (authorizedUser == null) {
                System.out.println("Выберите опцию\nEnter - для входа в систему\nRegister - для регестрации нового пользователя");
                String command = input.next();
                if (command.equalsIgnoreCase("Register"))
                {
                    while(true)
                    {
                        System.out.println("Введите имя пользователя или Quite для возврата на предыдущий экран");
                        String name = input.next();
                        if(name.equalsIgnoreCase("Quite")){break;}
                        System.out.println("Введите пароль для пользователя Quite для возврата на предыдущий экран");
                        String password = input.next();
                        if(password.equalsIgnoreCase("Quite")){break;}
                        int userCnt = 0;
                        for (User user : bank.users) 
                        {   
                            userCnt++;
                            if (user.getName().equals(name))
                            {
                                System.out.println("Пользователь с таким именем уже существует");;
                                break;
                            }
                        }
                        if(userCnt == bank.users.size())
                        {
                            System.out.println("Пользователь успешно зарегистрирован");
                            User registeredUser = new User(name, password);
                            bank.registerUser(registeredUser);
                            userCnt = 0;
                            break;
                        }
                        if(password.equalsIgnoreCase("Quite") || name.equalsIgnoreCase("Quite"))
                        {
                            break;
                        }
                    }
                }
                else if (command.equalsIgnoreCase("Enter"))
                {
                    System.out.println("Пожалуйста введите имя пользователя:");
                    String name = input.next();
                    
                    System.out.println("Пожалуйста введите пароль:");
                    String password = input.next();
                    
                    for (User user : bank.users) {
                        if (user.getName().equals(name) && user.getPassword().equals(password)) 
                        {
                            authorizedUser = user;
                            System.out.println("Вы успешно авторизовались!");
                            break;
                        }
                    }
                }
                if (authorizedUser == null && command.equalsIgnoreCase("Enter")) 
                {
                    System.out.println("Неверное имя пользователя или пароль!");
                }

                if(!command.equals("Register") && !command.equals("Enter"))
                {   
                    System.out.println("Введена не верная комманда\n");
                }
            }

            while (authorizedUser != null || input.hasNext() == true) {

                System.out.println("Выберите операцию:\n");
                System.out.println("Введите комманду Windrav снятия");
                System.out.println("Введите комманду Deposite для пополнения депозита");
                System.out.println("Введите комманду Transfer для перевода");
                System.out.println("Введите комманду Info для просмотра информации o счете");
                System.out.println("Введите комманду Delete для выхода\n");
                System.out.println("Введите комманду Quite для выхода\n");

                String command = input.next();
                if (command.equalsIgnoreCase("Windrav"))
                {
                    System.out.println("Ведите сумму:");
                    int money = input.nextInt();
                    authorizedUser.getAccount().withdraw(money);
                    printOperationMessage(authorizedUser.getAccount().getBalance(), money, OperationType.Decrease);
                } 
                else if (command.equalsIgnoreCase("Deposite")) 
                {
                    System.out.println("Ведите сумму:");
                    int money = input.nextInt();
                    authorizedUser.getAccount().deposit(money);
                    printOperationMessage(authorizedUser.getAccount().getBalance(), money, OperationType.Increase);

                } 
                else if (command.equalsIgnoreCase("Transfer"))
                {
                    System.out.println("Ведите имя пользователя:");

                    String name = input.next();
                    int userCnt = 0;
                    for (User user : bank.users) 
                    {
                        userCnt++;
                        if (user.getName().equalsIgnoreCase(name))
                        {
                            System.out.println("Ведите сумму:");

                            int money = input.nextInt();

                            printOperationMessage(authorizedUser.getAccount().getBalance(), money, OperationType.Decrease);
                            authorizedUser.getAccount().transfer(money, user.getAccount());
                            break;
                        }
                        if(userCnt == bank.users.size())
                        {
                            System.out.println("Данный пользователь не обнаружен");
                            userCnt = 0;
                        }
                        

                    }
                    
                } 
                else if (command.equalsIgnoreCase("Quite")) 
                {
                    authorizedUser = null;
                    break;
                } 
                else if (command.equalsIgnoreCase("Info")) 
                {
                    System.out.println(authorizedUser.toString());
                }
                else if (command.equalsIgnoreCase("Delete")) 
                {
                    bank.deleteAccount(authorizedUser);
                    authorizedUser = null;
                    break;
                }
                else if (command.equalsIgnoreCase("Bank"))
                {
                    for (User user : bank.users) {
                        System.out.println(user.toString());
                    }
                }
                else if(command.equalsIgnoreCase("Find"))
                {   
                    System.out.println("Введите имя пользователя\n");
                    String name = input.next();
                    int userCnt = 0;
                    for (User user : bank.users) 
                    {   
                        userCnt++;
                        if (user.getName().equals(name))
                        {
                            System.out.println(user.toString());
                            break;
                        }
                    }
                    if(userCnt == bank.users.size())
                    {
                        System.out.println("Данный пользователь не обнаружен");
                        userCnt = 0;
                    }

                }
                else 
                {
                    System.out.println("Введена не верная комманда!\n");
                }
            }
        }
    }
}
