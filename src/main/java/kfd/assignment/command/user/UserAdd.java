package kfd.assignment.command.user;

import kfd.assignment.entity.UserType;
import picocli.CommandLine;

@CommandLine.Command(name = "add", description = "Add new user")
public class UserAdd implements Runnable {

    @CommandLine.ParentCommand
    private UserCommand parent;

    @CommandLine.Option(
            names = {"-N", "--name"},
            description = "User name",
            required = true)
    private String name;

    @CommandLine.Option(
            names = {"-I", "--userId"},
            description = "User id",
            required = true)
    private String userId;

    @CommandLine.Option(
            names = {"-E", "--email"},
            description = "User email",
            required = true)
    private String email;

    @CommandLine.Option(
            names = {"-T", "--type"},
            description = "User type",
            converter = UserTypeConverter.class,
            required = true)
    private UserType type;

    @Override
    public void run() {
        parent.libraryOperations.registerUser(name, userId, email, type);
        parent.output.println("success");
    }
}
