
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    private ArrayList<Book> books = new ArrayList<>();
    public static ArrayList<Users> users = new ArrayList<>();
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Library library = new Library();
        library.run();
    }

    public Library() {
        // Sample books
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 1, 3));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction", 2, 2));
        books.add(new Book("1984", "George Orwell", "Dystopian", 3, 4));
    }

    // Main menu loop
    public void run() {
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("Enter 1 to add a user");
            System.out.println("Enter 2 to borrow a book");
            System.out.println("Enter 3 to return a book");
            System.out.println("Enter 4 to search for books");
            System.out.println("Enter 5 to view user by email");
            System.out.println("Enter 6 to view all books");
            System.out.println("Enter 0 to exit");

            // Directly using scanner for input
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1 ->
                    addUserMenu();
                case 2 ->
                    borrowBookMenu();
                case 3 ->
                    returnBookMenu();
                case 4 ->
                    searchBooksMenu();
                case 5 ->
                    viewUserByEmail();
                case 6 ->
                    viewAllBooks();
                case 0 -> {
                    System.out.println("Exiting system...");
                    return;
                }
                default ->
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    // Add a new user
    private void addUserMenu() {
        System.out.println("Enter name:");
        String name = scanner.nextLine().trim();
        System.out.println("Enter email:");
        String email = scanner.nextLine().trim();
        addUser(name, email);

    }

    public void addUser(String name, String email) {
        if (getUserByEmail(email) != null) {
            System.out.println("User with this email already exists.");
            return;
        }
        users.add(new Users(name, email));
        System.out.println("User added successfully.");

        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    // Borrow a book
    private void borrowBookMenu() {
        System.out.println("Enter your email:");
        String email = scanner.nextLine().trim();
        System.out.println("Enter the book ID you want to borrow:");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        borrowBook(email, bookId);
    }

    public void borrowBook(String userEmail, int bookId) {
        Users user = getUserByEmail(userEmail);
        Book book = getBookById(bookId);

        if (user == null) {
            System.out.println("User not found.");
            System.out.println("Press any key to continue...");
            scanner.next();
            return;
        }

        if (book == null) {
            System.out.println("Book not found.");
            System.out.println("Press any key to continue...");
            scanner.next();
            return;
        }

        if (book.number > 0) {
            user.borrowed_books.add(book);
            book.number--;
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("No copies available.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    // Return a book
    private void returnBookMenu() {
        System.out.println("Enter your email:");
        String email = scanner.nextLine().trim();
        System.out.println("Enter the book ID you want to return:");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        returnBook(email, bookId);
    }

    public void returnBook(String userEmail, int bookId) {
        Users user = getUserByEmail(userEmail);
        Book book = getBookById(bookId);

        if (user == null) {
            System.out.println("User not found.");
            System.out.println("Press any key to continue...");
            scanner.next();
            return;
        }

        if (book == null) {
            System.out.println("Book not found.");
            System.out.println("Press any key to continue...");
            scanner.next();
            return;
        }

        if (user.borrowed_books.remove(book)) {
            book.number++;
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("This user did not borrow this book.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    // Search books by title or author
    private void searchBooksMenu() {
        System.out.println("Enter title or author to search:");
        String keyword = scanner.nextLine().trim();
        searchBooks(keyword);
    }

    public void searchBooks(String keyword) {
        boolean found = false;
        keyword = keyword.toLowerCase();
        for (Book book : books) {
            if (book.title.toLowerCase().contains(keyword) || book.author.toLowerCase().contains(keyword) || book.subject.toLowerCase().contains(keyword)) {
                System.out.println("Found Book - ID: " + book.book_id + ", Title: " + book.title + ", Author: " + book.author);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found with the given keyword.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    // View user by email
    private void viewUserByEmail() {
        System.out.println("Enter the user's email to view:");
        String email = scanner.nextLine().trim();
        Users user = getUserByEmail(email);

        if (user != null) {
            System.out.println("User found: ");
            System.out.println("User ID: " + user.user_id + ", Name: " + user.name + ", Email: " + user.Email);
            System.out.println("Books borrowed by user: ");
            if (user.borrowed_books.isEmpty()) {
                System.out.println("No books borrowed.");
            } else {
                for (Book book : user.borrowed_books) {
                    System.out.println("Book ID: " + book.book_id + ", Title: " + book.title + ", Author: " + book.author);
                }
            }
        } else {
            System.out.println("No user found with that email.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
        
    }

    // View all books
    private void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            System.out.println("Press any key to continue...");
            scanner.next();
            return;
        }
        System.out.println("List of Books:");
        for (Book book : books) {
            System.out.println("Book ID: " + book.book_id + ", Title: " + book.title + ", Author: " + book.author + ", Subject: " + book.subject + ", Available Copies: " + book.number);
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    // Get user by email
    private Users getUserByEmail(String email) {
        for (Users user : users) {
            if (user.Email.equals(email)) {
                return user;
            }
        }
        return null;
    }

    // Get book by ID
    private Book getBookById(int bookId) {
        for (Book book : books) {
            if (book.book_id == bookId) {
                return book;
            }
        }
        return null;
    }
}

// Book class
class Book {

    String title;
    String author;
    String subject;
    int book_id;
    int number; // Number of available copies

    Book(String t, String a, String s, int id, int n) {
        title = t;
        author = a;
        subject = s;
        book_id = id;
        number = n;
    }
}

// User class
class Users {

    String name;
    String Email;
    int user_id;
    ArrayList<Book> borrowed_books = new ArrayList<>();

    Users(String n, String e) {
        name = n;
        Email = e;
        user_id = Library.users.size() + 1;
    }
}
