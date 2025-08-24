package kfd.assignment.command.book;

import picocli.CommandLine;

@CommandLine.Command(name = "add", description = "Add new book")
public class BookAdd implements Runnable {

    @CommandLine.ParentCommand
    private BookCommand parent;

    @CommandLine.Option(
            names = {"-T", "--title"},
            description = "Title book",
            required = true)
    private String title;

    @CommandLine.Option(
            names = {"-A", "--author"},
            description = "Author book",
            required = true)
    private String author;

    @CommandLine.Option(
            names = {"-I", "--isbn"},
            description = "Isbn book",
            required = true)
    private String isbn;

    @CommandLine.Option(
            names = {"-G", "--genre"},
            description = "Genre book",
            required = true)
    private String genre;

    @Override
    public void run() {
        parent.libraryOperations.addBook(title, author, isbn, genre);
        parent.output.println("success");
    }
}
