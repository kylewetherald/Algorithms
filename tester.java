public class tester
{
	public static void main( String[] args )
	{
		HugeInteger one = new HugeInteger("456763434562");
		//System.out.println("1: " + one + " digits: " + one.size());
		HugeInteger two = new HugeInteger("012334598704");
		//System.out.println("2: " + two + " digits: " + two.size());
		if(one.compareTo(two) > 0)
			System.out.println(one + " > " + two);
		if(one.compareTo(two) < 0)
			System.out.println(one + " < " + two);
		System.out.println(one + " + " + two + " = " + one.add(two) );
		System.out.println(one + " - " + two + " = " + one.subtract(two) );
		one.DIGIT_OPERATIONS = 0;
		System.out.println(one + " * " + two + " = " + one.multiply(two) );
		System.out.println("Digit_Operations = " + one.DIGIT_OPERATIONS );
				System.out.println(two + " * " + one + " = " + two.multiply(one) );
		one.DIGIT_OPERATIONS = 0;
		System.out.println(one + " * " + two + " = " + one.fastMultiply(two) );
		System.out.println("Digit_Operations = " + one.DIGIT_OPERATIONS );
		System.out.println(two + " * " + one + " = " + two.fastMultiply(one) );
		System.out.println("Digit_Operations = " + one.DIGIT_OPERATIONS );
	}
}