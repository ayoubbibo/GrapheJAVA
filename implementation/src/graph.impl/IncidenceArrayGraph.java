package graph.impl;

import graph.*;

import java.awt.Color;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import java.lang.reflect.Array;

public class IncidenceArrayGraph implements Graph {

    private ArrayList<ArrayList<Edge>> edges;
    private ArrayList<Vertex> vertices;
    private int maxVertex;

    /**
     * Constructeur qui crée un graphe vide
     * @param maxVertex number of edges
     */
    public IncidenceArrayGraph(int maxVertex) {
        this.edges = new ArrayList<ArrayList<Edge>>();
        for(int i = 0 ; i < maxVertex ; i++) {
            this.edges.add(new ArrayList<Edge>());
        }

        this.vertices = new ArrayList<Vertex>();
    }

    /**
     * Retourne le nombre de sommets contenus dans un graphe
     * @return Le nombre de sommets
     */
    public int nbOfVertices(){
        int count = 0;
        for(Vertex v : this.vertices){
            if(v != null){
                count++;
            }
        }
        return count;
    }

    /**
     * Retourne le nombre d'arêtes d'un graphe
     * @return Le nombre d'arêtes
     */
    public int nbOfEdges(){
        int count = 0;
        for(ArrayList<Edge> edgeList : edges){
            count += edgeList.size();
        }
        return count/2;
    }
    
    /**
     * Ajoute un sommet à un graphe
     * @param vertex Un sommet Vertex
     */
    public void addVertex(Vertex vertex) throws GraphOverflowException, IllegalArgumentException {
        int id = vertex.getId();
        if(id>=this.vertices.size()) {
            throw new GraphOverflowException("Too much vertices");
        } else {
            if(this.vertices.get(id) == null) {
                throw new IllegalArgumentException("Vertex already exists");
            } else {
                if(this.vertices.size() == this.maxVertex){
                    // agrandissement de la liste
                    for(int i = 0 ; i < maxVertex ; i++) {
                        this.edges.add(new ArrayList<Edge>());
                    }
                    this.maxVertex = this.vertices.size();
                }
                Array.set(this.vertices, id, vertex);
            }
        }
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
        int id1 = vertex1.getId();
        int id2 = vertex2.getId();

        if(this.edges.get(id1)==null && this.edges.get(id2)==null){
            throw new IllegalArgumentException("At least one of the vertices does not exist in graph");
        } else {
            Edge edge;
            if(directed) {
                edge = new DirectedEdge(color, value, this.vertices.get(id1), this.vertices.get(id2), 0);
            } else {
                edge = new UndirectedEdge(color, value, this.vertices.get(id1), this.vertices.get(id2));
            }  
            this.edges.get(id1).add(edge);
            this.edges.get(id2).add(edge);
        }
    }
    
    /**
    /**
     * Ajoute une arête non dirigée à un graphe
     * @param vertex1 La première arête qui va être relié par le sommet
     * @param vertex2 La deuxième arête
     */
    public void addEdge(Vertex vertex1, Vertex vertex2) throws IllegalArgumentException {
        addEdge(new Color(255, 255, 255), vertex1, vertex2, 0, false);
    }

    /**
     * @return true whether there is a path between the two vertices (without accounting for the edge directions)
     * @param vertex1 vertex
     * @param vertex2 vertex
     */
    public boolean isConnected(Vertex vertex1, Vertex vertex2) {
        int i = 0;
        boolean res = false;
        ArrayList<Vertex> vertexSeen = new ArrayList<>(0);
        vertexSeen.add(this.vertices.get(i));
        Vertex aVerif;
        while (i<vertexSeen.size()){
            Vertex actuel = vertexSeen.get(i);
            int index1 = actuel.getId();
            int j = 0;
            while(this.edges.get(index1).get(j) != null){
                if (this.edges.get(index1).get(j).getEnds()[0].getId()==index1){
                    aVerif = this.edges.get(index1).get(j).getEnds()[1];
                    int index2 = aVerif.getId();
                    int actAVerif;
                    boolean alreadySeen = false;
                    for(int k = 0; !alreadySeen && k<vertexSeen.size(); k++){
                        actAVerif = vertexSeen.get(k).getId();
                        if (actAVerif==index2){
                            alreadySeen = true;
                        }
                    }
                    if (!alreadySeen){
                        vertexSeen.add(aVerif);
                    }
                }
                if (this.edges.get(index1).get(j).getEnds()[1].getId()==index1){
                    aVerif = this.edges.get(index1).get(j).getEnds()[1];
                    int index2 = aVerif.getId();
                    int actAVerif;
                    boolean alreadySeen = false;
                    for(int k = 0; !alreadySeen && k<vertexSeen.size();k++){
                        actAVerif = vertexSeen.get(k).getId();
                        if (actAVerif==index2){
                            alreadySeen = true;
                        }
                    }
                    if (!alreadySeen){
                        vertexSeen.add(aVerif);
                    }
                }
                j++;
            }
            i++;
        }
        return vertexSeen.size()==this.vertices.size();
    }
    
