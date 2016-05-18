package common.presenter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * Created by Oxyde on 18/05/2016.
 */
public class Loader {
    public static URL res(String path)
    {
        URL url = Loader.class.getClassLoader().getResource(path);
        if (url == null)
        {
            try {
                url = new File(path).toURI().toURL();
            } catch (MalformedURLException e) {
            }
        }
        return url;
    }
}
