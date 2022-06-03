package CodeMemory;

public class ProgramStatement {

	private String _ins_operator;
	private char _sub_operator;
	private String _operand1;
	private String _operand2;
	private String _operand3;
	private int _constant;
	private String _target_label;
	
	ProgramStatement(){
		_ins_operator = null;
		_sub_operator = ' ';
		_operand1 = null;
		_operand2 = null;
		_operand3 = null;
		_constant = Integer.MAX_VALUE;
	}
	
	
	public String get_ins_operator() {
		return _ins_operator;
	}
	public void set_ins_operator(String _ins_operator) {
		this._ins_operator = _ins_operator;
	}
	public char get_sub_operator() {
		return _sub_operator;
	}
	public void set_sub_operator(char _sub_operator) {
		this._sub_operator = _sub_operator;
	}
	public String get_operand1() {
		return _operand1;
	}
	public void set_operand1(String _operand1) {
		this._operand1 = _operand1;
	}
	public String get_operand2() {
		return _operand2;
	}
	public void set_operand2(String _operand2) {
		this._operand2 = _operand2;
	}
	public String get_operand3() {
		return _operand3;
	}
	public void set_operand3(String _operand3) {
		this._operand3 = _operand3;
	}
	public int get_constant() {
		return _constant;
	}
	public void set_constant(int _constant) {
		this._constant = _constant;
	}
	public String get_target_label() {
		return _target_label;
	}
	public void set_target_label(String _target_label) {
		this._target_label = _target_label;
	}






}
