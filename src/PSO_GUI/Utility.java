package PSO_GUI;

public final class Utility {

    private Utility(){
        throw new AssertionError("Utility class cannot be instantiated");
    }

    public static double remap(double n, double start1, double stop1, double start2, double stop2){
        return (n - start1) / (stop1 - start1) * (stop2 -start2) + start2;
    }
}
