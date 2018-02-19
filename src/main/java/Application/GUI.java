package Application;

import javax.imageio.ImageIO;
import javax.management.JMException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GUI {

    private JFrame frame;

    public void initUI() {

        frame = new JFrame("Aplikancja");
        frame.pack();
        frame.setSize(Integer.parseInt(Application.getProperties().getProperty("framewidth")), Integer.parseInt(Application.getProperties().getProperty("frameheight")));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createMenuBar();
        Tab tab = new Tab(frame);
    }

    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu(Application.getActiveLanguagePack().getMenuFile());
        JMenu lang = new JMenu(Application.getActiveLanguagePack().getMenuLang());
        JMenu help = new JMenu(Application.getActiveLanguagePack().getMenuHelp());
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem(Application.getActiveLanguagePack().getMenuItemExit());
     //   JMenuItem savegameMenuItem = new JMenuItem("Save Game");
        JMenuItem newgameMenuItem = new JMenuItem(Application.getActiveLanguagePack().getMenuItemNewGame());
        JMenuItem langPlMenuItem = new JMenuItem(Application.getActiveLanguagePack().getLangPL());
        JMenuItem langEnMenuItem = new JMenuItem(Application.getActiveLanguagePack().getLangEN());
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText(Application.getActiveLanguagePack().getMenuItemExit());
        eMenuItem.addActionListener((ActionEvent eventExit) -> {
            System.exit(0);
        });
        newgameMenuItem.addActionListener((ActionEvent eventNew)-> {frame.dispose(); Application.main(null);});
        langEnMenuItem.addActionListener((ActionEvent eventLangEn)->{changeLanguage("En");});
        langPlMenuItem.addActionListener((ActionEvent eventLangPl)->{changeLanguage("Pl");});

        file.add(newgameMenuItem);
    //    file.add(savegameMenuItem);
        file.add(eMenuItem);
        lang.add(langEnMenuItem);
        lang.add(langPlMenuItem);

        menubar.add(file);
        menubar.add(lang);
        menubar.add(help);

        frame.setJMenuBar(menubar);
    }

    private void changeLanguage(String langCode)
    {
        if(!Application.getActiveLanguagePack().getLangCode().equals(langCode)) {
            Application.changeActiveLanguagePack();
            createMenuBar();
            Tab.refreshViewAfterChangeLanguage();
        }
    }

}