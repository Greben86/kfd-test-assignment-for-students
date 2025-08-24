package kfd.assignment.command.user;

import kfd.assignment.entity.UserType;
import picocli.CommandLine;

public class UserTypeConverter implements CommandLine.ITypeConverter<UserType> {
    @Override
    public UserType convert(String value) {
        return UserType.getByName(value)
                .orElseThrow(() -> new IllegalArgumentException("Unknown user type \"" + value + "\""));
    }
}
