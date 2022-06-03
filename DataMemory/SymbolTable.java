package DataMemory;

import java.util.HashMap;

import ExceptionHandling.SymbolNotFound;

public class SymbolTable {
	
	public static HashMap<String, Integer> _symTab = new HashMap<>();
	public static Integer getSymbolAddr(String sym) throws SymbolNotFound
	{
		if(_symTab.containsKey(sym))
			return _symTab.get(sym);
		else
			throw new ExceptionHandling.SymbolNotFound("The symbol "+sym+" never occurs in the program"); 
	}
	
	public static void addSymbolTableEntry(String sym, int addr)
	{
		if(!_symTab.containsKey(sym))
			_symTab.put(sym, addr);		
	}

}
