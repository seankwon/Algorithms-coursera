import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private class Node<Item> {
        public Item item;
        public Node<Item> next;
        public Node<Item> prev;

        public Node() { }
    }

    public int size = 0;
    public Node<Item> head;
    private Node<Item> tail;

    public Deque() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        checkNull(item);

        Node<Item> oldhead = head;
        head = new Node<Item>();
        head.item = item;
        head.next = oldhead;
        head.prev = null;

        if (isEmpty()) {
            tail = head;
        } else {
            oldhead.prev = head;
        }
        size += 1;

    }

    public void addLast(Item item) {
        checkNull(item);

        Node<Item> oldTail = tail;
        tail = new Node<Item>();
        tail.item = item;
        tail.next = null;

        if (isEmpty()) {
            head = tail;
        } else {
            oldTail.next = tail;
            tail.prev = oldTail;
        }

        size += 1;
    }


    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = head.item;
        head = head.next;
        size -= 1;

        if (isEmpty()) tail = null;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = tail.item;

        Node<Item> oldTail = tail;
        tail = tail.prev;
        oldTail = null;

        size -= 1;
        if (!isEmpty()) tail.next = null;
        else            head = null;

        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = head;
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            return;
        }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private void checkNull(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
    }

}


