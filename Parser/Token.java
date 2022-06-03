package Parser;

public class Token {
	
	private String type;
	private Object value;
	
	public String getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}

	public Token(String type, Object value)
	{
		this.type=type;
		this.value=value;
	}
	
	public void printToken()
	{
		System.out.println("Token{"+this.type+","+this.value.toString()+"}");
	}
	
}
