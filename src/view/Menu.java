package view;

import java.util.Scanner;

public class Menu {
    public void displayMenu(){
        System.out.println("====== CROSSFIRE ======");
        System.out.println("1. Login");
        System.out.println("2. Register");
    }
    public void viewOptionAfterLogin() {
        System.out.println("1. Change your username");
        System.out.println("2. Change your email");
        System.out.println("3. Change your password");
        System.out.println("4. Logout");
        System.out.println("5. Quit");
    }
    public void reEnterOrForgetPass() {
        System.out.println("Incorrect password, please choose: ");
        System.out.println("1. Re-enter password.");
        System.out.println("2. Forget password?");
    }
}
