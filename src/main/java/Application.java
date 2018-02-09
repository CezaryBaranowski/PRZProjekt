import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Application extends JFrame {

    public Application() {

        initUI();
    }

    private void initUI() {

      /*  JButton quitButton = new JButton("Quit");
        quitButton.setToolTipText("A button component");
        //quitButton.setMnemonic(KeyEvent.VK_B);

        quitButton.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });*/

        createMenuBar();
        createTabs();

       // createLayout(quitButton);
        setVisible(true);
        pack();
        setTitle("Aplikancja");
        setSize(1280, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createTabs()
    {
        JTabbedPane tabbedpane = new JTabbedPane();
        JComponent pane1 = makeTextPanel("Panel1",1);
        tabbedpane.addTab("Przeglad",pane1);

        JComponent pane2 = makeTextPanel("Panel2",2);
        tabbedpane.addTab("Samoloty",pane2);

        JComponent pane3 = makeTextPanel("Panel3",3);
        tabbedpane.addTab("Zlecenia",pane3);

        add(tabbedpane);
    }

    private JComponent makeTextPanel(String text, int tab) {
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
            filler.setLayout(new GridBagLayout());
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Nie udalo sie wczytac tla");
        }
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1,1));
        panel.add(filler);
        return panel;
    }

    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();
        ImageIcon icon = new ImageIcon("exit.png");

        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Pomoc");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem("Exit", icon);
        JMenuItem savegameMenuItem = new JMenuItem("Save Game");
        JMenuItem newgameMenuItem = new JMenuItem("New Game");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener((ActionEvent eventexit) -> {
            System.exit(0);
        });
        newgameMenuItem.addActionListener((ActionEvent eventnew)-> {Application.this.dispose(); Application.main(null);});

        file.add(newgameMenuItem);
        file.add(savegameMenuItem);
        file.add(eMenuItem);

        menubar.add(file);
        menubar.add(help);

        setJMenuBar(menubar);
    }

    private void createLayout(JComponent... arg) {

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }
}