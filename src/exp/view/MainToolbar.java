package exp.view;

import control.presenter.MainPresenter;
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
        MyToolTipManager.sharedInstance().setPresenter(presenter);

        JButton boundaryButton = new JButton(new ImageIcon("res/boundary.png"));
        boundaryButton.setToolTipText("Add Boundary");
        MyToolTipManager.sharedInstance().registerComponent(boundaryButton);
        boundaryButton.addActionListener(presenter.getAddBoundaryAction());

        JButton elementButton = new JButton(new ImageIcon("res/element.png"));
        elementButton.setToolTipText("Add Element");
        MyToolTipManager.sharedInstance().registerComponent(elementButton);
        elementButton.addActionListener(presenter.getElementAction());

        JButton imageButton = new JButton(new ImageIcon("res/image.png"));
        imageButton.setToolTipText("Add Image");
        MyToolTipManager.sharedInstance().registerComponent(imageButton);
        imageButton.addActionListener(presenter.getImageAction());

        JButton lineButton = new JButton(new ImageIcon("res/line.png"));
        lineButton.setToolTipText("Add Line");
        MyToolTipManager.sharedInstance().registerComponent(lineButton);
        lineButton.addActionListener(presenter.getLineAction());

        JButton linkButton = new JButton(new ImageIcon("res/link.png"));
        linkButton.setToolTipText("Add Link");
        MyToolTipManager.sharedInstance().registerComponent(linkButton);
        linkButton.addActionListener(presenter.getLinkAction());

        JButton noteButton = new JButton(new ImageIcon("res/note.png"));
        noteButton.setToolTipText("Add Note");
        MyToolTipManager.sharedInstance().registerComponent(noteButton);
        noteButton.addActionListener(presenter.getNoteAction());

        JButton relationshipButton = new JButton(new ImageIcon("res/relationship.png"));
        relationshipButton.setToolTipText("Add Relationship");
        MyToolTipManager.sharedInstance().registerComponent(relationshipButton);
        relationshipButton.addActionListener(presenter.getRelationshipAction());

        JButton alignTopButton = new JButton(new ImageIcon("res/aligntop.png"));
        alignTopButton.setToolTipText("Align Top");
        MyToolTipManager.sharedInstance().registerComponent(alignTopButton);
        alignTopButton.addActionListener(presenter.getAlignTopAction());

        JButton alignLeftButton = new JButton(new ImageIcon("res/alignleft.png"));
        alignLeftButton.setToolTipText("Align Left");
        MyToolTipManager.sharedInstance().registerComponent(alignLeftButton);
        alignLeftButton.addActionListener(presenter.getAlignLeftAction());

        JButton alignRightButton = new JButton(new ImageIcon("res/alignright.png"));
        alignRightButton.setToolTipText("Align Right");
        MyToolTipManager.sharedInstance().registerComponent(alignRightButton);
        alignRightButton.addActionListener(presenter.getAlignRightAction());

        JButton alignBottomButton = new JButton(new ImageIcon("res/alignbottom.png"));
        alignBottomButton.setToolTipText("Align Bottom");
        MyToolTipManager.sharedInstance().registerComponent(alignBottomButton);
        alignBottomButton.addActionListener(presenter.getAlignBottomAction());

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
