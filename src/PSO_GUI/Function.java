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
        ACKLEY,
        HIMMELBLAU,
        GOLDSTEIN,
        BUKIN
    }
//    private FunctionType function = FunctionType.BEALE;
    public FunctionType function = FunctionType.BEALE;

    public void setFunctionType(FunctionType functionType){
        function = functionType;
        create();
    }



    public int W, H;
    double xMax, xMin, yMax, yMin, vMin, vMax;

    boolean logScale = true;

    public BufferedImage getBufferedImage(){
        return functionImage;
    }

    public Function(int W, int H, FunctionType chosenFunction){

        this.W = W;
        this.H = H;

        function = chosenFunction;

        switch(chosenFunction){
            case BEALE:
                xMin = -4.5;
                xMax = 4.5;
                yMin = -4.5;
                yMax = 4.5;
                vMin = 1e-5;
                vMax = 2e6;
                logScale=true;
                break;
            case ACKLEY:
                xMin = -5;
                xMax = 5;
                yMin = -5;
                yMax = 5;
                vMin = 0;
                vMax = 15;
                logScale=false;
                break;
            case HIMMELBLAU:
                xMin = -5;
                xMax = 5;
                yMin = -5;
                yMax = 5;
                vMin = 1e-3;
                vMax = 1e3;
                logScale=true;
                break;
            case GOLDSTEIN:
                xMin = -2;
                xMax = 2;
                yMin = -3;
                yMax = 1;
                vMin = 1;
                vMax = 1e7;
                logScale=true;
                break;
            case BUKIN:
                xMin = -15;
                xMax = -5;
                yMin = -4;
                yMax = 6;
                vMin = 0;
                vMax = 250;
                logScale=false;
                break;
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
                double r, g, b;
                if(logScale){
                    r = remap(Math.log(v), Math.log(vMin), Math.log(vMax), rLow, rHigh);
                    g = remap(Math.log(v), Math.log(vMin), Math.log(vMax), gLow, gHigh);
                    b = remap(Math.log(v), Math.log(vMin), Math.log(vMax), bLow, bHigh);
                }else{
                    r = remap(v, vMin, vMax, rLow, rHigh);
                    g = remap(v, vMin, vMax, gLow, gHigh);
                    b = remap(v, vMin, vMax, bLow, bHigh);
                }
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
            case ACKLEY:
                return -20*Math.exp(-0.2*Math.sqrt(0.5*(Math.pow(x, 2) + Math.pow(y, 2)))) - Math.exp(0.5*(Math.cos(2*3.14*x)+Math.cos(2*3.14*y))) + Math.exp(1) + 20;
            case HIMMELBLAU:
                //do zmiany
                return Math.pow(x*x+y-11, 2) + Math.pow(x+y*y-7, 2);
            case GOLDSTEIN:
                return (1+Math.pow(x+y+1, 2)*(19-14*x+3*Math.pow(x, 2)-14*y+6*x*y+3*Math.pow(y, 2)))*(30+Math.pow((2*x-3*y), 2)*(18-32*x+12*Math.pow(x, 2) + 48*y-36*x*y+27*Math.pow(y, 2)));
            case BUKIN:
                return 100*Math.sqrt(Math.abs(y-0.01*x*x)) + 0.01*Math.abs(x+10);
            default:
                throw new IllegalArgumentException("Invalid function value: " + function);
        }
    }
}
