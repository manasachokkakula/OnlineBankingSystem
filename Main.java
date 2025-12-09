package bankingsystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankService bank = new BankService();
        User loggedUser = null;

        while (true) {
            System.out.println("\n===== ONLINE BANKING SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    System.out.print("Enter Password: ");
                    String pass = sc.next();

                    String accNo = bank.createAccount(name, pass);
                    System.out.println("Account Created Successfully!");
                    System.out.println("Your Account Number: " + accNo);
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    String a = sc.next();
                    System.out.print("Enter Password: ");
                    String p = sc.next();

                    loggedUser = bank.login(a, p);

                    if (loggedUser != null) {
                        System.out.println("Login Successful!");
                        userMenu(sc, bank, loggedUser);
                    } else {
                        System.out.println("Invalid Credentials!");
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using our service!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    // User menu after login
    static void userMenu(Scanner sc, BankService bank, User user) {
        while (true) {
            System.out.println("\n===== USER MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");

            int c = sc.nextInt();

            switch (c) {
                case 1:
                    System.out.println("Balance: " + user.getBalance());
                    break;

                case 2:
                    System.out.print("Enter amount: ");
                    bank.deposit(user, sc.nextDouble());
                    System.out.println("Deposit Successful!");
                    break;

                case 3:
                    System.out.print("Enter amount: ");
                    if (bank.withdraw(user, sc.nextDouble()))
                        System.out.println("Withdrawal Successful!");
                    else
                        System.out.println("Insufficient Balance!");
                    break;

                case 4:
                    System.out.print("Enter Receiver Account Number: ");
                    String r = sc.next();
                    System.out.print("Enter amount: ");
                    double amt = sc.nextDouble();

                    if (bank.transfer(user, r, amt))
                        System.out.println("Transfer Successful!");
                    else
                        System.out.println("Transfer Failed!");
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
