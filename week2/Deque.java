import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;

        public Node<Item> getNext() {
            return next;
        }
        public Node<Item> getPrev() {
            return prev;
        }
        public Item getItem() {
            return item;
        }
        public void setNext(Node<Item> n) {
            this.next = n;
        }
        public void setPrev(Node<Item> n) {
            this.prev = n;
        }
        public void setItem(Item i) {
            this.item = i;
        }
    }

    private int size = 0;
    private Node<Item> head;
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
        head.setItem(item);
        head.setNext(oldhead);
        head.setPrev(null);

        if (isEmpty()) {
            tail = head;
        } else {
            oldhead.setPrev(head);
        }
        size += 1;

    }

    public void addLast(Item item) {
        checkNull(item);

        Node<Item> oldTail = tail;
        tail = new Node<Item>();
        tail.setItem(item);
        tail.setNext(null);

        if (isEmpty()) {
            head = tail;
        } else {
            oldTail.setNext(tail);
            tail.setPrev(oldTail);
        }

        size += 1;
    }


    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = head.getItem();
        head = head.getNext();
        size -= 1;

        if (isEmpty()) tail = null;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = tail.getItem();

        Node<Item> oldTail = tail;
        tail = tail.getPrev();
        oldTail = null;

        size -= 1;
        if (!isEmpty()) tail.setNext(null);
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
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (isEmpty()) throw new java.util.NoSuchElementException();

            Item item = current.getItem();
            current = current.getNext();
            return item;
        }
    }

    private void checkNull(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
    }

}


