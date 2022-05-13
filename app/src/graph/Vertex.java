package graph;
import java.awt.*;

public class Vertex{
    private int id;
    private String info;
    private Color couleur;
    private static int nb_Vertex = 1;


    /**
     * Constructeur de la classe Vertex
     * @param couleur Objet couleur qui peut être utilisé comme une énumération du type "Color.blue"
     * @param info L'information contenue par cet arc
     * @return Un sommet avec un identifiant unique
     */
    public Vertex(Color couleur, String info){
        this.id = nb_Vertex;
        nb_Vertex++;
        this.couleur = couleur;
        this.info = info;
    }

    public int getId(){
        return this.id;
    }

    public int getNbVertex(){
        return nb_Vertex;
    }

    /**
     * Methode toString pour la classe Vertex
     */
    public String toString(){
        return "sommet d'identifiant : "+this.id+ " contenant l'information : "+this.info;
    }
}