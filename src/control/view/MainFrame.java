package control.view;

import common.Settings;
import control.presenter.MainPresenter;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

/**
 * Created by Oxyde on 9/05/2016.
 */
public class MainFrame extends JFrame implements PropertyChangeListener {
    private enum QuitOptions {
        Exit(0), Cancel(1);

        QuitOptions(int value)
        {
            this.value = value;
        }

        public int getValue()
        {
            return value;
        }

        private final int value;
    }

    public MainFrame(MainPresenter presenter, String title)
            throws HeadlessException {
        super(title);
        this.presenter = presenter;
        initialise();
    }

    private JMenuBar createMenu()
    {
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menu.addMenuListener(presenter.getMenuAction());
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_F);
        menubar.add(menu);

        JMenuItem menuItem = new JMenuItem("New Project...");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, InputEvent.CTRL_MASK));
        menu.add(menuItem);

        menuItem = new JMenuItem("Open Project...");
        menuItem.setMnemonic(KeyEvent.VK_O);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, InputEvent.CTRL_MASK));
        menu.add(menuItem);

        menuItem = new JMenuItem("Reload Project");
        menuItem.setMnemonic(KeyEvent.VK_R);
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Exit");
        menuItem.setMnemonic(KeyEvent.VK_X);
        menuItem.addActionListener(presenter.getExitAction());
        menu.add(menuItem);

        menu = new JMenu("Edit");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_E);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menu = new JMenu("View");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_V);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menu = new JMenu("Project");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_P);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menu = new JMenu("Package");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_K);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menu = new JMenu("Diagram");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_D);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menu = new JMenu("Element");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_L);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menu = new JMenu("Tools");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_T);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menu = new JMenu("Window");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_W);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menu = new JMenu("Help");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_H);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        return menubar;
    }

    private void initialise() {
        presenter.addPropertyChangeListener(this);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(presenter.getMainWindowAction());
        getContentPane().setLayout(
                new BorderLayout());
        getContentPane().add(
                new MainToolbar(presenter, Settings.MainToolbarName,
                        MainToolbar.HORIZONTAL),
                BorderLayout.PAGE_START);
        mainPanel = new MainPanel(presenter);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        setJMenuBar(createMenu());
        glass = new MainGlassPane(presenter);
        setGlassPane(glass);
        glass.setVisible(true);
        pack();
        setSize(new Dimension(600, 400));
        //mainPanel.getDrawingPanel().requestFocus();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            setTitle(Settings.ApplicationName);
            switch ((Integer) evt.getNewValue()) {
                case MainPresenter.ST_NOTHING:
                    getGlassPane().setVisible(false);
                    break;
                case MainPresenter.ST_QUIT:
                    int status = JOptionPane.showOptionDialog(
                            this,
                            "Diagram has not been saved. Exit without saving?",
                            Settings.QuitDialogTItle,
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.WARNING_MESSAGE, null,
                            QuitOptions.values(),
                            QuitOptions.Cancel.toString());
                    if (status == QuitOptions.Exit.getValue())
                    {
                        presenter.exit();
                        setVisible(false);
                        dispose();
                        System.exit(0);
                    }
                    presenter.cancel();
                    break;
                case MainPresenter.ST_PAUSE:
                    setTitle(Settings.ApplicationName + " - PAUSED");
                    break;
            }
        }
    }

    private MainGlassPane glass;
    private MainPresenter presenter;
    private MainPanel mainPanel;

}
