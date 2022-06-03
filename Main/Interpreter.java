/**
 * 
 */
package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import com.sun.xml.internal.bind.CycleRecoverable;

import ExceptionHandling.IOException;
import ExceptionHandling.ParsingException;
import Parser.Token;

public class Interpreter {
	public  String fileName;
	public static int paramsPtr;
	public static String params[];

	public Interpreter()
	{
		fileName=null;
		paramsPtr=1;
		params=null;
	}

	public  void runInterpretor(String[] args) throws ParsingException
	{
		System.out.println("Hello Welcome to the VSTinypreter");
		params=args;
		fileName=args[0];
		Scanner s = null;
		try {
			s = new Scanner(new File(args[0]));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("The file mentioned by the user is not found! Please try again.");
			return;
		}

		while (s.hasNextLine()) { 
			String str = s.nextLine();
			CodeMemory.Program pgm = new CodeMemory.Program(str);
			pgm.GetStmts();
		} 
		
		CodeMemory.ExecuteStmts exstmt = new CodeMemory.ExecuteStmts(CodeMemory.Program.PgmStmts);
		exstmt.Execution();
		System.out.println("******** END OF PROGRAM **********");
	}

}


