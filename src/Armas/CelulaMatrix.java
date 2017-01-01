package Armas;

import java.awt.Color;
import java.awt.Point;

import PosArmas.ExceptionPlacingNotAllowed;
import Tabuleiro.ExceptionCellAlreadyFilled;
import Tabuleiro.ExceptionCellAlreadyHit;

public class CelulaMatrix {
	protected int WIDTH=1;
	protected int HEIGHT=1;
	private CelulaSimples[][] m;
	private CelulaMatrix(Arma a, int x,int y){
		WIDTH = x;
		HEIGHT = y;
		m = new CelulaSimples[getMyWidth()][getMyHeight()];
		for(int i=0;i<getMyWidth();i++){
			m[i] = new CelulaSimples[getMyHeight()];
			for(int j=0;j<getMyHeight();j++){
				m[i][j] = new CelulaSimples(a);
			}
		}
	}
	private CelulaMatrix(int x,int y){
		WIDTH = x;
		HEIGHT = y;
		m = new CelulaSimples[getMyWidth()][getMyHeight()];
		for(int i=0;i<getMyWidth();i++){
			m[i] = new CelulaSimples[getMyHeight()];
			for(int j=0;j<getMyHeight();j++){
				m[i][j] = new CelulaSimples();
			}
		}
	}

	public static CelulaMatrix instance(Arma a,int x,int y){
		return new CelulaMatrix(a,x,y);
	}
	public static CelulaMatrix instance(int x,int y){
		return new CelulaMatrix(x,y);
	}
	public void spinClockwise(){
		CelulaSimples[][] temp = new CelulaSimples[getMyHeight()][getMyWidth()];
		for(int i=0;i<getMyHeight();i++){
			temp[i] = new CelulaSimples[getMyWidth()];
			for(int j=0;j<getMyWidth();j++){
				temp[i][j] = m[j][getMyHeight()-i-1];
			}
		}
		int t = HEIGHT;
		HEIGHT = WIDTH;
		WIDTH = t;
		m=temp;
	}
	public void spinCounterClockwise(){
		CelulaSimples[][] temp = new CelulaSimples[getMyHeight()][getMyWidth()];
		for(int i=0;i<getMyHeight();i++){
			temp[i] = new CelulaSimples[getMyWidth()];
			for(int j=0;j<getMyWidth();j++){
				temp[i][j] = m[getMyWidth()-1-j][getMyHeight()-1-i];
			}
		}
		int t = HEIGHT;
		HEIGHT = WIDTH;
		WIDTH = t;
		m=temp;
	}
	public void pasteIn(CelulaMatrix leaf, int x0, int y0){//x0 e y0 estão em referência 0
		for(int i=0;i<getMyWidth();i++){
			for(int j=0;j<getMyHeight();j++){
				System.out.printf("\t[x][y] = [%d][%d] CelulaMatrix.pasteIn\n",getMyWidth(),getMyHeight());
				System.out.printf("\t[i][j] = [%d][%d] CelulaMatrix.pasteIn\n", i,j);
				System.out.printf("\t[i+x0][j+y0] = [%d][%d] CelulaMatrix.pasteIn\n", i+x0,j+y0);
				leaf.m[i+x0][j+y0] = m[i][j];
			}
		}
	}

