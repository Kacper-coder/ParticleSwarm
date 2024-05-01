//Tymoteusz
package PSO_GUI;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
    private static ResourceBundle messages = ResourceBundle.getBundle("PSO_GUI.napisy", Locale.forLanguageTag("fr"));

    private static GUI mainFrame;

    public static void setMainFrame(GUI gui){
        mainFrame = gui;
    }

    public static String getMessage(String key){
        return messages.getString(key);
    }

    public static void switchLanguage(Locale locale){
        messages = ResourceBundle.getBundle("PSO_GUI.napisy", locale);
    }
}
