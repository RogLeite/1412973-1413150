package PosArmas;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.bind.Marshaller.Listener;

import Armas.ExceptionComponentIsNotArma;
import TopoNivel.GameFrame;
import TopoNivel.MyActionListener;
import TopoNivel.MyMouseListener;
import TopoNivel.TestadorListener;

public class FrameArmasListener extends Listener{
	public static String getThisActionCommand(Class<?> class1){
		if(class1.isAssignableFrom(MyMouseListener.class)){
			System.out.println("Cheguei MyMouseListener FrameArmasListener.getThisActionCommand()");
			return FrameArmas.getTakeActionString();
		}
		else if(class1.isAssignableFrom(MyActionListener.class)){
//			System.out.println("Cheguei MyActionListener FrameArmasListener.getThisActionCommand()");
			return FrameArmas.getBaseActionString();
		}
		return "<in FrameArmasListener>YOU DON'T KNOW WHAT YOU'RE LOOKING FOR!";
	}

	public static void takeAction(GameFrame g, Point point){
//		System.out.println("Cheguei FrameArmasListener.takeAction()");
		((FrameArmas)g).takeAction(point);
	}

	public static JFrame instance(String name) {
		return FrameArmas.instance(name);
	}

	public static void receiveCommand(String m) {
		if(m.equals(FrameArmas.getPlacementDoneString())){
			FrameArmas.safeTerminate();
			TestadorListener.receiveCommand(FrameArmasListener.class);
		}

	}

	public static void takeAction(Point p){
		System.out.println("Cheguei FrameArmasListener.takeAction(Point)");
				ArmasPickPanel.getInstance().selectArmaAqui(p);
	}



}
