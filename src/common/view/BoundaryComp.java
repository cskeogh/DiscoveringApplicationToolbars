package common.view;

import common.model.ModelObj;
import java.awt.*;
import javax.swing.*;

/**
 * Created by Oxyde on 12/05/2016.
 */
public class BoundaryComp extends JComponent implements ViewComp {

    public BoundaryComp(ModelObj model)
    {
        super();
        this.model = model;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public ModelObj getModel() {
        return model;
    }

    public void setModel(ModelObj model) {
        this.model = model;
    }

    private ModelObj model;
}
