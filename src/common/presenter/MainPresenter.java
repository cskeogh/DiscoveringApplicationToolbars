package common.presenter;

import common.Settings;
import common.model.Boundary;
import common.model.DataListener;
import common.model.Element;
import common.model.ModelObj;
import common.model.Note;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileSystemView;

/**
 * Created by Oxyde on 9/05/2016.
 */
public class MainPresenter {

    public MainPresenter(Settings.ResearchOptions researchOption)
    {
        this.researchOption = researchOption;
        propertyListeners = new PropertyChangeSupport(this);
        dataListeners = new LinkedList<DataListener>();
        theModel = new LinkedList<ModelObj>();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }

    public ActionListener getAlignLeftAction()
    {
        if (alignLeftAction == null)
        {
            alignLeftAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    int left = Integer.MAX_VALUE;
                    for (ModelObj obj : theModel)
                    {
                        left = Math.min(left, obj.getPoint().x);
                    }
                    for (ModelObj obj : theModel) {
                        obj.setPoint(new Point(left, obj.getPoint().y));
                        fireDataChanged(obj);
                    }
                    log("align left button");
                }
            };
        }
        return alignLeftAction;
    }
    public ActionListener getAlignRightAction()
    {
        if (alignRightAction == null)
        {
            alignRightAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    log("align right button");
                    next(ST_NOTHING);
                }
            };
        }
        return alignRightAction;
    }
    public ActionListener getAlignTopAction()
    {
        if (alignTopAction == null)
        {
            alignTopAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    log("align top button");
                    next(ST_NOTHING);
                }
            };
        }
        return alignTopAction;
    }
    public ActionListener getAlignBottomAction()
    {
        if (alignBottomAction == null)
        {
            alignBottomAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    log("align bottom button");
                    next(ST_NOTHING);
                }
            };
        }
        return alignBottomAction;
    }
    public ActionListener getAddBoundaryAction()
    {
        if (addBoundaryAction == null)
        {
            addBoundaryAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    log("boundary button");
                    next(ST_ADD_BOUNDARY);
                }
            };
        }
        return addBoundaryAction;
    }

    public ActionListener getElementAction()
    {
        if (elementAction == null)
        {
            elementAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    log("element button");
                    next(ST_ADD_ELEMENT);
                }
            };
        }
        return elementAction;
    }
    public ActionListener getImageAction()
    {
        if (imageAction == null)
        {
            imageAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    log("image button");
                    next(ST_NOTHING);
                }
            };
        }
        return imageAction;
    }
    public ActionListener getLineAction()
    {
        if (lineAction == null)
        {
            lineAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    log("line button");
                    next(ST_NOTHING);
                }
            };
        }
        return lineAction;
    }
    public ActionListener getLinkAction()
    {
        if (linkAction == null)
        {
            linkAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    log("link button");
                    next(ST_NOTHING);
                }
            };
        }
        return linkAction;
    }
    public ActionListener getNoteAction()
    {
        if (noteAction == null)
        {
            noteAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    log("note button");
                    next(ST_ADD_NOTE);
                }
            };
        }
        return noteAction;
    }
    public ActionListener getRelationshipAction()
    {
        if (relationshipAction == null)
        {
            relationshipAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    log("relationship button");
                    next(ST_NOTHING);
                }
            };
        }
        return relationshipAction;
    }

    public ActionListener getInitialPauseAction()
    {
        if (initialPauseAction == null)
        {
            initialPauseAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
                    File path = new File(FileSystemView.getFileSystemView().getDefaultDirectory(),
                            Settings.LogFilenamePrefix + researchOption.getValue()
                                    + formatter.format(new Date()) + ".txt");
                    try {
                        if (!path.exists()) {
                            path.createNewFile();
                        }
                        FileWriter fw = new FileWriter(path);
                        log = new BufferedWriter(fw);
                        log("start");
                        next(ST_NOTHING);
                    }
                    catch (IOException ex)
                    {
                        setError("Error creating file " + path.getPath() +
                                "\n" + ex.getMessage());
                    }
                }
            };
        }
        return initialPauseAction;
    }

    public ActionListener getPauseAction()
    {
        if (pauseAction == null)
        {
            pauseAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (state == ST_PAUSE) {
                        next(ST_NOTHING);
                    }
                    else
                    {
                        next(ST_PAUSE);
                    }
                }
            };
        }
        return pauseAction;
    }

    public ActionListener getExitAction()
    {
        if (exitAction == null)
        {
            exitAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { next(ST_QUIT); }
            };
        }
        return exitAction;
    }

    public MouseAdapter getPanelMouse() {
        if (panelMouse == null) {
            panelMouse = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    if (e.getButton() == MouseEvent.BUTTON1 )
                    {
                        switch(state)
                        {
                            case ST_ADD_BOUNDARY:
                                Boundary boundary = new Boundary();
                                boundary.setPoint(e.getPoint());
                                fireDataAdd(boundary);
                                theModel.add(boundary);
                                log("boundary added");
                                break;
                            case ST_ADD_ELEMENT:
                                Element element = new Element();
                                element.setPoint(e.getPoint());
                                fireDataAdd(element);
                                theModel.add(element);
                                log("element added");
                                break;
                            case ST_ADD_NOTE:
                                Note note = new Note();
                                note.setPoint(e.getPoint());
                                fireDataAdd(note);
                                theModel.add(note);
                                log("note added");
                                break;
                            default:
                                break;
                        }
                    }
                    next(ST_NOTHING);
                }
            };
        }
        return panelMouse;
    }

    public MouseAdapter getToolbarMouse()
    {
        if (toolbarMouse == null) {
            toolbarMouse = new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    String tip = ((JComponent)e.getSource()).getToolTipText();
                    if (researchOption == Settings.ResearchOptions.Control)
                    {
                        log("mouse enter " + tip);
                    }
                    else if (researchOption == Settings.ResearchOptions.Experimental)
                    {
                        setToolTipHelp(tip);
                        log("show " + tip);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (researchOption == Settings.ResearchOptions.Control)
                    {
                        log("mouse exit");
                    }
                    else if (researchOption == Settings.ResearchOptions.Experimental)
                    {
                        log("hide " + getToolTipHelp());
                        setToolTipHelp(null);
                    }
                }
            };
        }
        return toolbarMouse;
    }

    public WindowListener getMainWindowAction()
    {
        if (mainWindowAction == null)
        {
            mainWindowAction = new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e)
                {
                    next(ST_QUIT);
                }
            };
        }
        return mainWindowAction;
    }

    public MenuListener getMenuAction()
    {
        if (menuAction == null)
        {
            menuAction = new MenuListener() {
                @Override
                public void menuSelected(MenuEvent e) {
                    log("menu" + ((JMenu)e.getSource()).getText() + " opened");
                }

                @Override
                public void menuDeselected(MenuEvent e) {
                    log("menu" + ((JMenu)e.getSource()).getText() + " closed");
                }

                @Override
                public void menuCanceled(MenuEvent e) {
                    log("menu" + ((JMenu)e.getSource()).getText() + " closed");
                }
            };
        }
        return menuAction;
    }

    public String getToolTipHelp()
    {
        return toolTipHelp;
    }

    public void setToolTipHelp(String help)
    {
        String old = toolTipHelp;
        toolTipHelp = help;
        propertyListeners.firePropertyChange("toolTipHelp", old, help);
    }

    public void setError(String error)
    {
        String old = this.error;
        this.error = error;
        propertyListeners.firePropertyChange("error", old, error);
    }

    private void next(int newState)
    {
        int oldState = state;
        state = newState;
        if (oldState == ST_PAUSE)
        {
            log("unpause");
        }
        else if (newState == ST_PAUSE)
        {
            log("pause");
        }
        propertyListeners.firePropertyChange("state", oldState, newState);
    }

    public void cancel()
    {
        next(ST_NOTHING);
    }

    public void exit()
    {
        try {
            log("exit");
            if (log != null) {
                log.close();
            }
        } catch (IOException e) {
        }
    }

    public void log(String msg)
    {
        try {
            if (log != null) {
                log.write(dateFormat.format(new Date()) + ", " + msg + "\n");
            }
        } catch (IOException ex) {
            setError(ex.getMessage());
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        propertyListeners.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        propertyListeners.removePropertyChangeListener(listener);
    }

    public void addDataListener(DataListener pl)
    {
        dataListeners.add(pl);
    }

    public void removedDataListener(DataListener pl)
    {
        dataListeners.remove(pl);
    }

    private void fireDataAdd(ModelObj obj)
    {
        for (DataListener pl : dataListeners)
        {
            pl.dataAdded(obj);
        }
    }

    private void fireDataChanged(ModelObj obj)
    {
        for (DataListener pl : dataListeners)
        {
            pl.dataChanged(obj);
        }
    }

    private Settings.ResearchOptions researchOption;
    private List<ModelObj> theModel;
    private List<DataListener> dataListeners;
    private PropertyChangeSupport propertyListeners;
    private int state;
    private String toolTipHelp;
    private String error;
    private ActionListener alignLeftAction;
    private ActionListener alignRightAction;
    private ActionListener alignTopAction;
    private ActionListener alignBottomAction;
    private ActionListener addBoundaryAction;
    private ActionListener elementAction;
    private ActionListener imageAction;
    private ActionListener lineAction;
    private ActionListener linkAction;
    private ActionListener noteAction;
    private ActionListener relationshipAction;
    private ActionListener initialPauseAction;
    private ActionListener pauseAction;
    private ActionListener exitAction;
    private WindowListener mainWindowAction;
    private MouseAdapter panelMouse;
    private MouseAdapter toolbarMouse;
    private MenuListener menuAction;
    private BufferedWriter log;
    private SimpleDateFormat dateFormat;
    public static final int ST_START = 0;
    public static final int ST_NOTHING = 1;
    public static final int ST_ADD_BOUNDARY = 2;
    public static final int ST_ADD_ELEMENT = 3;
    public static final int ST_ADD_NOTE = 4;
    public static final int ST_QUIT = 5;
    public static final int ST_PAUSE = 6;
}
