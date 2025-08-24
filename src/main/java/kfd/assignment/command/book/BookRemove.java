package kfd.assignment.command.book;

import picocli.CommandLine;

@CommandLine.Command(name = "remove", description = "Remove book")
public class BookRemove implements Runnable {

    @CommandLine.ParentCommand
    private BookCommand parent;

    @CommandLine.Option(
            names = {"-I", "--isbn"},
            description = "Isbn book",
            required = true)
    private String isbn;

    @Override
    public void run() {
        parent.libraryOperations.removeBook(isbn);
        parent.output.println("removed");
    }
}
