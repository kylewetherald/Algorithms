/*
 *	Kyle Wetherald
 *	CS1501 
 *	Cloth Cutting problem
 *	A program to test the functionality of the cloth-cutting program
**/

import java.io.*;
import java.util.*;
import javax.swing.* ;

public class ClothCutterTester
{
	public static void main(String [] args)
	{
		ArrayList<Pattern> patterns = new ArrayList<Pattern>() ;
		ArrayList<Cut> cuts;
		ArrayList<Garment> garments;
		
	    patterns.add(new Pattern(2,2,1,"Tie")) ;
	    patterns.add(new Pattern(2,6,4,"Skirt")) ;
	    patterns.add(new Pattern(4,2,3,"Blouse")) ;
	    patterns.add(new Pattern(5,3,5,"Dress")) ;
	    int width = 30;
	    int height = 15;
	    ClothCutter cutter = new ClothCutter(width,height,patterns) ;
    	System.out.println( "Optimized value: " + cutter.optimize() );
    	cuts = cutter.getCuts();
    	garments = cutter.getGarments();
    	
    	ClothPanel panel = new ClothPanel(width, height);
    	JFrame frame = new JFrame("A luxurious bolt of fabric");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.getContentPane().add(panel);
    	frame.pack();
    	frame.setVisible(true);
    	sleep(1000);
    	for(int i=0 ; i<cuts.size() ; i++)
    	{
    		panel.drawCut(cuts.get(i));
    		sleep(100);
    	}
    	for(int i=0 ; i<garments.size() ; i++)
    	{
    		System.out.println(garments.get(i));
    		panel.drawGarment(garments.get(i));
    		sleep(100);
    	}
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