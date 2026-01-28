package list;

public class LinkedList<T> {
    private Node<T> head;
    private int length;

    public LinkedList() {
        this.length = 0;
    }

    public LinkedList(T info) {
        this.head = new Node<>(info);
        this.length = 1;
    }

    public void pushToEnd(T info) {
        if(this.head == null) {
            this.head = new Node<>(info);
        } else {
            Node<T> curr = this.head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = new Node<>(info);
        }
        length++;
    }
    public int getLength() {
        return length;
    }

    public T removeTheLastElement() {
        if(head == null) {
            throw new IllegalStateException("Empty list");
        }
        if(head.next == null) {
            T val = head.info;
            head = null;
            length--;
            return val;
        }

        Node<T> curr = head;
        while (curr.next.next != null) {
            curr = curr.next;
        }

        T val = curr.next.info;
        curr.next = null;
        length--;
        return val;
    }

    public T getWithIndex(int index) {
        if (index < 0 || index >= length) {
            throw new IllegalArgumentException("Illegal index");
        }
        Node<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.info;
    }

    public boolean containsNodeWithValue(T info) {
        Node<T> curr = head;
        for (int i = 0; i < length; i++) {
            if(curr == null)
                return false;
            if(curr.info.equals(info))
                return true;
            curr = curr.next;
        }
        return false;
    }
    public T replace(int i, T o) {
        T removedNode = removeWithIndex(i);
        addWithPosition(i, o);
        return removedNode;
    }


    public void addWithPosition(int index, T o) {
        if(index < 0 || index > length) {
            throw new IllegalArgumentException("Illegal index");
        }
        if (index == 0) {
            Node<T> newNode = new Node<>(o);
            newNode.next = head;
            head = newNode;
            length++;
            return;
        }

        Node<T> aux = this.head;
        for(int i = 0; i < index - 1; i++) {
            aux = aux.next;
        }
        Node<T> nodeToInsert = new Node<>(o);
        nodeToInsert.next = aux.next;
        aux.next = nodeToInsert;
        length++;
    }

    public boolean removeWithObject(Object o) {
        Node<T> aux = this.head;
        for(int i = 0; i < length; i++) {
            if(aux.info.equals(o)) {
                this.removeWithIndex(i);
                return true;
            }
            aux = aux.next;
        }
        return false;
    }

    public T removeWithIndex(int index) {
        if (index < 0 || index >= length) {
            throw new IllegalArgumentException("Illegal index");
        }
        if (index == 0) {
            T output = head.info;
            head = head.next;
            length--;
            return output;
        }

        Node<T> aux = this.head;
        for(int i = 0; i < index - 1; i++) {
            aux = aux.next;
        }
        T output = aux.next.info;
        aux.next = aux.next.next;
        length--;
        return output;
    }

}
