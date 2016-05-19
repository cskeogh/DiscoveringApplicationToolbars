package exp.view;

import common.presenter.MainPresenter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.*;

/**
 * Created by Oxyde on 17/05/2016.
 */
public class ToolTipPane extends JPanel implements ComponentListener {

    public ToolTipPane(MainPresenter presenter) {
        super(null);
        this.addComponentListener(this);
        setOpaque(false);
        tip = this.createToolTip();
        add(tip);
    }

    public void setTipText(String text)
    {
        tip.setTipText(text);
    }

    public void componentResized(ComponentEvent e) {
        int w = getWidth();
        int h = getGraphics().getFontMetrics().getHeight();
        tip.setBounds(10, 0, w-20, h+5);
    }

    public void componentMoved(ComponentEvent e) {}

    public void componentShown(ComponentEvent e) {}

    public void componentHidden(ComponentEvent e) {}

    private JToolTip tip;
}
