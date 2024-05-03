//Kacper
package PSO_GUI;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class SaveFile {
    public File saveSimToFile(int swarmSize, float maxForce, float maxVelocity, float alpha, float beta, ArrayList<Double> GB){
        JFileChooser fileChooser = new JFileChooser();
        int select = fileChooser.showSaveDialog(null);

        if(select == JFileChooser.APPROVE_OPTION){
            File filePath = fileChooser.getSelectedFile();
            JSONObject jsonData = parseData(swarmSize, maxForce, maxVelocity, alpha, beta, GB);

            try( FileWriter writer = new FileWriter(filePath)) {
                writer.write(jsonData.toString());
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "File saving failed!"+e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            };
        }

        return null;
    };

    public JSONObject parseData(int swarmSize, float maxForce, float maxVelocity, float alpha, float beta, ArrayList<Double> GB){
        JSONObject jsonData = new JSONObject();

        jsonData.put("Swarm Size", swarmSize);
        jsonData.put("Maximum Steering Force", maxForce);
        jsonData.put("Maximum Particle Velocity", maxVelocity);
        jsonData.put("Intelligence Alpha", alpha);
        jsonData.put("Intelligence Beta", beta);

        JSONArray GBarray = new JSONArray();
        for(double value : GB){
            GBarray.put(value);
        }
        jsonData.put("Global Best", GBarray);
        return jsonData;
    };
}