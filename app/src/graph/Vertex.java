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

    /**
     * Retourne la couleur du vertex
     * @return Une couleur du vertex
     */
    public Color getColor(){
        return this.couleur;
    }
    
    /**
     * l'Iidentifiant du vertex
     * @return un entier qui represente l'Iidentifiant du vertex
     */
    public int getId(){
        return this.id;
    }

    /**
     * l'Iinfo enregistrer de le vertex
     * @return un String qui represente      * l'Iinfo enregistrer de le vertex
     */
    public String getInfo(){
        return this.info;
    }
    
    /**
     * Methode toString pour la classe Vertex
     */
    public String toString(){
        return "sommet d'identifiant : "+this.id+ " contenant l'information : "+this.info;
    }
}