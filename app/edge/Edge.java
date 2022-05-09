package edge;
import java.awt.*;

import vertex.Vertex;

abstract class Edge {
    private int id;
    private Color couleur;
    protected Vertex[] ends;
    private double value;
    private static int nb_edge=1;


    /**
     * Construit un Objet de Classe Edge, elle ne peut pas être utilisé directement ici car c'est une classe abstraite,
     * ce constructeur va servir dans les classes qui héritent de celle-ci
     * @param couleur couleur de l'arc
     * @param value la valeur contenue par cet arc
     * @param sommet1   le premier sommet qui est une des deux extremités de cet arc
     * @param sommet2   le deuxième sommet à l'autre extremité de l'arc
     * @return un objet de classe Edge, qui est un arc
     */
    public Edge(Color couleur, double value, Vertex sommet1, Vertex sommet2){
        this.id = nb_edge;
        nb_edge++;
        this.couleur = couleur;
        this.value = value;
        this.ends = new Vertex[2];
        this.ends[0] = sommet1;
        this.ends[1] = sommet2;
    }

}
