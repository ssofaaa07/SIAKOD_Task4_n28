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
            while (node.right.key != MySkipListEntry.last && node.right.key.compareTo(key) >= 0
                    && node.right.key != MySkipListEntry.first) {
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
        n--;
        return oldValue;
    }

    @Override
    public Integer getValueOfMaxKey() {
        MySkipListEntry node = head;
        while (node.down != null) {
            node = node.down;
        }
        return node.right.value;
    }

    @Override
    public Integer getValueOfMaxLevel() {
        MySkipListEntry node = head;
        while (node.down != null && node.right.key == MySkipListEntry.last) {
            node = node.down;
        }
        node = node.right;
        while (node.down != null) {
            node = node.down;
        }
        return node.value;
    }

    public void printSkipList() {
        MySkipListEntry node = head;
        while (node.down != null) {
            node = node.down;
        }
        while (node != null) {
            MySkipListEntry node1 = node;
            System.out.print(node.key + " ");
            while (node1.key != MySkipListEntry.last && node1.up != null) {
                node1 = node1.up;
                System.out.print(node1.key + " ");
            }
            System.out.println();
            node = node.right;
        }
    }

    public double[] createArrayOfValue() {
        double[] values = new double[n];
        MySkipListEntry node = head;
        while (node.down != null) {
            node = node.down;
        }
        int i = 0;
        while (node.right.key != MySkipListEntry.last) {
            values[i] = node.right.value;
            node = node.right;
            i++;
        }
        return values;
    }

    public String[] createArrayOfKey() {
        String[] values = new String[n];
        MySkipListEntry node = head;
        while (node.down != null) {
            node = node.down;
        }
        int i = 0;
        while (node.right.key != MySkipListEntry.last) {
            values[i] = node.right.key;
            node = node.right;
            i++;
        }
        return values;
    }
}
