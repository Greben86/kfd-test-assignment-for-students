package kfd.assignment.command.borrow;

import kfd.assignment.model.LibraryOperations;
import lombok.RequiredArgsConstructor;
import picocli.CommandLine;

import java.io.PrintStream;

@RequiredArgsConstructor
@CommandLine.Command(name = "borrow", description = "Borrowing Operations",
        subcommands = {BorrowRegister.class, BorrowReturn.class})
public class BorrowCommand implements Runnable {

    protected final LibraryOperations libraryOperations;
    protected final PrintStream output;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    @Override
    public void run() {
        throw new CommandLine.ParameterException(spec.commandLine(), "This command can be use only with subcommand");
    }

    @CommandLine.Command(name = "overdue", description = "Get overdue list")
    public void list() {
        output.println(libraryOperations.getOverdueBooks());
    }
}
