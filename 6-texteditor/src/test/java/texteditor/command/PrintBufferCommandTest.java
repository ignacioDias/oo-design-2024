package texteditor.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import texteditor.Buffer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintBufferCommandTest {

    private Buffer buffer;
    private Scanner scanner;
    private Command command;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        buffer = new Buffer();
        List<String> lines = new ArrayList<>();
        lines.add("Line 1");
        lines.add("Line 2");
        lines.add("Line 3");
        buffer.setBuffer(lines);

        scanner = new Scanner(System.in);
        command = new PrintBufferCommand(buffer, scanner);

        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testExecutePrintsBufferContent() {
        command.execute();

        String output = outContent.toString();
        assertTrue(output.contains("Line 1"));
        assertTrue(output.contains("Line 2"));
        assertTrue(output.contains("Line 3"));
    }

    @Test
    public void testExecutePrintsEmptyBuffer() {
        Buffer emptyBuffer = new Buffer();
        PrintBufferCommand emptyCommand = new PrintBufferCommand(emptyBuffer, scanner);

        emptyCommand.execute();

        String output = outContent.toString();
        assertTrue(output.isEmpty() || output.trim().isEmpty() ||
                output.contains("empty") || output.contains("Empty"));
    }

    @Test
    public void testUndoDoesNothing() {
        command.execute();
        String afterExecute = outContent.toString();

        command.undo();
        String afterUndo = outContent.toString();

        assertEquals(afterExecute, afterUndo);
    }

    @Test
    public void testInitializeDoesNotRequireInput() {
        assertDoesNotThrow(() -> {
            command.initialize();
        });
    }

    @Test
    public void testDuplicateCreatesNewInstance() {
        Command duplicate = command.duplicate();

        assertNotSame(command, duplicate);
        assertInstanceOf(PrintBufferCommand.class, duplicate);
    }

    @Test
    public void testMultipleExecutions() {
        command.execute();
        command.execute();

        String output = outContent.toString();
        int countLine1 = output.split("Line 1", -1).length - 1;

        assertEquals(2, countLine1);
    }

    @Test
    public void testPrintAfterModification() {
        command.execute();

        buffer.insertLine(1, "Modified Line 2");
        outContent.reset();

        command.execute();

        String output = outContent.toString();
        assertTrue(output.contains("Modified Line 2"));
    }
}