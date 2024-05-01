//Tymoteusz
package PSO_GUI;

public class Best {
    public Vector pos;
    public double val;

    public Best(Vector position, double value){
        pos = new Vector(position.x, position.y);
        val = value;
    }
}
