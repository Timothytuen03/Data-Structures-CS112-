package conwaygame;
import java.util.ArrayList;

import javax.lang.model.util.ElementScanner14;
/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many iterations/generations.
 *
 * Rules 
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.

 * @author Seth Kelley 
 * @author Maxwell Goldberg
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean DEAD = false;

    private boolean[][] grid;    // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
    * Default Constructor which creates a small 5x5 grid with five alive cells.
    * This variation does not exceed bounds and dies off after four iterations.
    */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
    }

    /**
    * Constructor used that will take in values to create a grid with a given number
    * of alive cells
    * @param file is the input file with the initial game pattern formatted as follows:
    * An integer representing the number of grid rows, say r
    * An integer representing the number of grid columns, say c
    * Number of r lines, each containing c true or false values (true denotes an ALIVE cell)
    */
    public GameOfLife (String file) {

        // WRITE YOUR CODE HERE
        StdIn.setFile(file);
        int x = StdIn.readInt();
        int y = StdIn.readInt();
        grid = new boolean[x][y];

        for(int i = 0; i < x; i++)
        {
            for(int k = 0; k < y; k++)
            {
                grid[i][k] = StdIn.readBoolean();
            }
        }
    }

    /**
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid () {
        return grid;
    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells () {
        return totalAliveCells;
    }

    /**
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState (int row, int col) {

        // WRITE YOUR CODE HERE
        if(grid[row][col] == true){
            return true;
        }
        else{
            return false;
        }


    }

    /**
     * Returns true if there are any alive cells in the grid
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive () {

        // WRITE YOUR CODE HERE
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j] == true){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are 
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     */
    public int numOfAliveNeighbors (int row, int col) {

        // WRITE YOUR CODE HERE
        int alive = 0;
        if(row == 0 && col == 0)
        {
            if(grid[row][col+1] == true) {
                alive++;
            }
            if(grid[row+1][col] == true) {
                alive++;
            }
            if(grid[row+1][col+1] == true) {
                alive++;
            }
            if(grid[row][grid[0].length-1] == true)
            {
                alive++;
            }
            if(grid[grid.length-1][col] == true)
            {
                alive++;
            }
            if(grid[row+1][grid[0].length-1] == true)
            {
                alive++;
            }
            if(grid[grid.length-1][col+1] == true)
            {
                alive++;
            }
            if(grid[grid.length-1][grid[0].length-1] == true)
            {
                alive++;
            }
        } else if(row == grid.length-1 && col == 0) {
            if(grid[row-1][col] == true) {
                alive++;
            }
            if(grid[row-1][col+1] == true) {
                alive++;
            }
            if(grid[row][col+1] == true) {
                alive++;
            }
            if(grid[0][col] == true)
            {
                alive++;
            }
            if(grid[row][grid[0].length-1] == true)
            {
                alive++;
            }
            if(grid[0][col+1] == true)
            {
                alive++;
            }
            if(grid[row-1][grid[0].length-1] == true)
            {
                alive++;
            }
            if(grid[0][grid[0].length-1] == true)
            {
                alive++;
            }
        } else if(row == 0 && col == grid[0].length-1) {
            if(grid[row][col-1] == true) {
                alive++;
            }
            if(grid[row+1][col] == true) {
                alive++;
            }
            if(grid[row+1][col-1] == true) {
                alive++;
            }
            if(grid[row][0] == true)
            {
                alive++;
            }
            if(grid[grid.length-1][col] == true)
            {
                alive++;
            }
            if(grid[grid.length-1][col-1] == true)
            {
                alive++;
            }
            if(grid[row+1][0] == true)
            {
                alive++;
            }
            if(grid[grid.length-1][0] == true)
            {
                alive++; //SS
            }
        } else if(row == grid.length-1 && col == grid[0].length-1) {
            if(grid[row-1][col-1] == true) {
                alive++;
            }
            if(grid[row-1][col] == true) {
                alive++;
            }
            if(grid[row][col-1] == true) {
                alive++;
            }
            if(grid[row][0] == true)
            {
                alive++;
            }
            if(grid[0][col] == true)
            {
                alive++;
            }
            if(grid[0][col-1] == true)
            {
                alive++;
            }
            if(grid[row-1][0] == true)
            {
                alive++;
            }
            if(grid[0][0] == true)
            {
                alive++;
            }
        } else if(row == 0 && col != 0 && col != grid[0].length-1) {
            if(grid[row][col-1] == true) {
                alive++;
            }
            if(grid[row][col+1] == true) {
                alive++;
            }
            if(grid[row+1][col-1] == true) {
                alive++;
            }
            if(grid[row+1][col] == true) {
                alive++;
            }
            if(grid[row+1][col+1] == true) {
                alive++;
            }
            if(grid[grid.length-1][col] == true)
            {
                alive++;
            }
            if(grid[grid.length-1][col-1] == true)
            {
                alive++;
            }
            if(grid[grid.length-1][col+1] == true)
            {
                alive++;
            }
        } else if(row == grid.length-1 && col != 0 && col != grid[0].length-1) {
            if(grid[row-1][col-1] == true) {
                alive++;
            }
            if(grid[row-1][col] == true) {
                alive++;
            }
            if(grid[row-1][col+1] == true) {
                alive++;
            }
            if(grid[row][col-1] == true) {
                alive++;
            }
            if(grid[row][col+1] == true) {
                alive++;
            }
            if(grid[0][col] == true)
            {
                alive++;
            }
            if(grid[0][col-1] == true)
            {
                alive++;
            }
            if(grid[0][col+1] == true)
            {
                alive++;
            }
        } else if(col == 0 && row != 0 && row != grid.length-1) {
            if(grid[row-1][col] == true) {
                alive++;
            }
            if(grid[row-1][col+1] == true) {
                alive++;
            }
            if(grid[row][col+1] == true) {
                alive++;
            }
            if(grid[row+1][col] == true) {
                alive++;
            }
            if(grid[row+1][col+1] == true) {
                alive++;
            }
            if(grid[row][grid[0].length-1] == true)
            {
                alive++;
            }
            if(grid[row-1][grid[0].length-1] == true)
            {
                alive++;
            }
            if(grid[row+1][grid[0].length-1] == true)
            {
                alive++;
            }
        } else if(col == grid[0].length-1 && row != 0 && row != grid.length-1) {
            if(grid[row-1][col-1] == true) {
                alive++;
            }
            if(grid[row-1][col] == true) {
                alive++;
            }
            if(grid[row][col-1] == true) {
                alive++;
            }
            if(grid[row+1][col-1] == true) {
                alive++;
            }
            if(grid[row+1][col] == true) {
                alive++;
            }
            if(grid[row][0] == true)
            {
                alive++;
            }
            if(grid[row-1][0] == true)
            {
                alive++;
            }
            if(grid[row+1][0] == true)
            {
                alive++;
            }
        }else {
            if(grid[row-1][col-1] == true) {
                alive++;
            }
            if(grid[row-1][col] == true) {
                alive++;
            }
            if(grid[row-1][col+1] == true) {
                alive++;
            }
            if(grid[row][col-1] == true) {
                alive++;
            }
            if(grid[row][col+1] == true) {
                alive++;
            }
            if(grid[row+1][col-1] == true) {
                alive++;
            }
            if(grid[row+1][col] == true) {
                alive++;
            }
            if(grid[row+1][col+1] == true) {
                alive++;
            }
        }

        return alive; // update this line, provided so that code compiles
    }

    /**
     * Creates a new grid with the next generation of the current grid using 
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid () {

        // WRITE YOUR CODE HERE

        boolean[][] newGrid = new boolean[grid.length][grid[0].length];

        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(numOfAliveNeighbors(i, j) < 2 && getCellState(i, j) == true)
                {
                    newGrid[i][j] = false;
                } else if(numOfAliveNeighbors(i, j) == 3 && getCellState(i, j) == false)
                {
                    newGrid[i][j] = true;
                } else if(numOfAliveNeighbors(i, j) == 2 && getCellState(i, j) == true)
                {
                    newGrid[i][j] = true;
                } else if(numOfAliveNeighbors(i, j) == 3 && getCellState(i, j) == true)
                {
                    newGrid[i][j] = true;
                } else if(numOfAliveNeighbors(i, j) > 3 && getCellState(i, j) == true)
                {
                    newGrid[i][j] = false;
                }

            }
        }
        return newGrid;// update this line, provided so that code compiles
    }

    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration () {

        // WRITE YOUR CODE HERE

        boolean[][] g = computeNewGrid();

        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                grid[i][j] = g[i][j];
            }
        }
    }

    /**
     * Updates the current grid with the grid computed after multiple (n) generations. 
     * @param n number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration (int n) {

        // WRITE YOUR CODE HERE
        for(int i = 0; i < n; i++)
        {
            nextGeneration();
        }
    }

    /**
     * Determines the number of separate cell communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    public int numOfCommunities() {

        // WRITE YOUR CODE HERE
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(grid.length, grid[0].length);
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j] == true)
                {   
                    if(i == 0 && j == 0)
                    {
                        if(grid[i][j+1] == true) {
                            uf.union(i, j, i, j+1);
                        }
                        if(grid[i+1][j] == true) {
                            uf.union(i, j, i+1, j);
                        }
                        if(grid[i+1][j+1] == true) {
                            uf.union(i, j, i+1, j+1);
                        }
                        if(grid[grid.length-1][j] == true)
                        {
                            uf.union(i, j, grid.length-1, j);
                        }
                        if(grid[i][grid[0].length-1] == true)
                        {
                            uf.union(i, j, i, grid[0].length-1);
                        }
                        if(grid[i+1][grid[0].length-1] == true)
                        {
                            uf.union(i, j, i+1, grid[0].length-1);
                        }
                        if(grid[grid.length-1][j+1] == true)
                        {
                            uf.union(i, j, grid.length-1, j+1);
                        }
                        if(grid[grid.length-1][grid[0].length-1] == true)
                        {
                            uf.union(i, j, grid.length-1, grid[0].length-1);
                        }
                    } else if(i == grid.length-1 && j == 0) 
                    {
                        if(grid[i-1][j] == true) {
                            uf.union(i, j, i-1, j);
                        }
                        if(grid[i-1][j+1] == true) {
                            uf.union(i, j, i-1, j);
                        }
                        if(grid[i][j+1] == true) {
                            uf.union(i, j, i, j+1);
                        }  
                        if(grid[i][grid[0].length-1] == true)
                        {
                            uf.union(i, j, i, grid[0].length-1);
                        }
                        if(grid[0][j] == true)
                        {
                            uf.union(i, j, 0, j);
                        }
                        if(grid[0][j+1] == true)
                        {
                            uf.union(i, j, 0, j+1);
                        }
                        if(grid[i-1][grid[0].length-1] == true)
                        {
                            uf.union(i, j, i-1, grid[0].length-1);
                        }
                        if(grid[0][grid[0].length-1] == true)
                        {
                            uf.union(i, j, 0, grid[0].length-1);
                        }
                    } else if(i == 0 && j == grid[0].length-1) 
                    {
                        if(grid[i][j-1] == true) {
                            uf.union(i, j, i, j-1);
                        }
                        if(grid[i+1][j] == true) {
                            uf.union(i, j, i+1, j);
                        }
                        if(grid[i+1][j-1] == true) {
                            uf.union(i, j, i+1, j-1);
                        }
                        if(grid[i][0] == true)
                        {
                            uf.union(i, j, i, 0);
                        }
                        if(grid[grid.length-1][j])
                        {
                            uf.union(i, j, grid.length-1, j);
                        }
                        if(grid[i+1][0] == true)
                        {
                            uf.union(i, j, i+1, 0);
                        }
                        if(grid[grid.length-1][j-1])
                        {
                            uf.union(i, j, grid.length-1, j-1);
                        }
                        if(grid[grid.length-1][0] == true)
                        {
                            uf.union(i, j, grid.length-1, 0);
                        }
                    } else if(i == grid.length-1 && j == grid[0].length-1) 
                    {
                        if(grid[i-1][j-1] == true) {
                            uf.union(i, j, i-1, j-1);
                        }
                        if(grid[i-1][j] == true) {
                            uf.union(i, j, i-1, j);
                        }
                        if(grid[i][j-1]) {
                            uf.union(i, j, i, j-1);
                        }
                        if(grid[0][j] == true)
                        {
                            uf.union(i, j, 0, j);
                        }
                        if(grid[i][0] == true)
                        {
                            uf.union(i, j, i, 0);
                        }
                        if(grid[0][j-1] == true)
                        {
                            uf.union(i, j, 0, j-1);
                        }
                        if(grid[i-1][0] == true)
                        {
                            uf.union(i, j, i-1, 0);
                        }
                        if(grid[0][0] == true)
                        {
                            uf.union(i, j, 0, 0);
                        }
                    } else if(i == 0 && j != 0 && j != grid[0].length-1) 
                    {
                        if(grid[i][j-1] == true) {
                            uf.union(i, j, i, j-1);
                        }
                        if(grid[i][j+1] == true) {
                            uf.union(i, j, i, j+1);
                        }
                        if(grid[i+1][j-1] == true) {
                            uf.union(i, j, i+1, j-1);
                        }
                        if(grid[i+1][j] == true) {
                            uf.union(i, j, i+1, j);
                        }
                        if(grid[i+1][j+1] == true) {
                            uf.union(i, j, i+1, j+1);
                        }
                        if(grid[grid.length-1][j] == true)
                        {
                            uf.union(i, j, grid.length-1, j);
                        }
                        if(grid[grid.length-1][j-1] == true)
                        {
                            uf.union(i, j, grid.length-1, j-1);
                        }
                        if(grid[grid.length-1][j+1] == true)
                        {
                            uf.union(i, j, grid.length-1, j+1);
                        }
                    } else if(i == grid.length-1 && j!= 0 && j!= grid[0].length-1) 
                    {
                        if(grid[i-1][j-1] == true) {
                            uf.union(i, j, i-1, j-1);
                        }
                        if(grid[i-1][j] == true) {
                            uf.union(i, j, i-1, j);
                        }
                        if(grid[i-1][j+1] == true) {
                            uf.union(i, j, i-1, j+1);
                        }
                        if(grid[i][j-1] == true) {
                            uf.union(i, j, i, j-1);
                        }
                        if(grid[i][j+1] == true) {
                            uf.union(i, j, i, j+1);
                        }
                        if(grid[0][j] == true)
                        {
                            uf.union(i, j, 0, j);
                        }
                        if(grid[0][j-1] == true)
                        {
                            uf.union(i, j, 0, j-1);
                        }
                        if(grid[0][j+1] == true)
                        {
                            uf.union(i, j, 0, j+1);
                        }
                    } else if(j == 0 && i != 0 && i != grid.length-1) 
                    {
                        if(grid[i-1][j] == true) {
                            uf.union(i, j, i-1, j);
                        }
                        if(grid[i-1][j+1] == true) {
                            uf.union(i, j, i-1, j+1);
                        }
                        if(grid[i][j+1] == true) {
                            uf.union(i, j, i, j+1);
                        }
                        if(grid[i+1][j] == true) {
                            uf.union(i, j, i+1, j);
                        }
                        if(grid[i+1][j+1] == true) {
                            uf.union(i, j, i+1, j+1);
                        }
                        if(grid[i][grid[0].length-1])
                        {
                            uf.union(i, j, i, grid[0].length-1);
                        }
                        if(grid[i-1][grid[0].length-1] == true)
                        {
                            uf.union(i, j, i-1, grid[0].length - 1);
                        }
                        if(grid[i+1][grid[0].length-1] == true)
                        {
                            uf.union(i, j, i+1, grid[0].length-1);
                        }
                    } else if(j == grid[0].length-1 && i != 0 && i != grid.length-1) 
                    {
                        if(grid[i-1][j-1] == true) {
                            uf.union(i, j, i-1, j-1);
                        }
                        if(grid[i-1][j] == true) {
                            uf.union(i, j, i-1, j);
                        }
                        if(grid[i][j-1] == true) {
                            uf.union(i, j, i, j-1);
                        }
                        if(grid[i+1][j-1] == true) {
                            uf.union(i, j, i+1, j-1);
                        }
                        if(grid[i+1][j] == true) {
                            uf.union(i, j, i+1, j);
                        }
                        if(grid[i][0] == true)
                        {
                            uf.union(i, j, i, 0);
                        }
                        if(grid[i-1][0] == true)
                        {
                            uf.union(i, j, i-1, 0);
                        }
                        if(grid[i+1][0] == true)
                        {
                            uf.union(i, j, i+1, 0);
                        }   
                    } else {
                        if(grid[i-1][j-1] == true) {
                            uf.union(i, j, i-1, j-1);
                        }
                        if(grid[i-1][j] == true) {
                            uf.union(i, j, i-1, j);
                        }
                        if(grid[i-1][j+1] == true) {
                            uf.union(i, j, i-1, j+1);
                        }
                        if(grid[i][j-1] == true) {
                            uf.union(i, j, i, j-1);
                        }
                        if(grid[i][j+1] == true) {
                            uf.union(i, j, i, j+1);
                        }
                        if(grid[i+1][j-1] == true) {
                            uf.union(i, j, i+1, j-1);
                        }
                        if(grid[i+1][j] == true) {
                            uf.union(i, j, i+1, j);
                        }
                        if(grid[i+1][j+1] == true) {
                            uf.union(i, j, i+1, j+1);
                        }
                    }
                    
                }
            }
        }

        ArrayList<Integer> par = new ArrayList<>();

        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j] == true)
                {
                    par.add(uf.find(i, j));
                }
            }
        }

        if(par.size() == 0)
        {
            return 0;
        }

        int[] list = new int[par.size()];

        for(int i = 0; i < list.length; i++)
        {
            list[i] = par.get(i);
        }

        int n = list.length;
        for(int i = 0; i < n; i++)
        {
            int minpos = i;
            for(int j = i+1; j < n; j++)
            {
                if(list[j] < list[minpos])
                {
                    minpos = j;
                }
            }
            int temp = list[i];
            list[i] = list[minpos];
            list[minpos] = temp;
        }
        par.clear();
        par.add(list[0]);
        int index = 0;

        for(int i = 0; i < list.length; i++)
        {
            if(list[i] != par.get(index))
            {
                par.add(list[i]);
                index++;
            }
        }

        return par.size();
        
    }
}

