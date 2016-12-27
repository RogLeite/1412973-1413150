package Alternativa;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PosArmas.ArmasPickPanel;
import PosArmas.FrameArmas;
import PosArmas.FrameArmasListener;
import Tabuleiro.TabuleiroListener;
import TopoNivel.MyMouseListener;

public class FrameArmaMouseListener implements MouseListener{
	
	
	
	public void mouseClicked(MouseEvent arg0) {
		Object o = arg0.getSource();
		if(arg0.getButton()==MouseEvent.BUTTON1){//Posicionamento de armas
				//			System.out.println("Cheguei CAPT MyMouseListener.mouseClicked()");
				//			System.out.printf("\nCheguei CAPT (%s) MyMouseListener.mouseClicked\n",arg0.getPoint().toString());
//				FrameArmasListener.selectArma(arg0.getPoint());
		}
		else if(arg0.getButton()==MouseEvent.BUTTON2){System.out.printf("Cheguei BUTTON3 o = %s MyMouseListener.MouseClicked\n", o.toString());
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
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
