/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    private class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Added item is not null");

        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = null;
        first.prev = null;
        if (isEmpty()) last = first;
        else {
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Added item is not null");

        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        last.prev = null;
        if (isEmpty()) first = last;
        else {
            oldlast.next = last;
            last.prev = oldlast;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        // first.prev = null;
        n--;
        if (isEmpty()) last = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        // last.next = null;
        n--;
        if (isEmpty()) first = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedDequeue();
    }

    private class LinkedDequeue implements Iterator<Item> {
        private Node<Item> current;

        public LinkedDequeue() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        In in = new In(args[0]);
        Deque<String> q = new Deque<String>();
        while (!in.isEmpty()) {
            String s = in.readString();
            // if (i % 2 == 0) q.addFirst(s);
            // else
            q.addLast(s);
        }
        Iterator<String> list = q.iterator();
        while (list.hasNext()) {
            String s = list.next();
            StdOut.print(s + " ");
        }
        q.removeLast();
        q.removeFirst();
        list = q.iterator();
        while (list.hasNext()) {
            String s = list.next();
            StdOut.print(s + " ");
        }
    }
}
