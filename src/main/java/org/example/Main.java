package org.example;

import org.example.model.Product;
import org.example.service.InventoryManager;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final InventoryManager manager = new InventoryManager();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        showWelcomeScreen();
        int choice;

        do {
            showMenu();
            choice = getChoice();
            sc.nextLine(); // Consume the newline

            switch (choice) {
                case 1: addProduct(); break;
                case 2: manager.viewAllProducts(); break;
                case 3: searchProduct(); break;
                case 4: updateProduct(); break;
                case 5: removeProduct(); break;
                case 6: getReport(); break;
                case 7: System.out.println("👋 Exiting... Thank you!"); break;
                default: System.out.println("⚠️ Invalid choice. Try again.");
            }
        } while (choice != 7);

        sc.close();
    }

    private static void showWelcomeScreen() {
        System.out.println("=========================================");
        System.out.println("  Welcome to Inventory Management System");
        System.out.println("=========================================");
    }

    private static void showMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add a new product");
        System.out.println("2. View all products");
        System.out.println("3. Search for a product");
        System.out.println("4. Update a product");
        System.out.println("5. Remove a product");
        System.out.println("6. Get Inventory Report");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine();
            return -1;
        }
    }

    private static void addProduct() {
        try {
            System.out.print("ID: "); String id = sc.nextLine();
            System.out.print("Name: "); String name = sc.nextLine();
            System.out.print("Price: "); double price = sc.nextDouble();
            System.out.print("Quantity: "); int quantity = sc.nextInt();
            sc.nextLine();
            System.out.print("Manufacturing Date (YYYY-MM-DD): "); LocalDate date = LocalDate.parse(sc.nextLine());
            System.out.print("Supplier: "); String supplier = sc.nextLine();

            Product newProduct = new Product(id, name, price, quantity, date, supplier);
            manager.addProduct(newProduct);
        } catch (InputMismatchException e) {
            System.err.println("❌ Error: Invalid number format. Please enter a valid number for Price and Quantity.");
            sc.nextLine();
        } catch (DateTimeParseException e) {
            System.err.println("❌ Error: Invalid date format. Please use YYYY-MM-DD.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void searchProduct() {
        System.out.print("Enter Product ID to search: ");
        String id = sc.nextLine();
        Optional<Product> result = manager.searchProduct(id);
        if (result.isPresent()) {
            Product p = result.get();
            System.out.println("✅ Found: " + p.getProductName() + " | Price: $" + p.getPrice());
        } else {
            System.out.println("❌ Product not found.");
        }
    }

    private static void updateProduct() {
        try {
            System.out.print("Enter Product ID to update: ");
            String id = sc.nextLine();
            System.out.print("Enter new Price: ");
            double newPrice = sc.nextDouble();
            System.out.print("Enter new Quantity: ");
            int newQuantity = sc.nextInt();
            sc.nextLine();
            if (manager.updateProduct(id, newPrice, newQuantity)) {
                System.out.println("✅ Product updated successfully.");
            } else {
                System.out.println("❌ Product not found. Update failed.");
            }
        } catch (InputMismatchException e) {
            System.err.println("❌ Error: Invalid number format. Please enter a valid number.");
            sc.nextLine();
        }
    }

    private static void removeProduct() {
        System.out.print("Enter Product ID to remove: ");
        String id = sc.nextLine();
        if (manager.removeProduct(id)) {
            System.out.println("✅ Product removed successfully.");
        } else {
            System.out.println("❌ Product not found. Removal failed.");
        }
    }

    private static void getReport() {
        System.out.println("\n--- Inventory Report ---");
        System.out.println("Total number of unique products: " + manager.getTotalProducts());
        System.out.println("Total quantity of all products: " + manager.getTotalQuantity());
        System.out.printf("Total inventory value: $%.2f\n", manager.getTotalValue());
    }
}