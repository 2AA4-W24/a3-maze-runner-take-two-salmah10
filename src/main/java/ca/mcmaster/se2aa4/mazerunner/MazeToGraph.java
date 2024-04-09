package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class MazeToGraph {
    private static final Logger logger = LogManager.getLogger();
    private Map<Position, LinkedList<Position>> adjacencyList = new HashMap<>();



    public Map<Position, LinkedList<Position>> unweightedList(Maze maze){
        int rows = maze.getSizeX();
        int cols = maze.getSizeY();

        Direction dir = Direction.RIGHT;


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Position currentPos = new Position(i, j);

                if (!maze.isWall(currentPos)) {
                    Node node = new Node(currentPos, maze);
                    adjacencyList.put(currentPos, node.getConnections());

                    System.out.println(adjacencyList); //turn this into a logger
                }
            }
        }

        return adjacencyList;
    }
}
