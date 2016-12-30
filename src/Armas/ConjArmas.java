package Armas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.geom.Point2D;
import javax.swing.JFrame;

import Tabuleiro.ExceptionCellAlreadyHit;

public class ConjArmas{
	private static int TotalNotDestroyed=TipoArma.getSomaQtdMax();
	private static Arma selectedArma = null;
	public Arma ArmVect[] = new Arma[TotalNotDestroyed];/*[TotalNotDestroyed]*/

	public ConjArmas(int size){
		/*
		ArmVect=new Arma[TotalNotDestroyed];
		for(int i=0; i<TotalNotDestroyed;i++)
			ArmVect[i]= new Arma(size);*/

	}
	public ConjArmas(){
	}
	public void atingiuArma(int x, int y) throws ExceptionCellAlreadyHit, ExceptionNoWeaponHere, ExceptionArrayNotFilled{
		Point pt = new Point(x,y);
		for(int i=0; i<ArmVect.length; i++){

			System.out.printf("Cheguei (ArmVect==null) = %s\n", Boolean.toString((ArmVect==null)));
			System.out.printf("Cheguei (ArmVect[%d]==null) = %s\n", i,Boolean.toString((ArmVect[i]==null)));
			if(ArmVect[i]==null){
				//				throw new ExceptionArrayNotFilled();
				//				TODO
				throw new ExceptionNoWeaponHere();
			}

			if(ArmVect[i].Atingir(pt)){
				if(ArmVect[i].getDestroyed()){
					TotalNotDestroyed--;
				}
			}

		}
	}

	public boolean EhArma( int x, int y){
		if(getIndiceArma(x,y)==-1)
			return false;
		return true;
	}

	public boolean EhAresta(int x, int y){
		for(int i=-1; i<1; i++)
			for(int j=-1;j<1;j++)
				if(EhArma((x+i),(j+y)))
					return true;
		return false;
	}

	int getIndiceArma( int x, int y){
		Point2D.Float pt = new Point2D.Float((float)x,(float)y);

		for(int i=0; i<ArmVect.length; i++){
			if(ArmVect[i]==null){
			}
			else if((x>=ArmVect[i].getX())
					&& (x<=ArmVect[i].getX()+ArmVect[i].getWidth())
					&&(y>=ArmVect[i].getY())
					&& (x<=ArmVect[i].getY()+ArmVect[i].getHeight())){
				return i;
			}
		}
		return -1;
	}
	//(ArmVect[i].isHere(pt))
	/*((x>=ArmVect[i].getX())
	  	&& (x<=ArmVect[i].getX()+ArmVet[i].getWidth())
	  	&&(y>=ArmVect[i].getY())
	  	&& (x<=ArmVect[i].getY()+ArmVect[i].getHeight()))*/


	public TipoArma	getTipoArma ( int x, int y ){
		int i= getIndiceArma(x, y);
		return TipoArma.getnome( ArmVect[i].getNumPartes() );
	}

	public boolean allDestroyed(){
		//		for(int i=0; i<ArmVect.length; i++)
		//			if(!ArmVect[i].getDestroyed())
		if(TotalNotDestroyed == 0)
			return true;
		return false;
	}

	public Color getColorArma(int x,int y){
		int i=getIndiceArma(x, y);
		if(i != -1)
			return ArmVect[i].getColor();
		return null;
	}

