package avengers;
/**
 * 
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0), 
 * modify the edge weights using the functionality values of the vertices that each edge 
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0).
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 *    1. g (int): number of generators (vertices in the graph)
 *    2. g lines, each with 2 values, (int) generator number, (double) funcionality value
 *    3. g lines, each with g (int) edge values, referring to the energy cost to travel from 
 *       one generator to another 
 * Create an adjacency matrix for g generators.
 * 
 * Populate the adjacency matrix with edge values (the energy cost to travel from one 
 * generator to another).
 * 
 * Step 2:
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by DIVIDING it 
 * by the functionality of BOTH vertices (generators) that the edge points to. Then, 
 * typecast this number to an integer (this is done to avoid precision errors). The result 
 * is an adjacency matrix representing the TOTAL COSTS to travel from one generator to another.
 * 
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan. 
 * Output this number into your output file!
 * 
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, minCost represents the minimum cost to 
 *   travel from Earth to Titan):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(minCost);
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 * 
 * @author Yashas Ravi
 * 
 */

public class LocateTitan {
	
    public static void main (String [] args) {
    	
        if ( args.length < 2 ) {
            StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>");
            return;
        }

        String locateTitanInputFile = args[0];
        String locateTitanOutputFile = args[1];
        StdIn.setFile(locateTitanInputFile);
        StdOut.setFile(locateTitanOutputFile);

    	// WRITE YOUR CODE HERE
        int numGenerators = StdIn.readInt();
        Double[] genFunc = new Double[numGenerators];
        for(int i = 0; i < numGenerators; i++)
        {
            int index = StdIn.readInt();
            genFunc[index] = StdIn.readDouble();
        }

        int[][] adjMatrix = new int[numGenerators][numGenerators];
        for(int i = 0; i < numGenerators; i++)
        {
            for(int j = 0; j < numGenerators; j++)
            {
                adjMatrix[i][j] = StdIn.readInt();
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("adjMatrix Division:");
        System.out.println();
        for(int i = 0; i < numGenerators; i++)
        {
            for(int j = 0; j < numGenerators; j++)
            {
                adjMatrix[i][j] /= (genFunc[i] * genFunc[j]);
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }

        int[] minCost = new int[numGenerators];
        Boolean[] DijkstraSet = new Boolean[numGenerators];

        for(int i = 0; i < numGenerators; i++)
        {
            if(i == 0)
            {
                minCost[i] = 0;
            }
            else
            {
                minCost[i] = Integer.MAX_VALUE;
            }
            DijkstraSet[i] = false;
        }

        // int min = Integer.MAX_VALUE;
        // int minIndex = -1;

        // for(int i = 0; i < minCost.length; i++)
        // {
        //     if(DijkstraSet[i] == false && minCost[i] <= min)
        //     {
        //         min = minCost[i];
        //         minIndex = i;
        //     }
        // }

        

        for(int i = 0; i < numGenerators-1; i++)
        {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;

            for(int k = 0; k < DijkstraSet.length; k++)
            {
               if(DijkstraSet[k] == false && minCost[k] <= min)
                {
                    min = minCost[k];
                    minIndex = k;
                }
            }

            DijkstraSet[minIndex] = true;
            
            for(int j = 0; j < numGenerators; j++)
            {
                if(!DijkstraSet[j] && adjMatrix[minIndex][j] != 0 && minCost[minIndex] != Integer.MAX_VALUE
                && (minCost[minIndex] + adjMatrix[minIndex][j] < minCost[j]))
                {
                    minCost[j] = minCost[minIndex] + adjMatrix[minIndex][j];
                }
            }
        }

        // int shortestDistance = Integer.MAX_VALUE;

        System.out.println("MinCost Array: ");
        for(int i = 0; i < minCost.length; i++)
        {
            System.out.println(minCost[i]);
            // if(minCost[i] <= shortestDistance)
            // {
            //     shortestDistance = minCost[i];
            // }
        }
        
        StdOut.print(minCost[numGenerators-1]);
    }

    // private int shortestPath(int[] path, Boolean[] set)
    // {
    //     int min = Integer.MAX_VALUE;
    //     int minIndex = -1;

    //     for(int i = 0; i < path.length; i++)
    //     {
    //         if(set[i] == false && path[i] <= min)
    //         {
    //             min = path[i];
    //             minIndex = i;
    //         }
    //     }
    //     return minIndex;
    // }
}
