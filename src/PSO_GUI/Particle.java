//Tymoteusz
package PSO_GUI;

import javax.swing.*;
import java.awt.*;

import static PSO_GUI.Utility.remap;

public class Particle {
    private Vector pos, vel, acc;
    private Function f;
    //maksymalna dozwolona predkość - określa "krok" przeszukiwania przestrzeni, czyli dokładność
    static double maxVel=1e-2;
    //maksymalna siła sterująca - określa "bezwładność" czątki, czyli gwałtowność ruchu, przyczynia się do zwiększenia losowości ruchu
    static double maxForce= maxVel*1e-3;
    public Best LB;
//    public static Best LB;
    public static double alfa=0.8, beta=0.2;

    public Particle(Function func, double x, double y){
        f = func;
        pos = new Vector(x, y);
        vel = new Vector(2*Utility.getRandom()-1, 2*Utility.getRandom()-1);
        vel.setLength(maxVel);
        acc = new Vector(0, 0);

        LB = new Best(pos, f.val(pos.x, pos.y));
    }

//    public static void nullBest(){
//        LB = null;
//    }

    public void draw(Graphics g){
        //remapowanie pozycji z dziedziny funkcji na obszar okna
        double x = Utility.remap(pos.x, f.xMin, f.xMax, 0, f.W);
        double y = Utility.remap(pos.y, f.yMin, f.yMax, 0, f.H);
        g.setColor(Color.blue);
        g.fillOval((int)x, (int)y, 5, 5);
    }

    //aktualizacja parametrów ruchu cząstki
    public void update(){
        vel.add(acc);
        acc.mult(0);
        vel.limit(maxVel);
        pos.add(vel);

        //aktualizacja minimum lokalnego
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
            //spowolnienie cząstki w pobliżu minimum
            double newVel = remap(distance, 0, (f.xMax - f.xMin)/5, 0, maxVel);
            desired.setLength(newVel);
        }else{
            //w przeciwnym przypadku prędkość maksymalna
            desired.setLength(maxVel);
        }
        //wyznaczenie wektora siły sterującej - różnica między aktualną prędkością, a wyliczoną prędkością pożądana
        Vector steerF = new Vector(desired.x - vel.x, desired.y - vel.y);
        //ograniczenie maksymalnej wielkości siły sterującej, żeby zachować płynność ruchu cząstki i pozwolić jej "błądzić"
        steerF.limit(maxForce);
        return steerF;
    }

    public void search(Best GB){
        Vector steer = new Vector(0, 0);
        if(GB != null){
            Vector steerGB = seek(GB);
            steerGB.mult(alfa); //alfa
            steer.add(steerGB);
        }

        Vector steerLB = seek(LB);
        steerLB.mult(beta); //beta
        steer.add(steerLB);

        //wyznaczenie siły wypadkowej
        acc.add(steer);
    }

    public static void setAlfa(double n){
        alfa=n;
        System.out.println("alfa set");
    }

    public static void setBeta(double n){
        beta=n;
        System.out.println("beta set");
    }

    public static double getAlfa(){
        return alfa;
    }

    public static double getBeta(){
       return beta;
   }

   public static void setMaxVel(double n){
        maxVel = n;
       System.out.println("maxVel set");
   }

   public static void setMaxForce(double n){
        maxForce = n;
       System.out.println("maxForce set");
   }

   public static double getMaxVel(){
        return maxVel;
   }

   public static double getMaxForce(){
        return maxForce;
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
