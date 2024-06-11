//Kacper
package PSO_GUI;


import javax.swing.*;
import java.awt.*;

public class ShowChoosePresetDialog {

    public static void showChoosePresetDialog(Component parentComponent) {
        String[] languages = {"Beale", "Ackley", "Himmelblau","Goldstein","Bukin"};

        String selectedLanguage = (String) JOptionPane.showInputDialog(parentComponent,
                LanguageManager.getMessage("function"), LanguageManager.getMessage("preset"),
                JOptionPane.PLAIN_MESSAGE, null,
                languages, languages[0]);

        Function function;
        switch(selectedLanguage){
            case "Beale":
                function = new Function(600, 600, Function.FunctionType.BEALE);

                break;
            case "Ackley":
                function = new Function(600, 600, Function.FunctionType.ACKLEY);
                break;
            case "Himmelblau":
                function = new Function(600, 600, Function.FunctionType.HIMMELBLAU);
                break;
            case "Goldstein":
                function = new Function(600, 600, Function.FunctionType.GOLDSTEIN);
                break;
            case "Bukin":
                function = new Function(600, 600, Function.FunctionType.BUKIN);
                break;
        }

        if (selectedLanguage != null) {
            JOptionPane.showMessageDialog(parentComponent, LanguageManager.getMessage("function_prompt")+ selectedLanguage);
        }
    }
}
