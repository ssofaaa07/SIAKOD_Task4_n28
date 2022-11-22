import javax.swing.*;
import java.awt.*;

public class FormGist extends JFrame {
    private JPanel panelMain;
    private JPanel panel;


    public FormGist(String text) {
        super("Гистограмма частот слов");
        setContentPane(panelMain);
        MySkipList skipList = Logic.createSkipList(text);
        panelMain.add(new DrawGistPanel(skipList), BorderLayout.CENTER);
        panelMain.setBackground(Color.gray);
        setSize(skipList.n * 120, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
