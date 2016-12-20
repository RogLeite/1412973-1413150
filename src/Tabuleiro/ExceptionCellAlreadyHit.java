package Tabuleiro;

public class ExceptionCellAlreadyHit extends ExceptionInCell {
	private static final long serialVersionUID = 1L;
	public ExceptionCellAlreadyHit(){
		super();
	}
	public ExceptionCellAlreadyHit(Celula c){
		super();
		cell = c;
	}
	public ExceptionCellAlreadyHit(String m) {
		super(m);
	}
}
