/* 
 * Kyle Wetherald
 * 1501 Project 1
 * HugeInteger class that showcases the Karatsuba-Ofman 
 * fast multiplication algorithm
**/

import java.util.*;

public class HugeInteger implements Comparable<HugeInteger>
{
	private ArrayList<Integer> digits;
	public static int DIGIT_OPERATIONS = 0;
	

// init methods

	/*---- creates a new HugeInteger from a string of integers --------------------*/
	public HugeInteger(String s)
	{
		this.digits= new ArrayList<Integer>(s.length());
		for(int i=0; i<s.length(); i++)
			this.digits.add( s.charAt(s.length()-1-i) - '0');
	}
	
	/*---- creates a new HugeInteger of specified size ----------------------------*/
	public HugeInteger(int size)
	{
		this.digits = new ArrayList<Integer>(size);
	}
	
	/*---- creates a new HugeInteger using a supplied ArrayList<Integer> ----------*/
	public HugeInteger(ArrayList<Integer> list)
	{
		this.digits = list;
	}
// required methods

	/*---- compares this HugeInteger to provided HugeInteger for order ------------*/
	public int compareTo(HugeInteger that)
	{
		if( this == that )
			return 0;

		else if( this.size() > that.size() )
		{
			return 1;
		}
		else if( this.size() < that.size() )
		{
			return -1;
		}
		else
		{
			for(int i=this.size()-1; i>=0; i--)
			{
				int thisDigit = this.digitAt(i);
				int thatDigit = that.digitAt(i);
				DIGIT_OPERATIONS++;
				if( thisDigit > thatDigit )
					return 1;
				else if( thisDigit < thatDigit )
					return -1;
			}
			return 0;
		}
	}
	

	
	/*---- returns the sum of this and the provided HugeInteger -------------------*/
	public HugeInteger add(HugeInteger that)
	{
		this.removeLeadingZeroes();
		that.removeLeadingZeroes();
		
		HugeInteger result = new HugeInteger( max(this.size(), that.size()) );

		int carry = 0;
		int position = 0;
		int sum;
		while( position < min(this.size(), that.size()) )
		{
			sum = this.digitAt(position) + that.digitAt(position) + carry;
			if(sum >= 10)
			{
				sum -= 10;
				carry = 1;
			}
			else
			{
				carry = 0;
			}
			result.addDigit(sum);
			position++;
			DIGIT_OPERATIONS++;
		}
		if(this.size() > that.size())
		{
			while(position < this.size())
			{
				result.addDigit(this.digitAt(position) + carry);
				carry = 0;
				position++;
				DIGIT_OPERATIONS++;
			}

		}
		else if(that.size() > this.size())
		{
			while(position < that.size())
			{
				result.addDigit(that.digitAt(position) + carry);
				carry = 0;
				position++;
				DIGIT_OPERATIONS++;
			}

		}
		if(carry == 1)
		{
			result.addDigit(1);
		}

		return result;
	}
	
	/*---- Returns the difference of this and a specified HugeInteger -------------*/
	/*---- Assumes both HugeIntegers are positive and this > that -----------------*/
	public HugeInteger subtract(HugeInteger that)
	{
		if(this.compareTo(that) < 0)
			return that.subtract(this);
		HugeInteger result = new HugeInteger( this.size() );
		int borrow = 0;
		int difference;
		int position = 0;
		for( ; position < that.size() ; position++ )
		{
			difference = this.digitAt(position) - that.digitAt(position) - borrow;
			if(difference < 0)
			{
				difference += 10;
				borrow = 1;
			}
			else
			{
				borrow = 0;
			}
			result.addDigit(difference);
			DIGIT_OPERATIONS++;
		}
		for( ; position<this.size() ; position++ )
		{
			difference = this.digitAt(position)- borrow; 
			if(difference < 0)
			{
				difference += 10;
				borrow = 1;
			}
			else
			{
				borrow = 0;
			}
			result.addDigit(difference);
			DIGIT_OPERATIONS++;	
		}	
		result.removeLeadingZeroes();
		return result;
	}
	
	/*---- multiplies this and the specified HugeInteger using "school" algorithm -*/
	public HugeInteger multiply(HugeInteger that)
	{
		// create zero-filled HugeInteger
		int resultSize = this.size() + that.size();
		ArrayList<Integer> list = new ArrayList<Integer>(
												Collections.nCopies(resultSize, 0));
		HugeInteger result = new HugeInteger( list );
		
		int carry = 0;
		int m;
		for(int i=0 ; i<that.size() ; i++)
		{
			for(int j=0 ; j<this.size() ; j++)
			{
				m = this.digitAt(j) * that.digitAt(i);
				m = m + carry + result.digitAt(i+j);
				result.setDigit(i+j, m%10);
				carry = m/10;
				DIGIT_OPERATIONS +=3; // three digit operations: two add, one multiply
			}
			if(carry > 0)
			{
				result.setDigit(i+this.size(), result.digitAt(i+this.size()) + carry);
				carry = 0;
			}
		}
		result.removeLeadingZeroes();
		return result;
	}
	
