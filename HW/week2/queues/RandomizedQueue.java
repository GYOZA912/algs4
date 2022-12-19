import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] arr;


    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        arr = (Item[]) new Object[8];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        arr[size++] = item;
        if (arr.length == size) {
            resize(size * 2);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int rdm = StdRandom.uniformInt(0, size);
        Item returnItem = arr[rdm];
        arr[rdm] = arr[size - 1];
        arr[--size] = null;
        if (size > 4 && size < arr.length / 4) {
            resize(arr.length / 4);
        }
        return returnItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int rdm = StdRandom.uniformInt(0, size);
        return arr[rdm];

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    private class RandomizedQueueIterator implements Iterator<Item> {
        Item[] cpy;
        int cpySize;
        public RandomizedQueueIterator() {
            cpy = (Item[]) new Object[size];
            cpySize = size;
            for (int i = 0; i < size; i++) {
                cpy[i] = arr[i];
            }
            StdRandom.shuffle(cpy);
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        @Override
        public boolean hasNext() {
            return cpySize != 0;
        }
        @Override
        public Item next() {
            if (cpySize == 0) {
                throw new NoSuchElementException();
            }
            return cpy[--cpySize];
        }



    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> test = new RandomizedQueue<>();
        for (int i = 0; i < 5; i++) {
            test.enqueue("allo" + i);
        }
        for (String i : test) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < 6; i++) {
            System.out.print(test.sample() + " ");
            System.out.print(test.dequeue() + "\n");
        }
    }

}