import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
public class RandomizedQueue<Item> implements Iterable<Item> {
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
           resize(items.length * 2);
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

       if (res == null) res = dequeue();

       if (idx < N-1) {
           Item lastItem = items[N-1];
           items[idx] = lastItem;
       }

       items[N-1] = null;
       N--;
       size--;

       if (size > 4 && size < items.length / 4) resize(items.length / 4);

       return res;
   }

   public Iterator<Item> iterator() {
       return new QueueIterator();
   }

   private class QueueIterator implements Iterator<Item> {
       private int step = 0;
       public QueueIterator() {
           StdRandom.shuffle(items, 0, N);
       }

       public boolean hasNext() {
           return items[step] != null;
       }

       public void remove() { return; }

       public Item next() {
           Item item = items[step];
           step++;
           return item;
       }

   }

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

}
