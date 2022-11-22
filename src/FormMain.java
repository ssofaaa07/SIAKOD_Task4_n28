import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class FormMain extends JFrame {
    private JTextField textFieldTask;
    private JButton buttonDo;
    private JButton buttonInputFromFile;
    private JButton buttonClean;
    private JPanel panelMain;
    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;

    public FormMain() {
        this.setTitle("AccountFrame");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        JMenuBar menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        textFieldTask.setText("Ваня, Соня ела кашу, кашу ела Соня. Вот такая история, Соня");

        buttonDo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(textFieldTask.getText());
                FormGist gr = new FormGist(textFieldTask.getText());
                gr.setVisible(true);
            }
        });

        buttonInputFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    try {
                        textFieldTask.setText(Logic.readFromFile(fileChooserOpen.getSelectedFile().getPath()));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        buttonClean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldTask.setText("");
            }
        });
    }


}
