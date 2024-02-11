package Interface;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private JDesktopPane desktopPane;
    private MainMenu mainMenu;

    public MainWindow() {
        setTitle("Administrador de Memoria");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        begin();

    }

    private void begin() {
        beginComponents();
        addComponents();
    }

    private void beginComponents() {
        desktopPane = new JDesktopPane();
        mainMenu = new MainMenu(this);

    }

    private void addComponents() {
        setJMenuBar(mainMenu);
        addWindowListener(new HandlingEvents(this));
    }
}
