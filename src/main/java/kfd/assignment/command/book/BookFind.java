package kfd.assignment.command.book;

import picocli.CommandLine;

@CommandLine.Command(name = "find", description = "Find book")
public class BookFind implements Runnable {

    @CommandLine.ParentCommand
    private BookCommand parent;

    @CommandLine.Option(
            names = {"-I", "--isbn"},
            description = "Isbn book",
            required = true)
    private String isbn;

    @Override
    public void run() {
        parent.output.println(parent.libraryOperations.findBook(isbn));
    }
}
