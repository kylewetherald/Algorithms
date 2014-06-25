/*
 *	Kyle Wetherald
 *	CS1501 
 *	Cloth Cutting problem
 *	Cloth data structure to hold information about pieces of cloth
**/
public class Cloth
{	
	private int value;		// the optimized value
	private int cut;		// the type of cut to me made (zero represents no cut made)
	private int location;   // the location of the cut, or the location of the pattern in the ArrayList of patterns
	
	private static final int NONE = 0;
	private static final int VERTICAL = 1;
	private static final int HORIZONTAL = 2;
	
	
	public Cloth(int v, int c, int l)
	{
		this.value		= v;
		this.cut   		= c;
		this.location 	= l;
	}
	
	public int value()
	{
		return this.value;
	}
	
	public int cut()
	{
		return this.cut;
	}
	
	public int location()
	{
		return this.location;
	}
	
	public String toString()
	{
		if(cut == NONE)
		{
			return "  Value: " + this.value + "\n  Pattern: " + this.location;
		}
		if(cut == VERTICAL)
		{
			return "  Value: " + this.value + "\n  Vertical: " + this.location;
		}
		return "  Value: " + this.value + "\n  Horizontal: " + this.location;
	}

}