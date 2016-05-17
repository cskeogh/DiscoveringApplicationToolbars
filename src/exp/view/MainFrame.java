package exp.view;

import common.Settings;
import control.presenter.MainPresenter;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        menu.setMargin(new Insets(0, 10, 0, Settings.MenuSpacing));
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
        menuItem = new JMenuItem("Close Project",
                KeyEvent.VK_C);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Save Project As...",
                KeyEvent.VK_A);
        menu.add(menuItem);
        menuItem = new JMenuItem("Save As Shortcut...",
                KeyEvent.VK_S);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Print Setup...",
                KeyEvent.VK_I);
        menu.add(menuItem);
        menuItem = new JMenuItem("Page Setup...",
                KeyEvent.VK_G);
        menu.add(menuItem);
        menuItem = new JMenuItem("Print Preview...",
                KeyEvent.VK_V);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Print...",
                KeyEvent.VK_P);
        menu.add(menuItem);
        menuItem = new JMenuItem("Print to PDF...",
                KeyEvent.VK_D);
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

        menuItem = new JMenuItem("Open Source File",
                KeyEvent.VK_O);
        menu.add(menuItem);
        menuItem = new JMenuItem("Search in Model",
                KeyEvent.VK_M);
        menu.add(menuItem);
        menuItem = new JMenuItem("Search in Files",
                KeyEvent.VK_F);
        menu.add(menuItem);
        menuItem = new JMenuItem("Find a Package...",
                KeyEvent.VK_P);
        menu.add(menuItem);
        menuItem = new JMenuItem("Find a Diagram...",
                KeyEvent.VK_D);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Copy",
                KeyEvent.VK_Y);
        menu.add(menuItem);
        menuItem = new JMenuItem("Paste as Link",
                KeyEvent.VK_L);
        menu.add(menuItem);
        menuItem = new JMenuItem("Paste as New",
                KeyEvent.VK_N);
        menu.add(menuItem);
        menuItem = new JMenuItem("Paste Image from Clipboard",
                KeyEvent.VK_I);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Undo",
                KeyEvent.VK_U);
        menu.add(menuItem);
        menuItem = new JMenuItem("Redo",
                KeyEvent.VK_R);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Select All",
                KeyEvent.VK_A);
        menu.add(menuItem);
        menuItem = new JMenuItem("Select By Type",
                KeyEvent.VK_T);
        menu.add(menuItem);
        menuItem = new JMenuItem("Clear Selection",
                KeyEvent.VK_S);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Delete Selected",
                KeyEvent.VK_D);
        menu.add(menuItem);

        menu = new JMenu("View");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_V);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menuItem = new JMenuItem("Project Browser",
                KeyEvent.VK_J);
        menu.add(menuItem);
        menuItem = new JMenuItem("Navigator",
                KeyEvent.VK_A);
        menu.add(menuItem);
        menuItem = new JMenuItem("Portals",
                KeyEvent.VK_L);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Model Views",
                KeyEvent.VK_D);
        menu.add(menuItem);
        menuItem = new JMenuItem("Summary View",
                KeyEvent.VK_U);
        menu.add(menuItem);
        menuItem = new JMenuItem("Traceability",
                KeyEvent.VK_T);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Properties",
                KeyEvent.VK_P);
        menu.add(menuItem);
        menuItem = new JMenuItem("Notes View",
                KeyEvent.VK_N);
        menu.add(menuItem);
        menuItem = new JMenuItem("Tag Values",
                KeyEvent.VK_T);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Scripting",
                KeyEvent.VK_S);
        menu.add(menuItem);
        menuItem = new JMenuItem("Resources",
                KeyEvent.VK_R);
        menu.add(menuItem);

        menu = new JMenu("Project");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_P);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menuItem = new JMenuItem("Calendar",
                KeyEvent.VK_C);
        menu.add(menuItem);
        menuItem = new JMenuItem("Gnatt View",
                KeyEvent.VK_G);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Personal Tasks",
                KeyEvent.VK_T);
        menu.add(menuItem);
        menuItem = new JMenuItem("Model Mail",
                KeyEvent.VK_M);
        menu.add(menuItem);
        menuItem = new JMenuItem("Discussions",
                KeyEvent.VK_I);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Glossary",
                KeyEvent.VK_S);
        menu.add(menuItem);
        menuItem = new JMenuItem("Security",
                KeyEvent.VK_Y);
        menu.add(menuItem);
        menuItem = new JMenuItem("Auditing",
                KeyEvent.VK_N);
        menu.add(menuItem);
        menuItem = new JMenuItem("Status",
                KeyEvent.VK_S);
        menu.add(menuItem);

        menu = new JMenu("Package");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_K);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menuItem = new JMenuItem("New...",
                KeyEvent.VK_N);
        menu.add(menuItem);
        menuItem = new JMenuItem("View As List",
                KeyEvent.VK_V);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Baselines...",
                KeyEvent.VK_B);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Publish Model...",
                KeyEvent.VK_P);
        menu.add(menuItem);
        menuItem = new JMenuItem("Import/Export",
                KeyEvent.VK_X);
        menu.add(menuItem);
        menuItem = new JMenuItem("Model Validation",
                KeyEvent.VK_L);
        menu.add(menuItem);
        menuItem = new JMenuItem("Model Transformation",
                KeyEvent.VK_M);
        menu.add(menuItem);

        menu = new JMenu("Diagram");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_D);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menuItem = new JMenuItem("New...",
                KeyEvent.VK_N);
        menu.add(menuItem);
        menuItem = new JMenuItem("Properties...",
                KeyEvent.VK_P);
        menu.add(menuItem);
        menuItem = new JMenuItem("Find in Project",
                KeyEvent.VK_F);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Toolbox",
                KeyEvent.VK_T);
        menu.add(menuItem);
        menuItem = new JMenuItem("Appearance",
                KeyEvent.VK_E);
        menu.add(menuItem);
        menuItem = new JMenuItem("Advanced",
                KeyEvent.VK_A);
        menu.add(menuItem);
        menuItem = new JMenuItem("Lock...",
                KeyEvent.VK_C);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Save",
                KeyEvent.VK_S);
        menu.add(menuItem);
        menuItem = new JMenuItem("Save Image to File",
                KeyEvent.VK_I);
        menu.add(menuItem);
        menuItem = new JMenuItem("Save Image to Clipboard",
                KeyEvent.VK_C);
        menu.add(menuItem);

        menu = new JMenu("Element");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_L);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menuItem = new JMenuItem("Properties...",
                KeyEvent.VK_P);
        menu.add(menuItem);
        menuItem = new JMenuItem("Properties Page",
                KeyEvent.VK_E);
        menu.add(menuItem);
        menuItem = new JMenuItem("Find in Project",
                KeyEvent.VK_F);
        menu.add(menuItem);
        menuItem = new JMenuItem("Find in Diagrams",
                KeyEvent.VK_D);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Discussions",
                KeyEvent.VK_I);
        menu.add(menuItem);
        menuItem = new JMenuItem("Relationships",
                KeyEvent.VK_R);
        menu.add(menuItem);
        menuItem = new JMenuItem("Linked Document",
                KeyEvent.VK_L);
        menu.add(menuItem);
        menuItem = new JMenuItem("Browser",
                KeyEvent.VK_W);
        menu.add(menuItem);

        menu = new JMenu("Tools");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_T);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menuItem = new JMenuItem("Specification Manager",
                KeyEvent.VK_M);
        menu.add(menuItem);
        menuItem = new JMenuItem("Scenario Builder",
                KeyEvent.VK_S);
        menu.add(menuItem);
        menuItem = new JMenuItem("Database Builder",
                KeyEvent.VK_D);
        menu.add(menuItem);
        menuItem = new JMenuItem("Decision Table",
                KeyEvent.VK_T);
        menu.add(menuItem);
        menuItem = new JMenuItem("Schema Composer",
                KeyEvent.VK_C);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("XSLT Debugger",
                KeyEvent.VK_X);
        menu.add(menuItem);
        menuItem = new JMenuItem("Reusable Asset Service",
                KeyEvent.VK_A);
        menu.add(menuItem);

        menu = new JMenu("Analyser");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_A);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menuItem = new JMenuItem("Scripts",
                KeyEvent.VK_C);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Debug",
                KeyEvent.VK_D);
        menu.add(menuItem);
        menuItem = new JMenuItem("Record",
                KeyEvent.VK_R);
        menu.add(menuItem);
        menuItem = new JMenuItem("Profile",
                KeyEvent.VK_F);
        menu.add(menuItem);
        menuItem = new JMenuItem("Simulate",
                KeyEvent.VK_S);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Toolbars",
                KeyEvent.VK_O);
        menu.add(menuItem);
        menuItem = new JMenuItem("Test Points",
                KeyEvent.VK_P);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Build",
                KeyEvent.VK_B);
        menu.add(menuItem);
        menuItem = new JMenuItem("Run",
                KeyEvent.VK_U);
        menu.add(menuItem);
        menuItem = new JMenuItem("Test",
                KeyEvent.VK_T);
        menu.add(menuItem);
        menuItem = new JMenuItem("Deploy",
                KeyEvent.VK_Y);
        menu.add(menuItem);

        menu = new JMenu("Extensions");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_X);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menuItem = new JMenuItem("ArcGIS",
                KeyEvent.VK_A);
        menu.add(menuItem);
        menuItem = new JMenuItem("ODM",
                KeyEvent.VK_O);
        menu.add(menuItem);
        menuItem = new JMenuItem("GML",
                KeyEvent.VK_G);
        menu.add(menuItem);
        menuItem = new JMenuItem("NIEM",
                KeyEvent.VK_N);
        menu.add(menuItem);
        menuItem = new JMenuItem("DOORS",
                KeyEvent.VK_D);
        menu.add(menuItem);
        menuItem = new JMenuItem("Eclipse",
                KeyEvent.VK_E);
        menu.add(menuItem);
        menuItem = new JMenuItem("Visual Studio",
                KeyEvent.VK_V);
        menu.add(menuItem);

        menu = new JMenu("Window");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_W);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menuItem = new JMenuItem("Full Screen",
                KeyEvent.VK_F);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Reload",
                KeyEvent.VK_R);
        menu.add(menuItem);
        menuItem = new JMenuItem("Set Focus to Current View",
                KeyEvent.VK_F);
        menu.add(menuItem);
        menuItem = new JMenuItem("Float Current View",
                KeyEvent.VK_L);
        menu.add(menuItem);
        menu.addSeparator();

        menuItem = new JMenuItem("Close Current View",
                KeyEvent.VK_V);
        menu.add(menuItem);
        menuItem = new JMenuItem("Close All Except Current",
                KeyEvent.VK_X);
        menu.add(menuItem);
        menuItem = new JMenuItem("Close All",
                KeyEvent.VK_A);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Always On Top",
                KeyEvent.VK_T);
        menu.add(menuItem);

        menu = new JMenu("Help");
        menu.setMargin(new Insets(0, 0, 0, Settings.MenuSpacing));
        menu.setMnemonic(KeyEvent.VK_H);
        menu.addMenuListener(presenter.getMenuAction());
        menubar.add(menu);

        menuItem = new JMenuItem("About",
                KeyEvent.VK_A);
        menu.add(menuItem);
        menuItem = new JMenuItem("Help Contents",
                KeyEvent.VK_H);
        menu.add(menuItem);
        menuItem = new JMenuItem("Learning Centre",
                KeyEvent.VK_L);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("View Licence Agreement",
                KeyEvent.VK_V);
        menu.add(menuItem);
        menuItem = new JMenuItem("Order...",
                KeyEvent.VK_L);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Visit Webpage",
                KeyEvent.VK_W);
        menu.add(menuItem);

        return menubar;
    }

    private void initialise() {
        presenter.addPropertyChangeListener(this);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(presenter.getMainWindowAction());
        getContentPane().setLayout(
                new BorderLayout());
        MainToolbar toolbar = new MainToolbar(presenter, Settings.MainToolbarName,
                MainToolbar.HORIZONTAL);
        toolbar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                toolTipPane.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(!toolbar.contains(e.getPoint())) {
                    toolTipPane.setVisible(false);
                }
            }
        });
        getContentPane().add(toolbar, BorderLayout.PAGE_START);
        mainPanel = new MainPanel(presenter);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        setJMenuBar(createMenu());
        toolTipPane = new ToolTipPane(presenter);
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
                    if (getGlassPane() != toolTipPane) {
                        setGlassPane(toolTipPane);
                    }
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

    private ToolTipPane toolTipPane;
    private MainGlassPane glass;
    private MainPresenter presenter;
    private MainPanel mainPanel;

}
