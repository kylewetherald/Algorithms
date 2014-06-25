/**
 * Kyle Wetherald
 * CS 1501 
 * Project 4
 * DemonstrateEuclideanTSP.java
 * creates a set of random cities and determines an acceptible route for the
 * Euclidean Traveling Salesman Problem
 **/
 
 import java.io.*;
 import java.util.*;
 import javax.swing.*;
 
 public class DemonstrateEuclideanTSP
 {
 City[] cities = new City[6];
 EuclideanTSP tsp;
 	public static void main( String[] args )
 	{
 		// get number of cities
 		if( args.length != 1 )
 		{
 			System.out.println("Enter number of cities as a command line argument.");
 			System.exit(0);
 		}
 		int numberOfCities = Integer.parseInt(args[0]);
 		City[] cities = new City[numberOfCities]; //city array
 		Random r = new Random();				  //city array
 		char[] name = new char[3];				  //char array to hold random name
 		
 		// fill city array with random cities that have random names
 		for(int i=0 ; i<numberOfCities ; i++ )
 		{
 			name[0] = (char)(r.nextInt(26) + 'A');
			name[1] = (char)(r.nextInt(26) + 'a');
			name[2] = (char)(r.nextInt(26) + 'a');
 			cities[i] = new City(r.nextInt(800),r.nextInt(600), new String(name) );
 		}
 		
  		// init Map display
  		Map map = new Map();
 		JFrame frame = new JFrame("Traveling Samesman Itinerary");
 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.getContentPane().add(map);
    	frame.pack();
    	frame.setVisible(true);
    	sleep(1000);
  		
  		// add cities to map
  		for(int i=0 ; i<cities.length ; i++)
  		{
  			map.drawCity(cities[i]);
  		}
  		
		// find MST
  		EuclideanTSP tsp = new EuclideanTSP( cities );
  		System.out.println("MST Weight: " + tsp.findMST());
  		ArrayList<Edge> edges = tsp.getEdges();
  		sleep(1000);
  		
  		// add MST to map
  		for( int i=0 ; i<edges.size() ; i++ )
  		{
  			map.drawEdge( edges.get(i) );
  		}
  		sleep(1000);
  		
  		// find itinerary
  		ArrayList<City> routes = tsp.getItinerary();
  		double tripLength=0;
  		
  		// add route to map
  		for(int i=0 ; i<routes.size()-1 ; i++)
  		{
  			sleep(10);
  			map.drawRoute(routes.get(i),routes.get(i+1));
  			int height = Math.abs( routes.get(i).y - routes.get(i+1).y );
  			int width = Math.abs( routes.get(i).x - routes.get(i+1).x );
  			tripLength += Math.sqrt( height*height + width*width );
  		}
  		System.out.println("MST Tour Length: " + tripLength);
 	}	
 	
 	public static void sleep(long milliseconds) 
	{	
    	Date d ;
    	long start, now ;
    	d = new Date() ;
    	start = d.getTime() ;
    	do 
    	{ 
    		d = new Date() ; 
    		now = d.getTime() ; 
    	}while( (now - start) < milliseconds );
  	}
 }