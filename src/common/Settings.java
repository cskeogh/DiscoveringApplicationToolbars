package common;

/**
 * Created by Oxyde on 9/05/2016.
 */
public class Settings {
    public enum ResearchOptions {
        Control('C'), Experimental('E');

        ResearchOptions(char value)
        {
            this.value = value;
        }

        public char getValue()
        {
            return value;
        }

        private final char value;
    }

    public static final String ApplicationName = "Modelling Application";
    public static final String MainToolbarName = "Modelling Functions";
    public static final String LogFilenamePrefix = "ToolbarResearch_";
    public static final String QuitDialogTItle = "Exit Modelling Application Warning";
    public static final int BoundaryWidth = 200;
    public static final int BoundaryHeight = 200;
    public static final int Spacing = 10;
    public static final int MenuSpacing = 2;
}
