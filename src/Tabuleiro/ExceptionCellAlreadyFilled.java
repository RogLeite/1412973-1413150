package Tabuleiro;

public class ExceptionCellAlreadyFilled extends ExceptionInCell {
	private static final long serialVersionUID = 1L;
	public ExceptionCellAlreadyFilled(){
		super();
	}
	public ExceptionCellAlreadyFilled(Celula c){
		super();
		cell = c;
	}
	public ExceptionCellAlreadyFilled(String m){
		super(m);
	}
}
