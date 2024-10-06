import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class Project implements ActionListener {
    private JFrame mainFrame;
    private JTextArea printArea;
    private JPanel controlPanel;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextArea ta; //typing area
    private int WIDTH = 800;
    private int HEIGHT = 700;
    String link = "";
    int rows = 1;
    ArrayList printedLink = new ArrayList<>();



    public Project() {
        prepareGUI();
    }

    public static void main(String[] args) {
        Project swingControlDemo = new Project();
        swingControlDemo.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("John learning SWING");
        printArea = new JTextArea();
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(3, 1));
        JScrollPane scrollPane = new JScrollPane(printArea);
        printArea.setLineWrap(true);
        printArea.setWrapStyleWord(true);

        //menu at top
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        mb.add(file);
        mb.add(edit);
        mb.add(help);
        //end menu at top

        ta = new JTextArea();
        ta.setBounds(50, 5, WIDTH - 100, HEIGHT - 50);
        mainFrame.add(mb);  //add menu bar
        mainFrame.add(ta);//add typing area
        mainFrame.setJMenuBar(mb); //set menu bar


        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout()); //set the layout of the pannel

        mainFrame.add(controlPanel);
        mainFrame.add(printArea);
        mainFrame.setVisible(true);
    }

    private void showEventDemo() {

        JButton enterButton = new JButton("ENTER");
        enterButton.setActionCommand("ENTER");
        JLabel defaultText = new JLabel("Please Enter a Link");

        enterButton.addActionListener(new ButtonClickListener());

        Scanner scanner = new Scanner(System.in);

        controlPanel.add(enterButton, BorderLayout.CENTER);
        if (link.isEmpty()) {
            printArea.add(defaultText);
        }
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
        if (e.getSource() == selectAll)
            ta.selectAll();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            HtmlRead html = new HtmlRead();
            if (command.equals("ENTER")) {
                link = ta.getText();
                printedLink = html.readLink(link);
                for(int i = 0;i<printedLink.size();i++){
                    printArea.append(printedLink.get(i)+"\n");
                }
            }
        }
    }
}