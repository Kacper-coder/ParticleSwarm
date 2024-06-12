//Kacper
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
            parseJSON(readFileAsString(selectedFile));
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
        double maxForce = jsonData.getDouble("Maximum Steering Force");
        double maxVelocity = jsonData.getDouble("Maximum Particle Velocity");
        double alpha = jsonData.getDouble("Intelligence Alpha");
        double beta = jsonData.getDouble("Intelligence Beta");

        System.out.println("Swarm Size: " + swarmSize);
        System.out.println("Maximum Steering Force: " + maxForce);
        System.out.println("Maximum Particle Velocity: " + maxVelocity);
        System.out.println("Intelligence Alpha: " + alpha);
        System.out.println("Intelligence Beta: " + beta);

        Particle.setMaxForce(maxForce);
        Particle.setMaxVel(maxVelocity);
        Particle.setAlfa(alpha);
        Particle.setBeta(beta);
        FunctionPanel.setSwarmSize(swarmSize);
    }
}
