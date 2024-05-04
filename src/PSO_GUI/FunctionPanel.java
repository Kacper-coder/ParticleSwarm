//Tymoteusz
package PSO_GUI;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FunctionPanel extends JPanel implements Runnable{
    private BufferedImage imageOfFunction;
    List<Particle> particles = new ArrayList<Particle>();
    boolean running;
    Best GB = null;
    int swarmSize = 200;

    public void setSwarmSize(int swarmSize){
        this.swarmSize = swarmSize;
    }

    public int getSwarmSize(){
        return swarmSize;
    }

    public FunctionPanel(BufferedImage image){
        imageOfFunction = image;
    }

    public void addParticle(Function f){
        Particle p = new Particle(f, Math.random()*(f.xMax-f.xMin)+f.xMin, Math.random()*(f.yMax-f.yMin)+f.yMin);
//        Particle p = new Particle(f, Math.random()*590, Math.random()*590);
        particles.add(p);
    }

    public void deleteParticles(){
        particles.clear();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(imageOfFunction,0,0,null);
        for(Particle p : particles){
            p.draw(g);
        }
    }

    @Override
    public void run(){
        while(running){
            for(Particle p : particles){
                p.search(GB);
                p.update();
            }
            repaint();

            for(Particle p : particles){
                if(GB != null){
                    if(p.LB.val < GB.val){
                        GB.val = p.LB.val;
                        GB.pos.x = p.LB.pos.x;
                        GB.pos.y = p.LB.pos.y;
                    }
                }else{
                    GB = new Best(p.LB.pos, p.LB.val);
                }
            }

//            if(GB != null){
//                CircleShape c(5);
//                c.setFillColor(Color(255,0,0));
//                double x = remap(GB->pos->x, f.xMin, f.xMax, 0, f.W);
//                double y = remap(GB->pos->y, f.yMin, f.yMax, 0, f.H);
//                c.setPosition(x,y);
//                window.draw(c);
//            }

            Toolkit.getDefaultToolkit().sync();

            try{
                Thread.sleep(2);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void stop(){
        running = false;
    }

    public void start(){
        if(running == false)
            running = true;
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
