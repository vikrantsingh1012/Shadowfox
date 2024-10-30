import java.util.*;

public class Bankacc {
    // Inner class representing the Bank Account
    static class BankAccount {
        private final String accountNumber; // Marked as final
        private final String password; // Marked as final
        private double balance;
        private final double overdraftLimit; // Marked as final
        private final List<String> transactionHistory; // Marked as final
        private boolean isLocked;
        private static final double INTEREST_RATE = 0.02;

        public BankAccount(String accountNumber, String password, double initialBalance, double overdraftLimit) {
            this.accountNumber = accountNumber;
            this.password = password;
            this.balance = initialBalance;
            this.overdraftLimit = overdraftLimit;
            this.transactionHistory = new ArrayList<>();
            this.isLocked = false;
            transactionHistory.add("Account created with balance: " + initialBalance);
        }

        public boolean authenticate(String inputPassword) {
            return this.password.equals(inputPassword);
        }

        public void lockAccount() {
            isLocked = true;
        }

        public void unlockAccount() {
            isLocked = false;
        }

        public boolean isLocked() {
            return isLocked;
        }

        // Method to deposit amount
        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                transactionHistory.add("Deposited: " + amount);
            } else {
                throw new IllegalArgumentException("Deposit amount must be positive.");
            }
        }

        // Method to withdraw amount with overdraft protection
        public void withdraw(double amount) {
            if (amount > 0 && amount <= (balance + overdraftLimit)) {
                balance -= amount;
                transactionHistory.add("Withdrew: " + amount);
            } else if (amount > (balance + overdraftLimit)) {
                throw new IllegalArgumentException("Insufficient funds including overdraft.");
            } else {
                throw new IllegalArgumentException("Withdraw amount must be positive.");
            }
        }

        // Method to calculate interest on current balance
        public void applyInterest() {
            double interest = balance * INTEREST_RATE;
            balance += interest;
            transactionHistory.add("Interest applied: " + interest);
        }

        // Method to reverse last transaction
        public void rollbackLastTransaction() {
            if (!transactionHistory.isEmpty()) {
                String lastTransaction = transactionHistory.remove(transactionHistory.size() - 1);
                if (lastTransaction.startsWith("Deposited: ")) {
                    double amount = Double.parseDouble(lastTransaction.split(": ")[1]);
                    balance -= amount;
                } else if (lastTransaction.startsWith("Withdrew: ")) {
                    double amount = Double.parseDouble(lastTransaction.split(": ")[1]);
                    balance += amount;
                }
                transactionHistory.add("Last transaction rolled back.");
            }
        }

        // Method to check for fraud (simple detection based on large withdrawals)
        public boolean detectFraud(double amount) {
            return amount > 5000; // Example threshold for fraud detection
        }

        // Method to get the current balance
        public double getBalance() {
            return balance;
        }

        // Method to get transaction history
        public List<String> getTransactionHistory() {
            return transactionHistory;
        }
    }

    // Text-based UI
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, BankAccount> accounts = new HashMap<>();

        System.out.println("Welcome to the Bank Account Management System");

        while (true) {
            System.out.println("\n1. Create Account\n2. Log In\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    // Create new account
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter initial deposit: ");
                    double initialDeposit = scanner.nextDouble();
                    System.out.print("Enter overdraft limit: ");
                    double overdraftLimit = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    accounts.put(accountNumber, new BankAccount(accountNumber, password, initialDeposit, overdraftLimit));
                    System.out.println("Account created successfully.");
                }
                case 2 -> {
                    // Log in
                    System.out.print("Enter account number: ");
                    String loginAccountNumber = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();

                    BankAccount account = accounts.get(loginAccountNumber);
                    if (account != null && account.authenticate(loginPassword)) {
                        System.out.println("Logged in successfully.");

                        if (account.isLocked()) {
                            System.out.println("Your account is locked due to security reasons.");
                            break;
                        }

                        boolean loggedIn = true;
                        while (loggedIn) {
                            System.out.println("\n1. Deposit\n2. Withdraw\n3. View Balance\n4. View Transaction History");
                            System.out.println("5. Apply Interest\n6. Rollback Last Transaction\n7. Log Out");
                            int action = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            switch (action) {
                                case 1 -> { // Deposit
                                    System.out.print("Enter amount to deposit: ");
                                    double depositAmount = scanner.nextDouble();
                                    account.deposit(depositAmount);
                                    System.out.println("Deposit successful.");
                                }
                                case 2 -> { // Withdraw
                                    System.out.print("Enter amount to withdraw: ");
                                    double withdrawAmount = scanner.nextDouble();
                                    if (account.detectFraud(withdrawAmount)) {
                                        System.out.println("Fraud detected! Transaction aborted.");
                                        account.lockAccount();
                                        break;
                                    }
                                    try {
                                        account.withdraw(withdrawAmount);
                                        System.out.println("Withdrawal successful.");
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 3 -> { // View balance
                                    System.out.println("Current balance: " + account.getBalance());
                                }
                                case 4 -> { // View transaction history
                                    System.out.println("Transaction History:");
                                    for (String transaction : account.getTransactionHistory()) {
                                        System.out.println(transaction);
                                    }
                                }
                                case 5 -> { // Apply interest
                                    account.applyInterest();
                                    System.out.println("Interest applied.");
                                }
                                case 6 -> { // Rollback last transaction
                                    account.rollbackLastTransaction();
                                    System.out.println("Last transaction rolled back.");
                                }
                                case 7 -> { // Log out
                                    loggedIn = false;
                                    System.out.println("Logged out.");
                                }
                            }
                        }
                    } else {
                        System.out.println("Invalid account number or password.");
                    }
                }
                case 3 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
