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
		setSize(cellMatrix.getMyWidth()*CELL_SIZE, cellMatrix.getMyHeight()*CELL_SIZE);

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
		setSize(cellMatrix.getMyWidth()*CELL_SIZE, cellMatrix.getMyHeight()*CELL_SIZE);


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
//		setSize();
		g2d.clearRect(0, 0, getWidth(), getHeight());
		g2d.setBackground(Color.WHITE);
		g2d.setColor(this.tipo.getColor());
		g2d.fillRect(0,CELL_SIZE,CELL_SIZE,CELL_SIZE);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(0,CELL_SIZE,CELL_SIZE-1,CELL_SIZE-1);
		g2d.setColor(this.tipo.getColor());

		g2d.fillRect(CELL_SIZE,0,CELL_SIZE,CELL_SIZE);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(CELL_SIZE,0,CELL_SIZE-1,CELL_SIZE-1);
		g2d.setColor(this.tipo.getColor());

		g2d.fillRect(2*CELL_SIZE,CELL_SIZE,CELL_SIZE,CELL_SIZE);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(2*CELL_SIZE,CELL_SIZE,CELL_SIZE-1,CELL_SIZE-1);
	}
	protected void rotateClockwise() {
		cellMatrix.spinClockwise();
		//		setSize(cellMatrix.getMyHeight()*CELL_SIZE,cellMatrix.getMyWidth()*CELL_SIZE);
	}
	protected void rotateCounterClockwise() {
		cellMatrix.spinCounterClockwise();
		//		setSize(cellMatrix.getMyHeight()*CELL_SIZE,cellMatrix.getMyWidth()*CELL_SIZE);
	}
	protected TipoArma getTipo() {
		return tipo;
	}

}
