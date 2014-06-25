/**
 * Kyle Wetherald
 * CS 1501
 * Project 4
 * city.java data structure to hold coordinates of a destination in the 
 * Euclidean Travelins Salesman Problem
**/

import java.util.ArrayList;

public class City
{
	public int x;
	public int y;
	public ArrayList<City> children;
	public City closest;
	public double distance;
	public String name;
	
	public City(int lon, int lat, String n)
	{
		this.x = lon;
		this.y = lat;	
		this.children  = new ArrayList<City>();
		this.name = n;
		this.distance = 0;
	}
	
	public void addChild( City childCity )
	{
		this.children.add( childCity );
	}
	
	public void closest( City parentCity )
	{
		this.closest = parentCity;
	}
}