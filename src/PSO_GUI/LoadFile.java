//Kacper
package PSO_GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
public class LoadFile {

    private File selectedFile;

    public File loadFile(Component parentComponent){
        JFileChooser fileChooser = new JFileChooser();
        int choosenFile = fileChooser.showOpenDialog(parentComponent);
        if(choosenFile == JFileChooser.APPROVE_OPTION){
            selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(parentComponent, "Chosen file:"+selectedFile.getAbsolutePath());
            return selectedFile;
        } else{
            selectedFile = null;
            JOptionPane.showMessageDialog(parentComponent, "Nie wybrano pliku!");
            return null;
        }
    }
}
