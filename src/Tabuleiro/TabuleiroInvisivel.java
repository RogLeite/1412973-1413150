package Tabuleiro;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

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
import Armas.ExceptionNoWeaponHere;
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
		ArmaListener.confirmedReceive();

	}
	private Point pointToMatrixPoint(Point p) {
		float cellsize = ((TabuleiroListener)getParent()).getCellSize();
		Point np =  new Point((int) ((int) (p.x/cellsize)),(int) ((int) (p.y/cellsize)));
//		System.out.printf("\tnp = %s TabuleiroInvisivel.pointToMatrixPoint\n", np.toString());
		return np;
	}
	private int getBoardSize(){
		return BOARD_SIZE;
	}
	public void setVisibilidade(boolean v) {
		cellMatrix.setVisibilidade(v);
	}
	public boolean isVisivelHere(Point p) {
		return cellMatrix.isVisivelHere(pointToMatrixPoint(p).x,pointToMatrixPoint(p).y);
	}
	public boolean isHitHere(Point p) {
		return cellMatrix.isHitHere(pointToMatrixPoint(p).x,pointToMatrixPoint(p).y);
	}
	public boolean isFilledHere(Point p) {
		return cellMatrix.isFilledHere(pointToMatrixPoint(p).x,pointToMatrixPoint(p).y);
	}
	public boolean isDestroyedHere(Point p) {
		return cellMatrix.isDestroyedHere(pointToMatrixPoint(p).x,pointToMatrixPoint(p).y);
	}
	public Color isColorHere(Point p) throws IndexOutOfBoundsException, ExceptionNoWeaponHere {
		return cellMatrix.isColorHere(pointToMatrixPoint(p).x,pointToMatrixPoint(p).y);
	}
	public void hitHere(Point p) throws ExceptionCellAlreadyHit, ExceptionNoWeaponHere {
		if(isHitHere(p)){
			throw new ExceptionCellAlreadyHit();
		}
		cellMatrix.hitHere(pointToMatrixPoint(p).x,pointToMatrixPoint(p).y);
	}
	public boolean isAllDestroyed() {
		return cellMatrix.allDestroyed();
	}
}

