import javax.swing.*;
import java.awt.*;

import static java.awt.Color.*;

class DrawGistPanel extends JPanel {

    MySkipList skipList;

    Color[] colors = new Color[]{BLUE, RED, GREEN};

    public DrawGistPanel(MySkipList skipList) {
        this.skipList = skipList;
    }

    @Override
    protected void paintComponent(Graphics gh) {
        Graphics2D drp = (Graphics2D) gh;

        double[] arrOfCount = skipList.createArrayOfValue();
        String[] arrOfWords = skipList.createArrayOfKey();

        for (int i = 0; i < arrOfWords.length; i++) {
            drp.drawLine(50, 400 - i * 50, arrOfCount.length * 110, 400 - i * 50);
            int vs = i * 5;
            drp.drawString(vs + "", 30, 400 - 50 * i);
        }

        for (int i = 0; i < skipList.n; i++) {
            drp.drawString(arrOfWords[i], 50 + i * 110, 420);
        }

        for (int i = 0; i < arrOfCount.length; i++) {
            drp.setColor(colors[i % 3]);
            int realY = (int) (400 - 10 * arrOfCount[i]);
            drp.fillRect(50 + 100 * i, realY, 20, (int) (arrOfCount[i] * 10));
        }
    }
}