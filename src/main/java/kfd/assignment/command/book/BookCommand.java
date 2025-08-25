package kfd.assignment.command.book;

import kfd.assignment.model.LibraryOperations;
import lombok.RequiredArgsConstructor;
import picocli.CommandLine;

import java.io.PrintStream;

@RequiredArgsConstructor
@CommandLine.Command(name = "book", description = "Book Management",
        subcommands = {BookAdd.class, BookFind.class, BookRemove.class, BookSearch.class})
public class BookCommand implements Runnable {

    protected final LibraryOperations libraryOperations;
    protected final PrintStream output;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    @Override
    public void run() {
        throw new CommandLine.ParameterException(spec.commandLine(), "This command can be use only with subcommand");
    }
}
