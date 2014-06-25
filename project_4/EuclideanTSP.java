/**
 * Kyle Wetherald
 * CS 1501
 * EuclideanTSP.java
 * uses a modified version of Prim's MST algorithm to find an acceptable solution to the
 * Euclidean Traveling Salesman Problem
**/

import java.util.*;

public class EuclideanTSP
{

	public City[] cities;
	private double[] distanceMemo;
	private boolean[] visited;
	private int[] closest;
	private ArrayList<Edge> edges;

	//---- inti ----
	public EuclideanTSP( City[] c )
	{
		this.cities = c;
	}
	
	//---- modified Prim algorithm to find the MST of a set of Cities ----
	public double findMST()
	{
		double totalDistance = 0;
		int height;
		int width;
		
		// find the distance of every city from the start city
		// i marks the separation in the array between visited/unvisited cities
		for( int i=1 ; i<cities.length ; i++ )
		{
			height = Math.abs( cities[i].x - cities[0].x );
			width = Math.abs( cities[i].y - cities[0].y );
			cities[i].distance = Math.sqrt( (height * height)  +  (width * width) );
			cities[i].closest = cities[0];
		}
		
		// for each city not yet visited:
		for( int i=1 ; i<cities.length ; i++ )
		{
			double bestDistance = Double.MAX_VALUE;
			int bestCity = -1;
			
			// find the city closest to the MST
			for( int j=i ; j<cities.length ; j++ )
			{
				if( cities[j].distance < bestDistance )
				{
					bestCity = j;
					bestDistance = cities[j].distance;
				}
			}
			// add the closest one to the MST
			cities[bestCity].closest.addChild(cities[bestCity]);
			totalDistance += bestDistance;
			// mark city as visited
			if( i < cities.length-1)
			{
				City temp = cities[i];
				cities[i] = cities[bestCity];
				cities[bestCity] = temp;
			}
			
			// update distance of each city from the MST
			double distance;
			for( int j=i+1 ; j<cities.length ; j++ )
			{
				height = Math.abs( cities[i].x - cities[j].x );
				width = Math.abs( cities[i].y - cities[j].y );
				distance = Math.sqrt( (height * height)  +  (width * width) );
				
				if ( distance < cities[j].distance )
				{
					cities[j].closest = cities[i];
					cities[j].distance = distance;
				}
			}
		}
		return totalDistance;
	}
	
	public ArrayList<Edge> getEdges()
	{
		edges = new ArrayList<Edge>(cities.length-1);
		getEdges(cities[0]);
		return edges;
	}
	
	// recursive finds all of the edges in the MST
	public void getEdges(City c)
	{
		for(int i=0 ; i<c.children.size() ; i++)
		{
			edges.add(new Edge(c.children.get(i),c));
			getEdges(c.children.get(i));
		}
	}
	
	// does a walk of the MST without returning to parent cities
	public ArrayList<City> getItinerary()
	{	
		ArrayList<City> itinerary = new ArrayList<City>(cities.length);
		Stack<City> stack = new Stack<City>();
		itinerary.add( stack.push( cities[0] ) );
		while( !stack.empty() )
		{
			City currentCity = stack.peek();
			if( currentCity.children.isEmpty() )
				stack.pop();
			else
			{
				itinerary.add( stack.push( currentCity.children.remove(0) ) );
			}
		}
		itinerary.add( cities[0] );
		return itinerary;
	}
}