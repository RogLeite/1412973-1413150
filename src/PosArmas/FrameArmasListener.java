package PosArmas;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.bind.Marshaller.Listener;

import Armas.ExceptionComponentIsNotArma;
import Tabuleiro.TabuleiroListener;
import TabuleiroPartida.FrameEmbate;
import TabuleiroPartida.FrameEmbateListener;
import TabuleiroPartida.FrameEmbateMenuBar;
import TopoNivel.GameFrame;
import TopoNivel.MyActionListener;
import TopoNivel.MyMouseListener;
import TopoNivel.TestadorListener;

public interface FrameArmasListener{
	public String getThisActionCommand(Class<?> class1);/*{
//		if(class1.isAssignableFrom(MyMouseListener.class)){
//			System.out.println("Cheguei MyMouseListener FrameArmasListener.getThisActionCommand()");
//			return FrameArmas.getTakeActionString();
//		}
//		else if(class1.isAssignableFrom(MyActionListener.class)){
////			System.out.println("Cheguei MyActionListener FrameArmasListener.getThisActionCommand()");
//			return FrameArmas.getBaseActionString();
//		}
//		return "<in FrameArmasListener>YOU DON'T KNOW WHAT YOU'RE LOOKING FOR!";
		if(class1.isAssignableFrom(FrameArmasMenuBar.class)){
			System.out.println("Cheguei FrameArmasMenuBar string FrameArmasListener.getThisActionCommand()");
			System.out.println(FrameEmbate.getSaveString());
			return FrameEmbate.getSaveString();
		}
	}*/

	public void takeAction(Point p);/*{
//		System.out.println("Cheguei FrameArmasListener.takeAction()");
		((FrameArmas)g).takeAction(point);
	}*/

	public JFrame instance(String name); /*{
		return FrameArmas.instance(name);
	}*/

	public void receiveCommand(String m); /*{
		if(m.equals(FrameArmas.getPlacementDoneString())){
			FrameArmas.safeTerminate();
			TestadorListener.receiveCommand(FrameArmasListener.class);
		}

	}*/

	public void selectArma(Point p);/*{
		System.out.println("Cheguei FrameArmasListener.takeAction(Point)");
				ArmasPickPanel.getInstance().selectArmaAqui(p);
	}*/

	public void rememberBoard(int board); /*{
		TabuleiroListener.transferirTabuleiroInvisivel(FrameEmbateListener.getThisBoard(FrameEmbateListener.getInstance(),board),FrameArmas.getInstance().getBoard());
	}*/

	public void rotatePiece();/* {
		System.out.println("Cheguei FrameArmasListener.rotatePiece()");
		FrameArmas.getInstance().rotatePiece();

	}*/



}
