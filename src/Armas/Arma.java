package Armas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

//import javax.swing.JFrame;
import javax.swing.JPanel;

//import Tabuleiro.TabuleiroInvisivel;

public abstract class Arma extends JPanel {

	private static final long serialVersionUID = 1L;
	//	private final int qtdMax;
	//	private boolean destroyed; //indica se uma arma foi completamente atingida
	protected int numPartes=0;
	protected int rotate=0;			//cada acrescimo indica giro de 90º
	protected Point2D.Float vector[];		//vetor c coord das partes da arma
	protected boolean vectHit[];			//indica partes atingidas
	private final Color MAIN_COLOR;			//indica cor da arma
	private final Color DEFAUT_COLOR = Color.BLUE;
	private int SIZE;
	protected int WIDTH_IN_CELL;
	protected int HEIGHT_IN_CELL;
	protected int CELL_SIZE;

	public Arma(){
		MAIN_COLOR = DEFAUT_COLOR;
		SIZE=1;
	}

	public Arma(int size){
		SIZE=size;
		MAIN_COLOR = DEFAUT_COLOR;
	}

	public Arma(int partes, Color c,int size, int cellSize){
		numPartes=partes;
		MAIN_COLOR = c;
		SIZE=size;
		CELL_SIZE = cellSize;
		vector=new Point2D.Float[numPartes];
		vectHit=new boolean[numPartes];
		for(int i=0;i<vectHit.length;i++){
			vectHit[i] = false;
		}
		setNotDestroyed();
	}
	protected void setRotate(){
		System.out.println("Cheguei Arma.setRotate()");
		if (rotate==3)
			rotate=0;
		else
			rotate++;
	}
	protected void setRotate(int x){
		rotate=x;
	}
	//	protected void setColor(Color c){
	//		MAIN_COLOR=c;
	//	}
	public Color getColor(){
		return MAIN_COLOR;
	}
	public boolean getDestroyed(){
		for(int i=0; i<numPartes;i++)
			if(vectHit[i]==false)
				return false;
		return true;
	}
	protected void setNotDestroyed(){
		for(int i=0; i<numPartes;i++)
			vectHit[i]=false;
	}
	protected boolean isHitHere(Point2D p){
		int i;
		for (i=0; i<vector.length&&!(isHere((Point) p));i++);
		return vectHit[i];
	}
	protected boolean Atingir(Point2D.Float pt){
		for (int i=0; i<vector.length;i++)
			if(pt.equals(vector[i])){
				vectHit[i]=true;
				return true;
			}
		return false;
	}
	protected void setNumPartes(int x){
		numPartes=x;
		setNotDestroyed();
	}
	public int getNumPartes(){
		return numPartes;
	}
	public abstract void setLocation(float x, float y);

	public void paintComponent(Graphics g){
		//		//				System.out.println("Cheguei Armas.paintComponent()");
		//		
		//		//		for(i=0; i<numPartes; i++){
		//		//			g2d.setColor(MAIN_COLOR);
		//		//			g2d.fillRect((int)vector[i].getX(),(int)vector[i].getY(), (int) SIZE, (int) SIZE);
		//		//		//	g2d.setColor(Color.GRAY);
		//		//			g2d.drawRect((int)vector[i].getX(),(int)vector[i].getY(),(int)SIZE-1, (int)SIZE-1);
		//		//		}
		//		//		//		System.out.printf("\nCheguei numPartes = %d\tArmas.paintComponent()\n", numPartes);
		//		//		//	g2d.drawRect((int)vector[i].getX(),(int)vector[i].getY(),SIZE, SIZE);
		//		//		
		//		//
		//		Graphics2D g2d = (Graphics2D) g;
		//		System.out.println("Cheguei TabuleiroArmas.paintComponent()");
		//		for(int i=0; i<vector.length; i++){
		////			System.out.printf("Cheguei iteracao = %d\tArmas.paintComponent()\n", i);
		//			System.out.printf("Cheguei this.Size = Armas.paintComponent()\n", i);		
		//			
		//			g2d.setColor(Color.BLACK);
		//			g2d.drawRect((int)vector[i].getX()*numPartes*100*i,(int)vector[i].getY()*numPartes*100*(i+(int)vector[i].getX()),55*(int)SIZE,55*(int) SIZE);
		//			
		//			g2d.setColor(MAIN_COLOR);
		//			g2d.fillRect((int)vector[i].getX()*numPartes*100*i,(int)vector[i].getY()*numPartes*100*i,55*(int)SIZE,55*(int) SIZE);
		////			if(vectHit[i]){
		////				g2d.setColor(Color.BLACK);
		////				g2d.drawLine(0, 0,(int)SIZE, (int)SIZE);
		////				g2d.drawLine(0, (int)SIZE,(int)SIZE, 0);
		////			}

		Graphics2D g2d = (Graphics2D)g;
//		setLocation(getX(), getY());
		for(int i=0;i<numPartes;i++){	//vertical
//			System.out.printf("Cheguei vector[%d] = %s in Arma.paintComponent\n",i, vector[i].toString());
			int x = (int)(vector[i].getX());
			int y = (int)(vector[i].getY());
			g2d.setColor(getColor());
			g2d.fillRect(x,y,CELL_SIZE,CELL_SIZE);
			g2d.setColor(Color.BLACK);
			g2d.drawRect(x , y ,CELL_SIZE-2,CELL_SIZE-2);
		}

}

public boolean isHere(Point p) {
	int x = p.x-getX();
	int y = p.y-getY();
	for(int j=0; j<vector.length; j++)
		if((x>=vector[j].getX())
			  	&& (x<=vector[j].getX()+CELL_SIZE)
			  	&&(y>=vector[j].getY())
			  	&& (y<=vector[j].getY()+CELL_SIZE)){
			return true;
		}
	return false;
}
float getThisSize(){
	return SIZE;
}
protected Point2D.Float[] getVectorLocation(){
	return vector; 
} 
protected int getRotate(){
	return rotate; 
}

public void rotate() {
	
} 



}

