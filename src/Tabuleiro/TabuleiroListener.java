package Tabuleiro;

import java.awt.Point;

import javax.xml.bind.Marshaller.Listener;

import PosArmas.TabuleiroArmas;
import TabuleiroPartida.FrameEmbateMenuBar;
import TabuleiroPartida.TabuleiroEmbate;
import TopoNivel.MyMouseListener;

public interface TabuleiroListener{
	
	public TabuleiroInvisivel newInstanceTabuleiroInvisivel(int SIDE_TAB); /*{
		return TabuleiroInvisivel.newInstance(SIDE_TAB);
	}*/

	public void clicked(Point point);/*{
		try {
			t.takeAction(point);
		} catch (ExceptionCellAlreadyHit e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionCellAlreadyFilled e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	public String getThisActionCommand(Class<?> class1);/* {
		if(class1.isAssignableFrom(MyMouseListener.class)){
			return TabuleiroArmas.getTakeActionString();
		}
		return null;
	}*/
//	UNOFFICIAL
//	public void receiveCommand(String m){
//		if(m.compareTo(TabuleiroInvisivel.getTakeActionString())==0){
//			
//		}
//	}
//	

	public boolean imHit(Point p);/* {
		if(t instanceof TabuleiroArmas){
			return false;
		}
		else if(t instanceof TabuleiroEmbate){
			t.imHit(p);
		}
		return false;
	}*/

	public boolean getVisibilidade(); /*{
		return t.getVisibilidade();
	}*/

	public String getTakeActionString(); /*{
		return null;
	}*/
	public void transferirTabuleiroInvisivel(Tabuleiro in,Tabuleiro out);/*{
		out.transferirTabuleiroInvisivel(in);
	}*/
	
}
