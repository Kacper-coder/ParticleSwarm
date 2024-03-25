package PSO_GUI;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoadFile {

    private File selectedFile;

    public String loadFile(Component parentComponent) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        int choosenFile = fileChooser.showOpenDialog(parentComponent);
        if(choosenFile == JFileChooser.APPROVE_OPTION){
            selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(parentComponent, LanguageManager.getMessage("chosen_file")+selectedFile.getAbsolutePath());
            return readFileAsString(selectedFile);
        } else{
            selectedFile = null;
            JOptionPane.showMessageDialog(parentComponent, LanguageManager.getMessage("load_file_prompt"));
            return null;
        }
    }

    public static String readFileAsString(File filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }

    public void parseJSON(String jsonString){
        JSONObject jsonData = new JSONObject(jsonString);

        int swarmSize = jsonData.getInt("Swarm Size");
        float maxForce = jsonData.getFloat("Maximum Steering Force");
        float maxVelocity = jsonData.getFloat("Maximum Particle Velocity");
        float alpha = jsonData.getFloat("Intelligence Alpha");
        float beta = jsonData.getFloat("Intelligence Beta");

        System.out.println("Swarm Size: " + swarmSize);
        System.out.println("Maximum Steering Force: " + maxForce);
        System.out.println("Maximum Particle Velocity: " + maxVelocity);
        System.out.println("Intelligence Alpha: " + alpha);
        System.out.println("Intelligence Beta: " + beta);
    }
}
