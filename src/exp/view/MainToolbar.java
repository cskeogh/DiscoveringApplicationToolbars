package exp.view;

import common.presenter.MainPresenter;
import javax.swing.*;

/**
 * Created by Oxyde on 9/05/2016.
 */
public class MainToolbar extends JToolBar {
    public enum ToolbarButtons {

    }

    public MainToolbar(MainPresenter presenter, String name, int orientation)
    {
        super(name, orientation);
        this.presenter = presenter;
        initialise();
    }

    private void initialise()
    {
        ToolTipManager.sharedInstance().setEnabled(false);

        JButton boundaryButton = new JButton(new ImageIcon("res/boundary.png"));
        boundaryButton.setToolTipText("Add Boundary");
        boundaryButton.addActionListener(presenter.getAddBoundaryAction());
        boundaryButton.addMouseListener(presenter.getToolbarMouse());

        JButton elementButton = new JButton(new ImageIcon("res/element.png"));
        elementButton.setToolTipText("Add Element");
        elementButton.addActionListener(presenter.getElementAction());
        elementButton.addMouseListener(presenter.getToolbarMouse());

        JButton imageButton = new JButton(new ImageIcon("res/image.png"));
        imageButton.setToolTipText("Add Image");
        imageButton.addActionListener(presenter.getImageAction());
        imageButton.addMouseListener(presenter.getToolbarMouse());

        JButton lineButton = new JButton(new ImageIcon("res/line.png"));
        lineButton.setToolTipText("Add Line");
        lineButton.addActionListener(presenter.getLineAction());
        lineButton.addMouseListener(presenter.getToolbarMouse());

        JButton linkButton = new JButton(new ImageIcon("res/link.png"));
        linkButton.setToolTipText("Add Link");
        linkButton.addActionListener(presenter.getLinkAction());
        linkButton.addMouseListener(presenter.getToolbarMouse());

        JButton noteButton = new JButton(new ImageIcon("res/note.png"));
        noteButton.setToolTipText("Add Note");
        noteButton.addActionListener(presenter.getNoteAction());
        noteButton.addMouseListener(presenter.getToolbarMouse());

        JButton relationshipButton = new JButton(new ImageIcon("res/relationship.png"));
        relationshipButton.setToolTipText("Add Relationship");
        relationshipButton.addActionListener(presenter.getRelationshipAction());
        relationshipButton.addMouseListener(presenter.getToolbarMouse());

        JButton alignTopButton = new JButton(new ImageIcon("res/aligntop.png"));
        alignTopButton.setToolTipText("Align Top");
        alignTopButton.addActionListener(presenter.getAlignTopAction());
        alignTopButton.addMouseListener(presenter.getToolbarMouse());

        JButton alignLeftButton = new JButton(new ImageIcon("res/alignleft.png"));
        alignLeftButton.setToolTipText("Align Left");
        alignLeftButton.addActionListener(presenter.getAlignLeftAction());
        alignLeftButton.addMouseListener(presenter.getToolbarMouse());

        JButton alignRightButton = new JButton(new ImageIcon("res/alignright.png"));
        alignRightButton.setToolTipText("Align Right");
        alignRightButton.addActionListener(presenter.getAlignRightAction());
        alignRightButton.addMouseListener(presenter.getToolbarMouse());

        JButton alignBottomButton = new JButton(new ImageIcon("res/alignbottom.png"));
        alignBottomButton.setToolTipText("Align Bottom");
        alignBottomButton.addActionListener(presenter.getAlignBottomAction());
        alignBottomButton.addMouseListener(presenter.getToolbarMouse());

        //http://stackoverflow.com/questions/12822819/dynamically-update-tooltip-currently-displayed
        add(lineButton);
        add(relationshipButton);
        add(boundaryButton);
        add(elementButton);
        add(imageButton);
        add(linkButton);
        add(noteButton);
        add(alignTopButton);
        add(alignLeftButton);
        add(alignRightButton);
        add(alignBottomButton);
    }

    private MainPresenter presenter;
}
