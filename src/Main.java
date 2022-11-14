public class Main {
    public static void main(String[] args) {
        MySkipList skipList = new MySkipList();
        for (int i = 1; i < 10; i++) {
            skipList.put(String.valueOf(i), i*100);
        }
        System.out.println(skipList.h + " " + skipList.n);
        System.out.println(skipList.get(String.valueOf(6)));
        skipList.printSkipList();
        System.out.println(skipList.remove(String.valueOf(7)));
        skipList.printSkipList();
        System.out.println(skipList.get(String.valueOf(8)));

    }
}