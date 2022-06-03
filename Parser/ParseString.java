package Parser;


public class ParseString {

	char curr_char;
	
	public ParseString(char ch){
		this.curr_char = ch;
	}
	
	public Token getToken()
	{
		
		if(java.lang.Character.isDigit(curr_char)){
			int curr_no=makeNumber();
			return new Parser.Token("INTEGER", curr_no);
		}
		if(curr_char == '='){
			CodeMemory.Program.index++; 
			return new Parser.Token("EQUAL", curr_char);
		}
		if(curr_char == '#'){
			CodeMemory.Program.index++; 
			return new Parser.Token("NE", curr_char);// NE: Not Equal
		}
		if(curr_char == '?'){
			CodeMemory.Program.index++; 
			return new Parser.Token("COND", curr_char);
		}
		if(curr_char == '+'){
			CodeMemory.Program.index++; 
			return new Parser.Token("PLUS", curr_char);
		}
		if(curr_char == '-'){
			CodeMemory.Program.index++;
			return new Parser.Token("MINUS", curr_char);
		}
		if(curr_char == '<'){
			CodeMemory.Program.index++; 
			return new Parser.Token("LT", curr_char);
		}
		if(java.lang.Character.isLowerCase(curr_char)){
			String curr_label=makelabel();
			return new Parser.Token("LABEL",curr_label);
		}
		if(java.lang.Character.isUpperCase(curr_char)){
			String curr_label=makeIdentifier();
			//System.out.println(curr_label);
			if(curr_label.equals("READ"))
			{
				//System.out.println("Read label detected");
				return new Token("READ",curr_label);
			}
			else if(curr_label.equals("WRITE"))
			{
				return new Token("WRITE", curr_label);
			}
			else if(curr_label.equals("JMP")){
				return new Token("JMP", curr_label);
			}
			else
			{
				//System.out.println("Identifier detected");
				return new Token("IDENT",curr_label);
			}

		}


		return null;
	}

	private int makeNumber()
	{
		String completeNo = "";
		while(CodeMemory.Program.index< CodeMemory.Program.Stmt.length() && java.lang.Character.isDigit(CodeMemory.Program.Stmt.charAt(CodeMemory.Program.index)))
		{
				completeNo+=CodeMemory.Program.Stmt.charAt(CodeMemory.Program.index);
				CodeMemory.Program.index++;
		}
		return Integer.parseInt(completeNo);

	}
	private String makeIdentifier() {
		String identifier="";
		while(CodeMemory.Program.index< CodeMemory.Program.Stmt.length() && java.lang.Character.isUpperCase(CodeMemory.Program.Stmt.charAt(CodeMemory.Program.index)))
		{
			identifier=identifier+ CodeMemory.Program.Stmt.charAt(CodeMemory.Program.index);
			CodeMemory.Program.index++;
		}
		return identifier;
	}

	private String makelabel() {
		String label="";
		while(CodeMemory.Program.index< CodeMemory.Program.Stmt.length() && java.lang.Character.isLowerCase(CodeMemory.Program.Stmt.charAt(CodeMemory.Program.index)))
		{
			label+=CodeMemory.Program.Stmt.charAt(CodeMemory.Program.index);
			CodeMemory.Program.index++;
		}
		return label;
	}
}
