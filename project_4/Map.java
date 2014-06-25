/*
 *	Kyle Wetherald
 *	CS1501 
 *	Cloth Cutting problem
 *	An extension of JPanel that allows representations of cities and routes to be visually displayed
**/

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;

public class Map extends JPanel 
{
	public Color BG_COLOR = new Color(238,216,174);
	public Color CITY_COLOR = new Color(0,76,153);
	public Color NAME_COLOR = Color.black;
	public Color EDGE_COLOR = Color.gray;
	public Color ROUTE_COLOR = Color.red;

	//---- intit ----
	public Map()
	{		
		// make the display an apropriate size (around 800x600 pixels)
		super.setPreferredSize(new Dimension(800,600));
	}
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(BG_COLOR);
		g.fillRect(0,0,800,600);
	}

	//---- draws a small circle to represent each city ----
	public void drawCity(City c)
	{
		Graphics g = super.getGraphics();
		g.setColor(CITY_COLOR);
		g.fillOval(c.x-5, c.y-5, 10,10);
		g.setColor(NAME_COLOR);
		g.drawString(c.name,c.x+10,c.y);
	}
	
	//---- draws grey lines to represent MST edges ----
	public void drawEdge(Edge e)
	{
		Graphics g = super.getGraphics();
		g.setColor(this.EDGE_COLOR);
		g.drawLine(e.parent.x, e.parent.y, e.child.x, e.child.y);
	}
	
	//---- draws red lines to represent routes between cities ----
	public void drawRoute(City from, City to)
	{
		Graphics g = super.getGraphics();
		g.setColor(this.ROUTE_COLOR);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke( new BasicStroke(3) );
		g2.drawLine(from.x, from.y, to.x, to.y);
	}

}