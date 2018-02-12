package Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tab extends JPanel {

    private static BufferedImage img;

    public static void createTabs(JFrame frame)
    {
        JTabbedPane tabbedpane = new JTabbedPane();

        JComponent pane1 = makePanel("Panel1",1);
        tabbedpane.addTab("Przeglad",pane1);
        //frame.setContentPane(pane1);

        JComponent pane2 = makePanel("Panel2",2);
        tabbedpane.addTab("Samoloty",pane2);

        JComponent pane3 = makePanel("Panel3",3);
        tabbedpane.addTab("Zlecenia",pane3);

        frame.add(tabbedpane);
    }

    private static JPanel makePanel(String text, int tab) {
        JPanel panel = new JPanel();
        String path = new String();
        BackgroundPane background = null;
        switch(tab)
        {
            case 1: path = "src/main/resources/background1.jpg"; break;
            case 2: path = "src/main/resources/background2.jpg"; break;
            case 3: path = "src/main/resources/background3.jpg"; break;
        }
        try{
            img = ImageIO.read(new File(path));
            background = new BackgroundPane();
            background.setBackground(img);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Nie udalo sie wczytac tla");
        }
        //filler.setHorizontalAlignment(JLabel.CENTER);
        //panel.setLayout(new GridLayout());
       // panel.add(filler);
        panel.setOpaque(true);
        if(tab == 1) arrangepanel1(background);
        if(tab == 2) arrangepanel2(background);
        if(tab == 3) arrangepanel3(background);

        return background;
    }

    private static void arrangepanel1(JPanel panel)
    {
        //panel.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(Application.getAirportsVectors(),Application.getAirportHeaders());
        //table.setPreferredScrollableViewportSize(new Dimension(500,500));
        //table.setIntercellSpacing(new Dimension(5,5));
        table.setGridColor(new Color(20,200,50));
        table.setModel(model);
        //table.setBounds(50, 50, 200, 400);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        JButton button = new JButton("Hello");
        JTextField field = new JTextField("Tu wpisz tekst");

       // c1.gridwidth = 8;
        //c1.gridheight = 16;
        c1.weighty = 4;
        c1.weightx = 2;
        c1.anchor = GridBagConstraints.NORTHWEST;
        c1.gridx = 0;
        c1.gridy = 2;
        c1.ipady = 3;
        c1.insets = new Insets(10,20,30,40);
        c1.fill = GridBagConstraints.FIRST_LINE_START;
        panel.add(scrollPane,c1);

    }

    private static void arrangepanel2(JComponent panel)
    {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        JScrollPane scrollPane = new JScrollPane();
        JTable table = new JTable();
        scrollPane.add(table);
        JButton button = new JButton("Helllloo");
        JTextField field = new JTextField("Tu wpisz tekst");
        c1.gridx = 1;
        c1.gridy = 0;
        c1.gridwidth = 8;
        c1.gridheight = 16;
        panel.add(button,c1);
        //c1.anchor = GridBagConstraints.NORTH;
        c1.gridx = 2;
        c1.gridy = 2;
        panel.add(field,c1);
    }

    private static void arrangepanel3(JComponent panel)
    {
        GridBagConstraints c3 = new GridBagConstraints();
    }


    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(img, 0,0,null);
    }


    public static class BackgroundPane extends JPanel {

        private BufferedImage img;

        public BackgroundPane() {
            this.setLayout(new GridBagLayout());
        }

        public void setBackground(BufferedImage value) {
            if (value != img) {
                this.img = value;
                repaint();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, this);
        }
    }

}
