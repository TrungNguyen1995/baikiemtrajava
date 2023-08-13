import entities.Account;
import service.AccountService;
import view.Menu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList <Account> accounts = new ArrayList<>();
        AccountService accountService = new AccountService();
        Menu menu = new Menu();
        do {
            menu.displayMenu();
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1 -> {
                    accountService.Login(scanner,accounts,menu,accountService);
                    continue;
                }
                case 2 -> {
                    accounts.add(accountService.registerAccount(accounts,scanner));
                    continue;
                }
            }
            break;
        }
        while (true);
    }
}