package is.ru.stringcalculator;

import java.util.*;
import java.util.regex.*;


public class Calculator {

	public static int add(String text) throws NegativeNumberException
	{
		String delimiter =  "," ;
		
		

		if (text.startsWith("//")) 
		{
			int numberStartIndex = 0;
			if(!(text.contains("[") && text.contains("]")))
			{
				delimiter = text.substring(2, 3);
				numberStartIndex = 4;
				text = text.substring(numberStartIndex, text.length());
			}
			else
			{
				String [] numbers = splitWithMultiDelims(text);
				checkNeg(numbers);
				return sum(numbers);	
			}
			
		}
		
		

		text = removeNewLines(text);

		if(text.equals(""))
		{
			return 0;
		}
		else if(text.contains(delimiter))
		{
			String[] numbers = splitNumbers(text, delimiter);
			checkNeg(numbers);
			
			return sum(numbers);
		}
		else
			return toInt(text);
	}

	private static String[] splitWithMultiDelims(String text)
	{
		List<String> delims = new ArrayList<String>();
		
		int numberStartIndex = 0;
		Pattern p = Pattern.compile("\\[(.*?)\\]");
		Matcher m = p.matcher(text);
		while (m.find()) 
		{
			delims.add( m.group(1) );
			numberStartIndex = m.end() + 1;
		}

		text = text.substring(numberStartIndex, text.length());
		String[] numbers = {text};

		for(String d : delims)
		{
			List<String> tmpNumbers = new ArrayList<String>();
			for(String nums : numbers)
			{

				String[] tmp = nums.split(Pattern.quote(d));
				for(String n : tmp)
				{
					tmpNumbers.add(n);
				}
			}
			numbers = tmpNumbers.toArray(new String[tmpNumbers.size()]);
		}
			

		return numbers;	
	}


	private static int toInt(String number)
	{
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