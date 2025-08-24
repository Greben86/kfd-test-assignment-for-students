package kfd.assignment.command.user;

import kfd.assignment.model.LibraryOperations;
import lombok.RequiredArgsConstructor;
import picocli.CommandLine;

import java.io.PrintStream;

@RequiredArgsConstructor
@CommandLine.Command(name = "user", description = "User Management",
        subcommands = {UserAdd.class, UserFind.class})
public class UserCommand implements Runnable {

    protected final LibraryOperations libraryOperations;
    protected final PrintStream output;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    @Override
    public void run() {
        throw new CommandLine.ParameterException(spec.commandLine(), "This command can be use only with subcommand");
    }
}
