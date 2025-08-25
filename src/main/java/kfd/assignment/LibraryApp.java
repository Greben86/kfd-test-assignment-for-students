package kfd.assignment;

import java.util.concurrent.Callable;

import kfd.assignment.model.CommandProcessor;
import kfd.assignment.model.Library;
import picocli.CommandLine;

@CommandLine.Command
public class LibraryApp implements Callable<Integer> {

    public static void main(String[] args) {
        new CommandLine(new LibraryApp()).execute(args);
    }

    @Override
    public Integer call() {
        return new CommandLine(new CommandProcessor(System.in, System.out, new Library())).execute();
    }
}
