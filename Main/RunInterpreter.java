package Main;

import ExceptionHandling.ParsingException;

public class RunInterpreter {
	public static void main(String[] args) throws ParsingException {
		if(args.length < 1)
		{
			System.out.println("Please give the path of the TINY program to interpret");
		}
		else
		{
			String ext=Utilities.FileOps.getExtensionByStringHandling(args[0]);
			if(ext !=null && ext.equalsIgnoreCase("tiny") )
			{
				new Interpreter().runInterpretor(args);
			}
			else
			{
				System.out.println("Please make sure that you input file has a proper extension (.tiny/.TINY)");
			}		
		}
		return;
	}
}
