package TabuleiroPartida;

import java.awt.Point;
import java.io.IOException;

import javax.swing.JFrame;
import javax.xml.bind.Marshaller.Listener;

import PosArmas.FrameArmasListener;
import Tabuleiro.Tabuleiro;
import TopoNivel.GameFrame;
import TopoNivel.MyActionListener;
import TopoNivel.MyMouseListener;
import TopoNivel.TestadorListener;

public class FrameEmbateListener extends Listener{
	
	
	public static String getThisActionCommand(Class<?> class1){
		if(class1.isAssignableFrom(FrameEmbateMenuBar.class)){
//			System.out.println("Cheguei FrameEmbateMenuBar string FrameEmbateListener.getThisActionCommand()");
			System.out.println(FrameEmbate.getSaveString());
			return FrameEmbate.getSaveString();
		}
		else if(class1.isAssignableFrom(MyActionListener.class)){
//			System.out.println("Cheguei MyActionListener string FrameEmbateListener.getThisActionCommand()");
			return FrameEmbate.getBaseActionString();
		}
		else if(class1.isAssignableFrom(MyMouseListener.class)){
//			System.out.println("Cheguei MyMouseListener string FrameEmbateListener.getThisActionCommand()");
			return FrameEmbate.getTakeActionString();
		}
		else if(class1.isAssignableFrom(FrameArmasListener.class)){
//			System.out.println("Cheguei FrameEmbateMenuBar string FrameEmbateListener.getThisActionCommand()");
			System.out.println(FrameEmbate.getSaveString());
			return FrameEmbate.getSaveString();
		}
		return "<in FrameEmbateListener>YOU DON'T KNOW WHAT YOU'RE LOOKING FOR!";
	}
	public static void receiveCommand(String m) throws ExceptionBoardNotInstanced, IOException{

		System.out.printf("\nCheguei message: '%s' FrameEmbateListener.receiveCommand()\n",m);
		System.out.printf("\nCheguei BPS: '%s' FrameEmbateListener.receiveCommand()\n",FrameEmbate.getBeginPlayString());
		if(m.compareTo(FrameEmbate.getBeginPlayString())==0){
			System.out.println("Cheguei beginPlay FrameEmbateListener.receiveCommand()");
			FrameEmbate.getInstance().beginPlay();
		}
		else if(m.compareTo(FrameEmbate.getSwitchPlayersString())==0){
			System.out.println("Cheguei switchPlayers FrameEmbateListener.receiveCommand()");
			FrameEmbate.getInstance().switchPlayers();
		}
		else if(m.compareTo(FrameEmbate.getSaveString())==0){
			FrameEmbate.getInstance().saveBoards();
		}
		else if(m.compareTo(FrameEmbate.getLoadString())==0){
			try {
				FrameEmbate.getInstance().loadBoards();
			} catch (ClassNotFoundException e) {
				System.out.println("ClassNotFoundException in FrameEmbateListener.receiveCommand()");
//				e.printStackTrace();
			}
		}
		else if(m.compareTo(FrameEmbate.getEndPlayString())==0){
			TestadorListener.endedPlay();
		}
		else
			System.out.println("Cheguei else final FrameEmbateListener.receiveCommand()");
	}
	
	public static void takeAction(GameFrame g, String string, Point point){
//		System.out.println("Cheguei FrameEmbateListener.takeAction()");
		((FrameEmbate)g).takeAction(string, point);
	}
	public static FrameEmbate instance(String[] names) {
		return FrameEmbate.instance(names);
	}
	public static void addBoardIn(Object board, int board2) {
		// TODO Auto-generated method stub
		
	}
	public static FrameEmbate getInstance() throws ExceptionBoardNotInstanced {
		return FrameEmbate.getInstance();
	}
	public static Tabuleiro getThisBoard(FrameEmbate instance, int board) {
		return instance.getThisBoard(board);
	}
	public static String getThisActionCommand(String string) {
		return FrameEmbate.getThisActionCommand(string);
	}
}
