package texteditor.command;

import texteditor.Buffer;

import java.util.Scanner;

public class PrintBufferCommand implements Command {

    private final Buffer buffer;
    private final Scanner scanner;

    public PrintBufferCommand(Buffer buffer, Scanner scanner) {
        this.buffer = buffer;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println(buffer.toString());
    }

    @Override
    public void undo() {

    }

    @Override
    public void initialize() {
    }

    @Override
    public Command duplicate() {
        return new PrintBufferCommand(buffer, scanner);
    }
}