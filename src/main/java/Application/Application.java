package Application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Application {

    public Application() {

        GUI gui = new GUI();
        gui.initUI();
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            //ex.setVisible(true);
        });
    }
}