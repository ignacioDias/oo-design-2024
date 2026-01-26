package texteditor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class BufferTest {

    private Buffer buffer;

    @BeforeEach
    public void setUp() {
        buffer = new Buffer();
    }

    @Test
    public void testBufferIsEmptyWhenCreated() {
        assertTrue(buffer.isEmpty());
        assertEquals(0, buffer.getLineCount());
    }

    @Test
    public void testSetAndGetBuffer() {
        List<String> lines = new ArrayList<>();
        lines.add("Line 1");
        lines.add("Line 2");

        buffer.setBuffer(lines);

        assertEquals(2, buffer.getLineCount());
        assertFalse(buffer.isEmpty());
        assertEquals(lines, buffer.getBuffer());
    }

    @Test
    public void testGetLine() {
        List<String> lines = new ArrayList<>();
        lines.add("First line");
        lines.add("Second line");
        lines.add("Third line");

        buffer.setBuffer(lines);

        assertEquals("First line", buffer.getLine(0));
        assertEquals("Second line", buffer.getLine(1));
        assertEquals("Third line", buffer.getLine(2));
    }

    @Test
    public void testGetLineThrowsExceptionForInvalidIndex() {
        List<String> lines = new ArrayList<>();
        lines.add("Only line");
        buffer.setBuffer(lines);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            buffer.getLine(5);
        });
    }

    @Test
    public void testRemoveLine() {
        List<String> lines = new ArrayList<>();
        lines.add("Line 1");
        lines.add("Line 2");
        lines.add("Line 3");

        buffer.setBuffer(lines);
        buffer.removeLine(1);

        assertEquals(2, buffer.getLineCount());
        assertEquals("Line 1", buffer.getLine(0));
        assertEquals("Line 3", buffer.getLine(1));
    }

    @Test
    public void testInsertLine() {
        List<String> lines = new ArrayList<>();
        lines.add("Line 1");
        lines.add("Line 2");
        lines.add("Line 3");

        buffer.setBuffer(lines);
        buffer.insertLine(1, "New Line 2");

        assertEquals(4, buffer.getLineCount());
        assertEquals("Line 1", buffer.getLine(0));
        assertEquals("New Line 2", buffer.getLine(1));
        assertEquals("Line 2", buffer.getLine(2));
    }

    @Test
    public void testClear() {
        List<String> lines = new ArrayList<>();
        lines.add("Line 1");
        lines.add("Line 2");

        buffer.setBuffer(lines);
        assertFalse(buffer.isEmpty());

        buffer.clear();

        assertTrue(buffer.isEmpty());
        assertEquals(0, buffer.getLineCount());
    }

    @Test
    public void testToString() {
        List<String> lines = new ArrayList<>();
        lines.add("First");
        lines.add("Second");
        lines.add("Third");

        buffer.setBuffer(lines);

        String expected = "First\nSecond\nThird\n";
        assertEquals(expected, buffer.toString());
    }

    @Test
    public void testToStringEmptyBuffer() {
        assertEquals("", buffer.toString());
    }

    @Test
    public void testGetLineCount() {
        assertEquals(0, buffer.getLineCount());

        List<String> lines = new ArrayList<>();
        lines.add("Line 1");
        buffer.setBuffer(lines);

        assertEquals(1, buffer.getLineCount());

        lines.add("Line 2");
        lines.add("Line 3");
        buffer.setBuffer(lines);

        assertEquals(3, buffer.getLineCount());
    }
}