    /**
     * Permet de savoir si tous les sommets sont interconnectés
     * @return retoune True si ils sont interconnectés
     */
    public boolean isConnected(){
        for(ArrayList<Edge> edges : this.edges){
            for(Edge edge : edges) {
                Vertex[] vertices = edge.getEnds();
                if(!isConnected(vertices[0], vertices[1])){
                    return false;
                }
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
        ArrayList<Edge> edgeArray = new ArrayList<Edge>();
        for(Edge edge : this.edges.get(vertex1.getId())){
            Vertex[] vertices = edge.getEnds();
            if((vertices[0]==vertex1 && vertices[1]==vertex2) || (vertices[0]==vertex2 && vertices[1]==vertex1)){
                edgeArray.add(edge);
            }
        }

        // Créer un tableau de la bonne taille

        Edge result[] = new Edge[edgeArray.size()];
        int i = 0;
        while(i<edgeArray.size()){
            result[i] = edgeArray.get(i);
            i++;
        }

        return result;
    }
    
    /**
     * Retourne un tableau contenant toutes les arêtes existantes dans un Graphe
     * @return Tableau d'arêtes
     */
    public Edge[] getEdges(){
        ArrayList<Edge> edgeArray = new ArrayList<Edge>();
        for(ArrayList<Edge> edgeList : this.edges) {
            for(Edge edge : edgeList) {
                if(!edgeArray.contains(edge)){
                    edgeArray.add(edge);
                }
            }
        }

        // Créer un tableau de la bonne taille

        Edge result[] = new Edge[edgeArray.size()];
        int i = 0;
        while(i<edgeArray.size()){
            result[i] = edgeArray.get(i);
            i++;
        }

        return result;
    }
    
    /**
     * Retourne un tableau des Sommets contenus dans le Graphe
     * @return Tableau de Vertex (sommets)
     */
    public Vertex[] getVertices(){
        Vertex result[] = new Vertex[this.vertices.size()];
        int i = 0;
        while(i<this.vertices.size()){
            result[i] = this.vertices.get(i);
            i++;
        }
        return result;
    }
    
    /**
     * Retourne les arêtes qui sont liés au sommet donné en paramètres
     * @param vertex Sommet contenu dans le graphe
     * @return Tableau des arête dont un des deux sommets est le vertex en entrée
     */
    public Edge[] getNeighborEdges(Vertex vertex){
        ArrayList<Edge> edgeArray = new ArrayList<Edge>();
		for(Edge edge : this.edges.get(vertex.getId())) {
            Vertex[] vertices = edge.getEnds();
            if(vertices[0]==vertex || vertices[1]==vertex) {
                edgeArray.add(edge);
            }
        }

        // Créer un tableau de la bonne taille

        Edge result[] = new Edge[edgeArray.size()];
        int i = 0;
        while(i<edgeArray.size()){
            result[i] = edgeArray.get(i);
            i++;
        }

        return result;
    }

    /**
     * main function for test
     */
    public static void main(String[] args){
        System.out.println("IncidenceArrayGraph lancé");
        
        // Ne marche pas car Color n'est pas définit
        // Ne marche pas car Color n'est pas définit
        // Ne marche pas car Color n'est pas définit

        // couleur = new Color("White");
        
        // IncidenceArrayGraph graph = new IncidenceArrayGraph(10);
        // Vertex sommet1 = new Vertex(couleur, "Premier sommet");
        // Vertex sommet2 = new Vertex(couleur, "Deuxième sommet");
        // Vertex sommet3 = new Vertex(couleur, "Troisième sommet");
        // try {
        //     graph.addVertex(sommet1);
        //     graph.addVertex(sommet2);
        //     graph.addVertex(sommet3);
        // } catch(GraphOverflowException e) {
        //     System.out.println("Error GraphOverflowException");
        //     exit();
        // } catch(IllegalArgumentException e) {
        //     System.out.println("Error IllegalArgumentException");
        //     exit();
        // }

        // graph.addEdge(sommet1, sommet2);
        // graph.addEdge(sommet2, sommet3);

        // System.out.println("Le nombre de Sommets est : " + graph.nbOfVertices());
        // System.out.println("le nombre d'arrete est : " + graph.nbOfEdges());
    }
}