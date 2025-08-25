package kfd.assignment.model;

import kfd.assignment.entity.Book;
import kfd.assignment.entity.BorrowingRecord;
import kfd.assignment.entity.Faculty;
import kfd.assignment.entity.Guest;
import kfd.assignment.entity.Student;
import kfd.assignment.entity.User;
import kfd.assignment.entity.UserType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Library implements LibraryOperations {
    // Use HashMap for fast lookup by key
    private Map<String, Book> books;      // ISBN -> Book
    private Map<String, User> users;      // UserID -> User

    // Use List for ordered collections
    private List<BorrowingRecord> borrowingHistory;

    // Use Set for unique collections
    private Set<String> genres;

    public Library() {
        books = new HashMap<>();           // O(1) book lookup
        users = new HashMap<>();           // O(1) user lookup
        borrowingHistory = new ArrayList<>(); // Chronological order
        genres = new HashSet<>();          // Unique genres only
    }

    @Override
    public void addBook(String title, String author, String isbn, String genre) {
        final var book = new Book(title, author, isbn, genre);
        books.put(isbn, book);
        genres.add(genre);
    }

    @Override
    public boolean removeBook(String isbn) {
        final var book = books.get(isbn);
        if (book == null) {
            return false;
        }

        books.remove(isbn);

        if (books.values().stream().noneMatch(b -> book.getGenre().equals(b.getGenre()))) {
            genres.remove(book.getGenre());
        }

        return true;
    }

    @Override
    public Book findBook(String isbn) {
        return books.get(isbn);
    }

    @Override
    public List<Book> searchBooks(String query) {
        final var args = query.split("=");
        switch (args[0]) {
            case "title" -> {
                return books.values().stream()
                        .filter(b -> b.getTitle().equals(args[1]))
                        .toList();
            }
            case "author" -> {
                return books.values().stream()
                        .filter(b -> b.getAuthor().equals(args[1]))
                        .toList();
            }
            case "isbn" -> {
                return books.values().stream()
                        .filter(b -> b.getIsbn().equals(args[1]))
                        .toList();
            }
            case "genre" -> {
                return books.values().stream()
                        .filter(b -> b.getGenre().equals(args[1]))
                        .toList();
            }
            default -> {
                return List.of();
            }
        }
    }

    @Override
    public void registerUser(String name, String userId, String email, UserType type) {
        switch (type) {
            case STUDENT -> users.put(userId, new Student(name, userId, email));
            case FACULTY -> users.put(userId, new Faculty(name, userId, email));
            case GUEST -> users.put(userId, new Guest(name, userId, email));
        }
    }

    @Override
    public User findUser(String userId) {
        return users.get(userId);
    }

    @Override
    public boolean borrowBook(String userId, String isbn) {
        User user = users.get(userId);     // Fast lookup
        Book book = books.get(isbn);       // Fast lookup

        // Validation logic
        if (user == null || book == null) return false;
        if (!book.isAvailable()) return false;
        if (!user.canBorrow()) return false;

        // Process borrowing
        book.setAvailable(false);
        user.getBorrowedBooks().add(isbn);
        borrowingHistory.add(new BorrowingRecord(user, book, LocalDate.now()));

        return true;
    }

    @Override
    public boolean returnBook(String userId, String isbn) {
        final var user = users.get(userId);
        if (user == null) {
            throw new IllegalArgumentException("User \"" + userId + "\" not found");
        }

        final var book = books.get(isbn);
        if (book == null) {
            throw new IllegalArgumentException("Book \"" + isbn + "\" not found");
        }

        user.getBorrowedBooks().remove(isbn);
        book.setAvailable(true);

        return true;
    }

    @Override
    public List<BorrowingRecord> getOverdueBooks() {
        return borrowingHistory.stream()
                .filter(record -> {
                    final var user = record.getUser();
                    final var borrowDate = record.getTime().plusDays(user.getBorrowDays());
                    return borrowDate.isBefore(LocalDate.now());
                })
                .toList();
    }
}
