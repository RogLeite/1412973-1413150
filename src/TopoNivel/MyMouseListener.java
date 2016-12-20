package TopoNivel;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PosArmas.FrameArmasListener;
import Tabuleiro.Tabuleiro;
import Tabuleiro.TabuleiroListener;
import TabuleiroPartida.FrameEmbateListener;

public class MyMouseListener implements MouseListener {
	private  final String CURR_FRAME;
	public MyMouseListener(String current_frame){
		super();
		//		System.out.println("Cheguei MyMouseListener()");
		CURR_FRAME = current_frame;
	}
	public void mouseClicked(MouseEvent arg0) {
		//		System.out.println("Cheguei MyMouseListener.mouseClicked()");
		if(CURR_FRAME.equals(TabuleiroListener.getThisActionCommand(MyMouseListener.class))){

			Tabuleiro t = (Tabuleiro)((Component)arg0.getSource());
			
			 TabuleiroListener.clicked(t,arg0.getPoint());
		}
		else if(CURR_FRAME.equals(FrameEmbateListener.getThisActionCommand(MyMouseListener.class))){	//Partida
			//			System.out.println("Cheguei MATCH MyMouseListener.mouseClicked()");
			Tabuleiro t = (Tabuleiro)arg0.getSource();
			  
			  
			  			
			JPanel p = (JPanel)t.getParent();
			 JFrame f = (JFrame)(p.getParent().getParent().getParent());
			 GameFrame g = (GameFrame)f;
			 FrameEmbateListener.takeAction(g,t.getBoundPlayer(),arg0.getPoint());
		}
		else if(CURR_FRAME.equals(FrameArmasListener.getThisActionCommand(MyMouseListener.class))){	//Posicionamento de armas
			//			System.out.println("Cheguei CAPT MyMouseListener.mouseClicked()");
			JPanel t = (JPanel)arg0.getSource();
			
			//			System.out.printf("\nCheguei CAPT (%s) MyMouseListener.mouseClicked\n",arg0.getPoint().toString());
			FrameArmasListener.takeAction(arg0.getPoint());
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
