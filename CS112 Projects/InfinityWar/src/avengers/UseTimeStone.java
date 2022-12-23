package avengers;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Given a starting event and an Adjacency Matrix representing a graph of all possible 
 * events once Thanos arrives on Titan, determine the total possible number of timelines 
 * that could occur AND the number of timelines with a total Expected Utility (EU) at 
 * least the threshold value.
 * 
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * UseTimeStoneInputFile name is passed through the command line as args[0]
 * Read from UseTimeStoneInputFile with the format:
 *    1. t (int): expected utility (EU) threshold
 *    2. v (int): number of events (vertices in the graph)
 *    3. v lines, each with 2 values: (int) event number and (int) EU value
 *    4. v lines, each with v (int) edges: 1 means there is a direct edge between two vertices, 0 no edge
 * 
 * Note 1: the last v lines of the UseTimeStoneInputFile is an ajacency matrix for a directed
 * graph. 
 * The rows represent the "from" vertex and the columns represent the "to" vertex.
 * 
 * The matrix below has only two edges: (1) from vertex 1 to vertex 3 and, (2) from vertex 2 to vertex 0
 * 0 0 0 0
 * 0 0 0 1
 * 1 0 0 0
 * 0 0 0 0
 * 
 * Step 2:
 * UseTimeStoneOutputFile name is passed through the command line as args[1]
 * Assume the starting event is vertex 0 (zero)
 * Compute all the possible timelines, output this number to the output file.
 * Compute all the posssible timelines with Expected Utility higher than the EU threshold,
 * output this number to the output file.
 * 
 * Note 2: output these number the in above order, one per line.
 * 
 * Note 3: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut:
 *     StdOut.setFile(outputfilename);
 *     //Call StdOut.print() for total number of timelines
 *     //Call StdOut.print() for number of timelines with EU >= threshold EU 
 * 
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/UseTimeStone usetimestone.in usetimestone.out
 * 
 * @author Yashas Ravi
 * 
 */

public class UseTimeStone {

    public static void main (String [] args) {
    	
        if ( args.length < 2 ) {
            StdOut.println("Execute: java UseTimeStone <INput file> <OUTput file>");
            return;
        }

        String useTimeStoneIn = args[0];
        StdIn.setFile(useTimeStoneIn);
        String useTimeStoneOut = args[1];
        StdOut.setFile(useTimeStoneOut);

    	// WRITE YOUR CODE HERE

        int threshold = StdIn.readInt();
        int numEvents = StdIn.readInt();
        int[] euValues = new int[numEvents];

        for(int i = 0; i < numEvents; i++)
        {
            int eventNum = StdIn.readInt();
            int EUValue = StdIn.readInt();
            euValues[eventNum] = EUValue;
        }

        int[][] adjMatrix = new int[numEvents][numEvents];
        for(int i = 0; i < numEvents; i++)
        {
            for(int j = 0; j < numEvents; j++)
            {
                adjMatrix[i][j] = StdIn.readInt();
            }
        }

        boolean[] visited = new boolean[numEvents];
        boolean[] visited2 = new boolean[numEvents];

        ArrayList<Integer> numberPaths = new ArrayList<>();
        for(int i = 0; i < numEvents; i++)
        {
            int paths = 0;
            paths = countPaths(0, i, paths, adjMatrix, visited2);
            numberPaths.add(paths);
        }

        int largestNum = 0;
        for(int i = 0; i < numberPaths.size(); i++)
        {
            largestNum += numberPaths.get(i);
            //System.out.println(numberPaths.get(i));
        }
        //int paths = 0;
        //paths = dfs(adjMatrix, 0, visited, paths);
        //paths = countPaths(0, 2, paths);
        //System.out.println(largestNum);
        StdOut.println(largestNum);

        Stack<Integer> path = new Stack<>();
        TimeStone paths = new TimeStone(largestNum);
        for(int i = 0; i < largestNum; i++)
        {
            paths.addEdgeList(i);
        }
        for(int i = 0; i < numEvents; i++)
        {
            dfs(adjMatrix, 0, i, visited, path, paths);
        }
        //System.out.println("Done DFS");
        //System.out.println(printDFS(paths, largestNum));

        int[] euPaths = new int[paths.size()];

        for(int i = 0; i < largestNum; i++)
        {
            int cost = 0;
            for(int j = 0; j < paths.get(i).size(); j++)
            {
                cost += euValues[paths.get(i).get(j)];
            }
            euPaths[i] = cost;
        }

        int numGreater = 0;

        for(int i = 0; i < euPaths.length; i++)
        {
            if(euPaths[i] >= threshold)
            {
                numGreater++;
            }
        }

        //StdOut.println(euPaths.length);
        StdOut.println(numGreater);
    }

