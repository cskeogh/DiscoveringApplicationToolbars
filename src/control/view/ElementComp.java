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
public class ElementComp extends JLabel implements ViewComp {

    static {
        try {
            BufferedImage image = ImageIO.read(new File("res/elementcomp.png"));
            elementImage = new ImageIcon(image);
        } catch (IOException e) {
        }
    }

    public ElementComp(ModelObj model)
    {
        super();
        this.model = model;
        setIcon(elementImage);
        setSize(elementImage.getIconWidth(), elementImage.getIconHeight());
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
    private static ImageIcon elementImage;
}
