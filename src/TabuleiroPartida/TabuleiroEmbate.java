package TabuleiroPartida;

//import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

import Armas.ArmaListener;
import Armas.ConjArmas;
import Armas.ExceptionArrayNotFilled;
import Armas.ExceptionNoWeaponHere;
import Tabuleiro.Celula;
import Tabuleiro.ExceptionCellAlreadyHit;
import Tabuleiro.Tabuleiro;
import TopoNivel.MyMouseListener;

public class TabuleiroEmbate  extends Tabuleiro{

	private static final long serialVersionUID = 1L;

	public TabuleiroEmbate(int x, int y, float boardsize, String player) {
		super(x, y, boardsize, player);
		addMouseListener(new MyMouseListener(FrameEmbate.getTakeActionString()));
	}

	public void takeAction(Point2D p) throws ExceptionCellAlreadyHit{
		//		System.out.printf("\nCheguei TabuleiroEmbate.takeAction(%s)\n",p.toString());
		try {
			getTabuleiroInvisivel().getArmasArray().atingiuArma((int)p.getX(), (int)p.getY());
		} catch (ExceptionNoWeaponHere e1) {
			System.out.print("Foi na Agua!!\tTabuleiroEmbate.takeAction");
		} catch (ExceptionArrayNotFilled e1) {
			
		}
		
		try{
			Celula.hitCell(((Celula)getComponentAt((Point)p)));
		}
		catch(ClassCastException e){
			System.out.println("Não é Celula bobinho!");
		}
	}
	public boolean imHit(Point p){
		ConjArmas c = getTabuleiroInvisivel().getArmasArray();
		return ArmaListener.hasDestroyedPart(c,p.getLocation());
	}

}
