package TopoNivel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import Tabuleiro.TabuleiroListener;

public class MyMouseMotionListener implements MouseMotionListener{

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		Object o = arg0.getSource();
		System.out.println("Cheguei instanceof TabuleiroListener MyMouseMotionListener.mouseMoved()");
		if(o instanceof TabuleiroListener){
			((TabuleiroListener)o).hoveredHere(arg0.getPoint());
		}
	}

}
