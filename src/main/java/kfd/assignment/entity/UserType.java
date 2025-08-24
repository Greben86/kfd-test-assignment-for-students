package kfd.assignment.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public enum UserType {
    STUDENT("student"),
    FACULTY("faculty"),
    GUEST("guest");

    @Getter
    private final String name;

    private static final Map<String, UserType> commandMap = Stream.of(values())
            .collect(Collectors.toMap(UserType::getName, Function.identity()));

    public static Optional<UserType> getByName(String name) {
        return Optional.ofNullable(commandMap.get(name));
    }
}
