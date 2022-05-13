package graph;

public interface Graph{

    /**
     * Retourne le nombre de sommets contenus dans un graphe
     * @return Le nombre de sommets
     */
    int nbOfVertices();

    /**
     * Retourne le nombre d'arêtes d'un graphe
     * @return Le nombre d'arêtes
     */
    int nbOfEdges();
    
    /**
     * Ajoute un sommet à un graphe
     * @param sommet Un sommet Vertex
     */
    void addVertex(Vertex sommet) throws GraphOverflowException, IllegalArgumentException;
    
    /**
     * Ajoute une arête à un graphe
     * @param sommet1 Le premier sommet qui va être relié par le sommet
     * @param sommet2 Le deuxième sommet
     */
    void addEdge(Vertex sommet1, Vertex sommet2) throws IllegalArgumentException;
    
    /**
     * Permet de savoir si tous les sommets sont interconnectés, pas forcément d'arête entre tous 
     * @return retoune True si ils sont interconnectés
     */
    boolean isConnected();
    
    /**
     * Retourne un tableau contenant les arêtes reliant les deux sommets passées en paramètres
     * @param sommet1 Sommet qui doit exister dans le graphe
     * @param sommet2
     * @return
     */
    Edge[] getEdges(Vertex sommet1, Vertex sommet2);
    
    /**
     * Retourne un tableau contenant toutes les arêtes existantes dans un Graphe
     * @return Tableau d'arêtes
     */
    Edge[] getEdges();
    
    /**
     * Retourne un tableau des Sommets contenus dans le Graphe
     * @return Tableau de Vertex (sommets)
     */
    Vertex[] getVertices();
    
    /**
     * Retourne les arêtes qui sont liés au sommet donné en paramètres
     * @param sommet Sommet contenu dans le graphe
     * @return Tableau de Som
     */
    Edge[] getNeighborEdges(Vertex sommet);
}