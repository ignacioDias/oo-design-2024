package list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    private LinkedList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new LinkedList<>();
    }

    @Test
    public void testEmptyConstructor() {
        assertEquals(0, list.getLength());
    }

    @Test
    public void testConstructorWithValue() {
        LinkedList<String> stringList = new LinkedList<>("first");
        assertEquals(1, stringList.getLength());
        assertEquals("first", stringList.getWithIndex(0));
    }

    @Test
    public void testPushToEndToEmptyList() {
        list.pushToEnd(10);
        assertEquals(1, list.getLength());
        assertEquals(10, list.getWithIndex(0));
    }

    @Test
    public void testPushToEndMultipleElements() {
        list.pushToEnd(1);
        list.pushToEnd(2);
        list.pushToEnd(3);

        assertEquals(3, list.getLength());
        assertEquals(1, list.getWithIndex(0));
        assertEquals(2, list.getWithIndex(1));
        assertEquals(3, list.getWithIndex(2));
    }

    @Test
    public void testRemoveWithIndexTheLastElementSingleElement() {
        list.pushToEnd(42);
        int popped = list.removeTheLastElement();

        assertEquals(42, popped);
        assertEquals(0, list.getLength());
    }

    @Test
    public void testRemoveWithIndexTheLastElementMultipleElements() {
        list.pushToEnd(1);
        list.pushToEnd(2);
        list.pushToEnd(3);

        assertEquals(3, list.removeTheLastElement());
        assertEquals(2, list.getLength());
        assertEquals(2, list.removeTheLastElement());
        assertEquals(1, list.getLength());
        assertEquals(1, list.removeTheLastElement());
        assertEquals(0, list.getLength());
    }

    @Test
    public void testRemoveWithIndexTheLastElementEmptyList() {
        assertThrows(IllegalStateException.class, () -> list.removeTheLastElement());
    }

    @Test
    public void testGetWithIndexValidIndex() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(30);

        assertEquals(10, list.getWithIndex(0));
        assertEquals(20, list.getWithIndex(1));
        assertEquals(30, list.getWithIndex(2));
    }

    @Test
    public void testGetWithIndexNegativeIndex() {
        list.pushToEnd(1);
        assertThrows(IllegalArgumentException.class, () -> list.getWithIndex(-1));
    }

    @Test
    public void testGetWithIndexIndexTooLarge() {
        list.pushToEnd(1);
        assertThrows(IllegalArgumentException.class, () -> list.getWithIndex(1));
        assertThrows(IllegalArgumentException.class, () -> list.getWithIndex(5));
    }

    @Test
    public void testGetWithIndexOnEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> list.getWithIndex(0));
    }

    @Test
    public void testMixedOperations() {
        list.pushToEnd(1);
        list.pushToEnd(2);
        assertEquals(2, list.removeTheLastElement());
        list.pushToEnd(3);
        list.pushToEnd(4);

        assertEquals(3, list.getLength());
        assertEquals(1, list.getWithIndex(0));
        assertEquals(3, list.getWithIndex(1));
        assertEquals(4, list.getWithIndex(2));
    }

    @Test
    public void testWithDifferentTypes() {
        LinkedList<String> stringList = new LinkedList<>();
        stringList.pushToEnd("hello");
        stringList.pushToEnd("world");

        assertEquals(2, stringList.getLength());
        assertEquals("hello", stringList.getWithIndex(0));
        assertEquals("world", stringList.removeTheLastElement());
    }
    @Test
    public void testContainsNodeWithValueElementInList() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(30);

        assertTrue(list.containsNodeWithValue(10));
        assertTrue(list.containsNodeWithValue(20));
        assertTrue(list.containsNodeWithValue(30));
    }

    @Test
    public void testContainsNodeWithValueElementNotInList() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(30);

        assertFalse(list.containsNodeWithValue(5));
        assertFalse(list.containsNodeWithValue(100));
    }

    @Test
    public void testContainsNodeWithValueOnEmptyList() {
        assertFalse(list.containsNodeWithValue(10));
    }

    @Test
    public void testContainsNodeWithValueSingleElement() {
        list.pushToEnd(42);

        assertTrue(list.containsNodeWithValue(42));
        assertFalse(list.containsNodeWithValue(41));
    }

    @Test
    public void testContainsNodeWithValueFirstElement() {
        list.pushToEnd(1);
        list.pushToEnd(2);
        list.pushToEnd(3);

        assertTrue(list.containsNodeWithValue(1));
    }

    @Test
    public void testContainsNodeWithValueLastElement() {
        list.pushToEnd(1);
        list.pushToEnd(2);
        list.pushToEnd(3);

        assertTrue(list.containsNodeWithValue(3));
    }

    @Test
    public void testContainsNodeWithValueMiddleElement() {
        list.pushToEnd(1);
        list.pushToEnd(2);
        list.pushToEnd(3);

        assertTrue(list.containsNodeWithValue(2));
    }

    @Test
    public void testContainsNodeWithValueDuplicateElements() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(10);

        assertTrue(list.containsNodeWithValue(10));
    }

    @Test
    public void testContainsNodeWithValueAfterRemoveWithIndexTheLastElement() {
        list.pushToEnd(1);
        list.pushToEnd(2);
        list.pushToEnd(3);

        list.removeTheLastElement();

        assertTrue(list.containsNodeWithValue(1));
        assertTrue(list.containsNodeWithValue(2));
        assertFalse(list.containsNodeWithValue(3));
    }

    @Test
    public void testContainsNodeWithValueWithDifferentTypes() {
        LinkedList<String> stringList = new LinkedList<>();
        stringList.pushToEnd("apple");
        stringList.pushToEnd("banana");
        stringList.pushToEnd("cherry");

        assertTrue(stringList.containsNodeWithValue("banana"));
        assertFalse(stringList.containsNodeWithValue("grape"));
    }

    @Test
    public void testRemove_WithIndex_FirstElement() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(30);

        Integer removed = list.removeWithIndex(0);

        assertEquals(10, removed);
        assertEquals(2, list.getLength());
        assertEquals(20, list.getWithIndex(0));
        assertEquals(30, list.getWithIndex(1));
    }

    @Test
    public void testRemove_WithIndex_MiddleElement() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(30);

        Integer removed = list.removeWithIndex(1);

        assertEquals(20, removed);
        assertEquals(2, list.getLength());
        assertEquals(10, list.getWithIndex(0));
        assertEquals(30, list.getWithIndex(1));
    }

    @Test
    public void testRemove_WithIndex_LastElement() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(30);

        Integer removed = list.removeWithIndex(2);

        assertEquals(30, removed);
        assertEquals(2, list.getLength());
        assertEquals(10, list.getWithIndex(0));
        assertEquals(20, list.getWithIndex(1));
    }

    @Test
    public void testRemove_WithIndex_SingleElement() {
        list.pushToEnd(42);

        Integer removed = list.removeWithIndex(0);

        assertEquals(42, removed);
        assertEquals(0, list.getLength());
    }

    @Test
    public void testRemove_WithIndex_NegativeIndex() {
        list.pushToEnd(10);

        assertThrows(IllegalArgumentException.class, () -> {
            list.removeWithIndex(-1);
        });
    }

    @Test
    public void testRemove_WithIndex_IndexOutOfBounds() {
        list.pushToEnd(10);
        list.pushToEnd(20);

        assertThrows(IllegalArgumentException.class, () -> {
            list.removeWithIndex(2);
        });
    }

    @Test
    public void testRemove_WithIndex_EmptyList() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.removeWithIndex(0);
        });
    }

    @Test
    public void testAddWithPosition_AtBeginning() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(30);

        list.addWithPosition(0, 5);

        assertEquals(4, list.getLength());
        assertEquals(5, list.getWithIndex(0));
        assertEquals(10, list.getWithIndex(1));
        assertEquals(20, list.getWithIndex(2));
        assertEquals(30, list.getWithIndex(3));
    }

    @Test
    public void testAddWithPosition_InMiddle() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(30);

        list.addWithPosition(1, 15);

        assertEquals(4, list.getLength());
        assertEquals(10, list.getWithIndex(0));
        assertEquals(15, list.getWithIndex(1));
        assertEquals(20, list.getWithIndex(2));
        assertEquals(30, list.getWithIndex(3));
    }

    @Test
    public void testAddWithPosition_AtEnd() {
        list.pushToEnd(10);
        list.pushToEnd(20);

        list.addWithPosition(2, 30);

        assertEquals(3, list.getLength());
        assertEquals(10, list.getWithIndex(0));
        assertEquals(20, list.getWithIndex(1));
        assertEquals(30, list.getWithIndex(2));
    }

    @Test
    public void testAddWithPosition_EmptyList() {
        list.addWithPosition(0, 42);

        assertEquals(1, list.getLength());
        assertEquals(42, list.getWithIndex(0));
    }

    @Test
    public void testAddWithPosition_NegativeIndex() {
        list.pushToEnd(10);

        assertThrows(IllegalArgumentException.class, () -> {
            list.addWithPosition(-1, 5);
        });
    }

    @Test
    public void testAddWithPosition_IndexTooLarge() {
        list.pushToEnd(10);
        list.pushToEnd(20);

        assertThrows(IllegalArgumentException.class, () -> {
            list.addWithPosition(3, 30);
        });
    }

    @Test
    public void testRemoveWithIndexWithObject_FirstElement() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(30);

        boolean removed = list.removeWithObject(10);

        assertTrue(removed);
        assertEquals(2, list.getLength());
        assertEquals(20, list.getWithIndex(0));
        assertEquals(30, list.getWithIndex(1));
    }

    @Test
    public void testRemoveWithIndexWithObject_MiddleElement() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(30);

        boolean removed = list.removeWithObject(20);

        assertTrue(removed);
        assertEquals(2, list.getLength());
        assertEquals(10, list.getWithIndex(0));
        assertEquals(30, list.getWithIndex(1));
    }

    @Test
    public void testRemoveWithIndexWithObject_LastElement() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(30);

        boolean removed = list.removeWithObject(30);

        assertTrue(removed);
        assertEquals(2, list.getLength());
        assertEquals(10, list.getWithIndex(0));
        assertEquals(20, list.getWithIndex(1));
    }

    @Test
    public void testRemoveWithIndexWithObject_NotFound() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(30);

        boolean removed = list.removeWithObject(99);

        assertFalse(removed);
        assertEquals(3, list.getLength());
    }

    @Test
    public void testRemoveWithIndexWithObject_EmptyList() {
        boolean removed = list.removeWithObject(10);

        assertFalse(removed);
        assertEquals(0, list.getLength());
    }

    @Test
    public void testRemoveWithIndexWithObject_SingleElement() {
        list.pushToEnd(42);

        boolean removed = list.removeWithObject(42);

        assertTrue(removed);
        assertEquals(0, list.getLength());
    }

    @Test
    public void testRemoveWithIndexWithObject_Duplicate_RemovesFirstOccurrence() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(10);
        list.pushToEnd(30);

        boolean removed = list.removeWithObject(10);

        assertTrue(removed);
        assertEquals(3, list.getLength());
        assertEquals(20, list.getWithIndex(0));
        assertEquals(10, list.getWithIndex(1));
        assertEquals(30, list.getWithIndex(2));
    }


    @Test
    public void testReplace_FirstElement() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(30);

        Integer replaced = list.replace(0, 99);

        assertEquals(10, replaced);
        assertEquals(3, list.getLength());
        assertEquals(99, list.getWithIndex(0));
        assertEquals(20, list.getWithIndex(1));
        assertEquals(30, list.getWithIndex(2));
    }

    @Test
    public void testReplace_MiddleElement() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(30);

        Integer replaced = list.replace(1, 99);

        assertEquals(20, replaced);
        assertEquals(3, list.getLength());
        assertEquals(10, list.getWithIndex(0));
        assertEquals(99, list.getWithIndex(1));
        assertEquals(30, list.getWithIndex(2));
    }

    @Test
    public void testReplace_LastElement() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(30);

        Integer replaced = list.replace(2, 99);

        assertEquals(30, replaced);
        assertEquals(3, list.getLength());
        assertEquals(10, list.getWithIndex(0));
        assertEquals(20, list.getWithIndex(1));
        assertEquals(99, list.getWithIndex(2));
    }

    @Test
    public void testReplace_SingleElement() {
        list.pushToEnd(42);

        Integer replaced = list.replace(0, 99);

        assertEquals(42, replaced);
        assertEquals(1, list.getLength());
        assertEquals(99, list.getWithIndex(0));
    }

    @Test
    public void testReplace_InvalidIndex() {
        list.pushToEnd(10);
        list.pushToEnd(20);

        assertThrows(IllegalArgumentException.class, () -> {
            list.replace(5, 99);
        });
    }

    @Test
    public void testReplace_NegativeIndex() {
        list.pushToEnd(10);

        assertThrows(IllegalArgumentException.class, () -> {
            list.replace(-1, 99);
        });
    }

    @Test
    public void testCombinedOperations() {
        list.pushToEnd(10);
        list.pushToEnd(20);
        list.pushToEnd(30);
        list.pushToEnd(40);

        list.replace(1, 25);
        assertEquals(25, list.getWithIndex(1));

        list.addWithPosition(1, 15);
        assertEquals(5, list.getLength());
        assertEquals(15, list.getWithIndex(1));

        list.removeWithObject(30);
        assertEquals(4, list.getLength());
        assertFalse(list.containsNodeWithValue(30));

        list.removeWithIndex(0);
        assertEquals(3, list.getLength());
        assertEquals(15, list.getWithIndex(0));
    }

    @Test
    public void testStrings() {
        LinkedList<String> stringList = new LinkedList<>();
        stringList.pushToEnd("Apple");
        stringList.pushToEnd("Banana");
        stringList.pushToEnd("Cherry");

        String replaced = stringList.replace(1, "Blueberry");
        assertEquals("Banana", replaced);
        assertEquals("Blueberry", stringList.getWithIndex(1));

        boolean removed = stringList.removeWithObject("Apple");
        assertTrue(removed);
        assertEquals(2, stringList.getLength());

        stringList.addWithPosition(0, "Apricot");
        assertEquals("Apricot", stringList.getWithIndex(0));
    }

}