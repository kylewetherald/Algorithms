import java.io.*;
import java.util.ArrayList;

public class Tester
{
	public static void main(String [] args)
	{
		ArrayList<Pattern> patterns = new ArrayList<Pattern>() ;
	    patterns.add(new Pattern(2,2,1)) ;
	    patterns.add(new Pattern(2,6,4)) ;
	    patterns.add(new Pattern(4,2,3)) ;
	    patterns.add(new Pattern(5,3,5)) ;
	    int width = 30;
	    int height = 15;
	    ClothCutter cutter = new ClothCutter(width,height,patterns) ;

    	System.out.println( cutter.optimize() );
	}
}