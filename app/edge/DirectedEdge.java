package app.edge;
import java.awt.*;
import app.vertex.Vertex;

public class DirectedEdge extends Edge {
    private int source;

    /**
     * 
     * @param couleur
     * @param value
     * @param sommet1
     * @param sommet2
     * @param source
     */
    public DirectedEdge(Color couleur, double value, Vertex sommet1, Vertex sommet2, int source){
        super(couleur, value, sommet1, sommet2);
        this.source = source;
    }

    /**
     * Retourne un entier qui vaut soit, 0 ou 1, qui indique la position dans le tableau "ends" à laquelle se trouve l'origine de l'arête
     * @return Un entier qui correspond a la valeur stockée par source
     */
    public int getSource(){
        return this.source;
    }

    /**
     * Retourne le sommet (Vertex) sur lequel pointe l'arête 
     * @return Un Vertex qui est destinataire de cette arrête
     */
    public Vertex getSink(){
        if(this.getSource() == 1){
            return this.ends[0];
        }
        return this.ends[1];
    }
}
