//Kacper & Tymoteusz
package PSO_GUI;


import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class ShowLanguageDialog {

    static boolean shouldRebuildUI = false;

    public static void showLanguageDialog(Component parentComponent) {
        String[] languages = {"Polski", "English", "Français"};

        String selectedLanguage = (String) JOptionPane.showInputDialog(parentComponent,
                LanguageManager.getMessage("lang_prompt"), LanguageManager.getMessage("lang_settings"),
                JOptionPane.PLAIN_MESSAGE, null,
                languages, languages[0]);

        if (selectedLanguage != null) {
            JOptionPane.showMessageDialog(parentComponent, LanguageManager.getMessage("lang_prompt2") + selectedLanguage);
            Locale locale;
            switch (selectedLanguage){
                case "Polski":
                    locale = Locale.forLanguageTag("pl");
                    break;
                case "English":
                    locale = Locale.forLanguageTag("en");
                    break;
                case "Français":
                    locale = Locale.forLanguageTag("fr");
                    break;
                default:
                    locale = Locale.getDefault();
            }
            shouldRebuildUI = true;
            LanguageManager.switchLanguage(locale);
        }
    }
}
