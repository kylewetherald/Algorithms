/*--------------------------------------------------------------------------------------*/
/* Kyle Wetherald
/* CS1501 
/* Project 5
/* LCS.java finds the longest common substring of two strings
/*--------------------------------------------------------------------------------------*/
import java.io.*;
public class LCS
{
	public static String findLCS(String s1, String s2)
	{
	
		// find the lcs length using a memo
		int[][] lengthMemo = new int[s1.length()+1][s2.length()+1];
		// for empty string, length is 0;
		// i=0 and j=0 anre automatically set to 0 in the matrix.
		for(int i=0; i<s1.length(); i++)
		{
			for(int j=0; j<s2.length(); j++)
			{
				//if c1 = c2, length is the length of the previous cahracter +1;
				if(s1.charAt(i) == s2.charAt(j))
				{
					lengthMemo[i+1][j+1] = lengthMemo[i][j]+1;
				}
				//otherwise, the length is the maximum of LCS(s1+c1,s2), or eturn LCS(s1,s2+c2)
				else
				{
					lengthMemo[i+1][j+1] = Math.max(lengthMemo[i+1][j], lengthMemo[i][j+1]);
				}
			}
		}
		
		//find the actual subsequence
		String result = "";
		//start at the bottom right of the lengthMemo matrix
		int i=s1.length();
		int j=s2.length();
		//while you haven't yet reached the beginning 
		while(i>0 && j>0)
		{
			//if the length isn't incremented, the character was not a match.
			if(lengthMemo[i][j]==lengthMemo[i-1][j])
			{
				i--;
			}
			else if(lengthMemo[i][j]==lengthMemo[i][j-1])
			{
				j--;
			}
			//if the length incremented, the character is a match, 
			//so add it at the beginning of the string.
			else
			{
				result = s1.charAt(i-1)+result;
				i--;
				j--;
			}
		}
		return result;
	}
}