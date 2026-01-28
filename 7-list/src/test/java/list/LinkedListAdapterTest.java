package list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListAdapterTest {

    private LinkedListAdapter<Integer> adapter;
    private LinkedList<Integer> linkedList;

    @BeforeEach
    void setUp() {
        linkedList = new LinkedList<>();
        adapter = new LinkedListAdapter<>(linkedList);
    }

    @Nested
    @DisplayName("Basic Operations Tests")
    class BasicOperationsTests {

        @Test
        @DisplayName("Should return correct size for empty list")
        void testSizeEmpty() {
            assertEquals(0, adapter.size());
        }

        @Test
        @DisplayName("Should return correct size after adding elements")
        void testSizeAfterAdding() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);
            assertEquals(3, adapter.size());
        }

        @Test
        @DisplayName("Should correctly identify empty list")
        void testIsEmpty() {
            assertTrue(adapter.isEmpty());
            adapter.add(1);
            assertFalse(adapter.isEmpty());
        }

        @Test
        @DisplayName("Should add elements correctly")
        void testAdd() {
            assertTrue(adapter.add(1));
            assertTrue(adapter.add(2));
            assertEquals(2, adapter.size());
            assertEquals(1, adapter.get(0));
            assertEquals(2, adapter.get(1));
        }

        @Test
        @DisplayName("Should add element at specific position")
        void testAddAtPosition() {
            adapter.add(1);
            adapter.add(3);
            adapter.add(1, 2);

            assertEquals(3, adapter.size());
            assertEquals(1, adapter.get(0));
            assertEquals(2, adapter.get(1));
            assertEquals(3, adapter.get(2));
        }

        @Test
        @DisplayName("Should add element at beginning")
        void testAddAtBeginning() {
            adapter.add(2);
            adapter.add(3);
            adapter.add(0, 1);

            assertEquals(1, adapter.get(0));
            assertEquals(2, adapter.get(1));
            assertEquals(3, adapter.get(2));
        }

        @Test
        @DisplayName("Should add element at end")
        void testAddAtEnd() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(2, 3);

            assertEquals(3, adapter.size());
            assertEquals(3, adapter.get(2));
        }

        @Test
        @DisplayName("Should clear the list")
        void testClear() {
            adapter.add(1);
            adapter.add(2);
            adapter.clear();

            assertTrue(adapter.isEmpty());
            assertEquals(0, adapter.size());
        }
    }

    @Nested
    @DisplayName("Get and Set Tests")
    class GetAndSetTests {

        @Test
        @DisplayName("Should get element at index")
        void testGet() {
            adapter.add(10);
            adapter.add(20);
            adapter.add(30);

            assertEquals(10, adapter.get(0));
            assertEquals(20, adapter.get(1));
            assertEquals(30, adapter.get(2));
        }

        @Test
        @DisplayName("Should throw exception for invalid index in get")
        void testGetInvalidIndex() {
            adapter.add(1);
            assertThrows(IllegalArgumentException.class, () -> adapter.get(-1));
            assertThrows(IllegalArgumentException.class, () -> adapter.get(5));
        }

        @Test
        @DisplayName("Should set element and return old value")
        void testSet() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            Integer oldValue = adapter.set(1, 99);

            assertEquals(2, oldValue);
            assertEquals(99, adapter.get(1));
            assertEquals(3, adapter.size());
        }
    }

    @Nested
    @DisplayName("Contains Tests")
    class ContainsTests {

        @Test
        @DisplayName("Should find existing element")
        void testContains() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            assertTrue(adapter.contains(2));
            assertFalse(adapter.contains(99));
        }

        @Test
        @DisplayName("Should check if contains all elements")
        void testContainsAll() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);
            adapter.add(4);

            List<Integer> subset = Arrays.asList(2, 3);
            assertTrue(adapter.containsAll(subset));

            List<Integer> notSubset = Arrays.asList(2, 99);
            assertFalse(adapter.containsAll(notSubset));
        }

        @Test
        @DisplayName("Should return correct index of element")
        void testIndexOf() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);
            adapter.add(2);

            assertEquals(1, adapter.indexOf(2));
            assertEquals(-1, adapter.indexOf(99));
        }

        @Test
        @DisplayName("Should return correct last index of element")
        void testLastIndexOf() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);
            adapter.add(2);
            adapter.add(5);

            assertEquals(3, adapter.lastIndexOf(2));
            assertEquals(0, adapter.lastIndexOf(1));
            assertEquals(-1, adapter.lastIndexOf(99));
        }
    }

    @Nested
    @DisplayName("Remove Tests")
    class RemoveTests {

        @Test
        @DisplayName("Should remove element by index")
        void testRemoveByIndex() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            Integer removed = adapter.remove(1);

            assertEquals(2, removed);
            assertEquals(2, adapter.size());
            assertEquals(1, adapter.get(0));
            assertEquals(3, adapter.get(1));
        }

        @Test
        @DisplayName("Should remove element by object")
        void testRemoveByObject() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            assertTrue(adapter.remove(Integer.valueOf(2)));
            assertEquals(2, adapter.size());
            assertFalse(adapter.contains(2));

            assertFalse(adapter.remove(Integer.valueOf(99)));
        }

        @Test
        @DisplayName("Should remove first element")
        void testRemoveFirst() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            adapter.remove(0);

            assertEquals(2, adapter.size());
            assertEquals(2, adapter.get(0));
        }

        @Test
        @DisplayName("Should remove last element")
        void testRemoveLast() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            adapter.remove(2);

            assertEquals(2, adapter.size());
            assertEquals(2, adapter.get(1));
        }

        @Test
        @DisplayName("Should remove all elements from collection")
        void testRemoveAll() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);
            adapter.add(2);
            adapter.add(4);

            List<Integer> toRemove = Arrays.asList(2, 4);
            assertTrue(adapter.removeAll(toRemove));

            assertEquals(2, adapter.size());
            assertEquals(1, adapter.get(0));
            assertEquals(3, adapter.get(1));
        }

        @Test
        @DisplayName("Should return false when removeAll finds nothing")
        void testRemoveAllNothing() {
            adapter.add(1);
            adapter.add(2);

            List<Integer> toRemove = Arrays.asList(99, 100);
            assertFalse(adapter.removeAll(toRemove));
            assertEquals(2, adapter.size());
        }

        @Test
        @DisplayName("Should retain only specified elements")
        void testRetainAll() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);
            adapter.add(4);
            adapter.add(5);

            List<Integer> toRetain = Arrays.asList(2, 4);
            assertTrue(adapter.retainAll(toRetain));

            assertEquals(2, adapter.size());
            assertTrue(adapter.contains(2));
            assertTrue(adapter.contains(4));
            assertFalse(adapter.contains(1));
            assertFalse(adapter.contains(3));
            assertFalse(adapter.contains(5));
        }

        @Test
        @DisplayName("Should return false when retainAll makes no changes")
        void testRetainAllNoChanges() {
            adapter.add(1);
            adapter.add(2);

            List<Integer> toRetain = Arrays.asList(1, 2, 3, 4);
            assertFalse(adapter.retainAll(toRetain));
            assertEquals(2, adapter.size());
        }
    }

    @Nested
    @DisplayName("AddAll Tests")
    class AddAllTests {

        @Test
        @DisplayName("Should add all elements from collection")
        void testAddAll() {
            adapter.add(1);

            List<Integer> toAdd = Arrays.asList(2, 3, 4);
            assertTrue(adapter.addAll(toAdd));

            assertEquals(4, adapter.size());
            assertEquals(1, adapter.get(0));
            assertEquals(2, adapter.get(1));
            assertEquals(3, adapter.get(2));
            assertEquals(4, adapter.get(3));
        }

        @Test
        @DisplayName("Should return false when adding empty collection")
        void testAddAllEmpty() {
            adapter.add(1);

            List<Integer> toAdd = Collections.emptyList();
            assertFalse(adapter.addAll(toAdd));
            assertEquals(1, adapter.size());
        }

        @Test
        @DisplayName("Should add all elements at specific position")
        void testAddAllAtPosition() {
            adapter.add(1);
            adapter.add(5);

            List<Integer> toAdd = Arrays.asList(2, 3, 4);
            assertTrue(adapter.addAll(1, toAdd));

            assertEquals(5, adapter.size());
            assertEquals(1, adapter.get(0));
            assertEquals(2, adapter.get(1));
            assertEquals(3, adapter.get(2));
            assertEquals(4, adapter.get(3));
            assertEquals(5, adapter.get(4));
        }

        @Test
        @DisplayName("Should add all at beginning")
        void testAddAllAtBeginning() {
            adapter.add(4);
            adapter.add(5);

            List<Integer> toAdd = Arrays.asList(1, 2, 3);
            assertTrue(adapter.addAll(0, toAdd));

            assertEquals(5, adapter.size());
            assertEquals(1, adapter.get(0));
            assertEquals(2, adapter.get(1));
            assertEquals(3, adapter.get(2));
        }
    }

    @Nested
    @DisplayName("Array Conversion Tests")
    class ArrayConversionTests {

        @Test
        @DisplayName("Should convert to Object array")
        void testToArray() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            Object[] array = adapter.toArray();

            assertEquals(3, array.length);
            assertEquals(1, array[0]);
            assertEquals(2, array[1]);
            assertEquals(3, array[2]);
        }

        @Test
        @DisplayName("Should convert to typed array when array is large enough")
        void testToArrayTyped() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            Integer[] array = new Integer[5];
            Integer[] result = adapter.toArray(array);

            assertSame(array, result);
            assertEquals(1, result[0]);
            assertEquals(2, result[1]);
            assertEquals(3, result[2]);
            assertNull(result[3]);
        }

        @Test
        @DisplayName("Should create new array when provided array is too small")
        void testToArrayTypedSmall() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            Integer[] array = new Integer[1];
            Integer[] result = adapter.toArray(array);

            assertNotSame(array, result);
            assertEquals(3, result.length);
            assertEquals(1, result[0]);
            assertEquals(2, result[1]);
            assertEquals(3, result[2]);
        }
    }

    @Nested
    @DisplayName("Iterator Tests")
    class IteratorTests {

        @Test
        @DisplayName("Should iterate through all elements")
        void testIterator() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            Iterator<Integer> iterator = adapter.iterator();

            assertTrue(iterator.hasNext());
            assertEquals(1, iterator.next());
            assertTrue(iterator.hasNext());
            assertEquals(2, iterator.next());
            assertTrue(iterator.hasNext());
            assertEquals(3, iterator.next());
            assertFalse(iterator.hasNext());
        }

        @Test
        @DisplayName("Should throw exception when next() called without hasNext()")
        void testIteratorNoSuchElement() {
            adapter.add(1);

            Iterator<Integer> iterator = adapter.iterator();
            iterator.next();

            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        @DisplayName("Should remove element via iterator")
        void testIteratorRemove() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            Iterator<Integer> iterator = adapter.iterator();
            iterator.next();
            iterator.next();
            iterator.remove();

            assertEquals(2, adapter.size());
            assertEquals(1, adapter.get(0));
            assertEquals(3, adapter.get(1));
        }

        @Test
        @DisplayName("Should work with enhanced for loop")
        void testEnhancedForLoop() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            List<Integer> collected = new ArrayList<>();
            for (Integer value : adapter) {
                collected.add(value);
            }

            assertEquals(Arrays.asList(1, 2, 3), collected);
        }
    }

    @Nested
    @DisplayName("ListIterator Tests")
    class ListIteratorTests {

        @Test
        @DisplayName("Should iterate forward")
        void testListIteratorForward() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            ListIterator<Integer> iterator = adapter.listIterator();

            assertEquals(1, iterator.next());
            assertEquals(2, iterator.next());
            assertEquals(3, iterator.next());
            assertFalse(iterator.hasNext());
        }

        @Test
        @DisplayName("Should iterate backward")
        void testListIteratorBackward() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            ListIterator<Integer> iterator = adapter.listIterator(3);

            assertTrue(iterator.hasPrevious());
            assertEquals(3, iterator.previous());
            assertEquals(2, iterator.previous());
            assertEquals(1, iterator.previous());
            assertFalse(iterator.hasPrevious());
        }

        @Test
        @DisplayName("Should iterate forward and backward")
        void testListIteratorBothDirections() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            ListIterator<Integer> iterator = adapter.listIterator();

            assertEquals(1, iterator.next());
            assertEquals(2, iterator.next());
            assertEquals(2, iterator.previous());
            assertEquals(2, iterator.next());
            assertEquals(3, iterator.next());
        }

        @Test
        @DisplayName("Should return correct indices")
        void testListIteratorIndices() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            ListIterator<Integer> iterator = adapter.listIterator();

            assertEquals(0, iterator.nextIndex());
            assertEquals(-1, iterator.previousIndex());

            iterator.next();
            assertEquals(1, iterator.nextIndex());
            assertEquals(0, iterator.previousIndex());
        }

        @Test
        @DisplayName("Should add element via listIterator")
        void testListIteratorAdd() {
            adapter.add(1);
            adapter.add(3);

            ListIterator<Integer> iterator = adapter.listIterator(1);
            iterator.add(2);

            assertEquals(3, adapter.size());
            assertEquals(2, adapter.get(1));
        }

        @Test
        @DisplayName("Should set element via listIterator")
        void testListIteratorSet() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            ListIterator<Integer> iterator = adapter.listIterator();
            iterator.next();
            iterator.set(99);

            assertEquals(99, adapter.get(0));
        }

        @Test
        @DisplayName("Should remove element via listIterator")
        void testListIteratorRemove() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            ListIterator<Integer> iterator = adapter.listIterator();
            iterator.next();
            iterator.next();
            iterator.remove();

            assertEquals(2, adapter.size());
            assertEquals(1, adapter.get(0));
            assertEquals(3, adapter.get(1));
        }

        @Test
        @DisplayName("Should throw exception on invalid listIterator operations")
        void testListIteratorExceptions() {
            adapter.add(1);
            adapter.add(2);

            ListIterator<Integer> iterator = adapter.listIterator();

            assertThrows(IllegalStateException.class, iterator::remove);
            assertThrows(IllegalStateException.class, () -> iterator.set(99));

            iterator.next();
            iterator.remove();
            assertThrows(IllegalStateException.class, iterator::remove);
        }

        @Test
        @DisplayName("Should start listIterator at specified index")
        void testListIteratorStartIndex() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            ListIterator<Integer> iterator = adapter.listIterator(1);

            assertEquals(2, iterator.next());
            assertEquals(3, iterator.next());
        }
    }

    @Nested
    @DisplayName("SubList Tests")
    class SubListTests {

        @Test
        @DisplayName("Should create sublist")
        void testSubList() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);
            adapter.add(4);
            adapter.add(5);

            List<Integer> subList = adapter.subList(1, 4);

            assertEquals(3, subList.size());
            assertEquals(2, subList.get(0));
            assertEquals(3, subList.get(1));
            assertEquals(4, subList.get(2));
        }

        @Test
        @DisplayName("Should create empty sublist")
        void testSubListEmpty() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            List<Integer> subList = adapter.subList(1, 1);

            assertTrue(subList.isEmpty());
        }

        @Test
        @DisplayName("Should create sublist from beginning")
        void testSubListFromBeginning() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            List<Integer> subList = adapter.subList(0, 2);

            assertEquals(2, subList.size());
            assertEquals(1, subList.get(0));
            assertEquals(2, subList.get(1));
        }

        @Test
        @DisplayName("Should create sublist to end")
        void testSubListToEnd() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            List<Integer> subList = adapter.subList(1, 3);

            assertEquals(2, subList.size());
            assertEquals(2, subList.get(0));
            assertEquals(3, subList.get(1));
        }

        @Test
        @DisplayName("Should throw exception for invalid sublist indices")
        void testSubListInvalidIndices() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(3);

            assertThrows(IndexOutOfBoundsException.class, () -> adapter.subList(-1, 2));
            assertThrows(IndexOutOfBoundsException.class, () -> adapter.subList(0, 5));
            assertThrows(IndexOutOfBoundsException.class, () -> adapter.subList(2, 1));
        }
    }

    @Nested
    @DisplayName("String Type Tests")
    class StringTypeTests {

        @Test
        @DisplayName("Should work with String type")
        void testStringAdapter() {
            LinkedList<String> stringList = new LinkedList<>();
            LinkedListAdapter<String> stringAdapter = new LinkedListAdapter<>(stringList);

            stringAdapter.add("Hello");
            stringAdapter.add("World");

            assertEquals(2, stringAdapter.size());
            assertEquals("Hello", stringAdapter.get(0));
            assertTrue(stringAdapter.contains("World"));
            assertEquals(1, stringAdapter.indexOf("World"));
        }
    }

    @Nested
    @DisplayName("Edge Cases Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("Should handle operations on empty list")
        void testEmptyListOperations() {
            assertFalse(adapter.contains(1));
            assertEquals(-1, adapter.indexOf(1));
            assertEquals(-1, adapter.lastIndexOf(1));
            assertFalse(adapter.remove(Integer.valueOf(1)));
        }

        @Test
        @DisplayName("Should handle single element list")
        void testSingleElementList() {
            adapter.add(42);

            assertEquals(1, adapter.size());
            assertEquals(42, adapter.get(0));
            assertEquals(0, adapter.indexOf(42));
            assertEquals(0, adapter.lastIndexOf(42));

            adapter.remove(0);
            assertTrue(adapter.isEmpty());
        }

        @Test
        @DisplayName("Should handle duplicate elements")
        void testDuplicateElements() {
            adapter.add(1);
            adapter.add(2);
            adapter.add(1);
            adapter.add(2);
            adapter.add(1);

            assertEquals(5, adapter.size());
            assertEquals(0, adapter.indexOf(1));
            assertEquals(4, adapter.lastIndexOf(1));

            adapter.remove(Integer.valueOf(1));
            assertEquals(4, adapter.size());
        }
    }
}