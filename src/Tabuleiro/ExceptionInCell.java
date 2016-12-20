package Tabuleiro;

public class ExceptionInCell extends Exception{
	private static final long serialVersionUID = 1L;
	Celula cell;
	public ExceptionInCell(){
		super();
	}
	public ExceptionInCell(Celula c){
		super();
		cell = c;
	}
	public ExceptionInCell(String m){
		super(m);
	}
}
