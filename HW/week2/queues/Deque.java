import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node sentinel;


    private class Node {
        Item item;
        Node prev;
        Node next;
        Node() {
            item = null;
            prev = null;
            next = null;
        }
        Node(Node p, Item i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    // construct an empty deque
    public Deque() {
        size = 0;
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        // Throw an IllegalArgumentException if the client calls with a null
        // argument.
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node first = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;

        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        // Throw an IllegalArgumentException if the client calls with a null
        // argument.
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node last = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;

        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        // Throw a java.util.NoSuchElementException if the client calls when
        // the deque is empty.
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item returnItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;

        size--;
        return returnItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        // Throw a java.util.NoSuchElementException if the client calls when
        // the deque is empty.
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item returnItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;

        size--;
        return returnItem;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        // Throw a java.util.NoSuchElementException if the client calls the
        // next() method in the iterator when there are no more items to return.
        private Node n;
        public DequeIterator() {
            n = sentinel.next;
        }

        @Override
        public void remove() {
            // Throw an UnsupportedOperationException if the client calls the
            // remove() method in the iterator.
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return n != sentinel;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item returnNode = n.item;
            n = n.next;
            return returnNode;
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> test = new Deque<>();
        for (int i = 0; i < 5; i++) {
            test.addFirst("A" + i);
        }
        for (int i = 0; i < 5; i++) {
            test.addLast("B" + i);
        }
        for (String s : test) {
            System.out.println(s);
        }
        System.out.println("test has " + test.size() + " elements in total");
        for (int i = 0; i < 6; i++) {
            System.out.println(test.removeFirst());
            System.out.println(test.removeLast());
            System.out.println(test.size());
        }
    }

    }
