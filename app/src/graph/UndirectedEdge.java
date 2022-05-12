package graph;
import java.awt.*;

public class UndirectedEdge extends Edge{


    /**
     * Construit un objet de classe UndirectedEdge à l'aide du constructeur de la superclasse
     * @param couleur Un objet Color qui s'utilise comme énumération "Color.blue"
     * @param value Valeur stockée par l'arête
     * @param sommet1   Un des deux sommets qui sont reliés par l'arête
     * @param sommet2   Le deuxième sommet, diffèrent de "sommet1"
     * @return Un objet de Classe UndirectedEdge qui hérite les fields et les méthodes de Edge
     */
    public UndirectedEdge(Color couleur, double value, Vertex sommet1, Vertex sommet2){
        super(couleur, value, sommet1, sommet2);
    }

    /**
     * Retourne les deux sommets reliés par cette arête
     * @return Un tableau de dimension 2 contenant les deux objets Vertex
     */
    public Vertex[] getEnds(){
        return this.ends;
    }

    /**
     * Methode toString qui réutilise celui du duperConstructeur
     */
    public String toString(){
        return super.toString()+" C\'est une arête non dirigé. Les sommets sont "+this.ends[0]+" et "+this.ends[1];
    }
}
