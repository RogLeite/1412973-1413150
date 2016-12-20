package Armas;

import java.awt.Color;
import java.awt.geom.Point2D;

public class Hidroaviao extends Arma {

	private static final long serialVersionUID = 1L;

	public Hidroaviao(int size, int cellSize){
	//	super(3,Color.YELLOW,5,size);
		super(3,Color.YELLOW,size, cellSize);
		HEIGHT_IN_CELL = 2;
		WIDTH_IN_CELL = (int)size;
	}

	public void setLocation(float x, float y) {
		vector[0]= new Point2D.Float(x,y);
		vector[1]= new Point2D.Float(x+1,y+1);
		switch(rotate){
		case 0:
			vector[2]= new Point2D.Float(x+2,y);
			setSize(WIDTH_IN_CELL*CELL_SIZE, HEIGHT_IN_CELL*CELL_SIZE);
			break;
		case 1:
			vector[2]= new Point2D.Float(x+1,y-1);
			setSize( HEIGHT_IN_CELL*CELL_SIZE, WIDTH_IN_CELL*CELL_SIZE);
			break;
		case 2:
			vector[2]= new Point2D.Float(x-1,y+1);
			setSize(WIDTH_IN_CELL*CELL_SIZE, HEIGHT_IN_CELL*CELL_SIZE);
			break;
		case 3:
			vector[2]= new Point2D.Float(x,y+2);
			setSize( HEIGHT_IN_CELL*CELL_SIZE, WIDTH_IN_CELL*CELL_SIZE);
			break;
		}
	}
}
