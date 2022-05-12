package graph;
import graph.*;
import java.awt.*;
public class MyTest {
    public static void main(String[] args) {
        Vertex A = new Vertex(Color.blue, "A");
        Vertex B = new Vertex(Color.green, "B");
        DirectedEdge E = new DirectedEdge(Color.red,25,A,B,1);

        System.out.println(E);
    }
}