	public void fill(){
		for(int i=0;i<getMyWidth();i++){
			for(int j=0;j<getMyHeight();j++){
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
		for(int i=0;i<getMyWidth();i++){
			for(int j=0;j<getMyHeight();j++){
				System.out.printf("\t (m[%d][%d].isFilled()&&!m[%d][%d].isHit()) = %b\n\t\t\n",i,j,i,j,(m[i][j].isFilled()&&!m[i][j].isDestroyed()));
				if(m[i][j].isFilled()&&!m[i][j].isHit()){
					return false;
				}
			}
		}
		return true;
	}

	public boolean hitHere(int x, int y) throws ExceptionCellAlreadyHit, ExceptionNoWeaponHere {
		m[x][y].hit();
//		System.out.printf("(m[%d][%d].getArma()==null) = %b CelulaMatrix.hitHere\n", x,y,(m[x][y].getArma()==null));
		if(m[x][y].getArma()!=null){
			if(((Arma) m[x][y].getArma()).getDestroyed()){
				destroy();
			}
		}
		//		if(!isHere(x, y)){
		//			throw new ExceptionNoWeaponHere();
		//		}
		return true;
	}

	private void destroy() {
		System.out.println("Cheguei CelulaMatrix.destroy()");
		for(int i=0;i<getMyWidth();i++){
			for(int j=0;j<getMyHeight();j++){
				m[i][j].destroy();
			}
		}
	}
	public boolean isHere(int x, int y) throws IndexOutOfBoundsException{
		if(x<0||y<0||x>=getMyWidth()||y>=getMyHeight()){
			throw new IndexOutOfBoundsException();
		}
		return m[x][y].isFilled();
	}

	public int getMyWidth() {
		return WIDTH;
	}

	public int getMyHeight() {
		//		System.out.printf("\t getMyHeight() = %d CelulaMatrix.getMyHeight\n", getMyHeight());
		return HEIGHT;
	}
	public boolean checkSpaceIn(CelulaMatrix cellMatrix, int x, int y) throws ExceptionPlacingNotAllowed {
		for(int i=0;i<getMyWidth();i++){
			for(int j=0;j<getMyHeight();j++){
				if(i+x<0||j+y<0||i+x>=cellMatrix.getMyWidth()||j+y>=cellMatrix.getMyHeight()){
					throw new ExceptionPlacingNotAllowed();
				}
				for(int k=i+x-1;k<i+x+2;k++){
					for(int l=j+y-1;l<j+y+2;l++){
						if(!(k<0||l<0||k>=cellMatrix.getMyWidth()||l>=cellMatrix.getMyHeight())){
							if(cellMatrix.m[k][l].isFilled()&&m[i][j].isFilled()){
								throw new ExceptionPlacingNotAllowed();
							}
						}
					}	
				}
			}
		}
		return true;
	}
	public void setVisibilidade(boolean v) {
		for(int i=0;i<getMyWidth();i++){
			for(int j=0;j<getMyHeight();j++){
				m[i][j].setVisibilidade(v);
			}
		}
	}
	public Arma getThisWeapon(Point np) {
		return (Arma) m[np.x][np.y].getArma();
	}
	public void removeThisWeapon(Arma a) {
		for(int i=0;i<getMyWidth();i++){
			for(int j=0;j<getMyHeight();j++){
				Point np = new Point(i,j);
				if((Arma) getThisWeapon(np)==a){
					CelulaSimples c = new CelulaSimples();
					c.setVisibilidade(m[i][j].getVisibilidade());
					m[i][j] = c;
				}
			}
		}
		
	}
	
	public boolean isHitHere(int x, int y) throws IndexOutOfBoundsException{
		isHere(x,y);
		boolean b = m[x][y].isHit();
		if(b){
			System.out.printf("\t m[%d][%d] isHit = %b CelulaMatrix.isHitHere\n", x,y,m[x][y].isHit());
		}
		return b;
	}
	public boolean isVisivelHere(int x, int y) throws IndexOutOfBoundsException{
		isHere(x,y);
		return m[x][y].isVisivel();
	}
	public boolean isFilledHere(int x, int y) throws IndexOutOfBoundsException{
		isHere(x,y);
		boolean b = m[x][y].isFilled();
		if(b){
//			System.out.printf("\t m[%d][%d] ifFilled = %b CelulaMatrix.isFilledHere\n", x,y,m[x][y].isFilled());
		}
		//		System.out.printf("\t m[%d][%d] ifFilled = %b CelulaMatrix.isFilledHere\n", x,y,m[x][y].isFilled());
		return b;
	}
	public boolean isDestroyedHere(int x, int y) throws IndexOutOfBoundsException{
		isHere(x,y);
		return m[x][y].isDestroyed();
	}
	public Color isColorHere(int x, int y) throws IndexOutOfBoundsException, ExceptionNoWeaponHere{
		isHere(x,y);
		return m[x][y].Color();
	}
	public boolean isHoveredHere(int x, int y) {
		return m[x][y].isHovered();
	}
	public void updateHover(Point p, CelulaMatrix cm) {
		System.out.println("Foi\tCelulaMatrix.updateHover()");
		for(int i=0;i<getMyWidth();i++){
			for(int j=0;j<getMyHeight();j++){
				if(cm==null){
					m[i][j].setHovered(false);
				}
				else if((i-p.x>=0)
						&&(i-p.x<cm.getMyWidth())
						&&(j-p.y>=0)
						&&(j-p.y<cm.getMyHeight())){
					System.out.printf("SetHover\n\tcm.isFilledHere(%d-%d,%d-%d)\n\tm[%d][%d].setHovered(<%b>)\nCelulaMatrix.updateHover()\n",i,p.x,j,p.y,i,j,cm.isFilledHere(i-p.x,j-p.y));
					m[i][j].setHovered(cm.isFilledHere(i-p.x,j-p.y));
				}
				else{
					m[i][j].setHovered(false);
				}
			}
		}
	}
}
class CelulaSimples{

	private boolean VISIBLE;
	private boolean FILLED;
	private boolean HIT;
	private boolean DESTROYED;
	private boolean HOVERED;
	private Arma myArma=null;
	private TipoArma tArma=null;
	protected CelulaSimples(Arma a){
		VISIBLE = false;
		DESTROYED = false;
		HIT = false;
		FILLED = false;
		HOVERED = false;
		myArma = a;
		tArma = a.getTipo();
	}
	public void setHovered(boolean b) {
		HOVERED = b;
	}
	public boolean isHovered() {
		return HOVERED;
	}
	public boolean getVisibilidade() {
		return VISIBLE;
	}
	public void destroy() {
		DESTROYED = true;
	}
	public Object getArma() {
		return myArma;
	}
	public Color Color() throws ExceptionNoWeaponHere {
		if(tArma==null){
			System.out.printf("\n\tArma = %b\n\tTipoArma = %b\n\tCelulaSimples.Color\n",myArma==null,tArma==null);
			throw new ExceptionNoWeaponHere();
		}
		return tArma.getColor();
	}
	public boolean isVisivel() {
		return VISIBLE;
	}
	public void setVisibilidade(boolean v) {
		VISIBLE = v;
	}
	public CelulaSimples() {
		VISIBLE = false;
		DESTROYED = false;
		HIT = false;
		FILLED = false;
		HOVERED = false;
	}
	public boolean isFilled() {
		return FILLED;
	}
	public void hit() throws ExceptionCellAlreadyHit {
		if(HIT){
			throw new ExceptionCellAlreadyHit();
		}
		HIT = true;

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