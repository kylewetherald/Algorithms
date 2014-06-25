/**
 * Kyle Wetherald
 * CS 1502
 * Project 4
 * Edge.java
 * contains information on edge between nodes of MST
**/

public class Edge
{
	public City parent;
	public City child;
	
	public Edge( City p, City c )
	{
		this.parent = p;
		this.child  = c;
	}
}

