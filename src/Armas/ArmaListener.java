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

	public static ConjArmas getEmptyArray() {
		return ConjArmas.getEmptyArray();
	}

	public static boolean isHitHere(Point2D point, Arma a) {
		return a.isHitHere(point);
	}
	public static boolean isDestroyed(ConjArmas c, Point p){
		int index = ArmaListener.getIndiceArma(c, p);
		Arma a = c.getArmVec()[index];
		return a.getDestroyed();
	}

	public static int getIndiceArma(ConjArmas armasArray, Point p) {
		return armasArray.getIndiceArma((int)p.getX(), (int)p.getY());
	}

	public static Color getColor(Arma a) {
		return a.getColor();
	}

	public static Arma[] getFilledArray(int cellSize) {
		return ConjArmas.getFilledArray(cellSize) ;
	}

	public static void selectArma(Component c) throws ExceptionComponentIsNotArma, ExceptionWeaponAllreadySelected {

		System.out.println("Cheguei ArmaListener.selectArma");
		if(c instanceof Arma){
			Arma a = (Arma) c;
			try {
				a = ConjArmas.getSelectedArma();
				throw new ExceptionWeaponAllreadySelected();
			} catch (ExceptionNoWeaponSelected e) {
				ConjArmas.setSelectedArma(a);
			}
		}
		else{
			throw new ExceptionComponentIsNotArma();
		}


	}
	public static boolean hasArma(ConjArmas c, Point p) {
		int index = c.getIndiceArma((int)p.getX(), (int)p.getY());
		if(index>=0 && c.getArmVec()[index].isHere(p)){
			System.out.printf("Cheguei index = %d  ArmaListener.hasArma\n", index);
			System.out.printf("Cheguei Point = %s  ArmaListener.hasArma\n", p.toString());
			return true;
		}
		else{
			return false;
		}

	}
	public static boolean hasDestroyedPart(ConjArmas c, Point p) {
		if(hasArma(c, p)){
			int index = getIndiceArma(c, p);
			return c.ArmVect[index].isHitHere(p);
		}
		else{
			return false;
		}
	}
	public static Color getColorHere(ConjArmas c, Point p) throws ExceptionNoWeaponHere {
		if(hasArma(c, p)){
			int index = getIndiceArma(c, p);
			return c.ArmVect[index].getColor();
		}
		else{
			throw new ExceptionNoWeaponHere();
		}
	}
	public static Arma receiveArma() throws ExceptionNoWeaponSelected{
		return ConjArmas.receiveArma();
		
	}
	public static void confirmedReceive(){
		ConjArmas.confirmedReceive();
	}
	public static void rotatePiece() throws ExceptionNoWeaponSelected {
		System.out.println("Cheguei ArmasListener.rotatePiece()");
		ConjArmas.rotatePiece();
	}

	public static Arma getSelectedArma() throws ExceptionNoWeaponSelected {
		return ConjArmas.getSelectedArma();
	}

	public static void emptySelectedArma() {
		ConjArmas.emptySelectedArma();
		
	}


}
