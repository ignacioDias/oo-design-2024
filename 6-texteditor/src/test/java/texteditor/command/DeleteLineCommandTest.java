package texteditor.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import texteditor.Buffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeleteLineCommandTest {

    private Buffer buffer;
    private Scanner scanner;
    private DeleteLineCommand command;

    @BeforeEach
    public void setUp() {
        buffer = new Buffer();
        List<String> lines = new ArrayList<>();
        lines.add("Line 1");
        lines.add("Line 2");
        lines.add("Line 3");
        buffer.setBuffer(lines);

        scanner = new Scanner(System.in);
        command = new DeleteLineCommand(buffer, scanner);
    }

    @Test
    public void testExecuteDeletesLineAtIndex() {
        command.setLineIndex(1);

        command.execute();

        assertEquals(2, buffer.getLineCount());
        assertEquals("Line 1", buffer.getLine(0));
        assertEquals("Line 3", buffer.getLine(1));
    }

    @Test
    public void testExecuteDeletesFirstLine() {
        command.setLineIndex(0);

        command.execute();

        assertEquals(2, buffer.getLineCount());
        assertEquals("Line 2", buffer.getLine(0));
        assertEquals("Line 3", buffer.getLine(1));
    }

    @Test
    public void testExecuteDeletesLastLine() {
        command.setLineIndex(2);

        command.execute();

        assertEquals(2, buffer.getLineCount());
        assertEquals("Line 1", buffer.getLine(0));
        assertEquals("Line 2", buffer.getLine(1));
    }

    @Test
    public void testUndoRestoresDeletedLine() {
        command.setLineIndex(1);

        command.execute();
        assertEquals(2, buffer.getLineCount());

        command.undo();

        assertEquals(3, buffer.getLineCount());
        assertEquals("Line 1", buffer.getLine(0));
        assertEquals("Line 2", buffer.getLine(1));
        assertEquals("Line 3", buffer.getLine(2));
    }

    @Test
    public void testUndoRestoresLineAtCorrectPosition() {
        command.setLineIndex(0);

        command.execute();
        assertEquals("Line 2", buffer.getLine(0));

        command.undo();

        assertEquals("Line 1", buffer.getLine(0));
        assertEquals("Line 2", buffer.getLine(1));
        assertEquals("Line 3", buffer.getLine(2));
    }

    @Test
    public void testExecuteAndUndoMultipleTimes() {
        command.setLineIndex(1);

        command.execute();
        assertEquals(2, buffer.getLineCount());

        command.undo();
        assertEquals(3, buffer.getLineCount());
        assertEquals("Line 2", buffer.getLine(1));

        command.execute();
        assertEquals(2, buffer.getLineCount());

        command.undo();
        assertEquals(3, buffer.getLineCount());
    }

    @Test
    public void testDeleteOnlyLineInBuffer() {
        Buffer singleLineBuffer = new Buffer();
        List<String> lines = new ArrayList<>();
        lines.add("Only line");
        singleLineBuffer.setBuffer(lines);

        DeleteLineCommand singleCommand = new DeleteLineCommand(singleLineBuffer, scanner);
        singleCommand.setLineIndex(0);

        singleCommand.execute();

        assertTrue(singleLineBuffer.isEmpty());

        singleCommand.undo();

        assertEquals(1, singleLineBuffer.getLineCount());
        assertEquals("Only line", singleLineBuffer.getLine(0));
    }

    @Test
    public void testDeleteAndUndoPreservesContent() {
        command.setLineIndex(1);
        String originalContent = buffer.getLine(1);

        command.execute();
        command.undo();

        assertEquals(originalContent, buffer.getLine(1));
    }

    @Test
    public void testMultipleDeletesAndUndos() {
        DeleteLineCommand command1 = new DeleteLineCommand(buffer, scanner);
        command1.setLineIndex(0);

        DeleteLineCommand command2 = new DeleteLineCommand(buffer, scanner);
        command2.setLineIndex(0);

        command1.execute();
        assertEquals(2, buffer.getLineCount());
        assertEquals("Line 2", buffer.getLine(0));

        command2.execute();
        assertEquals(1, buffer.getLineCount());
        assertEquals("Line 3", buffer.getLine(0));

        command2.undo();
        assertEquals(2, buffer.getLineCount());
        assertEquals("Line 2", buffer.getLine(0));

        command1.undo();
        assertEquals(3, buffer.getLineCount());
        assertEquals("Line 1", buffer.getLine(0));
    }
}