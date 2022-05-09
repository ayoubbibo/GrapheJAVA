package app.graph;

import app.vertex.*;

abstract class Graph{
    private int nbOfVertices;
    private int nbOfEdges;


    public Graph(int nbOfVertices, int nbOfEdges){
        this.nbOfEdges = nbOfEdges;
        this.nbOfVertices = nbOfVertices;
    }

    public void addVertex(Vertex sommet){

    }
}