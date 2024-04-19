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
        nextFirst = (nextFirst - 1) % items.length;
        size++;

    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        nextFirst = (nextFirst + 1) % items.length;
        decreaseSize();
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = items[nextLast - 1];
        items[nextLast - 1] = null;
        nextLast = (nextLast - 1) % items.length;
        decreaseSize();
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        for(T item : items) {
            System.out.println(item);
        }
    }

    public T get(int index) {
        return items[index];
    }

    public int size() {
        return size;
    }

    private void decreaseSize() {
        while ((double) size / items.length < 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
    }
}


















