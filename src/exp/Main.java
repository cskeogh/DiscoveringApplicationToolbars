package exp;

import common.Settings;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import exp.view.MainFrame;
import common.presenter.MainPresenter;

/**
 * Created by Oxyde on 9/05/2016.
 */
public class Main {

    public Main()
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    UIManager.setLookAndFeel
                            (UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e)
                {
                    // do not care if can't set look and feel, continue.
                }
                MainFrame mainFrame = new MainFrame(
                        new MainPresenter(Settings.ResearchOptions.Experimental),
                        Settings.ApplicationName);
                mainFrame.setVisible(true);
            }
        });

    }

    public static void main(String[] args) {
        new Main();
    }
}
