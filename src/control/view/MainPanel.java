package control.view;

import common.Settings;
import common.model.Boundary;
import common.model.DataListener;
import common.model.Element;
import common.model.ModelObj;
import common.model.Note;
import control.presenter.MainPresenter;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by Oxyde on 9/05/2016.
 */
public class MainPanel extends JPanel
        implements PropertyChangeListener, DataListener, ComponentListener {
    static {
        try {
            BufferedImage image = ImageIO.read(new File("res/pause.png"));
            pauseImage = new ImageIcon(image);
        } catch (IOException e) {
        }
    }

    public MainPanel(MainPresenter presenter)
    {
        super(null);
        this.presenter = presenter;
        initialise();
    }

    public JPanel getDrawingPanel()
    {
        return drawingPanel;
    }

    private void initialise() {
        addComponentListener(this);
        presenter.addPropertyChangeListener(this);
        presenter.addDataListener(this);
        setBackground(Color.GRAY);
        drawingPanel = new JPanel(null);
        drawingPanel.addMouseListener(presenter.getPanelMouse());
        drawingPanel.setBackground(Color.WHITE);
        add(drawingPanel);
        pauseButton = new JButton(pauseImage);
        pauseButton.setSize(pauseImage.getIconWidth(), pauseImage.getIconHeight());
        pauseButton.addActionListener(presenter.getPauseAction());
        drawingPanel.setBounds(10, 10, 1000, 1000);
        drawingPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        drawingPanel.setFocusable(true);
        drawingPanel.add(pauseButton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            switch ((Integer) evt.getNewValue()) {
                case MainPresenter.ST_NOTHING:
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    getDrawingPanel().requestFocus();
                    break;
                case MainPresenter.ST_ADD_BOUNDARY:
                    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                    break;
                case MainPresenter.ST_ADD_ELEMENT:
                    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                    break;
                case MainPresenter.ST_ADD_NOTE:
                    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                    break;
            }
        }
    }

    @Override
    public void dataAdded(ModelObj obj) {
        if (obj instanceof Boundary) {
            BoundaryComp boundary = new BoundaryComp(obj);
            drawingPanel.add(boundary);
            boundary.setBounds(obj.getPoint().x, obj.getPoint().y,
                    Settings.BoundaryWidth, Settings.BoundaryHeight);
        }
        else if (obj instanceof Element){
            ElementComp ele = new ElementComp(obj);
            drawingPanel.add(ele);
            ele.setBounds(obj.getPoint().x, obj.getPoint().y,
                    ele.getWidth(), ele.getHeight());
        }
        else if (obj instanceof Note) {
            NoteComp note = new NoteComp(obj);
            drawingPanel.add(note);
            note.setBounds(obj.getPoint().x, obj.getPoint().y,
                    note.getWidth(), note.getHeight());
        }
    }

    @Override
    public void dataChanged(ModelObj obj) {
        for (Component comp : drawingPanel.getComponents())
        {
            if (comp instanceof ViewComp) {
                if (((ViewComp) comp).getModel() == obj) {
                    comp.setBounds(obj.getPoint().x, obj.getPoint().y,
                            comp.getWidth(), comp.getHeight());
                }
            }
        }
        getDrawingPanel().requestFocus();
    }
    @Override
    public void componentResized(ComponentEvent e) {
        int w = pauseButton.getWidth();
        int h = pauseButton.getHeight();
        int x = getWidth() - drawingPanel.getBounds().x - w;
        int y = getHeight() - drawingPanel.getBounds().y - h;
        pauseButton.setBounds(x, y, w, h);
    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}

    private static final int NEW_ITEM_INDEX = 0;
    private JPanel drawingPanel;
    private JButton pauseButton;
    private MainPresenter presenter;
    private static ImageIcon pauseImage;
}
