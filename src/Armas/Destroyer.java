package Armas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Destroyer extends Navio{
	private TipoArma tipo = TipoArma.Destroyer;
	private Destroyer() {
		super();
		tipo = TipoArma.Destroyer;
		cellMatrix = CelulaMatrix.instance(this, this.tipo.getNumCels(),1);
		cellMatrix.fill();
	}
	private Destroyer(float x, float y){
		super(x,y);
		tipo = TipoArma.Destroyer;
		cellMatrix = CelulaMatrix.instance(this, this.tipo.getNumCels(),1);
		cellMatrix.fill();
	}
	public static Destroyer instance(){
		return new Destroyer();
	}
	public static Destroyer instance(Point p){
		float x = p.x;
		float y = p.y;
		return new Destroyer(x,y);
	}
	public static Destroyer instance(float x, float y){
		return new Destroyer(x,y);
	}
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		setSize();
		for(int i=0;i<tipo.getNumCels();i++){
			g2d.setColor(this.tipo.getColor());
			g2d.fillRect(i*CELL_SIZE,0,CELL_SIZE,CELL_SIZE);
			g2d.setColor(Color.BLACK);
			g2d.drawRect(i*CELL_SIZE,0,CELL_SIZE-1,CELL_SIZE-1);
		}
	}
}


