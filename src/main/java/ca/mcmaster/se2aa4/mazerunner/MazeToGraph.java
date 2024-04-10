package ca.mcmaster.se2aa4.mazerunner;

import java.util.LinkedList;
import java.util.Map;

public interface MazeToGraph { //supports both adjacency lists and matrix's

//    public void weighted(Maze maze); for future implementations using dijkstras

    public Map<Position, LinkedList<Position>> unweighted(Maze maze);

}
