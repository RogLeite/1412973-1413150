package TabuleiroPartida;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JButton;

import Armas.ConjArmas;
import Armas.ExceptionArmVectFilled;
import Armas.ExceptionNoWeaponHere;
import Armas.SaveLoadGame;
import PlayerNameInput.FrameRegistroNomesListener;
import Tabuleiro.ExceptionCellAlreadyHit;
import Tabuleiro.Tabuleiro;
import TopoNivel.GameFrame;
import TopoNivel.MyActionListener;
import TopoNivel.MyMouseListener;

public class FrameEmbate extends GameFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static FrameEmbate tabuleiro;
	private static TabuleiroEmbate P1;
	private static TabuleiroEmbate P2;
	private JButton butt;
	private int charSize = 6;
	private static float SIZE_FACTOR = 20;
	private static float ALT_DEFAULT = 16*SIZE_FACTOR;
	private static float MARGIN = 1*SIZE_FACTOR;
	private static float LARG_DEFAULT = 2*ALT_DEFAULT;
	private static final String BEGIN_PLAY_STRING = "MATCH_BEGIN_PLAY";
	private static final String SWITCH_PLAYERS_STRING = "MATCH_TOGGLE_PLAYER";
	private static final int TAB_COUNT = 2;
	private static final int SHOTS_LEFT_DEFAULT = 3;
	private static final String SAVE_STRING = "MATCH_SAVE_BOARD";
	private static final String LOAD_STRING = "MATCH_LOAD_BOARD";
	private static final String BASE_ACTION_STRING = "MATCH";
	private static final String TAKE_ACTION_STRING = "MATCH_TAKE_ACTION";
	private static final String END_PLAY_STRING = "MATCH_END_PLAY";
	private static String Loser = null;
	private static int SHOTS_LEFT = SHOTS_LEFT_DEFAULT;
	private static float OVER_MARGIN = MARGIN*2;
	private FrameEmbate(String[] names){
		super();
		setTitle("Partida");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		CURR_FRAME = "MATCH";
		setBounds(0,0,(int)((LARG_DEFAULT+(MARGIN*4))+20),(int)(ALT_DEFAULT+(MARGIN*10)));


		setNeutralPlayer();

		P1 = new TabuleiroEmbate((int)MARGIN, (int)OVER_MARGIN,ALT_DEFAULT,getPlayerOption(1));
		//		System.out.printf("\t CURR_PLAYER: %s",P1.getBoundPlayer());

		P1.setVisibilidade(false);
		P1.setName(P1.getBoundPlayer()+" BOARD");

		P2 = new TabuleiroEmbate((int)(ALT_DEFAULT+3*MARGIN),(int)OVER_MARGIN,ALT_DEFAULT,getPlayerOption(2));
		//		System.out.printf("\t CURR_PLAYER: %s",P2.getBoundPlayer());

		P2.setVisibilidade(false);
		P2.setName(P2.getBoundPlayer()+" BOARD");

		P1.setLayout(null);
		P2.setLayout(null);

		getContentPane().add(P1,0);
		getContentPane().add(P2,1);

		butt = new JButton("Pronto");
		butt.setSize((int)(MARGIN*5), (int)(MARGIN*2));
		butt.setLocation((int)(getWidth()-butt.getWidth())/2,(int)(getHeight()-MARGIN*5));
		butt.addActionListener(new MyActionListener());
		butt.setActionCommand(getBeginPlayString());
		butt.setName("BUTTON at "+getTitle());
		butt.setIgnoreRepaint(true);
		butt.setEnabled(true);
		butt.setLayout(null);
		getContentPane().add(butt,2);

		//		butt = new JButton("Terminar");
		//		butt.setSize((int)(MARGIN*5), (int)(MARGIN*2));
		//		butt.setLocation((int)(getWidth()-butt.getWidth())/2,(int)(getHeight()-MARGIN*5));
		//		butt.addActionListener(new MyActionListener());
		//		butt.setActionCommand(getEndPlayString());
		//		butt.setName("BUTTON at "+getTitle());
		//		butt.setIgnoreRepaint(true);
		//		butt.setEnabled(false);
		//		butt.setVisible(false);
		//		butt.setLayout(null);
		//		getContentPane().add(butt,3);


		/*Adiciona a Barra de Menu*/

		setJMenuBar(FrameEmbateMenuBar.instanceEmbateMenuBar());


	}
	static String getEndPlayString() {
		return END_PLAY_STRING;
	}
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		if(Loser!=null){
			g2d.drawString(Loser,(int)(getWidth()-Loser.length()*charSize ),(int)(getHeight()-MARGIN*13/2));
		}

	}

	public static FrameEmbate instance(String[] names){
		//System.out.println("Cheguei TabuleiroEmbateEmbate.instance()");
		tabuleiro = new FrameEmbate(names);
		tabuleiro.setLayout(null);
		tabuleiro.setIgnoreRepaint(true);
		return tabuleiro;
	}
	public static FrameEmbate getInstance() throws ExceptionBoardNotInstanced{
		if(tabuleiro==null){
			throw new ExceptionBoardNotInstanced();
		}
		return tabuleiro;
	}


	protected void switchPlayers() {
		//		System.out.println("Cheguei TabuleiroEmbate.switchPlayers()");
		SHOTS_LEFT = SHOTS_LEFT_DEFAULT;
		((JButton)getContentPane().getComponent(2)).setEnabled(true);
		((JButton)getContentPane().getComponent(2)).setActionCommand(getBeginPlayString());
		((TabuleiroEmbate)(tabuleiro.getContentPane().getComponent(0))).setVisibilidade(false);
		((TabuleiroEmbate)(tabuleiro.getContentPane().getComponent(1))).setVisibilidade(false);
		tabuleiro.setNeutralPlayer();

	}
	protected void beginPlay(){
		//		System.out.println("Cheguei JButton able/disable TabuleiroEmbate.beginPlay()");
		if(getJMenuBar().getMenu(1)!=null){
			getJMenuBar().getMenu(1).setEnabled(false);
			getJMenuBar().remove(1);
			setJMenuBar(getJMenuBar());
		}
		tabuleiro.nextPlayer();
		((JButton)getContentPane().getComponent(2)).setEnabled(false);
		//		System.out.printf("\n\tNome: %s",((TabuleiroEmbate)(tabuleiro.getContentPane().getComponent(0))).getBoundPlayer());
		//		System.out.printf("\t CURR_PLAYER: %s",getCurrPlayer());
		//		System.out.printf("\n\tNome: %s",((TabuleiroEmbate)(tabuleiro.getContentPane().getComponent(1))).getBoundPlayer());
		//		System.out.printf("\t CURR_PLAYER: %s",getCurrPlayer());

		if(((TabuleiroEmbate)(tabuleiro.getContentPane().getComponent(0))).getBoundPlayer().equals(getCurrPlayer())){
			//			System.out.println("Cheguei P1 turn TabuleiroEmbateEmbate.beginPlay()");
			((TabuleiroEmbate)(tabuleiro.getContentPane().getComponent(0))).setVisibilidade(true);
			((TabuleiroEmbate)(tabuleiro.getContentPane().getComponent(0))).repaint(((TabuleiroEmbate)(tabuleiro.getContentPane().getComponent(1))).getBounds());
		}
		else{
			//			System.out.println("Cheguei P2 turn TabuleiroEmbateEmbate.beginPlay()");
			((TabuleiroEmbate)(tabuleiro.getContentPane().getComponent(1))).setVisibilidade(true);
			((TabuleiroEmbate)(tabuleiro.getContentPane().getComponent(1))).repaint(((TabuleiroEmbate)(tabuleiro.getContentPane().getComponent(0))).getBounds());
		}
	}
	public String getCurrFrame() {
		return CURR_FRAME;
	}
	public void denyedPlay() {
		setCanPlay(false);
	}
	protected void cellHit(TabuleiroEmbate t) {
		SHOTS_LEFT--;
		if(t.isAllDestroyed()){
			matchEnd(t.getBoundPlayer());
		}
		else{
			if(SHOTS_LEFT<=0){
				switchPlayers();
			}
		}
	}

	public void drawMessage(String s){
		System.out.printf("Cheguei DrawMessage (%s) in FrameEmbate", s);
		Graphics2D g2d = (Graphics2D)getGraphics();
		g2d.clearRect((int)(2*MARGIN),(int)( MARGIN*3)+3, (int)ALT_DEFAULT, -12);
		g2d.drawString(s, 2*MARGIN,MARGIN*4);
	}
	
	public void takeAction(String bound_player,Point p){
		for(int i=0;i<TAB_COUNT;i++){
			if(!currPlayerIsNeutral()){
				TabuleiroEmbate t = (TabuleiroEmbate)tabuleiro.getContentPane().getComponent(i);
				//System.out.printf("\nCheguei FrameEmbate.takeAction()\n\ttab.getBound: %s \n\tboundplayer: %s\n",((TabuleiroEmbate)tabuleiro.getContentPane().getComponent(i)).getBoundPlayer(),bound_player);
				if(t.getBoundPlayer().equals(bound_player)
						&& !t.getVisibilidade()/*getBoundPlayer().equals(getCurrPlayer())*/
						&& !bound_player.equals(getCurrPlayer())){
					try {
						t.takeAction(p);
						//System.out.println("Cheguei deu certo FrameEmbate.takeAction()");
						cellHit(t);
						return;
					} catch (ExceptionCellAlreadyHit e) {
						denyedPlay();
						drawMessage("Jogada inválida!");
						return;
					} catch (ExceptionNoWeaponHere e) {
						System.out.println("ExceptionNoWeaponHere FrameEmbate.takeAction");
						e.printStackTrace();
					}
				}
				else{}
			}
		}
	}
	private void matchEnd(String losingPlayer) {
		JButton butt = (JButton)getContentPane().getComponent(2);
		butt.setActionCommand(getEndPlayString());
		TabuleiroEmbate t1 = (TabuleiroEmbate)tabuleiro.getContentPane().getComponent(0);
		TabuleiroEmbate t2 = (TabuleiroEmbate)tabuleiro.getContentPane().getComponent(1);
		t1.setVisibilidade(true);
		t2.setVisibilidade(true);
		setNeutralPlayer();
		butt.setName("Terminar");
		butt.setEnabled(true);
		Loser = losingPlayer+" Perdeu!!!";

	}
	public static String getSaveString() {
		return SAVE_STRING;
	}
	static String getLoadString() {
		return LOAD_STRING;
	}
	public static String getBaseActionString() {
		return BASE_ACTION_STRING;
	}
	public static String getBeginPlayString() {
		return BEGIN_PLAY_STRING;
	}
	public static String getSwitchPlayersString() {
		return SWITCH_PLAYERS_STRING;
	}
	public void saveBoards() throws IOException {
		TabuleiroEmbate t1 = (TabuleiroEmbate)tabuleiro.getContentPane().getComponent(0);
		TabuleiroEmbate t2 = (TabuleiroEmbate)tabuleiro.getContentPane().getComponent(1);

		SaveLoadGame.SaveConjArmas(FrameRegistroNomesListener.getPlayerNames()[0], t1.getArmas());
		SaveLoadGame.SaveConjArmas(FrameRegistroNomesListener.getPlayerNames()[1], t2.getArmas());
		System.out.printf("\n/*************************/\n\tSALVEI O TABULEIROOOO\n/*************************/\n");

	}
	public void takeAction(Point p) {}
	public static String getTakeActionString() {
		return TAKE_ACTION_STRING;
	}
	public Tabuleiro getThisBoard(int board) {
		return (Tabuleiro)getContentPane().getComponent(board);
	}
	public static String getThisActionCommand(String string) {
		switch(string){
		case "SAVE": return getSaveString();
		case "LOAD": return getLoadString();
		}
		return null;
	}
	public void loadBoards() throws ClassNotFoundException, IOException, ExceptionArmVectFilled {
		
		TabuleiroEmbate t1 = (TabuleiroEmbate)tabuleiro.getContentPane().getComponent(0);
		TabuleiroEmbate t2 = (TabuleiroEmbate)tabuleiro.getContentPane().getComponent(1);

		ConjArmas c1=SaveLoadGame.LoadConjArmas(FrameRegistroNomesListener.getPlayerNames()[0]);
		ConjArmas c2=SaveLoadGame.LoadConjArmas(FrameRegistroNomesListener.getPlayerNames()[1]);
		t1.setArmas(c1);
		t2.setArmas(c2);
		
//		SaveLoadGame.LoadConjArmas(FrameRegistroNomesListener.getPlayerNames()[0], t1);
	//	SaveLoadGame.LoadConjArmas(FrameRegistroNomesListener.getPlayerNames()[1], t2);

//		SaveLoadGame.SaveConjArmas(FrameRegistroNomesListener.getPlayerNames()[0], t1.getArmas());
//		SaveLoadGame.SaveConjArmas(FrameRegistroNomesListener.getPlayerNames()[1], t2.getArmas());

		
		System.out.printf("\n/*************************/\n\tCARREGUEI O TABULEIROOOO\n/*************************/\n");

		
	}
}
