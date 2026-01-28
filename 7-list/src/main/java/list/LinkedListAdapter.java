package list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedListAdapter<T> implements List<T> {
    private LinkedList<T> ll;

    public LinkedListAdapter(LinkedList<T> ll) {
        this.ll = ll;
    }

    @Override
    public int size() {
        return ll.getLength();
    }

    @Override
    public boolean isEmpty() {
        return ll.getLength() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return ll.containsNodeWithValue((T) o);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < ll.getLength();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return ll.getWithIndex(currentIndex++);
            }

            @Override
            public void remove() {
                if (currentIndex == 0) {
                    throw new IllegalStateException();
                }
                ll.removeWithIndex(--currentIndex);
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[ll.getLength()];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = ll.getWithIndex(i);
        }
        return arr;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        int size = ll.getLength();
        if (a.length < size) {
            a = (E[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        }
        for(int i = 0; i < size; i++) {
            a[i] = (E) ll.getWithIndex(i);
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(T o) {
        ll.pushToEnd(o);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return ll.removeWithObject(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for(Object o : collection) {
            if(!ll.containsNodeWithValue((T) o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        for(Object o : collection) {
            ll.pushToEnd((T) o);
        }
        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int j = i;
        for(Object o : collection) {
            ll.addWithPosition(j++, (T) o);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean modified = false;
        for(Object o : collection) {
            while(ll.removeWithObject(o)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean modified = false;
        int i = 0;
        while (i < ll.getLength()) {
            T element = ll.getWithIndex(i);
            if (!collection.contains(element)) {
                ll.removeWithIndex(i);
                modified = true;
            } else {
                i++;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        ll = new LinkedList<T>();
    }

    @Override
    public T get(int i) {
        return ll.getWithIndex(i);
    }

    @Override
    public T set(int i, T o) {
        return ll.replace(i, o);
    }

    @Override
    public void add(int i, T o) {
        ll.addWithPosition(i, o);
    }

    @Override
    public T remove(int i) {
        return ll.removeWithIndex(i);
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < ll.getLength(); i++) {
            if(ll.getWithIndex(i).equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        for(int i = 0; i < ll.getLength(); i++) {
            if(ll.getWithIndex(i).equals(o)) {
                lastIndex = i;
            }
        }
        return lastIndex;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > ll.getLength()) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        return new ListIterator<T>() {
            private int currentIndex = index;
            private int lastReturnedIndex = -1;

            @Override
            public boolean hasNext() {
                return currentIndex < ll.getLength();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastReturnedIndex = currentIndex;
                return ll.getWithIndex(currentIndex++);
            }

            @Override
            public boolean hasPrevious() {
                return currentIndex > 0;
            }

            @Override
            public T previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                currentIndex--;
                lastReturnedIndex = currentIndex;
                return ll.getWithIndex(currentIndex);
            }

            @Override
            public int nextIndex() {
                return currentIndex;
            }

            @Override
            public int previousIndex() {
                return currentIndex - 1;
            }

            @Override
            public void remove() {
                if (lastReturnedIndex < 0) {
                    throw new IllegalStateException();
                }
                ll.removeWithIndex(lastReturnedIndex);
                if (lastReturnedIndex < currentIndex) {
                    currentIndex--;
                }
                lastReturnedIndex = -1;
            }

            @Override
            public void set(T e) {
                if (lastReturnedIndex < 0) {
                    throw new IllegalStateException();
                }
                ll.replace(lastReturnedIndex, e);
            }

            @Override
            public void add(T e) {
                ll.addWithPosition(currentIndex, e);
                currentIndex++;
                lastReturnedIndex = -1;
            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > ll.getLength() || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }

        LinkedList<T> subLinkedList = new LinkedList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subLinkedList.pushToEnd(ll.getWithIndex(i));
        }
        return new LinkedListAdapter<>(subLinkedList);
    }
}