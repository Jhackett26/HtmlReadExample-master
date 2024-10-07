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
    private JTextArea taURL; //typing area
    private JTextArea taSearchTerm;
    private int WIDTH = 800;
    private int HEIGHT = 700;
    String link = "";
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
        mainFrame.setLayout(new GridLayout(2, 1));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1,3));
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2,1));
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(2,1));
        printArea.setLineWrap(true);
        printArea.setWrapStyleWord(true);
        JLabel URLlabel = new JLabel("Please enter a valid URL above", JLabel.CENTER);
        JLabel SearchTermLabel = new JLabel("Please enter a search term above", JLabel.CENTER);

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

        taURL = new JTextArea();
        taURL.setBounds(50, 5, WIDTH - 100, HEIGHT - 50);
        taSearchTerm = new JTextArea();
        taSearchTerm.setBounds(50, 5, WIDTH - 100, HEIGHT - 50);
        mainFrame.add(mb);  //add menu bar
        mainFrame.add(panel1);
        panel1.add(panel2);
        panel2.add(taURL);//add typing area
        panel2.add(URLlabel);
        mainFrame.setJMenuBar(mb); //set menu bar
        printArea.setEditable(false);
        JScrollPane scrollPanel = new JScrollPane(printArea);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        panel1.add(controlPanel);
        panel1.add(panel3);
        panel3.add(taSearchTerm);
        panel3.add(SearchTermLabel);
        mainFrame.add(scrollPanel);
        mainFrame.setVisible(true);

    }

    private void showEventDemo() {

        JButton enterButton = new JButton("ENTER URL & SEARCH TERM");
        printArea.removeAll();
        enterButton.setActionCommand("ENTER");
        String defaultText = "Your links will show up here when you enter a URL and search term: \n \n";

        enterButton.addActionListener(new ButtonClickListener());

        controlPanel.add(enterButton, BorderLayout.CENTER);
        printArea.append(defaultText);
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            taURL.cut();
        if (e.getSource() == paste)
            taURL.paste();
        if (e.getSource() == copy)
            taURL.copy();
        if (e.getSource() == selectAll)
            taURL.selectAll();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            HtmlRead html = new HtmlRead();
            if (command.equals("ENTER")) {
                link = taURL.getText();
                String SearchTerm = taSearchTerm.getText();
                printedLink = html.readLink(link, SearchTerm);
                for(int i = 0;i<printedLink.size();i++){
                    printArea.append(printedLink.get(i)+"\n");
                }
            }
        }
    }
}