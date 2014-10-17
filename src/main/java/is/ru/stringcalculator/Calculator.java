package is.ru.stringcalculator;

import java.util.*;

public class Calculator {

	public static int add(String text) throws NegativeNumberException
	{
		String delimiter = ",";

		if (text.length() > 5 && text.substring(0, 2).equals("//")) {
			delimiter = text.substring(2, 3);
			text = text.substring(4, text.length());
		}

		text = removeNewLines(text);

		if(text.equals("")){
			return 0;
		}
		else if(text.contains(delimiter)){
			String[] numbers = splitNumbers(text, delimiter);
			checkNeg(numbers);
			
			return sum(numbers);
		}
		else
			return toInt(text);
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers, String delimiter){
	    return numbers.split(delimiter);
	}
      
    private static int sum(String[] numbers){
 	    int total = 0;
        for(String number : numbers){
        	if(toInt(number) > 1000) continue;
		    total += toInt(number);
		}
		return total;
    }

    private static String removeNewLines(String text){
    	return text.replace("\n", ",");
    }

    private static void checkNeg(String[] numbers) throws NegativeNumberException
    {
    	String message = "Negatives not allowed: ";
    	boolean negTrue = false;
		for(int i = 0; i < numbers.length; i++)
		{
			if(toInt(numbers[i]) < 0)
			{
			if (!negTrue) message += numbers[i];
			else message += "," + numbers[i];
			negTrue = true;
			}
		}
		if (negTrue) throw new NegativeNumberException(message);
    }



    public static class  NegativeNumberException extends Exception
	{


		public NegativeNumberException(String message)
		{
	 		super(message);
		}
 	}

}