    private static void dfs(int[][] adjMatrix, int index, int dest, boolean[] visited, Stack<Integer> path, TimeStone map)
    {
        // path.push(index);
        // visited[index] = true;
        // if(index != dest)
        // {
        //     for(int i = 0; i < adjMatrix[0].length; i++)
        //     {
        //         if(!visited[i] && (adjMatrix[index][i] == 1))
        //         {
        //             dfs(adjMatrix, i, dest, visited, path, map);
        //         }
        //     }
        // }
        // System.out.println(index);

        // System.out.println("Added path");
        // addPath(map, path, dest);
        // //path.pop();
        // visited[index] = false;
        //System.out.println("Destination: " + dest + " index: " + index);
        path.push(index);
        visited[index] = true;
        if(index != dest)
        {
            //System.out.println("in if statement");
            for(int i = 0; i < adjMatrix[0].length; i++)
            {
                if((adjMatrix[index][i] == 1) && !visited[i])
                {
                    //dfs(adjMatrix, i, visited, deleted);
                    dfs(adjMatrix, i, dest, visited, path, map);
                }
            }
        }
        else
        {
            for(int i = 0; i < map.size(); i++)
            {
                //add the path to the hashmap in a new key
                if(map.get(i).isEmpty())
                {
                    //System.out.println("path add");
                    addPath(map, path, i);
                    break;
                }
            }
        }
        path.pop();
        visited[index] = false;
    }

    private static void addPath(TimeStone map, Stack<Integer> path, int pathNum)
    {
        //System.out.println(pathNum);
        Stack<Integer> flip = new Stack<>();
        Stack<Integer> filler = new Stack<>();
        for(int i = 0; i < path.size(); i++)
        {
            filler.push(path.get(i));
        }

        while(!filler.isEmpty())
        {
            flip.push(filler.pop());
        }

        while(!flip.isEmpty())
        {
            map.addEdge(pathNum, flip.pop());
        }
    }

    // private static int countPaths(int a, int b, int numPaths, int[][] adjMatrix)
    // {
        
    //     if(a == b)
    //     {
    //         numPaths++;
    //     }
    //     else
    //     {
    //         for(int i = 0; i <= b; i++)
    //         {
    //             if(adjMatrix[a][i] == 1)
    //             {
    //                 numPaths = countPaths(i, b, numPaths, adjMatrix);
    //             }
    //         }
    //     }

    //     return numPaths;
    // }

    private static int countPaths(int a, int b, int numPaths, int[][] adjMatrix, boolean[] visited)
    {
        visited[a] = true;
        if(a == b)
        {
            numPaths++;
        }
        else
        {
            for(int i = 0; i < adjMatrix[0].length; i++)
            {
                if((adjMatrix[a][i] == 1) && !visited[i])
                {
                    numPaths = countPaths(i, b, numPaths, adjMatrix, visited);
                }
            }
        }
        visited[a] = false;
        return numPaths;
    }

    private static String printDFS(TimeStone map, int numPaths)
    {
        String r = "";
        for(int i = 0; i < numPaths; i++)
        {
            //System.out.println(i);
            for(int j = 0; j < map.get(i).size(); j++)
            {
                r = r + map.get(i).get(j) + "-";
            }
            r = r + "\n";
        }
        return r;
    }
}
