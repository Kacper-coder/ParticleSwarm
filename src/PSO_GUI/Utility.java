package PSO_GUI;

import java.util.Random;

public final class Utility {

    public static Random generator;

    private Utility(){
        throw new AssertionError("Utility class cannot be instantiated");
    }

    public static double remap(double n, double start1, double stop1, double start2, double stop2){
        return (n - start1) / (stop1 - start1) * (stop2 -start2) + start2;
    }

    public static void initRandom(long seed){
        generator = new Random(seed);
    }

    public static double getRandom(){
        return generator.nextDouble();
    }

}
