package bankingsystem;

import java.util.HashMap;

public class BankDatabase {

    private HashMap<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getAccountNumber(), user);
    }

    public User getUser(String accountNumber) {
        return users.get(accountNumber);
    }

    public boolean userExists(String accNo) {
        return users.containsKey(accNo);
    }
}
