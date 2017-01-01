package PosArmas;

import java.awt.Point;
import java.awt.geom.Point2D;

import Armas.ArmaListener;
import Armas.ConjArmas;
import Armas.ExceptionArmVectFilled;
import Armas.ExceptionNoWeaponHere;
import Armas.ExceptionNoWeaponSelected;
import Tabuleiro.Celula;
import Tabuleiro.ExceptionCellAlreadyFilled;
//import Tabuleiro.ExceptionCellAlreadyHit;
import Tabuleiro.Tabuleiro;
import Tabuleiro.TabuleiroListener;
import TabuleiroPartida.FrameEmbateListener;
import TopoNivel.MyMouseListener;
import TopoNivel.MyMouseMotionListener;
public class TabuleiroArmas extends Tabuleiro{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static FrameArmas frameMae;
	private static final String TAKE_ACTION_STRING = "TAB_TAKE_ACTION";

	public TabuleiroArmas(int x, int y, float boardsize, String player,FrameArmas f) {
		super(x, y, boardsize, player);
		frameMae = f;
		setVisibilidade(true);
		addMouseListener(new MyMouseListener(getTakeActionString()));
		addMouseMotionListener(new MyMouseMotionListener());
	}

	public void takeAction(Point2D p) throws ExceptionCellAlreadyFilled {
		System.out.printf("\nCheguei TabuleiroArmas.takeAction(%s)\n",p.toString());
		try {
			int cel = (int)CELL_SIZE;
//			System.out.printf("\nCheguei p = %s TabuleiroArmas.takeAction\n",getNewPointRelatively((Point) p).toString());
			getTabuleiroInvisivel().receiveArma(getNewPointRelatively((Point) p));
		}catch(ExceptionPlacingNotAllowed e){
			System.out.println("Cheguei ExceptionPlacingNotAllowed TabuleiroArmas.takeAction()");
			return;
		} catch (ExceptionNoWeaponSelected e) {
			try {
				frameMae.receberArma(getTabuleiroInvisivel().devolvaArma(getNewPointRelatively((Point) p)));
			} catch (ExceptionNoWeaponHere e1) {
				System.out.println("ExceptionPlacingNotAllowed TabuleiroArmas.takeAction()");
			}
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
	
	public void hoveredHere(Point point) {
		Point p = getNewPointRelatively(point);
		MousePointRelative = p;
		getTabuleiroInvisivel().updateHover(p);
		setVisibilidade(true);
	}

	@Override
	public Point getMousePointRelative() {
		return MousePointRelative;
	}

}
