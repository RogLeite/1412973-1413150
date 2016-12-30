package Armas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

import Tabuleiro.ExceptionCellAlreadyFilled;

public class Hidroaviao extends Arma {
	private final TipoArma tipo = TipoArma.Hidroaviao;
	public Hidroaviao(){
		super();
		cellMatrix = CelulaMatrix.instance(this,3,2);
		try {
			cellMatrix.fill(0,1);
			cellMatrix.fill(1,0);
			cellMatrix.fill(2,1);
		} catch (ExceptionCellAlreadyFilled e) {
			e.printStackTrace();
		}

	}
	public Hidroaviao(float x, float y){
		super(x,y);
		cellMatrix = CelulaMatrix.instance(this,3,2);
		try {
			cellMatrix.fill(0,1);
			cellMatrix.fill(1,0);
			cellMatrix.fill(2,1);
		} catch (ExceptionCellAlreadyFilled e) {
			e.printStackTrace();
		}

	}
	public static Hidroaviao instance() {
		return new Hidroaviao();
	}
	public static Hidroaviao instance(Point p){
		float x = p.x;
		float y = p.y;
		return new Hidroaviao(x,y);
	}
	public static Hidroaviao instance(float x, float y){
		return new Hidroaviao(x,y);
	}
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		setSize();
		for(int i=0;i<cellMatrix.getWidth();i++){
			for(int j=0;j<cellMatrix.getHeight();j++){
				if(cellMatrix.isHere(i,j)){
					g2d.setColor(this.tipo.getColor());
					g2d.fillRect(i*CELL_SIZE,j*CELL_SIZE,CELL_SIZE,CELL_SIZE);
					g2d.setColor(Color.BLACK);
					g2d.drawRect(i*CELL_SIZE,j*CELL_SIZE,CELL_SIZE,CELL_SIZE);
				}
			}
		}
	}
	protected void rotateClockwise() {
		cellMatrix.spinClockwise();
		setSize(cellMatrix.getHeight()*CELL_SIZE,cellMatrix.getWidth()*CELL_SIZE);
	}
	protected void rotateCounterClockwise() {
		cellMatrix.spinCounterClockwise();
		setSize(cellMatrix.getHeight()*CELL_SIZE,cellMatrix.getWidth()*CELL_SIZE);
	}
	
}
