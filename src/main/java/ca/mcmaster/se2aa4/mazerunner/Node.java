package ca.mcmaster.se2aa4.mazerunner;

import java.util.LinkedList;
public class Node {
    private final LinkedList<Position> connections = new LinkedList<>();
    public Position position;

    private final Maze maze;
//    private boolean visited;

    public Node (Position p, Maze m){
        this.position = p;
        this.maze = m;
        rightConnection();
        leftConnection();
        upConnection();
        downConnection();
    }


    public void rightConnection(){

        Position r = position.move(Direction.RIGHT);
        if (inBounds(r) && !maze.isWall(r)){

            connections.add(r);
        }

    }

    public void leftConnection(){

        Position l = position.move(Direction.LEFT);
        if (inBounds(l) && !maze.isWall(l)){
            connections.add(l);
        }

    }

    public void upConnection(){

        Position u = position.move(Direction.UP);
        if (inBounds(u) && !maze.isWall(u)){
            connections.add(u);
        }

    }

    public void downConnection(){

        Position d = position.move(Direction.DOWN);
        if (inBounds(d) && !maze.isWall(d)){
            connections.add(d);
        }

    }

//    public boolean getVisited(){
//        return visited;
//    }
//
//    public void setVisited(){
//        visited = true;
//    }

    public boolean inBounds(Position pos){

        boolean inBounds = true;


        if (!(pos.x() < maze.getSizeX())){
            inBounds = false;
        }
        else if (pos.x() < 0){
            inBounds = false;
        }
        else if (pos.y() < 0){
            inBounds = false;
        }
        else if (pos.y() > maze.getSizeY()){
            inBounds = false;
        }

        return inBounds;

    }
    public LinkedList<Position> getConnections(){ return connections; }




}
