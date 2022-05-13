package graph.impl;

import graph.*;

/**
 * Class for testing package graph
 */
public class IncidenceArrayGraph implements Graph {
    private Edge[] edges;
    private Vertex[] vertices;
    private Edge[][] incidence;
    private final boolean directed;
    private int nbOfEdges;
    private int nbOfVertices;

    /**
     * Constructor of graph.impl.IncidenceArrayGraph
     * @param nbOfVertices Number max of vertices in graph
     * @param directed Boolean which say if it is a undirected graph or not
     */
    public IncidenceArrayGraph(int nbOfVertices, boolean directed){
        /*System.out.println("Quel est le nombre maximum de sommets que peut avoir votre graphe ?");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();*/
        this.nbOfVertices = 0;
        this.nbOfEdges = 0;
        this.vertices = new Vertex[nbOfVertices];
        this.edges = new Edge[2*nbOfVertices];
        this.directed = directed;
        this.incidence = new Edge[nbOfVertices][2*nbOfVertices];
    }

    /**
     * @return number of vertices
     */
    public int nbOfVertices() {
        return this.nbOfVertices;
    }

    /**
     * @return number of edges
     */
    public int nbOfEdges() {
        return this.nbOfEdges;
    }


    /**
     * Verify if a Vertex is include or no in the Graph
     * @param sommet The vertex for which we need to use this function for
     * @return Return true if the vertex exist in the Graph, else false
     */
    private boolean appartenirVertex(Vertex sommet){
        boolean appartient = false;
        int i = 0;
        while( i < this.nbOfVertices && !appartient){
            if(this.vertices[i].getId() == sommet.getId()){
                appartient = true;
            }
        }
        return appartient;
    }


    /**
     * Add vertex
     * @param vertex Vertex that we will add to our graph
     */
    public void addVertex(Vertex vertex){
        if(this.nbOfVertices >= this.vertices.length){
            System.out.println("Erreur, la capacité est dépassée");
            return;
        }
        if(appartenirVertex(vertex)){
            System.out.println("Le sommet appartient déjà au graphe");
            return;
        }
        this.vertices[this.nbOfVertices] = vertex;
        this.nbOfVertices++;
    }


    /**
     * Add an edge directed of undirected
     * @param color color of edge
     * @param vertex1 first vertex of edge
     * @param vertex2 last vertex of edge
     * @param value value of edge
     * @param directed Value
     */
    public void addEdge(Color color, Vertex vertex1, Vertex vertex2, int value, Boolean directed){
        //We need to check if the vertices exists in the graph
        if(!appartenirVertex(vertex1) || !appartenirVertex(vertex2)){
            System.out.println("Un ou les deux sommets n'appartiennent pas au Graphe");
            return;
        }

        if(directed) {
            this.edges[this.nbOfEdges] = new DirectedEdge(color, vertex1, vertex2, value);
        } else {
            this.edges[this.nbOfEdges] = new UndirectedEdge(color, vertex1, vertex2, value);
        }
        //Il faut essayer d'ajouter ça avant d'incrémenter le nombre de edges, et si on arrive pas à le créer, il faut pas qu'il soit ajouté dans le tableau de edge
        addEdgeToIncidence(vertex1.getId(), this.edges[this.nbOfEdges]);
        addEdgeToIncidence(vertex2.getId(), this.edges[this.nbOfEdges]);
        this.nbOfEdges++;

    }

    private void addEdgeToIncidence(int vertexId, Edge edge1){
        boolean ajoute = false;
        int i = 0;
        while(i < this.edges.length && !ajoute){
            if(this.incidence[vertexId][i] == null ){
                this.incidence[vertexId][i] = edge1;
                ajoute = true;
            }
        }
        //Exception a avoir ici au cas ou il a atteint la capacité max de liaison
        if(!ajoute){
            System.out.println("Le sommet a atteint la capacité maximale de liaisons possibles");
        }
    }

