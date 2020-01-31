/*
        The following program generates a sudoku. It has been developed by:
        USN             Name
        1CR15CS092      Manjunath
        1CR15CS096      Mir Owais
        1CR15CS109      P. Raghavendra
        1CR15CS111      Prajwal Dhatwalia
        
        Subject         Design & Analysis of Algorithms (15CS43)
        Faculty         Dr. Jhansi Rani
        Dept            CSE
        Organisation    CMRIT,Bangalore   
*/
// Editted by ANON192
import java.util.*;
import java.text.*;

public class CMRSudoku
{
	/**Test case
	//0 marks an empty spot
	static int[][] board = {
			{ 1,2,3, 4,5,6, 7,8,9 },
			{ 4,5,6, 7,8,9, 1,2,3 },
			{ 7,8,9, 1,2,3, 4,5,6 },
			{ 2,3,1, 5,6,4, 8,9,7 },
			{ 5,6,4, 8,9,7, 2,3,1 },
			{ 8,9,7, 2,3,1, 5,6,4 },
			{ 3,1,2, 6,4,5, 9,7,8 },
			{ 6,4,5, 9,7,8, 3,1,2 },
			{ 9,7,8, 3,1,2, 6,4,5 } };
	 **/
	static int[][] board = {
			{ 0,0,0, 4,5,6, 7,8,9 },
			{ 4,5,6, 7,8,9, 1,2,3 },
			{ 7,8,9, 1,2,3, 4,5,6 },
			{ 2,3,1, 5,6,4, 8,9,7 },
			{ 5,6,4, 8,9,7, 2,3,1 },
			{ 8,9,7, 2,3,1, 5,6,4 },
			{ 3,1,2, 6,4,5, 9,7,8 },
			{ 6,4,5, 9,7,8, 3,1,2 },
			{ 9,7,8, 3,1,2, 6,4,5 } };

	private int operations;


	public CMRSudoku()        // Constructor
        {
   	        //board = new int[9][9];
        }

	public int[][] nextBoard(int difficulty)
	{
		//board = new int[9][9];
		nextCell(0,0);
		makeHoles(difficulty);
		return board;

	}


	public boolean nextCell(int x, int y)
	{
		int nextX = x;
		int nextY = y;
		int[] toCheck = {1,2,3,4,5,6,7,8,9};
		Random r = new Random();
		int tmp = 0;
		int current = 0;
		int top = 9;

   		for(int i=top-1;i>0;i--)
		{
		    current = r.nextInt(i);
		    tmp = toCheck[current];
		    toCheck[current] = toCheck[i];
		    toCheck[i] = tmp;
    	        }
		
		for(int i=0;i<toCheck.length;i++)
		{
			if(legalMove(x, y, toCheck[i]))
			{
				board[x][y] = toCheck[i];
				if(x == 8)
				{
					if(y == 8)
						return true;       
					else
					{
						nextX = 0;
						nextY = y + 1;
					}
				}
				else
				{
					nextX = x + 1;
				}
				if(nextCell(nextX, nextY))
					return true;
			}
		}
		board[x][y] = 0;
		return false;
	}
	
	private boolean legalMove(int x, int y, int current) 
	{
		int cornerX = 0, cornerY = 0;
		
		for(int i=0;i<9;i++) 
			if(current == board[x][i])
				return false;
		for(int i=0;i<9;i++) 
			if(current == board[i][y])
				return false;
				
		if(x > 2)
	                if(x > 5)
				cornerX = 6;
			else
				cornerX = 3;
		if(y > 2)
			if(y > 5)
				cornerY = 6;
			else
				cornerY = 3;

		for(int i=cornerX;i<10 && i<cornerX+3;i++)
			for(int j=cornerY;j<10 && j<cornerY+3;j++)
				if(current == board[i][j])
					return false;
		return true;
	}

	public void makeHoles(int h)    // h is the holes to be made
	{
		double remS = 81;               // remaining squares
		double remH = h;        // remaining holes

		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
			{
				double chance = remH/remS;
				if(Math.random() <= chance)
				{
					board[i][j] = 0;
					remH--;
				}
				remS--;
			}
	}

	public void display()
	{
		System.out.println();
		for(int i=0;i<9;i++)
		{
		        if(i%3 == 0 && i!=0)                            // to display _ _ _
		        {
		                for(int k=0;k<15;k++)
	                                System.out.print("_ ");
                                System.out.println("\n");
			}
			for(int j=0;j<9;j++)
			{
			        if(j%3 == 0 && j!=0)                    // to display |
			                System.out.print("| ");
			        if(board[i][j] != 0)
				        System.out.print(board[i][j]+"  ");
			        else
			                System.out.print("   ");
	                }
			System.out.println();
		}
		System.out.println();
	}

