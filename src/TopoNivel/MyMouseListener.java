package TopoNivel;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import PosArmas.ArmasPickPanel;
import PosArmas.FrameArmas;
import PosArmas.FrameArmasListener;
import Tabuleiro.Tabuleiro;
import Tabuleiro.TabuleiroListener;
import TabuleiroPartida.FrameEmbate;
import TabuleiroPartida.FrameEmbateListener;

public class MyMouseListener implements MouseListener {
	private  final String CURR_FRAME;
	public MyMouseListener(String current_frame){
		super();
		//		System.out.println("Cheguei MyMouseListener()");
		CURR_FRAME = current_frame;
	}
	public void mouseClicked(MouseEvent arg0) {
		Object o = arg0.getSource();
		//		System.out.println("Cheguei MyMouseListener.mouseClicked()");
		if(arg0.getButton()==MouseEvent.BUTTON1){
			System.out.printf("Cheguei BUTTON1 o = %s MyMouseListener.MouseClicked\n", o.toString());
			try{
					//			System.out.println("Cheguei MATCH MyMouseListener.mouseClicked()");
					Tabuleiro t = (Tabuleiro)arg0.getSource();
					JPanel p = (JPanel) ((Component) o).getParent();
					JFrame f = (JFrame)(p.getParent().getParent().getParent());
					FrameEmbate g = (FrameEmbate)f;
					FrameEmbateListener.takeAction(g,t.getBoundPlayer(),arg0.getPoint());
				}
			catch(ClassCastException e){
				if(o instanceof TabuleiroListener/*CURR_FRAME.equals(TabuleiroListener.getThisActionCommand(MyMouseListener.class))*/){
					TabuleiroListener t = (TabuleiroListener)o;

					t.clicked(arg0.getPoint());
				}
				else if(o instanceof ArmasPickPanel){	//Posicionamento de armas
					//			System.out.println("Cheguei CAPT MyMouseListener.mouseClicked()");
					ArmasPickPanel f = (ArmasPickPanel) o;
					//			System.out.printf("\nCheguei CAPT (%s) MyMouseListener.mouseClicked\n",arg0.getPoint().toString());
					f.selectArmaAqui(arg0.getPoint());
				}
			}
		}
		else if(arg0.getButton()==MouseEvent.BUTTON3){
			System.out.printf("Cheguei BUTTON3 o = %s MyMouseListener.MouseClicked\n", o.toString());
			if(o instanceof FrameArmasListener){	//Posicionamento de armas
				FrameArmasListener f = (FrameArmasListener) o;
				f.rotatePiece();
			}
			if(o instanceof ArmasPickPanel){	//Posicionamento de armasJPanel p = (JPanel)t.getParent();
				JPanel p = (JPanel) ((JPanel) o).getParent();
				JFrame f = (JFrame)(p.getParent().getParent().getParent());
				((FrameArmas) f).rotatePiece();
			}
			if(o instanceof TabuleiroListener){	//Posicionamento de armas
				JPanel p = (JPanel) ((JPanel) o).getParent();
				JFrame f = (JFrame)(p.getParent().getParent().getParent());
				((FrameArmas) f).rotatePiece();
			}
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
