package PosArmas;

import java.awt.Point;
import java.awt.geom.Point2D;

import Armas.ArmaListener;
import Armas.ConjArmas;
import Armas.ExceptionArmVectFilled;
import Armas.ExceptionNoWeaponSelected;
import Tabuleiro.Celula;
import Tabuleiro.ExceptionCellAlreadyFilled;
//import Tabuleiro.ExceptionCellAlreadyHit;
import Tabuleiro.Tabuleiro;
import Tabuleiro.TabuleiroListener;
import TopoNivel.MyMouseListener;
public class TabuleiroArmas extends Tabuleiro{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String TAKE_ACTION_STRING = "TAB_TAKE_ACTION";

	public TabuleiroArmas(int x, int y, float boardsize, String player) {
		super(x, y, boardsize, player);
		setVisibilidade(true);
		addMouseListener(new MyMouseListener(getTakeActionString()));
	}

	public void takeAction(Point2D p) throws ExceptionCellAlreadyFilled {
		System.out.printf("\nCheguei TabuleiroArmas.takeAction(%s)\n",p.toString());
		try {
			int cel = (int)CELL_SIZE;
			System.out.printf("\nCheguei p = %s TabuleiroArmas.takeAction\n",getNewPointRelatively((Point) p).toString());
			getTabuleiroInvisivel().receiveArma(getNewPointRelatively((Point) p));
		}catch(ExceptionPlacingNotAllowed e){
			System.out.println("Cheguei ExceptionPlacingNotAllowed TabuleiroArmas.takeAction()");
			return;
		} catch (ExceptionNoWeaponSelected e) {
			System.out.println("Cheguei ExceptionNoWeaponSelected TabuleiroArmas.takeAction()");
			return;
		}
//		System.out.println("Cheguei setVisibilidade(true) TabuleiroArmas.takeAction");
		setVisibilidade(true);
	}

	public String getTakeActionString() {
		return TAKE_ACTION_STRING;
	}
	public boolean imHit(Point p){
		return false;
	}

}
