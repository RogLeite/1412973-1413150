package Armas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Cruzador extends Navio{
	private TipoArma tipo = TipoArma.Cruzador;
	public Cruzador(){
		super();
		 tipo = TipoArma.Cruzador;
		cellMatrix = CelulaMatrix.instance(this, this.tipo.getNumCels(),1);
		cellMatrix.fill();
	}
	public Cruzador(float x, float y) {
		super(x,y);
		 tipo = TipoArma.Cruzador;
		cellMatrix = CelulaMatrix.instance(this, this.tipo.getNumCels(),1);
		cellMatrix.fill();
	}
	public static Cruzador instance() {
		return new Cruzador();
	}
	public static Cruzador instance(Point p){
		float x = p.x;
		float y = p.y;
		return new Cruzador(x,y);
	}
	public static Cruzador instance(float x, float y){
		return new Cruzador(x,y);
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
