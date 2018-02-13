package Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI {

    JFrame frame;

    public void initUI() {

        frame = new JFrame("Aplikancja");
        frame.pack();
        frame.setSize(1280, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createMenuBar();
        Tab.createTabs(frame);
        //frame.pack();
    }

    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();
        //ImageIcon icon = new ImageIcon("exit.png");

        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Pomoc");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem("Exit");
        JMenuItem savegameMenuItem = new JMenuItem("Save Game");
        JMenuItem newgameMenuItem = new JMenuItem("New Game");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener((ActionEvent eventExit) -> {
            System.exit(0);
        });
        newgameMenuItem.addActionListener((ActionEvent eventNew)-> {frame.dispose(); Application.main(null);});

        file.add(newgameMenuItem);
        file.add(savegameMenuItem);
        file.add(eMenuItem);

        menubar.add(file);
        menubar.add(help);

        frame.setJMenuBar(menubar);
    }

  /*  private void createLayout(JComponent... arg) {

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
    }*/

}
