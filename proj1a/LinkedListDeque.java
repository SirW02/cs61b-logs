/** First part of project1a
 * @author donny
 * @param <T>
 */
public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    public class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public Node(Node prev, Node next) {
            this.prev = prev;
            this.next = next;
        }
    }

    public LinkedListDeque() {
        this.sentinel = new Node(null, null);
        this.size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        size += 1;
        Node newNode = new Node(item, sentinel, null);
        if (sentinel.next == null) {
            newNode.next = sentinel;
        } else {
            newNode.next = sentinel.next;
            sentinel.next.prev = newNode;
        }
        sentinel.next = newNode;
    }

    public void addLast(T item) {
        size += 1;
        Node newNode = new Node(item, null, sentinel);
        if (sentinel.next == null) {
            newNode.prev = sentinel;
            sentinel.next = newNode;
        } else {
            newNode.prev = sentinel.prev;
            sentinel.prev.next = newNode;
            sentinel.prev = newNode;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {

        return size;
    }

    public void printDeque() {
        Node start = sentinel.next;
        while (start != sentinel) {
            System.out.print(start.item + " ");
            start = start.next;
        }
    }

    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        Node temp = sentinel.next;
        temp.next.prev = sentinel;
        temp.prev.next = temp.next;
        temp.prev = null;
        temp.next = null;
        size -= 1;
        return temp.item;
    }

    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        Node temp = sentinel.prev;
        temp.prev.next = sentinel;
        sentinel.prev = temp.prev;
        temp.prev = null;
        temp.next = null;
        size -= 1;
        return temp.item;
    }

    public T get(int index) {
        if (sentinel.next == sentinel || index > size) {
            return null;
        }
        Node temp = sentinel.next;
        while (index != 0) {
            temp = temp.next;
            index -= 1;
        }
        return temp.item;
    }

    public  T getRecursive(int index) {
        return getRHelper(sentinel.next, index);
    }

    private T getRHelper(Node start, int index) {
        if (sentinel.next == sentinel) {
            return null;
        } else if (index == 0) {
            return start.item;
        }
        return getRHelper(start.next, index - 1);
    }
}
