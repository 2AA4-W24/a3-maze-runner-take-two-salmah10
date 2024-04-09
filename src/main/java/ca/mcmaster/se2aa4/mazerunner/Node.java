package ca.mcmaster.se2aa4.mazerunner;

import java.util.LinkedList;

public class Node {
    private final LinkedList<Position> connections = new LinkedList<>();
    private final Position position;

    private final Maze maze;

    public Node (Position p, Maze m){
        this.position = p;
        this.maze = m;
        rConnection();
        lConnection();
        uConnection();
        dConnection();
    }


    public void rConnection(){

    }

    public void lConnection(){

    }

    public void uConnection(){

    }

    public void dConnection(){

    }

    public LinkedList<Position> getConnections(){ return connections; }

}
