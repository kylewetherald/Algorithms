import java.awt.*;
import javax.swing.*;

public class ClothPanel extends JPanel {
{
	private ArrayList<Cut> cuts;
	private ArrayList<Garment> garments;
	public Color BG_COLOR = Color.green;
	public Color GARMENT_COLOR = Color.blue;
	public Color LINE_COLOR = Color.black;
	public int height, int width, int magicNumber;
	
	public ClothDisplay( int h, int w, ArrayList<Cut> c, ArrayList<Garment> g)
	{
		this.cuts = c;
		this.garments = g;
		
		// make the display an apropriate size (around 800x600 pixels)
		this.magicNumber = min(800/w, 600/h);
		this.width = this.magicNumber * w;
		this.height = this.magicNumber *h;
		super.setSize(new Dimension(this.width,this.height))
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(this.BG_COLOR)
		g.fillRect(0,0,this.width,this.height);
	}

	public void drawCuts()
	{
		Graphics g = super.getGraphics();
		for(Cut c:cuts)
		{
			g.drawLine(c.xStart(),c.yStart(),c.xEnd(),c.yEnd());
		}
	}
	
	public void drawGarments()
	{
		Graphics g = super.getGraphics();
		for(Garment gar: garments)
		{
			g.fillRect(gar.xCoordinate(),gar.yCoordinate,gar.width(), gar.height());
		}
	}