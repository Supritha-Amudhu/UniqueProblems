
public class Solution1 {
	static int calculateNumberOfPaths(int m, int n)
	{
		int numberOfPaths[][] = new int[m][n];

		for (int i = 0; i < m; i++){
			numberOfPaths[i][0] = 1;
		}

		for (int j = 0; j < n; j++){
			numberOfPaths[0][j] = 1;
		}

		for (int i = 1; i < m; i++)
		{
			for (int j = 1; j < n; j++){
				numberOfPaths[i][j] = numberOfPaths[i-1][j] + numberOfPaths[i][j-1]+ numberOfPaths[i-1][j-1];
			}
		}
		return numberOfPaths[m-1][n-1];
	}


	public static void main(String args[])
	{
		System.out.println(calculateNumberOfPaths(2, 2));
	}
	
	
}
