package kfd.assignment.command.book;

import picocli.CommandLine;

@CommandLine.Command(name = "search", description = "Find book")
public class BookSearch implements Runnable {

    @CommandLine.ParentCommand
    private BookCommand parent;

    @CommandLine.Option(
            names = {"-Q", "--query"},
            description = "Query search",
            required = true)
    private String query;

    @Override
    public void run() {
        parent.output.println(parent.libraryOperations.searchBooks(query));
    }
}
