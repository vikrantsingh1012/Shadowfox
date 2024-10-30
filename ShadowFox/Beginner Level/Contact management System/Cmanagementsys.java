import java.util.*;
import java.util.regex.Pattern;

class Contact {
    private String name;
    private List<String> phones;
    private List<String> emails;
    private String category; // e.g., Family, Work, Friends
    private boolean isFavorite;
    private String birthday; // Format: DD-MM-YYYY

    public Contact(String name, List<String> phones, List<String> emails, String category, boolean isFavorite, String birthday) {
        this.name = name;
        this.phones = phones;
        this.emails = emails;
        this.category = category;
        this.isFavorite = isFavorite;
        this.birthday = birthday;
    }

    // Getters and Setters for all fields
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getPhones() { return phones; }
    public void setPhones(List<String> phones) { this.phones = phones; }

    public List<String> getEmails() { return emails; }
    public void setEmails(List<String> emails) { this.emails = emails; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean isFavorite) { this.isFavorite = isFavorite; }

    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }

    @Override
    public String toString() {
        return "Name: " + name + ", Phones: " + phones + ", Emails: " + emails + 
                ", Category: " + category + ", Favorite: " + (isFavorite ? "Yes" : "No") +
                ", Birthday: " + birthday;
    }
}

public class Cmanagementsys {
    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.out.println("\nAdvanced Contact Management System");
            System.out.println("1. Add Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Search Contacts");
            System.out.println("4. Update Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Sort Contacts");
            System.out.println("7. View Favorite Contacts");
            System.out.println("8. Bulk Update/Delete");
            System.out.println("9. View Contacts by Birthday Reminders");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    searchContacts();
                    break;
                case 4:
                    updateContact();
                    break;
                case 5:
                    deleteContact();
                    break;
                case 6:
                    sortContacts();
                    break;
                case 7:
                    viewFavoriteContacts();
                    break;
                case 8:
                    bulkUpdateOrDelete();
                    break;
                case 9:
                    viewBirthdayReminders();
                    break;
                case 10:
                    run = false;
                    System.out.println("Exiting Contact Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to validate phone numbers and emails
    public static boolean validatePhoneNumber(String phone) {
        return phone.matches("\\d{10}");
    }

    public static boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    // Method to add a new contact
    public static void addContact() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        List<String> phones = new ArrayList<>();
        List<String> emails = new ArrayList<>();

        // Collect multiple phone numbers
        while (true) {
            System.out.print("Enter Phone Number (10 digits, type 'done' to finish): ");
            String phone = scanner.nextLine();
            if (phone.equalsIgnoreCase("done")) break;
            if (validatePhoneNumber(phone)) {
                phones.add(phone);
            } else {
                System.out.println("Invalid phone number. Try again.");
            }
        }

        // Collect multiple emails
        while (true) {
            System.out.print("Enter Email (type 'done' to finish): ");
            String email = scanner.nextLine();
            if (email.equalsIgnoreCase("done")) break;
            if (validateEmail(email)) {
                emails.add(email);
            } else {
                System.out.println("Invalid email format. Try again.");
            }
        }

        // Get category
        System.out.print("Enter Category (Family, Work, Friends, etc.): ");
        String category = scanner.nextLine();

        // Favorite status
        System.out.print("Is this a favorite contact? (yes/no): ");
        boolean isFavorite = scanner.nextLine().equalsIgnoreCase("yes");

        // Birthday reminder
        System.out.print("Enter Birthday (DD-MM-YYYY) or leave blank: ");
        String birthday = scanner.nextLine();

        contacts.add(new Contact(name, phones, emails, category, isFavorite, birthday));
        System.out.println("Contact added successfully!");
    }

    // Method to view all contacts
    public static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("\nList of Contacts:");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    // Method to search contacts by name, phone, or email
    public static void searchContacts() {
        System.out.print("Enter search term (name, phone, or email): ");
        String searchTerm = scanner.nextLine();
        boolean found = false;

        for (Contact contact : contacts) {
            if (contact.getName().contains(searchTerm) || contact.getPhones().contains(searchTerm) ||
                    contact.getEmails().contains(searchTerm)) {
                System.out.println(contact);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No contacts found.");
        }
    }

    // Method to update a contact
    public static void updateContact() {
        System.out.print("Enter the name of the contact to update: ");
        String name = scanner.nextLine();

        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.print("Enter new name (leave blank to keep current): ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) contact.setName(newName);

                // Similar updates for phone, email, etc.

                System.out.println("Contact updated successfully!");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    // Method to delete a contact
    public static void deleteContact() {
        System.out.print("Enter the name of the contact to delete: ");
        String name = scanner.nextLine();
        contacts.removeIf(contact -> contact.getName().equalsIgnoreCase(name));
        System.out.println("Contact deleted successfully.");
    }

    // Method to sort contacts by name
    public static void sortContacts() {
        contacts.sort(Comparator.comparing(Contact::getName));
        System.out.println("Contacts sorted by name.");
    }

    // Method to view favorite contacts
    public static void viewFavoriteContacts() {
        System.out.println("Favorite Contacts:");
        for (Contact contact : contacts) {
            if (contact.isFavorite()) {
                System.out.println(contact);
            }
        }
    }

    // Method for bulk update or delete
    public static void bulkUpdateOrDelete() {
        System.out.println("Bulk Update/Delete");
        System.out.println("1. Update all contacts in a category");
        System.out.println("2. Delete all contacts in a category");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            System.out.print("Enter the category to update: ");
            String category = scanner.nextLine();
            for (Contact contact : contacts) {
                if (contact.getCategory().equalsIgnoreCase(category)) {
                    System.out.print("Enter new category for " + contact.getName() + ": ");
                    String newCategory = scanner.nextLine();
                    contact.setCategory(newCategory);
                }
            }
        } else if (choice == 2) {
            System.out.print("Enter the category to delete: ");
            String category = scanner.nextLine();
            contacts.removeIf(contact -> contact.getCategory().equalsIgnoreCase(category));
        }
        System.out.println("Bulk operation completed.");
    }

    // Method to view contacts with upcoming birthdays
    public static void viewBirthdayReminders() {
        System.out.print("Enter month number to view birthdays (1-12): ");
        int month = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Contacts with birthdays in month " + month + ":");
        for (Contact contact : contacts) {
            if (!contact.getBirthday().isEmpty() && contact.getBirthday().substring(3, 5).equals(String.format("%02d", month))) {
                System.out.println(contact);
            }
        }
    }
}
