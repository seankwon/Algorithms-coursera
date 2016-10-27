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

    public static void main(String[] args) {
        /*
         * XXX Test 1
         */
        Deque<Integer> d = new Deque<Integer>();
        String res = "";
        int t;
        d.addFirst(1);
        if (!d.isEmpty()) {
            System.out.println("1. Empty Test Passed");
        }

        /*
         * XXX Test 2
         */

        d = new Deque<Integer>();
        d.addFirst(3);
        d.addFirst(2);
        d.addFirst(1);
        for (Integer i : d) {
            res += (i + "");
        }
        if (res.equals("123")) {
            System.out.println("2. Add First Test Passed");
        }

        /*
         * XXX Test 3
         */

        d = new Deque<Integer>();
        res = "";
        d.addLast(1);
        d.addLast(3);
        d.addLast(5);
        for (Integer i : d) {
            res += (i + "");
        }
        if (res.equals("135")) {
            System.out.println("3. Add Last Test Passed");
        }

        /*
         * XXX Test 4
         */

        d = new Deque<Integer>();
        res = "";
        for (Integer i : d) {
            res += (i + "");
        }
        if (res.equals("")) {
            System.out.println("4. Empty String Test Passed");
        }
        /*
         * XXX Test 5
         */

        d = new Deque<Integer>();
        res = "";
        d.addFirst(3);
        d.addLast(4);
        d.addFirst(2);
        d.addLast(5);
        d.addFirst(1);
        for (Integer i : d) {
            res += (i + "");
        }
        if (res.equals("12345")) {
            System.out.println("5. Add first and last Test Passed");
        }

        /*
         * XXX Test 6
         */
        d = new Deque<Integer>();
        res = "";
        d.addFirst(3);
        for (Integer i : d) {
            res += (i + "");
        }
        if (res.equals("3")) {
            System.out.println("6. Add First singular Test Passed");
        }

        /*
         * XXX Test 7
         */
        d = new Deque<Integer>();
        res = "";
        d.addLast(3);
        for (Integer i : d) {
            res += (i + "");
        }
        if (res.equals("3")) {
            System.out.println("7. Add Last singular Test Passed");
        }

        /*
         * XXX Test 8
         */
        d = new Deque<Integer>();
        res = "";
        d.addFirst(3);
        t = d.removeFirst();
        for (Integer i : d) {
            res += (i + "");
        }
        if (t == 3 && d.isEmpty()) {
            System.out.println("8. Remove first add first Test Passed");
        }

        /*
         * XXX Test 9
         */

        d = new Deque<Integer>();
        res = "";
        d.addFirst(3);
        d.addFirst(2);
        d.addFirst(1);
        d.removeFirst();
        t = d.removeFirst();
        for (Integer i : d) {
            res += (i + "");
        }
        if (t == 2 && res.equals("3")) {
            System.out.println("9. Remove first twice Test Passed");
        }

        /*
         * XXX Test 10
         */
        d = new Deque<Integer>();
        res = "";
        d.addFirst(1);
        d.addFirst(0);
        d.addLast(2);
        d.addLast(3);
        t = d.removeFirst();
        t = d.removeFirst();
        for (Integer i : d) {
            res += (i + "");
        }
        if (t == 1 && res.equals("23")) {
            System.out.println("10. Remove First twice, add last Test Passed");
        }

        /*
         * XXX Test 11
         */
        d = new Deque<Integer>();
        res = "";
        d.addLast(3);
        t = d.removeLast();
        if (t == 3 && d.isEmpty()) {
            System.out.println("11. Remove last once Test Passed");
        }

        /*
         * XXX Test 12
         */
        d = new Deque<Integer>();
        res = "";
        d.addLast(1);
        d.addLast(2);
        d.addLast(3);
        t = d.removeLast();
        for (Integer i : d) {
            res += (i + "");
        }
        if (t == 3 && res.equals("12")) {
            System.out.println("12. Remove last once with multiple addLast Test Passed");
        }

        /*
         * XXX Test 13
         */
        d = new Deque<Integer>();
        res = "";
        d.addFirst(1);
        d.addFirst(0);
        d.removeLast();
        t = d.removeFirst();

        if (t == 0 && d.isEmpty()) {
            System.out.println("13. Remove last and first once empty Test Passed");
        }
        /*
         * XXX Test 14
         */
        d = new Deque<Integer>();
        res = "";
        d.addFirst(1);
        d.addFirst(0);
        d.addLast(2);
        d.addLast(3);
        d.addLast(4);

        d.removeLast();
        t = d.removeFirst();
        d.removeFirst();
        d.removeLast();

        for (Integer i : d) {
            res += (i + "");
        }

        if (t == 0 && res.equals("2")) {
            System.out.println("14. Remove last and first multiple empty Test Passed");
        }
        /*
         * XXX Test 14
         */
        d = new Deque<Integer>();
        res = "";
        d.addFirst(1);
        t = d.removeFirst();
        if (t == 1 && d.isEmpty()) {
            System.out.println("15. Remove last add first singular Test Passed");
        }

    }

}


