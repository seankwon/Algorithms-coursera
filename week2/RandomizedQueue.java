import edu.princeton.cs.algs4.StdRandom;
public class RandomizedQueue<Item> /*implements Iterable<Item>*/ {
   private int size;
   private Item[] items;
   private int N;
   public RandomizedQueue() {
       //item = (Item[]) new Object[];
       size = 0;
       N = 0;
   }

   public boolean isEmpty() {
       return size == 0;
   }

   public int size() {
       return size;
   }

   public void enqueue(Item item) {
       if (isEmpty()) {
           resize(1);
       } else if (N >= items.length) {
           resize(size * 2);
       }

       items[N] = item;
       N++;
       size++;
       return;
   }

   public Item sample() {
       if (isEmpty()) throw new java.util.NoSuchElementException();
       int idx = StdRandom.uniform(N);
       if (items[idx] == null) return sample();
       else return items[idx];
   }
   public Item dequeue() {
       if (isEmpty()) throw new java.util.NoSuchElementException();
       int idx = StdRandom.uniform(N);
       Item res = items[idx];

       if (res != null) {
           items[idx] = null;
           size--;

           if (size > 4 && size <= items.length / 4) {
               resize(items.length / 4);
           }
       } else {
           res = dequeue();
       }
       return res;
   }
   /*
   public Iterator<Item> iterator() {
       return new QueueIterator();
   }
   private class QueueIterator implements Iterator<Item> {

   }
   */
   private void resize(int n) {
       Item[] newitems;
       if (n == 1 || isEmpty()) {
           items = (Item[]) new Object[1];
           size = items.length;
       } else {
           newitems = (Item[]) new Object[n];
           addToNewListAndSet(newitems);
       }
   }

   private void addToNewListAndSet(Item[] newitems) {
       int count = 0;
       for (int i = 0; i < items.length; i++) {
           if (items[i] != null) {
               newitems[count] = items[i];
               count++;
           }
       }
       N = count;
       size = count;
       items = newitems;
   }

   private boolean needResizeParam() {
       return (N == items.length || size <= N / 2);
   }

   public static void main(String[] args) {
       RandomizedQueue<Integer> i = new RandomizedQueue<Integer>();
       i.enqueue(1);

       if (i.sample() == 1)
           System.out.println("1. enqueue test passed");

       i = new RandomizedQueue<Integer>();
       i.enqueue(1);
       i.dequeue();
       i.enqueue(2);
       i.enqueue(2);
       i.dequeue();
       i.dequeue();
       i.enqueue(2);
       i.enqueue(2);
       i.enqueue(2);
       i.dequeue();
       i.dequeue();
       i.dequeue();
       i.enqueue(3);
       i.enqueue(3);
       i.enqueue(3);
       i.dequeue();
       i.dequeue();
       i.dequeue();
       i.enqueue(3);
       i.dequeue();
       i.enqueue(3);
       i.enqueue(3);
       i.dequeue();
       i.dequeue();
       i.dequeue();
       i.enqueue(3);
       i.enqueue(3);
       System.out.println(i.isEmpty());
       System.out.println(i.size());

   }
}
