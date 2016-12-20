package Tabuleiro;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/*
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
*/
import javax.swing.JPanel;

import Armas.Arma;
import Armas.ArmaListener;
import Armas.ConjArmas;
import TopoNivel.MyMouseListener;

public class TabuleiroInvisivel extends JPanel{

	private static final long serialVersionUID = 1L;
	private static final String BASE_ACTION_STRING= "INV";
	private static final String TAKE_ACTION_STRING = "INV_TAKE_ACTION";
	private ConjArmas arrayArmas = ArmaListener.getEmptyArray();;
//	private final int SIDE_TAB = 16;
//	final float CELL_SIZE;
	public TabuleiroInvisivel(int boardsize){
		setBounds(0,0, boardsize, boardsize);
//		addMouseListener(new MyMouseListener(getTakeActionString()));
		setEnabled(true);
		setLayout(null);
		setIgnoreRepaint(false);
	}
	static String getTakeActionString() {
		return TAKE_ACTION_STRING;
	}
	public static TabuleiroInvisivel newInstance(int SIDE_TAB) {
		return new TabuleiroInvisivel(SIDE_TAB);
	}
	public static String getBaseActionString(){
		return BASE_ACTION_STRING;
	}
	public ConjArmas getArmasArray() {
		return arrayArmas;
	}

}