	public static Arma[] getFilledArray(int cellSize) {
		//		System.out.printf("\nCheguei forInsereArmas\n");
		int j=0;
		Arma.setCellSize(cellSize);
		Arma c[] = new Arma[TotalNotDestroyed];

		//		System.out.printf("getFilledArray:\n\tc\t= %s\n",Boolean.toString(c==null));
		//		System.out.printf("TipoArma.Hidroaviao.getQtdMax() = %d\n",TipoArma.Hidroaviao.getQtdMax());
		//		System.out.printf("TotalNotDestroyed = %d\n",TotalNotDestroyed);
		for(int i=0; i<TipoArma.Submarino.getQtdMax(); i++,j++)
			c[j]= Submarino.instance();
		for(int i=0; i<TipoArma.Destroyer.getQtdMax(); i++,j++)
			c[j]= Destroyer.instance();
		for(int i=0; i<TipoArma.Hidroaviao.getQtdMax(); i++,j++)
			c[j]= Hidroaviao.instance();
		for(int i=0; i<TipoArma.Cruzador.getQtdMax(); i++,j++)
			c[j]= Cruzador.instance();
		for(int i=0; i<TipoArma.Couracado.getQtdMax();i++,j++)
			c[j]= Couracado.instance();

		//		System.out.printf("J = %d\n",j);
		int k=1;
		TipoArma t = TipoArma.getnome(k);
//		System.out.printf("t = %s ConjArmas.getFilledArray()\n", t.toString());
		System.out.println("ConjArmas.getFilledArray()\n---------------------------------");
		for(int l=0;k<=TipoArma.getQtdTipes();k++,t = TipoArma.getnome(k)){
			System.out.printf("l=%d\n", l);
			for(int i=0;i<t.getQtdMax();i++){
				System.out.printf("\tl=%d\n", l);
				c[l].setLocation((float) (i*cellSize*(t.getNumCels()+1)*0.9),(float)(k)*(3*cellSize));
				//			System.out.printf("Cheguei width = %f ConjArmas.getFilledArray\n",width/3);
				//			System.out.printf("Cheguei i = %d ConjArmas.getFilledArray\n",i/5);
//				System.out.printf("Cheguei c[%d].getLocation = %s ConjArmas.getFilledArray\n",i,c[l].getLocation().toString());
				System.out.printf("\tc[%d] = %s \n",l,c[l].getLocation().toString());
				c[l].setIgnoreRepaint(false);
				c[l].setVisible(true);
				c[l].setLayout(null);
				l++;
			}
		}
		System.out.println("\n---------------------------------");
//
//		j=0;
//		int k=0;
//		for(j=0;j<c.length;k++)
//			for(int i=0;i<c[j].numPartes;i++,j++){
//				c[j].setLocation((float)i*cellSize*(c[j].numPartes+1),(float)(k/5)*(3*cellSize));
//				//			System.out.printf("Cheguei width = %f ConjArmas.getFilledArray\n",width/3);
//				//			System.out.printf("Cheguei i = %d ConjArmas.getFilledArray\n",i/5);
//				System.out.printf("Cheguei c[%d].getLocation = %s ConjArmas.getFilledArray\n",j,c[j].getLocation().toString());
//				c[j].setIgnoreRepaint(false);
//				c[j].setVisible(true);
//				c[j].setLayout(null);
//			}
		return c;
	}


	//	public void instArmaPlayer ( JFrame tab, TipoArma nome, int rotate,int x, int y){
	//		ArmVect[i].setColor(nome.getColor());
	//		setNumPartes(nome.getNumCels());
	//		setLocation( (float) x, (float) y );
	//		setRotate(rotate);
	//
	//
	//
	//	}

	public static ConjArmas getEmptyArray(){
		return new ConjArmas();
	}

	public static void receiveArma(ConjArmas c, Point2D p) throws ExceptionArmVectFilled, ExceptionNoWeaponSelected{
		System.out.println("Cheguei ConjArmas.receiveArma");
		Arma a = getSelectedArma();
		if(a==null){
			throw new ExceptionNoWeaponSelected();
		}
		if(AllowPlacement()){

		}
		System.out.printf("\nCheguei Arma Location %s ConjArmas.receiveArma\n",p.toString());
		a.setLocation((float)p.getX(),(float)p.getY());
		addArma(c,a);

		if(!AllowPlacement()){
		}


	}
	static void addArma(ConjArmas c, Arma a) throws ExceptionArmVectFilled {
		System.out.println("Cheguei ConjArmas.addArma");
		for(int i=0;i<TotalNotDestroyed;i++){
			if(c.ArmVect[i]==null){
				c.ArmVect[i] = a;
				System.out.printf("Cheguei c.ArmVect[i] tem algo?: %b ConjArmas.addArma\n",(c.ArmVect[i]!=null));
				emptySelectedArma();
				return;
			}
		}
		throw new ExceptionArmVectFilled();
	}
	static void setSelectedArma(Arma a){
		System.out.println("Cheguei ConjArmas.setSelectedArma");
		selectedArma = a;
	}
	static Arma getSelectedArma() {
		System.out.println("Cheguei ConjArmas.getSelectedArma");
		return selectedArma;
	}
	private static void emptySelectedArma() {
		System.out.println("Cheguei ConjArmas.emptySelectedArma");
		selectedArma = null;
	}
	public Arma[] getArmVec() {
		return ArmVect;
	}

	private static boolean AllowPlacement() {
		return false;
	}
	public static void rotatePiece() throws ExceptionNoWeaponSelected {
		System.out.println("Cheguei ConjArmas.rotatePiece()");
		if(getSelectedArma()==null){
			throw new ExceptionNoWeaponSelected();
		}
		else{
			getSelectedArma().rotateClockwise();
		}

	}

}

