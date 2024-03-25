//Kacper
package PSO_GUI;


import javax.swing.*;
import java.awt.*;

public class ShowChoosePresetDialog {

    public static void showChoosePresetDialog(Component parentComponent) {
        String[] languages = {"Funkcja1", "Funkcja2", "Funkcja3","Funkcja4","Funkcja5"};

        String selectedLanguage = (String) JOptionPane.showInputDialog(parentComponent,
                "Choose function:", "Choose Preset",
                JOptionPane.PLAIN_MESSAGE, null,
                languages, languages[0]);

        if (selectedLanguage != null) {
            JOptionPane.showMessageDialog(parentComponent, "Chosen function: " + selectedLanguage);
        }
    }
}
