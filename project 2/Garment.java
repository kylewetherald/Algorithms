/*
 *	Kyle Wetherald
 *	CS1501 
 *	Cloth Cutting problem
 *	Cloth data structure to hold information about garments to be drawn on a ClothPanel
**/

public class Garment
{
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	private String label;
	
	public Garment(int x, int y, int w, int h, String name)
	{
		this.xCoord = x;
		this.yCoord = y;
		this.width 	= w;
		this.height = h;
		this.label  = name;
	}
	public int xCoordinate()
	{
		return this.xCoord;
	}
	public int yCoordinate()
	{
		return this.yCoord;
	}
	public int width()
	{
		return this.width;
	}
	public int height()
	{
		return this.height;
	}
	public String label()
	{
		return this.label;
	}
	public String toString()
	{
		return this.label +" at ("+ this.xCoord +", "+ this.yCoord +")"; 
	}
}