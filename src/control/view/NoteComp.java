package control.view;

import common.model.ModelObj;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by Oxyde on 15/05/2016.
 */
public class NoteComp extends JLabel implements ViewComp {

    static {
        try {
            BufferedImage image = ImageIO.read(new File("res/notecomp.png"));
            noteImage = new ImageIcon(image);
        } catch (IOException e) {
        }
    }

    public NoteComp(ModelObj model)
    {
        super();
        this.model = model;
        setIcon(noteImage);
        setSize(noteImage.getIconWidth(), noteImage.getIconHeight());
    }

    @Override
    public ModelObj getModel() {
        return model;
    }

    @Override
    public void setModel(ModelObj model) {
        this.model = model;
    }

    private ModelObj model;
    private static ImageIcon noteImage;
}
