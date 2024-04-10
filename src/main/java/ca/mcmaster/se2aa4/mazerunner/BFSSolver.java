package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


public class BFSSolver implements MazeSolver {
    private static final Logger logger = LogManager.getLogger();
    private MazeToGraph adjacencyList = new AdjacencyList();
    Map<Position, Position> prevNode = new HashMap<>(); //used to compute the path after BFS

    @Override
    public Path solve(Maze maze) {

        traversal(maze);

        Position start = maze.getStart();
        Position end = maze.getEnd();

        List<Position> path = new LinkedList<>();
        Position current = end;

        while (current != null && !current.equals(start)) {
            path.add(current);
            current = prevNode.get(current);
        }

        path.add(start);

        if (current == null) {
            logger.error("No path found from start to end.");
            return null;
        }

        logger.info("The path coordinates are" + path);

        Path sp = new Path();
        sp = ListToPath(path);

        logger.info("The shortest path is:" + sp.getFactorizedForm());

        return sp;
    }


    //the goal of traversal is to generate prevNode which both keeps track of the visited nodes and is used to compute the shortest path after BFS
    public void traversal(Maze maze){
        Map<Position, LinkedList<Position>> adjList = adjacencyList.unweighted(maze); //getting adj list of the maze
        Position start = maze.getStart();
        Position end = maze.getEnd();

        Queue<Position> queue = new LinkedList<>();
        prevNode.put(start, null);
        queue.add(start);

        while(!queue.isEmpty()){

            logger.info("the queue is" + queue);

            Position currentNode = queue.poll();

            if (currentNode.equals(end)) {
                break; //the goal is to stop BFS once the target (end) node is reached
            }

            for (Position neighbour : adjList.get(currentNode)) {
                if (!prevNode.containsKey(neighbour)) {
                    queue.add(neighbour); //add it to the queue
                    prevNode.put(neighbour, currentNode); //then add it to the visited array
                }
            }
        }

        System.out.println(prevNode);

    }

    public Path ListToPath(List<Position> path){

        Direction dir = Direction.LEFT; //computes the path starting from the end point
        Path shortPath = new Path();

        for (int i = 0; i < path.size() - 1; i++) {

            Position currentPos = path.get(i);
            logger.info("current pos is" + currentPos);
            Position nextPos = path.get(i + 1);
            logger.info("next pos is" + nextPos);

            Position temp = currentPos;

            logger.info(temp.move(dir));
            logger.info(temp.move(dir.turnRight()));
            logger.info(temp.move(dir.turnLeft()));
            logger.info(temp.move(dir.turnRight().turnRight()));


            if (nextPos.equals(temp.move(dir))) {
                shortPath.addStep('F');
            } else if (nextPos.equals(temp.move(dir.turnRight()))) {
                shortPath.addStep('R');
                shortPath.addStep('F');
                dir = dir.turnRight();

            } else if (nextPos.equals(temp.move(dir.turnLeft()))) {
                shortPath.addStep('L');
                shortPath.addStep('F');
                dir = dir.turnLeft();

            } else if (nextPos.equals(temp.move(dir.turnRight().turnRight()))) {
                shortPath.addStep('L');
                shortPath.addStep('L');
                dir=dir.turnRight().turnRight();
            }
        }

        return shortPath;
    }

}
