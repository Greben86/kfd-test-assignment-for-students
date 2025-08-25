package kfd.assignment.command.borrow;

import picocli.CommandLine;

@CommandLine.Command(name = "return", description = "Return borrowing operation")
public class BorrowReturn implements Runnable {

    @CommandLine.ParentCommand
    private BorrowCommand parent;

    @CommandLine.Option(
            names = {"-U", "--userId"},
            description = "User id",
            required = true)
    private String userId;

    @CommandLine.Option(
            names = {"-I", "--isbn"},
            description = "Isbn book",
            required = true)
    private String isbn;

    @Override
    public void run() {
        parent.libraryOperations.returnBook(userId, isbn);
        parent.output.println("success");
    }
}
