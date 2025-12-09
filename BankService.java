package bankingsystem;

public class BankService {

    private BankDatabase db = new BankDatabase();

    // Create new account
    public String createAccount(String name, String password) {
        String accNo = "ACC" + (int)(Math.random() * 10000);
        User user = new User(accNo, name, password, 0.0);
        db.addUser(user);
        return accNo;
    }

    // Login method
    public User login(String accNo, String password) {
        User user = db.getUser(accNo);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // Deposit money
    public void deposit(User user, double amount) {
        user.setBalance(user.getBalance() + amount);
    }

    // Withdraw money
    public boolean withdraw(User user, double amount) {
        if (user.getBalance() >= amount) {
            user.setBalance(user.getBalance() - amount);
            return true;
        }
        return false;
    }

    // Transfer money to another account
    public boolean transfer(User fromUser, String toAccNo, double amount) {
        User receiver = db.getUser(toAccNo);
        if (receiver != null && fromUser.getBalance() >= amount) {
            fromUser.setBalance(fromUser.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);
            return true;
        }
        return false;
    }
}
