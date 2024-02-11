package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MainMenu extends JMenuBar {
    private JMenu methodFixed;
    private JMenu methodDynamic;

    private JMenuItem fixedPartition;
    private JMenuItem firstFit;
    private JMenuItem bestFit;
    private JMenuItem nextFit;

    private MainWindow mainWindow;

    public MainMenu(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        beginComponents();
        addComponents();
    }

    private void beginComponents(){
        methodFixed = new JMenu("Primer método");
        methodFixed.setIcon(new ImageIcon(new ImageIcon("Images/notas-fijadas.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));

        methodDynamic = new JMenu("Segundo método");
        methodDynamic.setIcon(new ImageIcon(new ImageIcon("Images/proceso.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));

        fixedPartition = new JMenuItem("Partición fija");
        fixedPartition.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        fixedPartition.setIcon(new ImageIcon(new ImageIcon("Images/fijado.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));

        firstFit = new JMenuItem("Primer ajuste");
        firstFit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        firstFit.setIcon(new ImageIcon(new ImageIcon("Images/ganador.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));

        bestFit = new JMenuItem("Mejor ajuste");
        bestFit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        bestFit.setIcon(new ImageIcon(new ImageIcon("Images/productividad.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));

        nextFit = new JMenuItem("Siguiente ajuste");
        nextFit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        nextFit.setIcon(new ImageIcon(new ImageIcon("Images/flecha-correcta.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));

    }

    private void addComponents() {
        methodFixed.add(fixedPartition);
        add(methodFixed);

        methodDynamic.add(firstFit);
        methodDynamic.add(bestFit);
        methodDynamic.add(nextFit);
        add(methodDynamic);
    }

    private Object getFixedPartition(){
        return fixedPartition;
    }

    private Object getFirstFit(){
        return firstFit;
    }

    private Object getBestFit(){
        return bestFit;
    }

    private Object getNextFit(){
        return nextFit;
    }

}
