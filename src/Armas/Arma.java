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
	protected static int CELL_SIZE;

	protected Arma() {
		setLocation(0,0);
		setVisible(true);
		setLayout(null);
		setIgnoreRepaint(false);
	}
	
	protected Arma(float x, float y){
		setLocation(x,y);
		setVisible(true);
		setLayout(null);
		setIgnoreRepaint(false);
	}

	public void setLocation(float x, float y) {
		setLocation((int)x,(int)y);
		setVisible(true);
		setLayout(null);
		setIgnoreRepaint(false);
	}
	protected abstract void rotateClockwise();
	protected abstract void rotateCounterClockwise();
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
		convertPoint((Point) p);
		int x = (int) (p.getX());
		int y = (int) (p.getY());
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
		convertPoint(pt);
		int x = (int) (pt.getX());
		int y = (int) (pt.getY());
		return cellMatrix.hitHere(x, y);
	}
	public int getNumPartes(){
		return numPartes;
	}

	public boolean isHere(Point pt){
		convertPoint(pt);
		int x = (int) (pt.getX());
		int y = (int) (pt.getY());
		boolean b;
		try {
			b = cellMatrix.isHere(x, y);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		return b;
	}
	public static void setCellSize(int c){
		CELL_SIZE = c;
	}
	public abstract void paintComponent(Graphics g);
	private void convertPoint(Point p){
		p.setLocation((p.getX()-getX())/CELL_SIZE-1, (p.getY()-getY())/CELL_SIZE-1); 
	}

	public CelulaMatrix getCellMatrix() {
		return cellMatrix;
	}
}

