package Armas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

public abstract class Navio extends Arma {
	protected void rotateClockwise(){
		cellMatrix.spinClockwise();
		setSize(cellMatrix.getMyHeight()*CELL_SIZE,cellMatrix.getMyWidth()*CELL_SIZE);
	}
	protected void rotateCounterClockwise(){
		cellMatrix.spinCounterClockwise();
		setSize(cellMatrix.getMyHeight()*CELL_SIZE,cellMatrix.getMyWidth()*CELL_SIZE);
	}
	protected Navio() {
		super();
	}
	protected Navio(float x, float y){
		super(x,y);
	}

}
