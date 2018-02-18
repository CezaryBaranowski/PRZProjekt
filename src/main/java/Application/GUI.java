package Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GUI {

    private JFrame frame;

    public void initUI() {

        frame = new JFrame("Aplikancja");
        frame.pack();
        frame.setSize(1280, 720);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createMenuBar();
        Tab tab = new Tab(frame);
    }

    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();
        //ImageIcon icon = new ImageIcon("exit.png");

        JMenu file = new JMenu(Application.getActiveLanguagePack().getMenuFile());
        JMenu help = new JMenu(Application.getActiveLanguagePack().getMenuHelp());
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem(Application.getActiveLanguagePack().getMenuItemExit());
     //   JMenuItem savegameMenuItem = new JMenuItem("Save Game");
        JMenuItem newgameMenuItem = new JMenuItem(Application.getActiveLanguagePack().getMenuItemNewGame());
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText(Application.getActiveLanguagePack().getMenuItemExit());
        eMenuItem.addActionListener((ActionEvent eventExit) -> {
            System.exit(0);
        });
        newgameMenuItem.addActionListener((ActionEvent eventNew)-> {frame.dispose(); Application.main(null);});

        file.add(newgameMenuItem);
    //    file.add(savegameMenuItem);
        file.add(eMenuItem);

        menubar.add(file);
        menubar.add(help);

        frame.setJMenuBar(menubar);
    }

}