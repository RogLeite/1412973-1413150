package Tabuleiro;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

/*
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
 */
import javax.swing.JPanel;

import Armas.Arma;
import Armas.ArmaListener;
import Armas.CelulaMatrix;
import Armas.ConjArmas;
import Armas.ExceptionNoWeaponSelected;
import PosArmas.ExceptionPlacingNotAllowed;
import TopoNivel.MyMouseListener;

public class TabuleiroInvisivel extends JPanel{

	private static final long serialVersionUID = 1L;
	private static final String BASE_ACTION_STRING= "INV";
	private static final String TAKE_ACTION_STRING = "INV_TAKE_ACTION";
	private ConjArmas arrayArmas = ArmaListener.getEmptyArray();
	private CelulaMatrix cellMatrix;
	private final int BOARD_SIZE;
	//	private final int SIDE_TAB = 16;
	//	final float CELL_SIZE;
	public TabuleiroInvisivel(int boardsize){
		BOARD_SIZE = boardsize;
		setBounds(0,0, getBoardSize(), getBoardSize());
		//		addMouseListener(new MyMouseListener(getTakeActionString()));
		cellMatrix = CelulaMatrix.instance(getBoardSize(), getBoardSize());
		setEnabled(true);
		setLayout(null);
		setIgnoreRepaint(false);
	}
	static String getTakeActionString() {
		return TAKE_ACTION_STRING;
	}
	public static TabuleiroInvisivel newInstance(int SIDE_TAB) {
		return new TabuleiroInvisivel(SIDE_TAB);
	}
	public static String getBaseActionString(){
		return BASE_ACTION_STRING;
	}
	public ConjArmas getArmasArray() {
		return arrayArmas;
	}
	public void receiveArma(Point p) throws ExceptionPlacingNotAllowed, ExceptionNoWeaponSelected{
		
		Arma a = ArmaListener.receiveArma();
		a.getCellMatrix().checkSpaceIn(cellMatrix,pointToMatrixPoint(p).x,pointToMatrixPoint(p).y);
		a.getCellMatrix().pasteIn(cellMatrix,pointToMatrixPoint(p).x, pointToMatrixPoint(p).y);
	}
	private Point pointToMatrixPoint(Point p) {
		float cellsize = ((TabuleiroListener)getParent()).getCellSize();
		return new Point((int) ((int) (p.x/cellsize)*cellsize),(int) ((int) (p.y/cellsize)*cellsize));
	}
	private int getBoardSize(){
		return BOARD_SIZE;
	}
}

