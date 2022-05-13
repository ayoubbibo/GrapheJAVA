package graph;
import java.awt.*;

public abstract class Edge {
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
    
    /**
     * Retourne les deux sommets reliés par cette arête
     * @return Un tableau de dimension 2 contenant les deux objets Vertex
     */
    public Vertex[] getEnds(){
        return this.ends;
    }

    /**
     * l'Iidentifiant de l'edge
     * @return un entier qui represente l'Iidentifiant de l'edge
     */
    public int getId(){
        return this.id;
    }

    /**
     * Retourne la valeur enregistrer dans l'edge
     * @return Une valeur de type double 2 enregistrer dans l'edge
     */
    public double getValue(){
        return this.value;
    }

    /**
     * Retourne la couleur de l'edge
     * @return Une couleur de l'edge
     */
    public Color getColor(){
        return this.couleur;
    }
    /**
     * Méthode toString
     */
    public String toString(){
        return "L'arête d'identifiant : "+this.id+" et la couleur est "+this.couleur+"et contenant la valeur : "+this.value+".";
    }

}
