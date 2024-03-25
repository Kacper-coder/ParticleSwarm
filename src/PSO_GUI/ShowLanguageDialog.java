//Kacper
package PSO_GUI;


import javax.swing.*;
import java.awt.*;

public class ShowLanguageDialog {

    public static void showLanguageDialog(Component parentComponent) {
        String[] languages = {"Polski", "English", "Español"};

        String selectedLanguage = (String) JOptionPane.showInputDialog(parentComponent,
                "Choose language:", "Language settings",
                JOptionPane.PLAIN_MESSAGE, null,
                languages, languages[0]);

        if (selectedLanguage != null) {
            JOptionPane.showMessageDialog(parentComponent, "Wybrany język: " + selectedLanguage);
        }
    }
}
