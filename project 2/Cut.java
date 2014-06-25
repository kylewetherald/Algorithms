/*
 *	Kyle Wetherald
 *	CS1501 
 *	Cloth Cutting problem
 *	Cloth data structure to hold information about cuts to be drawn on a ClothPanel
**/

public class Cut
{
	private int xStart;
	private int yStart;
	private int xEnd;
	private int yEnd;
	
	public Cut(int x1, int y1, int x2, int y2)
	{
		this.xStart = x1;
		this.yStart = y1;
		this.xEnd = x2;
		this.yEnd = y2;
	}
	
	public int xStart()
	{
		return this.xStart;
	}
	public int yStart()
	{
		return this.yStart;
	}
	public int xEnd()
	{
		return this.xEnd;
	}
	public int yEnd()
	{
		return this.yEnd;
	}
	public String toString()
	{
		return "(" + this.xStart +", "+ this.yStart +") to ("+ this.xEnd +", "+ this.yEnd +")";
	}
}