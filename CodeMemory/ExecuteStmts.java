package CodeMemory;

import java.util.ArrayList;

public class ExecuteStmts {
	public static ArrayList<ProgramStatement> PgmStmts;
	public static ArrayList<Integer> IntArray = new ArrayList<Integer>();
	
	public ExecuteStmts(ArrayList<ProgramStatement> pgmStmts){
		this.PgmStmts = pgmStmts;
	}

	public static void Execution(){
		for(int i=0;i< PgmStmts.size();i++){
			ProgramStatement stmt = PgmStmts.get(i);
			String insOp = stmt.get_ins_operator();
			if(insOp.equals("RD")){
				int val =  Integer.parseInt(Main.Interpreter.params[Main.Interpreter.paramsPtr]);
				AddSymTableEntry(stmt.get_operand1(), val);
				Main.Interpreter.paramsPtr++;
			}
			else if(insOp.equals("WR"))
				System.out.println(stmt.get_operand1() + ": "+ IntArray.get(DataMemory.SymbolTable._symTab.get(stmt.get_operand1())));
			else if(insOp.equals("AC"))
				AddSymTableEntry(stmt.get_operand1(), stmt.get_constant());
			else if(insOp.equals("AV"))
				AddSymTableEntry(stmt.get_operand1(), IntArray.get(DataMemory.SymbolTable._symTab.get(stmt.get_operand2())));
			else if(insOp.equals("AE"))
				HandleAssignExpr(stmt);
			else if(insOp.equals("NJ"))
				i = DataMemory.LabelTable._lab_tab.get(stmt.get_operand1()) - 1;
			else if(insOp.equals("CJ"))
				i = HandleCondJmp(stmt, i);
			
		}
	}
	
	
	public static int HandleCondJmp(ProgramStatement stmt, int i){
		int firstOp = IntArray.get(DataMemory.SymbolTable._symTab.get(stmt.get_operand1()));
		int secOp = IntArray.get(DataMemory.SymbolTable._symTab.get(stmt.get_operand2()));
		if(stmt.get_sub_operator() == '<'){
			if(firstOp < secOp)
				return (DataMemory.LabelTable._lab_tab.get(stmt.get_operand3()) -1);
		}
		else{
			if(firstOp != secOp)
				return (DataMemory.LabelTable._lab_tab.get(stmt.get_operand3()) -1);
		}
		return i;
	}
	
	public static void HandleAssignExpr(ProgramStatement stmt){
	//	System.out.println(stmt.get_operand2());
	//	System.out.println(DataMemory.SymbolTable._symTab.get(stmt.get_operand2()));
		int firstOp = IntArray.get(DataMemory.SymbolTable._symTab.get(stmt.get_operand2()));
		int secOp = IntArray.get(DataMemory.SymbolTable._symTab.get(stmt.get_operand3()));
		int res = -1;
		if(stmt.get_sub_operator() == '+')
			res = firstOp + secOp;
		else{
			res = firstOp - secOp;
		}
		AddSymTableEntry(stmt.get_operand1(), res); 
	}
	
	public static void AddSymTableEntry(String key, int value){
		if(DataMemory.SymbolTable._symTab.containsKey(key)){
			int arrayIndex = DataMemory.SymbolTable._symTab.get(key);
			IntArray.set(arrayIndex, value);
		}
		else{
			IntArray.add(value);
			DataMemory.SymbolTable.addSymbolTableEntry(key, IntArray.size()-1);	
		}
	}
}
