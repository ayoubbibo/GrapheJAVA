package app.graph;

import app.vertex.*;
import app.edge.*;

public interface Graph{
    /**
     * 
     * 
     */
    int nbOfVertices();
    int nbOfEdges();
    void addVertex(Vertex sommet);
    void addEdge(Vertex sommet1, Vertex sommet2);
    boolean isConnected();
    Edge[] getEdges(Vertex sommet1, Vertex sommet2);
    Edge[] getEdges();
    Vertex[] getVertices();
    Edge[] getNeighborEdges(Vertex sommet);
}