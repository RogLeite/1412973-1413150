package Armas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Submarino extends Arma{
	private final TipoArma tipo = TipoArma.Submarino;
	private Submarino() {
		super();
		setSize(CELL_SIZE, CELL_SIZE);
		cellMatrix = CelulaMatrix.instance(this, 1,1);
		cellMatrix.fill();
	}
	private Submarino(float x, float y){
		super(x,y);
		setSize(CELL_SIZE, CELL_SIZE);
		cellMatrix = CelulaMatrix.instance(this, 1,1);
		cellMatrix.fill();
	}
	public static Submarino instance(){
		return new Submarino();
	}
	public static Submarino instance(Point p){
		float x = p.x;
		float y = p.y;
		return new Submarino(x,y);
	}
	public static Submarino instance(float x, float y){
		return new Submarino(x,y);
	}
	public void paintComponent(Graphics g){
		setSize();
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(this.tipo.getColor());
		g2d.fillRect(getX()*CELL_SIZE, getY()*CELL_SIZE,CELL_SIZE,CELL_SIZE);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(getX()*CELL_SIZE, getY()*CELL_SIZE,CELL_SIZE,CELL_SIZE);
	}


}
