package PosArmas;

public class ExceptionNotInstanced extends Exception {
	private static final long serialVersionUID = 1L;
	public ExceptionNotInstanced(){
		super();
	}
	public ExceptionNotInstanced(String m){
		super(m);
	}
}
