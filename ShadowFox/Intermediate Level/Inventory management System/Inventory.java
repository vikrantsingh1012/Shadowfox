import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Inventory extends JFrame {
    private ArrayList<InventoryItem> inventory;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nameField, quantityField, priceField, searchField;
    private int selectedRow = -1;

    // Simple user storage (for demonstration purposes)
    private HashMap<String, String> userDatabase = new HashMap<>();

    // Inner class to represent an inventory item
    static class InventoryItem {
        String name;
        int quantity;
        double price;

        public InventoryItem(String name, int quantity, double price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }
    }

    public Inventory() {
        inventory = new ArrayList<>();
        setTitle("Inventory Management System");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Show sign-up screen initially
        showSignUpScreen();
    }

    private void showSignUpScreen() {
        JPanel signUpPanel = new JPanel(new GridLayout(4, 2));
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton signUpButton = new JButton("Sign Up");
        JButton loginButton = new JButton("Already have an account? Login");

        signUpPanel.add(usernameLabel);
        signUpPanel.add(usernameField);
        signUpPanel.add(passwordLabel);
        signUpPanel.add(passwordField);
        signUpPanel.add(signUpButton);
        signUpPanel.add(loginButton);

        signUpButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (userDatabase.containsKey(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                userDatabase.put(username, password); // Store user in the map
                JOptionPane.showMessageDialog(this, "Sign-up successful! You can now log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        loginButton.addActionListener(e -> showLoginScreen());

        add(signUpPanel);
        setVisible(true);
    }

    private void showLoginScreen() {
        getContentPane().removeAll(); // Remove sign-up panel
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
                getContentPane().removeAll(); // Remove login panel
                initializeGUI(); // Show main application
                revalidate();
                repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(loginPanel);
        setVisible(true);
    }

    private void initializeGUI() {
        // Set up the GUI
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create the table
        String[] columnNames = {"Item Name", "Quantity", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                nameField.setText((String) tableModel.getValueAt(selectedRow, 0));
                quantityField.setText(String.valueOf(tableModel.getValueAt(selectedRow, 1)));
                priceField.setText(String.valueOf(tableModel.getValueAt(selectedRow, 2)));
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Item Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);
        panel.add(inputPanel, BorderLayout.NORTH);

        // Search functionality
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search:"));
        searchField = new JTextField(15);
        searchPanel.add(searchField);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchButton);
        panel.add(searchPanel, BorderLayout.SOUTH);

        // Create buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Item");
        JButton updateButton = new JButton("Update Item");
        JButton deleteButton = new JButton("Delete Item");
        JButton feedbackButton = new JButton("Feedback");

        addButton.addActionListener(e -> addItem());
        updateButton.addActionListener(e -> updateItem());
        deleteButton.addActionListener(e -> deleteItem());
        feedbackButton.addActionListener(e -> submitFeedback());

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(feedbackButton);
        panel.add(buttonPanel, BorderLayout.WEST);

        searchButton.addActionListener(e -> searchItem());

        add(panel);
        setVisible(true);
    }

    private void addItem() {
        String name = nameField.getText();
        if (!validateInputs(name)) return; // Validate input before adding
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());

        InventoryItem item = new InventoryItem(name, quantity, price);
        inventory.add(item);
        tableModel.addRow(new Object[]{item.name, item.quantity, item.price});
        checkStockAlert(item); // Check stock alert
        clearFields();
    }

    private void updateItem() {
        if (selectedRow != -1) {
            String name = nameField.getText();
            if (!validateInputs(name)) return; // Validate input before updating
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());

            inventory.get(selectedRow).name = name;
            inventory.get(selectedRow).quantity = quantity;
            inventory.get(selectedRow).price = price;
            tableModel.setValueAt(name, selectedRow, 0);
            tableModel.setValueAt(quantity, selectedRow, 1);
            tableModel.setValueAt(price, selectedRow, 2);
            checkStockAlert(inventory.get(selectedRow)); // Check stock alert
            clearFields();
        }
    }

    private void deleteItem() {
        if (selectedRow != -1) {
            inventory.remove(selectedRow);
            tableModel.removeRow(selectedRow);
            clearFields();
            selectedRow = -1;
        }
    }

    private void searchItem() {
        String searchTerm = searchField.getText().toLowerCase();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String itemName = tableModel.getValueAt(i, 0).toString().toLowerCase();
            if (itemName.contains(searchTerm)) {
                table.setRowSelectionInterval(i, i); // Highlight found item
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Item not found", "Search", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean validateInputs(String name) {
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Item name cannot be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            if (quantity < 0 || price < 0) {
                JOptionPane.showMessageDialog(this, "Quantity and price must be positive", "Input Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantity and price must be valid numbers", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void checkStockAlert(InventoryItem item) {
        if (item.quantity < 5) {
            JOptionPane.showMessageDialog(this, "Warning: Low stock for " + item.name, "Stock Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        quantityField.setText("");
        priceField.setText("");
        selectedRow = -1;
        table.clearSelection();
    }

    private void submitFeedback() {
        String feedback = JOptionPane.showInputDialog(this, "Please provide your feedback:");
        if (feedback != null && !feedback.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thank you for your feedback!", "Feedback Submitted", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Inventory::new);
    }
}
