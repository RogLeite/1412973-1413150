package Armas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Navio extends Arma {

	private static final long serialVersionUID = 1L;
	public Navio(TipoArma nome, int size, int cellSize){
		//	super(nome.getNumCels(),nome.getColor(), nome.getQtdMax(),size);
		super(nome.getNumCels(),nome.getColor(),size, cellSize);
		HEIGHT_IN_CELL = 1;
		WIDTH_IN_CELL = (int)size;
	}

	public void setLocation(float x, float y) {
		setLocation((int)x, (int)y);
		if (rotate==0|| rotate ==3){	//horizontal
			setSize(WIDTH_IN_CELL*CELL_SIZE,numPartes*HEIGHT_IN_CELL*CELL_SIZE);
			for(int i=0;i<numPartes;i++){
				vector[i]=new Point2D.Float(i*CELL_SIZE,0);
			//				vector[i]=new Point2D.Float((x+i)*CELL_SIZE,y);
			}
		}
		else{
			setSize(numPartes*HEIGHT_IN_CELL*CELL_SIZE, WIDTH_IN_CELL*CELL_SIZE);
			for(int i=0;i<numPartes;i++)	//vertical
				vector[i]=new Point2D.Float(0,i*CELL_SIZE);
			//	vector[i]=new Point2D.Float(x,(y+i)*CELL_SIZE);
		}

	}
	//	public void paintComponent(Graphics g){
	//		Graphics2D g2d = (Graphics2D)g;
	//		setLocation(getX(), getY());
	//		for(int i=0;i<numPartes;i++){	//vertical
	//			int x = (int)(vector[i].getX());
	//			int y = (int)(vector[i].getY());
	//			g2d.setColor(getColor());
	//			g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE);
	//			g2d.setColor(Color.BLACK);
	//			g2d.drawRect(x, y, CELL_SIZE-1, CELL_SIZE-1);
	//		}
	//	}

}
