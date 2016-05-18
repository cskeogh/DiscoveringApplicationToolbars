package control.view;

import common.presenter.MainPresenter;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by Oxyde on 15/05/2016.
 */
public class MainGlassPane extends JPanel implements ComponentListener {
    static {
        try {
            BufferedImage image = ImageIO.read(new File("res/pause.png"));
            pauseImage = new ImageIcon(image);
        } catch (IOException e) {
        }
    }

    public MainGlassPane(MainPresenter presenter)
    {
        super(null);
        addComponentListener(this);
        this.presenter = presenter;
        setBackground(Color.GRAY);
        pauseButton = new JButton(pauseImage);
        pauseButton.setSize(pauseImage.getIconWidth(), pauseImage.getIconHeight());
        pauseButton.addActionListener(presenter.getInitialPauseAction());
        add(pauseButton);
    }
/*
    protected void paintComponent(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(0, 0, 200, 200);
    }*/

    @Override
    public void componentResized(ComponentEvent e) {
        int x = getWidth()/2;
        int y = getHeight()/2;
        int w = pauseButton.getWidth();
        int h = pauseButton.getHeight();
        pauseButton.setBounds(x-w/2, y-h/2, w, h);
    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}

    private JButton pauseButton;
    private MainPresenter presenter;
    private static ImageIcon pauseImage;
}
