//Tymoteusz
package PSO_GUI;

import javax.swing.*;
import java.awt.*;

import static PSO_GUI.Utility.remap;

public class Particle {
    private Vector pos, vel, acc;
    private Function f;
    double maxVel=1e-2;
    double maxForce= maxVel*1e-3;
    public Best LB;

    public Particle(Function func, double x, double y){
        f = func;
        pos = new Vector(x, y);
        vel = new Vector(2*Math.random()-1, 2*Math.random()-1);
        vel.setLength(maxVel);
        acc = new Vector(0, 0);

        LB = new Best(pos, f.val(pos.x, pos.y));
    }

    public void draw(Graphics g){
        double x = Utility.remap(pos.x, f.xMin, f.xMax, 0, f.W);
        double y = Utility.remap(pos.y, f.yMin, f.yMax, 0, f.H);
        g.setColor(Color.blue);
        g.fillOval((int)x, (int)y, 6, 6);
    }

    public void update(){
        vel.add(acc);
        acc.mult(0);
        vel.limit(maxVel);
        pos.add(vel);

        if(LB.pos.x >= f.xMin && LB.pos.x <= f.xMax && LB.pos.y >= f.yMin && LB.pos.y <= f.yMax){
            if(LB.val > f.val(pos.x, pos.y)){
                LB.val = f.val(pos.x, pos.y);
                LB.pos.x = pos.x;
                LB.pos.y = pos.y;
            }
        }
    }

    public Vector seek(Best target){
        Vector desired = new Vector(target.pos.x - pos.x, target.pos.y - pos.y);

        double distance = desired.length();
        if(distance < (f.xMax - f.xMin)/5){
            double newVel = remap(distance, 0, (f.xMax - f.xMin)/5, 0, maxVel);
            desired.setLength(newVel);
        }else{
            desired.setLength(maxVel);
        }
        Vector steerF = new Vector(desired.x - vel.x, desired.y - vel.y);
        steerF.limit(maxForce);
        return steerF;
    }

    public void search(Best GB){
        Vector steer = new Vector(0, 0);
        if(GB != null){
            Vector steerGB = seek(GB);
            steerGB.mult(0.8);
            steer.add(steerGB);
        }

        Vector steerLB = seek(LB);
        steerLB.mult(0.2);
        steer.add(steerLB);

        acc.add(steer);
    }

    //testy
//    public static void main(String[] args){
////        for(int i=0; i<10; i++) {
////            System.out.println(Math.random());
////        }
//
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                JFrame frame = new JFrame("Image Panel Example");
//                frame.setSize(600, 600);
//                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                frame.setLocationRelativeTo(null);
//                Function function1 = new Function(600, 600, Function.FunctionType.BEALE);
//                FunctionPanel rightPanel = new FunctionPanel(function1.getBufferedImage());
//
//                for(int i=0; i<40; i++){
//                    rightPanel.addParticle(function1);
//                }
//
//                frame.add(rightPanel);
//                frame.setVisible(true);
//
//                Thread thread = new Thread(rightPanel);
//                thread.start();
//            }
//        });
//    }


}
