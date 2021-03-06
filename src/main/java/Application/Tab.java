package Application;

import API.Weather;
import Model.FlightOrder;
import Model.Plane;
import org.omg.CORBA.INTERNAL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Tab extends JPanel {

    private BufferedImage img;
    private JTabbedPane tabbedpane = new JTabbedPane();
    private JPanel pane1 = new JPanel();
    private JPanel pane2 = new JPanel();
    private JPanel pane3 = new JPanel();
    private JFrame frame;
    private ArrayList<FlightOrder> changedOrders = new ArrayList<FlightOrder>();
    private DefaultTableModel newModel;

    public Tab(JFrame frame)
    {
        this.frame = frame;
        createTabs(frame);
    }

    public void createTabs(JFrame frame)
    {
        //JTabbedPane tabbedpane = new JTabbedPane();
        tabbedpane.removeAll();

        //tabbedpane.removeAll();
        pane1 = makePanel("Panel1",1);
        tabbedpane.addTab(Application.getActiveLanguagePack().getOverviewTab(),pane1);

        pane2 = makePanel("Panel2",2);
        tabbedpane.addTab(Application.getActiveLanguagePack().getPlanesTab(),pane2);

        pane3 = makePanel("Panel3",3);
        tabbedpane.addTab(Application.getActiveLanguagePack().getOrdersTab(),pane3);

        frame.add(tabbedpane);
    }

    private JPanel makePanel(String text, int tab) {
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

        panel.setOpaque(true);
        if(tab == 1) arrangepanel1(background);
        if(tab == 2) arrangepanel2(background);
        if(tab == 3) arrangepanel3(background);

        return background;
    }

    private void arrangepanel1(JPanel panel)
    {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();

        JLabel titleLabel = new JLabel(Application.getActiveLanguagePack().getOverviewLabel());
        titleLabel.setFont(new Font("Calibri",Font.PLAIN,40));
        titleLabel.setForeground(Color.GREEN);
        panel.add(titleLabel,c1);


        JLabel boughtPlanesLabel = new JLabel(Application.getActiveLanguagePack().getYourPlanesTab1());
        boughtPlanesLabel.setFont(new Font("Calibri",Font.PLAIN,28));
        boughtPlanesLabel.setForeground(Color.GREEN);
        c1.anchor = GridBagConstraints.NORTHWEST;
        c1.weighty = 6;
        c1.gridy = 1;
        c1.fill = GridBagConstraints.VERTICAL;
        c1.insets = new Insets(120,20,0,0);
        panel.add(boughtPlanesLabel,c1);


        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(Application.getVectorsFromPlanes(Simulation.getInstance().getBoughtPlanes()),Application.getPlaneHeaders());
        table.setGridColor(new Color(20,200,50));
        table.setModel(model);
        c1.weightx = 2;
        c1.gridwidth = 3;
        c1.gridheight = 1;
        c1.anchor = GridBagConstraints.NORTHWEST;
        c1.gridy = 2;
        c1.insets = new Insets(0,20,0,0);
        c1.fill = GridBagConstraints.BOTH;
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane,c1);


        JLabel acceptedOrdersLabel = new JLabel(Application.getActiveLanguagePack().getAcceptedOrdersTab1());
        acceptedOrdersLabel.setFont(new Font("Calibri",Font.PLAIN,28));
        acceptedOrdersLabel.setForeground(Color.GREEN);
        c1.anchor = GridBagConstraints.NORTHWEST;
        c1.gridy = 3;
        c1.insets = new Insets(20,20,0,0);
        panel.add(acceptedOrdersLabel,c1);


        JTable table2 = new JTable();
        DefaultTableModel model2 = new DefaultTableModel(Application.getVectorsFromOrders(Simulation.getInstance().getTakenFlightOrders()),Application.getOrdersHeaders());
        table2.setGridColor(new Color(20,200,50));
        table2.setModel(model2);
        table2.setShowGrid(true);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        c1.anchor = GridBagConstraints.NORTHWEST;
        c1.gridy = 4;
        c1.insets = new Insets(0,20,20,0);
        panel.add(scrollPane2,c1);


        JLabel dayLabel = new JLabel();
        dayLabel.setText(Application.getActiveLanguagePack().getDayLabel()+ " " + Simulation.getInstance().getDay().toString());
        dayLabel.setFont(new Font("Calibri",Font.PLAIN,28));
        dayLabel.setForeground(Color.orange);
        c1.anchor = GridBagConstraints.NORTH;
        c1.gridy = 1;
        c1.fill = GridBagConstraints.VERTICAL;
        c1.insets = new Insets(0,0,0,20);
        panel.add(dayLabel,c1);


        JLabel balanceLabel = new JLabel();
        balanceLabel.setText(Application.getActiveLanguagePack().getBalanceLabel() + " " + Simulation.getInstance().getBalance().toString());
        balanceLabel.setFont(new Font("Calibri",Font.PLAIN,32));
        c1.anchor = GridBagConstraints.NORTH;
        c1.gridy = 0;
        c1.fill = GridBagConstraints.VERTICAL;
        c1.insets = new Insets(0,0,0,20);
        panel.add(balanceLabel,c1);

        ActionNextDay nextDayAction = new ActionNextDay(Application.getActiveLanguagePack().getNextDayButton(),this);
        JButton nextDayButton = new JButton(nextDayAction);

        c1.anchor = GridBagConstraints.SOUTHEAST;
        c1.gridy = 0;
        c1.gridheight = 1;
        c1.fill = GridBagConstraints.VERTICAL;
        c1.insets = new Insets(20,0,0,20);
        panel.add(nextDayButton,c1);

    }





    private void arrangepanel2(JComponent panel)
    {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        JLabel titleLabel = new JLabel(Application.getActiveLanguagePack().getPlanesLabel());
        titleLabel.setFont(new Font("Calibri",Font.PLAIN,40));
        titleLabel.setForeground(Color.CYAN);
        c2.anchor = GridBagConstraints.FIRST_LINE_START;
        panel.add(titleLabel,c2);


        JLabel boughtPlanesLabel = new JLabel(Application.getActiveLanguagePack().getYourPlanesTab2());
        boughtPlanesLabel.setFont(new Font("Calibri",Font.PLAIN,28));
        boughtPlanesLabel.setForeground(Color.GREEN);
        c2.anchor = GridBagConstraints.NORTHWEST;
        c2.weighty = 6;
        c2.weightx = 2;
        c2.gridy = 0;
        c2.gridx = 0;
        c2.fill = GridBagConstraints.VERTICAL;
        c2.insets = new Insets(120,20,0,0);
        panel.add(boughtPlanesLabel,c2);


        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(Application.getVectorsFromPlanes(Simulation.getInstance().getBoughtPlanes()),Application.getPlaneHeaders());
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
        panel.add(scrollPane,c2);


        JLabel acceptedOrdersLabel = new JLabel(Application.getActiveLanguagePack().getAvailablePlanesTab2());
        acceptedOrdersLabel.setFont(new Font("Calibri",Font.PLAIN,28));
        acceptedOrdersLabel.setForeground(Color.GREEN);
        c2.weighty = 6;
        c2.weightx = 2;
        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.NORTHWEST;
        c2.gridy = 1;
        c2.insets = new Insets(160,20,0,0);
        panel.add(acceptedOrdersLabel,c2);


        JTable table2 = new JTable();
        DefaultTableModel model2 = new DefaultTableModel(Application.getVectorsFromPlanes(Simulation.getInstance().getAvailablePlanes()),Application.getPlaneHeaders());
        table2.setPreferredScrollableViewportSize(new Dimension(600,120));
        table2.setGridColor(new Color(20,200,50));
        table2.setModel(model2);
        table2.setShowGrid(true);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        scrollPane2.setPreferredSize(new Dimension(600,150));
        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.NORTHWEST;
        c2.gridy = 2;
        c2.insets = new Insets(0,20,0,0);
        panel.add(scrollPane2,c2);


        JLabel dayLabel = new JLabel();
        dayLabel.setText(Application.getActiveLanguagePack().getDayLabel()+ " " + Simulation.getInstance().getDay().toString());
        dayLabel.setFont(new Font("Calibri",Font.PLAIN,28));
        dayLabel.setForeground(Color.orange);
        c2.anchor = GridBagConstraints.NORTH;
        c2.gridy = 0;
        c2.gridx = 1;
        c2.fill = GridBagConstraints.VERTICAL;
        c2.insets = new Insets(20,0,150,20);
        panel.add(dayLabel,c2);


        JLabel balanceLabel = new JLabel();
        balanceLabel.setText(Application.getActiveLanguagePack().getBalanceLabel()+ " " + Simulation.getInstance().getBalance().toString());
        balanceLabel.setFont(new Font("Calibri",Font.PLAIN,32));
        c2.anchor = GridBagConstraints.NORTH;
        c2.gridy = 0;
        c2.gridx = 1;
        c2.fill = GridBagConstraints.VERTICAL;
        c2.insets = new Insets(0,0,0,20);
        panel.add(balanceLabel,c2);

        ActionNextDay nextDayAction = new ActionNextDay(Application.getActiveLanguagePack().getNextDayButton(),this);
        JButton nextDayButton = new JButton(nextDayAction);

        c2.anchor = GridBagConstraints.NORTHEAST;
        c2.gridy = 0;
        c2.gridheight = 1;
        c2.weighty = 6;
        c2.fill = GridBagConstraints.VERTICAL;
        c2.insets = new Insets(20,0,100,20);
        panel.add(nextDayButton,c2);


        JButton buyButton = new JButton(Application.getActiveLanguagePack().getBuyButton());
        c2.fill = GridBagConstraints.NONE;
        c2.anchor = GridBagConstraints.WEST;
        c2.gridheight = 1;
        c2.gridx = 0;
        c2.gridy = 1;
        c2.gridwidth = 1;
        c2.insets = new Insets(160,290,0,0);
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if(table2.getSelectedRow()!=-1) {
                    Plane boughtPlane =  Simulation.getInstance().getAvailablePlanes().get(table2.getSelectedRow());
                    if (Simulation.getInstance().getBalance() >= boughtPlane.getPrice()) {
                        Simulation.getInstance().getBoughtPlanes().add(boughtPlane);
                        Simulation.getInstance().getAvailablePlanes().remove(boughtPlane);
                        Simulation.getInstance().setBalance(Simulation.getInstance().getBalance() - boughtPlane.getPrice());
                        refreshView();
                    }
                    else JOptionPane.showMessageDialog(frame,"You can not afford this plane");
                }
                else JOptionPane.showMessageDialog(frame,"Select one plane");
            }
        });
        panel.add(buyButton,c2);

    }




    private void arrangepanel3(JComponent panel)
    {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c3 = new GridBagConstraints();
        JLabel titleLabel = new JLabel(Application.getActiveLanguagePack().getOrdersLabel());
        titleLabel.setFont(new Font("Calibri",Font.PLAIN,40));
        titleLabel.setForeground(Color.RED);
        c3.anchor = GridBagConstraints.FIRST_LINE_START;
        panel.add(titleLabel,c3);


        JLabel boughtPlanesLabel = new JLabel(Application.getActiveLanguagePack().getYourOrdersTab3());
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
        DefaultTableModel model = new DefaultTableModel(Application.getVectorsFromOrders(Simulation.getInstance().getTakenFlightOrders()),Application.getOrdersHeaders());
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
        panel.add(scrollPane,c3);


        JLabel acceptedOrdersLabel = new JLabel(Application.getActiveLanguagePack().getAvailableOrdersTab3());
        acceptedOrdersLabel.setFont(new Font("Calibri",Font.PLAIN,28));
        acceptedOrdersLabel.setForeground(Color.RED);
        c3.anchor = GridBagConstraints.NORTHWEST;
        c3.gridy = 1;
        c3.insets = new Insets(0,20,180,0);
        panel.add(acceptedOrdersLabel,c3);


        JTable table2 = new JTable();
        DefaultTableModel model2 = new DefaultTableModel(Application.getVectorsFromOrders(Simulation.getInstance().getAvailableFlightOrders()),Application.getOrdersHeaders());
        table2.setPreferredScrollableViewportSize(new Dimension(600,120));
        table2.setGridColor(new Color(20,200,50));
        table2.setModel(model2);
        table2.setShowGrid(true);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        scrollPane2.setPreferredSize(new Dimension(600,150));
        c3.anchor = GridBagConstraints.NORTHWEST;
        c3.gridy = 1;
        c3.insets = new Insets(100,20,0,0);
        panel.add(scrollPane2,c3);


        JButton takeOrderButton = new JButton(Application.getActiveLanguagePack().getTakeButton());
        takeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(table2.getSelectedRow()!=-1 && changedOrders.size()>0) {
                    FlightOrder takenOrder = changedOrders.get(table2.getSelectedRow());
                    Simulation.getInstance().getTakenFlightOrders().add(takenOrder);
                    changedOrders.remove(takenOrder);
                    Simulation.getInstance().getAvailableFlightOrders().remove(takenOrder);

                    refreshView();
                }
                else
                    if(table2.getSelectedRow()!=-1) {
                    FlightOrder takenOrder = Simulation.getInstance().getAvailableFlightOrders().get(table2.getSelectedRow());
                    Simulation.getInstance().getTakenFlightOrders().add(takenOrder);
                    Simulation.getInstance().getAvailableFlightOrders().remove(takenOrder);

                    refreshView();
                }

                else JOptionPane.showMessageDialog(frame,"Select one order");
            }
        });
        c3.insets = new Insets(20,280,200,260);
        panel.add(takeOrderButton,c3);


        JLabel dayLabel = new JLabel();
        dayLabel.setText(Application.getActiveLanguagePack().getDayLabel()+ " " + Simulation.getInstance().getDay().toString());
        dayLabel.setFont(new Font("Calibri",Font.PLAIN,28));
        dayLabel.setForeground(Color.orange);
        c3.anchor = GridBagConstraints.NORTH;
        c3.gridy = 0;
        c3.gridx = 1;
        c3.fill = GridBagConstraints.VERTICAL;
        c3.insets = new Insets(20,0,150,20);
        panel.add(dayLabel,c3);


        JLabel balanceLabel = new JLabel();
        balanceLabel.setText(Application.getActiveLanguagePack().getBalanceLabel()+ " " + Simulation.getInstance().getBalance().toString());
        balanceLabel.setFont(new Font("Calibri",Font.PLAIN,32));
        c3.anchor = GridBagConstraints.NORTH;
        c3.gridy = 0;
        c3.gridx = 1;
        c3.fill = GridBagConstraints.VERTICAL;
        c3.insets = new Insets(0,0,50,20);
        panel.add(balanceLabel,c3);

        ActionNextDay actionNextDay = new ActionNextDay(Application.getActiveLanguagePack().getNextDayButton(),this);
        JButton nextDayButton = new JButton(actionNextDay);

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
        idOrderField.setText(Application.getActiveLanguagePack().getNumberOfOrderLabel());
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
        idPlaneField.setText(Application.getActiveLanguagePack().getNumberOfPlaneLabel());
        c3.anchor = GridBagConstraints.WEST;
        c3.fill = GridBagConstraints.HORIZONTAL;
        c3.gridy = 0;
        c3.gridx = 0;
        c3.gridwidth = 1;
        c3.gridheight = 1;
        c3.insets = new Insets(0,260,300,50);
        panel.add(idPlaneField,c3);

        JButton assignmentButton = new JButton(Application.getActiveLanguagePack().getAssignButton());
        assignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlightOrder orderToAssign = Simulation.getInstance().getTakenFlightOrders().get(Integer.parseInt(idOrderField.getText()) - 1);
                // if(changedOrders.size()>0) orderToAssign = changedOrders.get(Integer.parseInt(idOrderField.getText())-1);
              //   else orderToAssign = Simulation.getInstance().getTakenFlightOrders().get(Integer.parseInt(idOrderField.getText()) - 1);

                Plane planeToAssign = Simulation.getInstance().getBoughtPlanes().get(Integer.parseInt(idPlaneField.getText()) - 1);
                if(planeToAssign.getCapacity()<orderToAssign.getAmountOfPassengers())
                {
                    JOptionPane.showMessageDialog(frame,"Selected plane has not enough seats");
                }
                else if(planeToAssign.getRange()<orderToAssign.getDistance())
                {
                    JOptionPane.showMessageDialog(frame,"Selected plane has not sufficient range");
                }
                else
                if(planeToAssign.getAvailable()
                    && planeToAssign.getCapacity()>=orderToAssign.getAmountOfPassengers()
                    && planeToAssign.getRange()>=orderToAssign.getDistance())
                {
                    planeToAssign.setAvailable(false);
                    planeToAssign.setCurrentlyAssignedOrder(orderToAssign);
                    SwingWorker worker = new SwingWorker() {
                        @Override
                        protected Object doInBackground() throws Exception {
                            orderToAssign.setExpectedDestinationConditions(Weather.getConditions(orderToAssign.getDestination().getCity()));
                            return null;
                        }
                    };
                    worker.execute();

                    refreshView();
                }
            }
        });
        c3.anchor = GridBagConstraints.WEST;
        c3.fill = GridBagConstraints.HORIZONTAL;
        c3.gridy = 0;
        c3.gridx = 0;
        c3.gridwidth = 1;
        c3.gridheight = 1;
        c3.insets = new Insets(20,260,260,50);
        panel.add(assignmentButton,c3);


        JTextField filterOrderField = new JTextField(3);
        filterOrderField.setBackground(Color.WHITE);
        filterOrderField.setText("");
        c3.anchor = GridBagConstraints.EAST;
        c3.fill = GridBagConstraints.HORIZONTAL;
        c3.gridy = 3;
        c3.gridx = 0;
        c3.gridwidth = 1;
        c3.gridheight = 1;
        c3.insets = new Insets(0,50,0,200);
        panel.add(filterOrderField,c3);

        filterOrderField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                changedOrders.clear();
                c3.anchor = GridBagConstraints.NORTHWEST;
                c3.weighty = 6;
                c3.weightx = 2;
                c3.gridy = 1;
                c3.gridx = 0;
                c3.insets = new Insets(70,20,20,0);
                c3.fill = GridBagConstraints.BOTH;

                String text = filterOrderField.getText();
                if(text.isEmpty()) {changedOrders = new ArrayList<FlightOrder>(Simulation.getInstance().getAvailableFlightOrders()); newModel = new DefaultTableModel(Application.getVectorsFromOrders(changedOrders),Application.getOrdersHeaders());}
                else
                {
                    for(FlightOrder fo : Simulation.getInstance().getAvailableFlightOrders())
                    {
                        if(fo.getFrom().getCity().startsWith(text))
                        {
                            changedOrders.add(fo);
                        }
                    }
                    newModel = new DefaultTableModel(Application.getVectorsFromOrders(changedOrders),Application.getOrdersHeaders());
                }
                pane1.remove(table2);
                table2.setModel(newModel);
                panel.add(table2,c3);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedOrders.clear();
                c3.anchor = GridBagConstraints.NORTHWEST;
                c3.weighty = 6;
                c3.weightx = 2;
                c3.gridy = 1;
                c3.gridx = 0;
                c3.insets = new Insets(70,20,20,0);
                c3.fill = GridBagConstraints.BOTH;
                String text = filterOrderField.getText();
                if(text.isEmpty()) {changedOrders = new ArrayList<FlightOrder>(Simulation.getInstance().getAvailableFlightOrders()); newModel = new DefaultTableModel(Application.getVectorsFromOrders(changedOrders),Application.getOrdersHeaders());}
                else
                {
                    for(FlightOrder fo : Simulation.getInstance().getAvailableFlightOrders())
                    {
                        if(fo.getFrom().getCity().startsWith(text))
                        {
                            changedOrders.add(fo);
                        }
                    }
                    newModel = new DefaultTableModel(Application.getVectorsFromOrders(changedOrders),Application.getOrdersHeaders());
                }
                pane1.remove(table2);
                table2.setModel(newModel);
                panel.add(table2,c3);

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changedOrders.clear();
                c3.anchor = GridBagConstraints.NORTHWEST;
                c3.weighty = 6;
                c3.weightx = 2;
                c3.gridy = 1;
                c3.gridx = 0;
                c3.insets = new Insets(70,20,20,0);
                c3.fill = GridBagConstraints.BOTH;
                String text = filterOrderField.getText();
                if(text.isEmpty()) {changedOrders = new ArrayList<FlightOrder>(Simulation.getInstance().getAvailableFlightOrders()); newModel = new DefaultTableModel(Application.getVectorsFromOrders(changedOrders),Application.getOrdersHeaders());}
                else
                {
                    for(FlightOrder fo : Simulation.getInstance().getAvailableFlightOrders())
                    {
                        if(fo.getFrom().getCity().startsWith(text))
                        {
                            changedOrders.add(fo);
                        }
                    }
                    newModel = new DefaultTableModel(Application.getVectorsFromOrders(changedOrders),Application.getOrdersHeaders());
                }
                pane1.remove(table2);
                table2.setModel(newModel);
                pane1.add(table2,c3);

            }
        });

    }

    public void refreshView()
    {
        pane1.removeAll();
        pane2.removeAll();
        pane3.removeAll();
        arrangepanel1(pane1);
        arrangepanel2(pane2);
        arrangepanel3(pane3);
        if(Simulation.getInstance().getBalance()<0)
        {
            JOptionPane.showMessageDialog(frame,"You are bankrupt");
        }

    }

    public void refreshViewAfterChangeLanguage()
    {
        pane1.removeAll();
        pane2.removeAll();
        pane3.removeAll();

        createTabs(frame);

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
