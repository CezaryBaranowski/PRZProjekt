package Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GUI {

    private JFrame frame;
    private int currentSkin;
    private Tab tab;

    public void initUI() {

        frame = new JFrame("Aplikancja");
        frame.pack();
        frame.setSize(Integer.parseInt(Application.getProperties().getProperty("framewidth")), Integer.parseInt(Application.getProperties().getProperty("frameheight")));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createMenuBar();
        currentSkin = 1;
        Tab tab = new Tab(frame);
    }

    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu(Application.getActiveLanguagePack().getMenuFile());
        JMenu lang = new JMenu(Application.getActiveLanguagePack().getMenuLang());
        JMenu plaf = new JMenu(Application.getActiveLanguagePack().getMenuSkin());
        JMenu nextDay = new JMenu(Application.getActiveLanguagePack().getNextDayButton());
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem(Application.getActiveLanguagePack().getMenuItemExit());
        JMenuItem newgameMenuItem = new JMenuItem(Application.getActiveLanguagePack().getMenuItemNewGame());
        JMenuItem langPlMenuItem = new JMenuItem(Application.getActiveLanguagePack().getLangPL());
        JMenuItem langEnMenuItem = new JMenuItem(Application.getActiveLanguagePack().getLangEN());
        JMenuItem skin1MenuItem = new JMenuItem(Application.getActiveLanguagePack().getSkin1());
        JMenuItem skin2MenuItem = new JMenuItem(Application.getActiveLanguagePack().getSkin2());
        JMenuItem skin3MenuItem = new JMenuItem(Application.getActiveLanguagePack().getSkin3());
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText(Application.getActiveLanguagePack().getMenuItemExit());
        eMenuItem.addActionListener((ActionEvent eventExit) -> {
            System.exit(0);
        });
        newgameMenuItem.addActionListener((ActionEvent eventNew)-> {frame.dispose(); Application.main(null);});
        langEnMenuItem.addActionListener((ActionEvent eventLangEn)->{changeLanguage("En");});
        langPlMenuItem.addActionListener((ActionEvent eventLangPl)->{changeLanguage("Pl");});
        skin1MenuItem.addActionListener((ActionEvent eventSkin)->{if(currentSkin!=1)changeSkin(1);});
        skin2MenuItem.addActionListener((ActionEvent eventSkin)->{if(currentSkin!=2)changeSkin(2);});
        skin3MenuItem.addActionListener((ActionEvent eventSkin)->{if(currentSkin!=3)changeSkin(3);});

        file.add(newgameMenuItem);
        file.add(eMenuItem);
        lang.add(langEnMenuItem);
        lang.add(langPlMenuItem);
        nextDay.add(new ActionNextDay(Application.getActiveLanguagePack().getNextDayButton(),tab));
        plaf.add(skin1MenuItem);
        plaf.add(skin2MenuItem);
        plaf.add(skin3MenuItem);

        menubar.add(file);
        menubar.add(plaf);
        menubar.add(lang);
        menubar.add(nextDay);

        frame.setJMenuBar(menubar);
    }

    private void changeLanguage(String langCode)
    {
        if(!Application.getActiveLanguagePack().getLangCode().equals(langCode)) {
            Application.changeActiveLanguagePack();
            createMenuBar();
            tab.refreshViewAfterChangeLanguage();
        }
    }

    private void changeSkin(int skin)
    {
        if(skin == 1)
        {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                SwingUtilities.updateComponentTreeUI(frame);
                currentSkin = 1;
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        if(skin == 2)
        {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                SwingUtilities.updateComponentTreeUI(frame);
                currentSkin = 2;
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        if(skin == 3)
        {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                SwingUtilities.updateComponentTreeUI(frame);
                currentSkin = 3;
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

}