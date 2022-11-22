import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Logic {

    public static MySkipList createSkipList(String text) {
        String pat = ".,/!@#$%^1234567890`><-.";
        for (int i = 0; i < pat.length() - 1; i++) {
            if (text.contains(pat.substring(i, i + 1))) {
                text = text.replace(pat.substring(i, i + 1), "").toLowerCase();
            }
        }

        System.out.println(text);

        String[] list = text.split(" ");
        MySkipList skipList = new MySkipList();

        for (String str : list) {
            if (!str.equals("")) {
                if (skipList.get(str) != null) {
                    skipList.put(str, skipList.get(str) + 1);
                } else {
                    skipList.put(str, 1);
                }
            }
        }
        return skipList;
    }

    public static String readFromFile(String fileName) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        String lines;
        try (Scanner scanner = new Scanner(new File(fileName), "UTF-8")) {
            while (scanner.hasNext()) {
                sb.append(scanner.nextLine());
            }
        }
        lines = sb.toString();
        return lines;
    }
}
