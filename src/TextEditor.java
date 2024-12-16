import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class TextEditor extends JFrame {
    JTextArea textArea;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu editMenu;
    JScrollPane scrollPane;
    Color fontColor;

    TextEditor(){
//        ----- About Frame -----
        this.setSize(700,700);
        this.setTitle("Untitled - TextEditor");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.getContentPane().setBackground(Color.WHITE);

//        ----- About MenuBar -----
//          ----- About Menu -----
//              ---- Open File
            JMenuItem openItem = new JMenuItem("Open");
            openItem.addActionListener(a ->{
                JFileChooser fileChooser = new JFileChooser(".");
                fileChooser.setFileFilter(new FileNameExtensionFilter("Text File","txt"));
                int response = fileChooser.showOpenDialog(null);
                if(response==JFileChooser.APPROVE_OPTION){
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    this.setTitle(file.getName() + " - TextEditor");
                    try (Scanner fileIn = new Scanner(file)) {
                        if (file.isFile()) {
                            while (fileIn.hasNext()) {
                                String line = fileIn.nextLine() + "\n";
                                textArea.append(line);
                            }
                        }
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

//              ----- Save File
            JMenuItem saveItem = new JMenuItem("Save");
            saveItem.addActionListener(a -> {
                JFileChooser fileChooser = new JFileChooser(".");
                fileChooser.setFileFilter(new FileNameExtensionFilter("Text File","txt"));
                fileChooser.setSelectedFile(new File("untitled.txt"));
                int response = fileChooser.showSaveDialog(null);
                if(response==JFileChooser.APPROVE_OPTION){
                    String fileName = fileChooser.getSelectedFile().getAbsolutePath();
                    if(!fileName.contains(".")){
                        fileName+=".txt";
                    }
                    File file = new File(fileName);
                    System.out.println(fileName);
                    try (PrintWriter fileOut = new PrintWriter(file)) {
                        fileOut.println(textArea.getText());
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

//              ---- Exit
            JMenuItem exitItem = new JMenuItem("Exit");
            exitItem.addActionListener(e -> System.exit(0));

            fileMenu = new JMenu("File");
            fileMenu.add(openItem);
            fileMenu.add(saveItem);
            fileMenu.add(exitItem);


            JMenuItem fontItem = new JMenuItem("Font");
            fontItem.addActionListener(e -> {
                new EditFont(textArea);
            });

            JMenuItem colorItem = new JMenuItem("Color");
            colorItem.addActionListener(e -> {
                fontColor = JColorChooser.showDialog(null,"Choose Color",textArea.getForeground());
                if(fontColor!=null){
                    textArea.setForeground(fontColor);
                }
            });

            editMenu = new JMenu("Edit");
            editMenu.add(fontItem);
            editMenu.add(colorItem);

        menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
//        ----- About TextArea -----
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial",Font.PLAIN,16));
        textArea.setLineWrap(true);
        textArea.setLineWrap(true);

//        ----- ScrollPane -----
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(680,630));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);

//        ----- Add to Frame ----
        this.setJMenuBar(menuBar);
        this.add(scrollPane);
        this.setVisible(true);
    }
}
