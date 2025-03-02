import java.util.*;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrow() {
        this.isBorrowed = true;
    }

    public void returnBook() {
        this.isBorrowed = false;
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Author: %s, ISBN: %s, Borrowed: %s",
                title, author, isbn, isBorrowed ? "Yes" : "No");
    }
}

class UserManager {
    private Map<String, String> users = new HashMap<>();
    private boolean isLoggedIn = false;

    public UserManager() {
        // Predefined user for demonstration
        users.put("admin", "admin123");
    }

    public boolean login(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            isLoggedIn = true;
            return true;
        }
        return false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}

public class LibraryManagementSystem {
    private ArrayList<Book> books = new ArrayList<>();
    private UserManager userManager = new UserManager();

    public void addBook(String title, String author, String isbn) {
        books.add(new Book(title, author, isbn));
        System.out.println("Book added successfully!");
    }

    public void updateBook(String isbn, String newTitle, String newAuthor) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                books.remove(book);
                books.add(new Book(newTitle, newAuthor, isbn));
                System.out.println("Book updated successfully!");
                return;
            }
        }
        System.out.println("Book not found!");
    }

    public void deleteBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        System.out.println("Book deleted successfully!");
    }

    public void searchBooks(String query) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(query) || 
                book.getAuthor().equalsIgnoreCase(query) || 
                book.getIsbn().equals(query)) {
                System.out.println(book);
                return;
            }
        }
        System.out.println("No book found with the given query!");
    }

    public void borrowBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (!book.isBorrowed()) {
                    book.borrow();
                    System.out.println("Book borrowed successfully!");
                } else {
                    System.out.println("Book is already borrowed!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }

    public void returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.isBorrowed()) {
                    book.returnBook();
                    System.out.println("Book returned successfully!");
                } else {
                    System.out.println("Book was not borrowed!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        // Login process
        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (library.userManager.login(username, password)) {
                System.out.println("Login successful!");
                break; // Exit the loop if login is successful
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        // Main menu
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Search Book");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Display All Books");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    library.addBook(title, author, isbn);
                    break;
                case 2:
                    System.out.print("Enter ISBN of the book to update: ");
                    String updateIsbn = scanner.nextLine();
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new author: ");
                    String newAuthor = scanner.nextLine();
                    library.updateBook(updateIsbn, newTitle, newAuthor);
                    break;
                case 3:
                    System.out.print("Enter ISBN of the book to delete: ");
                    String deleteIsbn = scanner.nextLine();
                    library.deleteBook(deleteIsbn);
                    break;
                case 4:
                    System.out.print("Enter title, author, or ISBN to search: ");
                    String query = scanner.nextLine();
                    library.searchBooks(query);
                    break;
                case 5:
                    System.out.print("Enter ISBN of the book to borrow: ");
                    String borrowIsbn = scanner.nextLine();
                    library.borrowBook(borrowIsbn);
                    break;
                case 6:
                    System.out.print("Enter ISBN of the book to return: ");
                    String returnIsbn = scanner.nextLine();
                    library.returnBook(returnIsbn);
                    break;
                case 7:
                    library.displayBooks();
                    break;
                case 8:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}