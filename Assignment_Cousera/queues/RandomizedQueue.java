/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    // private int head;
    // private int tale;
    private Item[] q;

    // construct an empty randomized queue
    public RandomizedQueue() {
        // head = tale = 0;
        n = 0;
        q = (Item[]) new Object[1001];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        q[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int index = StdRandom.uniformInt(n);
        Item item = q[index];
        for (int i = index; i <= n - 2; i++) q[i] = q[i + 1];
        q[--n] = null;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int index = StdRandom.uniformInt(n);
        Item item = q[index];
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListRandomQueue();
    }

    private class ListRandomQueue implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[i++];
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        In in = new In(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int i = 0;
        while (!in.isEmpty()) {
            String s = in.readString();
            q.enqueue(s);
        }
        StdOut.println(q.dequeue() + " ,Sample " + q.sample());
        Iterator<String> list = q.iterator();
        while (list.hasNext()) {
            String s = list.next();
            StdOut.println(s + " ");
        }
    }
}
