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
        final int MIN_BALANCE = 0;
        final int MIN_MONEY = 0;

        if (balance - money < MIN_BALANCE && money >= MIN_MONEY && operationType == OperationType.Decrease)
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
            while (authorizedUser == null)
            {
                System.out.println("Выберите опцию\nEnter - для входа в систему\nRegister - для регестрации нового пользователя");
                String command = input.next();
                if (command.equalsIgnoreCase("Register"))
                {   
                    boolean isRegistrationComplete = false;
                    while(!isRegistrationComplete)
                    {
                        System.out.println("Введите имя пользователя или Quite для возврата на предыдущий экран");
                        String name = input.next();
                        if(name.equalsIgnoreCase("Quite")){break;}
                        System.out.println("Введите пароль для пользователя Quite для возврата на предыдущий экран");
                        String password = input.next();
                        if(password.equalsIgnoreCase("Quite")){break;}
                        if(bank.users.stream().anyMatch(u -> u.getName().equalsIgnoreCase(name)))
                        {
                            System.out.println("Пользователь с таким именем уже существует");
                            break;
                        }
                        if(password.equalsIgnoreCase("Quite") || name.equalsIgnoreCase("Quite"))
                        {
                            break;
                        }
                        else
                        {
                            User registeredUser = new User(name, password);
                            bank.registerUser(registeredUser);
                            isRegistrationComplete = true;
                            System.out.println("Пользователь успешно зарегистрирован");
                        }
                    }
                }
                else if (command.equalsIgnoreCase("Enter"))
                {
                    System.out.println("Пожалуйста введите имя пользователя:");
                    String name = input.next();
                    
                    System.out.println("Пожалуйста введите пароль:");
                    String password = input.next();
                    
                    if (bank.users.stream().anyMatch(user -> user.getName().equalsIgnoreCase(name) && user.getPassword().equals(password))) 
                    {
                        this.authorizedUser = bank.users.stream().filter(u -> u.getName().equalsIgnoreCase(name) && u.getPassword().equals(password)).findFirst().orElse(null);
                        System.out.println("Вы успешно авторизовались!");
                        
                        break;
                    }
                    else
                    {
                        System.out.println("Неверное имя пользователя или пароль!");
                    }
                }
                else if(!command.equals("Register") && !command.equals("Enter"))
                {   
                    System.out.println("Введена не верная комманда\n");
                }
            }

            while (authorizedUser != null) {

                System.out.println("Выберите операцию:\n");
                System.out.println("Введите комманду Windrav снятия");
                System.out.println("Введите комманду Deposite для пополнения депозита");
                System.out.println("Введите комманду Transfer для перевода");
                System.out.println("Введите комманду Info для просмотра информации o счете");
                System.out.println("Введите комманду Delete для удалдения текущего пользователя");
                System.out.println("Введите комманду Find для поиска пользователя в системе");
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

                    if(bank.users.stream().anyMatch(u -> u.getName().equalsIgnoreCase(name)))
                    {
                        System.out.println("Ведите сумму:");
                        Account account = bank.users.stream().filter(u -> u.getName().equalsIgnoreCase(name)).findFirst().orElse(null).getAccount();
                        int money = input.nextInt();
                        authorizedUser.getAccount().transfer(money, account);
                        printOperationMessage(authorizedUser.getAccount().getBalance(), money, OperationType.Decrease);
                        break;
                    }
                    else
                    {
                        System.out.println("Данный пользователь не обнаружен");
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
                    if(bank.users.stream().anyMatch(u -> u.getName().equalsIgnoreCase(name)))
                    {
                        User user = bank.users.stream().filter(u -> u.getName().equalsIgnoreCase(name)).findFirst().orElse(null); 
                        System.out.println(user.toString());
                        break;
                    }
                    else
                    {
                        System.out.println("Пользователь с таким именем не обнаружен");
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
