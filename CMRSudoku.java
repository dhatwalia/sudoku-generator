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
        int board[][];
	private int operations;
	
        public CMRSudoku()        // Constructor
        {
   	        board = new int[9][9];
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
		double remH = (double)h;        // remaining holes
		
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
	
	public static void main(String[] args)
	{
		CMRSudoku obj = new CMRSudoku();
		obj.nextBoard(51);                                       // greater this number, greater the difficulty
		obj.display();
	}
}
