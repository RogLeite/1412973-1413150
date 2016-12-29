package Armas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Couracado extends Navio{
	private static TipoArma tipo = TipoArma.Couracado;
	private Couracado() {
		super();
		tipo = TipoArma.Couracado;
		cellMatrix = CelulaMatrix.instance(this, this.tipo.getNumCels(),1);
		cellMatrix.fill();
	}
	private Couracado(float x, float y){
		super(x,y);
		tipo = TipoArma.Couracado;
		cellMatrix = CelulaMatrix.instance(this, this.tipo.getNumCels(),1);
		cellMatrix.fill();
	}
	public static Couracado instance(){
		return new Couracado();
	}
	public static Couracado instance(float x, float y){
		return new Couracado(x,y);
	}
	public static Couracado instance(Point p){
		float x = p.x;
		float y = p.y;
		return new Couracado(x,y);
	}
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		setSize();
		for(int i=0;i<tipo.getNumCels();i++){
			g2d.setColor(this.tipo.getColor());
			g2d.fillRect(getX()*i*CELL_SIZE, getY()*CELL_SIZE,CELL_SIZE,CELL_SIZE);
			g2d.setColor(Color.BLACK);
			g2d.drawRect(getX()*i*CELL_SIZE, getY()*CELL_SIZE,CELL_SIZE,CELL_SIZE);
		}
	
	}
}
