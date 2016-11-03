import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
    public static void main(String[] args) {

        String[] data;
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);

        String line = StdIn.readLine();
        String[] s = line.split("\\s+");
        data = s;

        for (String ss : data) {
            queue.enqueue(ss);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(queue.dequeue());
        }

    }
}
