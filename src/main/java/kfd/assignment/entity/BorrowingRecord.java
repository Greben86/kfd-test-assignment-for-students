package kfd.assignment.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class BorrowingRecord {
    private final User user;
    private final Book book;
    private final LocalDate time;
}
