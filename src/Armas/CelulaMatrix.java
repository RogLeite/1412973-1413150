package Armas;

import Tabuleiro.ExceptionCellAlreadyFilled;
import Tabuleiro.ExceptionCellAlreadyHit;

public class CelulaMatrix {
	private CelulaSimples[][] m;
	private Arma myArma;
	private TipoArma tArma;
	private CelulaMatrix(Arma a, int x,int y){
		m = new CelulaSimples[x][y];
		for(int i=0;i<m.length;i++){
			m[i] = new CelulaSimples[y];
			for(int j=0;j<m[0].length;j++){
				m[i][j] = new CelulaSimples();
			}
		}
		myArma = a;
		tArma = a.tipo;
	}

	public static CelulaMatrix instance(Arma a,int x,int y){
		return new CelulaMatrix(a,x,y);
	}
	public void spinClockwise(){
		CelulaSimples[][] temp = new CelulaSimples[m[0].length][m.length];
		for(int i=0;i<=m[0].length;i++){
			temp[i] = new CelulaSimples[m.length];
			for(int j=0;j<m.length;j++){
				temp[i][j] = m[j][m[0].length-i-1];
			}
		}
		m=temp;
	}
	public void spinCounterClockwise(){
		CelulaSimples[][] temp = new CelulaSimples[m[0].length][m.length];
		for(int i=0;i<=m[0].length;i++){
			temp[i] = new CelulaSimples[m.length];
			for(int j=0;j<m.length;j++){
				temp[i][j] = m[m.length-1-j][m[0].length-1-i];
			}
		}
		m=temp;
	}
	public void pasteIn(CelulaMatrix leaf, int x0, int y0){//x0 e y0 estão em referência 0
		for(int i=0;i<m.length||(i+x0)<leaf.m.length;i++){
			for(int j=0;j<m[0].length||(j+y0)<leaf.m[0].length;j++){
				leaf.m[i+x0][j+y0] = m[i][j];
			}
		}
	}

	public void fill(){
		for(int i=0;i<m.length;i++){
			for(int j=0;j<m[0].length;j++){
				try {
					m[i][j].fill();
				} catch (ExceptionCellAlreadyFilled e) {
					
				}
			}
		}
	}
	public void fill(int x, int y) throws ExceptionCellAlreadyFilled{
		m[x][y].fill();
	}

	public boolean allDestroyed() {
		for(int i=0;i<m.length;i++){
			for(int j=0;j<m[0].length;j++){
				if(m[i][j].isDestroyed()){
					return false;
				}
			}
		}
		return true;
	}

	public boolean isHitHere(int x, int y) throws IndexOutOfBoundsException{
		isHere(x,y);
		return m[x][y].isHit();
	}

	public boolean hitHere(int x, int y) throws ExceptionCellAlreadyHit, ExceptionNoWeaponHere {
		if(!isHere(x, y)){
			throw new ExceptionNoWeaponHere();
		}
		m[x][y].hit();
		return true;
	}

	public boolean isHere(int x, int y) throws IndexOutOfBoundsException{
		if(x<0||y<0||x>=m.length||y>=m[0].length){
			throw new IndexOutOfBoundsException();
		}
		return m[x][y].isFilled();
	}

	public int getWidth() {
		return m.length;
	}

	public int getHeight() {
		return m[0].length;
	}
}
	class CelulaSimples{

		private boolean VISIBLE;
		private boolean FILLED;
		private boolean HIT;
		private boolean DESTROYED;
		protected CelulaSimples(){
			VISIBLE = false;
			DESTROYED = false;
			HIT = false;
			FILLED = false;
		}
		public boolean isFilled() {
			return FILLED;
		}
		public void hit() throws ExceptionCellAlreadyHit {
			if(HIT){
				throw new ExceptionCellAlreadyHit();
			}
			
		}
		public boolean isHit() {
			return HIT;
		}
		public boolean isDestroyed() {
			return DESTROYED;
		}
		public void fill() throws ExceptionCellAlreadyFilled {
			if(FILLED)
				throw new ExceptionCellAlreadyFilled();
			FILLED = true;
		}
	}