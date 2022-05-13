import app.src.graph.*;

import java.awt.Color;
import java.util.Scanner;

public class IncidenceArrayGraph implements Graph{

    private Vertex[] vertices;

    private Edge[] edges;

    private Edge[][] incidence;

    private boolean directed;


    /**
     * Constructeur qui crée un graphe vide
     */
    public IncidenceArrayGraph(boolean directed){
        this.vertices = new Vertex[0];
        this.edges = new Edge[0];
        this.directed = directed;
        this.incidence = new Edge[0][0];
    }


    /**
     * Retourne le nombre de sommets contenus dans un graphe
     * @return Le nombre de sommets
     */
    public int nbOfVertices(){
        return this.vertices.length;
    }

    /**
     * Retourne le nombre d'arêtes d'un graphe
     * @return Le nombre d'arêtes
     */
    public int nbOfEdges(){
        return this.edges.length;
    }
    
    /**
     * Ajoute un sommet à un graphe
     * @param sommet Un sommet Vertex
     */
    public void addVertex(Vertex sommet){
        if(appartenirVertex(sommet)){
            System.out.println("Erreur, le sommet appartient déjà au graphe");
            return;
        }
        Vertex[] tab = new Vertex[nbOfVertices() + 1];
        for(int i = 0; i < nbOfVertices(); i++){
            tab[i] = this.vertices[i];
        }
        tab[nbOfEdges()] = sommet;
        this.vertices = tab;

        //Mettre à jour le tableau des incidences

        Edge[][] t = new Edge[this.incidence.length+1][this.incidence[0].length];
        int i, j;
        for(i = 0; i < this.incidence.length; i++){
            for(j = 0; j < this.incidence[0].length; j++){
                t[i][j] = this.incidence[i][j];
            }
        }
        this.incidence = t;
    }


    private boolean appartenirVertex(Vertex sommet){
        boolean appartient = false;
        int i = 0;
        while( i < nbOfVertices() && !appartient){
            if(this.vertices[i].getId() == sommet.getId()){
                appartient = true;
            }
        }
        return appartient;
    }
    
    /**
     * Ajoute une arête à un graphe
     * @param sommet1 Le premier sommet qui va être relié par le sommet
     * @param sommet2 Le deuxième sommet
     */
    public void addEdge(Vertex sommet1, Vertex sommet2){
        if(!appartenirVertex(sommet1) || !appartenirVertex(sommet2)){
            System.out.println("Le sommet n'existe pas dans le graphe");
            return;
        }
        Edge[] tab = new Edge[nbOfEdges() + 1];
        for(int i = 0; i < nbOfEdges(); i++){
            tab[i] = this.edges[i];
        }
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        scanner.close();
        if(this.directed){
            System.out.println("Quelle est la source de l'arête ?");
            Scanner scan2 = new Scanner(System.in);
            int x2 = scan2.nextInt();
            scan2.close();
            tab[nbOfEdges()] = new DirectedEdge(Color.BLACK, x, sommet1, sommet2, x2);
        }
        else{
            tab[nbOfEdges()] = new UndirectedEdge(Color.BLACK, x, sommet1, sommet2);
        }
        this.edges = tab;

        //Il faut mettre a jour la table d'incidence 

        Edge[][] t = new Edge[this.incidence.length][this.incidence[0].length+1];
        int i, j;
        for(i = 0; i < this.incidence.length; i++){
            for(j = 0; j < this.incidence[0].length; j++){
                t[i][j] = this.incidence[i][j];
            }
        }
        this.incidence = t;        
    }
    
    /**
     * Permet de savoir si tous les sommets sont interconnectés, pas forcément d'arête entre tous 
     * @return retoune True si ils sont interconnectés
     */
    public boolean isConnected(){
        boolean connect = true;
        int i = 0;
        while(i<this.incidence.length && connect){
            int j = 0;
            boolean lignevide = true;
            while(j < this.incidence.length && lignevide){
                if(this.incidence[i][j] != null){
                    lignevide = false;
                }
            }
            if(lignevide){
                connect = false;
            }
        }
        return connect;
    }
    
    /**
     * Retourne un tableau contenant les arêtes reliant les deux sommets passées en paramètres
     * @param sommet1 Sommet qui doit exister dans le graphe
     * @param sommet2
     * @return
     */
    Edge[] getEdges(Vertex sommet1, Vertex sommet2){

    }
    
    /**
     * Retourne un tableau contenant toutes les arêtes existantes dans un Graphe
     * @return Tableau d'arêtes
     */
    Edge[] getEdges(){

    }
    
    /**
     * Retourne un tableau des Sommets contenus dans le Graphe
     * @return Tableau de Vertex (sommets)
     */
    Vertex[] getVertices(){

    }
    
    /**
     * Retourne les arêtes qui sont liés au sommet donné en paramètres
     * @param sommet Sommet contenu dans le graphe
     * @return Tableau de Som
     */
    Edge[] getNeighborEdges(Vertex sommet){}
}