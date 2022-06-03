package CodeMemory;

import java.util.ArrayList;

import ExceptionHandling.ParsingException;
import Parser.Token;

public class Program {
	
	public static ArrayList<ProgramStatement> PgmStmts = new ArrayList<ProgramStatement>();
	boolean PrintFlag;
	static int _pgm_cnt = 0; 
	public static int index;
	public static String Stmt;
	private boolean label = false;
	public Program(String str) {
		index = 0;
		Stmt=str;
		PrintFlag = false;
	}

	public void GetStmts() throws ParsingException{
		while(index < Stmt.length()-1){
			label = false;
			Token curr_token = get_curr_token();
			ProgramStatement pgmStmt = new ProgramStatement();
			String curr_token_type = curr_token.getType();
			if(curr_token_type.equals("IDENT")){
				String ident = (String) curr_token.getValue();
				pgmStmt.set_operand1(ident);
				HandleAssignToken(pgmStmt);	
			}
			else if(curr_token_type.equals("READ")){
				pgmStmt.set_ins_operator("RD"); 
				HandleRdWrToken(pgmStmt);
			}
			else if(curr_token_type.equals("WRITE")){
				pgmStmt.set_ins_operator("WR");
				HandleRdWrToken(pgmStmt);
			}
			else if(curr_token_type.equals("JMP")){
				pgmStmt.set_ins_operator("NJ");
				HandleNCondJmp(pgmStmt);
			}
			else if(curr_token_type.equals("LABEL")){
				index ++;
				DataMemory.LabelTable.addLabelTableEntry((String) curr_token.getValue(), _pgm_cnt);
				label = true;
			}
			else
				throw new ExceptionHandling.ParsingException(" Grammar Incorrect");
			if(!label){
				PgmStmts.add(pgmStmt);
				_pgm_cnt ++;
				if(PrintFlag){
					System.out.println("PRGM STMT: ");
					System.out.println(pgmStmt.get_ins_operator() + " "+pgmStmt.get_sub_operator()  + " "+pgmStmt.get_operand1() +" "+pgmStmt.get_constant()+ " "+ pgmStmt.get_operand2() + " "+pgmStmt.get_operand3() );
				}
			}
		}
	}
	
	public static void HandleNCondJmp(ProgramStatement pgmStmt) throws ParsingException{
		Token curr_token = get_curr_token();
		if(curr_token.getType() == "LABEL")
			pgmStmt.set_operand1((String)curr_token.getValue());
		else
			throw new ExceptionHandling.ParsingException(" Grammar Incorrect");
	}
	
	public static void HandleRdWrToken(ProgramStatement pgmStmt) throws ParsingException{
		Token curr_token = get_curr_token();
		if(curr_token.getType() == "IDENT"){
			pgmStmt.set_operand1((String)curr_token.getValue());
		}
		else
			throw new ExceptionHandling.ParsingException("Grammar Incorrect");
	}
	
	public static Token get_curr_token(){
		while(Stmt.charAt(index) == ' '){
			index++;
		}
		char ch = Stmt.charAt(index);
		Parser.ParseString parser = new Parser.ParseString(ch);
		Token curr_token = parser.getToken();
		return curr_token;
	}
	
	
	
	public static void HandleAssignment(ProgramStatement pgmStmt) throws ParsingException{
		Token curr_token = get_curr_token();
		if(curr_token.getType() == "INTEGER"){
			int val = (int) curr_token.getValue();
			pgmStmt.set_constant(val);
			pgmStmt.set_ins_operator("AC"); //AC: Assign constant
		}
		else if(curr_token.getType() == "IDENT"){
			pgmStmt.set_operand2((String) curr_token.getValue());
			if(index < Stmt.length()-1){
				curr_token = get_curr_token();
				if(curr_token.getType() == "PLUS" || curr_token.getType() == "MINUS")
				   pgmStmt.set_sub_operator((char)curr_token.getValue());
				else
					throw new ExceptionHandling.ParsingException(" Grammar Incorrect");
				curr_token = get_curr_token();
				if(curr_token.getType() == "IDENT")
					pgmStmt.set_operand3((String)curr_token.getValue());
				else
					throw new ExceptionHandling.ParsingException(" Grammar Incorrect");
				pgmStmt.set_ins_operator("AE");
			}
			else{
				pgmStmt.set_ins_operator("AV");
			}
		}
		else
			throw new ExceptionHandling.ParsingException(" Grammar Incorrect ");		
	}
	
	
	public static void HandleCondJmp(ProgramStatement pgmStmt) throws ParsingException{
		Token curr_token = get_curr_token();
		if(curr_token.getType() == "IDENT"){
			pgmStmt.set_operand2((String)curr_token.getValue());
			curr_token = get_curr_token();
			if(curr_token.getType()== "COND"){
				curr_token = get_curr_token();
				if(curr_token.getType() == "LABEL")
					pgmStmt.set_operand3((String) curr_token.getValue());
				else
					throw new ExceptionHandling.ParsingException(" Grammar Incorrect ");
			}
			else
				throw new ExceptionHandling.ParsingException(" Grammar Incorrect ");
		}
		else
			throw new ExceptionHandling.ParsingException(" Grammar Incorrect ");
		pgmStmt.set_ins_operator("CJ");
	}
	
	public static void HandleAssignToken(ProgramStatement pgmStmt) throws ParsingException{
		Token curr_token = get_curr_token();
		if(curr_token.getType() == "EQUAL"){
			HandleAssignment(pgmStmt);		
		}
		else if(curr_token.getType() == "LT" || curr_token.getType() == "NE"){
			pgmStmt.set_sub_operator((char) curr_token.getValue());
			HandleCondJmp(pgmStmt);
		}
		else{
			throw new ExceptionHandling.ParsingException(" Grammar Incorrect ");
		}
	}

}
