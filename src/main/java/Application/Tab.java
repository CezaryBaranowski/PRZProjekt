package Application;

import com.sun.javafx.binding.StringConstant;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tab extends JPanel {

    private static BufferedImage img;
    private static JTabbedPane tabbedpane = new JTabbedPane();
    private static JPanel pane1 = new JPanel();
    private static JPanel pane2 = new JPanel();
    private static JPanel pane3 = new JPanel();
    private static JFrame framee;

    public Tab(JFrame frame)
    {
        framee = frame;
        createTabs(framee);
    }

    public static void createTabs(JFrame frame)
    {
        //JTabbedPane tabbedpane = new JTabbedPane();

        //tabbedpane.removeAll();
        pane1 = makePanel("Panel1",1);
        tabbedpane.addTab("OVERVIEW",pane1);

        pane2 = makePanel("Panel2",2);
        tabbedpane.addTab("PLANES",pane2);

        pane3 = makePanel("Panel3",3);
        tabbedpane.addTab("ORDERS",pane3);

        frame.add(tabbedpane);
        //frame.pack();
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
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();

        JLabel titleLabel = new JLabel("OVERVIEW");
        titleLabel.setFont(new Font("Calibri",Font.PLAIN,40));
        titleLabel.setForeground(Color.GREEN);
        panel.add(titleLabel,c1);


        JLabel boughtPlanesLabel = new JLabel("YOUR PLANES");
        boughtPlanesLabel.setFont(new Font("Calibri",Font.PLAIN,28));
        boughtPlanesLabel.setForeground(Color.GREEN);
        c1.anchor = GridBagConstraints.NORTHWEST;
        c1.weighty = 6;
        c1.gridy = 1;
        c1.fill = GridBagConstraints.VERTICAL;
        c1.insets = new Insets(120,20,0,0);
        panel.add(boughtPlanesLabel,c1);


        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(Application.getVectorsFromPlanes(Simulation.getBoughtPlanes()),Application.getPlaneHeaders());
      //  table.setPreferredScrollableViewportSize(new Dimension(600,120));
        table.setGridColor(new Color(20,200,50));
    //    table.setPreferredSize(new Dimension(800,250));
        table.setModel(model);
        c1.weightx = 2;
        c1.gridwidth = 3;
        c1.gridheight = 1;
        c1.anchor = GridBagConstraints.NORTHWEST;
        c1.gridy = 2;
        c1.insets = new Insets(0,20,0,0);
        c1.fill = GridBagConstraints.BOTH;
        JScrollPane scrollPane = new JScrollPane(table);
      //  scrollPane.setPreferredSize(new Dimension(d.width,table.getRowHeight()*table.getRowCount()));
        panel.add(scrollPane,c1);


        JLabel acceptedOrdersLabel = new JLabel("ACCEPTED ORDERS");
        acceptedOrdersLabel.setFont(new Font("Calibri",Font.PLAIN,28));
        acceptedOrdersLabel.setForeground(Color.GREEN);
        c1.anchor = GridBagConstraints.NORTHWEST;
        c1.gridy = 3;
        c1.insets = new Insets(20,20,0,0);
        panel.add(acceptedOrdersLabel,c1);


        JTable table2 = new JTable();
        DefaultTableModel model2 = new DefaultTableModel(Application.getVectorsFromOrders(Simulation.getTakenFlightOrders()),Application.getOrdersHeaders());
       // table2.setPreferredScrollableViewportSize(new Dimension(600,120));
        table2.setGridColor(new Color(20,200,50));
        table2.setModel(model2);
        table2.setShowGrid(true);
        JScrollPane scrollPane2 = new JScrollPane(table2);
  //      scrollPane2.setPreferredSize(new Dimension(d.width,table2.getRowHeight()*table2.getRowCount()+1));
        c1.anchor = GridBagConstraints.NORTHWEST;
        c1.gridy = 4;
        c1.insets = new Insets(0,20,20,0);
        panel.add(scrollPane2,c1);


        JLabel dayLabel = new JLabel();
        dayLabel.setText("DAY " + Simulation.getDay().toString());
        dayLabel.setFont(new Font("Calibri",Font.PLAIN,28));
        dayLabel.setForeground(Color.orange);
        c1.anchor = GridBagConstraints.NORTH;
        c1.gridy = 1;
        c1.fill = GridBagConstraints.VERTICAL;
        c1.insets = new Insets(0,0,0,20);
        panel.add(dayLabel,c1);


        JLabel balanceLabel = new JLabel();
        balanceLabel.setText("BALANCE " + Simulation.getBalance().toString());
        balanceLabel.setFont(new Font("Calibri",Font.PLAIN,32));
        c1.anchor = GridBagConstraints.NORTH;
        c1.gridy = 0;
        c1.fill = GridBagConstraints.VERTICAL;
        c1.insets = new Insets(0,0,0,20);
        panel.add(balanceLabel,c1);


        JButton nextDayButton = new JButton("NEXT DAY");
        c1.anchor = GridBagConstraints.SOUTHEAST;
        c1.gridy = 0;
        c1.gridheight = 1;
        c1.fill = GridBagConstraints.VERTICAL;
        c1.insets = new Insets(20,0,0,20);
        panel.add(nextDayButton,c1);

    }





    private static void arrangepanel2(JComponent panel)
    {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        JLabel titleLabel = new JLabel("PLANES");
        titleLabel.setFont(new Font("Calibri",Font.PLAIN,40));
        titleLabel.setForeground(Color.CYAN);
        c2.anchor = GridBagConstraints.FIRST_LINE_START;
        panel.add(titleLabel,c2);


        JLabel boughtPlanesLabel = new JLabel("YOUR PLANES");
        boughtPlanesLabel.setFont(new Font("Calibri",Font.PLAIN,28));
        boughtPlanesLabel.setForeground(Color.GREEN);
        c2.anchor = GridBagConstraints.NORTHWEST;
        c2.weighty = 6;
        c2.weightx = 2;
        c2.gridy = 0;
        c2.gridx = 0;
       // c2.gridwidth = 3;
        c2.fill = GridBagConstraints.VERTICAL;
        c2.insets = new Insets(120,20,0,0);
        panel.add(boughtPlanesLabel,c2);


        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(Application.getVectorsFromPlanes(Simulation.getBoughtPlanes()),Application.getPlaneHeaders());
        //  table.setPreferredScrollableViewportSize(new Dimension(600,120));
        table.setGridColor(new Color(20,200,50));
        table.setModel(model);
        c2.weighty = 6;
        c2.weightx = 2;

        c2.anchor = GridBagConstraints.NORTHWEST;
        c2.gridy = 1;
        c2.gridwidth = 3;
        c2.gridheight = 1;
        c2.fill = GridBagConstraints.BOTH;
        c2.insets = new Insets(0,20,120,0);
        JScrollPane scrollPane = new JScrollPane(table);
        //scrollPane.setPreferredSize(new Dimension(d.width,table.getRowHeight()*table.getRowCount()+1));
        panel.add(scrollPane,c2);


        JLabel acceptedOrdersLabel = new JLabel("AVAILABLE PLANES");
        acceptedOrdersLabel.setFont(new Font("Calibri",Font.PLAIN,28));
        acceptedOrdersLabel.setForeground(Color.GREEN);
        c2.weighty = 6;
        c2.weightx = 2;
        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.NORTHWEST;
        c2.gridy = 1;
        c2.insets = new Insets(240,20,0,0);
        panel.add(acceptedOrdersLabel,c2);


        JTable table2 = new JTable();
        DefaultTableModel model2 = new DefaultTableModel(Application.getVectorsFromPlanes(Simulation.getAvailablePlanes()),Application.getPlaneHeaders());
        table2.setPreferredScrollableViewportSize(new Dimension(600,120));
        table2.setGridColor(new Color(20,200,50));
        table2.setModel(model2);
        table2.setShowGrid(true);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        scrollPane2.setPreferredSize(new Dimension(600,150));
      //  scrollPane2.setPreferredSize(new Dimension(d.width,table2.getRowHeight()*table2.getRowCount()+1));
        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.NORTHWEST;
        c2.gridy = 3;
        c2.insets = new Insets(0,20,20,0);
        panel.add(scrollPane2,c2);


        JLabel dayLabel = new JLabel();
        dayLabel.setText("DAY " + Simulation.getDay().toString());
        dayLabel.setFont(new Font("Calibri",Font.PLAIN,28));
        dayLabel.setForeground(Color.orange);
        c2.anchor = GridBagConstraints.NORTH;
        c2.gridy = 0;
        c2.gridx = 1;
        c2.fill = GridBagConstraints.VERTICAL;
        c2.insets = new Insets(20,0,150,20);
        panel.add(dayLabel,c2);


        JLabel balanceLabel = new JLabel();
        balanceLabel.setText("BALANCE " + Simulation.getBalance().toString());
        balanceLabel.setFont(new Font("Calibri",Font.PLAIN,32));
        c2.anchor = GridBagConstraints.NORTH;
        c2.gridy = 0;
        c2.gridx = 1;
        c2.fill = GridBagConstraints.VERTICAL;
        c2.insets = new Insets(0,0,0,20);
        panel.add(balanceLabel,c2);


        JButton nextDayButton = new JButton("NEXT DAY");
        nextDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Simulation.dailyUpdate();

                pane1.removeAll();
                pane2.removeAll();
                arrangepanel1(pane1);
                arrangepanel2(pane2);

            }

        });

        c2.anchor = GridBagConstraints.NORTHEAST;
        c2.gridy = 0;
        c2.gridheight = 1;
        c2.weighty = 6;
        c2.fill = GridBagConstraints.VERTICAL;
        c2.insets = new Insets(20,0,100,20);
        panel.add(nextDayButton,c2);


        JButton buyButton = new JButton("BUY");
        c2.fill = GridBagConstraints.NONE;
        c2.anchor = GridBagConstraints.WEST;
        c2.gridheight = 1;
        c2.gridx = 0;
        c2.gridy = 1;
        c2.gridwidth = 1;
        c2.insets = new Insets(230,260,0,0);
        panel.add(buyButton,c2);

    }




    private static void arrangepanel3(JComponent panel)
    {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c3 = new GridBagConstraints();
        JLabel titleLabel = new JLabel("ORDERS");
        titleLabel.setFont(new Font("Calibri",Font.PLAIN,40));
        titleLabel.setForeground(Color.RED);
        c3.anchor = GridBagConstraints.FIRST_LINE_START;
        panel.add(titleLabel,c3);


        JLabel boughtPlanesLabel = new JLabel("YOUR ORDERS");
        boughtPlanesLabel.setFont(new Font("Calibri",Font.PLAIN,28));
        boughtPlanesLabel.setForeground(Color.RED);
        c3.anchor = GridBagConstraints.NORTHWEST;
        c3.weighty = 6;
        c3.weightx = 2;
        c3.gridy = 0;
        c3.gridx = 0;
        c3.gridwidth = 3;
        c3.fill = GridBagConstraints.BOTH;
        c3.insets = new Insets(10,20,50,0);
        panel.add(boughtPlanesLabel,c3);


        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(Application.getVectorsFromOrders(Simulation.getTakenFlightOrders()),Application.getOrdersHeaders());
        //  table.setPreferredScrollableViewportSize(new Dimension(600,120));
        table.setGridColor(new Color(20,200,50));
        table.setModel(model);
        c3.weighty = 6;
        c3.weightx = 2;

        c3.anchor = GridBagConstraints.NORTH;
        c3.gridy = 0;
        c3.gridwidth = 3;
        c3.gridheight = 1;
        c3.fill = GridBagConstraints.BOTH;
        c3.insets = new Insets(200,20,0,0);
        JScrollPane scrollPane = new JScrollPane(table);
        //scrollPane.setPreferredSize(new Dimension(d.width,table.getRowHeight()*table.getRowCount()+1));
        panel.add(scrollPane,c3);


        JLabel acceptedOrdersLabel = new JLabel("AVAILABLE ORDERS");
        acceptedOrdersLabel.setFont(new Font("Calibri",Font.PLAIN,28));
        acceptedOrdersLabel.setForeground(Color.RED);
        c3.anchor = GridBagConstraints.NORTHWEST;
        c3.gridy = 1;
        c3.insets = new Insets(0,20,180,0);
        panel.add(acceptedOrdersLabel,c3);


        JTable table2 = new JTable();
        DefaultTableModel model2 = new DefaultTableModel(Application.getVectorsFromOrders(Simulation.getAvailableFlightOrders()),Application.getOrdersHeaders());
        table2.setPreferredScrollableViewportSize(new Dimension(600,120));
        table2.setGridColor(new Color(20,200,50));
        table2.setModel(model2);
        table2.setShowGrid(true);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        scrollPane2.setPreferredSize(new Dimension(600,150));
        //  scrollPane2.setPreferredSize(new Dimension(d.width,table2.getRowHeight()*table2.getRowCount()+1));
        c3.anchor = GridBagConstraints.NORTHWEST;
        c3.gridy = 1;
        c3.insets = new Insets(100,20,0,0);
        panel.add(scrollPane2,c3);


        JLabel dayLabel = new JLabel();
        dayLabel.setText("DAY " + Simulation.getDay().toString());
        dayLabel.setFont(new Font("Calibri",Font.PLAIN,28));
        dayLabel.setForeground(Color.orange);
        c3.anchor = GridBagConstraints.NORTH;
        c3.gridy = 0;
        c3.gridx = 1;
        c3.fill = GridBagConstraints.VERTICAL;
        c3.insets = new Insets(20,0,150,20);
        panel.add(dayLabel,c3);


        JLabel balanceLabel = new JLabel();
        balanceLabel.setText("BALANCE " + Simulation.getBalance().toString());
        balanceLabel.setFont(new Font("Calibri",Font.PLAIN,32));
        c3.anchor = GridBagConstraints.NORTH;
        c3.gridy = 0;
        c3.gridx = 1;
        c3.fill = GridBagConstraints.VERTICAL;
        c3.insets = new Insets(0,0,50,20);
        panel.add(balanceLabel,c3);


        JButton nextDayButton = new JButton("NEXT DAY");
        nextDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Simulation.dailyUpdate();

                pane1.removeAll();
                pane2.removeAll();
                pane3.removeAll();
                arrangepanel1(pane1);
                arrangepanel2(pane2);
                arrangepanel3(pane3);

            }

        });

        c3.anchor = GridBagConstraints.NORTHEAST;
        c3.gridy = 0;
        c3.gridheight = 1;
        c3.weighty = 6;
        c3.fill = GridBagConstraints.VERTICAL;
        c3.insets = new Insets(20,0,240,20);
        panel.add(nextDayButton,c3);


        JButton buyButton = new JButton("BUY");
        c3.fill = GridBagConstraints.NONE;
        c3.anchor = GridBagConstraints.WEST;
        c3.gridheight = 1;
        c3.gridx = 0;
        c3.gridy = 1;
        c3.gridwidth = 1;
        c3.insets = new Insets(220,260,0,0);
        panel.add(buyButton,c3);

        JTextField idOrderField = new JTextField(3);
        idOrderField.setBackground(Color.yellow);
        idOrderField.setText("Tu wpisz id zlecenia");
        c3.anchor = GridBagConstraints.WEST;
        c3.fill = GridBagConstraints.HORIZONTAL;
        c3.gridy = 0;
        c3.gridx = 0;
        c3.gridwidth = 1;
        c3.gridheight = 1;
        c3.insets = new Insets(0,260,180,50);
        panel.add(idOrderField,c3);

        JTextField idPlaneField = new JTextField(3);
        idPlaneField.setBackground(Color.orange);
        idPlaneField.setText("Tu wpisz id samolotu");
        c3.anchor = GridBagConstraints.WEST;
        c3.fill = GridBagConstraints.HORIZONTAL;
        c3.gridy = 0;
        c3.gridx = 0;
        c3.gridwidth = 1;
        c3.gridheight = 1;
        c3.insets = new Insets(0,260,300,50);
        panel.add(idPlaneField,c3);

        JButton assignmentButton = new JButton("ASSIGN ORDER");
        c3.anchor = GridBagConstraints.WEST;
        c3.fill = GridBagConstraints.HORIZONTAL;
        c3.gridy = 0;
        c3.gridx = 0;
        c3.gridwidth = 1;
        c3.gridheight = 1;
        c3.insets = new Insets(20,260,260,50);
        panel.add(assignmentButton,c3);
    }

/*
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(img, 0,0,null);
    }*/


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
