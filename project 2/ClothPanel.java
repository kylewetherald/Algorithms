/*
 *	Kyle Wetherald
 *	CS1501 
 *	Cloth Cutting problem
 *	An extension of JPanel that allows representations of cuts and garments to be visually displayed
**/

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class ClothPanel extends JPanel 
{
	public Color BG_COLOR = Color.green;
	public Color GARMENT_COLOR = Color.blue;
	public Color LINE_COLOR = Color.black;
	
	public int height, width;
	public int magicNumber; 	
	//magicNumber = amount to multiply each dimension to make the cloth an appropriate size on the screen
	
	public ClothPanel( int w, int h)
	{		
		// make the display an apropriate size (around 800x600 pixels)
		this.magicNumber = Math.min(800/w, 600/h);
		this.width = this.magicNumber * w;
		this.height = this.magicNumber * h;
		super.setPreferredSize(new Dimension(this.width,this.height));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(this.BG_COLOR);
		g.fillRect(0,0,this.width,this.height);
		g.setColor(this.LINE_COLOR);
		g.drawLine(0,0,0,this.height);
		g.drawLine(0,0,this.width,0);
	}

	public void drawCut(Cut c)
	{
		Graphics g = super.getGraphics();
		g.setColor(this.LINE_COLOR);
		g.drawLine(c.xStart()*magicNumber, c.yStart()*magicNumber, c.xEnd()*magicNumber, c.yEnd()*magicNumber);
	}
	
	public void drawGarment(Garment gar)
	{
		Graphics g = super.getGraphics();
		g.setColor(this.GARMENT_COLOR);
		g.fillRect(gar.xCoordinate()*magicNumber+1,gar.yCoordinate()*magicNumber+1,gar.width()*magicNumber-1, gar.height()*magicNumber-1);
		g.setColor(this.LINE_COLOR);
		g.drawString(gar.label(),gar.xCoordinate()*magicNumber+3,gar.yCoordinate()*magicNumber+15);
	}

}