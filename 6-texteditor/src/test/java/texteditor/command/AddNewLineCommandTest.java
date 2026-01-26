package texteditor.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import texteditor.Buffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddNewLineCommandTest {

    private Buffer buffer;
    private Scanner scanner;
    private AddNewLineCommand command;

    @BeforeEach
    public void setUp() {
        buffer = new Buffer();
        List<String> lines = new ArrayList<>();
        lines.add("Line 1");
        lines.add("Line 2");
        buffer.setBuffer(lines);

        scanner = new Scanner(System.in);
        command = new AddNewLineCommand(buffer, scanner);
    }

    @Test
    public void testExecuteAddsLineAtCorrectPosition() {
        command.setLineIndex(1);
        command.setLineContent("New Line");

        command.execute();

        assertEquals(3, buffer.getLineCount());
        assertEquals("New Line", buffer.getLine(1));
        assertEquals("Line 2", buffer.getLine(2));
    }

    @Test
    public void testExecuteAddsLineAtBeginning() {
        command.setLineIndex(0);
        command.setLineContent("First Line");

        command.execute();

        assertEquals(3, buffer.getLineCount());
        assertEquals("First Line", buffer.getLine(0));
        assertEquals("Line 1", buffer.getLine(1));
    }

    @Test
    public void testExecuteAddsLineAtEnd() {
        command.setLineIndex(2);
        command.setLineContent("Last Line");

        command.execute();

        assertEquals(3, buffer.getLineCount());
        assertEquals("Last Line", buffer.getLine(2));
    }

    @Test
    public void testUndoRemovesAddedLine() {
        command.setLineIndex(1);
        command.setLineContent("Temporary Line");

        command.execute();
        assertEquals(3, buffer.getLineCount());

        command.undo();

        assertEquals(2, buffer.getLineCount());
        assertEquals("Line 1", buffer.getLine(0));
        assertEquals("Line 2", buffer.getLine(1));
    }

    @Test
    public void testExecuteAndUndoMultipleTimes() {
        command.setLineIndex(1);
        command.setLineContent("Toggle Line");

        command.execute();
        assertEquals(3, buffer.getLineCount());
        assertEquals("Toggle Line", buffer.getLine(1));

        command.undo();
        assertEquals(2, buffer.getLineCount());

        command.execute();
        assertEquals(3, buffer.getLineCount());
        assertEquals("Toggle Line", buffer.getLine(1));

        command.undo();
        assertEquals(2, buffer.getLineCount());
    }

    @Test
    public void testDuplicateCreatesIndependentCopy() {
        command.setLineIndex(1);
        command.setLineContent("Original Content");

        Command duplicate = command.duplicate();

        assertNotSame(command, duplicate);
        assertTrue(duplicate instanceof AddNewLineCommand);

        duplicate.execute();
        assertEquals(3, buffer.getLineCount());
        assertEquals("Original Content", buffer.getLine(1));
    }

    @Test
    public void testDuplicatePreservesState() {
        command.setLineIndex(1);
        command.setLineContent("Test Content");

        AddNewLineCommand duplicate = (AddNewLineCommand) command.duplicate();
        duplicate.execute();

        assertEquals("Test Content", buffer.getLine(1));

        command.undo();
        assertEquals(2, buffer.getLineCount());
    }

    @Test
    public void testAddLineToEmptyBuffer() {
        Buffer emptyBuffer = new Buffer();
        AddNewLineCommand emptyCommand = new AddNewLineCommand(emptyBuffer, scanner);

        emptyCommand.setLineIndex(0);
        emptyCommand.setLineContent("First ever line");

        emptyCommand.execute();

        assertEquals(1, emptyBuffer.getLineCount());
        assertEquals("First ever line", emptyBuffer.getLine(0));
    }
}