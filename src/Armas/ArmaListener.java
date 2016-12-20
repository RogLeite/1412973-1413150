package Armas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.xml.bind.Marshaller.Listener;

//import Tabuleiro.TabuleiroInvisivel;
import Armas.ConjArmas;

public class ArmaListener extends Listener {

	public static float getThisSize(Arma a){
		return a.getThisSize();
	}
	public static ConjArmas getEmptyArray() {
		return ConjArmas.getEmptyArray();
	}

	public static boolean isHitHere(Point2D point, Arma a) {
		return a.isHitHere(point);
	}
	public static boolean isDestroyed(Arma a){
		return a.getDestroyed();
	}

	public static int getIndiceArma(ConjArmas armasArray, Point p) {
		return armasArray.getIndiceArma((int)p.getX(), (int)p.getY());
	}

	public static Color getColor(Arma a) {
		return a.getColor();
	}

	public static Arma[] getFilledArray( float width,int cellSize) {
		return ConjArmas.getFilledArray( width,cellSize) ;
	}

	public static void selectArma(Component c) throws ExceptionComponentIsNotArma, ExceptionWeaponAllreadySelected {

		System.out.println("Cheguei ArmaListener.selectArma");
		if(c instanceof Arma){
			Arma a = (Arma) c;
			if(ConjArmas.getSelectedArma()!=null){
				throw new ExceptionWeaponAllreadySelected();
			}
			ConjArmas.setSelectedArma(a);
		}
		else{
			throw new ExceptionComponentIsNotArma();
		}
		
		
	}


}
