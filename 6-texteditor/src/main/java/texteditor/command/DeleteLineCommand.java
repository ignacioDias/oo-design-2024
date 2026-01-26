package texteditor.command;

import texteditor.Buffer;

import java.util.List;
import java.util.Scanner;

public class DeleteLineCommand implements Command {

    private String lineContent;
    private int lineIndex;
    private final Buffer buffer;
    private final Scanner scanner;

    public DeleteLineCommand(Buffer buffer, Scanner scanner) {
        this.buffer = buffer;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        List<String> text = buffer.getBuffer();
        lineContent = text.remove(lineIndex);
        buffer.setBuffer(text);
    }

    @Override
    public void undo() {
        List<String> text = buffer.getBuffer();
        text.add(lineIndex, lineContent);
        buffer.setBuffer(text);
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
    }
    public void setLineIndex(int lineIndex) {
        this.lineIndex = lineIndex;
    }

    @Override
    public Command duplicate() {
        AddNewLineCommand copy = new AddNewLineCommand(buffer, scanner);
        copy.setLineIndex(lineIndex);
        return copy;
    }
}
