package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class AdjacencyList implements MazeToGraph {
    private final Map<Position, LinkedList<Position>> adjacencyList = new HashMap<>();

    @Override
    public Map<Position, LinkedList<Position>> unweighted(Maze maze) {
        int rows = maze.getSizeX();
        int cols = maze.getSizeY();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Position currentPos = new Position(i, j);
                if (!maze.isWall(currentPos)) {
                    Node node = new Node(currentPos, maze);
                    adjacencyList.put(currentPos, node.getConnections());

                }
            }
        }
//        System.out.println("The final adjacency list is:" + adjacencyList);
        return adjacencyList;
    }



}
