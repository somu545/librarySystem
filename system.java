import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean available;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Patron {
    private int patronId;
    private String name;
    private List<Book> borrowedBooks;

    public Patron(int patronId, String name) {
        this.patronId = patronId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getPatronId() {
        return patronId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
            System.out.println(name + " has borrowed '" + book.getTitle() + "'.");
        } else {
            System.out.println("Sorry, the book '" + book.getTitle() + "' is not available for borrowing.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.setAvailable(true);
            System.out.println(name + " has returned '" + book.getTitle() + "'.");
        } else {
            System.out.println("Error: This book is not borrowed by " + name + ".");
        }
    }
}

class Library {
    private List<Book> bookInventory;
    private List<Patron> patrons;

    public Library() {
        this.bookInventory = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }

    public void addBook(Book book) {
        bookInventory.add(book);
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public void displayBooks() {
        System.out.println("Library Inventory:");
        for (Book book : bookInventory) {
            System.out.println("ID: " + book.getBookId() + ", Title: " + book.getTitle() +
                    ", Author: " + book.getAuthor() + ", Available: " + (book.isAvailable() ? "Yes" : "No"));
        }
        System.out.println();
    }
}

public class system {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book(1, "Introduction to Java", "John Doe");
        Book book2 = new Book(2, "Data Structures and Algorithms Made Easy", "Narasimha Karumanchi");

        library.addBook(book1);
        library.addBook(book2);

        Patron patron1 = new Patron(101, "Rahul");
        Patron patron2 = new Patron(102, "Aniket");

        library.addPatron(patron1);
        library.addPatron(patron2);

        library.displayBooks();

        patron1.borrowBook(book1);
        patron2.borrowBook(book2);

        library.displayBooks();

        patron1.returnBook(book1);
        patron2.returnBook(book2);

        library.displayBooks();
    }
}