	public static boolean isCorrect(){
		Boolean[] currentCheckRow = {false, false, false, false, false, false, false, false, false};
		Boolean[] currentCheckColumn = {false, false, false, false, false, false, false, false, false};
		Boolean[] grid0 = {false, false, false, false, false, false, false, false, false};
		Boolean[] grid1 = {false, false, false, false, false, false, false, false, false};
		Boolean[] grid2 = {false, false, false, false, false, false, false, false, false};
		Boolean[] grid3 = {false, false, false, false, false, false, false, false, false};
		Boolean[] grid4 = {false, false, false, false, false, false, false, false, false};
		Boolean[] grid5 = {false, false, false, false, false, false, false, false, false};
		Boolean[] grid6 = {false, false, false, false, false, false, false, false, false};
		Boolean[] grid7 = {false, false, false, false, false, false, false, false, false};
		Boolean[] grid8 = {false, false, false, false, false, false, false, false, false};

		/** FOR VISUALIZATION
		// i = 0 - 2, j = 0 - 2
		Boolean[][] grid0 = {
				{false, false, false},
				{false, false, false},
				{false, false, false}
		};
		// i = 2 - 5, j = 0 - 2
		Boolean[][] grid1 = {
				{false, false, false},
				{false, false, false},
				{false, false, false}
		};
		// i = 5 - 8, j = 0 - 2
		Boolean[][] grid2 = {
				{false, false, false},
				{false, false, false},
				{false, false, false}
		};
		// i = 0 - 2, j = 2 - 5
		Boolean[][] grid3 = {
				{false, false, false},
				{false, false, false},
				{false, false, false}
		};
		// i = 2 - 5, j = 2 - 5
		Boolean[][] grid4 = {
				{false, false, false},
				{false, false, false},
				{false, false, false}
		};
		// i = 5 - 8, j = 2 - 5
		Boolean[][] grid5 = {
				{false, false, false},
				{false, false, false},
				{false, false, false}
		};
		// i = 0 - 2, j = 5 - 8
		Boolean[][] grid6 = {
				{false, false, false},
				{false, false, false},
				{false, false, false}
		};
		// i = 2 - 5, j = 5 - 8
		Boolean[][] grid7 = {
				{false, false, false},
				{false, false, false},
				{false, false, false}
		};
		// i = 5 - 8, j = 5 - 8
		Boolean[][] grid8 = {
				{false, false, false},
				{false, false, false},
				{false, false, false}
		};
		 **/

		// Traverse the puzzle
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){

				if(board[i][j] == 0){
					return false;
				}
				// Mark all the numbers found in the first row and column
				currentCheckRow[board[i][j] - 1] = true;
				currentCheckColumn[board[j][i] - 1] = true;

				// Mark all in subGrids
				if(i < 3){
					if(j < 3){
						//0
						grid0[board[i][j] - 1] = true;
					} else if(j < 6){
						//1
						grid1[board[i][j] - 1] = true;
					} else {
						//2
						grid2[board[i][j] - 1] = true;
					}
				} else if(i < 6){
					if(j < 3){
						//3
						grid3[board[i][j] - 1] = true;
					} else if(j < 6){
						//4
						grid4[board[i][j] - 1] = true;
					} else {
						//5
						grid5[board[i][j] - 1] = true;
					}
				} else {
					if(j < 3){
						//6
						grid6[board[i][j] - 1] = true;
					} else if(j < 6){
						//7
						grid7[board[i][j] - 1] = true;
					} else {
						//8
						grid8[board[i][j] - 1] = true;
					}
				}
			}

			// At end of each row and column confirm there is only 1 instance of each digit
			if(Arrays.asList(currentCheckRow).contains(false) || Arrays.asList(currentCheckColumn).contains(false)){

				// If there is more than one instance of a single digit then the puzzle is incorrect
				return false;
			}
			System.out.println(Arrays.toString(currentCheckRow));
			System.out.println(Arrays.toString(currentCheckColumn));


			resetCheckArray(currentCheckRow);
			resetCheckArray(currentCheckColumn);

		}

		// Testing stuff
		System.out.println("\n" + "grids time" + "\n");
		System.out.println(Arrays.toString(grid0));
		System.out.println(Arrays.toString(grid1));
		System.out.println(Arrays.toString(grid2));
		System.out.println(Arrays.toString(grid3));
		System.out.println(Arrays.toString(grid4));
		System.out.println(Arrays.toString(grid5));
		System.out.println(Arrays.toString(grid6));
		System.out.println(Arrays.toString(grid7));
		System.out.println(Arrays.toString(grid8));

		// Check all subgrids once all loops have been traversed and subgrids have been marked
		if(Arrays.asList(grid0).contains(false) || Arrays.asList(grid1).contains(false) || Arrays.asList(grid2).contains(false) || Arrays.asList(grid3).contains(false) || Arrays.asList(grid4).contains(false) || Arrays.asList(grid5).contains(false) || Arrays.asList(grid6).contains(false) || Arrays.asList(grid7).contains(false) || Arrays.asList(grid8).contains(false)){
			return false;
		}

		// If no instances of the same digit is found then the answer must be correct
		return true;
	}

	public static void resetCheckArray(Boolean[] currentCheckArray) {

		// Reset checks for next column/row
		currentCheckArray[0] = false;
		currentCheckArray[1] = false;
		currentCheckArray[2] = false;
		currentCheckArray[3] = false;
		currentCheckArray[4] = false;
		currentCheckArray[5] = false;
		currentCheckArray[6] = false;
		currentCheckArray[7] = false;
		currentCheckArray[8] = false;
	}

	public void insertNum(int x, int y, int num){

		// Error check first
		if(num < 1 || num > 9){
			System.out.println("num out of range of 1-9");
		} else if(board[x][y] != 0) {
			System.out.println("spot taken, choose another place on grid");
		} else {

			// Once validated number, add it to the grid
			board[x][y] = num;
		}
		this.display();
	}


	public static void main(String[] args){
		CMRSudoku obj = new CMRSudoku();
		//obj.nextBoard(51);                                       // greater this number, greater the difficulty
		obj.display();
		System.out.println("Is this solution true? " + isCorrect());
		obj.insertNum(0, 0, 1);
		System.out.println("Is this solution true? " + isCorrect());
		obj.insertNum(0, 1, 2);
		obj.insertNum(0, 2, 3);
		System.out.println("Is this solution true? " + isCorrect());

	}

}
