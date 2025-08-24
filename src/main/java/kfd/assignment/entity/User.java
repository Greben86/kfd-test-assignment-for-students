package kfd.assignment.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
public abstract class User {
    private final String name;
    private final String userId;
    private final String email;
    private final UserType userType;
    private final List<String> borrowedBooks = new ArrayList<>();

    // Each user type implements these differently
    public abstract int getMaxBooks();
    public abstract int getBorrowDays();
    public abstract double getFinePerDay();

    public boolean canBorrow() {
        return borrowedBooks.size() < getMaxBooks();
    }
}
