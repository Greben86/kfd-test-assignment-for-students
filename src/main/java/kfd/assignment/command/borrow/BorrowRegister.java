package kfd.assignment.command.borrow;

import picocli.CommandLine;

@CommandLine.Command(name = "register", description = "Register borrowing operation")
public class BorrowRegister implements Runnable {

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
        parent.libraryOperations.borrowBook(userId, isbn);
        parent.output.println("success");
    }
}
