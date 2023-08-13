package service;
import entities.Account;
import view.Menu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountService {
    public static boolean validPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*[.,-_;])(?!.*\\s).{7,15}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean validEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public Account registerAccount(ArrayList<Account> accounts, Scanner scanner) {
        System.out.println("------ REGISTER -------");
        do {
            int count = 0;
            System.out.println("Please input your username:");
            String username = scanner.nextLine();
            for (Account us : accounts) {
                if (us.getUsername().equalsIgnoreCase(username)) {
                    count++;
                }
            }
            if (count != 0) {
                System.out.println("Username already exists, try again!");
                continue;
            } else {
                do {
                    System.out.println("Please input your email:");
                    String email = scanner.nextLine();
                    if (!AccountService.validEmail(email)) {
                        System.out.println("Incorrect email, please try again!");
                        continue;
                    } else {
                        do {
                            System.out.println("Please input your password:");
                            String password = scanner.nextLine();
                            if (!AccountService.validPassword(password)) {
                                System.out.println("Incorrect password, please try again!");
                                continue;
                            } else {
                                System.out.println("Register user successful!!!");
                                return new Account(username, password, email);
                            }
                        }
                        while (true);
                    }
                }
                while (true);
            }
        } while (true);
    }

    public void Login(Scanner scanner, ArrayList<Account> accounts, Menu menu, AccountService accountService) {
        do {
            if (accounts.size() == 0) {
                System.out.println("No user exists, please register first!");
                accounts.add(registerAccount(accounts,scanner));
            }
            else {
                do {
                    System.out.println("Please input your username:");
                    String username = scanner.nextLine();
                    for (Account account : accounts) {
                        if (account.getUsername().equalsIgnoreCase(username)) {
                            checkPassword(scanner,account,menu);
                            menu.viewOptionAfterLogin();
                            selectOptionAfterLogin(scanner,account,accounts,menu);
                            break;
                        }
                        else {
                            System.out.println("Incorrect username, please try again!");
                        }
                    }
                    break;
                }
                while (true);
            }
            break;
        }
        while (true);
    }
    public void checkPassword(Scanner scanner, Account account, Menu menu) {
        do {
            System.out.println("Please input your password:");
            String password = scanner.nextLine();
            if (account.getPassword().equals(password)) {
                menu.reEnterOrForgetPass();
                int choosePw = Integer.parseInt(scanner.nextLine());
                switch (choosePw) {
                    case 1 -> {
                        continue;
                    }
                    case 2 -> {
                        changePassword(scanner, account);
                    }
                }
            }
            else {
                System.out.println("------ Login successful! ------");
            }
            break;
        }
        while (true);
    }
    public void selectOptionAfterLogin(Scanner scanner, Account account, ArrayList<Account> accounts, Menu menu) {
        do {
            menu.viewOptionAfterLogin();
            System.out.println("Welcome " + account.getUsername() + " , please choose the options above:");
            int select = Integer.parseInt(scanner.nextLine());
            switch (select) {
                case 1:
                    changeUsername(scanner, account, accounts);
                    continue;
                case 2:
                    changeEmail(scanner, account, accounts);
                    continue;
                case 3:
                    changePassword(scanner, account);
                    continue;
                case 4:
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
            }
            break;
        }
        while (true);
    }
    public void changePassword(Scanner scanner, Account account) {
        do {
            System.out.println("Please input your email:");
            String email = scanner.nextLine();
            if (!account.getEmail().equals(email)) {
                System.out.println("Incorrect email, please try again!");
                continue;
            }
                do {
                    System.out.println("Please input new password: ");
                    String newPassword = scanner.nextLine();
                    if (!AccountService.validPassword(newPassword)) {
                        System.out.println("Incorrect password, please try again!");
                        continue;
                    }
                    else {
                        System.out.println("Create new password successful!!!");
                        account.setPassword(newPassword);
                    }
                    break;
                }
                while (true);
            break;
        }
        while (true);
    }
    public void changeUsername(Scanner scanner, Account account, ArrayList<Account> accounts) {
        int count = 0;
        do {
            System.out.println("Please re-input your username:");
            String username = scanner.nextLine();
            if (!account.getUsername().equalsIgnoreCase(username)) {
                System.out.println("Incorrect username, please try again!");
                continue;
            }
            else {
                do {
                    System.out.println("Please re-input your password:");
                    String password = scanner.nextLine();
                    if (!account.getPassword().equalsIgnoreCase(password)) {
                        System.out.println("Incorrect password, please try again!");
                        continue;
                    }
                    else {
                        do {
                            System.out.println("Please enter your new username:");
                            String newUsername = scanner.nextLine();
                            for (Account us : accounts) {
                                if (us.getUsername().equalsIgnoreCase(newUsername)) count++;
                            }
                            if (count > 0) {
                                System.out.println("Username already exists, please re-enter");
                                continue;
                            }
                            else {
                                account.setUsername(newUsername);
                                System.out.println("Create new username successful!!!");
                            }
                            break;
                        }
                        while (true);
                    }
                    break;
                }
                while (true);
            }
            break;
        }
        while (true);
    }
    public void changeEmail(Scanner scanner, Account account, ArrayList<Account> accounts) {
        int count = 0;
        do {
            System.out.println("Please re-input your email:");
            String email = scanner.nextLine();
            if (!account.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Incorrect email, please try again!");
                continue;
            }
            do {
                System.out.println("Please input your password:");
                String password = scanner.nextLine();
                if (!account.getPassword().equalsIgnoreCase(password)) {
                    System.out.println("Incorrect password, please try again!");
                    continue;
                }
                else {
                    do {
                        count = 0;
                        System.out.println("Please enter your new email:");
                        String newEmail = scanner.nextLine();
                        if (count != 0) {
                            System.out.println("Email already exists, please re-enter");
                            continue;
                        }
                        account.setEmail(newEmail);
                        System.out.println("Create new email successful!!!");
                        break;
                    }
                    while (true);
                }
                break;
            }
            while (true);
            break;
        }
        while (true);
    }
}


