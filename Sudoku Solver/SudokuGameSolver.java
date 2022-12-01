public class SudokuGameSolver{
    
    //size of the grid
    public static final int GRID_SIZE = 9;
    public static void main(String[] args) {
        
        //sudoku board to be solved
        int [][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9},
        };

        //calling solveBoard method
        if(solveBoard(board))
        {
            System.out.println("Solved Successfully! ");
        }
        else
        {
            System.out.println("Invalid Board!!! ");
        }

        printBoard(board);
    }

    //method for printing the solved board
    private static void printBoard(int[][] board)
    {
        for(int row = 0; row<GRID_SIZE; row++)
        {
            for(int column = 0; column<GRID_SIZE; column++)
            {
                System.out.print(board[row][column] +" ");
            }
            System.out.println();
        }
    }

    //check if the number is already present in the given row
    public static boolean isNumberInRow(int[][] board, int number, int row)
    {
        for(int i=0; i<GRID_SIZE; i++)
        {
            if(board[row][i] == number)
            {
                return true;
            }
        }
        return false;
    }

    //check if the number is already present in the given column
    public static boolean isNumberInColumn(int[][] board, int number, int column)
    {
        for(int i=0; i<GRID_SIZE; i++)
        {
            if(board[i][column] == number)
            {
                return true;
            }
        }
        return false;
    }

    //check if the number is already in the 3x3 box
    public static boolean isNumberInBox(int[][] board, int number, int row, int column)
    {
        //formula for the 3x3 matrix starting coordinates
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for(int i = localBoxRow; i<localBoxRow+3; i++)
        {
            for(int j = localBoxColumn; j<localBoxColumn+3; j++)
            {
                if(board[i][j] == number)
                {
                    return true;
                }
            }
        }
        return false;
    }

    //check for all the above condition in a single method
    public static boolean isValidPlacement(int [][] board, int number, int row, int column)
    {
        return !isNumberInRow(board, number, row) && !isNumberInColumn(board, number, column) 
                && !isNumberInBox(board, number, row, column);
    }

    //full board checkup
    public static boolean solveBoard(int [][] board)
    {
        for(int row = 0; row<GRID_SIZE; row++)
        {
            for(int column = 0; column<GRID_SIZE; column++)
            {
                if(board[row][column] == 0)
                {
                    for(int numberToTry = 1; numberToTry<=GRID_SIZE; numberToTry++)
                    {
                        if(isValidPlacement(board, numberToTry, row, column))
                        {
                            board[row][column] = numberToTry;

                            if(solveBoard(board))
                            {
                                return true;
                            }
                            else
                            {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}