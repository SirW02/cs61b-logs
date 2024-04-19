/** Second part of project1a
 * @author donny
 * @param <T>
 */
public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;


    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private void resize(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            nextFirst = size + nextLast - 1;
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;

    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = items[plusOne(nextFirst)];
        items[plusOne(nextFirst)] = null;
        nextFirst = plusOne(nextFirst);
        size--;
        decreaseSize();
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = items[minusOne(nextLast)];
        items[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        size--;
        decreaseSize();
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        for (T item : items) {
            System.out.println(item);
        }
    }

    public T get(int index) {
        if (index < 0 || index >= items.length) {
            return null;
        }
        return items[plusOne(nextFirst) + index];
    }

    public int size() {
        return size;
    }

    private void decreaseSize() {
        while ((double) size / items.length < 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
    }

    private int minusOne(int index) {
        if (index == 0) {
            int len = items.length;
            return len - 1;
        }
        return index - 1;
    }

    private  int plusOne(int index) {
        if (index == items.length) {
            return 0;
        }
        return index + 1;
    }
}


















