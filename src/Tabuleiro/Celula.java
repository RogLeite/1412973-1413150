package Tabuleiro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

//import javax.swing.JComponent;
import javax.swing.JPanel;

import Armas.ExceptionNoWeaponHere;

public final class Celula extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Tabuleiro tabResp;
	private static float CELL_SIZE;
	private boolean VISIBLE = false;
	private boolean DESTROYED = false;
	private boolean HIT = false;
	private boolean FILLED = false;
	//	private boolean visibilidade = true;

	public Celula(Tabuleiro t, float size,float x, float y){
		tabResp = t;
		CELL_SIZE = size;
		setLocation((int)x,(int)y);
		setSize((int)CELL_SIZE,(int)CELL_SIZE);
		setLayout(null);
	}

	static public void hitCell(Celula c) throws ExceptionCellAlreadyHit{
		if (c.HIT==true){
			throw new ExceptionCellAlreadyHit();
		}
		else{
			c.HIT = true;
			c.repaint();
		}
	}


	static public void fillCell(Celula c) throws ExceptionCellAlreadyFilled{
		if(c.FILLED==true){
			throw new ExceptionCellAlreadyFilled();
		}
		else{
			c.FILLED = true;
			c.repaint();
		}

	}


	public void paintComponent(Graphics g){
		//
		Graphics2D g2d = (Graphics2D) g;
//				System.out.println("Cheguei Celula.paintComponent()");
//		System.out.printf("\tdecideCor() = %s Celula.paintComponent()\n",decideCor().toString());
		g2d.setColor(decideCor());
		g2d.fillRect(0, 0,(int)CELL_SIZE, (int)CELL_SIZE);
		if(imHit()){
			g2d.setColor(Color.BLACK);
			g2d.drawLine(0, 0,(int)CELL_SIZE, (int)CELL_SIZE);
			g2d.drawLine(0, (int)CELL_SIZE,(int)CELL_SIZE, 0);
		}
		g2d.setColor(Color.BLACK);
		g2d.drawRect(0, 0, (int) CELL_SIZE-1, (int) CELL_SIZE-1);



	}
	private boolean imVisivel() {
		Tabuleiro t = ((Tabuleiro)this.getParent());
		return t.isVisivel(this);
	}

	private boolean imHit() {
		Tabuleiro t = ((Tabuleiro)this.getParent());
		return t.isHit(this);
	}
	private boolean imFilled() {
		Tabuleiro t = ((Tabuleiro)this.getParent());
		return t.isFilled(this);
	}

	private boolean imDestroyed() {
		Tabuleiro t = ((Tabuleiro)this.getParent());
		return t.isDestroyed(this);
	}
	private Color myColor() throws IndexOutOfBoundsException, ExceptionNoWeaponHere {
		Tabuleiro t = ((Tabuleiro)this.getParent());
		return t.itsColor(this);
	}
	private Color decideCor(){
		//		System.out.println("Cheguei Celula.decideCor()");
		//		System.out.printf("imFilled %b\n",t.imFilled(this.getCorrectedLocation()));
		if(imVisivel()||imHit()){
			if(imFilled()){
				if(imDestroyed()){
					return Color.RED;
				}
				try {
					return myColor();
				}catch (ExceptionNoWeaponHere e) {
					//Azul Escuro
					System.out.println("ExceptionNoWeaponHere Celula.decideCor");
					return new Color(43, 66, 227);
				}
			}
			else{
				//Azul Escuro
				return new Color(43, 66, 227);
			}
		}
		else{
			//Azul Claro
			return new Color(101,183,235);
		}

	}
	//	public void toggleVisibilidade()
	//	{
	//		//		System.out.println("Cheguei Celula.toggleVisibilidade()");
	//		if(visibilidade){
	//			//			System.out.println("Cheguei visibilidade = false in Celula.toggleVisibilidade()");
	//			visibilidade = false;
	//		}
	//		else{
	//			//			System.out.println("Cheguei visibilidade = true in Celula.toggleVisibilidade()");
	//			visibilidade = true;
	//		}
	//	}
	//	public void setVisibilidade(boolean v){
	////		System.out.printf("\nCheguei Celula.setVisibilidade(%b)",v);
	//		visibilidade = v;
	//		repaint();
	//	}
	//	public boolean getVisibilidade(){
	//		return visibilidade;
	//	}




	private Point getCorrectedLocation() {

		return new Point((int)(getX()+CELL_SIZE),(int)(getY()+CELL_SIZE));
	}

}
