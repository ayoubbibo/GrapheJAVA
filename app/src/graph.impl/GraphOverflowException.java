package graph.impl;

public class GraphOverflowException extends Exception { 
    public GraphOverflowException(String errorMessage) {
        super(errorMessage);
    }
}