	/*---- multiplies this and the specified HugeInteger --------------------------*/
	/*---- using Karatsuba Ofman fast algorithm -----------------------------------*/
	public HugeInteger fastMultiply(HugeInteger that)
	{
		//this.removeLeadingZeroes();
		//that.removeLeadingZeroes();
		if( this.size() <= 5 || that.size() <= 5 )
			return this.multiply(that);
			
		// create zero-filled HugeInteger
		int resultSize = this.size() + that.size();
		ArrayList<Integer> list = new ArrayList<Integer>(
												Collections.nCopies(resultSize, 0));
		HugeInteger result = new HugeInteger( list );
		
		int midPoint = ( min(this.size(), that.size()) ) / 2;
		HugeInteger a = this.upperCopy(midPoint);
		HugeInteger b = this.lowerCopy(midPoint);
		HugeInteger c = that.upperCopy(midPoint);
		HugeInteger d = that.lowerCopy(midPoint);
		System.out.println(a +"|"+ b +" * "+ c +"|"+ d);
		HugeInteger low = b.fastMultiply(d);
		System.out.printf("b*d: %42s\n", low.toString());
		HugeInteger high = a.fastMultiply(c);
		System.out.printf("a*c: %42s\n", high.toString());
		HugeInteger mid = a.add(b);
		mid = mid.fastMultiply(c.add(d));
		System.out.printf("a+b: %42s\n", a.add(b).toString());
		System.out.printf("c+d: %42s\n", c.add(d).toString());
		System.out.printf("a+b*c+d: %38s\n", mid);
		mid = mid.subtract(high);
		mid = mid.subtract(low);
		System.out.printf("preshift: %37s\n\n",mid.toString());
		System.out.printf("mid: %42s\n",mid.shift(midPoint).toString());
		System.out.printf("hi : %42s\n", high.shift(2*midPoint).toString());
		System.out.printf("low: %42s\n", low.toString());
		high = high.shift(2*midPoint);
		//System.out.println("\n\n"+mid);
		mid = mid.shift(midPoint);
		//System.out.println("\n\n"+mid);
		result = result.add(low);
		result = result.add(mid);
		result = result.add(high);
		result.removeLeadingZeroes();
		System.out.printf("total: %40s\n\n", result.toString());
		return result;
	}
	
// helper methods

	/*---- returns the number of digits -------------------------------------------*/
	public int size()
	{
		return this.digits.size();
	}
	
	/*---- returns the digit at provided position ---------------------------------*/
	public int digitAt(int position)
	{
		return this.digits.get(position);
	}
	
	/*---- appends a digit in the most siginificant position ----------------------*/
	public void addDigit(int digit)
	{
		this.digits.add(digit);
	}
	
	/*---- replaces the digit at specified position with specified digit ----------*/
	public void setDigit(int position, int digit)
	{
		this.digits.set(position, digit);
	}
	
	/*---- removes zeroes in most significant digits ofthis -----------------------*/
	public void removeLeadingZeroes()
	{
		for( int i=this.size()-1 ; this.digitAt(i) == 0 && i > 0 ; i--)
		{
			this.digits.remove(i);
		}
	}
	
	/*---- returns a copy of the digits of this from provided integer to size()-1 -*/
	public HugeInteger upperCopy(int midPoint)
	{
		HugeInteger result = new HugeInteger(midPoint);
		for(int i = midPoint ; i<this.size() ; i++)
		{
			result.addDigit(this.digitAt(i));
		}
		//result.removeLeadingZeroes();
		return result;
	}
	
	/*---- returns a copy of the digits of this from provided integer to size()-1 -*/
	public HugeInteger lowerCopy(int midPoint)
	{
		HugeInteger result = new HugeInteger(midPoint);
		for(int i = 0; i<midPoint; i++)
		{
			result.addDigit(this.digitAt(i));
		}
		//result.removeLeadingZeroes();
		return result;
	}
	
	/*---- returns a copy of this shifted to the left by the specified amount -----*/
	public HugeInteger shift(int distance)
	{
		HugeInteger result = new HugeInteger(this.size() + distance);
		for( int i=0 ; i < distance ; i++ )
			result.addDigit(0);
		for( int i=0 ; i < this.size() ; i++ )
			result.addDigit(this.digitAt(i));
		return result;
	}
	
	private int min(int a, int b)
	{
		if(a<b){
			return a;
		} else {
			return b;
		}
	}
	
	private int max(int a, int b)
	{
		if(a>b){
			return a;
		} else {
			return b;
		}
	} 
	
	public String toString()
	{
		String result = "";
		for(int i=0; i<this.size(); i++)
		{
			result = this.digitAt(i) + result;
		}
		return result;
	}
	
}