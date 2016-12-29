package Armas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

//import javax.swing.JFrame;
import javax.swing.JPanel;

import Tabuleiro.ExceptionCellAlreadyHit;

//import Tabuleiro.TabuleiroInvisivel;

public abstract class Arma extends JPanel {


	private static final long serialVersionUID = 1L;

	protected CelulaMatrix cellMatrix;
	protected TipoArma tipo;
	//	private final int qtdMax;
	//	private boolean destroyed; //indica se uma arma foi completamente atingida
	protected int numPartes=0;
	protected int rotate;			//cada acrescimo indica giro de 90º
	protected Point2D.Float vector[];		//vetor c coord das partes da arma
	protected boolean vectHit[];			//indica partes atingidas
	protected int WIDTH_IN_CELL;
	protected int HEIGHT_IN_CELL;
	protected int CELL_SIZE;

	protected Arma() {
		setLocation(0,0);
	}
	
	protected Arma(float x, float y){
		setLocation(x,y);
	}

	public void setLocation(float x, float y) {
		setLocation((int)x,(int)y);
	}
	protected void rotateClockwise(){
		cellMatrix.spinClockwise();
	}protected void rotateCounterClockwise(){
		cellMatrix.spinCounterClockwise();
	}
	public Color getColor(){
		return tipo.getColor();
	}
	public boolean getDestroyed(){
		return cellMatrix.allDestroyed();
	}
	
	protected void setSize(){
		super.setSize(cellMatrix.getWidth()*CELL_SIZE, cellMatrix.getHeight()*CELL_SIZE);
	}
	protected boolean isHitHere(Point2D p){
		int x = (int) ((p.getX()-getX())/CELL_SIZE);
		int y = (int) ((p.getY()-getY())/CELL_SIZE);
		boolean b = false;
		try{
			b = cellMatrix.isHitHere(x,y);
		}
		catch(IndexOutOfBoundsException e){
			e.printStackTrace();
		}
		return b;
	}
	
	protected boolean Atingir(Point pt) throws ExceptionCellAlreadyHit, ExceptionNoWeaponHere{
		int x = (int) ((pt.getX()-getX())/CELL_SIZE);
		int y = (int) ((pt.getY()-getY())/CELL_SIZE);
		return cellMatrix.hitHere(x, y);
	}
	public int getNumPartes(){
		return numPartes;
	}

	public boolean isHere(Point pt){
		int x = (int) ((pt.getX()-getX())/CELL_SIZE);
		int y = (int) ((pt.getY()-getY())/CELL_SIZE);
		boolean b;
		try {
			b = cellMatrix.isHere(x, y);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		return b;
	}
}

