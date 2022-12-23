package avengers;

import java.util.Stack;

/**
 * Given an adjacency matrix, use a random() function to remove half of the nodes. 
 * Then, write into the output file a boolean (true or false) indicating if 
 * the graph is still connected.
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * PredictThanosSnapInputFile name is passed through the command line as args[0]
 * Read from PredictThanosSnapInputFile with the format:
 *    1. seed (long): a seed for the random number generator
 *    2. p (int): number of people (vertices in the graph)
 *    2. p lines, each with p edges: 1 means there is a direct edge between two vertices, 0 no edge
 * 
 * Note: the last p lines of the PredictThanosSnapInputFile is an ajacency matrix for
 * an undirected graph. 
 * 
 * The matrix below has two edges 0-1, 0-2 (each edge appear twice in the matrix, 0-1, 1-0, 0-2, 2-0).
 * 
 * 0 1 1 0
 * 1 0 0 0
 * 1 0 0 0
 * 0 0 0 0
 * 
 * Step 2:
 * Delete random vertices from the graph. You can use the following pseudocode.
 * StdRandom.setSeed(seed);
 * for (all vertices, go from vertex 0 to the final vertex) { 
 *     if (StdRandom.uniform() <= 0.5) { 
 *          delete vertex;
 *     }
 * }
 * Answer the following question: is the graph (after deleting random vertices) connected?
 * Output true (connected graph), false (unconnected graph) to the output file.
 * 
 * Note 1: a connected graph is a graph where there is a path between EVERY vertex on the graph.
 * 
 * Note 2: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, isConnected is true if the graph is connected,
 *   false otherwise):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(isConnected);
 * 
 * @author Yashas Ravi
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/PredictThanosSnap predictthanossnap.in predictthanossnap.out
*/

public class PredictThanosSnap {
	 
    public static void main (String[] args) {
 
        if ( args.length < 2 ) {
            StdOut.println("Execute: java PredictThanosSnap <INput file> <OUTput file>");
            return;
        }

        String thanosSnapIn = args[0];
        String thanosSnapOut = args[1];

    	// WRITE YOUR CODE HERE
        StdIn.setFile(thanosSnapIn);
        StdOut.setFile(thanosSnapOut);

        Long seed = StdIn.readLong();
        int numPeople = StdIn.readInt();

        int[][] adjMatrix = new int[numPeople][numPeople];
        for(int i = 0; i < numPeople; i++)
        {
            for(int j = 0; j < numPeople; j++)
            {
                adjMatrix[i][j] = StdIn.readInt();
            }
        }

        int[] people = new int[numPeople];

        StdRandom.setSeed(seed);
        for(int i = 0; i < numPeople; i++)
        {
            if(StdRandom.uniform() <= 0.5)
            {
                people[i] = 0;
            }
            else
                people[i] = 1;

            //System.out.println("Snapped: " + people[i]);
        }

        int numDeleted = 0;
        for(int i = 0; i < numPeople; i++)
        {
            for(int j = 0; j < numPeople; j++)
            {
                if(people[i] == 0)
                {
                    adjMatrix[i][j] = 0;
                    numDeleted++;
                }
                //System.out.print(adjMatrix[i][j] + " ");
            }
            //System.out.println();
        }




        Boolean[] r = new Boolean[numPeople];
        for(int i = 0; i < r.length; i++)
        {
            r[i] = false;
        }

        // for(int i = 0; i < numPeople; i++)
        // {
        //     for(int j = 0; j < numPeople; j++)
        //     {
        //         if(people[i] == 1)
        //         {
        //             if(i != j && adjMatrix[i][j] == 1 && (adjMatrix[i][j] == adjMatrix[j][i]))
        //             {
        //                 r = true;
        //             }
        //             else
        //             {
        //                 r = false;
        //             }
        //         }
               
        //     }
        // }

        //Stack<Integer> stack = new Stack<>();

        // while(!stack.isEmpty())
        // {
        //     int s = stack.peek();
        //     stack.pop();
        //     if(r[s] == false)
        //     {
        //         r[s] = true;
        //     }
        // }

        // for(int i = 0; i < numPeople; i++)
        // {
        //     for(int j = 0; j < numPeople; j++)
        //     {
        //         System.out.print(adjMatrix[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        Boolean start = false;
        int q = 0;
        while(start == false)
        {
            if(people[q] == 0)
            {
                q++;
            }
            else
            {
                start = true;
                dfs(adjMatrix, q, r, people);
            }
        }

        Boolean connect = true;
        for(int i = 0; i < r.length; i++)
        {
            System.out.println(r[i]);
            if(r[i] == false && people[i] == 1)
            {
                connect = false;
            }
        }
        //System.out.println(connect);
        StdOut.println(connect);
    }

    private static void dfs(int[][] adjMatrix, int index, Boolean[] visited, int[] deleted)
    {

        if(deleted[index] == 1)
        {
            visited[index] = true;
            //System.out.println("Index: " + index);
            //System.out.println(adjMatrix[0].length);
            for(int i = 0; i < adjMatrix[0].length; i++)
            {
                //int next = adjMatrix[index][i];
                //System.out.println("adjMatrix " + adjMatrix[index][i] + " visited?: " + visited[i]);
                if((adjMatrix[index][i] == 1) && !visited[i])
                {
                    //System.out.println("recursion");
                    dfs(adjMatrix, i, visited, deleted);
                }
            }
        }
        else
        {
            return;
        }
    }
}
