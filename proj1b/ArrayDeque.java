/** Second part of project1a
 * @param <T>
 * @author donny
 */
public class ArrayDeque<T> implements Deque<T> {
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
        if (plusOne(nextFirst) < nextLast) {
            for (int i = plusOne(nextFirst), j = 0; i < nextLast && j < size; i++, j++) {
                newItems[j] = items[i];
            }
        } else if (plusOne(nextFirst) >= nextLast) {
            int j = 0;
            for (int i = plusOne(nextFirst); j < items.length - nextFirst - 1; i++, j++) {
                newItems[j] = items[i];
            }
            for (int i = 0; j < size; i++, j++) {
                newItems[j] = items[i];
            }
        }
        nextFirst = newSize - 1;
        nextLast = size;
        items = newItems;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    @Override
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

    @Override
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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void printDeque() {
        int count = 0;
        int ptr = plusOne(nextFirst);
        while (count != size) {
            System.out.print(items[ptr] + " ");
            ptr = plusOne(ptr);
            count++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= items.length) {
            return null;
        }
        return items[plusOne(nextFirst + index)];
    }

    @Override
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
            return items.length - 1;
        }
        return index - 1;
    }

    private int plusOne(int index) {
        index %= items.length;
        if (index == items.length - 1) {
            return 0;
        }
        return index + 1;
    }
}

















