package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WindowMemoryFixed extends JInternalFrame implements KeyListener {


    private GridBagConstraints gbc = new GridBagConstraints();
    private JDesktopPane desktopPane;
    private MainWindow mainWindow;

    public WindowMemoryFixed(JDesktopPane desktopPane, MainWindow mainWindow){
        this.desktopPane = desktopPane;
        this.mainWindow = mainWindow;
        setTitle("Gestion de memoria por particiones fijas");
        setClosable(true);
        setIconifiable(true);
        setSize(700, 450);
        setLocation(30, 40);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(true);
        desktopPane.add(this);
        setLayout(null);
        begin();
    }

    private void begin() {
        beginComponents();
        addComponents();
    }

    private void beginComponents() {

    }

    private void addComponents() {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
