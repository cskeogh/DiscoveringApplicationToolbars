package exp;

import javax.swing.UIManager;
import javax.swing.SwingUtilities;

/**
 * Created by Oxyde on 9/05/2016.
 */
public class Main {

    private Main()
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
                //ImageFactory.loadIcons();
                /*
                MainFrame mainFrame = new MainFrame(Settings.ApplicationName);
                mainFrame.setVisible(true);*/
            }
        });

    }

    public static void main(String[] args) {
        new Main();
    }
}
