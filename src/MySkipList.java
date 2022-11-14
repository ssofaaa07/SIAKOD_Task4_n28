import java.util.Random;

public class MySkipList implements SkipList {

    public MySkipListEntry head;
    public MySkipListEntry tail;

    public int n;
    public int h;

    public Random r;


    public static class MySkipListEntry {
        String key;
        Integer value;

        MySkipListEntry up;
        MySkipListEntry down;
        MySkipListEntry right;
        MySkipListEntry left;

        public static final String first = (String) "-oo";
        public static final String last = (String) "+oo";

        public MySkipListEntry(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    public MySkipList() {

        MySkipListEntry node1 = new MySkipListEntry(MySkipListEntry.first, null);
        MySkipListEntry node2 = new MySkipListEntry(MySkipListEntry.last, null);

        node1.right = node2;
        node2.left = node1;

        this.head = node1;
        this.tail = node2;

        this.n = 0;
        this.h = 0;
        this.r = new Random();
    }

    @Override
    public MySkipListEntry findEntry(String key) {
        MySkipListEntry node = head;
        while (true) {
            while (node.right.key != MySkipListEntry.last && node.right.key.compareTo(key) >= 0) {
                node = node.right;
            }
            if (node.down != null) {
                node = node.down;
            } else {
                break;
            }
        }
        return node;
    }

    @Override
    public Integer get(String key) {
        MySkipListEntry node = findEntry(key);
        if (node.key.equals(key)) {
            return node.value;
        } else {
            return null;
        }
    }

    @Override
    public Integer put(String key, Integer value) {
        MySkipListEntry node = findEntry(key);

        if (node.key.compareTo(key) == 0) {
            Integer oldValue = node.value;
            node.value = value;
            return oldValue;
        }

        MySkipListEntry newNode = new MySkipListEntry(key, value);
        newNode.left = node;
        newNode.right = node.right;
        node.right.left = newNode;
        node.right = newNode;

        int i = 0;
        while (r.nextBoolean()) {
            if (i >= h) {
                addEmptyLevel();
            }
            while (node.up == null) {
                node = node.left;
            }
            node = node.up;

            MySkipListEntry newNodeUp = new MySkipListEntry(key, null);
            newNodeUp.left = node;
            newNodeUp.right = node.right;
            node.right.left = newNodeUp;
            node.right = newNodeUp;

            newNodeUp.down = newNode;
            newNode.up = newNodeUp;

            newNode = newNodeUp;
            i++;
        }
        n++;
        return null;
    }

    private void addEmptyLevel() {
        MySkipListEntry node1 = new MySkipListEntry(MySkipListEntry.first, null);
        MySkipListEntry node2 = new MySkipListEntry(MySkipListEntry.last, null);

        node1.right = node2;
        node2.left = node1;
        node1.down = head;
        node2.down = tail;

        head.up = node1;
        tail.up = node2;

        head = node1;
        tail = node2;

        h++;
    }

    @Override
    public Integer remove(String key) {


//        MySkipListEntry node = head;
//
//        while (true) {
//            while (node.right.key != MySkipListEntry.last && node.right.key.compareTo(key) >= 0) {
//                node = node.right;
//            }
//            if (node.down != null) {
//                node = node.down;
//            }
//            if (node.right.key.compareTo(key) == 0) {
//                break;
//            }
//        }
//        Integer oldValue = node.value;
//        MySkipListEntry deleteNode;
//
//        while (node != null) {
//            deleteNode = node.down;
//            node.right.left = node.left;
//            node.left.right = node.right;
//            node = deleteNode;
//        }

        MySkipListEntry node = findEntry(key);
        if (!node.key.equals(key)) {
            return null;
        }

        Integer oldValue = node.value;

        MySkipListEntry deleteNode = node;

        while (node != null) {
            deleteNode = node.up;
            node.right.left = node.left;
            node.left.right = node.right;
            node = deleteNode;
        }
        return oldValue;
    }

    @Override
    public Integer getMaxValue() {
        return null;
    }

    @Override
    public Integer getValueOfMaxLevel() {
        return null;
    }

    public void printSkipList() {
        for (int i = 1; i < n + 1; i++) {
            MySkipListEntry node = findEntry(String.valueOf(i));
            while (node != null) {
                System.out.print(node.key + " ");
                node = node.up;
            }
            System.out.println();
        }
    }
}
