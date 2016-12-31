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
		setSize();
	}
	public Cruzador(float x, float y) {
		super(x,y);
		 tipo = TipoArma.Cruzador;
		cellMatrix = CelulaMatrix.instance(this, this.tipo.getNumCels(),1);
		cellMatrix.fill();
		setSize();
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
			g2d.fillRect(i*CELL_SIZE,0,CELL_SIZE-1,CELL_SIZE-1);
			g2d.setColor(Color.BLACK);
			g2d.drawRect(i*CELL_SIZE,0,CELL_SIZE-1,CELL_SIZE-1);
		}
	}
	protected TipoArma getTipo() {
		return tipo;
	}
}
