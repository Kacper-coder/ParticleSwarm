//Tymoteusz
package PSO_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Function{
    private FunctionPanel functionPanel;
    private int rLow=36, gLow=70, bLow=86;
//    private int rLow=0, gLow=0, bLow=0; //czarny
//    private int rHigh=255, gHigh=255, bHigh=174; //żółtawy
//    private int rHigh=0, gHigh=128, bHigh=128; //trochę jaśniejszy teal
//    private int rHigh=50, gHigh=153, bHigh=153; //jeszcze trochę jaśniejszy teal
//    private int rHigh=102, gHigh=178, bHigh=178; //jeszcze jeszcze
    private int rHigh=153, gHigh=204, bHigh=204;//jeszcze jeszcze jaśniejszy
//    private int rHigh=178, gHigh=216, bHigh=216; //blady
    private BufferedImage functionImage;

    public enum FunctionType{
        BEALE,
        OTHER1,
        OTHER2
    }
    private FunctionType function = FunctionType.BEALE;

    public int W, H;
    double xMax, xMin, yMax, yMin, vMin, vMax;

    public BufferedImage getBufferedImage(){
        return functionImage;
    }

    public Function(int W, int H, FunctionType chosenFunction){

        this.W = W;
        this.H = H;

        switch(chosenFunction){
            case BEALE:
                xMin = -4.5;
                xMax = 4.5;
                yMin = -4.5;
                yMax = 4.5;
                vMin = 1e-5;
                vMax = 2e6;
            case OTHER1:
            case OTHER2:
        }

        functionImage = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        create();
    }

    public double remap(double n, double start1, double stop1, double start2, double stop2){
        return (n - start1) / (stop1 - start1) * (stop2 -start2) + start2;
    }

    //stworzenie bitmapy punkt po punkcie
    public void create(){
        Graphics2D g2d = functionImage.createGraphics();
        for(int i=0; i<W; i++){
            for(int j=0; j<H; j++){
                //mapowanie ekranu na dziedzinę funkcji
                double x = remap(i, 0, W-1, xMin, xMax);
                double y = remap(j, 0, H-1, yMin, yMax);
                double v = val(x, y);
                //mapowanie wartości funkcji na kolor
                double r = remap(Math.log(v), Math.log(vMin), Math.log(vMax), rLow, rHigh);
                double g = remap(Math.log(v), Math.log(vMin), Math.log(vMax), gLow, gHigh);
                double b = remap(Math.log(v), Math.log(vMin), Math.log(vMax), bLow, bHigh);
                //rysowanie punkt po punkcie
                int rgb = ((int)r << 16) | ((int)g << 8) | (int)b;
//                functionImage.setRGB(i, j, rgb);
                g2d.setColor(new Color((int)r,(int)g ,(int)b));
                g2d.fillRect(i, j, 1, 1);
            }
        }
        g2d.dispose();
    }

    public double val(double x, double y){
        switch(function){
            //wzór funkcji Beale'a
            case BEALE:
                return Math.pow((1.5-x+x*y), 2) + Math.pow((2.25-x+x*Math.pow(y, 2)) , 2) + Math.pow((2.625-x+x*Math.pow(y, 3)), 2);
            case OTHER1:
                return 0;
            case OTHER2:
                return 1;
            default:
                throw new IllegalArgumentException("Invalid function value: " + function);
        }
    }
}
