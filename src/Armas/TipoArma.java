package Armas;

import java.awt.Color;

public enum TipoArma {
	Submarino (1, Color.GREEN, 4),	//MAX = 4
	Destroyer (2,Color.ORANGE, 3),	//MAX = 3
	Hidroaviao (3,Color.YELLOW, 5),	//MAX = 5
	Cruzador(4,Color.BLACK, 2),		//MAX = 2
	Couracado (5,Color.WHITE, 1);	//MAX = 1

	private final int nCell;
	private final Color MAIN_COLOR; 
	private final int qtdMax;
	TipoArma(int numCelulas,Color c,int Max) {
		this.nCell = numCelulas;
		this.MAIN_COLOR = c;
		this.qtdMax = Max;
	} 
	public int getNumCels(){
		return nCell;
	}
	public Color getColor(){
		return MAIN_COLOR;
	}
	public int getQtdMax() {
		return qtdMax;
	}
	public static int getSomaQtdMax(){
		int i=0;
		i+=Submarino.qtdMax;
		i+=Destroyer.qtdMax;
		i+=Hidroaviao.qtdMax;
		i+=Cruzador.qtdMax;
		i+=Couracado.qtdMax;
		return i;
	}
	public static TipoArma getnome(int n){
		
		if(n==Submarino.nCell)
			return Submarino; 
		if(n==Destroyer.nCell)
			return Destroyer; 
		if(n==Hidroaviao.nCell)
			return Hidroaviao; 
		if(n==Cruzador.nCell)
			return Cruzador; 
		if(n==Couracado.nCell)
			return Couracado; 
		return null;
	}
	
}
