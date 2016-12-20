package TabuleiroPartida;

//import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

import Tabuleiro.Celula;
import Tabuleiro.ExceptionCellAlreadyHit;
import Tabuleiro.Tabuleiro;

public class TabuleiroEmbate  extends Tabuleiro{

	private static final long serialVersionUID = 1L;

	public TabuleiroEmbate(int x, int y, float boardsize, String player) {
		super(x, y, boardsize, player);
	}

	public void takeAction(Point2D p) throws ExceptionCellAlreadyHit {
//		System.out.printf("\nCheguei TabuleiroEmbate.takeAction(%s)\n",p.toString());
		Celula.hitCell(((Celula)getComponentAt((Point)p)));
	}

}
