package kfd.assignment.model;

import kfd.assignment.entity.Book;
import kfd.assignment.entity.BorrowingRecord;
import kfd.assignment.entity.User;
import kfd.assignment.entity.UserType;

import java.util.List;

public interface LibraryOperations {
    // Book management
    void addBook(String title, String author, String isbn, String genre);
    boolean removeBook(String isbn);
    Book findBook(String isbn);
    List<Book> searchBooks(String query);

    // User management
    void registerUser(String name, String userId, String email, UserType type);
    User findUser(String userId);

    // Borrowing operations
    boolean borrowBook(String userId, String isbn);
    boolean returnBook(String userId, String isbn);
    List<BorrowingRecord> getOverdueBooks();
}
