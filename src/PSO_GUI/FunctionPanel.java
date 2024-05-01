//Tymoteusz
package PSO_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FunctionPanel extends JPanel {
    private BufferedImage imageOfFunction;
    List<Particle> particles = new ArrayList<Particle>();

    public FunctionPanel(BufferedImage image){
        imageOfFunction = image;
    }

    //nowe
    public void addParticle(Function f){
        Particle p = new Particle(f, Math.random()*(f.xMax-f.xMin)+f.xMin, Math.random()*(f.yMax-f.yMin)+f.yMin);
//        Particle p = new Particle(f, Math.random()*590, Math.random()*590);
        particles.add(p);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(imageOfFunction,0,0,null);
        for(Particle p : particles){
            p.draw(g);
        }
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
