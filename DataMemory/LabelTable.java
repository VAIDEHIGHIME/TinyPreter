package DataMemory;

import java.util.HashMap;

import ExceptionHandling.SymbolNotFound;
import ExceptionHandling.usedButNotDeclares;

public class LabelTable {
	
	public static HashMap<String, Integer> _lab_tab = new HashMap<>();
	public static int getLabelLocation(String lab) throws SymbolNotFound, usedButNotDeclares{
		if(_lab_tab.containsKey(lab))
			if(_lab_tab.get(lab)==-1)
				throw new ExceptionHandling.usedButNotDeclares("The label"+lab+"is used but never declared"); 
			else
				return _lab_tab.get(lab);
		else
			throw new ExceptionHandling.SymbolNotFound("The label"+lab+"never occurs in the program"); 
		
	}
	
	public static void addLabelTableEntry(String lab, int addr){
		_lab_tab.put(lab, addr);			
	}

}
