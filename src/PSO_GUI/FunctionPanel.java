//Tymoteusz
package PSO_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FunctionPanel extends JPanel {
    private BufferedImage imageOfFunction;

    public FunctionPanel(BufferedImage image){
        imageOfFunction = image;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(imageOfFunction,0,0,null);
    }

//    public static void main(String[] args){
//        JFrame frame = new JFrame("Image Panel Example");
//        frame.setSize(600, 600);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        Function function = new Function(600, 600, Function.FunctionType.BEALE);
//        frame.add(new FunctionPanel(function.getBufferedImage()));
//        frame.setVisible(true);
//    }

}