    /**
     * @return true whether there is a path between the two vertices (without accounting for the edge directions)
     * @param vertex1 vertex
     * @param vertex2 vertex
     */
    public boolean isConnected(Vertex vertex1, Vertex vertex2) {
        if(!appartenirVertex(vertex1) || !appartenirVertex(vertex2)){
            //Erreur
            System.out.println("Un des deux sommet ou les deux n'existent pas dans le graphe");
        }
        boolean connected = false;
        int i = 0;
        int idVertex2 = vertex2.getId();
        int idVertex1 = vertex1.getId();
        while(i < this.edges.length && !connected){
            if(this.incidence[idVertex1][i].getEnds()[0].getId() == idVertex2 || this.incidence[idVertex1][i].getEnds()[0].getId() == idVertex2){
                connected = true;
            }
        }
        return connected;
    }

    /**
     * @return true if all vertices are interconnected
     */
    public boolean isConnected() {
        /*for(Vertex vertex1 : this.vertices) {
            Boolean connected = false;
            int i = 0;
            while(!connected && i<this.vertices.size()) {
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
        return true;*/
        boolean connect = true;
        int i = 0;
        while(i<this.incidence.length && connect){
            int j = 0;
            boolean lignevide = true;
            while(j < this.incidence[0].length && lignevide){
                if(this.incidence[i][j] != null){
                    lignevide = false;
                }
            }
            if(!lignevide){
                connect = false;
            }
            i++;
        }
        return connect;
    }

    /**
     * @return list of edges connecting two vertices
     * @param vertex1 first vertex to check
     * @param vertex2 second vertex to check
     */
    public Edge[] getEdges(Vertex vertex1, Vertex vertex2) {
        /*Edge edges = ____;
        for(Edge edge : this.edges){
            Vertex[] vertices = edge.getEnds();
            if((vertices[0]==vertex1 && vertices[1]==vertex2) || (vertices[0]==vertex2 && vertices[1]==vertex1)){
                edges.add(edge);
            }
        }
        return edges;
         */
        if(!appartenirVertex(vertex1) || !appartenirVertex(vertex2)){
            //ERREUR
        }
        Edge[] edges1 = new Edge[this.nbOfEdges];
        int j = 0;
        int i = 0;
        int idVertex1 = vertex1.getId();
        int idVertex2 = vertex2.getId();
        while(this.incidence[idVertex1][i] != null){
            if(this.incidence[idVertex1][i].getEnds()[0].getId() == idVertex2){
                edges1[j] = this.incidence[idVertex1][i];
                j++;
            }
            i++;
        }
        return edges1;
    }

    /**
     * @return all edges of the graph
     */
    public Edge[] getEdges() {
        return this.edges;
    }

    /**
     * @return all vertices of the graph
     */
    public Vertex[] getVertices() {
        return this.vertices;
    }

    /**
     * @return edges connected to this vertex
     * @param vertex vertex
     */
    public Edge[] getNeighborEdges(Vertex vertex) {
        int id = vertex.getId();
        return this.incidence[id];
    }

    /**
     * main function for test
     */
    public static void main(String[] args){
        IncidenceArrayGraph graph = new IncidenceArrayGraph(10, false);
        Color couleur = new Color(12, 34, 45);
        Vertex sommet1 = new Vertex(34, couleur);
        Vertex sommet2 = new Vertex(23, couleur);
        Vertex sommet3 = new Vertex("Salam", couleur);
        graph.addVertex(sommet1);
        graph.addVertex(sommet2);
        graph.addVertex(sommet3);
        graph.addEdge(couleur, sommet1, sommet2, 23, true);
        graph.addEdge(couleur, sommet3, sommet2, 345, false);
        System.out.println("Le nombre de Sommets est : "+graph.nbOfVertices());
        if(graph.isConnected()){
            System.out.println("Le graphe est connecté");
        }
        else{
            System.out.println("Le graphe n'est pas connecté");
        }

    }

}
