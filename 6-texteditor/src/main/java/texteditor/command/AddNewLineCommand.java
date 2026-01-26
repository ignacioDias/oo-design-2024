package texteditor.command;

import texteditor.Buffer;

import java.util.Scanner;

public class AddNewLineCommand implements Command {

    private String lineContent;
    private int lineIndex;
    private final Buffer buffer;
    private final Scanner scanner;

    public AddNewLineCommand(Buffer buffer, Scanner scanner) {
        this.buffer = buffer;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        buffer.insertLine(lineIndex, lineContent);
    }

    @Override
    public void undo() {
        buffer.removeLine(lineIndex);
    }

    @Override
    public void initialize() {
        System.out.print("Enter new line index (0-" + buffer.getLineCount() + "): ");
        lineIndex = scanner.nextInt();
        scanner.nextLine();

        // Validate
        if (lineIndex < 0 || lineIndex > buffer.getLineCount()) {
            throw new IllegalArgumentException("Invalid line index");
        }

        System.out.print("Enter new line content: ");
        lineContent = scanner.nextLine();
    }

    public void setLineContent(String lineContent) {
        this.lineContent = lineContent;
    }

    public void setLineIndex(int lineIndex) {
        this.lineIndex = lineIndex;
    }

    @Override
    public Command duplicate() {
        AddNewLineCommand copy = new AddNewLineCommand(buffer, scanner);
        copy.setLineContent(lineContent);
        copy.setLineIndex(lineIndex);
        return copy;
    }
}
