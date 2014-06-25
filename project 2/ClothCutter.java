/*
 *	Kyle Wetherald
 *	CS1501 
 *	Cloth Cutting problem
**/

import java.util.ArrayList;

public class ClothCutter
{
	private int originalWidth;
	private int originalHeight;
	private ArrayList<Pattern> patterns;
	public ArrayList<Cut> cuts;
	public ArrayList<Garment> garments;
	private Cloth[][] memo;
	private int optimizedValue;
	
	
	private static final int NONE = 0;
	private static final int VERTICAL = 1;
	private static final int HORIZONTAL = 2;
	

	/*---- intitializes this cloth cutter ----------------------------------------------*/
	public ClothCutter( int w, int h, ArrayList<Pattern> p )
	{	
		this.originalWidth 	= w;
		this.originalHeight = h;
		this.patterns 		= p;
		this.memo 			= new Cloth[w][h];
		this.cuts 			= new ArrayList<Cut>();
		this.garments		= new ArrayList<Garment>();
	}
	
	/*---- returns the optimized series of cuts ----------------------------------------*/
	public int optimize()
	{
		optimizedValue = optimize(this.originalWidth, this.originalHeight).value();
		if( optimizedValue < 0 )
			optimizedValue = 0;
		this.makeCuts();
		return optimizedValue;
	}	
										   	   
	/*---- memoized recursve cut optimization returns optimal value --------------------*/
	private Cloth optimize(int width, int height)
	{
		int optimalCut = NONE;
		
		if(memo[width-1][height-1] != null)
			return memo[width-1][height-1];
			
		int bestValue = -1; 
		Cloth optimizedCloth = new Cloth(-1,NONE,0);
		
		// try fitting every pattern.
		for(int i=0 ; i<patterns.size() ; i++)
		{
			Pattern p = patterns.get(i);
			if((p.width() <= width) && (p.height() <= height) && (p.value() > bestValue))
			{
				bestValue = p.value();	
				optimizedCloth = new Cloth(bestValue, NONE, i);
			}
		}
		
		// if nothing fits, stop the search here.
		// adding -1 to the memo shows that the cloth has gone through the memoization
		// algorithm and no patterns were found that fit in the cloth.
		// no cuts or patterns should be recorded.
		if(bestValue < 0)
		{
			memo[width-1][height-1] = optimizedCloth;
			return optimizedCloth;
		}
		
		// try all vertical cuts
		int thisValue;
		for(int i=1 ; i < width ; i++)
		{
			thisValue  	= optimize(i,height).value() + optimize(width-i,height).value();
			if( thisValue > bestValue )
			{
				bestValue = thisValue;
				optimizedCloth = new Cloth(bestValue, VERTICAL, i);	
			}
		}
		
		// try all horizontal cuts
		for(int i=1 ; i < height ; i++)
		{
			thisValue = optimize(width,i).value() + optimize(width,height-i).value();
			if( thisValue > bestValue )
			{
				bestValue = thisValue;
				optimizedCloth = new Cloth(bestValue, HORIZONTAL, i);
			}
		}
		
		// memoize the result
		memo[width-1][height-1] = optimizedCloth;
		return optimizedCloth;
	}
	
	/*---- generates and returns ArrayList of cuts and patterns to me made -------------*/
	private void makeCuts()
	{
		makeCuts(0,0,this.originalWidth,this.originalHeight);
	}
	
	private void makeCuts(int x, int y, int width, int height)
	{
		Cloth cloth = optimize(width,height);

		if(cloth.cut() == NONE)
		{
			Pattern pattern = this.patterns.get( cloth.location() );
			this.garments.add(  new Garment( x, y, pattern.width(), pattern.height(), pattern.name() )  );
		}
		else if(cloth.cut() == VERTICAL)
		{
			this.cuts.add(  new Cut( x+cloth.location() , y, x+cloth.location() , y+height)  );
			makeCuts(x, y, cloth.location(), height);
			makeCuts(x+cloth.location(), y, width-cloth.location(), height);
		}
		else
		{
			this.cuts.add(  new Cut( x, y+cloth.location(), x+width, y+cloth.location())  );
			makeCuts(x, y, width, cloth.location() );
			makeCuts(x, y+cloth.location(), width, height-cloth.location() );
		}
	}	
	
	/*---- returns an ArrayList of Cuts ------------------------------------------------*/
	public ArrayList<Cut> getCuts()
	{
		return this.cuts;
	}
	
	/*---- returns an ArrayList of Garments --------------------------------------------*/
	public ArrayList<Garment> getGarments()
	{
		return this.garments;
	}
}

