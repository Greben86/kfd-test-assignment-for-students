package kfd.assignment.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private final String genre;
    private boolean available = true;
}
