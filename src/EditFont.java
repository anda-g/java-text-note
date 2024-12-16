import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditFont extends JFrame {
    JComboBox<String> fontChooser;
    JSpinner fontSizeChooser;
    JLabel font;
    JLabel fontSize;
    JButton button;
    JPanel preview;
    JLabel previewText;
    JRadioButton regular;
    JRadioButton bold;
    JRadioButton italic;
    int fontStyle;
    EditFont(JTextArea textArea){
//        Create a Frame
        this.setSize(420,210);
        this.setTitle("Font Chooser");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new FlowLayout());


//        About Choosing Font
        font = new JLabel("Font : ");
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontChooser = new JComboBox<>(fonts);
        fontChooser.setEditable(true);
        fontChooser.setSelectedItem(textArea.getFont().getFamily());

//        About Choosing Font Size
        fontSize = new JLabel("Font Size : ");
        fontSizeChooser = new JSpinner(new SpinnerNumberModel(textArea.getFont().getSize(),12,100,2));
        fontSizeChooser.setPreferredSize(new Dimension(40,25));
        fontSizeChooser.setFocusable(false);

//        These are style of font
        regular = new JRadioButton("Regular");
        regular.addActionListener(e -> fontStyle =0);
        regular.setBorder(null);

        bold = new JRadioButton("Bold");
        bold.addActionListener(e -> fontStyle=1);
        bold.setBorder(null);

        italic = new JRadioButton("Italic");
        italic.addActionListener(e -> fontStyle=2);
        italic.setBorder(null);

//        Mixed the style box into a group
        ButtonGroup group = new ButtonGroup();
        group.add(regular);
        group.add(bold);
        group.add(italic);

        switch (textArea.getFont().getStyle()){
            case Font.PLAIN ->{
                group.setSelected(regular.getModel(),true);
                fontStyle=0;
            }
            case Font.BOLD ->{
                group.setSelected(bold.getModel(),true);
                fontStyle=1;
            }
            case Font.ITALIC ->{
                group.setSelected(italic.getModel(),true);
                fontStyle=2;
            }
        }

//        About previewing text label
        previewText = new JLabel();
        previewText.setText("The text is for previewing...");
        previewText.setHorizontalAlignment(JLabel.CENTER);
        previewText.setFont(new Font(textArea.getFont().getFontName(),textArea.getFont().getStyle(),textArea.getFont().getSize()));
        fontSizeChooser.addChangeListener(e -> {
            previewText.setFont(new Font((String) fontChooser.getSelectedItem(),fontStyle,(int) fontSizeChooser.getValue()));
        });
        fontChooser.addItemListener(e -> {
            previewText.setFont(new Font((String) fontChooser.getSelectedItem(),fontStyle,(int) fontSizeChooser.getValue()));
        });
        regular.addChangeListener(e -> {
            previewText.setFont(new Font((String) fontChooser.getSelectedItem(),fontStyle,(int) fontSizeChooser.getValue()));
        });
        bold.addChangeListener(e -> {
            previewText.setFont(new Font((String) fontChooser.getSelectedItem(),fontStyle,(int) fontSizeChooser.getValue()));
        });
        italic.addChangeListener(e -> {
            previewText.setFont(new Font((String) fontChooser.getSelectedItem(),fontStyle,(int) fontSizeChooser.getValue()));
        });

//        About preview panel that add a text label
        preview = new JPanel();
        preview.setPreferredSize(new Dimension(350,70));
        preview.setBackground(Color.WHITE);
        preview.setLayout(new BorderLayout());
        preview.setBorder(BorderFactory.createEtchedBorder());
        preview.add(previewText);

//        About Button to apply all style
        button = new JButton("Apply");
        button.setFocusable(false);
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.WHITE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(null);
            }
        });
        button.addActionListener(e -> {
            fontSizeChooser.setValue(fontSizeChooser.getValue());
            fontChooser.setSelectedItem(fontChooser.getSelectedItem());
            textArea.setFont(new Font((String) fontChooser.getSelectedItem(),fontStyle,(int) fontSizeChooser.getValue()));
            this.dispose();
        });

//        Adding to frame
        this.add(font);
        this.add(fontChooser);
        this.add(fontSize);
        this.add(fontSizeChooser);
        this.add(regular);
        this.add(bold);
        this.add(italic);
        this.add(preview);
        this.add(button);
        this.setVisible(true);
    }
}
