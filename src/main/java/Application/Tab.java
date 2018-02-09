package Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Tab extends JPanel {

    public static void createTabs(JFrame frame)
    {
        JTabbedPane tabbedpane = new JTabbedPane();
        JComponent pane1 = makePanel("Panel1",1);
        tabbedpane.addTab("Przeglad",pane1);

        JComponent pane2 = makePanel("Panel2",2);
        tabbedpane.addTab("Samoloty",pane2);

        JComponent pane3 = makePanel("Panel3",3);
        tabbedpane.addTab("Zlecenia",pane3);

        frame.add(tabbedpane);
    }

    private static JComponent makePanel(String text, int tab) {
        JPanel panel = new JPanel(true);
        String path = new String();
        JLabel filler = new JLabel();
        switch(tab)
        {
            case 1: path = "src/main/resources/background1.jpg"; break;
            case 2: path = "src/main/resources/background2.jpg"; break;
            case 3: path = "src/main/resources/background3.jpg"; break;
        }
        try{
            ImageIcon img = new ImageIcon(ImageIO.read(new File(path)));
            //filler = new JLabel(new ImageIcon(ImageIO.read(new File(path))));
            filler = new JLabel();
            filler.setIcon(img);
            //filler.setLayout(new GridBagLayout());
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Nie udalo sie wczytac tla");
        }
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout());
        panel.add(filler);
        panel.setOpaque(true);
        if(tab == 1) arrangepanel1(panel);
        if(tab == 2) arrangepanel2(panel);
        if(tab == 3) arrangepanel3(panel);

        return panel;
    }

    private static void arrangepanel1(JComponent panel)
    {
        GridBagConstraints c1 = new GridBagConstraints();
        JScrollPane scrollPane = new JScrollPane();
        JTable table = new JTable();
        scrollPane.add(table);
        JButton button = new JButton("Hi");
        c1.gridx = 1;
        c1.gridy = 0;
        c1.gridwidth = 1;
        c1.gridheight = 2;
        //c1.anchor = GridBagConstraints.NORTH;
        panel.add(button,c1);

    }

    private static void arrangepanel2(JComponent panel)
    {
        GridBagConstraints c2 = new GridBagConstraints();
    }

    private static void arrangepanel3(JComponent panel)
    {
        GridBagConstraints c3 = new GridBagConstraints();
    }


    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage()
    }

}
