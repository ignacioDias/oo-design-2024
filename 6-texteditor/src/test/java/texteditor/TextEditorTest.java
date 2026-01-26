package texteditor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import texteditor.command.AddNewLineCommand;
import texteditor.command.Command;
import texteditor.command.DeleteLineCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextEditorTest {

    private TextEditor editor;
    private Buffer buffer;
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        editor = new TextEditor();
        buffer = new Buffer();
        scanner = new Scanner(System.in);

        List<String> lines = new ArrayList<>();
        lines.add("Line 1");
        lines.add("Line 2");
        buffer.setBuffer(lines);
    }

    @Test
    public void testAddCommand() {
        Command command = new AddNewLineCommand(buffer, scanner);

        editor.addCommand(command);

        assertEquals(1, editor.getCommands().size());
        assertSame(command, editor.getCommands().get(0));
    }

    @Test
    public void testAddMultipleCommands() {
        Command command1 = new AddNewLineCommand(buffer, scanner);
        Command command2 = new DeleteLineCommand(buffer, scanner);

        editor.addCommand(command1);
        editor.addCommand(command2);

        assertEquals(2, editor.getCommands().size());
        assertSame(command1, editor.getCommands().get(0));
        assertSame(command2, editor.getCommands().get(1));
    }

    @Test
    public void testExecuteCommand() {
        Scanner mockScanner = new Scanner("1\nNew Line\n");
        AddNewLineCommand command = new AddNewLineCommand(buffer, mockScanner);

        editor.addCommand(command);

        editor.executeCommand(0);

        assertEquals(3, buffer.getLineCount());
        assertEquals("New Line", buffer.getLine(1));
    }

    @Test
    public void testExecuteCommandWithMockedInput() {
        Scanner mockScanner = new Scanner("1\nTest Line\n");
        AddNewLineCommand command = new AddNewLineCommand(buffer, mockScanner);

        editor.addCommand(command);
        editor.executeCommand(0);

        assertEquals(3, buffer.getLineCount());
        assertEquals("Test Line", buffer.getLine(1));
    }

    @Test
    public void testExecuteCommandThrowsExceptionForInvalidSlot() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            editor.executeCommand(5);
        });
    }

    @Test
    public void testUndoAfterOneCommand() {
        Scanner mockScanner = new Scanner("1\nTemporary\n");
        AddNewLineCommand command = new AddNewLineCommand(buffer, mockScanner);

        editor.addCommand(command);
        editor.executeCommand(0);

        assertEquals(3, buffer.getLineCount());

        editor.undoButtonWasPushed();

        assertEquals(2, buffer.getLineCount());
        assertEquals("Line 1", buffer.getLine(0));
        assertEquals("Line 2", buffer.getLine(1));
    }

    @Test
    public void testUndoWithEmptyStackDoesNothing() {
        int initialLineCount = buffer.getLineCount();

        editor.undoButtonWasPushed();

        assertEquals(initialLineCount, buffer.getLineCount());
    }

    @Test
    public void testRedoAfterUndo() {
        Scanner mockScanner = new Scanner("1\nTest Line\n");
        AddNewLineCommand command = new AddNewLineCommand(buffer, mockScanner);

        editor.addCommand(command);
        editor.executeCommand(0);
        editor.undoButtonWasPushed();

        assertEquals(2, buffer.getLineCount());

        editor.redoButtonWasPushed();

        assertEquals(3, buffer.getLineCount());
        assertEquals("Test Line", buffer.getLine(1));
    }

    @Test
    public void testRedoWithEmptyStackDoesNothing() {
        int initialLineCount = buffer.getLineCount();

        editor.redoButtonWasPushed();

        assertEquals(initialLineCount, buffer.getLineCount());
    }

    @Test
    public void testMultipleUndosAndRedos() {
        Scanner mockScanner1 = new Scanner("1\nFirst\n");
        AddNewLineCommand command1 = new AddNewLineCommand(buffer, mockScanner1);

        Scanner mockScanner2 = new Scanner("2\nSecond\n");
        AddNewLineCommand command2 = new AddNewLineCommand(buffer, mockScanner2);

        editor.addCommand(command1);
        editor.addCommand(command2);

        editor.executeCommand(0);
        assertEquals(3, buffer.getLineCount());

        editor.executeCommand(1);
        assertEquals(4, buffer.getLineCount());

        editor.undoButtonWasPushed();
        assertEquals(3, buffer.getLineCount());

        editor.undoButtonWasPushed();
        assertEquals(2, buffer.getLineCount());

        editor.redoButtonWasPushed();
        assertEquals(3, buffer.getLineCount());
        assertEquals("First", buffer.getLine(1));

        editor.redoButtonWasPushed();
        assertEquals(4, buffer.getLineCount());
        assertEquals("Second", buffer.getLine(2));
    }

    @Test
    public void testNewCommandClearsRedoStack() {
        Scanner mockScanner1 = new Scanner("1\nFirst\n");
        AddNewLineCommand command1 = new AddNewLineCommand(buffer, mockScanner1);

        Scanner mockScanner2 = new Scanner("1\nSecond\n");
        AddNewLineCommand command2 = new AddNewLineCommand(buffer, mockScanner2);

        editor.addCommand(command1);
        editor.addCommand(command2);

        editor.executeCommand(0);
        editor.undoButtonWasPushed();

        editor.executeCommand(1);

        editor.redoButtonWasPushed();

        assertEquals(3, buffer.getLineCount());
        assertEquals("Second", buffer.getLine(1));
    }

    @Test
    public void testToStringShowsEditorState() {
        AddNewLineCommand command = new AddNewLineCommand(buffer, scanner);
        editor.addCommand(command);

        String result = editor.toString();

        assertTrue(result.contains("TextEditor State"));
        assertTrue(result.contains("Available Commands: 1"));
        assertTrue(result.contains("AddNewLineCommand"));
    }

    @Test
    public void testCommandIsDuplicatedBeforeStoring() {
        Scanner mockScanner = new Scanner("1\nTest\n");
        AddNewLineCommand command = new AddNewLineCommand(buffer, mockScanner);

        editor.addCommand(command);
        editor.executeCommand(0);

        command.setLineContent("Modified");
        command.setLineIndex(0);
        editor.undoButtonWasPushed();
        assertEquals(2, buffer.getLineCount());
    }
}