package graph;

public class GraphOverflowException extends Exception { 
    public GraphOverflowException(String errorMessage) {
        super(errorMessage);
    }
}