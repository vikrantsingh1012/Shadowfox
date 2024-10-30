
# Java Projects Repository

Welcome to the Java Projects repository! This collection features a series of Java applications designed to showcase a range of programming skills, from foundational concepts to advanced programming techniques. Each project represents a hands-on application of Java principles, demonstrating essential features and best practices in software development.

## Contents
1. [Console-Based Calculator](#1-console-based-calculator)
2. [Contact Management System](#2-contact-management-system)
3. [Banking System with Unit Testing](#3-banking-system-with-unit-testing)
4. [Inventory Management GUI](#4-inventory-management-gui)
5. [Setup Instructions](#setup-instructions)
6. [Future Development Ideas](#future-development-ideas)

---

## 1. Console-Based Calculator

This project features a console application that serves as a calculator, offering a variety of mathematical operations along with scientific functionalities.

### Key Features:
- **Arithmetic Operations**: Perform addition, subtraction, multiplication, and division.
- **Scientific Functions**: Calculate square roots and perform exponentiation.
- **Unit Conversions**: Convert temperatures between Celsius and Fahrenheit, as well as basic currency conversions.

### Learning Outcomes:
- Grasping fundamental Java syntax and control structures.
- Creating methods for modular functionality.
- Implementing error handling for robust user input management.

---

## 2. Contact Management System

This command-line application allows users to efficiently manage their contacts, providing an easy way to add, view, modify, and delete contacts without the need for a database.

### Key Features:
- **Contact Creation**: Add new contacts with name, phone number, and email.
- **Contact Display**: View all saved contacts in a user-friendly format.
- **Update and Delete**: Modify existing contact details or remove them as needed.

### Learning Outcomes:
- Implementing CRUD (Create, Read, Update, Delete) operations.
- Utilizing Java collections (e.g., ArrayList) for dynamic data management.
- Managing user input and output effectively in a console application.

---

## 3. Banking System with Unit Testing

This project simulates a banking application where users can handle their accounts, process transactions, and maintain a record of their financial activities.

### Key Features:
- **Deposit and Withdraw**: Users can add funds to their account or withdraw cash.
- **Balance Check**: View the current balance of the account.
- **Transaction Logging**: Keep a history of all transactions for user reference.

### Learning Outcomes:
- Applying object-oriented programming concepts (classes, methods).
- Writing unit tests with JUnit to ensure functionality correctness.
- Implementing error handling for common banking scenarios (e.g., overdraft protection).

### Example Code:
```java
Bank account = new Bank("Jane Smith");
account.deposit(150.0);
account.withdraw(75.0);
System.out.println("Balance: $" + account.getBalance());
```

---

## 4. Inventory Management GUI

This project provides a graphical user interface (GUI) for managing inventory items, allowing users to interact with the application more intuitively.

### Key Features:
- **Add New Items**: Enter item details like name, quantity, and price.
- **Modify Existing Items**: Update item details as necessary.
- **Remove Items**: Delete items from the inventory that are no longer in stock.

### Learning Outcomes:
- Designing user-friendly interfaces using JavaFX or Swing.
- Implementing event-driven programming to handle user interactions.
- Connecting GUI components with underlying data management logic.

### Example Code:
```java
// Example of adding an item using the GUI
inventoryManager.addItem(new InventoryItem("Gadget", 30, 29.99));
```

---

## Setup Instructions

To get started with the projects, follow these steps:

1. **Clone the Repository**:
   ```bash
   git clone YOUR_GITHUB_REPO_URL
   ```

2. **Navigate to the Project Directory**:
   ```bash
   cd path/to/your/project
   ```

3. **Compile and Run the Java Files**:
   ```bash
   javac *.java
   java MainClassName
   ```
   Replace `MainClassName` with the name of the main class you wish to execute (e.g., `Calculator`, `ContactManager`, `Bank`, or `Inventory`).

---

## Future Development Ideas

- **Calculator**: Explore implementing features like graphing, support for complex numbers, or an enhanced user interface.
- **Contact Management System**: Consider adding persistent storage using a database, search functionalities, or tagging for better organization.
- **Banking System**: Future enhancements could include loan features, interest rate calculations, and user authentication for enhanced security.
- **Inventory Management**: Additional functionalities might involve reporting tools, integration with e-commerce platforms, or supplier management features.

