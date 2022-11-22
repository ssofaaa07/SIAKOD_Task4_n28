import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
//        MySkipList skipList = new MySkipList();
//        for (int i = 1; i < 10; i++) {
////            int index = (int) (Math.random()* 10);
//            int index = i;
//            skipList.put(String.valueOf(index), index*100);
//        }
//        System.out.println(skipList.h + " " + skipList.n);
//        System.out.println(skipList.get(String.valueOf(6)));
//        skipList.printSkipList();
//        System.out.println(skipList.remove(String.valueOf(7)));
//        System.out.println(skipList.h + " " + skipList.n);
//        skipList.printSkipList();
//        System.out.println(skipList.get(String.valueOf(8)));
//        System.out.println(skipList.getValueOfMaxKey());
//        System.out.println(skipList.getValueOfMaxLevel());
//
//        double[] values = skipList.createArrayOfValue();
//        for (int i = 0; i < values.length; i++) {
//            System.out.print(values[i] + " ");
//        }

        Locale.setDefault(Locale.ROOT);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMain().setVisible(true);
            }
        });
    }
}