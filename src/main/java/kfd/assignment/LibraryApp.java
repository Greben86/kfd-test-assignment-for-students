package kfd.assignment;

import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command
public class LibraryApp implements Callable<Integer> {

    public static void main(String[] args) {
        new CommandLine(new LibraryApp()).execute(args);
    }

    @Override
    public Integer call() throws Exception {
        return 0;
    }
}
