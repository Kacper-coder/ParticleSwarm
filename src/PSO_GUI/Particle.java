//Tymoteusz
package PSO_GUI;

import javax.swing.*;
import java.awt.*;

public class Particle {
    private Vector pos, vel, acc;
    private Function function;
    double maxVel=1e-2;
    double maxForce= maxVel*1e-3;
    public Best LB;

    public Particle(Function func, double x, double y){
        function = func;
        pos = new Vector(x, y);
        vel = new Vector(2*Math.random()-1, 2*Math.random()-1);
        vel.setLength(maxVel);
        acc = new Vector(0, 0);

        LB = new Best(pos, function.val(pos.x, pos.y));
    }

    public void draw(Graphics g){
        g.setColor(Color.blue);
        g.fillOval((int) pos.x, (int)pos.y, 10, 10);
    }

    //testy
    public static void main(String[] args){
//        for(int i=0; i<10; i++) {
//            System.out.println(Math.random());
//        }



        JFrame frame = new JFrame("Image Panel Example");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        Function function1 = new Function(600, 600, Function.FunctionType.BEALE);
        FunctionPanel rightPanel = new FunctionPanel(function1.getBufferedImage());

        for(int i=0; i<40; i++){
            rightPanel.addParticle(function1);
        }

        frame.add(rightPanel);
        frame.setVisible(true);

    }


}
