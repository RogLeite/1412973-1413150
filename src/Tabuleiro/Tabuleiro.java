package Tabuleiro;
import java.awt.Color;
//import java.awt.Component;
//import java.awt.Container;
//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
//import java.awt.event.MouseListener;
//import java.awt.geom.Point2D;
import java.awt.geom.Point2D;

//import javax.swing.JFrame;
import javax.swing.JPanel;

import Armas.ArmaListener;
import Armas.ConjArmas;
import Armas.ExceptionNoWeaponHere;
import PosArmas.TabuleiroArmas;
import TabuleiroPartida.TabuleiroEmbate;
import TopoNivel.MyMouseListener;

//import TabuleiroPartida.FrameEmbate;
//import TopoNivel.MyMouseListener;

//import PlayerNameInput.MyActionListener;

public abstract class Tabuleiro extends JPanel implements TabuleiroListener/* implements ITabuleiroPartida, ITabuleiroArmas*/{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String BASE_ACTION_STRING = "TAB";
	private final int SIDE_TAB = 16;
	private final int TAB_INV_IND = 15*15;
	protected float CELL_SIZE;
	private final String bound_player;
	private boolean visibilidade = false;
	private TabuleiroInvisivel tabInvisivel;
	public Tabuleiro(int x, int y, float boardsize, String player){
		CELL_SIZE = boardsize/SIDE_TAB;
		setBounds(x, y, (int)boardsize, (int)boardsize);
		setLayout(null);
		setIgnoreRepaint(false);
		bound_player = player;

		tabInvisivel = newInstanceTabuleiroInvisivel(SIDE_TAB);
//		System.out.printf("Cheguei tabInvisivel in %s TabuleiroInvisivel\n",tabInvisivel.getLocation().toString() );
		add(tabInvisivel);
		for(int i=1;i<SIDE_TAB;i++){
			for(int j=1;j<SIDE_TAB;j++){
				Celula tab = new Celula(this,CELL_SIZE, i*CELL_SIZE, j*CELL_SIZE);
				add(tab,(((SIDE_TAB-1)*(i-1))+(j-1))); // i~=posX j~=posY; logo, a ordem � para cada coluna, guarda suas fileiras
				/*
				 * add(tab,((SIDE_TAB-1)*i)+j)
				 * isso significa que o vetor(?) de contents do tabuleiro armazenar� as c�lulas nessa ordem
				 * (considere i = indice da coluna e j = indice da fileira de uma matriz)
				 * isso facilitar� achar a c�lula correta na hora de identificar o clique:
				 * 	Celula.hitCell( (Celula) (tabuleiro.getComponent ( /opera��o inversa pra achar o �ndice correto/ ) ) ) ;
				 * */
			}
		}


	}
	private String getBaseActionString() {
		return BASE_ACTION_STRING;
	}
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		//		System.out.printf("\nCheguei %s Tabuleiro.paintComponent()\n",bound_player);

		String n[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
		for(int i=1;i<=SIDE_TAB-1;i++){
			g2d.drawString(n[i-1],(int)(i*CELL_SIZE+CELL_SIZE/5), (int)(3*CELL_SIZE/4));
		}
		String c[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
		for(int i=1;i<=SIDE_TAB-1;i++){
			g2d.drawString(c[i-1],(int)CELL_SIZE/2, (int)(i*CELL_SIZE+(3*CELL_SIZE/4)));
		}

	}
	public TabuleiroInvisivel getTabuleiroInvisivel(){
		return tabInvisivel;
	}
	//	public void toggleVisibilidade()
	//	{
	//		for(int i=0;i<getComponentCount();i++)
	//			((Celula)getComponent(i)).toggleVisibilidade();
	//		if(visibilidade)
	//			visibilidade = false;
	//		else
	//			visibilidade = true;
	//	}
	public void setVisibilidade(boolean v){
		visibilidade = v;
//				System.out.printf("\nCheguei %s Tabuleiro.setVisibilidade(%b)\n",bound_player,v);
//		System.out.println("Cheguei Tabuleiro.setVisibilidade");
		for(int i=0;i<getComponentCount()-1;i++){
			((Celula)getComponent(i)).repaint();
		}
	}
	public boolean getVisibilidade(){
		return visibilidade ;
	}
	public String getBoundPlayer(){
		return bound_player;
	}
	public abstract void takeAction(Point2D p) throws ExceptionCellAlreadyHit, ExceptionCellAlreadyFilled;
	public abstract boolean imHit(Point p); 
	public boolean imFilled(Point p){
		ConjArmas c = getTabuleiroInvisivel().getArmasArray();
//		System.out.printf("Cheguei c tem algo? %b Tabuleiro.imFilled\n", (c!=null));
		return ArmaListener.hasArma(c,p.getLocation());
	}
	public boolean imDestroyed(Point p){
		ConjArmas c = getTabuleiroInvisivel().getArmasArray();
		return ArmaListener.isDestroyed(c,p);
	}
	public Color myColor(Point p) throws ExceptionNoWeaponHere {
		ConjArmas c = getTabuleiroInvisivel().getArmasArray();
		return ArmaListener.getColorHere(c,p);
	}
	void transferirTabuleiroInvisivel(Tabuleiro t){
		t.addTabuleiroInvisivel(getTabuleiroInvisivel());
	}
	private void addTabuleiroInvisivel(TabuleiroInvisivel tabuleiroInvisivel) {
		tabInvisivel = tabuleiroInvisivel;
		System.out.println("Cheguei Tabuleiro.addTabuleiroInvisivel");
	}
	public TabuleiroInvisivel newInstanceTabuleiroInvisivel(int SIDE_TAB){
		return TabuleiroInvisivel.newInstance(SIDE_TAB);
	}
	public void clicked(Point point){
		try {
			takeAction(point);
		} catch (ExceptionCellAlreadyHit e) {
			// TODO Auto-generated catch block
		} catch (ExceptionCellAlreadyFilled e) {
			// TODO Auto-generated catch block
		} 
	}
	public String getThisActionCommand(Class<?> class1) {
		if(class1.isAssignableFrom(MyMouseListener.class)){
			return getTakeActionString();
		}
		return null;
	}
	public String getTakeActionString() {
		return null;
	}

	public  void transferirTabuleiroInvisivel(Tabuleiro in,Tabuleiro out){
		out.transferirTabuleiroInvisivel(in);
	}
	public boolean imHovered(Point location) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean placingAllowed() {
		// TODO Auto-generated method stub
		return false;
	}
}
