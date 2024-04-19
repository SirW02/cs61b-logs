public class LinkedListDeque<anytype> {
    private Node sentinel;
    private int size;

    public class Node {
        public anytype item;
        public Node prev;
        public Node next;

        public Node(anytype item, Node prev, Node next) {
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
        this.sentinel = new Node(null,null);
        this.size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(anytype item) {
        size += 1;
        Node newNode = new Node(item, sentinel, null);
        if (sentinel.next == null) {
            newNode.next = sentinel;
        }else {
            newNode.next = sentinel.next;
            sentinel.next.prev = newNode;
        }
        sentinel.next = newNode;
    }

    public void addLast(anytype item) {
        size += 1;
        Node newNode = new Node(item, null, sentinel);
        if (sentinel.next == null) {
            newNode.prev = sentinel;
            sentinel.next = newNode;
        }else {
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

    public anytype removeFirst() {
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

    public anytype removeLast() {
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

    public anytype get(int index) {
        if (sentinel.next == sentinel || index > size) {
            return null;
        }
        Node temp = sentinel.next;
        while (index != 0){
            temp = temp.next;
            index -= 1;
        }
        return temp.item;
    }

    public  anytype getRecursive(int index) {
        return getR_Helper(sentinel.next, index);
    }

    private anytype getR_Helper(Node start, int index) {
        if (sentinel.next == sentinel) {
            return null;
        }else if (index == 0) {
            return start.item;
        }
        return getR_Helper(start.next, index-1);
    }
}