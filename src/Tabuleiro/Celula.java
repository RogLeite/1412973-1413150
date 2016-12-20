package Tabuleiro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

//import javax.swing.JComponent;
import javax.swing.JPanel;

public final class Celula extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Tabuleiro tabResp;
	private static float CELL_SIZE;
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
		Tabuleiro t = ((Tabuleiro)this.getParent());
//		System.out.println("Cheguei Celula.paintComponent()");
		
		if (TabuleiroListener.getVisibilidade(t)||TabuleiroListener.imHit(t,this.getLocation())) {
//			System.out.println("Cheguei visibilidade||HIT Celula.paintComponent()");
			g2d.setColor(decideCor(t));
			g2d.fillRect(0, 0, (int) CELL_SIZE, (int) CELL_SIZE);
			if(t.imHit(this.getLocation())){
				g2d.setColor(Color.BLACK);
				g2d.drawLine(0, 0,(int)CELL_SIZE, (int)CELL_SIZE);
				g2d.drawLine(0, (int)CELL_SIZE,(int)CELL_SIZE, 0);
			}
		}
		else{
//			System.out.println("Cheguei Azul Claro Celula.paintComponent()");
			g2d.setColor(new Color(101,183,235));
			g2d.fillRect(0, 0, (int) CELL_SIZE, (int) CELL_SIZE);
		}
		g2d.setColor(Color.BLACK);
		g2d.drawRect(0, 0, (int) CELL_SIZE-1, (int) CELL_SIZE-1);



	}
	private Color decideCor(Tabuleiro t){
//		System.out.println("Cheguei Celula.decideCor()");
//		System.out.printf("imFilled %b\n",t.imFilled(this.getLocation()));
		if(t.imFilled(this.getLocation())){//ve se tem arma
			if(t.imDestroyed(this.getLocation())){//SÓ MUDARÁ A CÔR SE ESTIVER DETONADA
				return new Color(224,41,20);
			}
			return t.myColor(this.getLocation());/*new Color(19,186,4);// será a cor da arma
*/		}
		else{
//			System.out.println("Cheguei Azul Escuro Celula.decideCor()");
			return new Color(43, 66, 227);
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

}
