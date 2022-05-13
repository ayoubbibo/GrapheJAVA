package graph.impl;

import graph.*;

import java.awt.Color;
import java.util.Scanner;

public class IncidenceArrayGraph implements Graph {

    private Edge[][] incidence;
    private Edge[] edges;
    private Vertex[] vertices;
    int maxEdges;
    int maxVertices;
    int numberOfEdges;
    int numberOfVerticies;


    /**
     * Constructeur qui crée un graphe vide
     * @param maxEdges number of edges
     */
    public IncidenceArrayGraph(int maxEdges, int maxVertices) {
        this.vertices = new Vertex[maxVertices];
        this.edges = new Edge[maxEdges];
        this.incidence = new Edge[maxEdges][maxVertices];
        this.maxEdges = maxEdges;
    }


    /**
     * Retourne le nombre de sommets contenus dans un graphe
     * @return Le nombre de sommets
     */
    public int nbOfVertices(){
        return this.numberOfVerticies;
    }

    /**
     * Retourne le nombre d'arêtes d'un graphe
     * @return Le nombre d'arêtes
     */
    public int nbOfEdges(){
        return this.numberOfEdges;
    }
    
    /**
     * Ajoute un sommet à un graphe
     * @param vertex Un sommet Vertex
     */
    public void addVertex(Vertex vertex) throws java.io.IOException {
        if(this.maxVertices == this.nbrVertices){
            throw new GraphOverflowException("Too much vertices");
        }
        // Vérifier que le sommet n'existe pas déjà
        int i = 0;
        for(Vertex vertex : this.vertices){
            if(vertex.getId() == vertex.getId()){
                throw new IllegalArgumentException("Vertex already exists");
            }
        }
        // Ajouter le sommet
        this.vertices.add(vertex);
    }
    
    /**
     * Ajoute une arête à un graphe
     * @param vertex1 La première arête qui va être relié par le sommet
     * @param vertex2 La deuxième arête
     * @param color Couleur du sommet
     * @param int Valeur à stocker
     * @param directed true si l'arête est dirigée
     */
    public void addEdge(Color color, Vertex vertex1, Vertex vertex2, int value, Boolean directed) throws IllegalArgumentException {
        Vertex firstVertex = null;
        Vertex secondVertex = null;
        for(Vertex vertex : this.vertices){
            if(vertex.getId() == vertex1.getId()){
                firstVertex = vertex;
            }
            if(v.getId() == vertex2.getId()){
                secondVertex = vertex;
            }
        }

        if(Object.isNull(firstVertex)  && Object.isNull(secondVertex)){
            throw new IllegalArgumentException("At least one of the vertices does not exist in graph");
        } else {
            if(directed) {
                this.edges.add(new DirectedEdge(color, firstVertex, secondVertex, value));
            } else {
                this.edges.add(new UndirectedEdge(color, firstVertex, secondVertex, value));
            }  
        }
    }
    
    /**
    /**
     * Ajoute une arête non dirigée à un graphe
     * @param vertex1 La première arête qui va être relié par le sommet
     * @param vertex2 La deuxième arête
     */
    public void addEdge(Vertex vertex1, Vertex vertex2) throws IllegalArgumentException {
        addVertex(new Color(255, 255, 255), vertex1, vertex2, 0, false);
    }

    /**
     * @return true whether there is a path between the two vertices (without accounting for the edge directions)
     * @param vertex1 vertex
     * @param vertex2 vertex
     */
    public boolean isConnected(Vertex vertex1, Vertex vertex2) {
        for(Edge edge : this.edges) {
            Vertex ends[] = edge.getEnds();
            if(ends[0] == vertex1 && ends[1] == vertex2) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Permet de savoir si tous les sommets sont interconnectés
     * @return retoune True si ils sont interconnectés
     */
    public boolean isConnected(){
        for(Vertex vertex1 : this.vertices) {
            Boolean connected = false;
            int i = 0;
            while(!connected && i<this.vertices.length) {
                Vertex vertex2 = this.vertices.get[i];
                if(vertex1!=vertex2) {
                    if(this.isConnected(vertex1, vertex2)){
                        connected = true;
                    }
                }
                i++;
            }
            if(!connected){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Retourne un tableau contenant les arêtes reliant les deux sommets passées en paramètres
     * @param vertex1 Sommet qui doit exister dans le graphe
     * @param vertex2
     * @return
     */
    public Edge[] getEdges(Vertex vertex1, Vertex vertex2){
		Edge edges = new Edge[this.edges.length];
        int count = 0;
        for(Edge edge : this.edges){
            Vertex[] vertices = edge.getEnds();
            if((vertices[0]==vertex1 && vertices[1]==vertex2) || (vertices[0]==vertex2 && vertices[1]==vertex1)){
                edges.add(edge);
                count++;
            }
        }

        // Créer un tableau de la bonne taille

        Edge result = new Edge[count];
        int i = 0;
        while(i<count){
            result[i] = this.edges[i];
            i++;
        }

        return result;
    }
    
    /**
     * Retourne un tableau contenant toutes les arêtes existantes dans un Graphe
     * @return Tableau d'arêtes
     */
    public Edge[] getEdges(){
        return this.edges;
    }
    
    /**
     * Retourne un tableau des Sommets contenus dans le Graphe
     * @return Tableau de Vertex (sommets)
     */
    public Vertex[] getVertices(){
        return this.vertices;
    }
    
    /**
     * Retourne les arêtes qui sont liés au sommet donné en paramètres
     * @param vertex Sommet contenu dans le graphe
     * @return Tableau des arête dont un des deux sommets est le vertex en entrée
     */
    public Edge[] getNeighborEdges(Vertex vertex){
		Edge edges = new Edge[this.edges.length];
        int count = 0;
		for(Edge edge : this.edges) {
            Vertex[] vertices = edge.getEnds();
            if(vertices[0]==vertex || vertices[1]==vertex) {
                edges.add(edge);
                count++;
            }
        }

        // Créer un tableau de la bonne taille

        Edge result = new Edge[count];
        int i = 0;
        while(i<count){
            result[i] = this.edges[i];
            i++;
        }

        return result;
    }

    /**
     * main function for test
     */
    public static void main(String[] args){
        System.out.println("IncidenceArrayGraph lancé");
    }
}