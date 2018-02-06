import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Application extends JFrame {

    public Application() {

        initUI();
    }

    private void initUI() {

        this.setSize(100,100);

        JButton quitButton = new JButton("Quit");
        quitButton.setToolTipText("A button component");
        //quitButton.setMnemonic(KeyEvent.VK_B);

        quitButton.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        createMenuBar();

        createLayout(quitButton);

        setTitle("Quit button");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();
        ImageIcon icon = new ImageIcon("exit.png");

        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Pomoc");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem("Exit", icon);
        JMenuItem savegameMenuItem = new JMenuItem("Zapisz gre");
        JMenuItem newgameMenuItem = new JMenuItem("Nowa gra");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        file.add(eMenuItem);
        file.add(savegameMenuItem);
        file.add(newgameMenuItem);

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