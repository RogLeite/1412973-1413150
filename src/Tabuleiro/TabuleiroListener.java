package Tabuleiro;

import java.awt.Point;

import javax.xml.bind.Marshaller.Listener;

import PosArmas.TabuleiroArmas;
import TabuleiroPartida.FrameEmbateMenuBar;
import TabuleiroPartida.TabuleiroEmbate;
import TopoNivel.MyMouseListener;

public class TabuleiroListener extends Listener {
	
	public static TabuleiroInvisivel newInstanceTabuleiroInvisivel(int SIDE_TAB) {
		return TabuleiroInvisivel.newInstance(SIDE_TAB);
	}

	public static void clicked(Tabuleiro t, Point point) {
		try {
			t.takeAction(point);
		} catch (ExceptionCellAlreadyHit e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionCellAlreadyFilled e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getThisActionCommand(Class<?> class1) {
		if(class1.isAssignableFrom(MyMouseListener.class)){
			return TabuleiroArmas.getTakeActionString();
		}
		return null;
	}
//	
//	public void receiveCommand(String m){
//		if(m.compareTo(TabuleiroInvisivel.getTakeActionString())==0){
//			
//		}
//	}
//	

	public static boolean imHit(Tabuleiro t, Point p) {
		if(t instanceof TabuleiroArmas){
			return false;
		}
		else if(t instanceof TabuleiroEmbate){
			t.imHit(p);
		}
		return false;
	}

	public static boolean getVisibilidade(Tabuleiro t) {
		return t.getVisibilidade();
	}

	public static String getTakeActionString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
