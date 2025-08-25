package kfd.assignment.model;

import kfd.assignment.command.book.BookCommand;
import kfd.assignment.command.borrow.BorrowCommand;
import kfd.assignment.command.user.UserCommand;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import picocli.CommandLine;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Callable;

@CommandLine.Command
@RequiredArgsConstructor
public class CommandProcessor implements Callable<Integer> {

    private final InputStream input;
    private final PrintStream output;
    private final LibraryOperations libraryOperations;

    private boolean isExitOnly = false;

    @Override
    public Integer call() {
        try (var scanner = new Scanner(input)) {
            while (!isExitOnly) {
                process(scanner);
            }

            return 0;
        } catch (Exception e) {
            output.println(e.getMessage());
            return -1;
        }
    }

    private void process(Scanner scanner) {
        if (isExitOnly) {
            return;
        }

        output.println("__________________________________________________");
        output.println("|              Library Management                |");
        output.println("__________________________________________________");
        output.println("|book        | Book Management                   |");
        output.println("| ├-add      | Add book                          |");
        output.println("| ├-find     | Find book                         |");
        output.println("| ├-search   | Search books                      |");
        output.println("| └-remove   | Remove book                       |");
        output.println("|user        | User Management                   |");
        output.println("| ├-find     | Find user                         |");
        output.println("| └-add      | Add new user                      |");
        output.println("|borrow      | Borrowing Operations              |");
        output.println("| ├-register | Borrow book                       |");
        output.println("| ├-return   | Return book                       |");
        output.println("| └-overdue  | Get overdue list                  |");
        output.println("|exit    | Exit from application                 |");
        output.println("__________________________________________________");
        while (!isExitOnly) {
            try {
                Thread.sleep(100);
                output.print("Command: ");
                var line = scanner.nextLine();
                var command = parseCommand(line);
                switch (command.getKey()) {
                    case "book" -> new CommandLine(new BookCommand(libraryOperations, output)).execute(command.getValue());
                    case "user" -> new CommandLine(new UserCommand(libraryOperations, output)).execute(command.getValue());
                    case "borrow" -> new CommandLine(new BorrowCommand(libraryOperations, output)).execute(command.getValue());
                    case "exit" -> isExitOnly = true;
                    case "" -> output.println("Empty command");
                    default -> output.println("Unknow command \"" + command.getKey() + "\"");
                }
            } catch (Exception ex) {
                output.println(ex.getMessage());
            }
        }
    }

    private Pair<String, String[]> parseCommand(String line) {
        if (line == null || line.isBlank()) {
            return Pair.of(StringUtils.EMPTY, null);
        }

        var lineAsArray = line.split(" ");
        var command = lineAsArray[0];
        var args = Arrays.copyOfRange(lineAsArray, 1, lineAsArray.length);
        return Pair.of(command, args);
    }
}
