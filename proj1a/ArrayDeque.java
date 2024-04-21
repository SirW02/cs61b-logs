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
        if (newSize < items.length){
            if (nextFirst < nextLast) {
                for (int i = nextFirst, j = 0; i < nextLast && j < size; i++, j++) {
                    newItems[j] = items[i];
                }
            } else if (nextFirst > nextLast) {
                int j = 0;
                for (int i = nextFirst + 1; j < items.length - nextFirst - 1; i++, j++) {
                    newItems[j] = items[i];
                }
                for (int i = 0; j < size; i++, j++) {
                    newItems[j] = items[i];
                }
            }
            nextFirst = newSize - 1;
            nextLast = size;
            } else {
            for (int i = 0; i < nextLast; i++) {
                newItems[i] = items[i];
            }
            for (int i = newSize - 1, j = size - 1; j > nextFirst; i--, j--) {
                newItems[i] = items[j];
            }
            nextFirst = size + nextFirst;
        }
        items = newItems;
    }

    public void addFirst(T item) {
        if (size == items.length) {
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
        int count = 0;
        int ptr = plusOne(nextFirst);
        while (count != size) {
            System.out.print(items[ptr] + " ");
            ptr = plusOne(ptr);
            count++;
        }
    }

    public T get(int index) {
        if (index < 0 || index >= items.length) {
            return null;
        }
        return items[plusOne(nextFirst + index)];
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
            return items.length - 1;
        }
        return index - 1;
    }

    private  int plusOne(int index) {
        if (index >= items.length - 1) {
            return index % (items.length - 1);
        }
        return index + 1;
    }
public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addFirst(1);
        test.addLast(2);
        test.addFirst(0);
        test.addLast(3);
        test.addLast(4);
        test.addLast(5);
        test.addLast(6);
        test.addLast(7);
        test.printDeque();
        test.addLast(8);
        test.removeFirst();
        test.removeFirst();
        test.printDeque();
        test.removeLast();
        test.removeFirst();
        test.printDeque();
        System.out.print(test.get(2));
        test.removeFirst();
        test.removeLast();
        System.out.print(test.get(2));

    }
}

















