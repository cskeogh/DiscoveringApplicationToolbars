package control.view;

import control.presenter.MainPresenter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import sun.awt.AppContext;

/**
 * Created by Oxyde on 11/05/2016.
 */
public class MyToolTipManager extends MouseAdapter {

    public static MyToolTipManager sharedInstance() {
        Object value = AppContext.getAppContext().get(MY_TOOL_TIP_MANAGER_KEY);
        if (value instanceof MyToolTipManager) {
            return (MyToolTipManager) value;
        }
        MyToolTipManager manager = new MyToolTipManager();
        AppContext.getAppContext().put(MY_TOOL_TIP_MANAGER_KEY, manager);
        return manager;
    }

    private MyToolTipManager()
    {
        enterTimer = new Timer(1000, new enterTimerAction());
        enterTimer.setRepeats(false);
    }

    public void registerComponent(JComponent component) {
        component.removeMouseListener(this);
        component.addMouseListener(this);
    }

    public void unregisterComponent(JComponent component) {
        component.removeMouseListener(this);
    }

    public void mouseEntered(MouseEvent event) {
        insideComponent = (JComponent)event.getSource();
        mouseEvent = event;
        enterTimer.start();
        insideComponent.removeMouseMotionListener(this);
        insideComponent.addMouseMotionListener(this);
    }

    public void mouseMoved(MouseEvent event) {
        mouseEvent = event;
    }

    public void mouseExited(MouseEvent event) {
        if (insideComponent != null) {
            insideComponent.removeMouseMotionListener(this);
        }
        enterTimer.stop();
        if (tipWindow != null) {
            tipWindow.hide();
            if (tipWindowShown) {
                presenter.log("hide " + insideComponent.getToolTipText());
                tipWindowShown = false;
            }
        }
    }

    public void mousePressed(MouseEvent event) {
        if (insideComponent != null) {
            insideComponent.removeMouseMotionListener(this);
        }
        enterTimer.stop();
        if (tipWindow != null) {
            tipWindow.hide();
            if (tipWindowShown)
            {
                presenter.log("hide " + insideComponent.getToolTipText());
                tipWindowShown = false;
            }
        }
    }

    protected class enterTimerAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tip = insideComponent.createToolTip();
            tip.setTipText(insideComponent.getToolTipText());
            PopupFactory popupFactory = PopupFactory.getSharedInstance();
            Point screenLocation = insideComponent.getLocationOnScreen();
            Point location;
            location = new Point(screenLocation.x + mouseEvent.getX(),
                    screenLocation.y + mouseEvent.getY() + 20);
            tipWindow = popupFactory.getPopup(insideComponent, tip,
                    location.x, location.y);
            tipWindowShown = true;
            tipWindow.show();
            presenter.log("show " + insideComponent.getToolTipText());
            enterTimer.stop();
        }
    }

    public MainPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    private JToolTip tip;
    private JComponent insideComponent;
    private Timer enterTimer;
    private MouseEvent mouseEvent;
    private MainPresenter presenter;
    private boolean tipWindowShown;
    private transient Popup tipWindow;
    private static final Object MY_TOOL_TIP_MANAGER_KEY = new Object();
}
