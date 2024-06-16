//Kacper
package PSO_GUI;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class SaveFile {
    public File saveSimToFile(Component parentComponent, int swarmSize, double maxForce, double maxVelocity, double alpha, double beta, double seed){
        JFileChooser fileChooser = new JFileChooser();
        int select = fileChooser.showSaveDialog(parentComponent);

        if(select == JFileChooser.APPROVE_OPTION){
            File filePath = fileChooser.getSelectedFile();
            JSONObject jsonData = parseData(swarmSize, maxForce, maxVelocity, alpha, beta, seed);

            try( FileWriter writer = new FileWriter(filePath)) {
                writer.write(jsonData.toString());
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "File saving failed!"+e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            };
        }

        return null;
    };

    public JSONObject parseData(int swarmSize, double maxForce, double maxVelocity, double alpha, double beta, double seed){
        JSONObject jsonData = new JSONObject();

        jsonData.put("Swarm Size", swarmSize);
        jsonData.put("Maximum Steering Force", maxForce);
        jsonData.put("Maximum Particle Velocity", maxVelocity);
        jsonData.put("Intelligence Alpha", alpha);
        jsonData.put("Intelligence Beta", beta);
        jsonData.put("Seed",seed);

//        JSONArray GBarray = new JSONArray();
//        for(double value : GB){
//            GBarray.put(value);
//        }
//        jsonData.put("Global Best", GBarray);
        return jsonData;
    };
}
