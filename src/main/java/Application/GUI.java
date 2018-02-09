package Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {

    public void initUI() {

        createMenuBar();
        Tab.createTabs(this);

        pack();
        setTitle("Aplikancja");
        setSize(1280, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        newgameMenuItem.addActionListener((ActionEvent eventnew)-> {this.dispose(); Application.main(null);});

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

}
