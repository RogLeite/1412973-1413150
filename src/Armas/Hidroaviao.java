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
//		setLocation((int)((x/CELL_SIZE)*CELL_SIZE), (int)((y/CELL_SIZE)*CELL_SIZE));
		setLocation((int)x, (int)y);
		switch(rotate){
		case 0:
			setSize(WIDTH_IN_CELL*CELL_SIZE, HEIGHT_IN_CELL*CELL_SIZE);
			vector[0]= new Point2D.Float(0,1*CELL_SIZE);
			vector[1]= new Point2D.Float(1*CELL_SIZE,0);
			vector[2]= new Point2D.Float(2*CELL_SIZE,1*CELL_SIZE);
			break;
		case 1:
			setSize( HEIGHT_IN_CELL*CELL_SIZE, WIDTH_IN_CELL*CELL_SIZE);
			vector[0]= new Point2D.Float(0,0);
			vector[1]= new Point2D.Float(1*CELL_SIZE,1*CELL_SIZE);
			vector[2]= new Point2D.Float(0,2*CELL_SIZE);
			break;
		case 2:
			setSize(WIDTH_IN_CELL*CELL_SIZE, HEIGHT_IN_CELL*CELL_SIZE);
			vector[0]= new Point2D.Float(2*CELL_SIZE,0);
			vector[1]= new Point2D.Float(1*CELL_SIZE,1*CELL_SIZE);
			vector[2]= new Point2D.Float(0,0);
			break;
		case 3:
			setSize( HEIGHT_IN_CELL*CELL_SIZE, WIDTH_IN_CELL*CELL_SIZE);
			vector[0]= new Point2D.Float(1*CELL_SIZE,2*CELL_SIZE);
			vector[1]= new Point2D.Float(1*CELL_SIZE,0);
			vector[2]= new Point2D.Float(0,1*CELL_SIZE);
			break;
		}
	}
}
