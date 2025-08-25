package kfd.assignment.command.user;

import picocli.CommandLine;

@CommandLine.Command(name = "find", description = "Find user")
public class UserFind implements Runnable {

    @CommandLine.ParentCommand
    private UserCommand parent;

    @CommandLine.Option(
            names = {"-I", "--userId"},
            description = "User id",
            required = true)
    private String userId;

    @Override
    public void run() {
        parent.output.println(parent.libraryOperations.findUser(userId));
    }
}
